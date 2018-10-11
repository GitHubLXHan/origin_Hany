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
 * ����˵���������
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
		// ��ȡ������
		this.jpanelFather = jpanelFather;
		// ���ô��ڴ�С
		this.setSize(400, 300);
		// �̶���С
		this.setResizable(false);
		// Ĭ�ϴ�������Ļ���м�
		this.setLocationRelativeTo(null);
		// ���ñ���
		this.setTitle("��������");
		// ���ڼ�����
		this.addWindowListener(new WindowAdapter() {
			// ���ڹر��¼�
			public void windowClosing(WindowEvent e) {
				// ���ð�ť�Ƿ�ɵ����������Ϊ�ɵ��
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
			help.setText("��������\n" + "�ϡ��¡����ҡ�(���Ʒɻ��ƶ�);\n" + "1:�ո��(������ͨ�ӵ�);\n" + "2:A��D���Ʒɻ�ǹ�ڷ���;\n" + "3:Sʹ�ñ���Ȧ����;\n"
					+ "4:Rʹ�ü����ӵ�;\n\n" + "�����ߣ�16��-������-�����;\n" + "ָ����ʦ��������;");
			help.setOpaque(false);
			help.setBounds(50, 20, 400, 300);
			help.setForeground(new Color(242, 10, 44));
			help.setFont(new Font("����", Font.BOLD, 20));
			this.add(help);
			this.setLayout(null);
			this.setVisible(true);

		}

	}

}
