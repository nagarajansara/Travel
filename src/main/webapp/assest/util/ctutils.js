var bmpUtil = new BmpUtil();
function BmpUtil() {
    this.RESPONSE_STATUS = 200;
    this.RESPONSE_MSG = "Success";
};
BmpUtil.prototype.isTextFieldEmpty = function(jqSelector) {
    var isValid = true;
    $(jqSelector).each(function() {
	if ($.trim($(this).val()) == '') {
	    isValid = false;
	}
    });
    return isValid;
};
BmpUtil.prototype.chkNumber = function(jqSelector, event) {
    if (event.which != 8 && event.which != 0
	    && (event.which < 48 || event.which > 57)) {
	return false;
    } else {
	return true;
    }
};
BmpUtil.prototype.getLastStartingURL = function() {
    var lastPart = ((location.href.toString()).split("/")).pop();
    return lastPart;
};
BmpUtil.prototype.showAlert = function(msg, title) {
    var toastCount = 0, $toastlast, shortCutFunction = "success", toastIndex = toastCount++, $toast;
    msg = msg || '';
    title = title || '';
    toastr.options = {
	closeButton : $('#closeButton').prop('checked'),
	debug : $('#debugInfo').prop('checked'),
	positionClass : $('#positionGroup input:radio:checked').val()
		|| 'toast-top-right',
	onclick : null
    };
    if ($('#addBehaviorOnToastClick').prop('checked')) {
	toastr.options.onclick = function() {
	    alert('You can perform some custom action after a toast goes away');
	};
    }
    ;
    toastr.options.showDuration = "300";
    toastr.options.hideDuration = "1000";
    toastr.options.timeOut = "5000";
    toastr.options.extendedTimeOut = "1000";
    toastr.options.showEasing = "swing";
    toastr.options.hideEasing = "linear";
    toastr.options.showMethod = "fadeIn";
    toastr.options.hideMethod = "fadeOut";
    $("#toastrOptions").text(
	    "Command: toastr[" + shortCutFunction + "](\"" + msg
		    + (title ? "\", \"" + title : '')
		    + "\")\n\ntoastr.options = "
		    + JSON.stringify(toastr.options, null, 2));
    $toast = toastr[shortCutFunction](msg, title); // Wire up an event
    // handler to a
    // button in the
    // toast, if it
    // exists
    $toastlast = $toast;
    if ($toast.find('#okBtn').length) {
	$toast.delegate('#okBtn', 'click', function() {
	    alert('you clicked me. i was toast #' + toastIndex + '. goodbye!');
	    $toast.remove();
	});
    }
    if ($toast.find('#surpriseBtn').length) {
	$toast.delegate('#surpriseBtn', 'click', function() {
	    alert('Surprise! you clicked me. i was toast #' + toastIndex
		    + '. You could perform an action here.');
	});
    }
    function getLastToast() {
	return $toastlast;
    }
    $('#clearlasttoast').click(function() {
	toastr.clear(getLastToast());
    });
    $('#cleartoasts').click(function() {
	toastr.clear();
    });

};
BmpUtil.prototype.getQParamByName = function(name, url) {
    if (url) {
	if (url.indexOf("?") >= 0) {
	    url = url.substring(url.indexOf("?") + 1, url.length);
	}
    } else {
	url = window.location.search;
    }
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(url);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
};
BmpUtil.prototype.getLoginPage = function() {
    return "/bulkmail/view/jsp/v1/login.jsp";
};
BmpUtil.prototype.validateURL = function(str) {

    var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    return regexp.test(str);
};
BmpUtil.prototype.validateEmail = function(email) {
    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    if (filter.test(email)) {
	return true;
    } else {
	return false;
    }
};
BmpUtil.prototype.passwordStrength = function(event, tObj, jqSelector) {
    var strongRegex = new RegExp(
	    "^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
    var mediumRegex = new RegExp(
	    "^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$",
	    "g");
    var enoughRegex = new RegExp("(?=.{6,}).*", "g");
    if (false == enoughRegex.test($(tObj).val())) {
	$(jqSelector).html('More Characters');
    } else if (strongRegex.test($(tObj).val())) {
	$(jqSelector).className = 'ok';
	$(jqSelector).css({
	    'background-color' : '#36BA38'
	});
	$(jqSelector).html('Strong!');
    } else if (mediumRegex.test($(tObj).val())) {
	$(jqSelector).className = 'alert';
	$(jqSelector).html('Medium!');
    } else {
	$(jqSelector).className = 'error';
	$(jqSelector).css({
	    'background-color' : '#F20000'
	});
	$(jqSelector).html('Weak!');
    }
    return true;
};
BmpUtil.prototype.convertDateENG = function(currDate) {
    var months = new Array(12), month_value = '', day_value = '', year_value = '', current_date, convertedJSON = {};
    months[0] = "January";
    months[1] = "February";
    months[2] = "March";
    months[3] = "April";
    months[4] = "May";
    months[5] = "June";
    months[6] = "July";
    months[7] = "August";
    months[8] = "September";
    months[9] = "October";
    months[10] = "November";
    months[11] = "December";
    current_date = new Date(currDate);

    month_value = current_date.getMonth();
    day_value = current_date.getDate();
    year_value = current_date.getFullYear();

    month_value = parseInt(month_value);
    convertedJSON["month"] = months[month_value];
    convertedJSON["year"] = year_value;
    convertedJSON["day"] = day_value;

    return convertedJSON;
};
BmpUtil.prototype.getTwoDatesDiff = function(date1, date2) {
    var diffDays = -1;
    var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
    var fromDate = date1.split("-");
    var toDate = date2.split("-");
    if (fromDate && fromDate.length > 0 && toDate && toDate.length > 0) {
	var date1 = new Date(fromDate[0], fromDate[1], fromDate[2]);
	var date2 = new Date(toDate[0], toDate[1], toDate[2]);

	diffDays = Math.round(((date1.getTime() - date2.getTime()) / (oneDay)));
    }
    return diffDays;

};