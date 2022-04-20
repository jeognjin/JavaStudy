package dentalApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.print.Doc;

public class AppointmentService {

	Scanner sc = new Scanner(System.in);
	LocalDateTime date = LocalDateTime.now();
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public AppointmentService() {
		// TODO Auto-generated constructor stub
	}

	// 예약날짜 확인
	public String appointmentDateCheck(String newAppointment, Patient patientInfo) {
		String result = null;
		LocalDate newDate = LocalDate.parse(newAppointment, dateTimeFormatter); // String을 LocalDate로 타입 변경
		LocalDate today = LocalDate.now(); // 오늘날짜 받아오기
		LocalDate afterDay = today.plusDays(7);
		if (newDate.isAfter(afterDay)) {
			result = "7일 이내의 날짜만 예약이 가능합니다.";
		}
		if (newDate.isBefore(today)) {
			result = "오늘 이전 날짜는 선택 하실 수 없습니다.";
		}
		return result;
	}

	// patient-예약완료
	public String newAppointment(Doctor doctor, String symptom, Patient patientInfo, String newAppointment) {
		Appointment appointment = new Appointment(doctor, newAppointment, patientInfo, symptom);
		patientInfo.getList().add(appointment);
		doctor.getList().add(appointment);
		return patientInfo.getPatientName() + "님 " + newAppointment + " 예약이 완료되었습니다.";
	}


	// 예약내역 조회하기-회원
	public List<Appointment> patientAppointmentList(Patient patientInfo) {
		return patientInfo.getList();
	}

	// 예약 날짜 변경하기
	public void changeAppointmentDate(Patient patientInfo, String newAppointment, Appointment appointment) {
		appointment.setAppointmentDate(newAppointment);
	}

	// 환자 메뉴 - 예약취소
	public String cancelOfPatientAppointment(int cancel, Patient patientInfo) {
		patientInfo.getList().remove(cancel - 1);
		return "예약 취소 완료 되었습니다.";
	}

	// 관리자메뉴 - 예약현황
	public List<Appointment> appointmentList(Doctor doctor) {
		return doctor.getList();
	}

	// 관리자메뉴 - 예약취소
	public String appointmentCancel(int cancel, Doctor doctor) {
		doctor.getList().remove(cancel - 1);
		return "예약 취소 완료 되었습니다.";
	}
}
