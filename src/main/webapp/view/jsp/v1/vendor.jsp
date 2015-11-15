<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsdatepicker.css"
	media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" />
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctvendor.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container ctVendorContainer">
			<div class="row">
				<%@ include file="lib/v_leftsidebar.jsp"%>
				<div class="col-md-8 ctExistingTripDetails">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">Listing</h3>
						<div
							class="ctOperationVendorBtn ctAddNewLisiting pull-right frame-btn thbg-color"
							style="cursor: pointer; color: rgb(255, 255, 255) ! important;">
							<a style="color: #ffffff !important;" href="javascript:void(0)">ADD
								New</a>
						</div>
					</div>
					<div class="kd-blog-list kd-blogmedium">
						<div class="row">
							<c:if test="${not empty model.responseData.tipDetails}">
								<c:if test="${not empty model.responseData.tipDetails}">
									<c:forEach items="${model.responseData.tipDetails}"
										var="element" varStatus="loop">
										<article class="col-md-12">
											<div class="bloginner" style="width: 100% !important">
												<figure>
													<a href="javascript:void(0)"><img width="255"
														height="191"
														src="${hostName}:${port}/travelimages/${(element.imageurls)}"
														alt=""></a>
												</figure>
												<section class="kd-bloginfo">
													<h2>
														<a
															href="${baseURL}/travelapi/vendor/editlisting/${(element.id)}"
															class="ctFont_FamilyStyle ctFontWeight_B ctFont_S"><c:out
																value="${(element.title) }" /></a>
													</h2>
													<ul class="kd-postoption">
														<li><a href="#" class="thcolorhover"><c:out
																	value="${(element.activityname) }" /> </a></li>
														<li><time datetime="2008-02-14 20:00">
																|
																<c:out value="${(element.createdat) }" />
															</time></li>
													</ul>
													
													<p>
														<c:out value="${(element.guidelines) }" />
													</p>
													<div class="kd-usernetwork">
														<ul class="kd-blogcomment">
															<li><a pk_ID="${(element.id) }"
																href="javascript:void(0)"
																class="thcolorhover ctTripDetailsDel"><img
																	title="Delete" alt="Delete"
																	src="/travel/theme/images/x.png"></a></li>
															<li><a
																href="${baseURL}/travelapi/vendor/editlisting/${(element.id)}"
																class="thcolorhover"><img title="Edit" alt="Edit"
																	src="/travel/theme/images/edit.png"></a></li>
														</ul>
														<div class="kd-social-network">
															<ul>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-facebook"></i></a></li>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-twitter"></i></a></li>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-tumblr"></i></a></li>
																<li><a href="#" class="thcolorhover"><i
																		class="fa fa-google-plus"></i></a></li>
															</ul>
														</div>
													</div>
												</section>
											</div>
										</article>
									</c:forEach>
								</c:if>
							</c:if>
							<c:if test="${model.responseData.numEntries eq 0}">
								<article class="col-md-12">
									<div align="center" style="color: red; cursor: pointer"
										class="ctListingEmpty">
										<span style="font-family: verdana; font-weight: bold">No
											data found</span>
									</div>
								</article>
							</c:if>
						</div>
					</div>
					<div class="pagination-wrap ctPaginationDiv" style="display: none;">
						<ul id="pagination-demo" class="pagination-sm"></ul>
					</div>
				</div>
				<div class="col-md-9 col-sm-9 ctAddListingDetails"
					style="display: none">
					<div class="kd-section-title">
						<h3 class="ctFont_FamilyStyle">ADD Listing</h3>
						<div
							class="ctOperationVendorBtn ctShowLisiting pull-right frame-btn thbg-color"
							style="cursor: pointer; color: rgb(255, 255, 255) ! important;">
							<a style="color: #ffffff !important;" href="javascript:void(0)">Show
								Listing</a>
						</div>
					</div>
					<form onsubmit="return ctInsertTripData();"
						enctype="multipart/form-data"
						action="${baseURL}/travelapi/trip/addTripDetails" method="post"
						role="form" class="form-horizontal ctVendorListingForm">
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Activity
								Type</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<c:if test="${not empty model.responseData.activityList}">
									<select name="activitytype" class="form-control ctVendorActivitySelectOPtion">
										<option value="">Select Activity</option>
										<c:forEach items="${model.responseData.activityList}"
											var="element" varStatus="loop">
											<option value="${(element.id)}"><c:out
													value="${(element.name) }" /></option>
										</c:forEach>
									</select>
								</c:if>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Location
								of Activity</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input style="width: 100%;" type="hidden" name="city"
									id="ctSelectActivityType">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">From
								Date</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input type="text" name="fromdate"
									class="bmQuoteCommonFontStyle" name="date" id="fromDatePicker" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-sm-3 col-md-3 control-label">To
								Date</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input type="text" class="bmQuoteCommonFontStyle" name="todate"
									id="toDatePicker" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-sm-3 col-md-3 control-label">Start
								Point</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input style="width: 100%;" name="startpoint" type="hidden"
									id="ctSelectCity">
							</div>
						</div>
						<div class="form-group ctRouteFormDiv ctRouteFormDivLen1">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Route</label>
							<div class="col-lg-4 col-md-4 col-sm-4 ctRouteDiv">
								<input name="route1" type="text" name="todate"
									class="ctRouteTxt ctRouteTxtLen form-control ctIsChkEmptyVal">
							</div>
							<div class="col-lg-1 col-md-1 ctRouteOperation">
								<button class="btn btn-primary ctAddIcon" type="button">
									<i class="fa fa-plus"></i>
								</button>
							</div>
							<div class="col-lg-2 col-md-2 ctRouteOperation">
								<button class="btn btn-danger ctMinusIcon" type="button">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="form-group ctDurationTxt">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Duration</label>
							<div class="col-lg-1 col-md-1">
								<input name="totalduration" type="text"
									class="form-control ctNoDurationTxt ctIsChkEmptyVal">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Description</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<textarea name="description" placeholder="Description" rows="3"
									class="ctIsChkEmptyVal form-control ctTripDescriptionTxt"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Guidelines</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<textarea name="guideline" placeholder="Guidelines" rows="3"
									class="ctIsChkEmptyVal form-control ctGuideLinesTxt"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Price</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<input value="${price}" name="price" type="text"
									class="form-control ctPriceTxt ctIsChkEmptyVal">
							</div>
						</div>
						<div
							class="form-group ctPhotogalleryParentDiv ctPhotogalleryParentLen1">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label">Photogallery</label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<label class="custom-file-input"> <input
									class="ctPhotogalleryFileName" name="file" type="file">
								</label>
							</div>
							<div class="col-lg-1 col-md-1">
								<button class="btn btn-primary ctAddIconPhotoGallery"
									title="addMoreFiles" type="button">
									<i class="fa fa-plus"></i>
								</button>
							</div>
							<div class="col-lg-2 col-md-2">
								<button class="btn btn-danger ctMinusIconPhotoGallery"
									title="removeFiles" type="button">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>
						<div class="form-group"></div>
						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label"></label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<button class="btn btn-primary" type="submit">Submit</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/assest/js/citytravels.js"></script>
<script src="${baseURL}/assest/js/ctconsumer.js"></script>
<script src="${baseURL}/assest/js/ctvendor.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/plugin/datepicker/tsdatepicker.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script src="${baseURL}/assest/plugin/pagination/renderpagination.js"></script>
<script type="text/javascript">
	$('.ctVendorListMenu li').removeClass('active');
	$('.ctVendorListMenu .ctVendorListingMenu').addClass('active');
	$('.ctPriceTxt').numeric();
	var searchTerm = null, numEntries = '${model.responseData.numEntries}', MAX_ENTRIES =
			10, START_PAGE = 1;
	var remoteDataConfig =
	{
		dropdownCssClass : 'bmSelect2Class',
		cache : "true",
		placeholder : "Enter your pickup pincode",
		minimumInputLength : 2,
		ajax :
		{
			url : '${baseURL}/travelapi/city/getCityApi.json',
			dataType : 'json',
			data : function(term, page) {
				searchTerm = term.toUpperCase();
				return{
					locationname : term,
					page_limit : 10
				};
			},
			results : function(data, page) {
				return{
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
		for ( var key in mockData)
		{
			if (mockData[key].text.toUpperCase().indexOf(searchTerm) >= 0)
			{
				foundOptions.push(mockData[key]);
			}
		}

		return foundOptions;
	};

	$("#ctSelectActivityType").select2(remoteDataConfig);
	$("#ctSelectCity").select2(remoteDataConfig);

	if (numEntries && numEntries > ctDAO.TOTAL_RECORDS_PER_PAGE)
	{
		var lastPart = bmpUtil.getLastStartingURL(), URL =
				"http://" + location.host + "/travel/travelapi/vendor/listing";
		lastPart = parseInt(lastPart);
		if (lastPart && typeof lastPart === 'number')
		{
			START_PAGE = lastPart;
		}

		ctSetPagination(numEntries, MAX_ENTRIES, 5, START_PAGE, URL);
	}
</script>

