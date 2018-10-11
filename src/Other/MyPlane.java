package Other;

import javax.swing.JPanel;

public class MyPlane extends Father {
	public MyPlane() {
	}

	public MyPlane(int x, int y, int mywidth, int myheight, String imgPath, JPanel jpanel) {
		super(x, y, mywidth, myheight, imgPath, jpanel);
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	// 英雄机向上移动
	public void moveDown() {
		this.y += 10;
	}

	// 英雄机向下移动
	public void moveUp() {
		this.y -= 10;
	}

	// 英雄机向左移动
	public void moveLeft() {
		this.x -= 10;
	}

	// 英雄机向下移动
	public void moveRight() {
		this.x += 10;
	}
}
