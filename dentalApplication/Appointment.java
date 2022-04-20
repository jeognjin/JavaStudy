package dentalApplication;

//Appointment DTO
public class Appointment {

	private Doctor doctor;
	private String appointmentDate;
	private Patient PatientService;
	private String symptom;

	public Appointment(Doctor doctor, String appointmentDate, Patient PatientService, String symptom) {
		super();
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.PatientService = PatientService;
		this.symptom = symptom;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Patient getPatientService() {
		return PatientService;
	}

	public void setPatientService(Patient PatientService) {
		this.PatientService = PatientService;
	}

	public String getsymptom() {
		return symptom;
	}

	public void setsymptom(String symptom) {
		this.symptom = symptom;
	}

	@Override
	public String toString() {
		return "이름: " + PatientService.getPatientName() + "님  담당의사: " + doctor.getDoctorName() + " 예약날짜: "
				+ appointmentDate + " 증상: " + symptom;
	}

}
