package dentalApplication;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class DentalApplication {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PatientService patientService = new PatientService();
		DoctorService doctorService = new DoctorService();
		AppointmentService appointment = new AppointmentService();
		boolean run = true;
		boolean flag = true;

		while (true) {
			run = true;
			flag = true;
			System.out.println("────────────────────────────────────────────────────");
			System.out.println("\t\t♧♣ 스마일 치과 예약 프로그램 ♣♧");
			System.out.println("────────────────────────────────────────────────────");
			System.out.println("  1.회원 등록      2.회원 로그인     3.의사 등록    4.의사 로그인  ");
			System.out.println("────────────────────────────────────────────────────");
			System.out.print("번호 선택>>> ");
			try { // 익셉션 체크
				int selectNo = Integer.parseInt(sc.nextLine());
				switch (selectNo) {
				case 1:
					System.out.print("사용할 아이디를 입력하세요>>>");
					String patientID = sc.nextLine();
					String joinResult = patientService.joinUs(patientID); // 입력받은 값을 메소드로 넘기기
					System.out.println(joinResult); // 메소드에서 가입완료 후 result 입력값을 받아서 출력하기
					break;
				case 2:
					System.out.print("아이디를 입력하세요>>>");
					String id = sc.nextLine();
					System.out.print("비밀번호를 입력하세요>>>");
					String password = sc.nextLine();
					Patient patientInfo = patientService.login(id, password); // 아이디와 비밀번호를 체크하여 해당하는 멤버객체 반환받기
					if (patientInfo != null) { // login메소드에서 id,비번이 일치하면 석세스 반환
						System.out.println("로그인 성공.");
						while (run) { // 로그인 메뉴
							System.out.println("────────────────────────────────────────────────────");
							System.out.println("  1.예약하기     2.예약내역 조회하기     3.예약 날짜 변경    4.예약취소");
							System.out.println("────────────────────────────────────────────────────");
							System.out.print("번호 선택(전단계: 0)>>> ");
							int select = Integer.parseInt(sc.nextLine());
							switch (select) {
							case 0: // 전단계로 돌아가기 - while문 빠져나감
								run = false;
								break;
							case 1:
								System.out.print("예약할 날짜를 입력하세요(yyyy/mm/dd)>>>");
								String newAppointment = sc.nextLine();
								// 입력한 날짜가 예약 가능한 날짜인지 체크
								try {
									String result = appointment.appointmentDateCheck(newAppointment, patientInfo);

									if (result != null) {
										System.out.println(result);
									} else {
										System.out.print("증상을 간략하게 기재해주세요>>>");
										String symptom = sc.nextLine();
										doctorService.doctorList(); // 의사 리스트 출력
										System.out.print("담당 의사를 선택해 주세요>>>");
										select = Integer.parseInt(sc.nextLine());
										Doctor doctor = DoctorService.doctors[select - 1]; // 선택된 의사 번호로 해당 닥터객체 반환받기
										result = appointment.newAppointment(doctor, symptom, patientInfo,
												newAppointment);
										System.out.println(result);
									}
								} catch (DateTimeParseException e) {
									System.out.println("형식에 맞게 입력하세요.(yyyy/mm/dd)");
								}
								break;
							case 2:
								List<Appointment> list = appointment.patientAppointmentList(patientInfo);
								if (list.isEmpty()) {
									System.out.println("예약이 없습니다.");
								} else {
									for (int i = 0; i < list.size(); i++) {
										System.out.println("[" + (i + 1) + "] " + list.toString());
									}
								}
								break;
							case 3:

								list = patientInfo.getList();
								for (int i = 0; i < list.size(); i++) {
									System.out.println("[" + (i + 1) + "] " + list.toString());
								}
								System.out.print("변경을 원하시는 예약을 선택해주세요>>>");
								select = Integer.parseInt(sc.nextLine());
								System.out.print("변경할 날짜를 입력해주세요(yyyy/mm/dd)>>>");
								newAppointment = sc.nextLine();
								appointment.changeAppointmentDate(patientInfo, newAppointment, list.get(select - 1));
								break;
							case 4:
								list = patientInfo.getList();
								for (int i = 0; i < list.size(); i++) {
									System.out.println("[" + (i + 1) + "] " + list.get(i));
								}
								System.out.print("취소 할 날짜를 선택하세요>>");
								int cancel = Integer.parseInt(sc.nextLine());
								String result = appointment.cancelOfPatientAppointment(cancel, patientInfo);
								System.out.println(result);
								break;
							default:
								System.out.println("1~4사이의 숫자를 선택해주세요.");
								break;
							}
						}
					} else {
						System.out.println("ID or 비밀번호 오류. 다시 시도해 주세요.");
					}
					break;
				case 3:
					String specialized = null;
					System.out.print("사용할 아이디를 입력하세요>>>");
					String doctorID = sc.nextLine();
					Doctor overlap = DoctorService.findDoctors(doctorID); // 아이디 중복 체크
					if (overlap != null) {
						System.out.println("아이디 중복. 다시 입력해주세요.");
					} else {
						System.out.print("비밀번호를 입력하세요>>>");
						String doctorPassword = sc.nextLine();
						System.out.print("이름을 입력하세요>>>");
						String doctorName = sc.nextLine();
						System.out.print("분야를 선택 하세요(1.임플란트/2.크라운/3.치아검진/4.스케일링)>>>");
						selectNo = Integer.parseInt(sc.nextLine());
						switch (selectNo) {
						case 1:
							specialized = "임플란트";
							break;
						case 2:
							specialized = "크라운";
							break;
						case 3:
							specialized = "치아검진";
							break;
						case 4:
							specialized = "스케일링";
							break;
						default:
							System.out.println("1~4사이의 숫자를 선택해주세요.");
							break;
						}
						String signUpResult = DoctorService.doctorSignUp(doctorID, doctorPassword, doctorName,
								specialized); // 입력받은 값으로 객체 생성
						System.out.println(signUpResult);
					}
					break;
				case 4:
					System.out.print("아이디를 입력하세요>>>");
					id = sc.nextLine();
					System.out.print("비밀번호를 입력하세요>>>");
					password = sc.nextLine();
					Doctor doctor = doctorService.masterLogin(id, password); // 아이디 비밀번호를 체크하여 해당하는 객체 반환
					if (doctor != null) {
						System.out.println("로그인 성공.");
						while (run) { // 관리자 메뉴
							System.out.println("────────────────────────────────────────────────────");
							System.out.println("\t\t1.회원 목록 보기   2.예약 관리  ");
							System.out.println("────────────────────────────────────────────────────");
							System.out.print("번호 선택(전단계: 0)>>> ");
							int selectMaster = Integer.parseInt(sc.nextLine());
							switch (selectMaster) {
							case 0: // 전단계로 돌아가기 - while문 빠져나감
								run = false;
								break;
							case 1:
								patientService.patientList();
								break;
							case 2:
								while (flag) {
									System.out.println("────────────────────────────────────────────────────");
									System.out.println("\t\t1.예약 현황        2.예약 취소  ");
									System.out.println("────────────────────────────────────────────────────");
									System.out.print("번호 선택(전단계: 0)>>> ");
									int selectManagement = Integer.parseInt(sc.nextLine());
									switch (selectManagement) {
									case 0: // 전단계로 돌아가기 - while문 빠져나감
										flag = false;
										break;
									case 1:
										List<Appointment> list = appointment.appointmentList(doctor);
										if (list.isEmpty()) {
											System.out.println("예약이 없습니다.");
										} else {
											for (int i = 0; i < list.size(); i++) {
												System.out.println("[" + (i + 1) + "] " + list.get(i));
											}
										}
										break;
									case 2:
										list = doctor.getList();
										for (int i = 0; i < list.size(); i++) {
											System.out.println("[" + (i + 1) + "] " + list.get(i));
										}
										System.out.print("취소 할 날짜를 선택하세요>>");
										int cancel = Integer.parseInt(sc.nextLine());
										String result = appointment.appointmentCancel(cancel, doctor);
										System.out.println(result);
										break;
									default:
										System.out.println("1 또는 2를 선택해주세요.");
										break;
									}
								}
								break;
							default:
								System.out.println("1 또는 2를 선택해주세요.");
								break;
							}

						}
					} else {
						System.out.println("ID 또는 비밀번호 오류. 다시 시도해 주세요.");
					}
					break;

				default:
					System.out.println("1~4사이의 숫자를 선택해주세요.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자를 정확하게 입력해주세요.");
			} catch (NullPointerException e) {
				System.out.println("정확하게 입력해주세요.");
			} catch (Exception e) {
				System.out.println("다시 시도해주세요.");
			}
		}
	}
}
