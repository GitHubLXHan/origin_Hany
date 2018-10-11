package Other;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends Father {

	public Button() {

	}

	/**
	 * ������ť����
	 *
	 * @param x
	 *            ����x
	 * @param y
	 *            ����y
	 * @param width
	 *            ��ť���
	 * @param height
	 *            ��ť�߶�
	 * @param imgPath
	 *            ��ťͼƬ·��
	 * @param visible
	 *            �Ƿ�ɼ�
	 * @param jpanel
	 *            ������
	 * @return JButton����
	 */
	public JButton button(int x, int y, int width, int height, String imgPath, boolean visible, JPanel jpanel) {
		// ����ͼƬ
		img = new ImageIcon(imgPath);
		// �õ����ź��ͼƬ
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		// ����һ����ť
		JButton button = new JButton();
		// ���ð�ť�ޱ߿�
		button.setBorder(null);
		// ����ͼƬΪ��ť
		button.setIcon(img);
		// �����ư�ť����
		button.setContentAreaFilled(false);
		// ���ð�ť�Ĵ�Сλ��
		button.setBounds(x, y, width, height);
		// �����Ʊ߿�
		button.setBorderPainted(false);
		// ��ȡ����
		button.setFocusable(false);
		// ��ť�Ƿ���ʾ
		button.setVisible(visible);
		// �Ѱ�ť��ӵ������
		jpanel.add(button);
		// jpanel.requestFocus();
		return button;
	}
}
