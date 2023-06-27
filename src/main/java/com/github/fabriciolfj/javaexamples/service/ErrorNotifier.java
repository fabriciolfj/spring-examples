package com.github.fabriciolfj.javaexamples.service;

public interface ErrorNotifier {

    void notifyCopyError(final String srcDir, String destDir, String fileName);
}
