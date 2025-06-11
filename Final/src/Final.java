public class Final {
    public static void main(String[] args) {

        String[][] courses = {
                {"JAVA프로그래밍1", "3학점", "남수만 교수"},
                {"자료구조", "3학점", "정성재 교수"}
        };

        String[][] notices = {
                {"기말고사 안내", "6월 13일(금) 13:50"},
                {"보강수업 안내", "6월 23일 9시"}
        };

        String[][] assignments = {
                {"리눅스 USB 마운트", "2025-06-12"},
                {"개인 발표(PPT)문서", "2025-06-10"}
        };

        System.out.println("=== 수강과목 목록 ===");
        for(String[] course : courses) {
            System.out.println("\n▶ " + course[0]);
            System.out.println("  - 학점: " + course[1]);
            System.out.println("  - 담당교수: " + course[2]);

            System.out.println("\n[공지사항]");
            for(String[] notice : notices) {
                System.out.println("  - " + notice[0] + ": " + notice[1]);
            }

            System.out.println("\n[과제]");
            for(String[] assignment : assignments) {
                System.out.println("  - " + assignment[0] + " (마감: " + assignment[1] + ")");
            }
        }
    }
}
