import java.io.IOException;
import java.util.ArrayList;

/**
 * This java class runs all steps, from loading the local HTML file, parsing and
 * processing the file, to writing it to a CSV.
 * 
 * @author Jie (Jocelyn) Bao
 *
 */
public class JobPostingDataSourcing {

	public static void main(String[] args) throws IOException {
		HTMLParser myHTMLParser = new HTMLParser();
		JobPostingDataWriter myDataWriter = new JobPostingDataWriter();
		ArrayList<String> myData = myHTMLParser.myHTMLParser();
		myDataWriter.writeJobInfo(myData);

	}
}
