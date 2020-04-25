import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class BackendTest {
	@Test
	void testHeader() throws FileNotFoundException, IOException {
		HTMLParser myHTMLParser = new HTMLParser();
		File inputHtml = new File("JobPostingData.html");
		String actualResult = myHTMLParser.myHTMLParser(inputHtml).get(0);
		String expectedResult = "Job title, Job description, Company, Primary Location, Required Skills";
		assertEquals(actualResult, expectedResult);

	}

	@Test
	void testContent() throws FileNotFoundException, IOException {
		HTMLParser myHTMLParser = new HTMLParser();
		File inputHtml = new File("JobPostingData.html");
		String actualResult = myHTMLParser.myHTMLParser(inputHtml).get(1);
		String expectedResult = "Senior Data Scientist, Work directly with our C-level team and our software developers to devise plans for turning maritime data (brought in through the Voyager platform and from outside sources) into valuable insights and recommendations for our clients; Source ~  ingest ~  clean ~  and store heterogeneous data sources; Perform preliminary descriptive analyses; Build predictive models; Oversee the deployment ~  monitoring ~  and maintenance of these predictive models on the Voyager platform; Interact cross-functionally ~  making business recommendations (e.g. ~  cost-benefit ~  forecasting ~  experiment analysis) with effective presentations of findings at multiple levels of stakeholders (internal and external) through visual displays of quantitative informatioN; Work with Voyager software team to integrate these models into the web-based portal, Voyager, Miami | FL, Strong Python software engineering skills ~  experience with deployment of machine learning models on AWS ~  familiarity with Jupyter notebooks ~  a strong grasp of statistics ~  and experience with supervised and unsupervised machine learning methods; Knowledge of the shipping industry or commodities ~  experience with ML DevOps tools ~  and experience with logistical optimization problems are a major pluses; English proficiency at least FLUENT level is a necessity.";
		assertEquals(actualResult, expectedResult);

	}

	@Test
	void testGetFileName() throws FileNotFoundException, IOException {
		JobPostingDataWriter myDataWriter = new JobPostingDataWriter();
		String actualResult = myDataWriter.getFileName();
		String expectedResult = "JobPostingData.csv";
		assertEquals(actualResult, expectedResult);

	}

}
