package windemo;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Other.Bullet;
import Other.Button;
import Other.Enemy;
import Other.Father;
import Other.Music;
import Other.MyBullet;
import Other.MyPlane;
import Other.Prop;
import mysql.MysqlOperation;
import mysql.Integral;
import mysql.User;

/**
 * 游戏过程面板，核心类
 *
 * @author Hany
 *
 */
class GamePanel extends JPanel implements ActionListener {
	int resetY, resetX, myPlWid = 200, myPlHe = 140, enPlWid1 = 100, enPlHe1 = 150, enPlWid2 = 50, enPlHe2 = 70,
			enPlWid3 = 300, enPlHe3 = 500, bloodBorderWid = 200, bloodBorderHe = 60, HpWid = 160, HpHe = 43,
			boss1BlBorWid = 300, boss1BlBorHe = 30, boss1HpWid = 287, boss1HpHe = 20, bombWid1 = 100, bombHe1 = 100,
			bombWid2 = 80, bombHe2 = 80, bossBombWid1 = 400, bossBombHe1 = 400, myPlBuWid = 15, myPlBuHe = 35,
			enPlBuWid1 = 15, enPlBuHe1 = 35, dx = 5, dy = 5, enemyNum = 0, enemyNum1 = 15;
	int score = 0;
	int bgy1 = -998;
	int bgy2 = 0;
	int id;
	int propInt;
	int prop1Int = 0;
	int prop2Int = 0;
	int beStrikeNum = 0;
	int strikeNum = 0;
	int planeState = 3;
	int[] ey = new int[5];
	int[] ex = new int[5];
	boolean keyListener[] = { false, false, false, false, false };
	boolean closeThread = false;
	boolean stop = true;
	boolean Boss = false;
	boolean life = true;
	boolean gameOver = false;
	boolean warn = false;
	boolean musicOpSt = true;
	boolean isProp;
	boolean isProtect;
	boolean isRay;
	String name;
	String[] planeArr = { "image/PlaneLeft2.png", "image/PlaneLeft2.png", "image/PlaneLeft1.png", "image/PlaneDown.png",
			"image/PlaneRight1.png", "image/PlaneRight2.png", "image/PlaneRight2.png", };
	String[] bulletArr = { "image/bullet1_left2.png", "image/bullet1_left2.png", "image/bullet1_left1.png",
			"image/bullet1.png", "image/bullet1_right1.png", "image/bullet1_right2.png", "image/bullet1_right2.png", };
	User user;
	JPanel jpanelFather;
	Thread bulletThread;
	Thread enemyThread;
	Thread propThread;
	Enemy e;
	Enemy boss1;
	Enemy boss1Blood;
	Enemy warning;
	MyPlane mp;
	MyBullet mb;
	Bullet b1;
	Bullet b3;
	Father ot;
	Father bo;
	Button bu;
	Music mu;
	Father grade;
	Father bigGrade;
	Prop[] propArr = { new Prop(300, 200, 80, 50, "image/protect.png", GamePanel.this),
			new Prop(300, 200, 80, 50, "image/power.png", GamePanel.this) };
	AudioClip warningMusic;
	AudioClip bombMusic;
	AudioClip bossBombMusic;
	AudioClip bgMusic;
	AudioClip clickMusic;
	AudioClip attackMusic;
	AudioClip gameOverMusic;
	AudioClip getProp;
	AudioClip doProp;
	JFrame jframe;
	JButton musicOpBtn;
	JButton musicClBtn;
	JButton gameStopBtn;
	JButton backGaBtn;
	JButton backMeBtn1;
	JButton backMeBtn2;
	JButton quitBtn1;
	JButton quitBtn2;
	JButton rePlayBtn1;
	JButton rePlayBtn2;

	Vector<Enemy> enemiesVec1 = new Vector<Enemy>();
	Vector<Enemy> enemiesVec2 = new Vector<Enemy>();
	Vector<Enemy> enemiesVec3 = new Vector<Enemy>();
	Vector<Bullet> bulletsVec = new Vector<Bullet>();
	Vector<MyBullet> myBulletVec = new Vector<MyBullet>();
	Vector<Father> otherVec = new Vector<Father>();
	Vector<Father> bombVec = new Vector<Father>();
	Vector<Father> propVec = new Vector<Father>();
	Vector<Integer> BulletStateVec = new Vector<Integer>();

	// 利用Other类中的Img方法创建图片
	Father Img = new Father();
	ImageIcon bgImg = Img.Img("image/bg6.jpg", 750, 1000);
	ImageIcon multiImg = Img.Img("image/multi.png", 22, 22);
	ImageIcon prop1Img = Img.Img("image/protect.png", 70, 40);
	ImageIcon prop2Img = Img.Img("image/power.png", 70, 40);
	ImageIcon proLoopImg = Img.Img("image/protectLoop.png", 210, 150);
	ImageIcon rayImg = Img.Img("image/ray.gif", 60, 500);

	public GamePanel(JFrame jframe, User user, JPanel jpanelFather) {
		this.user = user;
		this.jpanelFather = jpanelFather;
		this.name = user.getName();
		this.id = user.getId();
		System.out.println(this.id);
		// 初始化数据
		startData();
		this.jframe = jframe;
		this.jframe.add(this);
		this.setLayout(null);
		this.requestFocus();

		// 创建按钮
		bu = new Button();
		gameStopBtn = bu.button(670, 16, 40, 40, "image/stop.png", true, this);
		backGaBtn = bu.button(300, 510, 120, 50, "image/backgame.png", false, this);
		backMeBtn1 = bu.button(300, 560, 120, 50, "image/backmenu.png", false, this);
		backMeBtn2 = bu.button(260, 480, 200, 100, "image/backmenu.png", false, this);
		musicOpBtn = bu.button(300, 460, 120, 50, "image/soundOp.png", false, this);
		musicClBtn = bu.button(300, 410, 120, 50, "image/soundCl.png", false, this);
		quitBtn1 = bu.button(310, 620, 40, 40, "image/qiut.png", false, this);
		quitBtn2 = bu.button(290, 580, 60, 60, "image/qiut.png", false, this);
		rePlayBtn1 = bu.button(370, 620, 40, 40, "image/rePlay.png", false, this);
		rePlayBtn2 = bu.button(370, 580, 60, 60, "image/rePlay.png", false, this);

		// 添加按钮监听器
		gameStopBtn.addActionListener(this);
		backGaBtn.addActionListener(this);
		backMeBtn1.addActionListener(this);
		backMeBtn2.addActionListener(this);
		musicOpBtn.addActionListener(this);
		musicClBtn.addActionListener(this);
		quitBtn1.addActionListener(this);
		quitBtn2.addActionListener(this);
		rePlayBtn1.addActionListener(this);
		rePlayBtn2.addActionListener(this);

		// 音乐
		mu = new Music();
		bgMusic = mu.music("music/bgMusic.wav");
		clickMusic = mu.music("music/click2.wav");
		bombMusic = mu.music("music/bomb.wav");
		bossBombMusic = mu.music("music/bossBomb.wav");
		warningMusic = mu.music("music/warning.wav");
		attackMusic = mu.music("music/bomb1.wav");
		gameOverMusic = mu.music("music/gameover.wav");
		getProp = mu.music("music/getProp.wav");
		doProp = mu.music("music/doProp.wav");
		bgMusic.loop();

	}

	public void paintComponent(Graphics g) {// 画笔
		super.paintComponent(g);
		drawGame(g);
	}

	/**
	 * 数据初始化
	 */
	public void startData() {
		// 英雄机数据初始化
		mp = new MyPlane(300, 800, myPlWid, myPlHe, planeArr[planeState], this);
		mp.makeImg();

		// 敌机数据初始化
		for (int i = 0; i < 4; i++) {
			e = new Enemy(new Random().nextInt(640), -(800 - new Random().nextInt(400)), enPlWid1, enPlHe1,
					"image/enemy1.png", this);
			enemiesVec1.add(e);
			e.makeImg();
		}
		// 初始化英雄机生命值
		ot = new Father(5, 16, bloodBorderWid, bloodBorderHe, "image/energy.png", this);
		ot.makeImg();
		otherVec.add(ot);
		ot = new Father(28, 25, HpWid, HpHe, "image/hp.png", this);
		ot.makeImg();
		otherVec.add(ot);

		// 启动敌机线程和子弹线程
		bulletThread = new BulletThread();
		enemyThread = new EnemyThread();
		propThread = new PropThread();
		bulletThread.start();
		enemyThread.start();
		propThread.start();

		// 成就
		grade = new Father(10, 90, 120, 60, "image/grade2.png", this);
		grade.makeImg();

		// 暂停、游戏结束时显示成绩
		bigGrade = new Father(200, 50, 350, 420, "image/gameOver3.png", this);
		bigGrade.makeImg();

		// 添加监听器数据初始化
		this.addKeyListener(new MykeyListener());
		// 把光标设置在此面板上
		this.requestFocusInWindow();
	}

	/**
	 * 画出组件
	 *
	 * @param g
	 */
	public void drawGame(Graphics g) {
		// 画出背景
		g.drawImage(bgImg.getImage(), 0, bgy1, 750, 1000, this);
		g.drawImage(bgImg.getImage(), 0, bgy2, 750, 1000, this);

		// 画出道具图标
		g.drawImage(multiImg.getImage(), 90, 235, 22, 22, this);
		g.drawImage(prop1Img.getImage(), 15, 230, 70, 40, this);
		g.drawImage(multiImg.getImage(), 90, 305, 22, 22, this);
		g.drawImage(prop2Img.getImage(), 15, 300, 70, 40, this);

		// 画出保护圈
		if (isProtect) {
			g.drawImage(proLoopImg.getImage(), mp.getX() - 15, mp.getY() - 30, 230, 200, this);
		}

		// 画出激光子弹
		if (isRay) {
			g.drawImage(rayImg.getImage(), mp.getX() + 70, mp.getY() - 500, 60, 500, this);
		}
		// 画出道具数量
		Img.num(prop1Int, g, this, 55, 235, 22, 22);
		Img.num(prop2Int, g, this, 55, 305, 22, 22);
		// 判断英雄机是否存活，并画出英雄机
		if (life) {
			mp.draw(g);
		}
		// 画出敌方飞机
		for (int j = 0; j < enemiesVec1.size(); j++) {
			Enemy a = enemiesVec1.get(j);
			a.draw(g);
		}
		for (int j = 0; j < enemiesVec2.size(); j++) {
			Enemy a = enemiesVec2.get(j);
			a.draw(g);
		}
		for (int i = 0; i < enemiesVec3.size(); i++) {
			enemiesVec3.get(i).draw(g);
		}

		// 画出英雄机的子弹
		for (int i = 0; i < myBulletVec.size(); i++) {
			myBulletVec.get(i).draw(g);
		}

		// 画出敌机子弹
		for (int i = 0; i < bulletsVec.size(); i++) {
			Bullet a1 = bulletsVec.get(i);
			a1.draw(g);
		}
		// 画出血条
		for (int i = 0; i < otherVec.size(); i++) {
			otherVec.get(i).draw(g);
		}

		// 画出爆炸效果
		for (int i = 0; i < bombVec.size(); i++) {
			bombVec.get(i).draw(g);
		}
		// 画出分数
		grade.draw(g);
		Img.num(score, g, this, 20, 160, 20, 25);

		// 游戏结束时画出成绩
		if (gameOver) {
			int maxScore = 0;
			if (maxScore == 0) {
				ResultSet rs = new MysqlOperation().sort();
				try {
					while (rs.next()) {
						if (rs.getString("name").equals(user.getName())) {
							maxScore = rs.getInt("score");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			quitBtn2.setVisible(true);
			rePlayBtn2.setVisible(true);
			backMeBtn2.setVisible(true);
			gameStopBtn.setVisible(false);
			bigGrade.draw(g);
			Img.num(score, g, this, 300, 230, 30, 40);
			Img.num(maxScore, g, this, 300, 380, 30, 40);
		}

		// 画出道具
		propArr[propInt].draw(g);
	}

	/**
	 * 英雄机坐标监听
	 *
	 * @author Hany
	 *
	 */
	class MykeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();// 获得所按得键的编码
			switch (code) {
			case KeyEvent.VK_LEFT:
				keyListener[0] = true;
				break;
			case KeyEvent.VK_RIGHT:
				keyListener[1] = true;
				break;
			case KeyEvent.VK_UP:
				keyListener[2] = true;
				break;
			case KeyEvent.VK_DOWN:
				keyListener[3] = true;
				break;
			case KeyEvent.VK_SPACE:
				if (!isRay) {
					firing();
				}
				break;
			case KeyEvent.VK_S:
				if (prop1Int >= 1) {
					beStrikeNum = 0;
					isProtect = true;
					prop1Int--;
					doProp.play();
				}
				break;
			case KeyEvent.VK_R:
				if (prop2Int >= 1) {
					strikeNum = 0;
					isRay = true;
					prop2Int--;
					doProp.play();
				}
				break;
			case KeyEvent.VK_A:
				if (planeState >= 1) {
					planeState--;
					mp.setImgPath(planeArr[planeState]);
					mp.makeImg();
				}
				break;
			case KeyEvent.VK_D:
				if (planeState <= 5) {
					planeState++;
					mp.setImgPath(planeArr[planeState]);
					mp.makeImg();
				}
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();// 获得所按得键的编码
			switch (code) {

			case KeyEvent.VK_LEFT:
				keyListener[0] = false;
				break;
			case KeyEvent.VK_RIGHT:
				keyListener[1] = false;
				break;
			case KeyEvent.VK_UP:
				keyListener[2] = false;
				break;
			case KeyEvent.VK_DOWN:
				keyListener[3] = false;
				break;
			}
		}
	}

	/**
	 * 内部类，线程类，道具线程
	 *
	 * @author Hany
	 *
	 */
	class PropThread extends Thread {

		private boolean suspend = false;

		public void setSuspend(boolean suspend) {
			this.suspend = suspend;
		}

		public void run() {
			while (!closeThread) {
				synchronized (this) {
					while (suspend) {
						try {
							this.wait(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				propInt = new Random().nextInt() > 0.5 ? 1 : 0;
				propArr[propInt].makeImg();
				isProp = true;
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 内部类，线程类
	 *
	 * @author Hany
	 *
	 */
	class EnemyThread extends Thread {
		private boolean suspend = false;

		public void setSuspend(boolean suspend) {
			this.suspend = suspend;
		}

		public void run() {
			while (!closeThread) {
				synchronized (this) {
					while (suspend) {
						try {
							this.wait(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					// 让道具移动
					if (isProp) {
						if (mp.getX() > propArr[propInt].getX()) {
							propArr[propInt].setX(propArr[propInt].getX() + 1);
							propArr[propInt].setY(propArr[propInt].getY() + 5);
						} else if (mp.getX() < propArr[propInt].getX()) {
							propArr[propInt].setX(propArr[propInt].getX() - 1);
							propArr[propInt].setY(propArr[propInt].getY() + 5);
						} else {
							propArr[propInt].setY(propArr[propInt].getY() + 5);
						}
					}

					// 当按下或松开上、下、左、右键时英雄机的位移变动
					if (life) {
						bgy1 += 2;
						bgy2 += 2;
						if (bgy2 >= 998) {
							bgy2 = -998;
						}
						if (bgy1 >= 998) {
							bgy1 = -998;
						}

						int getx = mp.getX();
						int gety = mp.getY();
						if (keyListener[0]) {
							if (getx >= 5) {
								mp.moveLeft();
							} else {
								keyListener[0] = false;
							}
						}
						if (keyListener[1]) {
							if (getx <= 535) {
								mp.moveRight();

							} else {
								keyListener[1] = false;
							}

						}
						if (keyListener[2]) {
							if (gety >= 5) {
								mp.moveUp();
							} else {
								keyListener[2] = false;
							}

						}
						if (keyListener[3]) {
							if (gety <= 820) {
								mp.moveDown();
							} else {
								keyListener[3] = false;
							}
						}
					}
					// 判断是否出现boss
					if (score % 50 == 0 && score > 0 && boss1HpWid == 287) {
						Boss = true;
						boss1 = new Enemy(225, -(1100 - new Random().nextInt(800)), enPlWid3, enPlHe3,
								"image/boss1.png", GamePanel.this);
						boss1.makeImg();
						enemiesVec3.add(boss1);
						boss1Blood = new Enemy(200, 100, boss1BlBorWid, boss1BlBorHe, "image/HpBoder.png",
								GamePanel.this);
						boss1Blood.makeImg();
						enemiesVec3.add(boss1Blood);
						boss1Blood = new Enemy(208, 105, boss1HpWid, boss1HpHe, "image/bossHp.png", GamePanel.this);
						boss1Blood.makeImg();
						enemiesVec3.add(boss1Blood);
						warning = new Enemy(200, -150, 300, 80, "image/warning.gif", GamePanel.this);
						warning.makeImg();
						enemiesVec3.add(warning);
						boss1HpWid = 286;
					}

					// boss出现
					if (Boss) {
						if (warning.getY() <= 220) {
							warning.moveDownSlow();
						}
						stop = false;
						int x = boss1.getX();
						int y = boss1.getY();
						if (x <= 5) {
							dx = 2;
						} else if (x >= 400) {
							dx = -2;
						} else if (y <= 5) {
							dy = 2;
						} else if (y >= 120) {
							dy = -2;
						}
						boss1.setX(x + dx);
						boss1.setY(y + dy);
						// 判断是否使用道具子弹。
						if (isRay) {
							if (crash(mp.getX() + 70, mp.getY() - 500, boss1.getX(), boss1.getY(), enPlWid3, enPlHe3,
									60, 500)) {
								if (musicOpSt) {
									attackMusic.play();
								}
								boss1HpWid -= 3;
								boss1Blood.setWid(boss1HpWid);
								bo = new Father(mp.getX() + 70, mp.getY() - 500, 50, 50, "image/baozha1.gif",
										GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
								strikeNum++;
							}
						}
					}

					// 判断boss是否存活
					if (boss1HpWid <= 1) {
						if (musicOpSt) {
							bossBombMusic.play();
						}
						score += 10;
						boss1HpWid = 287;
						Boss = false;
						stop = true;
						bo = new Father(boss1.getX(), boss1.getY(), bossBombWid1, bossBombHe1, "image/bossBomb.gif",
								GamePanel.this);
						bo.makeImg();
						bombVec.add(bo);
						enemyNum1 = 15;
					}

					// 判断是否在保护的情况下。敌机子弹击中英雄机时，英雄机掉去血量
					for (int i = 0; i < bulletsVec.size(); i++) {
						Bullet getBu = bulletsVec.get(i);
						if (crash(mp.getX(), mp.getY(), getBu.getX(), getBu.getY(), enPlBuWid1, enPlBuHe1, myPlWid,
								myPlHe)) {
							if (isProtect) {
								bulletsVec.remove(i);
								beStrikeNum++;
							} else {
								HpWid -= 5;
								ot.setWidth(HpWid);
								bulletsVec.remove(i);
								if (musicOpSt) {
									attackMusic.play();
								}
								bo = new Father(getBu.getX(), getBu.getY(), 50, 50, "image/baozha1.gif",
										GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
							}
						}
					}

					// 改变敌机子弹Y坐标
					for (int i = 0; i < bulletsVec.size(); i++) {
						Bullet get = bulletsVec.get(i);
						get.moveDown();
						if (get.getY() > 1000) {
							bulletsVec.remove(i);
						}
					}

					// 改变 敌机_1 Y坐标
					for (int i = 0; i < enemiesVec1.size(); i++) {
						Enemy get = enemiesVec1.get(i);
						get.moveDown();
					}

					// 改变敌机_2 Y坐标
					for (int i = 0; i < enemiesVec2.size(); i++) {
						Enemy get = enemiesVec2.get(i);
						get.moveDownQuickly();
					}

					// 判断道具子弹是否击中敌机_1
					if (isRay) {
						for (int j = 0; j < enemiesVec1.size(); j++) {
							Enemy getEne = enemiesVec1.get(j);

							if (crash(mp.getX() + 70, mp.getY() - 500, getEne.getX(), getEne.getY(), enPlWid1, enPlHe1,
									60, 500)) {
								if (musicOpSt) {
									bombMusic.play();
								}
								bo = new Father(getEne.getX(), getEne.getY(), bombWid1, bombHe1, "image/baozha.gif",
										GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
								score += 1;
								enemiesVec1.remove(j);
								strikeNum++;
							}
						}
					}
					// 改变英雄机子弹X、Y坐标并判断是否击中敌机，若击中则显示爆炸效果
					for (int i = 0; i < myBulletVec.size(); i++) {
						MyBullet getMB = myBulletVec.get(i);
						int state = BulletStateVec.get(i);
						switch (state) {
						case 0:
							getMB.moveUp();
							getMB.moveLeft2();
							break;
						case 1:
							getMB.moveUp();
							getMB.moveLeft2();
							break;
						case 2:
							getMB.moveUp();
							getMB.moveLeft1();
							break;
						case 3:
							getMB.moveUp();
							break;
						case 4:
							getMB.moveUp();
							getMB.moveRight1();
							break;
						case 5:
							getMB.moveUp();
							getMB.moveRight2();
							break;
						case 6:
							getMB.moveUp();
							getMB.moveRight2();
							break;
						}
						for (int j = 0; j < enemiesVec1.size(); j++) {
							Enemy getEne = enemiesVec1.get(j);

							if (crash(getMB.getX(), getMB.getY(), getEne.getX(), getEne.getY(), enPlWid1, enPlHe1,
									myPlBuWid, myPlBuHe)) {
								if (musicOpSt) {
									bombMusic.play();
								}
								bo = new Father(getEne.getX(), getEne.getY(), bombWid1, bombHe1, "image/baozha.gif",
										GamePanel.this);
								enemiesVec1.remove(j);
								myBulletVec.remove(i);
								BulletStateVec.remove(i);
								bo.makeImg();
								bombVec.add(bo);
								score += 1;
							}
						}

						// boss出现的情况
						if (Boss) {
							if (crash(getMB.getX(), getMB.getY(), boss1.getX(), boss1.getY(), enPlWid3, enPlHe3,
									myPlBuWid, myPlBuHe) && Boss && boss1.getY() >= 0) {
								if (musicOpSt) {
									attackMusic.play();
								}
								myBulletVec.remove(i);
								BulletStateVec.remove(i);
								boss1HpWid -= 3;
								boss1Blood.setWid(boss1HpWid);
								bo = new Father(getMB.getX(), getMB.getY(), 50, 50, "image/baozha1.gif",
										GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
							}
						}

						// 当子弹出界时被删除
						if (getMB.getY() < 0) {
							myBulletVec.remove(i);
							BulletStateVec.remove(i);
						}

					}

					// 在敌机超过窗口、与我方战机发生碰撞
					for (int i = 0; i < enemiesVec1.size(); i++) {
						Enemy get = enemiesVec1.get(i);
						if (get.getY() > 1100) {
							enemiesVec1.remove(i);
						} else if (crash(get.getX(), get.getY(), mp.getX(), mp.getY(), myPlWid, myPlHe, enPlWid1,
								enPlHe1)) {
							if (isProtect) {
								enemiesVec1.remove(i);
								if (musicOpSt) {
									bombMusic.play();
								}
								bo = new Father(get.getX(), get.getY(), 50, 50, "image/baozha1.gif", GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
								beStrikeNum++;
							} else {
								enemiesVec1.remove(i);
								if (musicOpSt) {
									bombMusic.play();
								}
								bo = new Father(get.getX(), get.getY(), 50, 50, "image/baozha1.gif", GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
								HpWid -= 5;
								ot.setWidth(HpWid);
							}

						}

					}
					// 判断是否在保护的情况下。在敌机超过窗口、与我方战机发生碰撞
					for (int i = 0; i < enemiesVec2.size(); i++) {
						Enemy get = enemiesVec2.get(i);
						if (get.getY() > 1300) {
							enemiesVec2.remove(i);
						} else if (crash(get.getX(), get.getY(), mp.getX(), mp.getY(), myPlWid, myPlHe, enPlWid2,
								enPlHe2)) {
							if (isProtect) {
								enemiesVec2.remove(i);
								bo = new Father(get.getX(), get.getY(), bombWid2, bombHe2, "image/baozha.gif",
										GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
								if (musicOpSt) {
									bombMusic.play();
								}
								beStrikeNum++;
							} else {
								enemiesVec2.remove(i);
								bo = new Father(get.getX(), get.getY(), bombWid2, bombHe2, "image/baozha.gif",
										GamePanel.this);
								bo.makeImg();
								bombVec.add(bo);
								if (musicOpSt) {
									bombMusic.play();
								}
								HpWid -= 5;
								ot.setWidth(HpWid);
							}
						}
					}
					// 判断英雄机是否存活,并将数据存入得分表
					if (HpWid <= 1) {
						System.out.println(score + " " + name + " " + id);
						new MysqlOperation().insert(new Integral(score, name, id));
						life = false;
						gameOver = true;
						if (musicOpSt) {
							bossBombMusic.play();
						}
						bo = new Father(mp.getX(), mp.getY(), myPlWid, myPlHe, "image/bossBomb.gif", GamePanel.this);
						bo.makeImg();
						bombVec.add(bo);
						bgMusic.stop();
						if (musicOpSt) {
							gameOverMusic.play();
						}
						((EnemyThread) enemyThread).setSuspend(true);
						stop=false;
						closeThread = true;
					}

					try {
						Thread.sleep(40);// 线程休眠，每隔40毫秒执行循环一次
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}
		}
	}

	/**
	 * 内部类，线程类
	 *
	 * @author Hany
	 *
	 */
	class BulletThread extends Thread {

		private boolean suspend = false;

		public void setSuspend(boolean suspend) {
			this.suspend = suspend;
		}

		public void run() {
			while (!closeThread) {
				synchronized (this) {
					while (suspend) {
						try {
							this.wait(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					// 判断道具是否存在
					if (crash(propArr[propInt].getX(), propArr[propInt].getY(), mp.getX(), mp.getY(), mp.getWidth(),
							mp.getHeight(), propArr[propInt].getWidth(), propArr[propInt].getHeight())
							|| propArr[propInt].getY() >= 1100) {
						if (propInt == 0) {
							prop1Int++;
						} else if (propInt == 1) {
							prop2Int++;
						}
						propArr[propInt].setY(-500);
						isProp = false;
						getProp.play();
					}
					// 判断保护圈是否破损
					if (beStrikeNum >= 15) {
						isProtect = false;
						beStrikeNum = 0;
					}

					// 判断道具子弹是否消失
					if (strikeNum >= 15) {
						isRay = false;
						strikeNum = 0;
					}
					// 敌机发射子弹，依据敌机移动的距离发射子弹
					if (life) {
						for (int i = 0; i < enemiesVec1.size(); i++) {
							Enemy e = enemiesVec1.get(i);
							if (e.getCount() > 150) {
								b1 = new Bullet(e.getX() + 30, e.getY() + 110, enPlBuWid1, enPlBuHe1,
										"image/bullet_opp.png", GamePanel.this);
								b1.makeImg();
								bulletsVec.add(b1);
								b1 = new Bullet(e.getX() + 60, e.getY() + 110, enPlBuWid1, enPlBuHe1,
										"image/bullet_opp.png", GamePanel.this);
								b1.makeImg();
								bulletsVec.add(b1);
								e.setCountNull();
							}
						}
					}

					// 播放警报声
					if (Boss) {
						if (musicOpSt) {
							warningMusic.play();
						}
					}
					// 敌机Boss子弹发射
					if (Boss && boss1.getY() >= 0) {
						b3 = new Bullet(boss1.getX() + 145, boss1.getY() + 490, enPlBuWid1, enPlBuHe1,
								"image/bullet2_opp.png", GamePanel.this);
						b3.makeImg();
						bulletsVec.add(b3);
						b3 = new Bullet(boss1.getX() + 90, boss1.getY() + 460, enPlBuWid1, enPlBuHe1,
								"image/bullet4.png", GamePanel.this);
						b3.makeImg();
						bulletsVec.add(b3);
						b3 = new Bullet(boss1.getX() + 200, boss1.getY() + 460, enPlBuWid1, enPlBuHe1,
								"image/bullet4.png", GamePanel.this);
						b3.makeImg();
						bulletsVec.add(b3);
						b3 = new Bullet(boss1.getX() + 35, boss1.getY() + 440, enPlBuWid1, enPlBuHe1,
								"image/bullet_opp.png", GamePanel.this);
						b3.makeImg();
						bulletsVec.add(b3);
						b3 = new Bullet(boss1.getX() + 250, boss1.getY() + 440, enPlBuWid1, enPlBuHe1,
								"image/bullet_opp.png", GamePanel.this);
						b3.makeImg();
						bulletsVec.add(b3);
					}
					// 让爆炸效果消失
					for (int i = 0; i < bombVec.size(); i++) {
						bombVec.remove(i);
					}

					// 增加敌机数量
					if(!Boss){
						if (enemyNum1 <= 60) {
							enemyNum = (int) (enemyNum1 / 2);
							enemyNum1 += 6;
						}
						if (enemiesVec1.size() < enemyNum) {
							e = new Enemy(new Random().nextInt(640), -(700 - new Random().nextInt(200)), enPlWid1, enPlHe1,
									"image/enemy1.png", GamePanel.this);
							e.makeImg();
							enemiesVec1.add(e);
						}
					}
					// 增加 敌机_2
					if (score % 10 == 0 && score > 0) {
						for (int i = 0; i < 3; i++) {
							e = new Enemy(new Random().nextInt(640), -(new Random().nextInt(300)), enPlWid2,
									enPlHe2, "image/enemy2.png", GamePanel.this);
							e.makeImg();
							enemiesVec2.add(e);
						}
					}

					if (stop) {
						// 删除boss和boss血槽
						for (int i = 0; i < enemiesVec3.size(); i++) {
							enemiesVec3.remove(i);
						}
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

	/**
	 * 按钮监听
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == musicOpBtn) {
			// 音乐开启
			bgMusic.loop();
			musicOpSt = true;
			// 点击声音
			clickMusic.play();
		} else if (e.getSource() == musicClBtn) {
			// 音乐关闭
			bgMusic.stop();
			musicOpSt = false;
			// 点击声音
			clickMusic.play();
		} else if (e.getSource() == gameStopBtn) {
			// 暂停画面
			((BulletThread) bulletThread).setSuspend(true);
			((EnemyThread) enemyThread).setSuspend(true);
			((PropThread) propThread).setSuspend(true);
			// 显示其他按钮
			musicOpBtn.setVisible(true);
			musicClBtn.setVisible(true);
			gameStopBtn.setVisible(false);
			backGaBtn.setVisible(true);
			backMeBtn1.setVisible(true);
			quitBtn1.setVisible(true);
			rePlayBtn1.setVisible(true);
			// 点击声音
			clickMusic.play();
		} else if (e.getSource() == backGaBtn) {

			// 按钮的显示情况
			musicOpBtn.setVisible(false);
			musicClBtn.setVisible(false);
			gameStopBtn.setVisible(true);
			backGaBtn.setVisible(false);
			backMeBtn1.setVisible(false);
			quitBtn1.setVisible(false);
			rePlayBtn1.setVisible(false);
			// 继续画面
			((BulletThread) bulletThread).setSuspend(false);
			((EnemyThread) enemyThread).setSuspend(false);
			// 点击声音
			clickMusic.play();
		} else if (e.getSource() == backMeBtn1) {
			clickMusic.play();
			int n = JOptionPane.showConfirmDialog(this, "想返回主页吗?战绩将不保存", "返回？", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				// 关闭此界面
				this.setVisible(false);
				// 关闭背景音乐
				bgMusic.stop();
				// 删除此界面中所有组件
				this.removeAll();
				// 关闭线程
				closeThread = true;
				// 点击声音
				clickMusic.play();
				// 重新加载开始界面
				JPanel start = new StartPanel(this.jframe, this.user);
			}

		} else if (e.getSource() == backMeBtn2) {
			clickMusic.play();
			int n = JOptionPane.showConfirmDialog(this, "想返回主页吗?", "返回？", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				// 关闭此界面
				this.setVisible(false);
				// 关闭背景音乐
				bgMusic.stop();
				// 删除此界面中所有组件
				this.removeAll();
				// 关闭线程
				closeThread = true;
				// 点击声音
				clickMusic.play();
				// 重新加载开始界面
				JPanel start = new StartPanel(this.jframe, this.user);
			}
		} else if (e.getSource() == quitBtn1) {
			clickMusic.play();
			int n = JOptionPane.showConfirmDialog(this, "想退出游戏吗?战绩将不保存", "退出？", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				// 退出程序
				System.exit(0);
			}
		} else if (e.getSource() == quitBtn2) {
			clickMusic.play();
			int n = JOptionPane.showConfirmDialog(this, "想退出游戏吗?战绩保存", "退出？", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				// 退出程序
				System.exit(0);
			}
		} else if (e.getSource() == rePlayBtn1) {
			clickMusic.play();
			int n = JOptionPane.showConfirmDialog(this, "想重新开始游戏吗?战绩将不保存", "重新开始", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				life = false;
				// 删除此面板所有组件
				this.removeAll();
				// 关闭此面板
				this.setVisible(false);
				// 关闭此面板上所有音效
				bgMusic.stop();
				clickMusic.stop();
				bombMusic.stop();
				bossBombMusic.stop();
				attackMusic.stop();
				gameOverMusic.stop();
				// 关闭线程
				closeThread = true;
				// 重新启动一个面板
				new GamePanel(this.jframe, this.user, this.jpanelFather);
			}

		} else if (e.getSource() == rePlayBtn2) {
			clickMusic.play();
			int n = JOptionPane.showConfirmDialog(this, "想重新开始游戏吗?战绩保存", "重新开始？", JOptionPane.YES_NO_OPTION);// i=0/1
			if (n == 0) {
				new MysqlOperation().insert(new Integral(score, name, id));
				life = false;
				// 删除此面板所有组件
				this.removeAll();
				// 关闭此面板
				this.setVisible(false);
				// 关闭此面板上所有音效
				bgMusic.stop();
				clickMusic.stop();
				bombMusic.stop();
				bossBombMusic.stop();
				attackMusic.stop();
				gameOverMusic.stop();
				// 关闭线程
				closeThread = true;
				// 重新启动一个面板
				new GamePanel(this.jframe, this.user, this.jpanelFather);
			}

		}
	}

	/**
	 * 英雄机发射子弹
	 */
	public void firing() {
		mb = new MyBullet(mp.getX() + 40, mp.getY(), myPlBuWid, myPlBuHe, bulletArr[planeState], GamePanel.this);
		mb.makeImg();
		myBulletVec.add(mb);
		BulletStateVec.add(planeState);
		mb = new MyBullet(mp.getX() + 145, mp.getY(), myPlBuWid, myPlBuHe, bulletArr[planeState], GamePanel.this);
		mb.makeImg();
		myBulletVec.add(mb);
		BulletStateVec.add(planeState);
	}

	/**
	 * 两（A,B）矩形碰撞算法
	 *
	 * @param ex
	 *            A矩形x坐标
	 * @param ey
	 *            A矩形y坐标
	 * @param x
	 *            B矩形坐标
	 * @param y
	 *            B矩形坐标
	 * @param width1
	 *            B矩形宽度
	 * @param height1
	 *            B矩形高度
	 * @param width2
	 *            A矩形宽度
	 * @param height2
	 *            B矩形高度
	 * @return 布尔值，判断是否相交
	 */
	public boolean crash(int ex, int ey, int x, int y, int width1, int height1, int width2, int height2) {
		int nMaxLeft = ex >= x ? ex : x;
		int nMaxTop = ey >= y ? ey : y;
		int nMinRight = (ex + width2) <= (x + width1) ? (ex + height2) : (x + width1);
		int nMinBottom = (ey + height2) <= (y + height1) ? (ey + height2) : (y + height1);
		// 判断是否相交
		if (nMaxLeft > nMinRight || nMaxTop > nMinBottom) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isOverlap(int x1, int y1, int wid1, int hei1, int x2, int y2, int wid2, int hei2) {
		if (x1 + wid1 > x2 && x2 + wid2 > x1 && y1 + hei1 > y2 && y2 + hei2 > y1)
			return true;
		else
			return false;
	}

}