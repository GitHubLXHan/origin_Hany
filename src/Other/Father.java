package Other;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * �����ࡢ������
 *
 * @author Hany
 *
 */
public class Father {
	int x, y, width, height;
	JPanel jpanel;
	ImageIcon img;
	String imgPath;

	public Father() {
	}

	public Father(int x, int y, int width, int height, String imgPath, JPanel jpanel) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.imgPath = imgPath;
		this.jpanel = jpanel;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * ����ͼƬ
	 *
	 * @param g
	 */
	public void draw(Graphics g) {
		// ����� �ϻ���ͼƬ
		g.drawImage(this.img.getImage(), this.x, this.y, this.width, this.height, this.jpanel);
	}

	/**
	 * ����ͼƬ
	 */
	public void makeImg() {
		// ����һ��ͼƬ
		this.img = new ImageIcon(imgPath);
		// �õ����ź��ͼƬ
		this.img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
	}

	/**
	 * ��ͼƬ�ı������ַ��
	 *
	 * @param num
	 *            ����
	 * @param g
	 *            Graphics�����
	 * @param jpanel
	 *            ������
	 * @param x
	 *            ����x
	 * @param y����y
	 * @param width
	 *            ���
	 * @param height
	 *            �߶�
	 */
	public void num(int num, Graphics g, JPanel jpanel, int x, int y, int width, int height) {
		int hundredsDigit = num / 100;
		int unitsDigit = (num % 100) / 10;
		int tenDigit = num % 10;
		ImageIcon img = null;
		ImageIcon img1 = null;
		ImageIcon img2 = null;
		switch (hundredsDigit) {
		case 0:
			img = Img("image/0.png", width, height);
			break;
		case 1:
			img = Img("image/1.png", width, height);
			break;
		case 2:
			img = Img("image/2.png", width, height);
			break;
		case 3:
			img = Img("image/3.png", width, height);
			break;
		case 4:
			img = Img("image/4.png", width, height);
			break;
		case 5:
			img = Img("image/5.png", width, height);
			break;
		case 6:
			img = Img("image/6.png", width, height);
			break;
		case 7:
			img = Img("image/7.png", width, height);
			break;
		case 8:
			img = Img("image/8.png", width, height);
			break;
		case 9:
			img = Img("image/9.png", width, height);
			break;
		}
		if (hundredsDigit != 0) {
			g.drawImage(img.getImage(), x, y, width, height, jpanel);
		}

		switch (unitsDigit) {
		case 0:
			img1 = Img("image/0.png", width, height);
			break;
		case 1:
			img1 = Img("image/1.png", width, height);
			break;
		case 2:
			img1 = Img("image/2.png", width, height);
			break;
		case 3:
			img1 = Img("image/3.png", width, height);
			break;
		case 4:
			img1 = Img("image/4.png", width, height);
			break;
		case 5:
			img1 = Img("image/5.png", width, height);
			break;
		case 6:
			img1 = Img("image/6.png", width, height);
			break;
		case 7:
			img1 = Img("image/7.png", width, height);
			break;
		case 8:
			img1 = Img("image/8.png", width, height);
			break;
		case 9:
			img1 = Img("image/9.png", width, height);
			break;
		}
		if (unitsDigit != 0) {
			g.drawImage(img1.getImage(), x + 30, y, width, height, jpanel);
		}
		switch (tenDigit) {
		case 0:
			img2 = Img("image/0.png", width, height);
			break;
		case 1:
			img2 = Img("image/1.png", width, height);
			break;
		case 2:
			img2 = Img("image/2.png", width, height);
			break;
		case 3:
			img2 = Img("image/3.png", width, height);
			break;
		case 4:
			img2 = Img("image/4.png", width, height);
			break;
		case 5:
			img2 = Img("image/5.png", width, height);
			break;
		case 6:
			img2 = Img("image/6.png", width, height);
			break;
		case 7:
			img2 = Img("image/7.png", width, height);
			break;
		case 8:
			img2 = Img("image/8.png", width, height);
			break;
		case 9:
			img2 = Img("image/9.png", width, height);
			break;
		}
		g.drawImage(img2.getImage(), x + 60, y, width, height, jpanel);
	}

	/**
	 * ����ͼƬ
	 *
	 * @param imgPath
	 *            ͼƬ·��
	 * @param width
	 *            ͼƬ���
	 * @param height
	 *            ͼƬ�߶�
	 * @return ImageIcon
	 */
	public ImageIcon Img(String imgPath, int width, int height) {
		// ����ͼƬ
		ImageIcon img = new ImageIcon(imgPath);
		// �õ����ź��ͼƬ��
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return img;

	}

	/**
	 * ���ð�ť�Ƿ���Ե��
	 *
	 * @param jpanel
	 */
	public void btnIsClick(JPanel jpanel, boolean is) {
		int count = jpanel.getComponentCount();// ��ȡ�����������
		// �������JButtonʱ����Ϊ��ʹ��
		for (int i = 0; i < count; i++) {
			Component comp = jpanel.getComponent(i);
			if (comp instanceof JButton) {
				JButton btn = (JButton) comp;
				btn.setEnabled(is);

			}
		}
	}
}
