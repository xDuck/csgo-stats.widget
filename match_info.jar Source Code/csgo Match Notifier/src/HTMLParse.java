import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class HTMLParse {

	public static ArrayList<Match> updateMatches() throws IOException, ParseException {
		Document doc = Jsoup.connect("https://www.hltv.org/")
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.referrer("http://www.google.com")
				.timeout(1000*10)
				.get();
		//Get team names
		Elements e = doc.select(".vsbox span");
		ArrayList<Match> matches = new ArrayList<Match>();
		int index = 0;
		Match m = new Match();
		for(int i = 0; i < e.size(); i++){
			String team = e.get(i).text();
			if(e.get(i).hasText()) {
				if(index%2 == 0) m.team1 = team;
				else {
					m.team2 = team;
					matches.add(m);
					m = new Match();
				}
				index++;
			}
		}
		//Get Link to match pages
		e = doc.select(".vsbox a");
		for(int i = 0; i < e.size(); i++){
			matches.get(i).link += e.get(i).attr("href");
		}
		//Get Match-Specific info
		for(int i =0; i < matches.size(); i++){
			doc = Jsoup.connect(matches.get(i).link)
					.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.referrer("http://www.hltv.org")
					.timeout(1000*10)
					.get();
			//Get date/time
			e = doc.select(".centerFade div span");
			SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy HH':'mm");
			fmt.setTimeZone(TimeZone.getTimeZone("CET"));
			String d = e.get(3).parent().text().replace("1st", "1").replace("rd", "").replace("th","").replace("of", "").replaceAll("[ ]+", " ");
			Date date = (Date)fmt.parse(d);
			matches.get(i).date = date;

			//Get League
			e = doc.select(".centerFade div a");
			matches.get(i).league = e.get(2).text();

			//Get Maps
			e = doc.select(".hotmatchbox").get(0).select("div img");
			for(int j =0; j < e.size();j++) {
				String map = e.get(j).attr("src").substring(e.get(j).attr("src").lastIndexOf("/")+1).replace(".png", "");
				matches.get(i).maps.add(map);
			}
		}
		return matches;
	}
}
