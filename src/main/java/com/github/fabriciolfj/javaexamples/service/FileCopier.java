package com.github.fabriciolfj.javaexamples.service;

import java.nio.file.Path;

public interface FileCopier {

    void copyFile(final Path srcFile, final Path destDir);
}
