package mysql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库操作类
 *
 * @author Hany
 *
 */
public class MysqlOperation {
	private Statement stmt;
	private Connection conn;

	public MysqlOperation() {
		conn = new ConnectToDataBase().getDataBaseConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入
	 *
	 * @param obj
	 */
	public void insert(Object obj) {
		Class cl = obj.getClass();//获取实体类对象
		String clname = obj.getClass().getName();//获取实体类的名称
		clname = clname.toLowerCase();
		System.out.println(clname);
		String[] strs = clname.split("\\.");
		clname = strs[strs.length - 1].toString();
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(clname + " (");

		Field[] fName = cl.getDeclaredFields();// 获取实体类中所有的属性名
		// 下面的逻辑是去除fName的前面的包名等
		for (Field field : fName) {
			String fname = field.toString();
			String[] fnameReally = fname.split("\\.");
			sql.append(fnameReally[fnameReally.length - 1].toString() + ",");
		}
		int length1 = sql.length();
		sql.delete(length1 - 1, length1).append(") values ('");

		Method[] methods = cl.getMethods();// 获取实体类中所有的方法
		// 下面的逻辑时取对应于属性名顺序而取属性值
		for (Field field : fName) {
			String fname = field.toString();
			String[] fnameReally = fname.split("\\.");
			String nameLast = fnameReally[fnameReally.length - 1].toString().substring(0, 1).toUpperCase()
					+ fnameReally[fnameReally.length - 1].toString().substring(1);
			for (Method m : methods) {
				String mName = m.getName();
				// 判断是否是get方法并且当符合顺序时的取值方法
				if (mName.startsWith("get") && !mName.startsWith("getClass") && mName.endsWith(nameLast)) {
					try {
						//执行该Method.invoke方法的参数是执行这个方法的对象owner，
						//和参数数组args，可以这么理解：owner对象中带有参数args的method方法。
						//返回值是Object，也既是该方法的返回值
						Object value = m.invoke(obj);
						sql.append(value + "','");
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}

		int length2 = sql.length();
		sql.delete(length2 - 2, length2).append(")");
		 System.out.println(sql);
		try {
			stmt.executeUpdate(sql.toString());// 创建数据对象
			System.out.println("插入数据库成功！");
			if (stmt == null)
				stmt.close();
			if (conn == null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("插入数据库失败！");
		}

	}

	/**
	 * 查询
	 *
	 * @param user
	 * @return
	 */
	public ResultSet search(Object obj) {
		Class cl = obj.getClass();
		String clname = obj.getClass().getName();
		clname = clname.toLowerCase();
		String[] strs = clname.split("\\.");
		clname = strs[strs.length - 1].toString();
		StringBuffer sql = new StringBuffer("select*from ");
		sql.append(clname + " where ");
		Field fName = null;
		try {
			fName = cl.getDeclaredField("name");
		} catch (NoSuchFieldException e2) {
			e2.printStackTrace();
		} catch (SecurityException e2) {
			e2.printStackTrace();
		}
		String aa = fName.toString();
		String[] cc = aa.split("\\.");
		sql.append(cc[cc.length - 1].toString() + " = '");

		Method[] methods = cl.getDeclaredMethods();
		for (Method m : methods) {
			String mName = m.getName();

			if (mName.startsWith("get") && !mName.startsWith("getClass") && mName.endsWith("Name")) {
				try {
					Object value = m.invoke(obj);
					sql.append(value + "','");
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		int length2 = sql.length();
		sql.delete(length2 - 2, length2);
		ResultSet rs = null;

			try {
				rs = stmt.executeQuery(sql.toString());// 创建数据对象

				if (stmt == null)
					stmt.close();
				if (conn == null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return rs;
	}

	/**
	 * 更新
	 *
	 * @param obj
	 */
	public void upDate(Object obj) {
		Object id = null;
		Class cl = obj.getClass();
		String clname = obj.getClass().getName();
		clname = clname.toLowerCase();
		String[] strs = clname.split("\\.");
		clname = strs[strs.length - 1].toString();
		StringBuffer sql = new StringBuffer("update ");
		sql.append(clname + " set ");

		Field[] fName = cl.getDeclaredFields();// 获取实体类中所有的属性名
		Method[] methods = cl.getMethods();// 获取实体类中所有的方法
		// 下面的逻辑时取对应于属性名顺序而取属性值
		for (Field field : fName) {
			String fname = field.toString();
			String[] fnameReally = fname.split("\\.");
			String nameLast = fnameReally[fnameReally.length - 1].toString();
			if (!nameLast.equals("id")) {
				sql.append(nameLast + "='");
			}

			String nameLastUpper = fnameReally[fnameReally.length - 1].toString().substring(0, 1).toUpperCase()
					+ fnameReally[fnameReally.length - 1].toString().substring(1);

			for (Method m : methods) {

				String mName = m.getName();
				// 获取id值
				if (mName.startsWith("get") && !mName.startsWith("getClass") && mName.endsWith(nameLastUpper)
						&& mName.endsWith("Id")) {
					try {
						id = m.invoke(obj);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				// 判断是否是get方法并且当符合顺序时的取值方法
				if (mName.startsWith("get") && !mName.startsWith("getClass") && mName.endsWith(nameLastUpper)
						&& !mName.endsWith("Id")) {
					try {
						Object value = m.invoke(obj);
						sql.append(value + "',");
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}

		int length2 = sql.length();
		sql.delete(length2 - 1, length2).append(" where id=" + id);
		try {
			stmt.execute(sql.toString());// 创建数据对象
			System.out.println("更新数据库成功！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("更新数据库失败！");
		}
		// 断开连接
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将数据库按名字(name)分组再取每组中最大的分数(score)
	 *
	 * @return 结果集
	 */
	public ResultSet sort() {
		ResultSet rs = null;
		// String sql = "select a.* from integral as a where score = (select
		// max(score) from integral where a.name=name)";
		// String sql = "select a.* from integral a inner join (select
		// name,max(score) integral from integral group by name)b on
		// a.name=b.name and a.score=b.score order by a.name";
		// String sql = "select a.* from integral as a where not exists (select
		// * from integral where name=a.name and score>a.score)";
		// String sql = "select a.* from integral as a where exists (select
		// count(*) from integral where name=a.name and score>a.score having
		// count(*)=0)";
		// String sql = "select a.* from integral as a where score = (select
		// max(score) from integral where a.name=name)";
		// String sql = "select * from integral order by score limit 0,10";
		String sql = "SELECT a.name, a.score FROM integral a WHERE a.score in (SELECT max(score)FROM integral GROUP BY name)";
		try {
			stmt.executeQuery(sql);
			rs = stmt.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

}
