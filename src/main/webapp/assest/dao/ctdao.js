var ctDAO = new CtDAO();

function CtDAO() {
    this.CONTEXT_NAME = "travel";
    this.DOMAIN_NAME = window.location.host;
    this.HTTP_URL_PREFIX = "http://" + window.location.host + "/"
	    + this.CONTEXT_NAME + "/";
    this.HTTPS_URL_PREFIX = "https://" + window.location.host + "/"
	    + this.CONTEXT_NAME + "/";
    this.API_PREFIX = this.HTTP_URL_PREFIX + "travelapi";

    this.GET_CONSUMER_PROFILE_DETAILS = this.API_PREFIX
	    + "/consumer/getprofile.json";
    this.GET_CONSUMER_CALLBACK_DETAILS = this.API_PREFIX
	    + "/consumer/getcallbacksdetails.json";
    this.GET_MY_TARVELS = this.API_PREFIX + "/consumer/getmytravels.json";
    this.GET_DELETE_TRIP_IMAGE_URL = this.API_PREFIX
	    + "/vendor/deleteTripImage.json";
    this.GET_TRIPDETAILS_UPDATE_STATUS_URL = this.API_PREFIX
	    + "/trip/updateTripDetailStatus.json";
    this.ADD_TRIP_COMMENTS = this.API_PREFIX + "/trip/addComments.json";
    this.GET_TRIP_COMMENTS_PAGENO = this.API_PREFIX
	    + "/trip/getCommentsPagno.json";
    this.ADD_ENQUIRY_FORM = this.API_PREFIX + "/trip/addEnquiry.json";
    this.ACTIVATE_ENQUIRY = this.API_PREFIX + "/login/activateEnquiry.json";
    this.UPDATE_DEALS = this.API_PREFIX + "/vendor/updateDeals.json";
    this.GET_VENDOR_REVIEW = this.API_PREFIX + "/vendor/getVendorReviews.json";
    this.GET_VENDOR_REVIW_PAGENO = this.API_PREFIX
	    + "/vendor/getVendorReviewsPageNo.json";
    this.GET_ALL_TRIPDETAILS = this.API_PREFIX
	    + "/vendor/getAllTripDetails.json";
    this.GET_CREDITS_REDUCTION_MORE_DETAILS = this.API_PREFIX
	    + "/vendor/getCreditsReductionMoreDetails.json";
    this.GET_MORE_SENT_ENQUIRY_DETAILS = this.API_PREFIX
	    + "/login/getMoreSentEnquireDetails.json";
    // IMAGE_TYPE (COVERIMAGE OR PROFILE IMAGE)
    this.UPDATE_TRIPIMAGE_TYPE = this.API_PREFIX
	    + "/trip/updateTripImgType.json";
    this.ADD_SAVED_TRIPLIST = this.API_PREFIX + "/consumer/addSavedTrips.json";
    this.ADD_ACTIVITY = this.API_PREFIX + "/vendor/addNewActivity.json";
    this.ADD_SUB_ACTIVITY = this.API_PREFIX + "/vendor/addNewSubActivity.json";
    this.GET_REST_API_ACTIVITY = this.API_PREFIX + "/getRestActivitys.json";
    this.ADD_GET_QUOTE_DETAILS = this.API_PREFIX
	    + "/consumer/addQuoteDetails.json";

    this.RESPONSE_CACHE = {};

    this.TOTAL_RECORDS_PER_PAGE = 5;
    this.INITIAL_USER_CREDITS = 0;

    this.CACHE_MAP = {};

};
CtDAO.prototype.getConsumerProfile = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_CONSUMER_PROFILE_DETAILS, param, cbk);
};
CtDAO.prototype.getCallbacksDetails = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_CONSUMER_CALLBACK_DETAILS, param, cbk);
};
CtDAO.prototype.getMytravels = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_MY_TARVELS, param, cbk);
};
CtDAO.prototype.getDeleteTripImage = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_DELETE_TRIP_IMAGE_URL, param, cbk);
};
CtDAO.prototype.getTripDetailsUpdateStatus = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_TRIPDETAILS_UPDATE_STATUS_URL, param, cbk);
};
CtDAO.prototype.addComments = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_TRIP_COMMENTS, param, cbk);
};
CtDAO.prototype.getCommentsPagno = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_TRIP_COMMENTS_PAGENO, param, cbk);
};
CtDAO.prototype.addEnquiry = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_ENQUIRY_FORM, param, cbk);
};
CtDAO.prototype.activateEnquiry = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.ACTIVATE_ENQUIRY, param, cbk);
};
CtDAO.prototype.updateDeals = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPDATE_DEALS, param, cbk);
};
CtDAO.prototype.getVendorReviews = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_VENDOR_REVIEW, param, cbk);
};
CtDAO.prototype.getVendorReviewsPageNo = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_VENDOR_REVIW_PAGENO, param, cbk);
};
CtDAO.prototype.getAllTripDetails = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_ALL_TRIPDETAILS, param, cbk);
};
CtDAO.prototype.getCreditsReductionMoreDetails = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_CREDITS_REDUCTION_MORE_DETAILS, param, cbk);

};
CtDAO.prototype.getMoreSentEnquireDetails = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_MORE_SENT_ENQUIRY_DETAILS, param, cbk);

};
CtDAO.prototype.updateTripImgType = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.UPDATE_TRIPIMAGE_TYPE, param, cbk);

};
CtDAO.prototype.addSavedTrips = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_SAVED_TRIPLIST, param, cbk);
};
CtDAO.prototype.addNewActivity = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_ACTIVITY, param, cbk);
};
CtDAO.prototype.addNewSubActivity = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_SUB_ACTIVITY, param, cbk);
};
CtDAO.prototype.getRestApiActivity = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.GET_REST_API_ACTIVITY, param, cbk, true);
};
CtDAO.prototype.addGetQuoteDetails = function(param, cbk) {
    var tObj = this;
    tObj.getData(tObj.ADD_GET_QUOTE_DETAILS, param, cbk);
};
CtDAO.prototype.getData = function(url, postParams, callback, isCacheMap,
	isParse) {
    var tObj = this, cbk = function(data, isCacheMap) {
	if (data && data.model) {
	    if (!isParse) {

	    }
	    console.log("isCacheMap :" + isCacheMap);
	    if (isCacheMap) {
		tObj.CACHE_MAP[url] = data;
		console.log(tObj.CACHE_MAP[url]);
	    }
	    data = data.model;
	}
	callback(data);
    }, ajaxConfig = {
	"type" : (postParams ? "POST" : "GET"),
	"url" : url,
	"dataType" : "json",
	"data" : postParams,
	error : function(err) {

	},
	success : function(data) {
	    cbk(data, isCacheMap);
	},
	statusCode : {
	    404 : function() {
		alert("Check your internet connection");
	    }
	}

    };
    if (!tObj.CACHE_MAP[url]) {
	jQuery.ajax(ajaxConfig);
    } else {
	cbk(tObj.CACHE_MAP[url], isCacheMap);
    }

};
CtDAO.prototype.parseJSON = function(jsonData) {
    try {
	jsonData = JSON.parse(jsonData);
    } catch (ex) {
	console.log(ex);
    }
    return jsonData;
};
