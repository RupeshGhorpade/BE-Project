<%@page import="java.util.ArrayList"%>
<%@page import="db.DAOQuires"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="Stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<%

ArrayList<Problem> problemList = new DAOQuires().getList(); 

%>
</head>
<body>
<table class="responstable">
  
  <tr>
    <th>Problem Location</th>
    <th><span>Description</span></th>
    <th>Email Id</th>
    <th>Problem Type</th>
    <th>Image</th>
    <th>Status</th>
  </tr>
 <%for(int i=0;i<problemList.size();i++) {%>
  <tr>
    <td><input type="button" class="m" value="<%=problemList.get(i).getLocation().replaceAll("Latitude: ","").replaceAll("Longitude: ","")%>"></td>
    <td><%=problemList.get(i).getDescription() %></td>
    <td><%=problemList.get(i).getEmailid() %></td>
    <td><%=problemList.get(i).getProblemType() %></td>
    <td><a href="<%=request.getContextPath()%>/JSP/displayImage.jsp?name=<%=problemList.get(i).getImagename()%>"><%=problemList.get(i).getImagename()%></a></td>
    <td>
    
    <%String status = problemList.get(i).getStatus();%>
        <label> <%=status %> </label>
    
    </td>
  </tr>
  <%}%>
  </table>
  <%@include file="Footer.jsp"%>
  
  	<script
			src="http://maps.google.com/maps/api/js?sensor=false&key=AIzaSyCeq9acroDtMHrtMbYgQpcn2V9Qw9BE8EE"></script>

		<script type="text/javascript">
			jQuery(function($) {
				$(".m").click(function(){
					var loc = $(this).val().split(",");
					var myLocation = new google.maps.LatLng(loc[0],loc[1]);
					var mapOptions = {
						center : myLocation,
						zoom : 16
					};
					var marker = new google.maps.Marker({
						position : myLocation,
						title : "Property Location"
					});
					var map = new google.maps.Map(document
							.getElementById("map1"), mapOptions);
					marker.setMap(map);
				
			
			});
			});
		</script>

		<style>
.map {
	min-width: 300px;
	min-height: 300px;
	width: 100%;
	height: 100%;
}

.header {
	background-color: #F5F5F5;
	color: #36A0FF;
	height: 70px;
	font-size: 27px;
	padding: 10px;
}
</style>
  
  				<div id="map1" class="map"></div>
  
</body>
</html>