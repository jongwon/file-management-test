package com.sp.unitt.files.service;


import com.sp.unitt.files.error.FileError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
public class FileService {

    @Value("${sp.file.root:${user.home}/dev/test}")
    private String rootPath;

    private Path root;

    public void setRoot(Path _root) throws IOException {
        this.root = _root;
        ensurePath();
    }

    public Path getRoot(){
        return this.root;
    }

    @PostConstruct
    private void ensurePath() throws IOException {
        if(root == null) this.root = Path.of(this.rootPath);
        if(!Files.exists(this.root)){
            Files.createDirectories(this.root);
        }
        log.info("Files root dir : {}", this.root.toFile().getAbsolutePath());
    }

    public Path getFile(String fileName) {
        if(fileName.startsWith("/")) fileName = fileName.substring(1);
        Path path =  this.root.resolve(fileName);
        if(!isChildPath(root, path)) throw new FileError(101, "루트 경로를 벗어남.");
        return path;
    }

    private boolean isChildPath(Path parent, Path child) {
        Path p = parent.normalize();
        Path c = child.normalize();
        return c.startsWith(p);
    }


}
