import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		try {
			ArrayList<Match> matches = HTMLParse.updateMatches();
			for(int i =0; i < matches.size(); i++) {
				System.out.println(matches.get(i));
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}
