<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/css/responsive-slider.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/animate.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">	
<title>Insert title here</title>
</head>
<body>
 <header>
<div class="container">
			<div class="row">
				<nav class="navbar navbar-default" role="navigation">
					<div class="container-fluid">
						<div class="navbar-header">
							<div class="navbar-brand">
								<a href="Homepage.jsp"><h1>Jarvis</h1></a>
							</div>
						</div>
						<div class="menu">
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation"><a href="<%=request.getContextPath()%>/JSP/Homepage.jsp">Home</a></li>
								<li role="presentation"><a href="<%=request.getContextPath()%>/JSP/Login.jsp">Login</a></li>
								<li role="presentation"><a href="<%=request.getContextPath()%>/JSP/Registration.jsp">Registration</a></li>
								<li role="presentation"><a href="<%=request.getContextPath()%>/JSP/Forgetpassword.jsp">Forget Password</a></li>
							</ul>
						</div>
					</div>			
				</nav>
			</div>
		</div>
	</header>
</body>
</html>