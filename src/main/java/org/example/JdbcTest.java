package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydb2?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String password = "1234";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, user, password);

            // SQL 쿼리 준비
            String sql = "SELECT order_id, customer_name FROM total_table";
            preparedStatement = connection.prepareStatement(sql);

            // 쿼리 실행
            resultSet = preparedStatement.executeQuery();

            // 결과 처리
            while (resultSet.next()) {
                int id = resultSet.getInt("order_id");
                String name = resultSet.getString("customer_name");
                System.out.println("order_id: " + id + ", customer_name: " + name);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
