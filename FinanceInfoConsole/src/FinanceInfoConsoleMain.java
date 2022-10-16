import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FinanceInfoConsoleMain {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		getKisRatingSpread();
	}
	
	/**
	 * 한국신용평가 등급별금리스프레드 추출
	 */
	public static void getKisRatingSpread() {
		final String kisRatingSpreadList = "https://www.kisrating.com/ratingsStatistics/statics_spread.do#";
		Connection conn = Jsoup.connect(kisRatingSpreadList);
		
		try {
			Document document = conn.get();
			
			/* 헤더 */
			String thead = getKisRatingHeader(document);
			
			/* 바디 */
			String tbody = getKisRatingBody(document);
			
			//TODO : AAA - BBB- 계산한 '신용스프레드' insert
			
			System.out.println(thead);
			System.out.println(tbody);
		} catch (IOException ex) {
		}
	}
	
	/**
	 * 한국신용평가 등급별금리스프레드 헤더
	 * @param document
	 * @return
	 */
	public static String getKisRatingHeader(Document document) {
		Elements kisRatingTableBody = document.select("div.table_ty1 thead tr");
		StringBuilder sb = new StringBuilder();
		
		for(Element element : kisRatingTableBody) {
			for(Element td : element.select("th")) {
				sb.append(td.text());
				sb.append(" ");
			}
			break;
		}
		return sb.toString();
	}
	
	/**
	 * 한국신용평가 등급별금리스프레드 바디
	 * @param document
	 * @return
	 */
	public static String getKisRatingBody(Document document) {
		Elements kisRatingTableBody = document.select("div.table_ty1 tbody tr");
		StringBuilder sb = new StringBuilder();
		
		for(Element element : kisRatingTableBody) {
			for(Element td : element.select("td")) {
				String text = td.text();
				sb.append(text);
				sb.append(" ");
			}
			sb.append(System.getProperty("line.separator"));	// 줄바꿈
		}

		return sb.toString();
	}
}