package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库驱动类
 *
 * @author Hany
 *
 */
public class ConnectToDataBase {
	public static Connection getDataBaseConnection() {
		String driver = "com.mysql.jdbc.Driver";//连接mysql用的数据库驱动类型
		String url = "jdbc:mysql://localhost:3306/game_plane?useSSL=false&useUnicode=true&characterEncoding=UTF-8";//指定字符的编码、解码格式
		String user = "root";
		String password = "123456";
		// 加载驱动程序
		try {
			Class.forName(driver);//返回与给定的字符串名称相关联类或接口的Class对象。初始化类,加载时使用的类加载器
			System.out.println("驱动程序加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 链接数据库
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);//类似登录数据库动作，返回一个数据库对象
			if (!conn.isClosed()) {
				System.out.println("连接数据库成功");
			}
		} catch (SQLException e) {
			System.out.println("链接数据库失败: " + e.getMessage());
		}
		return conn;
	}

}