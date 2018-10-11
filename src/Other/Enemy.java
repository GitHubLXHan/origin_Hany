package Other;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 敌机类
 *
 * @author Hany
 *
 */
public class Enemy {
	int x, y, enemywidth, enemyheight, count = 0;
	String imgpath;
	JPanel jpanel;
	ImageIcon img;

	public Enemy() {
	}

	public Enemy(int x, int y, int enemywidth, int enemyheight, String imgpath, JPanel jpanel) {
		super();
		this.x = x;
		this.y = y;
		this.enemywidth = enemywidth;
		this.enemyheight = enemyheight;
		this.imgpath = imgpath;
		this.jpanel = jpanel;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void draw(Graphics g) {

		// 在面板 上画出图片
		g.drawImage(this.img.getImage(), this.x, this.y, this.enemywidth, this.enemyheight, this.jpanel);
	}

	public void makeImg() {
		// 创建一张图片
		this.img = new ImageIcon(this.imgpath);
		// 得到缩放后的图片
		img.setImage(img.getImage().getScaledInstance(enemywidth, enemyheight, Image.SCALE_DEFAULT));

	}

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}

	// 敌机向下移动
	public void moveDown() {
		this.y += 6;
		this.count+=2;
	}

	public void moveDownQuickly() {
		this.y += 30;
	}

	// 敌机向下移动
	public void moveDownSlow() {
		this.y += 2;
	}

	// 敌机向左移动
	public void moveLeft() {
		this.x -= 3;
	}

	// 敌机向右移动
	public void moveRight() {
		this.x += 3;
	}

	// 敌机向右移动
	public void moveUp() {
		this.y -= 3;
	}

	public int getCount() {
		return this.count;
	}

	public void setCountNull() {
		this.count = 0;
	}

	public void setWid(int width) {
		this.enemywidth = width;
	}
}
