Execution

-Clone the project from GitHub
>>git clone https://github.com/dsaqp1415g3/GelApp.git

-Go to GelApp/gelapp-api directory
>>cd Gelapp/gelapp-api

-Create the war file using maven
>>mvn package

-Add a new Tomcat entry with a reference to the path where the war file is.
-Restart Tomcat or manually try
>>mvn tomcat run