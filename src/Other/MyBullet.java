package Other;

import javax.swing.JPanel;

public class MyBullet extends Father {

	public MyBullet(int x, int y, int width, int height, String imgPath, JPanel jpanel) {
		super(x, y, width, height, imgPath, jpanel);
	}

	public MyBullet() {
	}

	// �ӵ������ƶ�
	public void moveUp() {
		this.y -= 12;
	}

	// �ӵ������ƶ�
	public void moveLeft1() {
		this.x -= 12;
	}

	// �ӵ������ƶ�
	public void moveLeft2() {
		this.x -= 16;
	}

	// �ӵ������ƶ�
	public void moveRight1() {
		this.x += 12;
	}

	// �ӵ������ƶ�
	public void moveRight2() {
		this.x += 16;
	}

}
