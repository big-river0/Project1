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
        
            Connection.Response loginPage = Jsoup
                    .connect("https://hive.cju.ac.kr/common/login/loginpage.do")
                    .method(Connection.Method.GET)
                    .execute();

            Map<String, String> cookies = loginPage.cookies();
            Document loginDoc = loginPage.parse();


            Element form = loginDoc.selectFirst("form");
            String loginAction = null;

            if (form != null) {

                String actionAttr = form.attr("action");
                if (actionAttr != null && !actionAttr.trim().isEmpty()) {
                    loginAction = form.absUrl("action");
                }
            }


            if (loginAction == null || loginAction.trim().isEmpty()) {
                loginAction = "https://hive.cju.ac.kr/common/login/loginProc.do";
                System.out.println("기본 로그인 URL 사용: " + loginAction);
            }


            Connection.Response loginResp = Jsoup
                    .connect(loginAction)
                    .cookies(cookies)
                    .data("userId", id)
                    .data("userPwd", pw)
                    .method(Connection.Method.POST)
                    .execute();

            cookies.putAll(loginResp.cookies());


            if (!loginResp.body().contains("로그아웃")) {
                System.out.println("로그인 실패: 아이디/비밀번호 확인");
                return;
            }

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

    private static void parseNotices(Document doc) {
        Elements rows = doc.select("table tbody tr");
        if (rows.isEmpty()) {
            System.out.println("공지사항이 없습니다.");
            return;
        }
        for (Element row : rows) {
            Elements titleCells = row.select("td.title");
            Elements dateCells = row.select("td.date");

            String title = titleCells.isEmpty() ? "제목 없음" : titleCells.text();
            String date = dateCells.isEmpty() ? "날짜 없음" : dateCells.text();

            System.out.println("- " + title + " (" + date + ")");
        }
    }
}
