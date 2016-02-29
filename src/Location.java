
public class Location {
	private String buildingID;
	private String roomNumber;
	
	public Location(String bID, String rNumber){
		setBuildingID(bID);
		setRoomNumber(rNumber);
	}
	
	public String toString(){
		return getBuildingID() + " " + getRoomNumber();
	}
	
	public boolean equals(Location other){
		if(getBuildingID().equals(other.getBuildingID()) && getRoomNumber().equals(other.getRoomNumber()))
			return true;
		return false;
	}
	
	public String getBuildingID() {
		return buildingID;
	}

	private void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	private void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
}
