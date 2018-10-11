package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���ݿ�������
 *
 * @author Hany
 *
 */
public class ConnectToDataBase {
	public static Connection getDataBaseConnection() {
		String driver = "com.mysql.jdbc.Driver";//����mysql�õ����ݿ���������
		String url = "jdbc:mysql://localhost:3306/game_plane?useSSL=false&useUnicode=true&characterEncoding=UTF-8";//ָ���ַ��ı��롢�����ʽ
		String user = "root";
		String password = "123456";
		// ������������
		try {
			Class.forName(driver);//������������ַ���������������ӿڵ�Class���󡣳�ʼ����,����ʱʹ�õ��������
			System.out.println("����������سɹ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// �������ݿ�
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);//���Ƶ�¼���ݿ⶯��������һ�����ݿ����
			if (!conn.isClosed()) {
				System.out.println("�������ݿ�ɹ�");
			}
		} catch (SQLException e) {
			System.out.println("�������ݿ�ʧ��: " + e.getMessage());
		}
		return conn;
	}

}