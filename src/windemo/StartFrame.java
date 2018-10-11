package windemo;

import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JFrame;

import mysql.User;

/**
 * 入口类、开始窗口类
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
		// 启动开始界面
		jpanel = new StartPanel(StartFrame.this, user);
		// 设置窗口大小
		this.setSize(750, 1000);
		// 固定大小
		this.setResizable(false);
		// 使窗口可见
		this.setVisible(true);
		// 默认窗口在屏幕的中间
		this.setLocationRelativeTo(null);
		// 设置标题
		this.setTitle("飞机大战V1.0");
		// 把面板加入窗口
		this.add(jpanel);
		// 设置默认关闭处理，当按了关闭按钮时结束程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
