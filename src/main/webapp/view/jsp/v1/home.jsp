<%@ page language="java" contentType="text/html" %>
	<%@ include file="lib/header.jsp" %>
		<!--// MainBanner //-->
	    <div id="mainbanner">
	      <ul class="bxslider">
	        <li><img src="${baseURL}/theme/extraimages/banner1.jpg" alt="" />
	          <div class="kd-caption">
	            <h2>Looking For Best Trip</h2>
	            <h1>We Offer Best Services</h1>
	            <div class="linksection"> <a href="#">Get Deal Now</a></div>
	          </div>
	        </li>
	        <li><img src="${baseURL}/theme/extraimages/banner2.jpg" alt="" />
	          <div class="kd-caption">
	            <h2>We Plan Your Trip</h2>
	            <h1>Best Available Choice in town</h1>
	            <div class="linksection"> <a href="#">Get started now</a></div>
	          </div>
	        </li>
	        <li><img src="${baseURL}/theme/extraimages/banner3.jpg" alt="" />
	          <div class="kd-caption">
	            <h2>Looking For Travel Agent</h2>
	            <h1>We are available 24/7</h1>
	            <div class="linksection"> <a href="#">Ask Now</a></div>
	          </div>
	        </li>
	        <li><img src="${baseURL}/theme/extraimages/banner4.jpg" alt="" />
	          <div class="kd-caption">
	            <h2>Family Trip Planner</h2>
	            <h1>Thinking like a creative</h1>
	            <div class="linksection"> <a href="#">Get started now</a></div>
	          </div>
	        </li>
	      </ul>
	      
	      <div class="kd-tourform" style="display: none">
	        <div class="container">
	          <a href="#" class="formbtn">Find the tour</a>
	          <form>
	            <ul>
	                  <li class="tsDefaultSearchFontStyle">
						<select class="ctCityNames">	
								
						</select>
		           	</li>
		          <li class="tsDefaultSearchFontStyle">
					<div class="input-group input-append date tsDefaultSearchFontStyle" id="datePicker">
						<input type="text" class="form-control" name="date" />
						<span class="input-group-addon add-on"><span class="fa fa-calendar"></span></span>
					</div>
	              </li>
	              <li class="tsDefaultSearchFontStyle">
		               <div class="input-group input-append date tsDefaultSearchFontStyle" id="datePicker">
							<input type="text" class="form-control" name="date" />
							<span class="input-group-addon add-on"><span class="fa fa-calendar"></span></span>
						</div>
	              </li>
	              <li class="tsDefaultSearchFontStyle">
	                 <select class="ctCityNames">
	                  
	                  </select>
	              </li>
	              <li class="tsDefaultSearchFontStyle">
	                 <select class="ctCityNames">
	                  	
	                  </select>
	                </label>
	              </li>
	              <li><input type="submit" class="thbg-color tsHomeSearchBtn" value="search now"></li>
	            </ul>
	          </form>
	        </div>
	      </div>

	    </div>
	    <!--// MainBanner //-->

	<%@ include file="lib/footer.jsp" %>
	<script src="${baseURL}/theme/dashboard/js/jquery.icheck.min.js"></script>
	<script src="${baseURL}/theme/dashboard/js/jquery.validate.js"></script>
	
	<script src="${baseURL}/theme/dashboard/js/jquery.nicescroll.min.js"></script>
	<script src="${baseURL}/theme/dashboard/js/unicorn.js"></script>
	<script src="${baseURL}/theme/dashboard/js/unicorn.form_validation.js"></script>
	<script src="${baseURL}/assest/js/citytravels.js"></script>
	<script type="text/javascript">
		var loginStatus = '${model.responseStatus}';
		ctLoginStatus(loginStatus);
	</script>