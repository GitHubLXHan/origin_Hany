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
 * ��ʼ���������
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

		// ���ð�ť
		playBtn = bu.button(300, 620, 150, 50, "image/play.png", true, this);
		paiBtn = bu.button(620, 250, 120, 50, "image/paibutton.png", true, this);
		helpBtn = bu.button(300, 690, 150, 50, "image/help.png", true, this);
		musicOpBtn = bu.button(300, 750, 150, 60, "image/soundOp.png", true, this);
		musicClBtn = bu.button(300, 800, 150, 60, "image/soundCl.png", true, this);
		logoutBtn = bu.button(670, 20, 50, 50, "image/logout.png", false, this);
		if (user.getId() != 0) {// ���Ѿ���¼�˻�ʱ��ʾ�˰�ť
			logoutBtn.setVisible(true);
		}
		// ��Ӱ�ť������
		playBtn.addActionListener(this);
		paiBtn.addActionListener(this);
		helpBtn.addActionListener(this);
		musicOpBtn.addActionListener(this);
		musicClBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		// ��ӱ�������
		bgMusic = mu.music("music/bgMusic2.wav");
		bgMusic.loop();
		click = mu.music("music/click1.wav");
		/**
		 * ������ʾ����
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException exception) {
			exception.printStackTrace();
		}
	}

	// �̶�����ͼƬ���������JPanel������ͼƬ������������
	protected void paintComponent(Graphics g) {

		ImageIcon startBgImg = new ImageIcon("image/startBg.png");
		g.drawImage(startBgImg.getImage(), 0, 0, 750, 1000, this);
	}

	// ��ť������
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playBtn) {
			if (user.getId() == 0) {
				StartPanel.this.setEnabled(false);// �Ƚ�����������Ϊ���ɲ���״̬
				JFrame login = new LoginFrame(this, user);
				// ���ð�ť�Ƿ�ɵ����������Ϊ�ɵ��
				other.btnIsClick(this, false);
				login.setVisible(true);
				login.setAlwaysOnTop(true);
				// ��ť����
				click.play();
			} else {
				// ����ʾ���
				StartPanel.this.setVisible(false);
				// �������ֹر�
				bgMusic.stop();
				// ��ť����
				click.play();
				// �Ѵ��ڴ��ݸ����
				new GamePanel(jframe, user, this);
			}

		} else if (e.getSource() == this.getComponentAt(670, 20)) {
			int n = JOptionPane.showConfirmDialog(null, "���˳����˺���?", "ע����", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				this.getComponentAt(670, 20).setVisible(false);
				this.user.setId(0);
			}
		} else if (e.getSource() == musicClBtn) {
			// �������ֹر�
			bgMusic.stop();
			// ��ť����
			click.play();

		} else if (e.getSource() == musicOpBtn) {
			// �������ֹر�
			bgMusic.loop();
			// ��ť����
			click.play();

		} else if (e.getSource() == paiBtn) {
			JFrame ranging = new RankingFrame(this);
			// ���ð�ť�Ƿ�ɵ����������Ϊ�ɵ��
			other.btnIsClick(this, false);
			ranging.setVisible(true);
			ranging.setAlwaysOnTop(true);
			// ��ť����
			click.play();

		} else if (e.getSource() == helpBtn) {
			JFrame help = new HelpFrame(this);
			// ���ð�ť�Ƿ�ɵ����������Ϊ�ɵ��
			other.btnIsClick(this, false);
			help.setVisible(true);
			help.setAlwaysOnTop(true);
			// ��ť����
			click.play();
		}

	}

}
