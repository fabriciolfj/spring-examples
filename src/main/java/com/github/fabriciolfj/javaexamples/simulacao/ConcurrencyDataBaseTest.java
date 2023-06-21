package com.github.fabriciolfj.javaexamples.simulacao;

import com.github.fabriciolfj.javaexamples.repository.BookShop;
import org.apache.kafka.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcurrencyDataBaseTest {

    @Autowired
    private BookShop bookShop;

    public void execute() {
        var thread1 = new Thread(() -> bookShop.checkStock("0001"), "thread 1");
        var thread2 = new Thread(() -> {
            try {
                bookShop.increaseStock("0001", 5);
            } catch (Exception e) {

            }
        }, "thread 2");

        thread1.start();
        Utils.sleep(5000);
        thread2.start();

        /*var thread1 = new Thread(() -> {
            try {
                bookShop.increaseStock("0001", 15);
            } catch(RuntimeException e){
            }
        }, "Tópico 1");
        var thread2 = new Thread(() -> bookShop.checkStock("0001"), "Thread 2");
        thread1.start();
        Utils.sleep(10000);
        thread2.start();*/
    }
}
