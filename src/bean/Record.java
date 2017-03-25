package bean;

import java.io.Serializable;
import java.util.Comparator;
public class Record implements Serializable, Comparable <Record>{

	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	private String company;

	public Record() {
		name = "";
		address = "";
		email = "";
		phoneNumber = "";
		company = "";
	}

	public Record(String name, String address, String email, String phoneNumber, String company) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public int compareTo(Record o) {
		
		return (this.name.compareToIgnoreCase(o.name));
	}
	
	@Override
	public String toString() {
		return "Record [name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", company=" + company + "]";
	}

	public class CompanyComparator implements Comparator<Record>{
		@Override 
	    public int compare(Record a, Record b){
	     
	        return a.getCompany().compareTo(b.getCompany());
	    }
	}

}
