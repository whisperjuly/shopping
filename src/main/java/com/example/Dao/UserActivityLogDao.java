package com.example.Dao;

import com.example.domain.UserActivityLog;
import com.example.utils.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserActivityLogDao {
    /**
     * 获取购买记录
     */
    public List<UserActivityLog> getPurchaseLogs() {
        List<UserActivityLog> logs = new ArrayList<>();
        String sql = "SELECT u.UserID AS userId, p.name AS productName, o.created_at AS activityTime, '购买' AS actionType " +
                "FROM users u " +
                "JOIN orders o ON u.UserID = o.user_id " +
                "JOIN order_items oi ON o.id = oi.order_id " + // 连接 order_items 表
                "JOIN products p ON oi.product_id = p.id " + // 通过 order_items 连接 products
                "WHERE o.created_at IS NOT NULL " +
                "ORDER BY o.created_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserActivityLog log = new UserActivityLog();
                log.setUserId(rs.getInt("userId"));
                log.setProductName(rs.getString("productName"));
                log.setActivityTime(rs.getTimestamp("activityTime"));
                log.setActionType(rs.getString("actionType"));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    /**
     * 获取浏览记录
     */
    public List<UserActivityLog> getBrowseLogs() {
        List<UserActivityLog> logs = new ArrayList<>();
        String sql = "SELECT u.UserID AS userId, p.name AS productName, c.added_at AS activityTime, '加入购物车' AS actionType " +
                "FROM users u " +
                "JOIN cart c ON u.UserID = c.user_id " +
                "JOIN products p ON c.product_id = p.id " +
                "WHERE c.added_at IS NOT NULL " +
                "ORDER BY c.added_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserActivityLog log = new UserActivityLog();
                log.setUserId(rs.getInt("userId"));
                log.setProductName(rs.getString("productName"));
                log.setActivityTime(rs.getTimestamp("activityTime"));
                log.setActionType(rs.getString("actionType"));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
