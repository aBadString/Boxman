import java.sql.*;

public class sql
{
    public static void main(String[] args)
    {
        Driver d = new sun.jdbc.odbc.JdbcOdbcDriver; // 创建驱动程序实例
        DriverManager.registerDriver(d); // 注册驱动
        // 创建与数据库之间的连接
        Connection con = DriverManager.getConnection("jdbc:odbc:wombat", "login", "password");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT a, b, c FORM Table");
        while(rs.next())
        {
            int x = rs.getInt("a");
            String s = rs.getString("b");
            float f = rs.getFloat("c");
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}