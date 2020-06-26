package db;

import java.sql.*;

public class DB {
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConn() throws SQLException {
        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/medicine?useUnicode=true&characterEncoding=utf8";
        return DriverManager.getConnection(url, user, password);
    }
    public static void release(ResultSet set,Statement stmt,Connection conn){
        if(set!=null){
            try{
                set.close();
            }catch(SQLException e){

                e.printStackTrace();
            }
        }
        if(stmt!=null){
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
