package interview_05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleTest {
    public Connection getConnection() {
        Connection conn = null;
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "${url}"; // 代表url
        String user = "user";
        String pw = "password";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void printResult() {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        conn = getConnection();
        String sql = "${Query}";
        try {
            pstat = conn.prepareStatement(sql); // 代表sql语句
            rs = pstat.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("${columnName}")); // 代表列名
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstat != null) {
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
