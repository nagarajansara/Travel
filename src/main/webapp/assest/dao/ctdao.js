var ctDAO = new CtDAO();

function CtDAO()
{
	this.CONTEXT_NAME = "travel";
	this.DOMAIN_NAME = window.location.host;
	this.HTTP_URL_PREFIX = "http://" + window.location.host + "/" + this.CONTEXT_NAME + "/";
	this.HTTPS_URL_PREFIX = "https://" + window.location.host + "/" + this.CONTEXT_NAME + "/";
	this.API_PREFIX = this.HTTP_URL_PREFIX + "travelapi";


	this.GET_CONSUMER_PROFILE_DETAILS = this.API_PREFIX + "/consumer/getprofile.json";
	this.GET_CONSUMER_CALLBACK_DETAILS = this.API_PREFIX + "/consumer/getcallbacksdetails.json";
	this.GET_MY_TARVELS = this.API_PREFIX + "/consumer/getmytravels.json";
	this.GET_DELETE_TRIP_IMAGE_URL = this.API_PREFIX + "/vendor/deleteTripImage.json";
	this.GET_TRIPDETAILS_UPDATE_STATUS_URL = this.API_PREFIX + "/trip/updateTripDetailStatus.json";

	this.RESPONSE_CACHE = {};

	this.TOTAL_RECORDS_PER_PAGE = 10;

	
	this.CACHE_MAP = {};

};
CtDAO.prototype.getConsumerProfile = function(param, cbk)
{
	var tObj = this;
	tObj.getData(tObj.GET_CONSUMER_PROFILE_DETAILS, param, cbk);
};
CtDAO.prototype.getCallbacksDetails = function(param, cbk)
{
	var tObj = this;
	tObj.getData(tObj.GET_CONSUMER_CALLBACK_DETAILS, param, cbk);
};
CtDAO.prototype.getMytravels = function(param, cbk)
{
	var tObj = this;
	tObj.getData(tObj.GET_MY_TARVELS, param, cbk);
};
CtDAO.prototype.getDeleteTripImage = function(param, cbk)
{
	var tObj = this;
	tObj.getData(tObj.GET_DELETE_TRIP_IMAGE_URL, param, cbk);
};
CtDAO.prototype.getTripDetailsUpdateStatus = function(param, cbk)
{
	var tObj = this;
	tObj.getData(tObj.GET_TRIPDETAILS_UPDATE_STATUS_URL, param, cbk);
};
CtDAO.prototype.getData = function(url, postParams, callback, isCacheMap, isParse)
{
	var	tObj = this,
		cbk = function(data)
		{
			if(data && data.model)
			{
				data = data.model;
				if(!isParse)
				{
					
				}
				if(isCacheMap && !tObj.CACHE_MAP[url])
				{
					tObj.CACHE_MAP[url] = data; 
				}
			}
			callback(data);
		},
		ajaxConfig = {
			"type": (postParams ? "POST" : "GET"),
			"url": url,
			"dataType": "json",
			"data": postParams,
			error: function (err) 
			{
				
			},
			success: function(data)
			{
				cbk(data);
			},
			statusCode: 
			{
				404: function() 
				{
					alert( "Check your internet connection" );
				}
			}
		
    	};
		jQuery.ajax(ajaxConfig);
};
CtDAO.prototype.parseJSON = function(jsonData)
{
	try
	{
		jsonData = JSON.parse(jsonData);
	}
	catch(ex)
	{
		console.log(ex);
	}
	return jsonData;
};