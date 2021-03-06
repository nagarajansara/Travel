
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html>
<html lang="en">
  
<!-- Mirrored from kodeforest.com/html/travel/index.html by HTTrack Website Copier/3.x [XR&CO'2010], Sun, 24 May 2015 04:03:14 GMT -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Travel</title>

    <!-- Css Folder -->
    <link href="/travel/theme/css/bootstrap.css" rel="stylesheet">
    <link href="/travel/theme/css/bootstrap-theme.css" rel="stylesheet">
    <link href="/travel/theme/css/font-awesome.css" rel="stylesheet">
    <link href="/travel/theme/css/color.css" rel="stylesheet">
    <link href="/travel/theme/style.css" rel="stylesheet">
    <link href="/travel/theme/css/responsive.css" rel="stylesheet">
    <link href="/travel/theme/css/themetypo.css" rel="stylesheet">
    <link href="/travel/theme/css/bxslider.css" rel="stylesheet">
    <link href="/travel/theme/css/datepicker.css" rel="stylesheet">
   
    
    <link href="/travel/theme/plugin/datepicker/tsdatepicker.css" rel="stylesheet">
    <link href="/travel/assest/css/travels.css" rel="stylesheet">
   

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
    
  </head>
  <body>
  	 <header id="mainheader">
  	 	 <!--// Top Baar //-->
      <div class="kd-topbar">
        <div class="container">
          <div class="row">
            <div class="col-md-7">
              <ul class="kd-topinfo">
                <li>
                  <div id="lang_sel">
                    <ul>
                        <li>
                            <a class="lang_sel_sel icl-en" href="#">EN</a>
                            <ul>
                                <li class="icl-fr">
                                    <a href="#">FR</a>
                                </li>
                                <li class="icl-de">          
                                    <a href="#">DE</a>
                                </li>
                                <li class="icl-nl">          
                                    <a href="#">NL</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                  </div>
                </li>
                <li>
                  <i class="fa fa-phone"></i> Phone: +44 123 456 789
                </li>
                <li>
                  <i class="fa fa-envelope-o"></i> <a href="#">Email: Info@kodeforest.com</a>
                </li>
              </ul>
            </div>
            <div class="col-md-5">
              <ul class="kd-userinfo">
                <li>
                  <div class="kd-social-network">
                    <ul>
                      <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                      <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                      <li><a href="#"><i class="fa fa-tumblr"></i></a></li>
                      <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                      <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                      <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                    </ul>
                  </div>
                </li>
                <li><a href="#" data-toggle="modal" data-target="#registerModalbox">Register</a>
                  <!-- Modal -->
                  <div class="modal fade kd-loginbox" id="registerModalbox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-body">
                          <a href="#" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
                          <div class="kd-login-title">
                            <h2>Register TO</h2>
                            <span>Your Account</span>
                            <div class="kd-login-network">
                              <ul>
                                <li><a href="#" data-original-title="Facebook"><i class="fa fa-facebook"></i> Login with Facebook</a></li>
                                <li><a href="#" data-original-title="Twitter"><i class="fa fa-twitter"></i> Login with Twitter</a></li>
                              </ul>
                            </div>
                          </div>
                          <div class="kd-login-sepratore"><span>OR</span></div>

                          <form>
                            <p><i class="fa fa-envelope-o"></i> <input type="text" placeholder="Email Address"></p>
                            <p><i class="fa fa-lock"></i> <input type="password" placeholder="Your Password"></p>
                            <p><input type="submit" value="Register now" class="thbg-color"> </p>
                          </form>

                        </div>
                      </div>
                    </div>
                  </div>
                </li>
                <li><a href="#" data-toggle="modal" data-target="#Modalbox">Login</a>
                  <!-- Modal -->
                  <div class="modal fade kd-loginbox" id="Modalbox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-body">
                          <a href="#" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
                          <div class="kd-login-title">
                            <h2>LOGIN TO</h2>
                            <span>Your Account</span>
                            <div class="kd-login-network">
                              <ul>
                                <li><a href="#" data-original-title="Facebook"><i class="fa fa-facebook"></i> Login with Facebook</a></li>
                                <li><a href="#" data-original-title="Twitter"><i class="fa fa-twitter"></i> Login with Twitter</a></li>
                              </ul>
                            </div>
                          </div>
                          <div class="kd-login-sepratore"><span>OR</span></div>

                          <form>
                            <p><i class="fa fa-envelope-o"></i> <input type="text" placeholder="Email Address"></p>
                            <p><i class="fa fa-lock"></i> <input type="password" placeholder="Your Password"></p>
                            <p><input type="submit" value="Login now" class="thbg-color"> <a href="#">Forget Password?</a></p>
                          </form>

                        </div>
                      </div>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <!--// Header Baar //-->
      <div class="kd-headbar">
        <div class="container">
          <div class="row">
            <div class="col-md-3"><a href="index.html" class="logo"><img src="/travel/theme/images/logo.png" alt=""></a></div>
            <div class="col-md-9">
              <div class="kd-rightside">
                <nav class="navbar navbar-default navigation">
                  <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-1">
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                  </div>

                  <div class="collapse navbar-collapse" id="navbar-collapse-1">
                    <ul class="nav navbar-nav">
                      <li><a href="index.html">Home</a>
                        <ul class="sub-dropdown">
                          <li><a href="index-map.html">Map HomePage</a></li>
                        </ul>
                      </li>
                      <li><a href="about-us.html">About Us</a></li>
                      <li><a href="#">Blog</a>
                        <ul class="sub-dropdown">
                          <li><a href="blog-grid.html">Blog Grid</a></li>
                          <li><a href="blog-large.html">Blog Large</a></li>
                          <li><a href="blog-medium.html">Blog Medium</a></li>
                          <li><a href="blog-detail.html">Single Post Layout</a>
                            <ul class="sub-dropdown">
                              <li><a href="blog-detail-video.html">Video & Soundcloud</a></li>
                              <li><a href="blog-detail.html">Single Image Layout</a></li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                      <li><a href="package-list.html">Tour Packages</a>
                        <ul class="sub-dropdown">
                          <li><a href="package-list.html">Packages List</a></li>
                          <li><a href="package-detail.html">Package Single Layout</a>
                            <ul class="sub-dropdown">
                              <li><a href="package-video.html">Video & Soundcloud</a></li>
                              <li><a href="package-detail.html">Package Single Image</a></li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                      <li><a href="gallery.html">Gallery</a>
                        <ul class="sub-dropdown">
                          <li><a href="gallery-2column.html">Gallery 2Column</a></li>
                          <li><a href="gallery-3column.html">Gallery 3Column</a></li>
                          <li><a href="gallery.html">Gallery 4Column</a></li>
                        </ul>
                      </li>
                      <li><a href="#">Pages</a>
                        <ul class="sub-dropdown">
                          <li><a href="undercunstruction.html">Undercunstruction</a></li>
                          <li><a href="404.html">404</a></li>
                          <li><a href="services.html">Services</a></li>
                          <li><a href="#">Team</a>
                            <ul class="sub-dropdown">
                              <li><a href="team-grid.html">Team Grid</a></li>
                              <li><a href="team-medium.html">Team Medium</a></li>
                              <li><a href="team-detail.html">Team Detail</a></li>
                            </ul>
                          </li>
                        </ul>
                      </li>
                      <li><a href="#">ShortCode</a>
                        <ul class="sub-dropdown">
                          <li><a href="accordian.html">Accordian</a></li>
                          <li><a href="button.html">Button</a></li>
                          <li><a href="frame.html">Frame</a></li>
                          <li><a href="list.html">list Style</a></li>
                          <li><a href="message.html">Alert Message</a></li>
                          <li><a href="sepratore.html">Sepratore</a></li>
                          <li><a href="services.html">Services</a></li>
                          <li><a href="skills.html">Skills</a></li>
                          <li><a href="table.html">Table</a></li>
                          <li><a href="tabs.html">Tabs</a></li>
                          <li><a href="shortcode.html">Testimonial & Actions</a></li>
                          <li><a href="video.html">Video</a></li>
                          <li><a href="map.html">Map</a></li>
                        </ul>
                      </li>
                      <li><a href="contact-us.html">contact us</a></li>
                    </ul>
                  </div>
                    <!-- /.navbar-collapse -->
                </nav>
                <div class="kd-search">
                  <a href="#" class="kd-searchbtn" data-toggle="modal" data-target="#searchmodalbox"><i class="fa fa-search"></i></a>
                  <!-- Modal -->
                  <div class="modal fade kd-loginbox" id="searchmodalbox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-body">
                          <a href="#" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></a>
                          <div class="kd-login-title">
                            <h2>Search Your KeyWord</h2>
                          </div>

                          <form>
                            <p><i class="fa fa-search"></i> <input type="text" placeholder="Enter Your Keyword"></p>
                            <p><input type="submit" value="Search" class="thbg-color"> </p>
                          </form>

                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--// Header Baar //-->
  	 </header>
  	 