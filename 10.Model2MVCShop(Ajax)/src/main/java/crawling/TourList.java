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

		// ���� ���� ���� �˻��Ϸ��� serviceType ����
		String searchUrl = "https://www.wishbeen.co.kr/city?serviceType=europe&searchByContent=" + keyword
				+ "&tab=attraction&viewPageNum=" + pageNum + "&perPage=20";

		Document doc = Jsoup.connect(searchUrl).userAgent(
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
				.get();

		//System.out.println(doc);

		// System.out.println(doc.select(".paging .btn-next").text().equals("����"));

		

		Elements el = doc.select(".paging li");

		String viewCount = "";
		for (Element e : el) {
			// System.out.println(e.text());
			viewCount = e.text();

		}
		// System.out.println(viewCount);
		System.out.println("�����Ʈ ������");

		

		for (int i = 1; i < 100; i++) {
			el = doc.select(".spots-contents:nth-child(" + i + ")");
			// for�� �����鼭 ������ ������ �ڽı��� �Դ��� üũ
			if (el.isEmpty()) {
				break;
			}
			for (Element e : el) {
				Tour tour = new Tour();
			System.out.println("�� ��ȸ�� ���̵� : "+e.select(".spot-info-box").attr("data-id"));
			System.out.println("�������� : "+e.select(".spot-name").text());
			System.out.println("������ ����� src : "+e.select(".spots-contents > .spot-img a img").attr("src"));
			System.out.println("������ �������� : "+e.select(".spot-state").text());
			System.out.println("������ ��ġ ����?? : " + e.select(".desc > .town").text());
			System.out.println("=======================================================================================");

				tour.setTourId(e.select(".spot-info-box").attr("data-id"));
				tour.setTourName(e.select(".spot-name").text());
				tour.setTourThumb(e.select(".spots-contents > .spot-img a img").attr("src"));
				tour.setTourShortDesc(e.select(".spot-state").text());
				tour.setTourLoc(e.select(".desc > .town").text());
				// true�� 10������ ���ĵ� �����ϴ°�. �Ʒ� searchKeyword , viewPageNum=11 , url �Ķ���ͷ� ������
				tour.setNextBtn(doc.select(".paging .btn-next").text().equals("����"));
				tour.setViewCount(viewCount);// ������ŭ ������ �� �����.
				tour.setKeyword(keyword);
				list.add(tour);
			}
		}

		return list;
	}
}
