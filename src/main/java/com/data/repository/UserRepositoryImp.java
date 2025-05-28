package com.data.repository;

import com.data.connection.ConnectionDB;
import com.data.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository {
    @Override
    public List<User> getAllUser() {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<User> users = new ArrayList<User>();
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call get_all_users()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean addUser(User user) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call add_new_usser(?, ?, ?)}");
            cstmt.setString(1, user.getUsername());
            cstmt.setString(2, user.getPassword());
            cstmt.setString(3, user.getEmail());
            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return false;
    }
}
