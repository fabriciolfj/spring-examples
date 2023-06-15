package com.github.fabriciolfj.javaexamples.repository.impl;

import com.github.fabriciolfj.javaexamples.repository.BookShop;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JdbcBookShop implements BookShop {

    private DataSource dataSource;

    public JdbcBookShop(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void purchase(String isbn, String username) {
        try (var conn = dataSource.getConnection()) {
            int price;
            var PRICE_SQL = "SELECT PRICE FROM BOOK WHERE ISBN = ?";
            try (var stmt1 = conn.prepareStatement(PRICE_SQL)) {
                stmt1.setString(1, isbn);
                try (var rs = stmt1.executeQuery()) {
                    rs.next();
                    price = rs.getInt("PRICE");
                }
            }
            var STOCK_SQL = "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?";
            try (var stmt2 = conn.prepareStatement(STOCK_SQL)) {
                stmt2.setString(1, isbn);
                stmt2.executeUpdate();
                var BALANCE_SQL = "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?";
                try (var stmt3 = conn.prepareStatement(BALANCE_SQL)) {
                    stmt3.setInt(1, price);
                    stmt3.setString(2, username);
                    stmt3.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
