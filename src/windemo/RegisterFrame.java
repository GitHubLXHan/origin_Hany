package windemo;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
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

import Other.Button;
import Other.Father;
import Other.Music;
import mysql.MysqlOperation;
import mysql.User;

/**
 * ע�ᴰ��
 *
 * @author Hany
 *
 */
public class RegisterFrame extends JFrame {
	int iconW = 30, iconH = 30;
	User user;
	JPanel BGPanel;
	JPanel jpanelFather;
	JTextField usernameJtf = null;
	JPasswordField passwordJpf1 = null;
	JPasswordField passwordJPf2 = null;
	JLabel usernameJla = null;
	JLabel passwordJla1 = null;
	JLabel passwordJla2 = null;
	JButton backBtn;
	JButton registerBtn;
	Father other = new Father();
	Button bu = new Button();
	Music mu = new Music();
	ImageIcon imgBackGround = other.Img("image/registerbg.jpg", 500, 300);
	ImageIcon imgusername = other.Img("image/usersign.png", iconW, iconH);
	ImageIcon imgpassword = other.Img("image/passwordsign.png", iconW, iconH);
	ImageIcon imgBorder = other.Img("image/bgborder.png", 250, 200);

	public RegisterFrame(JPanel jpanelFather, User user) {
		BGPanel = new BGPanel(jpanelFather, user);
		this.user = user;
		this.add(BGPanel);
		// ��ȡ������
		this.jpanelFather = jpanelFather;
		// ���ô��ڴ�С
		this.setSize(500, 300);
		// �̶���С
		this.setResizable(false);
		// Ĭ�ϴ�������Ļ���м�
		this.setLocationRelativeTo(null);
		// ���ñ���
		this.setTitle("��ע��");
		// ���ڼ�����
		this.addWindowListener(new WindowAdapter() {
			// ���ڹر��¼�
			public void windowClosing(WindowEvent e) {
				int count = jpanelFather.getComponentCount();// ��ȡ�����������
				// �������JButtonʱ����Ϊ��ʹ��
				for (int i = 0; i < count; i++) {
					Component comp = jpanelFather.getComponent(i);
					if (comp instanceof JButton) {
						JButton btn = (JButton) comp;

						btn.setEnabled(true);
					}
				}
			}
		});
	}

	/**
	 * ����ࣨ�ڲ��ࣩ���û���Ӱ�ť�����������
	 *
	 * @author Hany
	 *
	 */
	class BGPanel extends JPanel implements ActionListener {
		JPanel jpanelFather;
		User user;
		AudioClip clickMusic;

		/**
		 * ��������
		 */
		@Override
		public void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.drawImage(imgBackGround.getImage(), 0, 0, 500, 300, this);
			g.drawImage(imgBorder.getImage(), 110, 35, 250, 200, this);
		}

		public BGPanel(JPanel jpanelFather, User user) {
			this.user = user;
			this.jpanelFather = jpanelFather;
			usernameJla = new JLabel();
			usernameJla.setIcon(imgusername);
			usernameJla.setBounds(120, 50, iconW, iconH);

			usernameJtf = new JTextField("--�û���--");
			usernameJtf.setToolTipText("�������û�����");
			usernameJtf.setBounds(170, 50, 180, 30);
			usernameJtf.setOpaque(false);
			usernameJtf.setFont(new Font("����", Font.BOLD, 20));
			usernameJtf.setForeground(new Color(210, 200, 210));
			usernameJtf.setCaretColor(Color.RED);
			usernameJtf.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					// TODO Auto-generated method stub
					if (e.getSource() == usernameJtf) {
						if (usernameJtf.getText().equals("")) {
							usernameJtf.setText("--�û���--");
						}
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (usernameJtf.getText().equals("--�û���--")) {
						usernameJtf.setText("");
					}
				}
			});

			passwordJla1 = new JLabel();
			passwordJla1.setIcon(imgpassword);
			passwordJla1.setBounds(120, 90, iconW, iconH);

			passwordJpf1 = new JPasswordField("---����---");
			passwordJpf1.setOpaque(false);
			passwordJpf1.setEchoChar('\0');
			passwordJpf1.setToolTipText("����������!");
			passwordJpf1.setBounds(170, 90, 180, 30);
			passwordJpf1.setFont(new Font("����", Font.BOLD, 20));
			passwordJpf1.setForeground(new Color(210, 200, 210));
			passwordJpf1.setCaretColor(Color.RED);
			passwordJpf1.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					char[] p = passwordJpf1.getPassword();
					String password = new String(p);
					if (password.equals("")) {
						passwordJpf1.setEchoChar('\0');
						passwordJpf1.setText("---����---");
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					char[] p = passwordJpf1.getPassword();
					String password = new String(p);
					if (password.equals("---����---")) {
						passwordJpf1.setEchoChar('*');
						passwordJpf1.setText("");
					}
				}
			});

			passwordJla2 = new JLabel();
			passwordJla2.setIcon(imgpassword);
			passwordJla2.setBounds(120, 130, iconW, iconH);

			passwordJPf2 = new JPasswordField("-ȷ������-");
			passwordJPf2.setOpaque(false);
			passwordJPf2.setEchoChar('\0');
			passwordJPf2.setToolTipText("��ȷ������");
			passwordJPf2.setBounds(170, 130, 180, 30);
			passwordJPf2.setFont(new Font("����", Font.BOLD, 20));
			passwordJPf2.setForeground(new Color(210, 200, 210));
			passwordJPf2.setCaretColor(Color.RED);
			passwordJPf2.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					char[] p = passwordJPf2.getPassword();
					String password = new String(p);
					if (password.equals("")) {
						passwordJPf2.setEchoChar('\0');
						passwordJPf2.setText("-ȷ������-");
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					char[] p = passwordJPf2.getPassword();
					String password = new String(p);
					if (password.equals("-ȷ������-")) {
						passwordJPf2.setEchoChar('*');
						passwordJPf2.setText("");
					}
				}
			});

			backBtn = bu.button(130, 180, 40, 35, "image/backsign.png", true, this);
			backBtn.addActionListener(this);
			registerBtn = bu.button(185, 180, 150, 35, "image/registernow.png", true, this);
			registerBtn.addActionListener(this);

			clickMusic = mu.music("music/click1.wav");
			this.add(usernameJla);
			this.add(usernameJtf);
			this.add(passwordJla1);
			this.add(passwordJpf1);
			this.add(passwordJla2);
			this.add(passwordJPf2);
			this.add(registerBtn);
			this.add(backBtn);
			this.setLayout(null);
			this.setVisible(true);
		}

		/**
		 * ��ť����������ť������һϵ�ж���
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == backBtn) {
				clickMusic.play();
				LoginFrame login = new LoginFrame(this.jpanelFather, this.user);
				login.setVisible(true);
				login.setAlwaysOnTop(true);
				dispose();
			} else if (e.getSource() == registerBtn) {
				clickMusic.play();
				String userN = usernameJtf.getText();
				char[] passW1 = passwordJpf1.getPassword();
				String password1 = new String(passW1);
				char[] passW2 = passwordJPf2.getPassword();
				String password2 = new String(passW2);

				if (userN.equals("") || userN.equals("--�û���--")) {
					JOptionPane.showMessageDialog(this, "��������Ĵ������r(�s���t)�q", "��������Ĵ�����", JOptionPane.PLAIN_MESSAGE);
				} else if (password1.equals("") || password1.equals("---����---")) {
					JOptionPane.showMessageDialog(this, "������������룡�r(�s���t)�q", "�������������룡", JOptionPane.PLAIN_MESSAGE);
				} else if (password2.equals("") || password2.equals("-ȷ������-")) {
					JOptionPane.showMessageDialog(this, "����ȷ�����룡�r(�s���t)�q", "�ٴ��������룡", JOptionPane.PLAIN_MESSAGE);
				} else if (!password1.equals(password2) && !userN.equals("")) {
					JOptionPane.showMessageDialog(this, "�������벻һ�£��r(�s���t)�q", "���벻һ��", JOptionPane.PLAIN_MESSAGE);
				} else if (!userN.equals("") && !password1.equals("") && !password2.equals("")
						&& password1.equals(password2) && !userN.equals("")) {
					String userReally = userN.toString().trim().replaceAll(" ", "");
					String passwordReally1 = password1.toString().trim().replaceAll(" ", "");
					// ���ж��û��Ƿ��Ѿ����ڣ��ڽ���ע��
					if (!userN.equals(userReally) || !password1.equals(passwordReally1)) {
						JOptionPane.showMessageDialog(this, "�����пո񣡣���", "�����飿����", JOptionPane.PLAIN_MESSAGE);
					} else {
						this.user.setName(userReally);
						this.user.setPassword(passwordReally1);
						ResultSet rs = new MysqlOperation().search(this.user);
						boolean isHave = true;
						System.out.println(userReally + " " + passwordReally1);
						try {
							System.out.println(rs.next());
							if (rs.next()) {
								JOptionPane.showMessageDialog(this, "ע��ʧ�ܣ��ô����Ѵ��ڣ�", "ע��ʧ�ܣ�", JOptionPane.PLAIN_MESSAGE);
								isHave = false;
							}
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

						if (isHave) {
							new MysqlOperation().insert(this.user);
							JOptionPane.showMessageDialog(this, "��ϲ�㣬" + userReally + ", ���Կ�ʼ��Ϸ�ˣ�", "ע��ɹ���",
									JOptionPane.PLAIN_MESSAGE);
							LoginFrame login = new LoginFrame(this.jpanelFather, this.user);
							login.setVisible(true);
							login.setAlwaysOnTop(true);
							dispose();// �رմ���
						}

					}
				}
			}
		}

	}

}
