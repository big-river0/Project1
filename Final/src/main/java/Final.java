import java.awt.Desktop;
import java.net.URI;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

//청주대 학생도우미 봇
// 기능 1. 학식 식단표 링크 열기 2. 셔틀버스 다음 출발 시각 확인

public class Final {

    private static final String[] CAFETERIA = {
            "학생식당",
            "기숙사 식당",
            "경상대학 식당",
            "본관 교직원 식당"
    };
    private static final String[] CAFETERIA_URL = {
            "https://www.cju.ac.kr/www/selectTnRstrntMenuListU.do?sc1=student&key=5120&",
            "https://www.cju.ac.kr/www/selectTnRstrntMenuListU.do?sc1=domitory&key=5118&",
            "https://www.cju.ac.kr/www/selectTnRstrntMenuListU.do?sc1=mcollege&key=9140&",
            "https://www.cju.ac.kr/www/selectTnRstrntMenuListU.do?sc1=employee&key=5119&"
    };

    private static final String[] mainToDorm = {
            "08:10","08:30","08:50","09:10","09:30",
            "10:10","10:30","11:10","11:40","12:20",
            "13:00","13:40","14:20","15:00","15:40",
            "16:20","16:40","17:20","17:40"
    };

    private static final String[] dormToMain = {
            "08:20","08:40","09:00","09:20","09:40",
            "10:20","10:40","11:20","11:40","12:40",
            "13:20","14:00","14:40","15:20","16:00",
            "16:40","17:00","17:40","18:00"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 청주대 학생도우미 봇 ===");
            System.out.println("1. 학식 식단표 열기");
            System.out.println("2. 다음 셔틀버스 출발 시간");
            System.out.println("3. 종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    openMeal(sc);
                    break;
                case 2:
                    nextBus();
                    break;
                case 3:
                    System.out.println("이용해주셔서 감사합니다!");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private static void openMeal(Scanner sc) {
        for (int i = 0; i < CAFETERIA.length; i++)
            System.out.printf("%d) %s%n", i + 1, CAFETERIA[i]);

        System.out.print("열고 싶은 식당 번호 (0=취소): ");
        int pick = sc.nextInt() - 1;
        if (pick < 0 || pick >= CAFETERIA.length) return;

        try {
            Desktop.getDesktop().browse(new URI(CAFETERIA_URL[pick]));
            System.out.println("브라우저가 열렸습니다: " + CAFETERIA[pick]);
        } catch (Exception e) {
            System.out.println("링크 열기에 실패했습니다: " + e.getMessage());
        }
    }

    private static void nextBus() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("\n현재 시각: " + now.format(fmt));

        System.out.println("\n정문 → 생활관");
        System.out.println("다음 버스: " + nextTime(now, mainToDorm));

        System.out.println("\n생활관 → 정문");
        System.out.println("다음 버스: " + nextTime(now, dormToMain));

        System.out.println("\n※ 화·수·목 09:00~11:00에는 버스 1대만 운행");
        System.out.println("※ 만차 시 무정차(경적 2회)하니 주의하세요!");
    }

    private static String nextTime(LocalTime now, String[] table) {
        for (String t : table) {
            if (LocalTime.parse(t).isAfter(now)) return t;
        }
        return "오늘 운행 종료";
    }
}
