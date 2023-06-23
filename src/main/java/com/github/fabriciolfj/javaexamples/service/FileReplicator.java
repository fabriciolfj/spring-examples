package com.github.fabriciolfj.javaexamples.service;

import java.io.IOException;

public interface FileReplicator {

    String getSrcDir();
    void setSrcDir(String srcDir);
    String getDestDir();
    void setDestDir(final String destDir);
    void replicate() throws IOException;
}
