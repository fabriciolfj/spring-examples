package com.github.fabriciolfj.javaexamples.simulacao;

import com.github.fabriciolfj.javaexamples.repository.BookShop;
import org.apache.kafka.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ConcorrenciaBase implements CommandLineRunner {

    @Autowired
    private ConcurrencyDataBaseTest test;

    @Override
    public void run(String... args) throws Exception {
        //test.execute();
    }
}
