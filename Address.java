package finalProject;

public class Address {

	private String street;
	private String city;
	private USState usState;
	private String zipCode;
	
	public Address(String street, String city, USState usState, String zipCode) {
		this.street = street;
		this.city = city;
		this.usState = usState;
		this.zipCode = zipCode;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setUSState(USState usState) {
		this.usState = usState;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public USState getUSState() {
		return usState;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(street);
		str.append("\n" + city);
		str.append(", " + usState);
		str.append("\n" + zipCode);
		return str.toString();
	}
}
