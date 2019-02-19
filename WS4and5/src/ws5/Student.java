package ws5;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private int stdID;
	private String firstName;
	private String lastName;
	private ArrayList<String> courses;
	
	public Student() {
		this.stdID = 0;
		this.firstName = "";
		this.lastName = "";
		courses = new ArrayList<String>();
	}

	public Student(int stdID, String firstName, String lastName) {
		super();
		if (stdID > 0)
			this.stdID = stdID;
		this.firstName = firstName;
		this.lastName = lastName;

		courses = new ArrayList<String>();
	}

	public int getStdID() {
		return stdID;
	}

	public void setStdID(int stdID) {
		if (stdID > 0)
			this.stdID = stdID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

}
