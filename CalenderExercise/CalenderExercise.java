package api05_date_time;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

class DateInputException extends Exception {
	public DateInputException() {
	}

	public DateInputException(String msg) {
		super(msg);
	}

}

class CalenderExercise {
	private int year;
	private int month;

	CalenderExercise() {
		// TODO Auto-generated constructor stub
	}

	int yearCheck(int year) throws DateInputException {
		if (year == 0) { // 프로그램 종료 선택
			System.out.println("프로그램 종료.");
			return 0;
		} else if (year > 9999) { // 9999년도 이상 강제 오류 발생
			throw new DateInputException("입력오류. 9999년 이하 연도를 입력하시오.");
		} else {
			this.year = year; // CalenderView에 사용할 year값 저장해주기
			return year; // 정상입력시 year 돌려주기
		}

	}

	int monthCheck(int month) throws DateInputException {
		if (month > 12) { // 1~12벗어나는 숫자 입력시 강제 오류 발생
			throw new DateInputException("입력오류. 1~12사이의 숫자를 입력하시오.");
		} else if (month < 1) {
			throw new DateInputException("입력오류. 1~12사이의 숫자를 입력하시오.");
		} else {
			this.month = month; // CalenderView에 사용할 month값 저장해주기
			return month; // 정상입력시 month 돌려주기
		}
	}

	void CalenderView() {
		// 현재시간 cal에 저장
		Calendar cal = Calendar.getInstance();
		// 입력받은 연도, 월(한국:-1), 일(시작일:1)설정
		cal.set(year, month - 1, 1);
		// cal(셋팅된 년월)이 가질수 있는 최대 날짜
		int lastOfDate = cal.getActualMaximum(Calendar.DATE);
		// cal의 1일의 요일을 구해서 week에 저장
		int week = cal.get(Calendar.DAY_OF_WEEK);
		// 시작일 전에 공백입력(일:1, 토:7 week에서 i를 빼면 요일만큼 공백발생-calender는 일요일부터 시작)
		for (int i = 1; i < week; i++)
			System.out.print("│   ");

		for (int i = 1; i <= lastOfDate; i++) {
			if (i == lastOfDate) {
				System.out.printf("│%3d│", i); // 마지막날 끝에 줄 넣기
			} else {
				System.out.printf("│%3d", i); // day출력
				if (week % 7 == 0)
					System.out.println("│"); // 토요일은 끝에 줄 넣어주기
				week++;
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		CalenderExercise ce = new CalenderExercise();
		int year = 0;
		int month = 0;
		while (true) {
			System.out.println("연도와 월을 선택 하세요.(프로그램종료 : 0)");
			System.out.print("연도입력>>>>");

			try { // year 입력오류 잡기
				year = sc.nextInt();
				ce.yearCheck(year);
				if (year == 0) { // 프로그램 종료
					break;
				} else if (year < 99) { // yearCheck 메소드에서 실행이 안되서 메인으로 옮겼어요
					year += 1900;
				}
			} catch (DateInputException e) {
				System.out.println(e.getMessage()); // 입력오류 메세지 출력
				continue; // 입력 오류시 다시시작. 프로그램 종료 노노
			} catch (InputMismatchException e) {
				System.out.println("정수를 입력하시오."); // 소숫점 입력시 오류메세지 출력
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.print("월 입력>>");

			try {
				month = sc.nextInt();
				ce.monthCheck(month);
			} catch (DateInputException e) {
				System.out.println(e.getMessage()); // 입력오류 메세지 출력
				continue; // 입력 오류시 다시시작.
			} catch (InputMismatchException e) {
				System.out.println("정수를 입력하시오."); // 소숫점 입력시 오류메세지 출력
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			System.out.printf("      ♣♧ %d년 %d월 달력 ♧♣\n", year, month);
			System.out.println("┌───┬───┬───┬───┬───┬───┬───┐");
			System.out.println("│Sun│Mon│Tue│Wed│Thu│Fri│Sat│");
			System.out.println("├───┼───┼───┼───┼───┼───┼───┤");

			ce.CalenderView();

			System.out.println("\n└───┴───┴───┴───┴───┴───┴───┘");

		}
	}
}
