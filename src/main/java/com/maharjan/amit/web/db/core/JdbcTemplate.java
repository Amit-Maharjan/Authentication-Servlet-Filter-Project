package com.maharjan.amit.web.db.core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    private Connection conn = null;

    private void connect() throws SQLException, ClassNotFoundException {
        String hostUrl = "jdbc:mysql://localhost/example?useSSL=false";
        String username = "root";
        String password = "root";
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(hostUrl, username, password);
    }

    public List<T> query(String sql, RowMapper<T> mapper) throws SQLException, ClassNotFoundException {
        List<T> rows = new ArrayList<>();
        connect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            rows.add(mapper.mapRow(rs));
        }
        conn.close();
        return rows;
    }

    public T queryForObject(String sql, Object[] args, RowMapper<T> mapper) throws SQLException, ClassNotFoundException {
        T row = null;
        connect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParameters(args, stmt);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            row = mapper.mapRow(rs);
        }
        conn.close();
        return row;
    }

    public int update(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        connect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        setParameters(params, stmt);
        int result = stmt.executeUpdate();
        conn.close();
        return result;
    }

    private void setParameters(Object[] params, PreparedStatement stmt) throws SQLException {
        int counter = 1;
        for (Object param : params) {
            stmt.setObject(counter, param);
            counter++;
        }
    }
}
