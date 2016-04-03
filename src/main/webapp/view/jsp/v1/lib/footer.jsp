<!--// Footer //-->
<footer id="footer-widget"
	class="ctFont_FamilyStyle ctTravelFooterColor">
	<div class="container">
		<div class="row">
			<div class="widget col-md-2 kd-textwidget">
				<div class="kd-widget-title">
					<img alt="" src="/travel/theme/images/logo.png">
				</div>
				<div class="kd-contactinfo"></div>
			</div>
			<div class="widget col-md-2 kd-userinfo-widget">
				<div class="kd-widget-title">
					<h2 class="ctFont_FamilyStyle">Brand Name</h2>
				</div>
				<ul>
					<li><a href="#"> About us </a></li>
					<li><a href="#"> Contact us </a></li>
					<li><a href="#"> Press Room </a></li>
					<li><a href="#"> Careers </a></li>
					<li><a href="#"> Invite friend </a></li>
					<li><a href="#"> What people are saying </a></li>
				</ul>
			</div>
			<div class="widget col-md-2 kd-userinfo-widget">
				<div class="kd-widget-title">
					<h2 class="ctFont_FamilyStyle">Trip Seekers</h2>
				</div>
				<ul>
					<li><a href="#"> My Account </a></li>
					<li><a href="#"> Find a Vendor </a></li>
					<li><a href="#"> Blog </a></li>
					<li><a href="#"> Get a Quote </a></li>
				</ul>
			</div>
			<div class="widget col-md-2 kd-userinfo-widget">
				<div class="kd-widget-title">
					<h2 class="ctFont_FamilyStyle">Trip Provider</h2>
				</div>
				<ul>
					<li><a href="#"> Join us </a></li>
					<li><a href="#"> How it works </a></li>
					<li><a href="#"> Login </a></li>
					<li><a href="#"> What vendor have to say </a></li>
				</ul>
			</div>
			<div class="widget col-md-3 kd-userinfo-widget">
				<div class="kd-widget-title">
					<h2 class="ctFont_FamilyStyle">Contact with us</h2>
				</div>
				<ul>
					<li><a href="#"> Facebook </a></li>
					<li><a href="#"> Twitter </a></li>
					<li><a href="#"> Pinrest </a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="widget col-md-2 kd-textwidget"></div>
			<div class="widget col-md-2 kd-textwidget"></div>
			<div class="widget col-md-3 kd-textwidget">
				<p>© Copyright 2014 All Rights Reserved by KodeForest</p>
			</div>
			<div class="widget col-md-2 kd-textwidget"></div>
		</div>
	</div>
</footer>
<!--// CopyRight //-->
<!-- <div id="copyright">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<p>© Copyright 2014 All Rights Reserved by KodeForest</p>
			</div>
			<div class="col-md-6">
				<nav class="footer-nav">
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="#">Blog</a></li>
						<li><a href="#">Shop</a></li>
						<li><a href="#">ShortCode</a></li>
						<li><a href="#">TypoGraphy</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</div> -->
<!--// CopyRight //-->
<!-- jQuery (Necessary For JavaScript Plugins) -->

<!-- MINIFY JS -->
<script src="${baseURL}/theme/js/jquery.js"></script>
<script src="${baseURL}/theme/js/bootstrap.min.js"></script>
<script src="${baseURL}/theme/plugin/datepicker/tsdatepicker.js"></script>
<script src="${baseURL}/assest/plugin/alert/sweetalert.min.js"></script>
<script src="${baseURL}/assest/plugin/alert/sweetalert-dev.js"></script>
<script src="${baseURL}/assest/plugin/numeric/numeric.min.js"></script>
<%-- <script src="${baseURL}/theme/js/jquery.bxslider.min.js"></script> --%>
<script src="${baseURL}/theme/js/waypoints-min.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<!-- END -->

<%-- <script src="${baseURL}/theme/js/bootstrap-datepicker.js"></script> --%>
<script src="${baseURL}/assest/dao/ctdao.js"></script>
<script src="${baseURL}/assest/util/ctutils.js"></script>

<script>
    var remoteDataConfig = {
	dropdownCssClass : 'bmSelect2Class',
	cache : "true",
	placeholder : "Select your city",
	minimumInputLength : 2,
	ajax : {
	    url : '${baseURL}/travelapi/city/getCityApi.json',
	    dataType : 'json',
	    data : function(term, page) {
		searchTerm = term.toUpperCase();
		return {
		    locationname : term,
		    page_limit : 10
		};
	    },
	    results : function(data, page) {
		return {
		    results : getMockData(data.model)
		};
	    }
	},
	formatResult : function(option) {
	    return "<div>" + option.text + "</div>";
	},
	formatSelection : function(option) {
	    return option.text;
	}
    };
    function getMockData(mockData) {
	mockData = JSON.parse(mockData);
	var foundOptions = [];
	for ( var key in mockData) {
	    if (mockData[key].text.toUpperCase().indexOf(searchTerm) >= 0) {
		foundOptions.push(mockData[key]);
	    }
	}

	return foundOptions;
    };
    $("#ctSelectHeaderCity").select2(remoteDataConfig);

    $(".ctListSearchBtn")
	    .click(
		    function(e) {
			var data = $("#ctSelectHeaderCity").select2('data'), activityId = $(
				'.ctActivityOptionTag option:selected').val();
			activityId = activityId ? (parseInt(activityId) != -1 ? parseInt(activityId)
				: "EMPTY")
				: "EMPTY";

			if (data) {
			    bmpUtil.setLocalStorage("CT_CITY_NAME", data.text);
			    bmpUtil.setLocalStorage("CT_CITY_ID", data.id);
			    requestedFilterParamsJSON = {
				"locationid" : "" + data.id + "",
				"fromdate" : "",
				"activitytype" : "" + activityId + "",
				"toprice" : "100000",
				"fromprice" : "10",
				"startLocation" : "" + data.text + "",
				"subactivitytype" : "EMPTY"
			    };
			    var params = JSON
				    .stringify(requestedFilterParamsJSON);
			    var URL = "http://"
				    + location.host
				    + "/travel/travelapi/trip/getFilterTripsDetailsPageNo/"
				    + params;
			    location.href = URL + "/1";
			}
		    });

    if (bmpUtil.getLocalStorage("CT_CITY_ID")
	    && bmpUtil.getLocalStorage("CT_CITY_NAME")) {
	$("#ctSelectHeaderCity").select2("data", {
	    id : bmpUtil.getLocalStorage("CT_CITY_ID"),
	    text : bmpUtil.getLocalStorage("CT_CITY_NAME")
	});
    }
    var param = {};
    ctDAO
	    .getRestApiActivity(
		    param,
		    function(data) {
			if (data) {
			    var html = '', responseData;

			    for (var i = 0; i < data.length; i++) {
				html += '<option pk="'+ data[i].id +'" value="'+ data[i].id  +'">'
					+ data[i].name + '</option>';
			    }
			    $('.ctHeaderActivity')
				    .html(
					    '<select class="ctActivityOptionTag"><option value="-1">Select Activity</option>'
						    + html + '</select>');
			}
		    });
</script>

</body>