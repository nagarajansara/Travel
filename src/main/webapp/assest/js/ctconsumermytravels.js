function ctSetConsumerPagination(numEntries, entryPerPage, visiblePageNo,
	startPage, hrefURL) {
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