<%@ page language="java" contentType="text/html"%>
<%@ include file="lib/header.jsp"%>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsdatepicker.css"
	media="screen" />
<%-- <link rel="stylesheet"
	href="${baseURL}/assest/plugin/datepicker/tsstyle.css" media="screen" /> --%>
<link rel="stylesheet"
	href="${baseURL}/assest/plugin/selecttwo/select_min.css" media="screen" />
<link rel="stylesheet" href="${baseURL}/assest/css/ctvendor.css"
	media="screen" />
<div class="kd-content">
	<section style="background: #ffffff; padding: 40px 0px;"
		class="pagesection ctPagesection">
		<div class="container">
			<div class="row">
				<div class="col-md-7">
					<div class="kd-section-title">
						<h2 class="ctFont_FamilyStyle">Edit Listing</h2>
					</div>
					<c:if test="${not empty model.responseData.trip}">
						<c:forEach items="${model.responseData.trip}" var="element"
							varStatus="loop">
							<c:set var="tripId" value="${element.id}" />
							<c:set var="todate" value="${element.todate}" />
							<c:set var="fromdate" value="${element.fromdate}" />
							<c:set var="description" value="${element.description}" />
							<c:set var="guidelines" value="${element.guidelines}" />
							<c:set var="fromcity" value="${element.fromcity}" />
							<c:set var="tocity" value="${element.tocity}" />
							<c:set var="route" value="${element.route}" />
							<c:set var="duration" value="${element.duration}" />
							<c:set var="locationid" value="${element.locationid}" />
							<c:set var="startpoint" value="${element.startpoint}" />
							<c:set var="activityname" value="${element.activityname}" />
							<c:set var="subactivityname" value="${element.subactivityname}" />
							<c:set var="price" value="${element.price}" />
						</c:forEach>
					</c:if>
					<c:if test="${not empty model.responseData.trip}">
						<form onsubmit="return ctEditListingData();"
							action="${baseURL}/travelapi/vendor/updatelisting/${tripId}"
							method="POST" role="form"
							class="form-horizontal ctFontWeight_B ctEditVendorListingForm">
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Activity
									Type</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<select name="activitytype"
										class="form-control ctEditSelectActivityType">
										<c:if test="${not empty model.responseData.activityList}">
											<c:forEach items="${model.responseData.activityList}"
												var="element" varStatus="loop">
												<option
													<c:if test="${activityname eq element.name}"><c:out value="selected" /></c:if>
													value="${(element.id)}"><c:out
														value="${(element.name) }" /></option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">SubActivity
									Type</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<select name="subactivitytype"
										class="form-control ctEditSelectActivityType">
										<c:if test="${not empty model.responseData.subActivityList}">
											<c:forEach items="${model.responseData.subActivityList}"
												var="element" varStatus="loop">
												<option
													<c:if test="${subactivityname eq element.name}"><c:out value="selected" /></c:if>
													value="${(element.id)}"><c:out
														value="${(element.name) }" /></option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Location
									of Activity</label>
								<div class="col-lg-4 col-md-4  col-sm-4">
									<input data-option="${tocity}" style="width: 100%;"
										value="${startpoint}" type="hidden" name="city"
										id="ctEditSelectActivityLocation">
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">From
									Date</label>
								<div class="col-lg-4 col-md-4  col-sm-4">
									<input type="text" value="${fromdate}" name="fromdate"
										class="bmQuoteCommonFontStyle" name="date"
										id="ctEditfromDatePicker" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">To
									Date</label>
								<div class="col-lg-4 col-md-4  col-sm-4">
									<input value="${todate}" type="text"
										class="bmQuoteCommonFontStyle" name="todate"
										id="ctEdittoDatePicker" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Start
									Point</label>
								<div class="col-lg-4 col-md-4  col-sm-4">
									<input style="width: 100%;" data-option="${fromcity}"
										value="${locationid}" name="startpoint" type="hidden"
										id="ctEditStartPointCity">
								</div>
							</div>
							<div class="form-group ctRouteFormDiv ctRouteFormDivLen1">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Route</label>
								<div class="col-lg-4 col-md-4  col-sm-4 ctEditRouteDiv">
									<input value="${route}" name="route1" type="text" name="todate"
										class="form-control ctIsChkEmptyVal"> <span
										style="font-family: Junction; font-size: 10px; color: #c64837;">Using
										same route format like (X->Y)</span>
								</div>
							</div>
							<div class="form-group ctEditDurationTxt">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Duration</label>
								<div class="col-lg-1 col-md-1">
									<input value="${duration}" name="totalduration" type="text"
										class="form-control ctEditNoDurationTxt ctIsChkEmptyVal">
								</div>
							</div>
							<c:if test="${not empty model.responseData.trip}">
								<c:forEach items="${model.responseData.trip}" var="elements"
									varStatus="loops">
									<div
										class="form-group ctEditItenaryParentDiv ctItenaryLen${loops.index + 1}"
										style="position: relative">
										<label class="col-lg-3 col-md-3 col-sm-3 control-label"></label>
										<div class="col-lg-6 col-md-6 col-sm-6">
											<img class="ctEditItenaryCloseBtn"
												style="cursor: pointer; position: absolute; right: 0px;"
												alt="" src="${baseURL}/theme/images/x.png">
											<textarea name="duration${loops.index + 1}"
												placeholder="Enter your Plan for day" rows="3"
												class="ctIsChkEmptyVal form-control ctItenaryTxtArea"><c:out
													value="${elements.daysdesc}" /></textarea>
										</div>
									</div>
								</c:forEach>
							</c:if>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Description</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<textarea name="description" placeholder="Description" rows="3"
										class="ctIsChkEmptyVal form-control ctEditTripDescriptionTxt"><c:out
											value="${description}" /></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Guidelines</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<textarea name="guideline" placeholder="Guidelines" rows="3"
										class="ctIsChkEmptyVal form-control ctEditGuideLinesTxt"><c:out
											value="${guidelines}" /></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label">Price</label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<input value="${price}" name="price" type="text"
										class="form-control ctPriceTxt ctIsChkEmptyVal">

								</div>
							</div>
							<div class="form-group"></div>
							<div class="form-group">
								<label class="col-lg-3 col-md-3 col-sm-3 control-label"></label>
								<div class="col-lg-4 col-md-4 col-sm-4">
									<button class="btn btn-primary" type="submit">Update</button>
								</div>
							</div>
						</form>
					</c:if>
				</div>
				<div class="col-md-5">
					<div class="kd-section-title">
						<h2 class="ctFont_FamilyStyle">Uploaded Imgae</h2>
					</div>
					<c:if test="${not empty model.responseData.tripImagesList}">
						<table class="kd-table kd-tabletwo"
							style="border-top: 1px; border-bottom: 1px solid; border-right: 1px solid; border-left: 1px solid;">
							<thead>
							</thead>
							<tbody>
								<c:forEach items="${model.responseData.tripImagesList}"
									var="elements" varStatus="loops">
									<tr>
										<td style="line-height: 5.5; height: 156px;"><img
											style="width: 50%; height: 100%"
											src="${request.serverName}/${uploadedImageFolderName}/${elements.name}">
										</td>
										<td style="line-height: 5.5; width: 20%; height: 20%;"><img
											style="cursor: pointer;" class="ctEditTripImageDel"
											pk_tripID="${tripId}" pk_ID="${elements.id}"
											src="/travel/theme/images/x.png" title="Delete Image" alt="">
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.tripImagesList}">
						<span style="color: red; font-family: Junction; font-weight: bold">No
							existing image found</span>
					</c:if>
					</br> </br> </br> </br> </br> </br>
					<%-- <c:if test="${not empty model.responseData.tripImagesList}"> --%>
					<form enctype="multipart/form-data"
						onsubmit="return ctEditImageListingData();"
						action="${baseURL}/travelapi/vendor/updatelistingimage/${tripId}"
						method="POST" role="form"
						class="form-horizontal ctFontWeight_B ctEditVendorListingImgForm">
						<div
							class="form-group ctEditPhotogalleryParentDiv ctEditPhotogalleryParentLen1">
							<div class="kd-section-title">
								<h2 class="ctFont_FamilyStyle">Upload New Image</h2>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-5">
								<label class=""> <input class="ctEditPhotogalleryFile"
									type="file" name="file">
								</label>
							</div>
							<div class="col-lg-1 col-md-1">
								<button type="button" title="addMoreFiles"
									style="font-size: 8px;"
									class="btn btn-primary ctEditAddIconPhotoGallery">
									<i class="fa fa-plus" style=""></i>
								</button>
							</div>
							<div class="col-lg-1 col-md-1">
								<button type="button" style="font-size: 8px;"
									title="removeFiles"
									class="btn btn-danger ctEditMinusIconPhotoGallery">
									<i class="fa fa-minus"></i>
								</button>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-3 col-md-3 col-sm-3 control-label"></label>
							<div class="col-lg-4 col-md-4 col-sm-4">
								<button class="btn btn-primary" type="submit">Upload</button>
							</div>
						</div>
					</form>
					<div class="kd-section-title">
						<h2 class="ctFont_FamilyStyle">Choose Cover Imgae</h2>
					</div>
					<c:if test="${not empty model.responseData.tripImagesList}">
						<table class="kd-table kd-tabletwo"
							style="border-top: 1px; border-bottom: 1px solid; border-right: 1px solid; border-left: 1px solid;">
							<thead>
							</thead>
							<tbody>
								<c:forEach items="${model.responseData.tripImagesList}"
									var="elements" varStatus="loops">
									<tr>
										<td style="line-height: 5.5; height: 156px;"><img
											style="width: 50%; height: 50%"
											src="${request.serverName}/${uploadedImageFolderName}/${elements.name}">
										</td>
										<c:choose>
											<c:when test="${elements.imagetype eq 'coverimage'}">
												<td style="line-height: 5.5; width: 20%; height: 20%;"><input
													type="radio" class="ctSelectCoverImg" checked="checked"
													name="coverImg" pk_ID="${elements.id}"
													pk_tripID="${elements.tripid}"></td>
											</c:when>
											<c:otherwise>
												<td style="line-height: 5.5; width: 20%; height: 20%;"><input
													type="radio" class="ctSelectCoverImg" name="coverImg"
													pk_ID="${elements.id}" pk_tripID="${elements.tripid}"></td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${empty model.responseData.tripImagesList}">
						<span style="color: red; font-family: Junction; font-weight: bold">No
							existing image found</span>
					</c:if>
				</div>
				<c:if test="${empty model.responseData.trip}">
					<div class="row">
						<div class="col-md-8 col-md-8">
							<div class="">
								<h2 class="ctFont_FamilyStyle" style="color: red">No data
									found</h2>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp"%>
<script src="${baseURL}/theme/dashboard/js/jquery.icheck.min.js"></script>
<script src="${baseURL}/theme/dashboard/js/jquery.validate.js"></script>

<script src="${baseURL}/theme/dashboard/js/jquery.nicescroll.min.js"></script>
<script src="${baseURL}/assest/js/citytravels.js"></script>
<script src="${baseURL}/assest/js/cteditlisting.js"></script>
<script src="${baseURL}/assest/plugin/selecttwo/select_min.js"></script>
<script src="${baseURL}/assest/plugin/datepicker/tsdatepicker.js"></script>
<script src="${baseURL}/assest/plugin/pagination/twbsPagination.js"></script>
<script type="text/javascript">
    $(document)
	    .ready(
		    function() {
			ctUpdateTripImgType();
			var searchTerm = null, numEntries = '${model.responseData.numEntries}', MAX_ENTRIES = 10, START_PAGE = 1;
			$('.ctPriceTxt').numeric();
			var remoteDataConfig = {
			    dropdownCssClass : 'bmSelect2Class',
			    triggerChange : true,
			    cache : true,
			    placeholder : "Select your city",
			    minimumInputLength : 1,
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
			    },
			    initSelection : function(element, callback) {
				var id = element.val();
				var text = element.data('option');
				var data = {
				    id : id,
				    text : text
				};
				callback(data);
			    },
			    escapeMarkup : function(m) {
				return m;
			    }
			};

			function getMockData(mockData) {
			    mockData = JSON.parse(mockData);
			    var foundOptions = [];
			    for ( var key in mockData) {
				if (mockData[key].text.toUpperCase().indexOf(
					searchTerm) >= 0) {
				    foundOptions.push(mockData[key]);
				}
			    }
			    return foundOptions;
			}
			;

			$("#ctEditSelectActivityLocation").select2(
				remoteDataConfig)
			$('#ctEditSelectActivityLocation').select2('val',
				'${startpoint}');
			$('#ctEditStartPointCity').select2(remoteDataConfig);
			$('#ctEditStartPointCity').select2('val',
				'${locationid}');

			$(".ctEditTripImageDel")
				.click(
					function() {
					    var param = {
						"tripId" : $(this).attr(
							'pk_tripID'),
						"id" : $(this).attr('pk_ID')
					    }, tObj = this;
					    swal(
						    {
							title : "Are you sure?",
							text : "You will not be able to recover this imaginary file!",
							type : "warning",
							showCancelButton : true,
							confirmButtonColor : "#DD6B55",
							confirmButtonText : "Yes, delete it!",
							cancelButtonText : "No, cancel plx!",
							closeOnConfirm : false,
							closeOnCancel : false
						    },
						    function(isConfirm) {
							if (isConfirm) {
							    ctDAO
								    .getDeleteTripImage(
									    param,
									    function(
										    responseDatas) {
										if (responseDatas.responseStatus == 200) {
										    swal(
											    "Deleted!",
											    "Your imaginary file has been deleted.",
											    "success");
										    $(
											    tObj)
											    .parent()
											    .parent()
											    .remove();
										}
									    });
							} else {
							    swal(
								    "Cancelled",
								    "Your imaginary file is safe :)",
								    "error");
							}
						    });

					});

		    });
    function ctChkEditFileUploaded() {
	var isUploaded = true;
	$('.ctPhotogalleryFileName').each(function() {
	    if (!$(this).val()) {
		isUploaded = false;
		return isUploaded;
	    }
	});
	return isUploaded;
    };
    function ctEditListingData() {
	var isNotEmpty = true, editDurationValues = $('.ctEditNoDurationTxt')
		.val();
	if (bmpUtil.isTextFieldEmpty('.ctIsChkEmptyVal') && editDurationValues
		&& parseInt(editDurationValues) > 0) {
	    var ctRouteFormLen = $('.ctRouteFormDiv').length, ctDurationLen = $('.ctEditItenaryParentDiv').length, convertDateENG = bmpUtil
		    .convertDateENG($("#ctEditfromDatePicker").val()), location = $(
		    "#ctEditSelectActivityLocation").select2('data').text, tripTitle = $(
		    '.ctEditSelectActivityType option:selected').text()
		    + ", "
		    + location
		    + " - "
		    + convertDateENG["month"]
		    + " & "
		    + convertDateENG["year"];
	    $('.ctEditVendorListingForm')
		    .append(
			    '<input type="hidden" class="ctDurationHidden" value="'+ ctDurationLen +'" name="durationcount">');
	    $('.ctEditVendorListingForm')
		    .append(
			    '<input type="hidden" class="ctDurationHidden" value="'+ ctDurationLen +'" name="durationcount">');
	    $('.ctEditVendorListingForm')
		    .append(
			    '<input type="hidden" class="ctTipTitle" value="'+ tripTitle +'" name="edittriptitle">');
	} else {
	    isNotEmpty = false;
	    sweetAlert("Oops...", "Please enter all values!", "error");
	}
	return isNotEmpty;
    };
    function ctEditImageListingData() {
	var isNotEmpty = true;
	$('.ctEditPhotogalleryFile').each(function() {
	    var tempFile = $(this).val();
	    if (tempFile.length == 0) {
		isNotEmpty = false;
	    }
	});
	if (!isNotEmpty) {
	    sweetAlert("Oops...", "Please enter all values!", "error");
	}
	return isNotEmpty;
    };
</script>
