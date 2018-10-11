package windemo;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Other.Button;
import Other.Father;
import Other.Music;
import mysql.User;

/**
 * 开始界面面板类
 *
 * @author Hany
 *
 */
public class StartPanel extends JPanel implements ActionListener {
	JButton playBtn;
	JButton paiBtn;
	JButton helpBtn;
	JButton musicOpBtn;
	JButton musicClBtn;
	JButton logoutBtn;

	JFrame jframe;
	AudioClip bgMusic;
	AudioClip click;
	Father other = new Father();
	Button bu = new Button();
	Music mu = new Music();
	User user;

	public StartPanel() {

	}

	public StartPanel(JFrame jframe, User user) {
		this.user = user;
		this.jframe = jframe;
		this.jframe.add(this);
		this.setLayout(null);

		// 设置按钮
		playBtn = bu.button(300, 620, 150, 50, "image/play.png", true, this);
		paiBtn = bu.button(620, 250, 120, 50, "image/paibutton.png", true, this);
		helpBtn = bu.button(300, 690, 150, 50, "image/help.png", true, this);
		musicOpBtn = bu.button(300, 750, 150, 60, "image/soundOp.png", true, this);
		musicClBtn = bu.button(300, 800, 150, 60, "image/soundCl.png", true, this);
		logoutBtn = bu.button(670, 20, 50, 50, "image/logout.png", false, this);
		if (user.getId() != 0) {// 当已经登录账户时显示此按钮
			logoutBtn.setVisible(true);
		}
		// 添加按钮监听器
		playBtn.addActionListener(this);
		paiBtn.addActionListener(this);
		helpBtn.addActionListener(this);
		musicOpBtn.addActionListener(this);
		musicClBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		// 添加背景音乐
		bgMusic = mu.music("music/bgMusic2.wav");
		bgMusic.loop();
		click = mu.music("music/click1.wav");
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

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {

		ImageIcon startBgImg = new ImageIcon("image/startBg.png");
		g.drawImage(startBgImg.getImage(), 0, 0, 750, 1000, this);
	}

	// 按钮监听器
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playBtn) {
			if (user.getId() == 0) {
				StartPanel.this.setEnabled(false);// 先将主界面设置为不可操作状态
				JFrame login = new LoginFrame(this, user);
				// 调用按钮是否可点击方法，设为可点击
				other.btnIsClick(this, false);
				login.setVisible(true);
				login.setAlwaysOnTop(true);
				// 按钮音乐
				click.play();
			} else {
				// 不显示面板
				StartPanel.this.setVisible(false);
				// 背景音乐关闭
				bgMusic.stop();
				// 按钮音乐
				click.play();
				// 把窗口传递给面板
				new GamePanel(jframe, user, this);
			}

		} else if (e.getSource() == this.getComponentAt(670, 20)) {
			int n = JOptionPane.showConfirmDialog(null, "想退出此账号吗?", "注销？", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				this.getComponentAt(670, 20).setVisible(false);
				this.user.setId(0);
			}
		} else if (e.getSource() == musicClBtn) {
			// 背景音乐关闭
			bgMusic.stop();
			// 按钮音乐
			click.play();

		} else if (e.getSource() == musicOpBtn) {
			// 背景音乐关闭
			bgMusic.loop();
			// 按钮音乐
			click.play();

		} else if (e.getSource() == paiBtn) {
			JFrame ranging = new RankingFrame(this);
			// 调用按钮是否可点击方法，设为可点击
			other.btnIsClick(this, false);
			ranging.setVisible(true);
			ranging.setAlwaysOnTop(true);
			// 按钮音乐
			click.play();

		} else if (e.getSource() == helpBtn) {
			JFrame help = new HelpFrame(this);
			// 调用按钮是否可点击方法，设为可点击
			other.btnIsClick(this, false);
			help.setVisible(true);
			help.setAlwaysOnTop(true);
			// 按钮音乐
			click.play();
		}

	}

}
