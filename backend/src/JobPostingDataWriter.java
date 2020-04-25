import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This java class writes the parsed job posting data into a CSV.
 * 
 * @author Jie (Jocelyn) Bao
 *
 */
public class JobPostingDataWriter {
	private String fileName = "JobPostingData.csv";

	/**
	 * Writes a CSV of job posting information from parsed data.
	 */
	public void writeJobInfo(ArrayList<String> JobPostingData) {
		File out = new File("JobPostingData.csv");

		try (PrintWriter pw = new PrintWriter(out)) {

			// Prints each line
			for (String s : JobPostingData) {
				pw.println(s);
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not write the File out.");
		}
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
}
