package DA;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Object.User;

public class UserDA {

	private Connection con;

	public UserDA() {
		try {
			con = ConnectionUtil.getConnectionUtil();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getName(String username) throws SQLException {
		String s = "";
		String sql = "SELECT fullname FROM user WHERE username = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			s = rs.getString(1);
		}
		return s;
	}

	public boolean checkUser(String user, String password) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public boolean checkUsername(String user) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
	
	public boolean checkAdmin(String user, String password) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND role = 0";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, user);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public boolean checkId(int id) throws SQLException {
		String sql = "SELECT * FROM user WHERE id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public List<User> getAll() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM user";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDate(6), rs.getString(7), rs.getInt(8));
			users.add(user);
		}
		return users;
	}

	public void addUser(int id, String username, String password, String email, String fullname, Date dob, boolean sex,
			int role) throws SQLException {
		String sql = "INSERT INTO user(id, username, password, email, fullname ,dob, sex, role)"
				+ " values(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setString(2, username);
		stmt.setString(3, password);
		stmt.setString(4, email);
		stmt.setString(5, fullname);
		stmt.setDate(6, dob);
		if (sex)
			stmt.setString(7, "Male");
		else
			stmt.setString(7, "Female");
		stmt.setInt(8, role);
		stmt.executeUpdate();
		stmt.close();
	}

	public void deleteUser(int id) throws SQLException {
		String sql = "DELETE FROM user WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();
	}

	public void updateUser(int id, String username, String password, String email, String fullname, Date dob,
			boolean isMale, int role) throws SQLException {
		String sql = "UPDATE user SET username = ?, password = ?, email = ?, fullname = ?, dob =?, sex = ?, role = ? WHERE id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(8, id);
		stmt.setString(1, username);
		stmt.setString(2, password);
		stmt.setString(3, email);
		stmt.setString(4, fullname);
		stmt.setDate(5, dob);
		if (isMale)
			stmt.setString(6, "Male");
		else
			stmt.setString(6, "Female");
		stmt.setInt(7, role);
		stmt.executeUpdate();
		stmt.close();
	}

//	public List<User> search(int id, String username, String password, String email, String fullname, Date dob,
//			String sex, int role) throws SQLException {
//		List<User> users = new ArrayList<>();
//		String q1, q2, q3, q4, q5, q6, q7, q8;
//		if (id != 0) {
//			q1 = "AND id = ?";
//		} else {
//			q1 = "AND ( 1=1 OR id = ?)";
//		}
//		if (username.compareTo("") != 0) {
//			q2 = "AND username = ?";
//		} else {
//			q2 = "AND ( 1=1 OR username = ?)";
//		}
//		if (password.compareTo("") != 0) {
//			q3 = "AND password = ?";
//		} else {
//			q3 = "AND ( 1=1 OR password = ?)";
//		}
//		if (email.compareTo("") != 0) {
//			q4 = "AND email = ?";
//		} else {
//			q4 = "AND ( 1=1 OR email = ?)";
//		}
//		if (fullname.compareTo("") != 0) {
//			q5 = "AND fullname = ?";
//		} else {
//			q5 = "AND ( 1=1 OR fullname = ?)";
//		}
//		if (String.valueOf(dob).compareTo("0001-01-01") != 0) {
//			q6 = "AND dob = ?";
//		} else {
//			q6 = "AND ( 1=1 OR dob = ?)";
//		}
//		if (sex.compareTo("NULL") != 0) {
//			q7 = "AND sex = ?";
//		} else {
//			q7 = "AND ( 1=1 OR sex = ?)";
//		}
//		if (role != -1) {
//			q8 = "AND role = ?";
//		} else {
//			q8 = "AND ( 1=1 OR role = ?)";
//		}
//		String sql = "SELECT * FROM user WHERE 1=1" + " " + q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5 + " " + q6
//				+ " " + q7 + " " + q8;
//		PreparedStatement stmt = con.prepareStatement(sql);
//		stmt.setInt(1, id);
//		stmt.setString(2, username);
//		stmt.setString(3, password);
//		stmt.setString(4, email);
//		stmt.setString(5, fullname);
//		stmt.setDate(6, dob);
//		stmt.setString(7, sex);
//		stmt.setInt(8, role);
//		ResultSet rs = stmt.executeQuery();
//		while (rs.next()) {
//			User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
//					rs.getDate(6), rs.getString(7), rs.getInt(8));
//			users.add(user);
//		}
//		return users;
//	}
	public List<User> search(int id, String username, String password, String email, String fullname, Date dob,
			String sex, int role) throws SQLException {
		List<User> users = new ArrayList<>();
		String q1, q2, q3, q4, q5, q6, q7, q8;
		if (id != 0) {
			q1 = "AND id = ?";
		} else {
			q1 = "AND ( 1=1 OR id = ?)";
		}
		if (username.compareTo("") != 0) {
			q2 = "AND username = ?";
		} else {
			q2 = "AND ( 1=1 OR username = ?)";
		}
		if (password.compareTo("") != 0) {
			q3 = "AND password = ?";
		} else {
			q3 = "AND ( 1=1 OR password = ?)";
		}
		if (email.compareTo("") != 0) {
			q4 = "AND email = ?";
		} else {
			q4 = "AND ( 1=1 OR email = ?)";
		}
		if (fullname.compareTo("") != 0) {
			q5 = "AND fullname = ?";
		} else {
			q5 = "AND ( 1=1 OR fullname = ?)";
		}
		if (String.valueOf(dob).compareTo("0001-01-01") != 0) {
			q6 = "AND dob = ?";
		} else {
			q6 = "AND ( 1=1 OR dob = ?)";
		}
		if (sex.compareTo("NULL") != 0) {
			q7 = "AND sex = ?";
		} else {
			q7 = "AND ( 1=1 OR sex = ?)";
		}
		if (role != -1) {
			q8 = "AND role = ?";
		} else {
			q8 = "AND ( 1=1 OR role = ?)";
		}
		String sql = "SELECT * FROM user WHERE 1=1" + " " + q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5 + " " + q6
				+ " " + q7 + " " + q8;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setString(2, username);
		stmt.setString(3, password);
		stmt.setString(4, email);
		stmt.setString(5, fullname);
		stmt.setDate(6, dob);
		stmt.setString(7, sex);
		stmt.setInt(8, role);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDate(6), rs.getString(7), rs.getInt(8));
			users.add(user);
		}
		return users;
	} 
}
