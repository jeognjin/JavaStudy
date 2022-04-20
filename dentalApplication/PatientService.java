package dentalApplication;

import java.util.Scanner;

public class PatientService {
	Scanner sc = new Scanner(System.in);

	// 회원 정보를 저장할 배열 - DB 대체
	static Patient[] patients = new Patient[1000];

	public PatientService() {
	}

	public static Patient[] getpatients() {
		return patients;
	}

	public static void setpatients(Patient[] patients) {
		PatientService.patients = patients;
	}

	static {
		patients[0] = new Patient("patient1", "1", "tom", "010-1234-1234");
		patients[1] = new Patient("patient2", "2", "sam", "010-1234-1234");
		patients[2] = new Patient("patient3", "3", "jane", "010-1234-1234");
		patients[3] = new Patient("patient4", "4", "jerry", "010-1234-1234");
		patients[4] = new Patient("patient5", "5", "john", "010-1234-1234");
	}

	// 회원가입
	public String joinUs(String patientID) {
		String result = "";
		if (findpatients(patientID) != null) { // 아이디 중복체크
			System.out.println("아이디 중복. 새로운 아이디를 입력하세요.");
		} else {
			System.out.print("비밀번호를 입력하세요>>>");
			String patientPassword = sc.nextLine();
			System.out.print("이름을 입력하세요>>>");
			String patientName = sc.nextLine();
			System.out.print("전화번호를 입력하세요>>>");
			String patientPhone = sc.nextLine();
			// 입력받은 정보로 객체 생성
			Patient newPatient = new Patient(patientID, patientPassword, patientName, patientPhone);
			for (int i = 0; i < patients.length; i++) { // array에 회원정보 저장
				if (patients[i] == null) { // 빈자리에 입력
					patients[i] = newPatient;
					result = newPatient.getPatientName() + "님의 아이디 [" + newPatient.getPatientID() + "]가 생성되었습니다.";
					break;
				}
			}
		}
		return result;
	}

	// 로그인
	public Patient login(String id, String password) {
		Patient patientService = null;
		if (id != null) {
			patientService = findpatients(id); // 입력받은 값으로 동일한 아이디가 있는지 체크해서 객체로 반환
			if (patientService != null && password.equals(patientService.getPatientPassword())) {
				return patientService;
			} else {
				return null;
			}
		}
		return patientService;
	}

	// patientID 확인하기
	private Patient findpatients(String patientID) {
		Patient patientService = null;
		for (int i = 0; i < patients.length; i++) {
			if (patients[i] != null) {
				String dbPatient = patients[i].getPatientID();
				if (dbPatient.equals(patientID)) { // array에 있는 ID와 일치여부 확인
					patientService = patients[i]; // 일치하는 정보를 객체에 입력
					break;
				}
			} else {
				return null;
			}
		}
		return patientService;
	}

	// 회원목록
	public void patientList() {
		try {
			for (int i = 0; i < patients.length; i++) {
				if (patients[i].getPatientID() != null) {
					System.out.println("[" + (i + 1) + "]" + patients[i]);
				}
			}
		} catch (NullPointerException e) {

		}
	}
}
