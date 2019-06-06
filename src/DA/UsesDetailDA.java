package DA;

import java.sql.*;
import java.util.ArrayList;

import java.util.*;

import Object.UsesDetail;;

public class UsesDetailDA {

	private Connection con;

	public UsesDetailDA() {
		try {
			con = ConnectionUtil.getConnectionUtil();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<UsesDetail> getAll() throws SQLException {
		List<UsesDetail> usesDetails = new ArrayList<>();
		String sql = "SELECT * FROM usesdetail";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			UsesDetail usesDetail = new UsesDetail(rs.getInt(1), rs.getTime(2), rs.getTime(3), rs.getInt(4),
					rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
			usesDetails.add(usesDetail);
		}
		return usesDetails;
	}

	public boolean checkId(int id) throws SQLException {
		String sql = "SELECT * FROM usesdetail WHERE id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public boolean checkPK1(int roomId, int userId) throws SQLException {
		String sql = "SELECT * FROM usesdetail WHERE lectureroom_id = ? AND user_id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, roomId);
		stmt.setInt(2, userId);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public boolean checkPK2(int roomId, int adminID) throws SQLException {
		String sql = "SELECT * FROM usesdetail WHERE lectureroom_id = ? AND admin_id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, roomId);
		stmt.setInt(2, adminID);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}
	
	public boolean checkDateTime(Time timeIn, Time timeOut, int date) throws SQLException {
		String sql = "SELECT * FROM usesdetail WHERE time_in = ? AND time_out = ? AND date = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setTime(1, timeIn);
		stmt.setTime(2, timeOut);
		stmt.setInt(3, date);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public void addUsesDetail(int id, Time timeIn, Time timeOut, int date, int adminId, int roomId, int userId,
			String purpose) throws SQLException {
		String sql = "INSERT INTO usesdetail(id, time_in, time_out, date, admin_id ,lectureroom_id, user_id, purpose)"
				+ " values(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setTime(2, timeIn);
		stmt.setTime(3, timeOut);
		stmt.setInt(4, date);
		stmt.setInt(5, adminId);
		stmt.setInt(6, roomId);
		stmt.setInt(7, userId);
		stmt.setString(8, purpose);
		stmt.executeUpdate();
		stmt.close();
	}

	public void deleteUsesDetail(int id) throws SQLException {
		String sql = "DELETE FROM usesdetail WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();
	}

	public void updateUsesDetail(int id, Time timeIn, Time timeOut, int date, int adminId, int roomId, int userId,
			String purpose) throws SQLException {
		String sql = "UPDATE usesdetail SET time_in = ?, time_out = ?, date = ?, admin_id = ?, lectureroom_id =?, user_id = ?, purpose = ? WHERE id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setTime(1, timeIn);
		stmt.setTime(2, timeOut);
		stmt.setInt(3, date);
		stmt.setInt(4, adminId);
		stmt.setInt(5, roomId);
		stmt.setInt(6, userId);
		stmt.setString(7, purpose);
		stmt.setInt(8, id);
		stmt.executeUpdate();
		stmt.close();
	}

	public List<UsesDetail> search(int id, Time timeIn, Time timeOut, int date, int adminId, int roomId, int userId,
			String purpose) throws SQLException {
		List<UsesDetail> usesDetails = new ArrayList<>();
		String q1, q2, q3, q4, q5, q6, q7, q8;
		if (id != 0) {
			q1 = "AND id = ?";
		} else {
			q1 = "AND ( 1=1 OR id = ?)";
		}
		if (String.valueOf(timeIn).compareTo("00:00:00") != 0) {
			q2 = "AND time_in = ?";
		} else {
			q2 = "AND ( 1=1 OR time_in = ?)";
		}
		if (String.valueOf(timeOut).compareTo("00:00:00") != 0) {
			q3 = "AND time_out = ?";
		} else {
			q3 = "AND ( 1=1 OR time_out = ?)";
		}
		if (date != 0) {
			q4 = "AND date = ?";
		} else {
			q4 = "AND ( 1=1 OR date = ?)";
		}
		if (adminId != 0) {
			q5 = "AND admin_id = ?";
		} else {
			q5 = "AND ( 1=1 OR admin_id = ?)";
		}
		if (roomId != 0) {
			q6 = "AND lectureroom_id = ?";
		} else {
			q6 = "AND ( 1=1 OR lectureroom_id = ?)";
		}
		if (userId != 0) {
			q7 = "AND user_id = ?";
		} else {
			q7 = "AND ( 1=1 OR user_id = ?)";
		}
		if (purpose.compareTo("") != 0) {
			q8 = "AND purpose = ?";
		} else {
			q8 = "AND ( 1=1 OR purpose = ?)";
		}
		String sql = "SELECT * FROM usesdetail WHERE 1=1" + " " + q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5 + " "
				+ q6 + " " + q7 + " " + q8;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setTime(2, timeIn);
		stmt.setTime(3, timeOut);
		stmt.setInt(4, date);
		stmt.setInt(5, adminId);
		stmt.setInt(6, roomId);
		stmt.setInt(7, userId);
		stmt.setString(8, purpose);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			UsesDetail usesDetail = new UsesDetail(rs.getInt(1), rs.getTime(2), rs.getTime(3), rs.getInt(4),
					rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8));
			usesDetails.add(usesDetail);
		}
		return usesDetails;
	}
}
