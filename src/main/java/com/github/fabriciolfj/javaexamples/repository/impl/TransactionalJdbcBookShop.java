package com.github.fabriciolfj.javaexamples.repository.impl;

import com.github.fabriciolfj.javaexamples.repository.BookShop;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import javax.sql.DataSource;

@Repository
public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

    private final TransactionTemplate transactionTemplate;

    public TransactionalJdbcBookShop(final TransactionTemplate transactionTemplate, final DataSource dataSource) {
        this.transactionTemplate = transactionTemplate;
        setDataSource(dataSource);
    }

    @Override
    public void purchase(String isbn, String username) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus ts) {
                var BOOK_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = ?";
                int price = getJdbcTemplate().queryForObject(BOOK_SQL, Integer.class, isbn);
                var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?";
                getJdbcTemplate().update(STOCK_SQL, isbn);
                var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?";
                getJdbcTemplate().update(BALANCE_SQL, price, username);
            }
        });
    }
}
