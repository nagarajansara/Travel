<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet" href="${baseURL}/assest/css/ctvendorreview.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorProfileContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-8 col-sm-8">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Review Details</h3>
					</div>
					<form action="#" method="post" role="form" class="form-horizontal">
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B">
							</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input style="width: 100%;" type="hidden" name="city"
									id="ctSelectedTripId">
							</div>
						</div>
						<div class="form-group">
							<label
								class="col-lg-3 col-md-3 col-sm-3 control-label ctFontWeight_B">
							</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<a class="custom-btn thbg-color ctGetReviewDetailsSubmitBtn"
									href="javascript:void(0)">Search</a>
							</div>
						</div>
						<div class="form-group"></div>
					</form>
					<div class="row">
						<div class="kd-section-title ctKd_SectionDangerTxt"
							style="display: none">
							<span class="ctDangerDefaultTxt"> No reviews found</span>
						</div>
						<div id="kdcomments" class="ctKdCommentsReview">
							<ul class="ctViewVendorReview">

							</ul>
							<div align="center" class="kd-section-title ctReviewLoadMore">
								<span pk_No="1" class="ctVendorReviewsPageNo"> Load More
								</span>
							</div>
							<div align="center" class="kd-section-title ctReviewLoadMoreImg">
								<img src="${baseURL}/assest/img/loading.gif">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/js/ctvendorreview.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorReviewsMenu').addClass('active');
    var remoteDataConfig = {
	dropdownCssClass : 'bmSelect2Class',
	cache : "true",
	placeholder : "Enter trip title",
	minimumInputLength : 2,
	ajax : {
	    url : '${baseURL}/travelapi/vendor/getAllTripDetails.json',
	    dataType : 'json',
	    data : function(term, page) {
		searchTerm = term.toUpperCase();
		return {
		    startTitle : term,
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

    $("#ctSelectedTripId").select2(remoteDataConfig);

    ctInitVendorReview();
</script>