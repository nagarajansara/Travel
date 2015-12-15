function ctRenderCharts(jqSelector, data, title) {
    $(jqSelector)
	    .highcharts(
		    'StockChart',
		    {

			rangeSelector : {
			    enabled : false
			},
			navigator : {
			    enabled : false
			},
			title : {
			    text : '<spna style="font-family: verdana; font-weight: bold">'
				    +  title  +'</span>'
			},
			series : [ {
			    name : title,
			    data : eval(data),
			    marker : {
				enabled : true,
				radius : 3
			    },
			    shadow : true,
			    tooltip : {
				valueDecimals : 2
			    }
			} ]
		    });

}
