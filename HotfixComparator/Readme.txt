# HotfixComparator

Hotfix-Comparator - as the name suggests, is a tool that does a full compare on the hot-fix files taken from 2 different environments from the same PRPC version.
No more manual efforts are required for comparing the hotfix scan sheets, just upload them to this tool and you have the results right away. 

## Deployment and usage instructions

1.Download the jar file and .bat file and place them under the same folder structure.
2.Run the .bat file.
3.On the Browser, just hit the : http://localhost:7008
4.Upload the csv files on to the browser.
5.Click the Compare Button.

### Prerequisites

Oracle JDK 1.8


## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The development framework used
* [Maven](https://maven.apache.org/) - Dependency Management and Packaging Mechanism
* [HTML+AJAX+JQuery] - Web-Development frameworks.

## Versioning

We use [Github](https://github.com/) for versioning. For the versions available : Just the First version for now.

##Known issues :
1. There is a fakepath issue that is being encountered while uploading the files using browsers like Chrome, Latest Firefox and Edge (Security issues).
   This works fine in IE 11.
   Planning to fix this in the next version of the project.
2. The port on which this application is going to run is as of now not configurable, it runs on 7008.
   This is also going to be addressed in 2.0 version as a dynamically configurable one.
3. The Application right now doesn't support PRPC version 7.1.8 and below, 7.4 and higher. 

##Planned Enhancements:
1. Export the results to a PDF/Word document.
2. Extend the  tool to 7.X and above PRPC releases.
 
## Authors

Sai Krishna Teja D and Lakshmi Venkatesh K 
