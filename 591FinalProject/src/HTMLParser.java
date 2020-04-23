
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This java class parses the HTML file that contains job data.
 * 
 * @author Jie (Jocelyn) Bao
 *
 */
public class HTMLParser {

	/**
	 * This method parses the HTML file.
	 */
	public ArrayList<String> myHTMLParser() throws FileNotFoundException, IOException {
		ArrayList<String> result = new ArrayList<>();
		File inputHtml = new File("JobPostingData.html");
		Document doc = Jsoup.parse(inputHtml, "UTF-8", "");

		// Strip table from the document
		Element table = doc.select("table[class=JobPostingData]").first();

		// Strip rows from the table
		Elements rows = table.select("tr");

		// Convert table into a 2D array, add delimiter between columns
		String[][] myArray = new String[rows.size()][];
		for (int i = 0; i < rows.size(); i++) {
			Elements columns = rows.get(i).select("td");
			myArray[i] = new String[columns.size()];
			for (int j = 0; j < columns.size(); j++) {
				// Make sure commas originally in the file won't be treated as separators
				myArray[i][j] = columns.get(j).text().replace(", ", " ~ ");
				myArray[i][j] = columns.get(j).text().replace(",", " ~ ");
			}
		}

		// Store 2D array information into ArrayList
		for (int i = 0; i < myArray.length; i++) {
			// Convert each row of string array into a string
			String rowStr = Arrays.deepToString(myArray[i]);
			// Remove left & right brackets
			rowStr = rowStr.replace("[", "");
			rowStr = rowStr.replace("]", "");
			result.add(rowStr);
		}
		return result;
	}

}
