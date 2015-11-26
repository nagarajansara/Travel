function ctInitConsumer()
{
	var param = {
	};
	
	$('.ctMyTravels').click(function(){
		ctSetActiveClass('.ctMyTravels');
		var param = {

		};
		ctDAO.getMytravels(param, function(data){
			console.log(bmpUtil.RESPONSE_STATUS);
			if(bmpUtil.RESPONSE_STATUS == data.responseStatus)
			{
				var responseData = data.responseData,
					callBacks = responseData.callback,
					booking = responseData.booking,
					callBackHTML = ''.
					bookingHTML = '';
				if(booking.length > 0)  
				{
					bookingHTML = '<table class="kd-table kd-tableone"><thead>'+
								    '<tr>'+
								      '<th>Vedor Name</th>'+
								      '<th>Trip Name</th>'+
								      '<th>Trip Date</th>'+
								      '</tr>'+
								    '</thead>';
					for(var i = 0; i < booking.length; i++)
					{
						bookingHTML += '<tr>'+
											
										'</tr>';
					}
					$('.ctDynamicCosumerDetails').html(bookingHTML);
					$('.ctDynamicCosumerDetails').append(bookingHTML);
				}
				else
				{
					
				}
			}
		});
	});
}
function ctSetActiveClass(jqlSel)
{
	$('.ctCustomerListMenu li').removeClass('active');
	$(jqlSel).addClass('active');
}
