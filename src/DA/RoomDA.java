package DA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Object.Room;

public class RoomDA {
	private Connection con;

	public RoomDA() {
		try {
			con = ConnectionUtil.getConnectionUtil();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkId(int id) throws SQLException {
		String sql = "SELECT * FROM lectureroom WHERE id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			return true;
		}
		return false;
	}

	public List<Room> getAll() throws SQLException {
		List<Room> rooms = new ArrayList<>();
		String sql = "SELECT * FROM lectureroom";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Room room = new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			rooms.add(room);
		}
		return rooms;
	}

	public void addRoom(int id, String location, String type, int capacity, Boolean airconditoner, Boolean projector,
			Boolean fan, Boolean screen, String note) throws SQLException {
		String sql = "INSERT INTO lectureroom(id, location, type,capacity ,airconditoner, projector, fan, screen, note) "
				+ " values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setString(2, location);
		stmt.setString(3, type);
		stmt.setInt(4, capacity);
		if (airconditoner)
			stmt.setString(5, "Yes");
		else
			stmt.setString(5, "No");
		if (projector)
			stmt.setString(6, "Yes");
		else
			stmt.setString(6, "No");
		if (fan)
			stmt.setString(7, "Yes");
		else
			stmt.setString(7, "No");
		if (screen)
			stmt.setString(8, "Yes");
		else
			stmt.setString(8, "No");
		stmt.setString(9, note);
		stmt.executeUpdate();
		stmt.close();
	}

	public void deleteRoom(int id) throws SQLException {
		String sql = "DELETE FROM lectureroom WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();
	}

	public void updateRoom(int id, String location, String type, int capacity, Boolean airconditoner, Boolean projector,
			Boolean fan, Boolean screen, String note) throws SQLException {
		String sql = "UPDATE lectureroom SET location = ?, type = ?, capacity = ?,airconditoner =?, projector = ?, fan = ?, screen = ?, note = ?  WHERE id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, location);
		stmt.setString(2, type);
		stmt.setInt(3, capacity);
		if (airconditoner)
			stmt.setString(4, "Yes");
		else
			stmt.setString(4, "No");
		if (projector)
			stmt.setString(5, "Yes");
		else
			stmt.setString(5, "No");
		if (fan)
			stmt.setString(6, "Yes");
		else
			stmt.setString(6, "No");
		if (screen)
			stmt.setString(7, "Yes");
		else
			stmt.setString(7, "No");
		stmt.setString(8, note);
		stmt.setInt(9, id);
		stmt.executeUpdate();
		stmt.close();
	}

	public List<Room> search(int id, String location, String type, int capacity, String airconditoner, String projector,
			String fan, String screen, String note) throws SQLException {
		List<Room> rooms = new ArrayList<>();
		String q1, q2, q3, q4, q5, q6, q7, q8, q9;
		if (id != 0) {
			q1 = "AND id = ?";
		} else {
			q1 = "AND (1=1 OR id = ?)";
		}
		if (location.compareTo("") != 0) {
			q2 = "AND location = ?";
		} else {
			q2 = "AND (1=1 OR location = ?)";
		}
		if (type.compareTo("") != 0) {
			q3 = "AND type = ?";
		} else {
			q3 = "AND (1=1 OR type = ?)";
		}
		if (capacity != 0) {
			q4 = "AND capacity = ?";
		} else {
			q4 = "AND (1=1 OR capacity = ?)";
		}
		if (airconditoner.compareTo("NULL") != 0) {
			q5 = "AND airconditoner = ?";
		} else {
			q5 = "AND (1=1 OR airconditoner = ?)";
		}
		if (projector.compareTo("NULL") != 0) {
			q6 = "AND projector = ?";
		} else {
			q6 = "AND (1=1 OR projector = ?)";
		}
		if (fan.compareTo("NULL") != 0) {
			q7 = "AND fan = ?";
		} else {
			q7 = "AND (1=1 OR fan = ?)";
		}
		if (screen.compareTo("NULL") != 0) {
			q8 = "AND screen = ?";
		} else {
			q8 = "AND (1=1 OR screen = ?)";
		}
		if (note.compareTo("") != 0) {
			q9 = "AND note = ?";
		} else {
			q9 = "AND (1=1 OR note = ?)";
		}
		String sql = "SELECT * FROM lectureroom WHERE 1=1" + " " + q1 + " " + q2 + " " + q3 + " " + q4 + " " + q5 + " "
				+ q6 + " " + q7 + " " + q8 + " " + q9;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setString(2, location);
		stmt.setString(3, type);
		stmt.setInt(4, capacity);
		stmt.setString(5, airconditoner);
		stmt.setString(6, projector);
		stmt.setString(7, fan);
		stmt.setString(8, screen);
		stmt.setString(9, note);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Room room = new Room(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			rooms.add(room);
		}
		return rooms;
	}

}