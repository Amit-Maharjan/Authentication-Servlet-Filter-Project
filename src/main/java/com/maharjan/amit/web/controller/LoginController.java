/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.maharjan.amit.web.controller;

import com.maharjan.amit.web.db.core.JdbcTemplate;
import com.maharjan.amit.web.model.User;
import com.maharjan.amit.web.util.Md5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student99
 */
@WebServlet(name = "login", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (login(username, Md5.getMd5(password))) {
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedIn", true);
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/login?error");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "select * from user where username=? and password=?;";
        User user =  jdbcTemplate.queryForObject(sql, new Object[]{username, password}, rs -> mapData(rs));
        return user != null;
    }

    private User mapData(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedDate(rs.getDate("created_date"));
        user.setModifiedDate(rs.getDate("modified_date"));
        return user;
    }
}
