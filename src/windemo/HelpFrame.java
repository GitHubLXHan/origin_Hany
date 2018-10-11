package windemo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Other.Father;

/**
 * 帮助说明界面面板
 *
 * @author Hany
 *
 */
public class HelpFrame extends JFrame {
	Father other = new Father();
	JPanel jpanelFather;
	JPanel BGPanel;
	ImageIcon imgBackGround = other.Img("image/helpbg.jpg", 400, 300);

	public HelpFrame(JPanel jpanelFather) {
		BGPanel = new BGPanel(jpanelFather);
		this.add(BGPanel);
		// 获取父窗口
		this.jpanelFather = jpanelFather;
		// 设置窗口大小
		this.setSize(400, 300);
		// 固定大小
		this.setResizable(false);
		// 默认窗口在屏幕的中间
		this.setLocationRelativeTo(null);
		// 设置标题
		this.setTitle("帮助关于");
		// 窗口监听器
		this.addWindowListener(new WindowAdapter() {
			// 窗口关闭事件
			public void windowClosing(WindowEvent e) {
				// 调用按钮是否可点击方法，设为可点击
				other.btnIsClick(jpanelFather, true);
			}
		});
	}

	public class BGPanel extends JPanel {
		JPanel jpanelFather;
		JTextArea help;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imgBackGround.getImage(), 0, 0, 400, 300, this);
		}

		public BGPanel(JPanel jpanelFather) {
			this.jpanelFather = jpanelFather;
			help = new JTextArea();
			help.setText("操作键：\n" + "上、下、左、右、(控制飞机移动);\n" + "1:空格键(发射普通子弹);\n" + "2:A、D控制飞机枪口方向;\n" + "3:S使用保护圈道具;\n"
					+ "4:R使用激光子弹;\n\n" + "制作者：16级-软工三班-刘晓瀚;\n" + "指导老师：徐婉珍;");
			help.setOpaque(false);
			help.setBounds(50, 20, 400, 300);
			help.setForeground(new Color(242, 10, 44));
			help.setFont(new Font("黑体", Font.BOLD, 20));
			this.add(help);
			this.setLayout(null);
			this.setVisible(true);

		}

	}

}
