var MIN_TRIP_DAYS = 1;
$(document)
	.ready(
		function() {
		    ctAddActivityEvent();
		    ctAddSubActivity();
		    $('.ctTripDetailsDel')
			    .click(
				    function() {
					var param = {
					    "tripId" : $(this).attr('pk_ID'),
					    "status" : 'deactive'
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
								.getTripDetailsUpdateStatus(
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
											.closest(
												'.col-md-12')
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

		    $('.ctAddIcon').click(function() {

			ctAddCityTravelDiv();
		    });

		    $(".ctListingEmpty").click(function() {
			ctEmptyListing();
		    });

		    $(".ctAddNewLisiting").click(function() {
			$(".ctAddListingDetails").show();
			$(".ctExistingTripDetails").hide();

			$('#fromDatePicker').Zebra_DatePicker();
			$('#toDatePicker').Zebra_DatePicker();
		    });
		    $(".ctShowLisiting").click(function() {
			$(".ctAddListingDetails").hide();
			$(".ctExistingTripDetails").show();
		    });
		    $('.ctMinusIcon').click(function() {
			ctMinusCityTravelDiv(this);
		    });

		    $(".ctNoDurationTxt")
			    .blur(
				    function() {
					if ($(this).val()
						&& $.trim($(this).val()).length > 0) {

					    var totalLen = $('.ctItenaryParentDiv').length;
					    totalLen = parseInt(totalLen);
					    currLen = parseInt($(this).val());

					    if (currLen == 0) {
						$('.ctItenaryParentDiv')
							.remove();
					    }

					    currLen = currLen - totalLen;

					    if (totalLen == 0) {
						var j = 0;
						for (var i = 1; i <= currLen; i++) {
						    if (j == 0) {
							$('.ctDurationTxt')
								.after(
									ctgetAddItenaryDiv(
										"ctItenaryLen"
											+ i,
										i));
						    } else {
							$('.ctItenaryParentDiv')
								.last()
								.after(
									ctgetAddItenaryDiv(
										"ctItenaryLen"
											+ i,
										i));
						    }
						    j++;

						}
					    }

					    if (totalLen != 0 && currLen > 0) {
						for (var i = currLen; i >= 1; i--) {

						    $('.ctItenaryParentDiv')
							    .last()
							    .after(
								    ctgetAddItenaryDiv(
									    "ctItenaryLen"
										    + (totalLen + i),
									    (totalLen + i)));
						}
					    }

					    $('.ctItenaryCloseBtn')
						    .click(
							    function() {
								$(this)
									.parent()
									.parent()
									.remove();
								ctRearrangeClassName();
								$(
									".ctNoDurationTxt")
									.val(
										$('.ctItenaryParentDiv').length);
							    })

					} else {
					    totalLen = parseInt($(this).val());
					    if (totalLen == 0) {
						$('.ctItenaryParentDiv')
							.remove();
					    }
					}

				    });

		    $('.ctAddIconPhotoGallery')
			    .click(
				    function() {
					var photoGallryDIVLen = $('.ctPhotogalleryParentDiv').length, jqSel = '.ctPhotogalleryParentLen'
						+ photoGallryDIVLen;
					$(jqSel)
						.after(
							ctgetPhotoGalleryFileField('ctPhotogalleryParentLen'
								+ (photoGallryDIVLen + 1)));
				    });
		    $('.ctMinusIconPhotoGallery')
			    .click(
				    function() {
					var photoGallryDIVLen = $('.ctPhotogalleryParentDiv').length, jqSel = '.ctPhotogalleryParentLen'
						+ photoGallryDIVLen;
					if (photoGallryDIVLen > 1) {
					    $(jqSel).remove();
					}
				    });

		});
function ctChkFileUploaded() {
    var isUploaded = true;
    $('.ctPhotogalleryFileName').each(function() {
	if (!$(this).val()) {
	    isUploaded = false;
	    return isUploaded;
	}
    });
    return isUploaded;
};
function ctInsertTripData() {
    var isNotEmpty = true, noDaysTxt = $('.ctNoDurationTxt').val(), fromDate = $(
	    "#fromDatePicker").val(), toDate = $('#toDatePicker').val();

    if (noDaysTxt && noDaysTxt.length > 0
	    && parseInt(noDaysTxt) >= MIN_TRIP_DAYS) {

	if (bmpUtil.isTextFieldEmpty('.ctIsChkEmptyVal')
		&& $(".ctVendorActivitySelectOPtion").prop("selectedIndex") > 0
		&& $(".ctVendorSubActivitySelectOPtion").prop("selectedIndex") > 0
		&& ctChkFileUploaded()) {
	    var ctRouteFormLen = $('.ctRouteFormDiv').length, ctDurationLen = $('.ctItenaryParentDiv').length, convertDateENG = bmpUtil
		    .convertDateENG($("#fromDatePicker").val()), location = $(
		    "#ctSelectActivityType").select2('data').text, tripTitle = $(
		    '.ctVendorActivitySelectOPtion option:selected').text()
		    + ", "
		    + location
		    + " - "
		    + convertDateENG["month"]
		    + " & "
		    + convertDateENG["year"];

	    $('.ctRouteHidden').remove();
	    $('.ctDurationHidden').remove();
	    $('.ctVendorListingForm').append(
		    '<input type="hidden" class="ctRouteHidden" value="'
			    + ctRouteFormLen + '" name="routecount">');
	    $('.ctVendorListingForm').append(
		    '<input type="hidden" class="ctDurationHidden" value="'
			    + ctDurationLen + '" name="durationcount">');
	    $('.ctVendorListingForm').append(
		    '<input type="hidden" class="ctTipTitle" value="'
			    + tripTitle + '" name="triptitle">');
	    $('.ctPhotogalleryFileName').each(function() {
		var temFileName = $(this).val();
		if (temFileName.length == 0) {
		    isNotEmpty = false;
		}
	    });
	    if (bmpUtil.getTwoDatesDiff(fromDate, toDate) > 0) {
		sweetAlert("Oops...", "Please enter correct date !", "error");
		isNotEmpty = false;

	    }

	} else {
	    sweetAlert("Oops...", "Please enter all values!", "error");
	    isNotEmpty = false;
	}
    } else {
	sweetAlert("Oops...",
		"Please enter your days must be greater than zero!", "error");
	isNotEmpty = false;
    }
    return isNotEmpty;
}
function ctRearrangeClassName() {
    var i = 1;
    $('.ctItenaryParentDiv').each(function() {
	var tObj = (this);
	$(".ctItenaryTxtArea", tObj).attr("name", "duration" + i);
	i = i + 1;
    });
};
function ctEmptyListing() {
    $(".ctAddListingDetails").show();
    $(".ctExistingTripDetails").hide();
};
function ctAddCityTravelDiv() {
    var routeLen = $('.ctRouteTxtLen').length, formDIvLen = $('.ctRouteFormDiv').length, html = ctgetAddRouteDiv(
	    "ctRouteFormDivLen" + (formDIvLen + 1), "ctRouteTxtLen"
		    + (routeLen + 1), (routeLen + 1));
    $('.ctRouteFormDivLen' + formDIvLen).after(html);

    $('.ctRouteTxt').alphanum(); // Allow only the letters inside the path

};
function ctMinusCityTravelDiv(tObj) {
    var formDIvLen = $('.ctRouteFormDiv').length;
    if (formDIvLen != 1) {
	$('.ctRouteFormDivLen' + formDIvLen).remove();
    }
};
function ctgetAddRouteDiv(jqParentSel, jqRouteSel, len) {

    var html = '<div class="form-group ctRouteFormDiv ' + jqParentSel + '">'
	    + '<label class="col-lg-3 control-label"></label>'
	    + '<div class="col-lg-4 ctRouteDiv">' + '<input name="route' + len
	    + '" type="text" class="ctIsChkEmptyVal ctRouteTxtLen ctRouteTxt '
	    + jqRouteSel + ' form-control">' + '</div>' + '</div>';
    return html;
};
function ctgetAddItenaryDiv(jqSel, nameLen) {
    var html = '<div class="ctItenaryParentDiv form-group '
	    + jqSel
	    + '" style="position: relative">'
	    + '<label class="col-lg-3 control-label"></label>'
	    + '<div class="col-lg-6 col-sm-3">'
	    + '<img class="ctItenaryCloseBtn" style="cursor: pointer; position: absolute; right: 0px;" alt="" src="/travel/theme/images/x.png">'
	    + '<textarea name="duration'
	    + nameLen
	    + '" placeholder="Enter your Plan for days"  rows="3" class="ctIsChkEmptyVal form-control ctItenaryTxtArea"></textarea>'
	    + '</div>' + '</div>';
    return html;

};
function ctgetPhotoGalleryFileField(jqSel) {
    var html = '<div class="form-group ctPhotogalleryParentDiv ' + jqSel + '">'
	    + '<label class="col-lg-3 col-sm-3 control-label"></label>'
	    + '<div class="col-lg-4 col-sm-4">' + '<label class="">' // custom-file-input
	    + '<input class="ctPhotogalleryFileName" name="file" type="file">'
	    + '</label>' + '</div>' + '</div>';
    return html;
};
function ctSetPagination(numEntries, entryPerPage, visiblePageNo, startPage,
	hrefURL) {
    $(".ctPaginationDiv").css({
	display : "block"
    });
    numEntries = numEntries ? numEntries : 50;
    entryPerPage = entryPerPage ? entryPerPage : 10;
    visiblePageNo = visiblePageNo ? visiblePageNo : 5;
    startPage = startPage ? startPage : 1;
    if (numEntries && entryPerPage) {
	var config = {
	    "parentSelector" : "#pagination-demo",
	    "totalPages" : numEntries,
	    "visiblePages" : visiblePageNo,
	    "entryPerPage" : entryPerPage,
	    "startPage" : startPage,
	    "cbk" : function(event, pageNo) {

	    },
	    "HREF_URL" : hrefURL
	}, renderPagination = new RenderPagination(config);
	renderPagination.render();
    }
};
function ctAddActivityEvent() {
    $('.ctActivityBtn').click(function() {
	var param = {}, activityName = $('.ctActivityName').val();
	if (bmpUtil.isTextFieldEmpty('.ctActivityName')) {
	    $('.ctAddCommentLoadingImage').show();
	    $('.ctActivityNotificationMsg').hide();
	    param = {
		"activityName" : activityName
	    }
	    ctDAO.addNewActivity(param, function(data) {
		if (data && bmpUtil.RESPONSE_STATUS == data.responseStatus) {
		    $('.ctAddCommentLoadingImage').hide();
		    $('.ctActivityNotificationMsg').show();
		} else {
		    $('.ctAddCommentLoadingImage').hide();
		    {
			alert('Already exits');
		    }

		}

	    });
	} else {
	    alert("Please fill all the details");
	}

    });
};
function ctAddSubActivity() {
    $('.ctSubActivityBtn').click(function() {
	var param = {}, subActivityName = $('.ctSubActivityName').val();
	if (bmpUtil.isTextFieldEmpty('.ctSubActivityName')) {
	    $('.ctAddCommentLoadingImage').show();
	    $('.ctSubActivityNotificationMsg').hide();
	    param = {
		"activityName" : subActivityName
	    }
	    ctDAO.addNewSubActivity(param, function(data) {
		if (data && bmpUtil.RESPONSE_STATUS == data.responseStatus) {
		    $('.ctAddCommentLoadingImage').hide();
		    $('.ctSubActivityNotificationMsg').show();
		} else {
		    $('.ctAddCommentLoadingImage').hide();
		    alert(data.responseData);
		}

	    });
	} else {
	    {
		alert('Already exits');
	    }

	}
    });
}
