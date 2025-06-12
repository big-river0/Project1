import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Map;

public class Final {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디: ");
        String id = sc.nextLine().trim();
        System.out.print("비밀번호: ");
        String pw = sc.nextLine().trim();

        try {
            /* 1) 로그인 페이지 접속(쿠키 확보) */
            Connection.Response loginPage = Jsoup
                    .connect("https://hive.cju.ac.kr/common/login/loginpage.do")
                    .method(Connection.Method.GET)
                    .execute();

            Map<String, String> cookies = loginPage.cookies();
            Document loginDoc = loginPage.parse();

            /* 2) 로그인 폼 action 추출 (예비값 포함) */
            Element form = loginDoc.selectFirst("form");
            String loginAction = (form != null)
                    ? form.absUrl("action")          // 동적 추출
                    : "https://hive.cju.ac.kr/common/login/loginProc.do"; // 추정 기본값

            /* 3) POST로 자격 증명 제출 */
            Connection.Response loginResp = Jsoup
                    .connect(loginAction)
                    .cookies(cookies)                // 세션 유지
                    .data("userId", id)              // 파라미터 이름은 실제 HTML 확인 필요
                    .data("userPwd", pw)
                    .method(Connection.Method.POST)
                    .execute();

            cookies.putAll(loginResp.cookies());     // 신규 쿠키 병합

            /* 4) 로그인 성공 여부 확인 */
            if (!loginResp.body().contains("로그아웃")) {
                System.out.println("로그인 실패: 아이디/비밀번호 확인");
                return;
            }

            /* 5) 공지사항 페이지 접근 */
            Document noticePage = Jsoup
                    .connect("https://hive.cju.ac.kr/usr/classroom/course/bbs/notice/list.do")
                    .cookies(cookies)
                    .get();

            System.out.println("\n=== 수강과목 공지사항 ===");
            parseNotices(noticePage);

        } catch (IOException e) {
            System.out.println("정보 조회 중 오류 발생: " + e.getMessage());
        }
    }

    /* 공지사항 테이블 파싱 */
    private static void parseNotices(Document doc) {
        Elements rows = doc.select("table tbody tr");
        if (rows.isEmpty()) {
            System.out.println("공지사항이 없습니다.");
            return;
        }
        for (Element row : rows) {
            String title = row.selectFirst("td.title").text();
            String date  = row.selectFirst("td.date").text();
            System.out.println("- " + title + " (" + date + ")");
        }
    }
}
