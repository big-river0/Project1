import java.util.Scanner;

public class Final {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== 청주대 학생도우미 봇 ===");
            System.out.println("1. 학식 정보 보기");
            System.out.println("2. 셔틀버스 다음 출발 시간 확인");
            System.out.println("3. 종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    meal(sc);
                    break;
                case 2:
                    bus();
                    break;
                case 3:
                    System.out.println("이용해주셔서 감사합니다!");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
            }
        }
    }

    private static void meal(Scanner sc) {
        System.out.println("\n=== 학식 정보 ===");
        System.out.println("1) 학생식당");
        System.out.println("2) 기숙사 식당");
        System.out.println("3) 경상대학 식당");
        System.out.println("4) 본관 교직원 식당");
        System.out.print("식당 번호 선택 (0: 취소): ");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("\n[학생식당 메뉴]");
                System.out.println("월요일: 김치찌개, 돈까스");
                System.out.println("화요일: 부대찌개, 생선까스");
                System.out.println("수요일: 순두부찌개, 치킨까스");
                System.out.println("목요일: 매운찜닭, 돈까스");
                System.out.println("금요일: 육개장, 치킨까스");
                break;
            case 2:
                System.out.println("\n[기숙사 식당 메뉴]");
                System.out.println("월요일: 미역국, 제육볶음");
                System.out.println("화요일: 콩나물국, 닭갈비");
                System.out.println("수요일: 된장국, 불고기");
                System.out.println("목요일: 미소된장국, 제육볶음");
                System.out.println("금요일: 김치찌개, 닭볶음탕");
                break;
            case 3:
                System.out.println("\n[경상대학 식당 메뉴]");
                System.out.println("월요일: 된장찌개, 치킨까스");
                System.out.println("화요일: 육개장, 함박스테이크");
                System.out.println("수요일: 김치찌개, 돈까스");
                System.out.println("목요일: 갈비탕, 생선까스");
                System.out.println("금요일: 부대찌개, 함박스테이크");
                break;
            case 4:
                System.out.println("\n[본관 교직원 식당 메뉴]");
                System.out.println("월요일: 갈비탕, 연어스테이크");
                System.out.println("화요일: 갈비찜, 새우튀김");
                System.out.println("수요일: 스테이크, 연어구이");
                System.out.println("목요일: 해물탕, 안심스테이크");
                System.out.println("금요일: 특식메뉴, 회덮밥");
                break;
            case 0:
                System.out.println("취소되었습니다.");
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }

    private static void bus() {

        String[] mainToDorm = {"08:10","08:30","08:50","09:10","09:30","10:10","10:30","11:10","11:40","12:20","13:00","13:40","14:20","15:00","15:40","16:20","16:40","17:20","17:40"};
        String[] dormToMain = {"08:20","08:40","09:00","09:20","09:40","10:20","10:40","11:20","11:40","12:40","13:20","14:00","14:40","15:20","16:00","16:40","17:00","17:40","18:00"};

        System.out.println("\n[정문 → 생활관]");
        for (String t : mainToDorm) System.out.print(t + "  ");
        System.out.println("\n\n[생활관 → 정문]");
        for (String t : dormToMain) System.out.print(t + "  ");

        System.out.println("\n\n※ 화·수·목 09:00~11:00엔 버스 1대만 운행");
        System.out.println("※ 만차 시 무정차(경적 2회)하니 주의하세요!");
    }
}
