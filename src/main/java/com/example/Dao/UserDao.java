package com.example.Dao;

import com.example.domain.User;
import com.example.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT UserID, UserName, Email, Phone FROM users ";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("UserID"));
                user.setName(rs.getString("UserName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // 这里可以换成日志记录
        }

        return users;
    }

    // 根据ID获取用户
    public User getUserById(int id) {
        String sql = "SELECT id, name, email, phone FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 根据用户名获取用户
    public User getUserByName(String name) {
        String sql = "SELECT id, name, email, phone FROM users WHERE name = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 添加新用户
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());

            int row = stmt.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 更新用户信息
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setInt(4, user.getId());

            int row = stmt.executeUpdate();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 删除用户
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int row = stmt.executeUpdate();
            if(row>0)conn.commit();
            else conn.rollback();
            return row > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
