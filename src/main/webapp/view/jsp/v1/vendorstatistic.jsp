<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/css/ctvendorstatistic.css" media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/chart/highchart.css" media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-8 ctExistingTripDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Statistics</h3>
						&nbsp;<a href="#" data-toggle="tooltip" data-placement="right"
							title=""
							data-original-title="Find out how many people viewed your profile"
							class="red-tooltip ctVendorStatisticIcon"><i
							class="fa fa-info-circle"></i></a>
					</div>
					<div
						class="col-md-12 col-sm-12 ctVedorStatisticViewersChartEmpty ctDefaultDisplayNone">
						<span class="ctDangerDefaultTxt"> No data found in viewers
						</span>
					</div>
					<div class="col-md-12 col-sm-12 ctVedorTripViewersStatistic"
						style="height: 400px; min-width: 310px"></div>
					<br> <br> <br>
					<div
						class="col-md-12 col-sm-12 ctVedorStatisticBookingChartEmpty ctDefaultDisplayNone">
						<span class="ctDangerDefaultTxt"> No data found in booking
						</span>
					</div>
					<div class="col-md-12 col-sm-12 ctVendorStatistic"
						style="height: 400px; min-width: 310px"></div>
					<br> <br> <br>
					<div
						class="col-md-12 col-sm-12 ctVedorStatisticEnquiryChartEmpty ctDefaultDisplayNone">
						<span class="ctDangerDefaultTxt"> No data found in enquiry
						</span>
					</div>
					<div class="col-md-12 col-sm-12 ctVendorEnquiryStatistic"
						style="height: 400px; min-width: 310px; margin-top: 20px;"></div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/ctvendorstatistic.js"></script>
<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
<script type="text/javascript">
    $('.ctVendorListMenu li').removeClass('active');
    $('.ctVendorListMenu .ctVendorStatisticsMenu').addClass('active');
    var bookingChart = '${model.responseData.bookingStatistic}', enquiryChart = '${model.responseData.enquiryStatistic}', viewersChart = '${model.responseData.viewersStatistic}';
    if (bookingChart == 'EMPTY') {
	$('.ctVedorStatisticBookingChartEmpty').show();
    } else {
	ctRenderCharts('.ctVendorStatistic', bookingChart, 'BOOKING');
    }

    if (enquiryChart == 'EMPTY') {
	$('.ctVedorStatisticEnquiryChartEmpty').show();
    } else {
	ctRenderCharts('.ctVendorEnquiryStatistic', enquiryChart, 'ENQUIRY');
    }

    if (viewersChart == 'EMPTY') {
	$('.ctVedorStatisticViewersChartEmpty').show();
    } else {
	ctRenderCharts('.ctVedorTripViewersStatistic', viewersChart, 'Viewers');
    }
    $(".ctVendorStatisticIcon").tooltip();
</script>