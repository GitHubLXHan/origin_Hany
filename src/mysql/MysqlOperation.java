package mysql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ݿ������
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
	 * ����
	 *
	 * @param obj
	 */
	public void insert(Object obj) {
		Class cl = obj.getClass();//��ȡʵ�������
		String clname = obj.getClass().getName();//��ȡʵ���������
		clname = clname.toLowerCase();
		System.out.println(clname);
		String[] strs = clname.split("\\.");
		clname = strs[strs.length - 1].toString();
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(clname + " (");

		Field[] fName = cl.getDeclaredFields();// ��ȡʵ���������е�������
		// ������߼���ȥ��fName��ǰ��İ�����
		for (Field field : fName) {
			String fname = field.toString();
			String[] fnameReally = fname.split("\\.");
			sql.append(fnameReally[fnameReally.length - 1].toString() + ",");
		}
		int length1 = sql.length();
		sql.delete(length1 - 1, length1).append(") values ('");

		Method[] methods = cl.getMethods();// ��ȡʵ���������еķ���
		// ������߼�ʱȡ��Ӧ��������˳���ȡ����ֵ
		for (Field field : fName) {
			String fname = field.toString();
			String[] fnameReally = fname.split("\\.");
			String nameLast = fnameReally[fnameReally.length - 1].toString().substring(0, 1).toUpperCase()
					+ fnameReally[fnameReally.length - 1].toString().substring(1);
			for (Method m : methods) {
				String mName = m.getName();
				// �ж��Ƿ���get�������ҵ�����˳��ʱ��ȡֵ����
				if (mName.startsWith("get") && !mName.startsWith("getClass") && mName.endsWith(nameLast)) {
					try {
						//ִ�и�Method.invoke�����Ĳ�����ִ����������Ķ���owner��
						//�Ͳ�������args��������ô��⣺owner�����д��в���args��method������
						//����ֵ��Object��Ҳ���Ǹ÷����ķ���ֵ
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
			stmt.executeUpdate(sql.toString());// �������ݶ���
			System.out.println("�������ݿ�ɹ���");
			if (stmt == null)
				stmt.close();
			if (conn == null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�������ݿ�ʧ�ܣ�");
		}

	}

	/**
	 * ��ѯ
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
				rs = stmt.executeQuery(sql.toString());// �������ݶ���

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
	 * ����
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

		Field[] fName = cl.getDeclaredFields();// ��ȡʵ���������е�������
		Method[] methods = cl.getMethods();// ��ȡʵ���������еķ���
		// ������߼�ʱȡ��Ӧ��������˳���ȡ����ֵ
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
				// ��ȡidֵ
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
				// �ж��Ƿ���get�������ҵ�����˳��ʱ��ȡֵ����
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
			stmt.execute(sql.toString());// �������ݶ���
			System.out.println("�������ݿ�ɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("�������ݿ�ʧ�ܣ�");
		}
		// �Ͽ�����
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����ݿⰴ����(name)������ȡÿ�������ķ���(score)
	 *
	 * @return �����
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
