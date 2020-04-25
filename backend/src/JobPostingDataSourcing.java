import java.io.File;
import java.io.FileNotFoundException;
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

	public JobPostingDataSourcing() {

	}

	public static void main(String[] args) throws IOException {
		HTMLParser myHTMLParser = new HTMLParser();
		JobPostingDataWriter myDataWriter = new JobPostingDataWriter();
		ArrayList<String> myData = null;
		try {
			File inputHtml = new File("JobPostingData.html");
			myData = myHTMLParser.myHTMLParser(inputHtml);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myDataWriter.writeJobInfo(myData);
		System.out.println("Jobs posting file " + myDataWriter.getFileName() + " generated successfully");
	}
}
