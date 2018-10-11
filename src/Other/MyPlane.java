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

	// Ӣ�ۻ������ƶ�
	public void moveDown() {
		this.y += 10;
	}

	// Ӣ�ۻ������ƶ�
	public void moveUp() {
		this.y -= 10;
	}

	// Ӣ�ۻ������ƶ�
	public void moveLeft() {
		this.x -= 10;
	}

	// Ӣ�ۻ������ƶ�
	public void moveRight() {
		this.x += 10;
	}
}
