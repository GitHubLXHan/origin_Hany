package windemo;

import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JFrame;

import mysql.User;

/**
 * ����ࡢ��ʼ������
 *
 * @author Hany
 *
 */
public class StartFrame extends JFrame {
	StartPanel jpanel;
	Statement stmt;
	User user;

	public StartFrame() throws SQLException {
		user = new User();
		System.out.println(user.getId());		System.out.println(user.getName());
		// ������ʼ����
		jpanel = new StartPanel(StartFrame.this, user);
		// ���ô��ڴ�С
		this.setSize(750, 1000);
		// �̶���С
		this.setResizable(false);
		// ʹ���ڿɼ�
		this.setVisible(true);
		// Ĭ�ϴ�������Ļ���м�
		this.setLocationRelativeTo(null);
		// ���ñ���
		this.setTitle("�ɻ���սV1.0");
		// �������봰��
		this.add(jpanel);
		// ����Ĭ�Ϲرմ��������˹رհ�ťʱ��������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
