package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.service.FileCopier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioFileCopier implements FileCopier {

    @Override
    public void copyFile(Path srcFile, Path destDir) {
        var destFile = destDir.resolve(srcFile.getFileName());
        try {
            Files.copy(srcFile, destFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("cannot copy file.", e);
        }
    }
}
