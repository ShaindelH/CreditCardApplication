package finalProject;

public class Vendor {

	private String name;
	private Address address;
	
	public Vendor(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	
	public Vendor (String name, String street, String city, USState state, String zipCode) {
		
		this.name = name;
		this.address = new Address(street,city, state, zipCode);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}
}
