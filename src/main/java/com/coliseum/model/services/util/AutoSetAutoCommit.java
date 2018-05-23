package com.coliseum.model.services.util;

import java.sql.Connection;
import java.sql.SQLException;

class AutoSetAutoCommit implements AutoCloseable {

    private Connection conn;
    private boolean originalAutoCommit;

    AutoSetAutoCommit(Connection conn, boolean autoCommit) throws SQLException {
        this.conn = conn;
        originalAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(autoCommit);
    }

    @Override
    public void close() throws SQLException {
        conn.setAutoCommit(originalAutoCommit);
    }

}