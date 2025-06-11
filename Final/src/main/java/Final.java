
import java.util.Scanner;

public class Final {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디: ");
        String id = sc.nextLine();
        System.out.print("비밀번호: ");
        String pw = sc.nextLine();

        String[] Courses = {" JAVA프로그래밍", "데이터통신"};
        System.out.println("\n=== 수강과목 목록 ===");
        for(String course : Courses) {
            System.out.println("\n▶ " + course);
            printNotices();
            printAssignments();
        }
    }

    private static void printNotices() {
        System.out.println("[공지사항]");
        System.out.println("- 06/13: 기말고사 일정 안내");
        System.out.println("- 06/23: 보강수업 공지");
    }

    private static void printAssignments() {
        System.out.println("[과제]");
        System.out.println("- 과제 4 제출 (마감: 06/12)");
        System.out.println("- 개인발표 문서 제출 (마감: 06/10)");
    }
}
