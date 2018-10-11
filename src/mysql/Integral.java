package mysql;

/**
 * 实体类（得分类）
 *
 * @author Hany
 *
 */
public class Integral {
	int score;
	String name;
	int id;

	public Integral() {

	}

	public Integral(int score, String name, int id) {
		this.score = score;
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
