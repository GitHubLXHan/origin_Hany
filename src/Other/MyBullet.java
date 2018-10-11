package Other;

import javax.swing.JPanel;

public class MyBullet extends Father {

	public MyBullet(int x, int y, int width, int height, String imgPath, JPanel jpanel) {
		super(x, y, width, height, imgPath, jpanel);
	}

	public MyBullet() {
	}

	// 子弹向上移动
	public void moveUp() {
		this.y -= 12;
	}

	// 子弹向左移动
	public void moveLeft1() {
		this.x -= 12;
	}

	// 子弹向左移动
	public void moveLeft2() {
		this.x -= 16;
	}

	// 子弹向右移动
	public void moveRight1() {
		this.x += 12;
	}

	// 子弹向右移动
	public void moveRight2() {
		this.x += 16;
	}

}
