function RenderPagination(config)
{
	config = config ? config : {};
	this.parentSelector = config.parentSelector ? config.parentSelector : "test_";
	this.totalPages = config.totalPages ? config.totalPages : 0;
	this.visiblePages = config.visiblePages ? config.visiblePages : 0;
	this.entryPerPage = config.entryPerPage ? config.entryPerPage : 0;
	this.cbk = 	config.cbk;
	this.startPage = config.startPage;
	this.HREF_URL = config.HREF_URL;
};
RenderPagination.prototype.render = function()
{
	var tObj = this;
	$(tObj.parentSelector).twbsPagination({
		totalPages: tObj.getTotalPage(),
		visiblePages: tObj.visiblePages,
		onPageClick: function (event, page) {
			if(tObj.cbk)
			{
				tObj.cbk(event, page);
			}
		},
		href: tObj.HREF_URL + '/{{number}}',
		first: ">>",
		prev: "<",
		next: ">",
		last: ">>",
		startPage: tObj.startPage
	});
};
RenderPagination.prototype.getTotalPage = function()
{
	var tObj = this,
		tempTotalNo = tObj.totalPages / tObj.entryPerPage;
	if(!tObj.isFloatNumber(tempTotalNo))
	{
		tempTotalNo = Math.floor(tempTotalNo);
		tempTotalNo = tempTotalNo + 1;
	}
	return tempTotalNo;
};
RenderPagination.prototype.isFloatNumber = function(n)
{
  return n % 1 === 0;
};
