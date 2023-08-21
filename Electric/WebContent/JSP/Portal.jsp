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
<style type="text/css">
.responstable {
  margin: 1em 0;
  width: 100%;
  overflow: hidden;
  background: #FFF;
  color: #024457;
  border-radius: 10px;
  border: 1px solid #167F92;
}

.responstable tr {
  border: 1px solid #D9E4E6;
}

.responstable tr:nth-child(odd) {
  background-color: #EAF3F3;
}

.responstable th {
  display: none;
  border: 1px solid #FFF;
  background-color: #167F92;
  color: #FFF;
  padding: 1em;
}

.responstable th:first-child {
  display: table-cell;
  text-align: center;
}

.responstable th:nth-child(2) {
  display: table-cell;
}

.responstable th:nth-child(2) span {
  display: none;
}

.responstable th:nth-child(2):after {
  content: attr(data-th);
}

@media (min-width: 480px) {
  .responstable th:nth-child(2) span {
    display: block;
  }
  .responstable th:nth-child(2):after {
    display: none;
  }
}

.responstable td {
  display: block;
  word-wrap: break-word;
  max-width: 7em;
}

.responstable td:first-child {
  display: table-cell;
  text-align: center;
  border-right: 1px solid #D9E4E6;
}

@media (min-width: 480px) {
  .responstable td {
    border: 1px solid #D9E4E6;
  }
}

.responstable th, .responstable td {
  text-align: left;
  margin: .5em 1em;
}

@media (min-width: 480px) {
  .responstable th, .responstable td {
    display: table-cell;
    padding: 1em;
  }
}

</style>

<title>Insert title here</title>
<%

ArrayList<String> problemList = new DAOQuires().getListApprove(); 

%>
</head>
<body>
<table class="responstable">
  
  <tr>
    <th>Meter no</th>
    <th><span>username</span></th>
    <th>Month</th>
    <th>Filename</th>
    <th>time</th>
    <th>Status</th>
  </tr>
 <%for(int i=0;i<problemList.size();i++) {%>
  <tr>
    <td><%=problemList.get(i).split(",")[0] %></td>
    <td><%=problemList.get(i).split(",")[1]  %></td>
    <td><%=problemList.get(i).split(",")[2]  %></td>
    <td><a href="<%=request.getContextPath()%>/FileDownloader?filename=<%=problemList.get(i).split(",")[3] %>"><%=problemList.get(i).split(",")[3] %></a></td>
    <td><%=problemList.get(i).split(",")[4]  %></td>
    <td>
    
    <%String status = problemList.get(i).split(",")[5]; %>
 
       <form method="post" action="<%=request.getContextPath()%>/RegistrationServlet" method="post" >
    <%
    if(status.trim().equals("Pending"))
    {%>
    <select name="status">
    
    <option>Approve</option>
    
    
    
    </select>	
    <%}else{ %>
    <label> <%=status %> </label>
    <%} if((status.trim().equals("Pending"))){%>
    
    <input type="submit" value="Save">
    <input type="hidden" name="page" value="save">
    <input type="hidden" name="imagename" value="<%=problemList.get(i).split(",")[3]%>">
    <%} %>
    </td>
  </tr>
  </form>
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