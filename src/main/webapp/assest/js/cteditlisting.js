var TRIP_IMAGE_TYPE_COVER = "coverimage";
$(function() {

	$('#ctEditfromDatePicker').Zebra_DatePicker();
	$('#ctEdittoDatePicker').Zebra_DatePicker();

	$('.ctEditItenaryCloseBtn').click(function() {
		$(this).parent().parent().remove();
		ctEditRearrangeClassName();
		$(".ctEditNoDurationTxt").val($('.ctEditItenaryParentDiv').length);
	});

	$('.ctEditAddIconPhotoGallery')
			.click(
					function() {
						var photoGallryDIVLen =
								$('.ctEditPhotogalleryParentDiv').length, jqSel =
								'.ctEditPhotogalleryParentLen'
										+ photoGallryDIVLen;
						$(jqSel)
								.after(
										ctgetEditPhotoGalleryFileField('ctEditPhotogalleryParentLen'
												+ (photoGallryDIVLen + 1)));
					});
	$('.ctEditMinusIconPhotoGallery').click(
			function() {
				var photoGallryDIVLen =
						$('.ctEditPhotogalleryParentDiv').length, jqSel =
						'.ctEditPhotogalleryParentLen' + photoGallryDIVLen;
				if (photoGallryDIVLen > 1)
				{
					$(jqSel).remove();
				}
			});

	$(".ctEditNoDurationTxt").blur(
			function() {
				if ($(this).val() && $.trim($(this).val()).length > 0)
				{

					var totalLen = $('.ctEditItenaryParentDiv').length;
					totalLen = parseInt(totalLen);
					currLen = parseInt($(this).val());

					if (currLen == 0)
					{
						$('.ctEditItenaryParentDiv').remove();
					}

					currLen = currLen - totalLen;

					if (totalLen == 0)
					{
						var j = 0;
						for (var i = 1; i <= currLen; i++)
						{
							if (j == 0)
							{
								$('.ctEditDurationTxt').after(
										ctEditgetAddItenaryDiv("ctItenaryLen"
												+ i, i));
							}
							else
							{
								$('.ctEditItenaryParentDiv').last().after(
										ctEditgetAddItenaryDiv("ctItenaryLen"
												+ i, i));
							}
							j++;

						}
					}

					if (totalLen != 0 && currLen > 0)
					{
						for (var i = currLen; i >= 1; i--)
						{

							$('.ctEditItenaryParentDiv').last().after(
									ctEditgetAddItenaryDiv("ctItenaryLen"
											+ (totalLen + i), (totalLen + i)));
						}
					}

					$('.ctEditItenaryCloseBtn').unbind('click');
					$('.ctEditItenaryCloseBtn').click(
							function() {
								$(this).parent().parent().remove();
								ctEditRearrangeClassName();
								$(".ctEditNoDurationTxt").val(
										$('.ctEditItenaryParentDiv').length);
							});

				}
				else
				{
					totalLen = parseInt($(this).val());
					if (totalLen == 0)
					{
						$('.ctItenaryParentDiv').remove();
					}
				}

			});
});
function ctgetEditPhotoGalleryFileField(jqSel) {
	var html =
			'<div class="form-group ctEditPhotogalleryParentDiv '
					+ jqSel
					+ '">'
					+ '<div class="col-lg-4 col-sm-4">'
					+ '<label class="">'
					+ '<input class="ctEditPhotogalleryFile" name="file" type="file">'
					+ '</label>' + '</div>' + '</div>';
	return html;
};
function ctEditRearrangeClassName() {
	var i = 1;
	$('.ctEditItenaryParentDiv').each(function() {
		var tObj = (this);
		$(".ctItenaryTxtArea", tObj).attr("name", "duration" + i);
		i = i + 1;
	});
};
function ctEditgetAddItenaryDiv(jqSel, nameLen) {
	var html =
			'<div class="ctEditItenaryParentDiv form-group '
					+ jqSel
					+ '" style="position: relative">'
					+ '<label class="col-lg-3 control-label"></label>'
					+ '<div class="col-lg-6 col-sm-3">'
					+ '<img class="ctEditItenaryCloseBtn" style="cursor: pointer; position: absolute; right: 0px;" alt="" src="/travel/theme/images/x.png">'
					+ '<textarea name="duration'
					+ nameLen
					+ '" placeholder="Enter your Plan for days"  rows="3" class="ctIsChkEmptyVal form-control ctItenaryTxtArea"></textarea>'
					+ '</div>' + '</div>';
	return html;

};
function ctUpdateTripImgType()
{
    $("input[name=coverImg]:radio").change(function () {
	var imgID = $(this).attr('pk_ID'),
		tripID = $(this).attr('pk_tripID'),
		status = TRIP_IMAGE_TYPE_COVER,
		param = {};
	param = {
		"tripId": tripID,
		"imgId": imgID
	};
	ctDAO.updateTripImgType(param, function(data){
	    if(data && data.responseStatus == 200)
		{
			alert("Successfully updated");
		}
	    else
		{
			alert("Error occur");
		}
	}); 
	
    });
    
}