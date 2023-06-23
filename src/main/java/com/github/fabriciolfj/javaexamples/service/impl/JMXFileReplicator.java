package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.service.FileCopier;
import com.github.fabriciolfj.javaexamples.service.FileReplicator;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ManagedResource
public class JMXFileReplicator implements FileReplicator {
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;

    @ManagedAttribute(description = "obter diretorio de origem")
    public String getSrcDir() {
        return srcDir;
    }

    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
    }

    public FileCopier getFileCopier() {
        return fileCopier;
    }

    public void setFileCopier(FileCopier fileCopier) {
        this.fileCopier = fileCopier;
    }

    @Override
    @ManagedOperation(description = "replicar arquivos")
    public synchronized void replicate() throws IOException {
        var files = Path.of(srcDir);
        try (var fileList = Files.list(files)) {
            fileList.filter(Files::isRegularFile)
                    .forEach(it -> fileCopier.copyFile(it, Path.of(destDir)));
        }
    }
}
