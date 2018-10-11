package mysql;

/**
 * @author Hany 实体类（用户类）
 */
public class User {

	private String name;
	private String password;
	private int id;

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
