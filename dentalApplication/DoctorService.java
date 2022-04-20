package dentalApplication;

import java.util.Scanner;

public class DoctorService {

	static Doctor[] doctors = new Doctor[1000];
	static Scanner sc = new Scanner(System.in);

	static {
		doctors[0] = new Doctor("doctor1", "1", "김철수", "임플란트");
		doctors[1] = new Doctor("doctor2", "2", "최영희", "크라운");
		doctors[2] = new Doctor("doctor3", "3", "Oliver", "치아검진");
		doctors[3] = new Doctor("doctor4", "4", "Emma", "스케일링");
	}

	public DoctorService() {
	}

	public static Doctor[] getDoctors() {
		return doctors;
	}

	public static void setDoctors(Doctor[] doctors) {
		DoctorService.doctors = doctors;
	}

	// 관리자 등록
	public static String doctorSignUp(String doctorID, String doctorPassword, String doctorName, String specialized) {
		String result = "";
		// 입력받은 정보로 객체 생성
		Doctor newDoctor = new Doctor(doctorID, doctorPassword, doctorName, specialized);
		for (int i = 0; i < doctors.length; i++) { // array에 회원정보 저장
			if (doctors[i] == null) { // 빈자리에 입력
				doctors[i] = newDoctor;
				result = doctorName + "님의 아이디 [" + doctorID + "]가 생성되었습니다.";
				return result;
			}
		}
		return result;
	}

	// 관리자 ID 중복 확인하기
	public static Doctor findDoctors(String doctorID) {
		Doctor doctorship = null;
		for (int i = 0; i < doctors.length; i++) {
			if (doctors[i] != null) {
				String dbDoctor = doctors[i].getDoctorID();
				if (dbDoctor.equals(doctorID)) { // array에 있는 ID와 일치여부 확인
					doctorship = doctors[i];
					break;
				}
			} else {
				return null;
			}
		}
		return doctorship;
	}

	// 관리자 로그인 id, password 일치 확인
	public Doctor masterLogin(String id, String password) {
		Doctor doctorship = null;
		if (id != null) {
			doctorship = findDoctors(id);
			if (doctorship != null && password.equals(doctorship.getDoctorPassword())) {
				return doctorship;
			} else {
				return null;
			}
		}
		return doctorship;
	}

	// Doctor List
	public void doctorList() {
		for (int i = 0; i < doctors.length; i++) {
			if (doctors[i] != null) {
				System.out.println("[" + (i + 1) + "] " + doctors[i]);

			}
		}
	}

}
