package windemo;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Other.Button;
import Other.Father;
import Other.Music;
import mysql.MysqlOperation;
import mysql.User;

/**
 * 登录窗口
 *
 * @author Hany
 *
 */
public class LoginFrame extends JFrame {
	int iconW = 30, iconH = 30;
	User user;
	JPanel jpanelFather;
	JPanel BGPanel;
	Button bu = new Button();
	Music mu = new Music();
	Father other = new Father();
	ImageIcon imgBackGround = other.Img("image/loginbg.jpg", 500, 300);
	ImageIcon imgusername = other.Img("image/usersign.png", iconW, iconH);
	ImageIcon imgpassword = other.Img("image/passwordsign.png", iconW, iconH);
	ImageIcon imgBorder = other.Img("image/bgborder.png", 250, 150);

	public LoginFrame(JPanel jpanelFather, User user) {
		BGPanel = new BGPanel(jpanelFather, user, this);
		this.user = user;
		this.add(BGPanel);
		// 获取父窗口
		this.jpanelFather = jpanelFather;
		// 设置窗口大小
		this.setSize(500, 300);
		// 固定大小
		this.setResizable(false);
		// 默认窗口在屏幕的中间
		this.setLocationRelativeTo(null);
		// 设置标题
		this.setTitle("请登录");
		// 窗口监听器
		this.addWindowListener(new WindowAdapter() {
			// 窗口关闭事件
			public void windowClosing(WindowEvent e) {
				// /调用按钮是否可点击方法，设为可点击
				other.btnIsClick(jpanelFather, true);
			}
		});
		/**
		 * 设置提示框风格
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 面板类（内部类），用户添加按钮、输入框等组件
	 *
	 * @author Hany
	 *
	 */
	class BGPanel extends JPanel implements ActionListener {
		JPanel jpanelFather;
		JTextField usernameJtf = null;
		JPasswordField passwordJpf1 = null;
		JLabel usernameJla = null;
		JLabel passwordJla1 = null;
		JButton loginBtn;
		JButton registerBtn;
		JButton QQBtn;
		JButton WeChatBtn;
		JButton weiboBtn;
		User user;
		LoginFrame loginframe;
		AudioClip clickMusic;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imgBackGround.getImage(), 0, 0, 500, 300, this);
			g.drawImage(imgBorder.getImage(), 110, 35, 250, 150, this);
		}

		public BGPanel(JPanel jpanelFather, User user, LoginFrame loginframe) {
			this.jpanelFather = jpanelFather;
			this.user = user;
			this.loginframe = loginframe;
			usernameJla = new JLabel();
			usernameJla.setIcon(imgusername);
			usernameJla.setBounds(120, 50, iconW, iconH);

			usernameJtf = new JTextField("-用户名-");
			usernameJtf.setToolTipText("请输入用户名");
			usernameJtf.setBounds(170, 50, 180, 30);
			usernameJtf.setOpaque(false);
			usernameJtf.setFont(new Font("黑体", Font.BOLD, 20));
			usernameJtf.setForeground(new Color(210, 200, 210));
			usernameJtf.setCaretColor(Color.RED);
			usernameJtf.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					if (usernameJtf.getText().equals("")) {
						usernameJtf.setText("-用户名-");
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (usernameJtf.getText().equals("-用户名-")) {
						usernameJtf.setText("");// 让文本为空白
					}
				}
			});
			passwordJla1 = new JLabel();
			passwordJla1.setIcon(imgpassword);
			passwordJla1.setBounds(120, 90, iconW, iconH);

			passwordJpf1 = new JPasswordField("--密码--");
			passwordJpf1.setOpaque(false);
			passwordJpf1.requestFocus();
			passwordJpf1.setEchoChar('\0');
			passwordJpf1.setToolTipText("请输入密码!");
			passwordJpf1.setBounds(170, 90, 180, 30);
			passwordJpf1.setOpaque(false);
			passwordJpf1.setFont(new Font("黑体", Font.BOLD, 20));
			passwordJpf1.setForeground(new Color(210, 200, 210));
			passwordJpf1.setCaretColor(Color.RED);
			passwordJpf1.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
					char[] p = passwordJpf1.getPassword();
					String password = new String(p);
					if (password.equals("--密码--")) {
						passwordJpf1.setEchoChar('*');
						passwordJpf1.setText("");
					}
				}

				@Override
				public void focusLost(FocusEvent e) {
					char[] p = passwordJpf1.getPassword();
					String password = new String(p);
					if (password.equals("")) {
						passwordJpf1.setEchoChar('\0');
						passwordJpf1.setText("--密码--");
					}
				}
			});

			loginBtn = bu.button(170, 140, 70, 35, "image/login.png", true, this);
			loginBtn.addActionListener(this);
			registerBtn = bu.button(280, 140, 70, 35, "image/register.png", true, this);
			registerBtn.addActionListener(this);
			QQBtn = bu.button(180, 200, iconW, iconW, "image/QQ.png", true, this);
			QQBtn.addActionListener(this);
			WeChatBtn = bu.button(230, 200, iconW, iconW, "image/WeChat.png", true, this);
			WeChatBtn.addActionListener(this);
			weiboBtn = bu.button(280, 200, iconW, iconW, "image/weibo.png", true, this);
			weiboBtn.addActionListener(this);

			clickMusic = mu.music("music/click1.wav");
			this.add(usernameJla);
			this.add(usernameJtf);
			this.add(passwordJla1);
			this.add(passwordJpf1);
			this.setLayout(null);
			this.setVisible(true);
		}

		/**
		 * 实现监听方法
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginBtn) {
				clickMusic.play();
				String userN = usernameJtf.getText();
				// String passW = passwordJpf1.getPassword().toString();
				char[] passW = passwordJpf1.getPassword();
				String password = new String(passW);
				// String s = passwordJpf
				// String
				// regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
				// Pattern p = Pattern.compile(regEx);
				// Matcher username = p.matcher(userN);
				// Matcher password = p.matcher(passW);
				// System.out.println(username.matches() + " "
				// +password.matches());
				if (userN.equals("") || userN.equals("-用户名-")) {
					JOptionPane.showMessageDialog(this, "请输入你的大名！╮(╯▽╰)╭", "请输入你的大名", JOptionPane.PLAIN_MESSAGE);
				} else if (password.equals("") || password.equals("--密码--")) {
					JOptionPane.showMessageDialog(this, "请输入你的密码！╮(╯▽╰)╭", "请输入密码", JOptionPane.PLAIN_MESSAGE);
				} else if (!userN.equals("") && !password.equals("")) {

					String userReally = userN.toString().trim().replaceAll(" ", "");
					String passwordReally = password.toString().trim().replaceAll(" ", "");
					if (!userN.equals(userReally) || !password.equals(passwordReally)) {
						JOptionPane.showMessageDialog(this, "不能有空格！！！", "搞事情？？？", JOptionPane.PLAIN_MESSAGE);
					} else {
						this.user.setName(userReally);
						this.user.setPassword(passwordReally);
						ResultSet rs = new MysqlOperation().search(this.user);

						try {
							boolean error = true;
							if (rs.next()) {
								if (userReally.equals((String) rs.getString("name"))
										&& passwordReally.equals((String) rs.getString("password"))) {
									this.user.setId(rs.getInt("id"));
									JOptionPane.showMessageDialog(this, "恭喜你，" + userReally + "，登陆成功！╮(╯▽╰)╭", "登录成功！",
											JOptionPane.PLAIN_MESSAGE);
									jpanelFather.getComponentAt(670, 20).setVisible(true);
									// 调用按钮是否可点击方法，设为可点击
									other.btnIsClick(jpanelFather, true);
									dispose();// 关闭窗口
								}
								error = false;
							}

							if (error) {
								JOptionPane.showMessageDialog(this, "登录失败，大名或密码错了?", "登录失败！！！",
										JOptionPane.PLAIN_MESSAGE);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}

			} else if (e.getSource() == registerBtn) {
				clickMusic.play();
				RegisterFrame register = new RegisterFrame(this.jpanelFather, this.user);
				register.setVisible(true);// 设置窗口可见
				register.setAlwaysOnTop(true);// 设置为始终在窗口最顶层
				dispose();
			} else if (e.getSource() == QQBtn || e.getSource() == WeChatBtn || e.getSource() == weiboBtn) {
				clickMusic.play();
				// 弹出提示框
				JOptionPane.showMessageDialog(this, "此功能还未开通！╮(╯▽╰)╭", "未开通", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

}
