package dentalApplication;

import java.util.ArrayList;
import java.util.List;

//Doctor DTO
public class Doctor {
	private String doctorName;
	private String doctorID;
	private String doctorPassword;
	private List<Appointment> list = new ArrayList<>();
	private String specialized;
	
	public Doctor(String doctorID, String doctorPassword, String doctorName, 
			String specialized) {
		super();
		this.doctorName = doctorName;
		this.doctorID = doctorID;
		this.doctorPassword = doctorPassword;
		this.specialized = specialized;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}
	public String getDoctorPassword() {
		return doctorPassword;
	}
	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}
	public List<Appointment> getList() {
		return list;
	}
	public void setList(List<Appointment> list) {
		this.list = list;
	}
	public String getSpecialized() {
		return specialized;
	}
	public void setSpecialized(String specialized) {
		this.specialized = specialized;
	}
	@Override
	public String toString() {
		return "담당의사: " + doctorName + " 진료과목: " + specialized ;
	}

	
}
