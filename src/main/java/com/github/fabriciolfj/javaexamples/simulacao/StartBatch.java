package com.github.fabriciolfj.javaexamples.simulacao;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartBatch implements CommandLineRunner {

    @Autowired
    private JobLauncher jobExecution;

    @Autowired
    @Qualifier("userjob")
    private Job job;

    @Override
    public void run(String... args) throws Exception {
        jobExecution.run(job, new JobParameters());
    }
}
