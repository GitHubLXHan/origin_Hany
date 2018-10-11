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
 * 排行版窗口类
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
		// 获取父窗口
		this.jpanelFather = jpanelFather;
		// 设置窗口大小
		this.setSize(500, 800);
		// 固定大小
		this.setResizable(false);
		// 默认窗口在屏幕的中间
		this.setLocationRelativeTo(null);
		// 设置标题
		this.setTitle("排行榜");
		// 窗口监听器
		this.addWindowListener(new WindowAdapter() {
			// 窗口关闭事件
			public void windowClosing(WindowEvent e) {
				// 调用按钮是否可点击方法，设为可点击
				other.btnIsClick(jpanelFather, true);
			}
		});
	}

	/**
	 * 内部类、注册界面面板，在此面板上添加组件
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
					// 将数据存入ArrayList数组中
					arrayName.add(rs.getString("name"));
					arrayScore.add(rs.getInt("score"));
					System.out.println(rs.getString("name") + "　" + rs.getInt("score"));
				}
				scoreJla = new JLabel[arrayName.size()];// 创建积分JLabel数组
				nameJla = new JLabel[arrayScore.size()];// 创建用户名JLabel数组

				// 排序
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
				// 冒泡排序
				String Stringtemp;
				int temp; // 记录临时中间值
				int size = arrayName.size(); // 数组大小
				for (int i = 0; i < size - 1; i++) {
					for (int j = i + 1; j < size; j++) {
						if (arrayScore.get(i) < arrayScore.get(j)) { // 交换两数的位置
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
					// 用户名
					nameJla[i] = new JLabel(arrayName.get(i));
					nameJla[i].setBounds(200, y, 70, 50);
					nameJla[i].setFont(new Font("Font.BOLD", Font.ITALIC, 22));
					nameJla[i].setForeground(Color.cyan);
					scoreJla[i] = new JLabel(arrayScore.get(i).toString() + "分！");
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
		 * 实现监听方法
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == backBtn) {
				clickMusic.play();
				// 调用按钮是否可点击方法，设为可点击
				other.btnIsClick(jpanelFather, true);
				mu.music("music/click1.wav").play();
				dispose();// 关闭窗口
			}
		}
	}

}
