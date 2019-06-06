package B;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DA.UsesDetailDA;
import Object.UsesDetail;

public class DetailB {
	private UsesDetailDA da;

	public DetailB() {
		da = new UsesDetailDA();
	}

	public DefaultTableModel getAllItem() throws SQLException {
		List<UsesDetail> details = da.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Time in");
		model.addColumn("Time out");
		model.addColumn("Date");
		model.addColumn("Admin ID");
		model.addColumn("Lectureroom ID");
		model.addColumn("User ID");
		model.addColumn("Pupose");

		for (UsesDetail detail : details) {
			String[] cells = new String[8];
			cells[0] = String.valueOf(detail.getId());
			cells[1] = String.valueOf(detail.getTime_in());
			cells[2] = String.valueOf(detail.getTime_out());
			cells[3] = String.valueOf(detail.getDate());
			cells[4] = String.valueOf(detail.getAdmin_id());
			cells[5] = String.valueOf(detail.getLectureroom_id());
			cells[6] = String.valueOf(detail.getUser_id());
			cells[7] = detail.getPurpose();

			model.addRow(cells);
		}

		return model;
	}

	public boolean checkID(int id) throws SQLException {
		return da.checkId(id);
	}

	public boolean checkPK(int adminID, int userID, int roomID) throws SQLException {
		if (!da.checkPK1(roomID, userID) && !da.checkPK2(roomID, adminID))
			return true;
		else return false;
	}
	
	public void addDetail(int id, Time time_in, Time time_out, int date, int admin_id, int lectureroom_id, int user_id,
			String purpose) throws SQLException {
		da.addUsesDetail(id, time_in, time_out, date, admin_id, lectureroom_id, user_id, purpose);
	}

	public void deleteDetail(int id) throws SQLException {
		da.deleteUsesDetail(id);
	}

	public void updateDetail(int id, Time time_in, Time time_out, int date, int admin_id, int lectureroom_id,
			int user_id, String purpose) throws SQLException {
		da.updateUsesDetail(id, time_in, time_out, date, admin_id, lectureroom_id, user_id, purpose);
	}

	public DefaultTableModel searchDetail(int id, Time timeIn, Time timeOut, int date, int adminId, int roomId,
			int userId, String purpose) throws SQLException {
		List<UsesDetail> list = da.search(id, timeIn, timeOut, date, adminId, roomId, userId, purpose);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Time in");
		model.addColumn("Time out");
		model.addColumn("Date");
		model.addColumn("Admin ID");
		model.addColumn("Lectureroom ID");
		model.addColumn("User ID");
		model.addColumn("Pupose");

		for (UsesDetail detail : list) {
			String[] cells = new String[8];
			cells[0] = String.valueOf(detail.getId());
			cells[1] = String.valueOf(detail.getTime_in());
			cells[2] = String.valueOf(detail.getTime_out());
			cells[3] = String.valueOf(detail.getDate());
			cells[4] = String.valueOf(detail.getAdmin_id());
			cells[5] = String.valueOf(detail.getLectureroom_id());
			cells[6] = String.valueOf(detail.getUser_id());
			cells[7] = detail.getPurpose();

			model.addRow(cells);
		}

		return model;
	}
}
