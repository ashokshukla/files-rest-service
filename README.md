# Directory and files can be configured in application.properties file.
PRE-REQUISITES - Copy all provided XSL and CSV file under configured directory path Ex: "C:\\Temp\\Input".
 
- Go to project directory
 
- To clean and compile the build
mvn clean install
 
- To launch service:  java -jar target/files-rest-service-1.0.0.jar
 
- Get list of all files: http://localhost:8085/identity/files
 
- Get list of files with configured extensions: http://localhost:8085/identity/files/configFiles