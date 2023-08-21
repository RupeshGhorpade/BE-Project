
import java.util.ArrayList;

import db.DAOQuires;

public class Problem {
String emailid,description,location,status,imagename,problemType;

public String getProblemType() {
	return problemType;
}

public void setProblemType(String problemType) {
	this.problemType = problemType;
}

public String getEmailid() {	
	return emailid;
	
}

public void setEmailid(String emailid) {
	this.emailid = emailid;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getImagename() {
	return imagename;
}

public void setImagename(String imagename) {
	this.imagename = imagename;
}
}
