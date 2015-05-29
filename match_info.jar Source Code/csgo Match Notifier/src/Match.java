import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Match {
	public String team1 = "";
	public String team2 = "";
	public String link = "http://hltv.org";
	public String league = "";
	public ArrayList<String> maps = new ArrayList<String>();
	public Date date;
	public String toString() {
		String id = link.substring(link.indexOf("match/")+6,link.indexOf("-"));
		SimpleDateFormat fmt = new SimpleDateFormat("MMMM d 'at' h:mm a");
		String output = "<a href='"+link+"'>"
		+ "<div id='"+id+"' class='match status-"+(new Date().before(date) ? "red":"green")+"'>" +
        "<div class='title'>" +
				team1 + " vs. " + team2 +
        "</div>" +
        "<div class='league'>" +
        		league +
        "</div>" +
        "<div class='maps'>BO"+maps.size()+":" +
		format(maps)+
		"</div>" +
        "<div class='date'>" +
        		fmt.format(date) +
        "</div>" +
        "</div></a>";
		return output;
	}
	private String format(ArrayList<String> arr) {
		String fmt = " "+arr.toString().replaceAll("[^ ,a-zA-Z]", "");
		for(int i=1; i < fmt.length(); i++){
			if(fmt.charAt(i-1) == ' '){
				fmt = fmt.substring(0, i) + (""+(char)(fmt.charAt(i)-32))+ fmt.substring(i+1);
			}
		}
		return fmt;
	}
}
