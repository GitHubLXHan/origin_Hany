package Other;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends Father {

	public Button() {

	}

	/**
	 * 创建按钮对象
	 *
	 * @param x
	 *            坐标x
	 * @param y
	 *            坐标y
	 * @param width
	 *            按钮宽度
	 * @param height
	 *            按钮高度
	 * @param imgPath
	 *            按钮图片路径
	 * @param visible
	 *            是否可见
	 * @param jpanel
	 *            面板对象
	 * @return JButton对象
	 */
	public JButton button(int x, int y, int width, int height, String imgPath, boolean visible, JPanel jpanel) {
		// 创建图片
		img = new ImageIcon(imgPath);
		// 得到缩放后的图片
		img.setImage(img.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		// 创建一个按钮
		JButton button = new JButton();
		// 设置按钮无边框
		button.setBorder(null);
		// 设置图片为按钮
		button.setIcon(img);
		// 不绘制按钮区域
		button.setContentAreaFilled(false);
		// 设置按钮的大小位置
		button.setBounds(x, y, width, height);
		// 不绘制边框
		button.setBorderPainted(false);
		// 获取焦点
		button.setFocusable(false);
		// 按钮是否显示
		button.setVisible(visible);
		// 把按钮添加到面板上
		jpanel.add(button);
		// jpanel.requestFocus();
		return button;
	}
}
