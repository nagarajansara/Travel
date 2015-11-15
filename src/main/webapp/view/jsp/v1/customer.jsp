<%@ page language="java" contentType="text/html" %>
<%@ include file="lib/header.jsp" %>
<!--<div class="kd-subheader">
	<div class="container">
		<div class="row">
		  <div class="col-md-12">

		    <div class="subheader-info">
		      <h1>Tabs</h1>
		    </div>
		    <div class="kd-breadcrumb">
		      <ul>
			<li><a href="#">Home</a></li>
			<li><a href="#">Blog</a></li>
			<li><a href="#">Our Team</a></li>
		      </ul>
		    </div>

		  </div>
		</div>
	</div>
</div>-->
<div class="kd-content">
	<section style="background: #ffffff; padding: 10px 0px;" class="pagesection ctPagesection">
		<div class="container ctConsumerContainer">
			<div class="row"> 
				<div class="col-md-12">
					<div class="kd-tab kd-vertical-tab">
					  <ul role="tablist" class="nav nav-tabs ctCustomerListMenu">
					    <li class="active" role="presentation"><a data-toggle="tab" role="tab" aria-controls="homeone" href="#homeone" aria-expanded="false">Profile</a></li>
					    <li role="presentation" class="ctMyTravels"><a data-toggle="tab" role="tab" aria-controls="profileone" href="#profileone" aria-expanded="true">My Travels</a></li>
					    <li role="presentation" class="ctMyPointsEarned"><a data-toggle="tab" role="tab" aria-controls="messagesone" href="#messagesone">Points Earned</a></li>
					    <li role="presentation" class="ctreviews"><a data-toggle="tab" role="tab" aria-controls="messagesone" href="#messagesone">Reviews</a></li>
					    <li role="presentation" class="ctCurrentDeals"><a data-toggle="tab" role="tab" aria-controls="messagesone" href="#messagesone">Current Deals</a></li>
					    <li role="presentation" class="ctContactUs"><a data-toggle="tab" role="tab" aria-controls="messagesone" href="#messagesone">Contact Us</a></li>
					    <li role="presentation" class="ctTutorials"><a data-toggle="tab" role="tab" aria-controls="messagesone" href="#messagesone">Tutorials</a></li>
					  </ul>
					  <div class="tab-content" align="center">
					    <!--<div id="homeone" class="tab-pane" role="tabpanel">
					      <div class="kd-dropcap"><p>Dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobor nisl ut aliquip ex ea commodo consequat. Duis autem vel eu iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero er et accumsan et iusto odio dignissim qui.</p></div>
					    </div>
					    <div id="profileone" class="tab-pane active" role="tabpanel"><div class="kd-dropcap"><p>orem ipsum dolor sit amet, consecteture adipiscing elit, sed diam nonummy hibh euismo tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enime ad minim venlam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequ Duis autem vel eum iriure dolor in hendrerit in.</p></div></div>
					    <div id="messagesone" class="tab-pane" role="tabpanel">
					      <div class="kd-dropcap"><p>Lorem ipsum dolor sit amet, consecteture adipiscing elit, sed diam nonummy hibh euismo tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enime ad minim venlam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex.</p></div>
					    </div>-->
					    <div class="row">
					    	<div class="col-md-9 ctDynamicCosumerDetails">
					    		<!--<form id="contactform" class="comments-form" method="post">
					    			<div class="form-group">
										<label class="col-sm-3 col-md-3 col-lg-3 control-label">Normal text input</label>
										<div class="col-sm-9 col-md-9 col-lg-8">
											<input type="text" class="form-control input-sm">
										</div>
									</div>
					    		</form>-->
					    	</div>
					    </div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<div class="clear"></div>
<%@ include file="lib/footer.jsp" %>
<script src="${baseURL}/theme/dashboard/js/jquery.icheck.min.js"></script>
<script src="${baseURL}/theme/dashboard/js/jquery.validate.js"></script>

<script src="${baseURL}/theme/dashboard/js/jquery.nicescroll.min.js"></script>
<script src="${baseURL}/theme/dashboard/js/unicorn.js"></script>
<script src="${baseURL}/theme/dashboard/js/unicorn.form_validation.js"></script>
<script src="${baseURL}/assest/js/citytravels.js"></script>
<script src="${baseURL}/assest/js/ctconsumer.js"></script>
<script type="text/javascript">
	ctInitConsumer();
</script>