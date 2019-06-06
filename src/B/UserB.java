package B;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import DA.UserDA;
import Object.User;

public class UserB {
	private UserDA uDA;

	public UserB() {
		uDA = new UserDA();
	}

	public boolean checkID(int id) throws SQLException {
		return uDA.checkId(id);
	}
	
	public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
		return uDA.checkUser(username, password);
	}

	public boolean checkAdmin(String username, String password) throws SQLException {
		return uDA.checkAdmin(username, password);
	}
	
	public boolean checkUsername(String username) throws SQLException {
		return uDA.checkUsername(username);
	}

	public boolean checkPass(String password) {
		boolean hasNum = false;
		boolean hasCap = false;
		boolean hasLow = false;
		char c;
		for (int i = 0; i < password.length(); i++) {
			c = password.charAt(i);
			if (Character.isDigit(c))
				hasNum = true;
			else if (Character.isUpperCase(c))
				hasCap = true;
			else if (Character.isLowerCase(c))
				hasLow = true;
			if (hasNum && hasCap && hasLow)
				return true;
		}
		return false;
	}

	public boolean valPassword(String password) {
		if (password.length() > 7) {
			if (checkPass(password))
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
	
	public DefaultTableModel getAllItemAdminPermission() throws SQLException {
		List<User> users = uDA.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Username");
		model.addColumn("Password");
		model.addColumn("Email");
		model.addColumn("Fullname");
		model.addColumn("DateOfBirth");
		model.addColumn("Sex");
		model.addColumn("Role");

		for (User user : users) {
			String[] cells = new String[8];
			cells[0] = String.valueOf(user.getId());
			cells[1] = user.getUsername();
			cells[2] = user.getPassword();
			cells[3] = user.getEmail();
			cells[4] = user.getFullname();
			cells[5] = String.valueOf(user.getDob());
			cells[6] = user.getSex();
			int role = user.getRole();
			switch (role) {
			case 0:
				cells[7] = "ADMIN";
				break;
			case 1:
				cells[7] = "TEACHER";
				break;
			case 2:
				cells[7] = "STUDENT";
				break;
			default:
				cells[7] = "NULL";
				break;
			}
			 
			model.addRow(cells);
		}

		return model;
	}
	
	public DefaultTableModel getAllItem() throws SQLException {
		List<User> users = uDA.getAll();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Username");
		model.addColumn("Email");
		model.addColumn("Fullname");
		model.addColumn("DateOfBirth");
		model.addColumn("Sex");
		model.addColumn("Role");

		for (User user : users) {
			String[] cells = new String[7];
			cells[0] = String.valueOf(user.getId());
			cells[1] = user.getUsername();
			cells[2] = user.getEmail();
			cells[3] = user.getFullname();
			cells[4] = String.valueOf(user.getDob());
			cells[5] = user.getSex();
			int role = user.getRole();
			switch (role) {
			case 0:
				cells[6] = "ADMIN";
				break;
			case 1:
				cells[6] = "TEACHER";
				break;
			case 2:
				cells[6] = "STUDENT";
				break;
			default:
				cells[6] = "NULL";
				break;
			}
			 
			model.addRow(cells);
		}

		return model;
	}
	
	public String getName(String username) throws SQLException {
		return uDA.getName(username);
	}
	
	public void addUser(int id, String username, String password, String email, String fullname, Date dob, boolean isMale,
			int role) throws SQLException {
		uDA.addUser(id, username, password, email, fullname, dob, isMale, role);
	}

	public void editUser(int id, String username, String password, String email, String fullname, Date dob,
			boolean isMale, int role) throws SQLException {
		uDA.updateUser(id, username, password, email, fullname, dob, isMale, role);
	}

	public DefaultTableModel searchUser(int id, String username,String password, String email, String fullname, Date dob,
			String sex, int role) throws SQLException {
		List<User> list= uDA.search(id, username, password, email, fullname, dob, sex, role);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Username");
		model.addColumn("Email");
		model.addColumn("Fullname");
		model.addColumn("DateOfBirth");
		model.addColumn("Sex");
		model.addColumn("Role");
		
		for (User user : list) {
			String[] cells = new String[7];
			cells[0] = String.valueOf(user.getId());
			cells[1] = user.getUsername();
			cells[2] = user.getEmail();
			cells[3] = user.getFullname();
			cells[4] = String.valueOf(user.getDob());
			cells[5] = user.getSex();
			int role1 = user.getRole();
			switch (role1) {
			case 0:
				cells[6] = "ADMIN";
				break;
			case 1:
				cells[6] = "TEACHER";
				break;
			case 2:
				cells[6] = "STUDENT";
				break;
			default:
				cells[6] = "NULL";
				break;
			}
			 
			model.addRow(cells);
		}
		
		return model;
	}
	
	public void deleteUser(int id) throws SQLException {
		uDA.deleteUser(id);
	}
}
