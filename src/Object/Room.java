package Object;

public class Room {
	
	private int id;
	private String location;
	private String type;
	private int capacity;
	private String airConditioner;
	private String projector;
	private String fan;
	private String screen;
	private String note;
	
	public Room() {
		super();
	}

	public Room(int id, String location, String type, int capacity, String airConditioner, String projector,
			String fan, String screen, String note) {
		super();
		this.id = id;
		this.location = location;
		this.type = type;
		this.capacity = capacity;
		this.airConditioner = airConditioner;
		this.projector = projector;
		this.fan = fan;
		this.screen = screen;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(String airConditioner) {
		this.airConditioner = airConditioner;
	}

	public String getProjector() {
		return projector;
	}

	public void setProjector(String projector) {
		this.projector = projector;
	}

	public String getFan() {
		return fan;
	}

	public void setFan(String fan) {
		this.fan = fan;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
}
