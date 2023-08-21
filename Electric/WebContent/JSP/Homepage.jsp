<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="slider">
	<div class="container">
		<div class="row">
			<div class="responsive-slider" data-spy="responsive-slider" data-autoplay="true">
				<div class="slides" data-group="slides">
					<ul>
						<li>
							<div class="slide-body" data-group="slide">
								<img src="../img/2a.jpg" alt="">
								<div class="caption header" data-animate="slideAppearUpToDown" data-delay="500" data-length="300">
									<button class="btn btn-primary"><h2>Voice Command Jarvis</h2></button>
									<div class="caption-sub" data-animate="slideAppearDownToUp" data-delay="1200" data-length="300"><button class="btn btn-primary"><h4><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit sit amet.</span></h4></button></div>
									<div class="caption-sub" data-animate="slideAppearLeftToRight" data-delay="900" data-length="300"><button class="btn btn-primary"><h3>With one to one swipe movement!</h3></button></div>
								</div>
							</div>
						</li>
						<li>
							<div class="slide-body" data-group="slide">
								<img src="../img/1.jpg" alt="">
								<div class="caption header" data-animate="slideAppearDownToUp" data-delay="500" data-length="300">
									<button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off"><h2>creative design Responsive slider</h2></button>
									<div class="caption-sub" data-animate="slideAppearUpToDown" data-delay="800" data-length="300"><button class="btn btn-primary"><h4><span>Lorem ipsum dolor sit amet, consectetur adipisicing elit sit amet. </span></h4></button></div>
									<div class="caption-sub" data-animate="slideAppearRightToLeft" data-delay="1200" data-length="300"><button class="btn btn-primary"><h3>Clean and Flat</h3></button></div>
								</div>
							</div>
						</li>
						<li>
							<div class="slide-body" data-group="slide">
								<img src="../img/2.jpg" alt="">
								<div class="caption header" data-animate="slideAppearUpToDown" data-delay="500" data-length="300">
								  <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false" autocomplete="off"><h2>creative design Custom animations</h2></button>
								  <div class="caption-sub" data-animate="slideAppearLeftToRight" data-delay="800" data-length="300"><button class="btn btn-primary"><h4>Lorem ipsum dolor sit amet, consectetur adipisicing elit sit amet.</h4></button></div>
								  <div class="caption-sub" data-animate="slideAppearDownToUp" data-delay="1200" data-length="300"><button class="btn btn-primary"><h3><span>New style Slides!</span></h3></button></div>
						
								</div>
							</div>
						</li>
				
					</ul>
				</div>
		   
				<a class="slider-control left" href="#" data-jump="prev"><i class="fa fa-angle-left fa-2x"></i></a>
				<a class="slider-control right" href="#" data-jump="next"><i class="fa fa-angle-right fa-2x"></i></a>		
			</div>
		</div>
	</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
	<script src="../js/responsive-slider.js"></script>
	<script src="../js/wow.min.js"></script>
	<script>
	wow = new WOW(
	 {
	
		}	) 
		.init();
	</script>
</body>
</html>