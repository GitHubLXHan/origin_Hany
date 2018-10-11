package windemo;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Other.Button;
import Other.Father;
import Other.Music;
import mysql.MysqlOperation;

/**
 * ���а洰����
 *
 * @author Hany
 *
 */
public class RankingFrame extends JFrame {
	Father other = new Father();
	Button bu = new Button();
	Music mu = new Music();
	JPanel jpanelFather;
	JPanel BGPanel;
	JButton backBtn;
	ImageIcon imgBackGround = other.Img("image/pai.jpg", 500, 800);

	public RankingFrame(JPanel jpanelFather) {
		BGPanel = new BGPanel(jpanelFather);
		this.add(BGPanel);
		// ��ȡ������
		this.jpanelFather = jpanelFather;
		// ���ô��ڴ�С
		this.setSize(500, 800);
		// �̶���С
		this.setResizable(false);
		// Ĭ�ϴ�������Ļ���м�
		this.setLocationRelativeTo(null);
		// ���ñ���
		this.setTitle("���а�");
		// ���ڼ�����
		this.addWindowListener(new WindowAdapter() {
			// ���ڹر��¼�
			public void windowClosing(WindowEvent e) {
				// ���ð�ť�Ƿ�ɵ����������Ϊ�ɵ��
				other.btnIsClick(jpanelFather, true);
			}
		});
	}

	/**
	 * �ڲ��ࡢע�������壬�ڴ������������
	 *
	 * @author Hany
	 *
	 */
	public class BGPanel extends JPanel implements ActionListener {
		AudioClip clickMusic;
		JPanel jpanelFather;
		JLabel[] scoreJla;
		JLabel[] nameJla;
		ArrayList<String> arrayName = new ArrayList<String>();
		ArrayList<Integer> arrayScore = new ArrayList<Integer>();

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imgBackGround.getImage(), 0, 0, 500, 800, this);
		}

		public BGPanel(JPanel jpanelFather) {
			this.jpanelFather = jpanelFather;
			backBtn = bu.button(170, 670, 140, 60, "image/back.png", true, this);
			backBtn.addActionListener(this);

			ResultSet rs = new MysqlOperation().sort();
			try {
				while (rs.next()) {
					// �����ݴ���ArrayList������
					arrayName.add(rs.getString("name"));
					arrayScore.add(rs.getInt("score"));
					System.out.println(rs.getString("name") + "��" + rs.getInt("score"));
				}
				scoreJla = new JLabel[arrayName.size()];// ��������JLabel����
				nameJla = new JLabel[arrayScore.size()];// �����û���JLabel����

				// ����
				// int size = arrayName.size(), temp;
				// String Stringtemp;
				// for (int i = 0; i < size; i++) {
				// int k = i;
				// for (int j = size - 1; j > i; j--) {
				// if (arrayScore.get(j) < arrayScore.get(k))
				// k = j;
				// }
				// temp = arrayScore.get(i);
				// Stringtemp = arrayName.get(i);
				// arrayScore.set(i, arrayScore.get(k));
				// arrayName.set(i, arrayName.get(k));
				// arrayScore.set(i, temp);
				// arrayName.set(i, Stringtemp);
				// System.out.println(arrayScore.get(i));
				// }
				// ð������
				String Stringtemp;
				int temp; // ��¼��ʱ�м�ֵ
				int size = arrayName.size(); // �����С
				for (int i = 0; i < size - 1; i++) {
					for (int j = i + 1; j < size; j++) {
						if (arrayScore.get(i) < arrayScore.get(j)) { // ����������λ��
							temp = arrayScore.get(i);
							Stringtemp = arrayName.get(i);
							arrayScore.set(i, arrayScore.get(j));
							arrayName.set(i, arrayName.get(j));
							arrayScore.set(j, temp);
							arrayName.set(j, Stringtemp);
						}
					}
				}
				int y = 100;
				for (int i = 0; i <= arrayName.size() - 1; i++, y += 50) {
					// �û���
					nameJla[i] = new JLabel(arrayName.get(i));
					nameJla[i].setBounds(200, y, 70, 50);
					nameJla[i].setFont(new Font("Font.BOLD", Font.ITALIC, 22));
					nameJla[i].setForeground(Color.cyan);
					scoreJla[i] = new JLabel(arrayScore.get(i).toString() + "�֣�");
					scoreJla[i].setBounds(300, y, 80, 50);
					scoreJla[i].setFont(new Font("Font.BOLD", Font.ITALIC, 22));
					scoreJla[i].setForeground(Color.green);

					this.add(nameJla[i]);
					this.add(scoreJla[i]);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			clickMusic = mu.music("music/click1.wav");
			this.setLayout(null);
			this.setVisible(true);

		}

		/**
		 * ʵ�ּ�������
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == backBtn) {
				clickMusic.play();
				// ���ð�ť�Ƿ�ɵ����������Ϊ�ɵ��
				other.btnIsClick(jpanelFather, true);
				mu.music("music/click1.wav").play();
				dispose();// �رմ���
			}
		}
	}

}
