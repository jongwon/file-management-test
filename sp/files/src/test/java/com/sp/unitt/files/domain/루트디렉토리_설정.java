package com.sp.unitt.files.domain;

import com.sp.unitt.files.error.FileError;
import com.sp.unitt.files.service.FileService;
import org.junit.jupiter.api.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class 루트디렉토리_설정 {

    private FileService fileService;
    private Path root;

    @BeforeEach
    void before() throws IOException {
        fileService = new FileService();

        Path fileRoot = new ClassPathResource("fileRoot").getFile().toPath();
        this.root = fileRoot.resolve(""+Math.abs(new Random().nextLong()));
        fileService.setRoot(root);
    }

    @AfterEach
    void after() throws IOException {
        FileSystemUtils.deleteRecursively(root);
    }


    @DisplayName("1. 루트디렉토리를 설정한다.")
    @Test
    void test_1(){
        assertThat(Files.exists(this.root)).isTrue();
        assertThat(fileService.getRoot()).isEqualTo(root);
    }

    @DisplayName("2. 루트 디렉토리 밑의 상대경로로 파일을 접근한다.")
    @Test
    void test_2(){
        Path filePath = fileService.getFile("test.txt");
        assertThat(filePath.getParent()).isEqualTo(this.root);

        filePath = fileService.getFile("/dirpath/filename.txt");
        assertThat(filePath.getParent().getParent()).isEqualTo(this.root);
    }


    @DisplayName("3. 루트 디렉토리 경로를 벗어난 접근은 허용하지 않는다.")
    @Test
    void test_3(){
        FileError error = assertThrows(FileError.class, () -> fileService.getFile(".."));
        assertThat(error.getErrorMessage().getCode()).isEqualTo(101);
    }



}
