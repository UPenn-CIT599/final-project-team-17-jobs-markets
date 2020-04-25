# Job Markets  - CIT 591 Team 17 Final Project

# [Overview]

Check out ['Project Summary'](https://github.com/UPenn-CIT599/final-project-team-17-jobs-markets/blob/master/summary.txt) to get an overview of this project. 

This application includes three syb-system. This doc provides instruction on how to run each of the sub-system. 

All source codes can be pulled from here: ['Job Markets Github Repo'](https://github.com/UPenn-CIT599/final-project-team-17-jobs-markets)

 
# [Backend] - Bao Jie
 
The backend portion of this application contains three Java files and one test file.

Follow the below steps to generate the `JobPostingData.csv` file that can be later used for the API Service.

Step 1. Go to https://github.com/UPenn-CIT599/final-project-team-17-jobs-markets/tree/master/backend folder.

Step 2. Open Eclipse IDE, create a new project called "Backend"

Step 3. In the Step 1 link, copy and paste all files in the "src" folder to the "src" folder in the "Backend" project. Copy and paste "JobPostingData.html" directly under "Backend" project.

Step 4. Download all files ending with ".jar" in the "lib" folder of the Step 1 link to a local directory.

Step 5. In Eclipse, right click "Backend" -> Build Path -> Configure Build Path. In the "Library" tab, click "Add External JARs", and import all the ".jar" files downloaded in Step 4. Hit "Apply and Close."

Step 6. Open the "JobPostingDataSourcing.java" file, hit run. 

Step 7. Right click "Backend" -> Refresh, there should be a newly-generated csv called "JobPostingData.csv"

# [API Service] - Zhong Liu

API Service read data from the `JobPostingData.csv` file generated by the backend system above, provides a set of RESTFul APIs over HTTP(s) for client side application to use. 

More information about the API service, check out ['Project Summary'](https://github.com/UPenn-CIT599/final-project-team-17-jobs-markets/blob/master/summary.txt) and ['Technical Design'](https://github.com/UPenn-CIT599/final-project-team-17-jobs-markets/blob/master/Job%20Markets%20Trend%20Application%20Technical%20Design.pdf). 

Installation

Following installation guidance is for `Mac OS user`. You can google guidance on how to install required tools & libraries in other operating systems, which should be similar.  Note that you might need to `root` user permission to install tools. 

Step 1: Preparation. The API Service is designed & implemented in a way that can be easily built & deployed onto AWS. There are a couple of tools that you need to install before you can deploy & run it. 

1.1. We use `Maven` to build API service. ['How to Install Maven'](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

1.2. Install `Brew` so that you can easily install other tools required to simulate AWS env in local machine and to deploy the application onto AWS.  ['How to install Brew'](https://osxdaily.com/2018/03/07/how-install-homebrew-mac-os/)

1.3. Install `Docker` which is required to simulate AWS env in local.

```bash
$ brew cask install docker
```


or to follow ['docker for mac'](https://docs.docker.com/docker-for-mac/)

['Reference on potential issue'](https://stackoverflow.com/questions/44084846/cannot-connect-to-the-docker-daemon-on-macos)


1.4. Install `aws-sam-local` to run AWS application locally. 

```bash
$ brew install npm
$ npm install -g aws-sam-local
```

1.5. To make sure  `JAVA 8` or above version is installed, to have `JAVA_HOME`, `PATH` properly configured. 

E.g. 

```bash
$ export `JAVA_HOME`=/Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home
$ export `PATH`=/Users/zhongliu/apache-maven-3.6.3/bin:/Users/zhongliu/apache-maven-3.6.3/bin/:$PATH:$JAVA_HOME/bin 
```


Step 2: Build API Service

2.1. Make sure are you in the root of project folder (the root folder of where you put code pulled from https://github.com/UPenn-CIT599/final-project-team-17-jobs-markets), I call this as `JOBS_HOME`. E.g.

```bash 
$ cd $JOBS_HOME 
```


[Optional] copy `JobPostingData.csv` from `JOBS_HOME/backend/` folder to `JOBS_HOME/src/main/resources/` folder. Before doing this, please make sure there is job data inside the `JobPostingData.csv` file. 
 
 
2.2. Build API Service using `Maven`, all 18 test cases will be ran automatically during this p rocess.
 
```bash
$ cd $JOBS_HOME
$ mvn archetype:generate -DartifactId=jobmarkets -DarchetypeGroupId=com.amazonaws.serverless.archetypes -DarchetypeArtifactId=aws-serverless-jersey-archetype -DarchetypeVersion=1.4 -DgroupId=com.upenn.cit591 -Dversion=0.0.1-SNAPSHOT -Dinteractive=false

Note: you can try to skip above mvn archetype:generate command if error occurs.
 
$ mvn clean package

[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.upenn.cit591:jobmarkets >---------------------
[INFO] Building Serverless Jersey API 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.upenn.cit591.jobmarkets.JobTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.035 sec
Running com.upenn.cit591.jobmarkets.JobsTest
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec
Running com.upenn.cit591.jobmarkets.StreamLambdaHandlerTest
SLF4J: No SLF4J providers were found.
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#noProviders for further details.
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.906 sec
Running com.upenn.cit591.jobmarkets.libs.JobsCSVHelperTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec
Running com.upenn.cit591.jobmarkets.libs.WordPairTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec

Results :

Tests run: 18, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 6.546 s
[INFO] Finished at: 2018-02-15T08:39:33-08:00
[INFO] Final Memory: XXM/XXXM
[INFO] ------------------------------------------------------------------------
```

2.3. Start API Service

```bash
$ sam local start-api --template sam.yaml
...
Mounting com.amazonaws.serverless.archetypes.StreamLambdaHandler::handleRequest (java8) at http://127.0.0.1:3000/{proxy+} [OPTIONS GET HEAD POST PUT DELETE PATCH]
...

```
2.4. Test API

Using a new shell, you can send a test ping request to your API:

```bash
$ curl -s http://127.0.0.1:3000/jobs/states | python -m json.tool

{
    "AL": 1,
    "AZ": 1,
    "CA": 8,
    "FL": 3,
    "MA": 3,
    "MD": 6,
    "ME": 1,
    "MI": 1,
    "MN": 1,
    "NC": 2,
    "NY": 10,
    "OH": 1,
    "PA": 3,
    "TX": 3,
    "VA": 3,
    "WA": 1
}
 
```

You can also user browser (FIREFOX is recommended) to access the APIs. 


2.5. Congratulations! You now have API service built & running successfully on your local machine simulating AWS. 

Following is a set of API you can access. Check [API Service](https://github.com/UPenn-CIT599/final-project-team-17-jobs-markets/blob/master/Job%20Markets%20Trend%20Application%20Technical%20Design.pdf) part of our technical design for more information if needed. 

Jobs By States: http://127.0.0.1:3000/jobs/states

Jobs By Companies: http://127.0.0.1:3000/jobs/companies

Jobs By Titles: http://127.0.0.1:3000/jobs/titles

All jobs: http://127.0.0.1:3000/jobs/all

Job query by state: http://127.0.0.1:3000/jobs/query/state/NY  , NY can be replaced by other state name, e.g. FL. 


Note that All APIs have been deployed onto AWS with Zhong's account. 

You can access those APIs on AWS by replacing `http://127.0.0.1:3000` to `https://yrdltjhgh7.execute-api.us-east-1.amazonaws.com/Prod`. E.g. 

https://yrdltjhgh7.execute-api.us-east-1.amazonaws.com/Prod/jobs/query/state/NY 


# [Frontend] - Terry Zhang

```
The Frontend is deveoped using JavaScript for displaying the job market data, consisting of four graphs. 
1. An interactive map to show how many job openings in each state from our scraped websites.  
2. A word-cloud graph, The word cloud is comprised of the buzz words mentioned in the job descriptions with different font size to indicate the frequency.
3. A pie chart, The pie chart is the minimum degree and experience requirement of the job qualification.
4. A bar chart. The Bar chart is organized by each company type to show statistics of fields that have the most opening jobs for software engineering. 
```

Language and Libraries used in the WebApplication:
```
1. HTML allows us to specify the structure of Web content
2. CSS is a formatting language used to describe the appearance of content in an HTML file
3. Bootstrap: open source front-end development framework produced and maintained by Twitter that aids in producing clean, responsive web pages and applications. 
4. JavaScripts:  Dynamically change Web Content
5. D3: Data Driven Document
6. SVG: Scalable Vector Graphics
```
WebApp Code Architecture 
```bash
|-- WebApp 
    |-- index.html 
    
    |-- api_discription.doc
    
    |-- js
    
        `-- barChart.js 
        
        `-- jquery.min.js
        
        `-- pieChart.js
        
        `-- uStates.js
        
        `-- wordCount.js
        
    |-- css
    
        `-- viz.css     
```


