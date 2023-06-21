package com.github.fabriciolfj.javaexamples.repository.impl;

import com.github.fabriciolfj.javaexamples.repository.BookShop;
import com.zaxxer.hikari.util.SuspendResumeLock;
import org.apache.kafka.common.utils.Utils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import javax.sql.DataSource;
import java.io.IOException;


@Repository
public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

    public TransactionalJdbcBookShop(final DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
        rollbackFor = IOException.class,
        noRollbackFor = ArithmeticException.class)
    public void purchase(String isbn, String username) {
        var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = ?";
        int price = getJdbcTemplate().queryForObject(BOOK_SQL, Integer.class, isbn);
        var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?";
        getJdbcTemplate().update(STOCK_SQL, isbn);
        var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?";
        getJdbcTemplate().update(BALANCE_SQL, price, username);
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = 30, readOnly = true)
    public void increaseStock(String isbn, int stock) {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(threadName + " - Prepare to increase book stock");
            var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK + ? WHERE ISBN = ?";
            getJdbcTemplate().update(STOCK_SQL, stock, isbn);
            System.out.println(threadName + " - Book stock increased by " + stock);
            sleep(threadName);
        } catch (Exception e) {
            System.out.println(threadName + " - Book stock rolled back");
            throw new RuntimeException("Increased by mistake");
        }

    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int checkStock(String isbn) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Prepare to check book stock");
        var STOCK_SQL = "SELECT STOCK FROM BOOK_STOCK WHERE ISBN = ?";
        int stock = getJdbcTemplate().queryForObject(STOCK_SQL, Integer.class, isbn);
        System.out.println(threadName + " - Book stock is " + stock);
        sleep(threadName);
        int stock2 = getJdbcTemplate().queryForObject(STOCK_SQL, Integer.class, isbn);
        System.out.println(threadName + " - book stock after sleep " + stock2);
        return stock;
    }

    private void sleep(String threadName) {
        System.out.println(threadName + " - Sleeping");
        Utils.sleep(20000);
        System.out.println(threadName + " - Wake up");
    }
}
