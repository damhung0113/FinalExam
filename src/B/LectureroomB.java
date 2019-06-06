package B;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DA.RoomDA;
import Object.Room;

public class LectureroomB {
	private RoomDA da;

	public LectureroomB() {
		da = new RoomDA();
	}

	public DefaultTableModel getAllItem() throws SQLException {
		List<Room> rooms = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Location");
		model.addColumn("Type");
		model.addColumn("Capacity");
		model.addColumn("AirConditional");
		model.addColumn("Projector");
		model.addColumn("Fan");
		model.addColumn("Screen");
		model.addColumn("Note");

		for (Room room : rooms) {
			String[] cells = new String[9];
			cells[0] = String.valueOf(room.getId());
			cells[1] = room.getLocation();
			cells[2] = room.getType();
			cells[3] = String.valueOf(room.getCapacity());
			if (room.getAirConditioner().equals("Yes"))
				cells[4] = "X";
			else
				cells[4] = "";
			if (room.getProjector().equals("Yes"))
				cells[5] = "X";
			else
				cells[5] = "";
			if (room.getFan().equals("Yes"))
				cells[6] = "X";
			else
				cells[6] = "";
			if (room.getScreen().equals("Yes"))
				cells[7] = "X";
			else
				cells[7] = "";
			cells[8] = room.getNote();
			model.addRow(cells);
		}

		return model;
	}

	public boolean checkID(int id) throws SQLException {
		return da.checkId(id);
	}

	public void addRoom(int id, String location, String type, int capacity, boolean airConditioner, boolean projector,
			boolean fan, boolean screen, String note) throws SQLException {
		da.addRoom(id, location, type, capacity, airConditioner, projector, fan, screen, note);
	}

	public void deleteRoom(int id) throws SQLException {
		da.deleteRoom(id);
	}

	public void editRoom(int id, String location, String type, int capacity, boolean airConditioner, boolean projector,
			boolean fan, boolean screen, String note) throws SQLException {
		da.updateRoom(id, location, type, capacity, airConditioner, projector, fan, screen, note);
	}

	public DefaultTableModel searchRoom(int id, String location, String type, int capacity, String airconditoner,
			String projector, String fan, String screen, String note) throws SQLException {
		List<Room> list = da.search(id, location, type, capacity, airconditoner, projector, fan, screen, note);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Location");
		model.addColumn("Type");
		model.addColumn("Capacity");
		model.addColumn("AirConditional");
		model.addColumn("Projector");
		model.addColumn("Fan");
		model.addColumn("Screen");
		model.addColumn("Note");

		for (Room room : list) {
			String[] cells = new String[9];
			cells[0] = String.valueOf(room.getId());
			cells[1] = room.getLocation();
			cells[2] = room.getType();
			cells[3] = String.valueOf(room.getCapacity());
			if (room.getAirConditioner().equals("Yes"))
				cells[4] = "X";
			else
				cells[4] = "";
			if (room.getProjector().equals("Yes"))
				cells[5] = "X";
			else
				cells[5] = "";
			if (room.getFan().equals("Yes"))
				cells[6] = "X";
			else
				cells[6] = "";
			if (room.getScreen().equals("Yes"))
				cells[7] = "X";
			else
				cells[7] = "";
			cells[8] = room.getNote();
			model.addRow(cells);
		}

		return model;
	}
}
