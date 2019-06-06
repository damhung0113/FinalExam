package Object;

import java.sql.Time;

public class UsesDetail {

	private int id;
	private Time time_in;
	private Time time_out;
	private int date;
	private int admin_id;
	private int lectureroom_id;
	private int user_id;
	private String purpose;
	
	public UsesDetail(int id, Time time_in, Time time_out, int date, int admin_id, int lectureroom_id,
			int user_id, String purpose) {
		super();
		this.id = id;
		this.time_in = time_in;
		this.time_out = time_out;
		this.date = date;
		this.admin_id = admin_id;
		this.lectureroom_id = lectureroom_id;
		this.user_id = user_id;
		this.purpose = purpose;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Time getTime_in() {
		return time_in;
	}

	public void setTime_in(Time time_in) {
		this.time_in = time_in;
	}

	public Time getTime_out() {
		return time_out;
	}

	public void setTime_out(Time time_out) {
		this.time_out = time_out;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public int getLectureroom_id() {
		return lectureroom_id;
	}

	public void setLectureroom_id(int lectureroom_id) {
		this.lectureroom_id = lectureroom_id;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}
