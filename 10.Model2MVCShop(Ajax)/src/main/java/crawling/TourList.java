package crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.model2.mvc.service.domain.Tour;


public class TourList {

	public static List<Tour> tourList(String keyword, String pageNum) throws IOException {

		
		List<Tour> list = new ArrayList<>();

		// 유럽 외의 지역 검색하려면 serviceType 생략
		String searchUrl = "https://www.wishbeen.co.kr/city?serviceType=europe&searchByContent=" + keyword
				+ "&tab=attraction&viewPageNum=" + pageNum + "&perPage=20";

		Document doc = Jsoup.connect(searchUrl).userAgent(
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
				.get();

		//System.out.println(doc);

		// System.out.println(doc.select(".paging .btn-next").text().equals("다음"));

		

		Elements el = doc.select(".paging li");

		String viewCount = "";
		for (Element e : el) {
			// System.out.println(e.text());
			viewCount = e.text();

		}
		// System.out.println(viewCount);
		System.out.println("투어리스트 진행중");

		

		for (int i = 1; i < 100; i++) {
			el = doc.select(".spots-contents:nth-child(" + i + ")");
			// for문 돌리면서 비교하자 마지막 자식까지 왔는지 체크
			if (el.isEmpty()) {
				break;
			}
			for (Element e : el) {
				Tour tour = new Tour();
			System.out.println("상세 조회시 아이디값 : "+e.select(".spot-info-box").attr("data-id"));
			System.out.println("관광지명 : "+e.select(".spot-name").text());
			System.out.println("관광지 썸네일 src : "+e.select(".spots-contents > .spot-img a img").attr("src"));
			System.out.println("관광지 간략설명 : "+e.select(".spot-state").text());
			System.out.println("관광지 위치 도시?? : " + e.select(".desc > .town").text());
			System.out.println("=======================================================================================");

				tour.setTourId(e.select(".spot-info-box").attr("data-id"));
				tour.setTourName(e.select(".spot-name").text());
				tour.setTourThumb(e.select(".spots-contents > .spot-img a img").attr("src"));
				tour.setTourShortDesc(e.select(".spot-state").text());
				tour.setTourLoc(e.select(".desc > .town").text());
				// true면 10페이지 이후도 존재하는것. 아래 searchKeyword , viewPageNum=11 , url 파라미터로 보낼것
				tour.setNextBtn(doc.select(".paging .btn-next").text().equals("다음"));
				tour.setViewCount(viewCount);// 개수만큼 페이지 수 만들것.
				tour.setKeyword(keyword);
				list.add(tour);
			}
		}

		return list;
	}
}
