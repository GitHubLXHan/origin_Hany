package Other;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * �л��ӵ���
 *
 * @author Hany
 *
 */
public class Bullet {
	int x, y, buWidth, buHeight;
	JPanel jpanel;
	String imgPath;
	ImageIcon img;

	public Bullet() {
	}

	public Bullet(int x, int y, int buWidth, int buHeight, String imgPath, JPanel jpanel) {
		super();
		this.x = x;
		this.y = y;
		this.buWidth = buWidth;
		this.buHeight = buHeight;
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

	public void draw(Graphics g) {
		// ����� �ϻ���ͼƬ
		g.drawImage(this.img.getImage(), this.x, this.y, this.buWidth, this.buHeight, this.jpanel);

	}

	public void makeImg() {
		// ����һ��ͼƬ
		this.img = new ImageIcon(this.imgPath);
		// �õ����ź��ͼƬ
		this.img.setImage(img.getImage().getScaledInstance(buWidth, buHeight, Image.SCALE_DEFAULT));

	}

	public void makeImg2() {
		// ����һ��ͼƬ
		this.img = new ImageIcon("image/bullet1.png");
		// �õ����ź��ͼƬ
		this.img.setImage(img.getImage().getScaledInstance(buWidth, buHeight, Image.SCALE_DEFAULT));

	}

	// �ӵ������ƶ�
	public void moveDown() {
		this.y += 7;
	}
}
