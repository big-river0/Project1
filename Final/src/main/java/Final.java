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
        String id = sc.nextLine();
        System.out.print("비밀번호: ");
        String pw = sc.nextLine();

        try {
            Connection.Response loginResponse = Jsoup.connect("https://hive.cju.ac.kr/common/login/loginpage.do")
                    .method(Connection.Method.GET)
                    .execute();

            Map<String, String> cookies = loginResponse.cookies();


            Document noticePage = Jsoup.connect("https://hive.cju.ac.kr/usr/classroom/course/bbs/notice/list.do")
                    .cookies(cookies)
                    .get();

            System.out.println("\n=== 수강과목 공지사항 ===");
            parseNotices(noticePage);

        } catch (IOException e) {
            System.out.println("정보 조회 중 오류 발생: " + e.getMessage());
        }
    }



    private static void parseNotices(Document doc) {
        Elements noticeList = doc.select("table.board-list > tbody > tr");

        for (Element notice : noticeList) {
            String title = notice.select("td.title > a").text();
            String date = notice.select("td.date").text();

            System.out.println("\n▶ " + title);
            System.out.println("  - 등록일: " + date);
        }
    }
}
