package com.sp.unitt.files.domain;


import com.sp.unitt.TestApp;
import com.sp.unitt.files.service.FileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApp.class)
public class 루트디렉토리_스프링_기본경로 {

    @Autowired
    private FileService fileService;

    @DisplayName("1. 루트 디렉토리 기본경로가 설정되는지 확인한다. ")
    @Test
    void test_1() throws IOException {
        assertThat(Path.of(System.getProperty("user.home")+"/dev/test")).isEqualTo(fileService.getRoot());
        FileSystemUtils.deleteRecursively(Path.of(System.getProperty("user.home")+"/dev/test"));
    }

}
