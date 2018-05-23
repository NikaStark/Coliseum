package com.coliseum.model.services.util;

import java.sql.Connection;
import java.sql.SQLException;

class AutoRollback implements AutoCloseable {

    private Connection conn;
    private boolean committed;

    AutoRollback(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public void commit() throws SQLException {
        conn.commit();
        committed = true;
    }

    @Override
    public void close() throws SQLException {
        if (!committed) {
            conn.rollback();
        }
    }

}