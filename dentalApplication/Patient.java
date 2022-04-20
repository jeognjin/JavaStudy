package dentalApplication;

import java.util.ArrayList;
import java.util.List;

//Membership DTO
public class Patient {
	private String patientID;
	private String patientPassword;
	private String patientName;
	private String patientPhone;
	private List<Appointment> list = new ArrayList<>();

	public Patient(String patientID, String patientPassword, String patientName, String patientPhone) {
		super();
		this.patientName = patientName;
		this.patientPhone = patientPhone;
		this.patientID = patientID;
		this.patientPassword = patientPassword;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getPatientPassword() {
		return patientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}

	public List<Appointment> getList() {
		return list;
	}

	public void setList(List<Appointment> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "이름: " + patientName + " 번호: " + patientPhone + " 아이디: " + patientID;
	}

}
