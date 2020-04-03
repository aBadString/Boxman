//package gz.itcast.a_jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

//import org.junit.Test;
/**
 * jdbc连接数据库
 * @author APPle
 *
 */
public class mysql {
    //连接数据库的URL
    private String url = "jdbc:mysql://localhost:3306/day17";
                        // jdbc协议:数据库子协议:主机:端口/连接的数据库   //

    private String user = "root";//用户名
    private String password = "root";//密码
    
    /**
     * 第一种方法
     * @throws Exception
     
    @Test
    public void test1() throws Exception{
        //1.创建驱动程序类对象
        Driver driver = new com.mysql.jdbc.Driver(); //新版本
        //Driver driver = new org.gjt.mm.mysql.Driver(); //旧版本
        
        //设置用户名和密码
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        
        //2.连接数据库，返回连接对象
        Connection conn = driver.connect(url, props);
        
        System.out.println(conn);
    }
    */
    /**
     * 使用驱动管理器类连接数据库(注册了两次，没必要)
     * @throws Exception
     
    @Test
    public void test2() throws Exception{
        Driver driver = new com.mysql.jdbc.Driver();
        //Driver driver2 = new com.oracle.jdbc.Driver();
        //1.注册驱动程序(可以注册多个驱动程序)
        DriverManager.registerDriver(driver);
        //DriverManager.registerDriver(driver2);
        
        //2.连接到具体的数据库
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
        
    }*/
    
    /**
     * （推荐使用这种方式连接数据库）
     * 推荐使用加载驱动程序类  来 注册驱动程序 
     * @throws Exception
     */
    //@Test
    public void test3() throws Exception{
        //Driver driver = new com.mysql.jdbc.Driver();
        
        //通过得到字节码对象的方式加载静态代码块，从而注册驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        
        //Driver driver2 = new com.oracle.jdbc.Driver();
        //1.注册驱动程序(可以注册多个驱动程序)
        //DriverManager.registerDriver(driver);
        //DriverManager.registerDriver(driver2);
        
        //2.连接到具体的数据库
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
        
    }
    public static void main(String[] args) throws Exception
	{
        mysql l = new mysql();
        l.test3();
	}
}