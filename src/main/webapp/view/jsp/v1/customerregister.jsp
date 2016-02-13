<%@page import="travel.com.model.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
%>

<%@ page language="java" contentType="text/html"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Travel Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="/travel/theme/dashboard/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/travel/theme/dashboard/css/font-awesome.css" />
<link rel="stylesheet"
	href="/travel/theme/dashboard/css/icheck/flat/blue.css" />
<link rel="stylesheet" href="/travel/theme/dashboard/css/select2.css" />
<link rel="stylesheet" href="/travel/theme/dashboard/css/unicorn.css" />




<!--[if lt IE 9]>
		<script type="text/javascript" src="js/respond.min.js"></script>
		<![endif]-->

<style>
.ctWrapper {
	background-color: none !important;
}

.ctWrapper {
	/*margin-top: 25px !important;*/
	
}

.ctWrapper {
	font-family: verdana;
	font-size: 12px;
}

body {
	background: none !important;
}

.ctRegCitySelect {
	height: 35px;
}

.ctOpenLoginRow {
	margin-top: 16px;
}

.ctHeaderTag {
	background-color: rgb(8, 125, 194) !important;
}

.ctRequired {
	color: red;
}
</style>

</head>
<body data-color="white" class="flat">
	<div id="wrapper">
		<div id="header" class="ctHeaderTag">
			<h1>
				<a href="index.html">Unicorn Admin</a>
			</h1>
			<!--<a href="#" id="menu-trigger"><i class="fa fa-bars"></i></a>-->
		</div>
		<div id="content" class="ctWrapper">
			<div class="row">
				<div class="col-xs-12 col-sm-8">
					<div class="row ctOpenLoginRow">
						<!--<div class="col-xs-12 col-sm-6">
								<a href="<%-- <%=fbConnection.getFBAuthUrl()%> --%>">
									<input type="button" value="Login with Facebook" class="btn btn-primary btn-block">
								</a>	
							</div>
							<div class="col-xs-12 col-sm-6">
								<input type="button" value="Login with Twitter" class="btn btn-danger btn-block">
							</div>-->
					</div>
					</br> </br>
					<c:if test="${not empty model.responseStatus}">
						<c:if test="${model.responseStatus ne 200}">
							<c:set var="respomseMsg" value="${(model.responseMsg)}" />
							<div class="row">
								<div class="col-xs-12">
									<c:choose>
										<c:when test="${fn:contains(respomseMsg, 'Duplicate entry')}">
											<div class="alert alert-danger">
												<button data-dismiss="alert" class="close">�</button>
												<strong></strong>User already exits
											</div>
										</c:when>
										<c:otherwise>
											<div class="alert alert-danger">
												<button data-dismiss="alert" class="close">�</button>
												<strong></strong>
												<c:out value="${(model.responseMsg)}" />
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:if>
						<c:if test="${model.responseStatus eq 200}">
							<%-- <c:redirect url="/view/jsp/v1/home.jsp" /> --%>
							<span style="font-family: verdana; color: red">Registered
								success. Please check your email and activate your account</span>
						</c:if>
					</c:if>
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="fa fa-align-justify"></i>
							</span>
							<h5>Customer Registration</h5>
						</div>
						<div class="widget-content nopadding">
							<form class="form-horizontal" method="post"
								action="/travel/travelapi/login/customerregister"
								name="basic_validate" id="basic_validate"
								novalidate="novalidate">
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Email<span
										class="ctRequired"> (*)</span></label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm" name="email"
											id="email">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">First
										Name<span class="ctRequired"> (*)</span>
									</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm"
											name="firstName" id="firstName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Last
										Name<span class="ctRequired"> (*)</span>
									</label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="text" class="form-control input-sm"
											name="lastName" id="lastName">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">Password<span
										class="ctRequired"> (*)</span></label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<input type="password" class="form-control input-sm"
											name="password" id="password">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 col-md-3 col-lg-3 control-label">City<span
										class="ctRequired"> (*)</span></label>
									<div class="col-sm-9 col-md-9 col-lg-5">
										<select name="cityName" class="ctRegCitySelect">
											<option value="Achhnera">Achhnera</option>

											<option value="Adalaj">Adalaj</option>

											<option value="Adilabad">Adilabad</option>

											<option value="Adityapur">Adityapur</option>

											<option value="Adoni">Adoni</option>

											<option value="Adoor">Adoor</option>

											<option value="Adra">Adra</option>

											<option value="Adyar">Adyar</option>

											<option value="Afzalpur">Afzalpur</option>

											<option value="Agartala">Agartala</option>

											<option value="Agra">Agra</option>

											<option value="Ahmedabad">Ahmedabad</option>

											<option value="Ahmednagar">Ahmednagar</option>

											<option value="Aizawl">Aizawl</option>

											<option value="Ajmer">Ajmer</option>

											<option value="Akola">Akola</option>

											<option value="Akot">Akot</option>

											<option value="Alappuzha">Alappuzha</option>

											<option value="Aligarh">Aligarh</option>

											<option value="Alipurduar">Alipurduar</option>

											<option value="Alirajpur">Alirajpur</option>

											<option value="Allahabad">Allahabad</option>

											<option value="Alwar">Alwar</option>

											<option value="Amalapuram">Amalapuram</option>

											<option value="Amalner">Amalner</option>

											<option value="Ambejogai">Ambejogai</option>

											<option value="Ambikapur">Ambikapur</option>

											<option value="Amravati">Amravati</option>

											<option value="Amreli">Amreli</option>

											<option value="Amritsar">Amritsar</option>

											<option value="Amroha">Amroha</option>

											<option value="Anakapalle">Anakapalle</option>

											<option value="Anand">Anand</option>

											<option value="Anantapur">Anantapur</option>

											<option value="Anantnag">Anantnag</option>

											<option value="Anjangaon">Anjangaon</option>

											<option value="Anjar">Anjar</option>

											<option value="Ankleshwar">Ankleshwar</option>

											<option value="Arakkonam">Arakkonam</option>

											<option value="Arambagh">Arambagh</option>

											<option value="Araria">Araria</option>

											<option value="Arrah">Arrah</option>

											<option value="Arsikere">Arsikere</option>

											<option value="Aruppukkottai">Aruppukkottai</option>

											<option value="Arvi">Arvi</option>

											<option value="Arwal">Arwal</option>

											<option value="Asansol">Asansol</option>

											<option value="Asarganj">Asarganj</option>

											<option value="Ashok Nagar">Ashok Nagar</option>

											<option value="Athni">Athni</option>

											<option value="Attingal">Attingal</option>

											<option value="Aurangabad">Aurangabad</option>

											<option value="Azamgarh">Azamgarh</option>

											<option value="Bagaha">Bagaha</option>

											<option value="Bageshwar">Bageshwar</option>

											<option value="Bahadurgarh">Bahadurgarh</option>

											<option value="Baharampur">Baharampur</option>

											<option value="Bahraich">Bahraich</option>

											<option value="Balaghat">Balaghat</option>

											<option value="Balangir">Balangir</option>

											<option value="Baleshwar Town">Baleshwar Town</option>

											<option value="Ballari">Ballari</option>

											<option value="Balurghat">Balurghat</option>

											<option value="Bankura">Bankura</option>

											<option value="Bapatla">Bapatla</option>

											<option value="Baramula">Baramula</option>

											<option value="Barbil">Barbil</option>

											<option value="Bargarh">Bargarh</option>

											<option value="Barh">Barh</option>

											<option value="Baripada Town">Baripada Town</option>

											<option value="Barnala">Barnala</option>

											<option value="Barpeta">Barpeta</option>

											<option value="Batala">Batala</option>

											<option value="Bathinda">Bathinda</option>

											<option value="Begusarai">Begusarai</option>

											<option value="Belagavi">Belagavi</option>

											<option value="Bellampalle">Bellampalle</option>

											<option value="Belonia">Belonia</option>

											<option value="Bengaluru">Bengaluru</option>

											<option value="Bettiah">Bettiah</option>

											<option value="Bhabua">Bhabua</option>

											<option value="Bhadrachalam">Bhadrachalam</option>

											<option value="Bhadrak">Bhadrak</option>

											<option value="Bhagalpur">Bhagalpur</option>

											<option value="Bhainsa">Bhainsa</option>

											<option value="Bharatpur">Bharatpur</option>

											<option value="Bharuch">Bharuch</option>

											<option value="Bhatapara">Bhatapara</option>

											<option value="Bhavnagar">Bhavnagar</option>

											<option value="Bhawanipatna">Bhawanipatna</option>

											<option value="Bheemunipatnam">Bheemunipatnam</option>

											<option value="Bhilai Nagar">Bhilai Nagar</option>

											<option value="Bhilwara">Bhilwara</option>

											<option value="Bhimavaram">Bhimavaram</option>

											<option value="Bhiwandi">Bhiwandi</option>

											<option value="Bhiwani">Bhiwani</option>

											<option value="Bhongir">Bhongir</option>

											<option value="Bhopal">Bhopal</option>

											<option value="Bhubaneswar">Bhubaneswar</option>

											<option value="Bhuj">Bhuj</option>

											<option value="Bikaner">Bikaner</option>

											<option value="Bilaspur">Bilaspur</option>

											<option value="Bobbili">Bobbili</option>

											<option value="Bodhan">Bodhan</option>

											<option value="Bokaro Steel City">Bokaro Steel City</option>

											<option value="Bongaigaon City">Bongaigaon City</option>

											<option value="Brahmapur">Brahmapur</option>

											<option value="Buxar">Buxar</option>

											<option value="Byasanagar">Byasanagar</option>

											<option value="Chaibasa">Chaibasa</option>

											<option value="Chalakudy">Chalakudy</option>

											<option value="Chandausi">Chandausi</option>

											<option value="Chandigarh">Chandigarh</option>

											<option value="Changanassery">Changanassery</option>

											<option value="Charkhi Dadri">Charkhi Dadri</option>

											<option value="Chatra">Chatra</option>

											<option value="Chennai">Chennai</option>

											<option value="Cherthala">Cherthala</option>

											<option value="Chhapra">Chhapra</option>

											<option value="Chikkamagaluru">Chikkamagaluru</option>

											<option value="Chilakaluripet">Chilakaluripet</option>

											<option value="Chirala">Chirala</option>

											<option value="Chirkunda">Chirkunda</option>

											<option value="Chirmiri">Chirmiri</option>

											<option value="Chittoor">Chittoor</option>

											<option value="Chittur-Thathamangalam">Chittur-Thathamangalam</option>

											<option value="Coimbatore">Coimbatore</option>

											<option value="Cuttack">Cuttack</option>

											<option value="Dalli-Rajhara">Dalli-Rajhara</option>

											<option value="Darbhanga">Darbhanga</option>

											<option value="Darjiling">Darjiling</option>

											<option value="Davanagere">Davanagere</option>

											<option value="Deesa">Deesa</option>

											<option value="Dehradun">Dehradun</option>

											<option value="Dehri-on-Sone">Dehri-on-Sone</option>

											<option value="Delhi">Delhi</option>

											<option value="Deoghar">Deoghar</option>

											<option value="Dhamtari">Dhamtari</option>

											<option value="Dhanbad">Dhanbad</option>

											<option value="Dharmanagar">Dharmanagar</option>

											<option value="Dharmavaram">Dharmavaram</option>

											<option value="Dhenkanal">Dhenkanal</option>

											<option value="Dhoraji">Dhoraji</option>

											<option value="Dhubri">Dhubri</option>

											<option value="Dhule">Dhule</option>

											<option value="Dhuri">Dhuri</option>

											<option value="Dibrugarh">Dibrugarh</option>

											<option value="Dimapur">Dimapur</option>

											<option value="Diphu">Diphu</option>

											<option value="Dumka">Dumka</option>

											<option value="Dumraon">Dumraon</option>

											<option value="Durg">Durg</option>

											<option value="Eluru">Eluru</option>

											<option value="English Bazar">English Bazar</option>

											<option value="Erode">Erode</option>

											<option value="Etawah">Etawah</option>

											<option value="Faridabad">Faridabad</option>

											<option value="Faridkot">Faridkot</option>

											<option value="Farooqnagar">Farooqnagar</option>

											<option value="Fatehabad">Fatehabad</option>

											<option value="Fatehpur Sikri">Fatehpur Sikri</option>

											<option value="Fazilka">Fazilka</option>

											<option value="Firozabad">Firozabad</option>

											<option value="Firozpur">Firozpur</option>

											<option value="Firozpur Cantt.">Firozpur Cantt.</option>

											<option value="Forbesganj">Forbesganj</option>

											<option value="Gadwal">Gadwal</option>

											<option value="Gangarampur">Gangarampur</option>

											<option value="Ganjbasoda">Ganjbasoda</option>

											<option value="Gaya">Gaya</option>

											<option value="Giridih">Giridih</option>

											<option value="Goalpara">Goalpara</option>

											<option value="Gobichettipalayam">Gobichettipalayam</option>

											<option value="Gobindgarh">Gobindgarh</option>

											<option value="Godhra">Godhra</option>

											<option value="Gohana">Gohana</option>

											<option value="Gokak">Gokak</option>

											<option value="Gooty">Gooty</option>

											<option value="Gopalganj">Gopalganj</option>

											<option value="Greater Mumbai">Greater Mumbai</option>

											<option value="Gudivada">Gudivada</option>

											<option value="Gudur">Gudur</option>

											<option value="Gumia">Gumia</option>

											<option value="Guntakal">Guntakal</option>

											<option value="Guntur">Guntur</option>

											<option value="Gurdaspur">Gurdaspur</option>

											<option value="Gurgaon">Gurgaon</option>

											<option value="Guruvayoor">Guruvayoor</option>

											<option value="Guwahati">Guwahati</option>

											<option value="Gwalior">Gwalior</option>

											<option value="Habra">Habra</option>

											<option value="Hajipur">Hajipur</option>

											<option value="Haldwani-cum-Kathgodam">Haldwani-cum-Kathgodam</option>

											<option value="Hansi">Hansi</option>

											<option value="Hapur">Hapur</option>

											<option value="Hardoi">Hardoi</option>

											<option value="Hardwar">Hardwar</option>

											<option value="Hazaribag">Hazaribag</option>

											<option value="Hindupur">Hindupur</option>

											<option value="Hisar">Hisar</option>

											<option value="Hoshiarpur">Hoshiarpur</option>

											<option value="Hubli-Dharwad">Hubli-Dharwad</option>

											<option value="Hugli-Chinsurah">Hugli-Chinsurah</option>

											<option value="Hyderabad">Hyderabad</option>

											<option value="Ichalkaranji">Ichalkaranji</option>

											<option value="Imphal">Imphal</option>

											<option value="Indore">Indore</option>

											<option value="Itarsi">Itarsi</option>

											<option value="Jabalpur">Jabalpur</option>

											<option value="Jagdalpur">Jagdalpur</option>

											<option value="Jaggaiahpet">Jaggaiahpet</option>

											<option value="Jagraon">Jagraon</option>

											<option value="Jagtial">Jagtial</option>

											<option value="Jaipur">Jaipur</option>

											<option value="Jalandhar">Jalandhar</option>

											<option value="Jalandhar Cantt.">Jalandhar Cantt.</option>

											<option value="Jalpaiguri">Jalpaiguri</option>

											<option value="Jamalpur">Jamalpur</option>

											<option value="Jammalamadugu">Jammalamadugu</option>

											<option value="Jammu">Jammu</option>

											<option value="Jamnagar">Jamnagar</option>

											<option value="Jamshedpur">Jamshedpur</option>

											<option value="Jamui">Jamui</option>

											<option value="Jangaon">Jangaon</option>

											<option value="Jatani">Jatani</option>

											<option value="Jehanabad">Jehanabad</option>

											<option value="Jhansi">Jhansi</option>

											<option value="Jhargram">Jhargram</option>

											<option value="Jharsuguda">Jharsuguda</option>

											<option value="Jhumri Tilaiya">Jhumri Tilaiya</option>

											<option value="Jind">Jind</option>

											<option value="Jodhpur">Jodhpur</option>

											<option value="Jorhat">Jorhat</option>

											<option value="Kadapa">Kadapa</option>

											<option value="Kadi">Kadi</option>

											<option value="Kadiri">Kadiri</option>

											<option value="Kagaznagar">Kagaznagar</option>

											<option value="Kailasahar">Kailasahar</option>

											<option value="Kaithal">Kaithal</option>

											<option value="Kakinada">Kakinada</option>

											<option value="Kalimpong">Kalimpong</option>

											<option value="Kalpi">Kalpi</option>

											<option value="Kalyan-Dombivali">Kalyan-Dombivali</option>

											<option value="Kamareddy">Kamareddy</option>

											<option value="Kancheepuram">Kancheepuram</option>

											<option value="Kandukur">Kandukur</option>

											<option value="Kanhangad">Kanhangad</option>

											<option value="Kannur">Kannur</option>

											<option value="Kanpur">Kanpur</option>

											<option value="Kapadvanj">Kapadvanj</option>

											<option value="Kapurthala">Kapurthala</option>

											<option value="Karaikal">Karaikal</option>

											<option value="Karimganj">Karimganj</option>

											<option value="Karimnagar">Karimnagar</option>

											<option value="Karjat">Karjat</option>

											<option value="Karnal">Karnal</option>

											<option value="Karur">Karur</option>

											<option value="Karwar">Karwar</option>

											<option value="Kasaragod">Kasaragod</option>

											<option value="Kashipur">Kashipur</option>

											<option value="Kathua">Kathua</option>

											<option value="Katihar">Katihar</option>

											<option value="Kavali">Kavali</option>

											<option value="Kayamkulam">Kayamkulam</option>

											<option value="Kendrapara">Kendrapara</option>

											<option value="Kendujhar">Kendujhar</option>

											<option value="Keshod">Keshod</option>

											<option value="Khair">Khair</option>

											<option value="Khambhat">Khambhat</option>

											<option value="Khammam">Khammam</option>

											<option value="Khanna">Khanna</option>

											<option value="Kharagpur">Kharagpur</option>

											<option value="Kharar">Kharar</option>

											<option value="Khowai">Khowai</option>

											<option value="Kishanganj">Kishanganj</option>

											<option value="Kochi">Kochi</option>

											<option value="Kodungallur">Kodungallur</option>

											<option value="Kohima">Kohima</option>

											<option value="Kolar">Kolar</option>

											<option value="Kolkata">Kolkata</option>

											<option value="Kollam">Kollam</option>

											<option value="Koratla">Koratla</option>

											<option value="Korba">Korba</option>

											<option value="Kot Kapura">Kot Kapura</option>

											<option value="Kothagudem">Kothagudem</option>

											<option value="Kottayam">Kottayam</option>

											<option value="Kovvur">Kovvur</option>

											<option value="Koyilandy">Koyilandy</option>

											<option value="Kozhikode">Kozhikode</option>

											<option value="Kunnamkulam">Kunnamkulam</option>

											<option value="Kurnool">Kurnool</option>

											<option value="Kyathampalle">Kyathampalle</option>

											<option value="Lachhmangarh">Lachhmangarh</option>

											<option value="Ladnu">Ladnu</option>

											<option value="Ladwa">Ladwa</option>

											<option value="Lahar">Lahar</option>

											<option value="Laharpur">Laharpur</option>

											<option value="Lakheri">Lakheri</option>

											<option value="Lakhimpur">Lakhimpur</option>

											<option value="Lakhisarai">Lakhisarai</option>

											<option value="Lakshmeshwar">Lakshmeshwar</option>

											<option value="Lal Gopalganj Nindaura">Lal Gopalganj
												Nindaura</option>

											<option value="Lalganj">Lalganj</option>

											<option value="Lalgudi">Lalgudi</option>

											<option value="Lalitpur">Lalitpur</option>

											<option value="Lalsot">Lalsot</option>

											<option value="Lanka">Lanka</option>

											<option value="Lar">Lar</option>

											<option value="Lathi">Lathi</option>

											<option value="Latur">Latur</option>

											<option value="Lilong">Lilong</option>

											<option value="Limbdi">Limbdi</option>

											<option value="Lingsugur">Lingsugur</option>

											<option value="Loha">Loha</option>

											<option value="Lohardaga">Lohardaga</option>

											<option value="Lonar">Lonar</option>

											<option value="Lonavla">Lonavla</option>

											<option value="Longowal">Longowal</option>

											<option value="Loni">Loni</option>

											<option value="Losal">Losal</option>

											<option value="Lucknow">Lucknow</option>

											<option value="Ludhiana">Ludhiana</option>

											<option value="Lumding">Lumding</option>

											<option value="Lunawada">Lunawada</option>

											<option value="Lunglei">Lunglei</option>

											<option value="Macherla">Macherla</option>

											<option value="Machilipatnam">Machilipatnam</option>

											<option value="Madanapalle">Madanapalle</option>

											<option value="Maddur">Maddur</option>

											<option value="Madhepura">Madhepura</option>

											<option value="Madhubani">Madhubani</option>

											<option value="Madhugiri">Madhugiri</option>

											<option value="Madhupur">Madhupur</option>

											<option value="Madikeri">Madikeri</option>

											<option value="Madurai">Madurai</option>

											<option value="Magadi">Magadi</option>

											<option value="Mahad">Mahad</option>

											<option value="Mahalingapura">Mahalingapura</option>

											<option value="Maharajganj">Maharajganj</option>

											<option value="Maharajpur">Maharajpur</option>

											<option value="Mahasamund">Mahasamund</option>

											<option value="Mahbubnagar">Mahbubnagar</option>

											<option value="Mahe">Mahe</option>

											<option value="Mahemdabad">Mahemdabad</option>

											<option value="Mahendragarh">Mahendragarh</option>

											<option value="Mahesana">Mahesana</option>

											<option value="Mahidpur">Mahidpur</option>

											<option value="Mahnar Bazar">Mahnar Bazar</option>

											<option value="Mahuva">Mahuva</option>

											<option value="Maihar">Maihar</option>

											<option value="Mainaguri">Mainaguri</option>

											<option value="Makhdumpur">Makhdumpur</option>

											<option value="Makrana">Makrana</option>

											<option value="Malaj Khand">Malaj Khand</option>

											<option value="Malappuram">Malappuram</option>

											<option value="Malavalli">Malavalli</option>

											<option value="Malda">Malda</option>

											<option value="Malegaon">Malegaon</option>

											<option value="Malerkotla">Malerkotla</option>

											<option value="Malkangiri">Malkangiri</option>

											<option value="Malkapur">Malkapur</option>

											<option value="Malout">Malout</option>

											<option value="Malpura">Malpura</option>

											<option value="Malur">Malur</option>

											<option value="Manachanallur">Manachanallur</option>

											<option value="Manasa">Manasa</option>

											<option value="Manavadar">Manavadar</option>

											<option value="Manawar">Manawar</option>

											<option value="Mancherial">Mancherial</option>

											<option value="Mandalgarh">Mandalgarh</option>

											<option value="Mandamarri">Mandamarri</option>

											<option value="Mandapeta">Mandapeta</option>

											<option value="Mandawa">Mandawa</option>

											<option value="Mandi">Mandi</option>

											<option value="Mandi Dabwali">Mandi Dabwali</option>

											<option value="Mandideep">Mandideep</option>

											<option value="Mandla">Mandla</option>

											<option value="Mandsaur">Mandsaur</option>

											<option value="Mandvi">Mandvi</option>

											<option value="Mandya">Mandya</option>

											<option value="Manendragarh">Manendragarh</option>

											<option value="Maner">Maner</option>

											<option value="Mangaldoi">Mangaldoi</option>

											<option value="Mangaluru">Mangaluru</option>

											<option value="Mangalvedhe">Mangalvedhe</option>

											<option value="Manglaur">Manglaur</option>

											<option value="Mangrol">Mangrol</option>

											<option value="Mangrulpir">Mangrulpir</option>

											<option value="Manihari">Manihari</option>

											<option value="Manjlegaon">Manjlegaon</option>

											<option value="Mankachar">Mankachar</option>

											<option value="Manmad">Manmad</option>

											<option value="Mansa">Mansa</option>

											<option value="Manuguru">Manuguru</option>

											<option value="Manvi">Manvi</option>

											<option value="Manwath">Manwath</option>

											<option value="Mapusa">Mapusa</option>

											<option value="Margao">Margao</option>

											<option value="Margherita">Margherita</option>

											<option value="Marhaura">Marhaura</option>

											<option value="Mariani">Mariani</option>

											<option value="Marigaon">Marigaon</option>

											<option value="Markapur">Markapur</option>

											<option value="Marmagao">Marmagao</option>

											<option value="Masaurhi">Masaurhi</option>

											<option value="Mathabhanga">Mathabhanga</option>

											<option value="Mathura">Mathura</option>

											<option value="Mattannur">Mattannur</option>

											<option value="Mauganj">Mauganj</option>

											<option value="Mavelikkara">Mavelikkara</option>

											<option value="Mavoor">Mavoor</option>

											<option value="Mayang Imphal">Mayang Imphal</option>

											<option value="Medak">Medak</option>

											<option value="Medininagar (Daltonganj)">Medininagar
												(Daltonganj)</option>

											<option value="Medinipur">Medinipur</option>

											<option value="Meerut">Meerut</option>

											<option value="Mehkar">Mehkar</option>

											<option value="Memari">Memari</option>

											<option value="Merta City">Merta City</option>

											<option value="Mhaswad">Mhaswad</option>

											<option value="Mhow Cantonment">Mhow Cantonment</option>

											<option value="Mhowgaon">Mhowgaon</option>

											<option value="Mihijam">Mihijam</option>

											<option value="Mira-Bhayandar">Mira-Bhayandar</option>

											<option value="Mirganj">Mirganj</option>

											<option value="Miryalaguda">Miryalaguda</option>

											<option value="Modasa">Modasa</option>

											<option value="Modinagar">Modinagar</option>

											<option value="Moga">Moga</option>

											<option value="Mohali">Mohali</option>

											<option value="Mokameh">Mokameh</option>

											<option value="Mokokchung">Mokokchung</option>

											<option value="Monoharpur">Monoharpur</option>

											<option value="Moradabad">Moradabad</option>

											<option value="Morena">Morena</option>

											<option value="Morinda, India">Morinda, India</option>

											<option value="Morshi">Morshi</option>

											<option value="Morvi">Morvi</option>

											<option value="Motihari">Motihari</option>

											<option value="Motipur">Motipur</option>

											<option value="Mount Abu">Mount Abu</option>

											<option value="Mudabidri">Mudabidri</option>

											<option value="Mudalagi">Mudalagi</option>

											<option value="Muddebihal">Muddebihal</option>

											<option value="Mudhol">Mudhol</option>

											<option value="Mukerian">Mukerian</option>

											<option value="Mukhed">Mukhed</option>

											<option value="Muktsar">Muktsar</option>

											<option value="Mul">Mul</option>

											<option value="Mulbagal">Mulbagal</option>

											<option value="Multai">Multai</option>

											<option value="Mundargi">Mundargi</option>

											<option value="Mundi">Mundi</option>

											<option value="Mungeli">Mungeli</option>

											<option value="Munger">Munger</option>

											<option value="Murliganj">Murliganj</option>

											<option value="Murshidabad">Murshidabad</option>

											<option value="Murtijapur">Murtijapur</option>

											<option value="Murwara (Katni)">Murwara (Katni)</option>

											<option value="Musabani">Musabani</option>

											<option value="Mussoorie">Mussoorie</option>

											<option value="Muvattupuzha">Muvattupuzha</option>

											<option value="Muzaffarpur">Muzaffarpur</option>

											<option value="Mysore">Mysore</option>

											<option value="Nabadwip">Nabadwip</option>

											<option value="Nabarangapur">Nabarangapur</option>

											<option value="Nabha">Nabha</option>

											<option value="Nadbai">Nadbai</option>

											<option value="Nadiad">Nadiad</option>

											<option value="Nagaon">Nagaon</option>

											<option value="Nagapattinam">Nagapattinam</option>

											<option value="Nagar">Nagar</option>

											<option value="Nagari">Nagari</option>

											<option value="Nagarkurnool">Nagarkurnool</option>

											<option value="Nagaur">Nagaur</option>

											<option value="Nagda">Nagda</option>

											<option value="Nagercoil">Nagercoil</option>

											<option value="Nagina">Nagina</option>

											<option value="Nagla">Nagla</option>

											<option value="Nagpur">Nagpur</option>

											<option value="Nahan">Nahan</option>

											<option value="Naharlagun">Naharlagun</option>

											<option value="Naidupet">Naidupet</option>

											<option value="Naihati">Naihati</option>

											<option value="Naila Janjgir">Naila Janjgir</option>

											<option value="Nainital">Nainital</option>

											<option value="Nainpur">Nainpur</option>

											<option value="Najibabad">Najibabad</option>

											<option value="Nakodar">Nakodar</option>

											<option value="Nakur">Nakur</option>

											<option value="Nalbari">Nalbari</option>

											<option value="Namagiripettai">Namagiripettai</option>

											<option value="Namakkal">Namakkal</option>

											<option value="Nanded-Waghala">Nanded-Waghala</option>

											<option value="Nandgaon">Nandgaon</option>

											<option value="Nandivaram-Guduvancheri">Nandivaram-Guduvancheri</option>

											<option value="Nandura">Nandura</option>

											<option value="Nandurbar">Nandurbar</option>

											<option value="Nandyal">Nandyal</option>

											<option value="Nangal">Nangal</option>

											<option value="Nanjangud">Nanjangud</option>

											<option value="Nanjikottai">Nanjikottai</option>

											<option value="Nanpara">Nanpara</option>

											<option value="Narasapuram">Narasapuram</option>

											<option value="Narasaraopet">Narasaraopet</option>

											<option value="Naraura">Naraura</option>

											<option value="Narayanpet">Narayanpet</option>

											<option value="Nargund">Nargund</option>

											<option value="Narkatiaganj">Narkatiaganj</option>

											<option value="Narkhed">Narkhed</option>

											<option value="Narnaul">Narnaul</option>

											<option value="Narsinghgarh">Narsinghgarh</option>

											<option value="Narsipatnam">Narsipatnam</option>

											<option value="Narwana">Narwana</option>

											<option value="Nashik">Nashik</option>

											<option value="Nasirabad">Nasirabad</option>

											<option value="Natham">Natham</option>

											<option value="Nathdwara">Nathdwara</option>

											<option value="Naugachhia">Naugachhia</option>

											<option value="Naugawan Sadat">Naugawan Sadat</option>

											<option value="Nautanwa">Nautanwa</option>

											<option value="Navalgund">Navalgund</option>

											<option value="Navi Mumbai">Navi Mumbai</option>

											<option value="Navsari">Navsari</option>

											<option value="Nawabganj">Nawabganj</option>

											<option value="Nawada">Nawada</option>

											<option value="Nawanshahr">Nawanshahr</option>

											<option value="Nawapur">Nawapur</option>

											<option value="Nedumangad">Nedumangad</option>

											<option value="Neem-Ka-Thana">Neem-Ka-Thana</option>

											<option value="Neemuch">Neemuch</option>

											<option value="Nehtaur">Nehtaur</option>

											<option value="Nelamangala">Nelamangala</option>

											<option value="Nellikuppam">Nellikuppam</option>

											<option value="Nellore">Nellore</option>

											<option value="Nepanagar">Nepanagar</option>

											<option value="New Delhi">New Delhi</option>

											<option value="Neyveli (TS)">Neyveli (TS)</option>

											<option value="Neyyattinkara">Neyyattinkara</option>

											<option value="Nidadavole">Nidadavole</option>

											<option value="Nilambur">Nilambur</option>

											<option value="Nilanga">Nilanga</option>

											<option value="Nimbahera">Nimbahera</option>

											<option value="Nirmal">Nirmal</option>

											<option value="Niwai">Niwai</option>

											<option value="Niwari">Niwari</option>

											<option value="Nizamabad">Nizamabad</option>

											<option value="Nohar">Nohar</option>

											<option value="Noida">Noida</option>

											<option value="Nokha">Nokha</option>

											<option value="Nongstoin">Nongstoin</option>

											<option value="Noorpur">Noorpur</option>

											<option value="North Lakhimpur">North Lakhimpur</option>

											<option value="Nowgong">Nowgong</option>

											<option value="Nowrozabad (Khodargama)">Nowrozabad
												(Khodargama)</option>

											<option value="Nuzvid">Nuzvid</option>

											<option value="O' Valley">O' Valley</option>

											<option value="Obra">Obra</option>

											<option value="Oddanchatram">Oddanchatram</option>

											<option value="Ongole">Ongole</option>

											<option value="Orai">Orai</option>

											<option value="Osmanabad">Osmanabad</option>

											<option value="Ottappalam">Ottappalam</option>

											<option value="Ozar">Ozar</option>

											<option value="P.N.Patti">P.N.Patti</option>

											<option value="Pachora">Pachora</option>

											<option value="Pachore">Pachore</option>

											<option value="Pacode">Pacode</option>

											<option value="Padmanabhapuram">Padmanabhapuram</option>

											<option value="Padra">Padra</option>

											<option value="Padrauna">Padrauna</option>

											<option value="Paithan">Paithan</option>

											<option value="Pakaur">Pakaur</option>

											<option value="Palacole">Palacole</option>

											<option value="Palai">Palai</option>

											<option value="Palakkad">Palakkad</option>

											<option value="Palampur">Palampur</option>

											<option value="Palani">Palani</option>

											<option value="Palanpur">Palanpur</option>

											<option value="Palasa Kasibugga">Palasa Kasibugga</option>

											<option value="Palghar">Palghar</option>

											<option value="Pali">Pali</option>

											<option value="Palia Kalan">Palia Kalan</option>

											<option value="Palitana">Palitana</option>

											<option value="Palladam">Palladam</option>

											<option value="Pallapatti">Pallapatti</option>

											<option value="Pallikonda">Pallikonda</option>

											<option value="Palwal">Palwal</option>

											<option value="Palwancha">Palwancha</option>

											<option value="Panagar">Panagar</option>

											<option value="Panagudi">Panagudi</option>

											<option value="Panaji">Panaji</option>

											<option value="Panamattom">Panamattom</option>

											<option value="Panchkula">Panchkula</option>

											<option value="Panchla">Panchla</option>

											<option value="Pandharkaoda">Pandharkaoda</option>

											<option value="Pandharpur">Pandharpur</option>

											<option value="Pandhurna">Pandhurna</option>

											<option value="Pandua">Pandua</option>

											<option value="Panipat">Panipat</option>

											<option value="Panna">Panna</option>

											<option value="Panniyannur">Panniyannur</option>

											<option value="Panruti">Panruti</option>

											<option value="Panvel">Panvel</option>

											<option value="Pappinisseri">Pappinisseri</option>

											<option value="Paradip">Paradip</option>

											<option value="Paramakudi">Paramakudi</option>

											<option value="Parangipettai">Parangipettai</option>

											<option value="Parasi">Parasi</option>

											<option value="Paravoor">Paravoor</option>

											<option value="Parbhani">Parbhani</option>

											<option value="Pardi">Pardi</option>

											<option value="Parlakhemundi">Parlakhemundi</option>

											<option value="Parli">Parli</option>

											<option value="Partur">Partur</option>

											<option value="Parvathipuram">Parvathipuram</option>

											<option value="Pasan">Pasan</option>

											<option value="Paschim Punropara">Paschim Punropara</option>

											<option value="Pasighat">Pasighat</option>

											<option value="Patan">Patan</option>

											<option value="Pathanamthitta">Pathanamthitta</option>

											<option value="Pathankot">Pathankot</option>

											<option value="Pathardi">Pathardi</option>

											<option value="Pathri">Pathri</option>

											<option value="Patiala">Patiala</option>

											<option value="Patna">Patna</option>

											<option value="Patratu">Patratu</option>

											<option value="Pattamundai">Pattamundai</option>

											<option value="Patti">Patti</option>

											<option value="Pattran">Pattran</option>

											<option value="Pattukkottai">Pattukkottai</option>

											<option value="Patur">Patur</option>

											<option value="Pauni">Pauni</option>

											<option value="Pauri">Pauri</option>

											<option value="Pavagada">Pavagada</option>

											<option value="Pedana">Pedana</option>

											<option value="Peddapuram">Peddapuram</option>

											<option value="Pehowa">Pehowa</option>

											<option value="Pen">Pen</option>

											<option value="Perambalur">Perambalur</option>

											<option value="Peravurani">Peravurani</option>

											<option value="Peringathur">Peringathur</option>

											<option value="Perinthalmanna">Perinthalmanna</option>

											<option value="Periyakulam">Periyakulam</option>

											<option value="Periyasemur">Periyasemur</option>

											<option value="Pernampattu">Pernampattu</option>

											<option value="Perumbavoor">Perumbavoor</option>

											<option value="Petlad">Petlad</option>

											<option value="Phagwara">Phagwara</option>

											<option value="Phalodi">Phalodi</option>

											<option value="Phaltan">Phaltan</option>

											<option value="Phillaur">Phillaur</option>

											<option value="Phulabani">Phulabani</option>

											<option value="Phulera">Phulera</option>

											<option value="Phulpur">Phulpur</option>

											<option value="Phusro">Phusro</option>

											<option value="Pihani">Pihani</option>

											<option value="Pilani">Pilani</option>

											<option value="Pilibanga">Pilibanga</option>

											<option value="Pilibhit">Pilibhit</option>

											<option value="Pilkhuwa">Pilkhuwa</option>

											<option value="Pindwara">Pindwara</option>

											<option value="Pinjore">Pinjore</option>

											<option value="Pipar City">Pipar City</option>

											<option value="Pipariya">Pipariya</option>

											<option value="Piriyapatna">Piriyapatna</option>

											<option value="Piro">Piro</option>

											<option value="Pithampur">Pithampur</option>

											<option value="Pithapuram">Pithapuram</option>

											<option value="Pithoragarh">Pithoragarh</option>

											<option value="Pollachi">Pollachi</option>

											<option value="Polur">Polur</option>

											<option value="Pondicherry">Pondicherry</option>

											<option value="Ponnani">Ponnani</option>

											<option value="Ponneri">Ponneri</option>

											<option value="Ponnur">Ponnur</option>

											<option value="Porbandar">Porbandar</option>

											<option value="Porsa">Porsa</option>

											<option value="Port Blair">Port Blair</option>

											<option value="Powayan">Powayan</option>

											<option value="Prantij">Prantij</option>

											<option value="Pratapgarh">Pratapgarh</option>

											<option value="Prithvipur">Prithvipur</option>

											<option value="Proddatur">Proddatur</option>

											<option value="Pudukkottai">Pudukkottai</option>

											<option value="Pudupattinam">Pudupattinam</option>

											<option value="Pukhrayan">Pukhrayan</option>

											<option value="Pulgaon">Pulgaon</option>

											<option value="Puliyankudi">Puliyankudi</option>

											<option value="Punalur">Punalur</option>

											<option value="Punch">Punch</option>

											<option value="Pune">Pune</option>

											<option value="Punganur">Punganur</option>

											<option value="Punjaipugalur">Punjaipugalur</option>

											<option value="Puranpur">Puranpur</option>

											<option value="Puri">Puri</option>

											<option value="Purna">Purna</option>

											<option value="Purnia">Purnia</option>

											<option value="Purquazi">Purquazi</option>

											<option value="Purulia">Purulia</option>

											<option value="Purwa">Purwa</option>

											<option value="Pusad">Pusad</option>

											<option value="Puthuppally">Puthuppally</option>

											<option value="Puttur">Puttur</option>

											<option value="Qadian">Qadian</option>

											<option value="Raayachuru">Raayachuru</option>

											<option value="Rabkavi Banhatti">Rabkavi Banhatti</option>

											<option value="Radhanpur">Radhanpur</option>

											<option value="Rae Bareli">Rae Bareli</option>

											<option value="Rafiganj">Rafiganj</option>

											<option value="Raghogarh-Vijaypur">Raghogarh-Vijaypur</option>

											<option value="Raghunathganj">Raghunathganj</option>

											<option value="Raghunathpur">Raghunathpur</option>

											<option value="Rahatgarh">Rahatgarh</option>

											<option value="Rahuri">Rahuri</option>

											<option value="Raiganj">Raiganj</option>

											<option value="Raigarh">Raigarh</option>

											<option value="Raikot">Raikot</option>

											<option value="Raipur">Raipur</option>

											<option value="Rairangpur">Rairangpur</option>

											<option value="Raisen">Raisen</option>

											<option value="Raisinghnagar">Raisinghnagar</option>

											<option value="Rajagangapur">Rajagangapur</option>

											<option value="Rajahmundry">Rajahmundry</option>

											<option value="Rajakhera">Rajakhera</option>

											<option value="Rajaldesar">Rajaldesar</option>

											<option value="Rajam">Rajam</option>

											<option value="Rajampet">Rajampet</option>

											<option value="Rajapalayam">Rajapalayam</option>

											<option value="Rajauri">Rajauri</option>

											<option value="Rajgarh">Rajgarh</option>

											<option value="Rajgarh (Alwar)">Rajgarh (Alwar)</option>

											<option value="Rajgarh (Churu)">Rajgarh (Churu)</option>

											<option value="Rajgir">Rajgir</option>

											<option value="Rajkot">Rajkot</option>

											<option value="Rajnandgaon">Rajnandgaon</option>

											<option value="Rajpipla">Rajpipla</option>

											<option value="Rajpura">Rajpura</option>

											<option value="Rajsamand">Rajsamand</option>

											<option value="Rajula">Rajula</option>

											<option value="Rajura">Rajura</option>

											<option value="Ramachandrapuram">Ramachandrapuram</option>

											<option value="Ramagundam">Ramagundam</option>

											<option value="Ramanagaram">Ramanagaram</option>

											<option value="Ramanathapuram">Ramanathapuram</option>

											<option value="Ramdurg">Ramdurg</option>

											<option value="Rameshwaram">Rameshwaram</option>

											<option value="Ramganj Mandi">Ramganj Mandi</option>

											<option value="Ramgarh">Ramgarh</option>

											<option value="Ramnagar">Ramnagar</option>

											<option value="Ramngarh">Ramngarh</option>

											<option value="Rampur">Rampur</option>

											<option value="Rampur Maniharan">Rampur Maniharan</option>

											<option value="Rampura Phul">Rampura Phul</option>

											<option value="Rampurhat">Rampurhat</option>

											<option value="Ramtek">Ramtek</option>

											<option value="Ranaghat">Ranaghat</option>

											<option value="Ranavav">Ranavav</option>

											<option value="Ranchi">Ranchi</option>

											<option value="Ranebennuru">Ranebennuru</option>

											<option value="Rangia">Rangia</option>

											<option value="Rania">Rania</option>

											<option value="Ranibennur">Ranibennur</option>

											<option value="Ranipet">Ranipet</option>

											<option value="Rapar">Rapar</option>

											<option value="Rasipuram">Rasipuram</option>

											<option value="Rasra">Rasra</option>

											<option value="Ratangarh">Ratangarh</option>

											<option value="Rath">Rath</option>

											<option value="Ratia">Ratia</option>

											<option value="Ratlam">Ratlam</option>

											<option value="Ratnagiri">Ratnagiri</option>

											<option value="Rau">Rau</option>

											<option value="Raurkela">Raurkela</option>

											<option value="Raver">Raver</option>

											<option value="Rawatbhata">Rawatbhata</option>

											<option value="Rawatsar">Rawatsar</option>

											<option value="Raxaul Bazar">Raxaul Bazar</option>

											<option value="Rayachoti">Rayachoti</option>

											<option value="Rayadurg">Rayadurg</option>

											<option value="Rayagada">Rayagada</option>

											<option value="Reengus">Reengus</option>

											<option value="Rehli">Rehli</option>

											<option value="Renigunta">Renigunta</option>

											<option value="Renukoot">Renukoot</option>

											<option value="Reoti">Reoti</option>

											<option value="Repalle">Repalle</option>

											<option value="Revelganj">Revelganj</option>

											<option value="Rewa">Rewa</option>

											<option value="Rewari">Rewari</option>

											<option value="Rishikesh">Rishikesh</option>

											<option value="Risod">Risod</option>

											<option value="Robertsganj">Robertsganj</option>

											<option value="Robertson Pet">Robertson Pet</option>

											<option value="Rohtak">Rohtak</option>

											<option value="Ron">Ron</option>

											<option value="Roorkee">Roorkee</option>

											<option value="Rosera">Rosera</option>

											<option value="Rudauli">Rudauli</option>

											<option value="Rudrapur">Rudrapur</option>

											<option value="Rupnagar">Rupnagar</option>

											<option value="Sabalgarh">Sabalgarh</option>

											<option value="Sadabad">Sadabad</option>

											<option value="Sadalagi">Sadalagi</option>

											<option value="Sadasivpet">Sadasivpet</option>

											<option value="Sadri">Sadri</option>

											<option value="Sadulpur">Sadulpur</option>

											<option value="Sadulshahar">Sadulshahar</option>

											<option value="Safidon">Safidon</option>

											<option value="Safipur">Safipur</option>

											<option value="Sagar">Sagar</option>

											<option value="Sagara">Sagara</option>

											<option value="Sagwara">Sagwara</option>

											<option value="Saharanpur">Saharanpur</option>

											<option value="Saharsa">Saharsa</option>

											<option value="Sahaspur">Sahaspur</option>

											<option value="Sahaswan">Sahaswan</option>

											<option value="Sahawar">Sahawar</option>

											<option value="Sahibganj">Sahibganj</option>

											<option value="Sahjanwa">Sahjanwa</option>

											<option value="Saidpur">Saidpur</option>

											<option value="Saiha">Saiha</option>

											<option value="Sailu">Sailu</option>

											<option value="Sainthia">Sainthia</option>

											<option value="Sakaleshapura">Sakaleshapura</option>

											<option value="Sakti">Sakti</option>

											<option value="Salaya">Salaya</option>

											<option value="Salem">Salem</option>

											<option value="Salur">Salur</option>

											<option value="Samalkha">Samalkha</option>

											<option value="Samalkot">Samalkot</option>

											<option value="Samana">Samana</option>

											<option value="Samastipur">Samastipur</option>

											<option value="Sambalpur">Sambalpur</option>

											<option value="Sambhal">Sambhal</option>

											<option value="Sambhar">Sambhar</option>

											<option value="Samdhan">Samdhan</option>

											<option value="Samthar">Samthar</option>

											<option value="Sanand">Sanand</option>

											<option value="Sanawad">Sanawad</option>

											<option value="Sanchore">Sanchore</option>

											<option value="Sandi">Sandi</option>

											<option value="Sandila">Sandila</option>

											<option value="Sanduru">Sanduru</option>

											<option value="Sangamner">Sangamner</option>

											<option value="Sangareddy">Sangareddy</option>

											<option value="Sangaria">Sangaria</option>

											<option value="Sangli">Sangli</option>

											<option value="Sangole">Sangole</option>

											<option value="Sangrur">Sangrur</option>

											<option value="Sankarankoil">Sankarankoil</option>

											<option value="Sankari">Sankari</option>

											<option value="Sankeshwara">Sankeshwara</option>

											<option value="Santipur">Santipur</option>

											<option value="Sarangpur">Sarangpur</option>

											<option value="Sardarshahar">Sardarshahar</option>

											<option value="Sardhana">Sardhana</option>

											<option value="Sarni">Sarni</option>

											<option value="Sarsod">Sarsod</option>

											<option value="Sasaram">Sasaram</option>

											<option value="Sasvad">Sasvad</option>

											<option value="Satana">Satana</option>

											<option value="Satara">Satara</option>

											<option value="Sathyamangalam">Sathyamangalam</option>

											<option value="Satna">Satna</option>

											<option value="Sattenapalle">Sattenapalle</option>

											<option value="Sattur">Sattur</option>

											<option value="Saunda">Saunda</option>

											<option value="Saundatti-Yellamma">Saundatti-Yellamma</option>

											<option value="Sausar">Sausar</option>

											<option value="Savanur">Savanur</option>

											<option value="Savarkundla">Savarkundla</option>

											<option value="Savner">Savner</option>

											<option value="Sawai Madhopur">Sawai Madhopur</option>

											<option value="Sawantwadi">Sawantwadi</option>

											<option value="Sedam">Sedam</option>

											<option value="Sehore">Sehore</option>

											<option value="Sendhwa">Sendhwa</option>

											<option value="Seohara">Seohara</option>

											<option value="Seoni">Seoni</option>

											<option value="Seoni-Malwa">Seoni-Malwa</option>

											<option value="Shahabad">Shahabad</option>

											<option value="Shahabad, Hardoi">Shahabad, Hardoi</option>

											<option value="Shahabad, Rampur">Shahabad, Rampur</option>

											<option value="Shahade">Shahade</option>

											<option value="Shahbad">Shahbad</option>

											<option value="Shahdol">Shahdol</option>

											<option value="Shahganj">Shahganj</option>

											<option value="Shahjahanpur">Shahjahanpur</option>

											<option value="Shahpur">Shahpur</option>

											<option value="Shahpura">Shahpura</option>

											<option value="Shajapur">Shajapur</option>

											<option value="Shamgarh">Shamgarh</option>

											<option value="Shamli">Shamli</option>

											<option value="Shamsabad, Agra">Shamsabad, Agra</option>

											<option value="Shamsabad, Farrukhabad">Shamsabad,
												Farrukhabad</option>

											<option value="Shegaon">Shegaon</option>

											<option value="Sheikhpura">Sheikhpura</option>

											<option value="Shendurjana">Shendurjana</option>

											<option value="Shenkottai">Shenkottai</option>

											<option value="Sheoganj">Sheoganj</option>

											<option value="Sheohar">Sheohar</option>

											<option value="Sheopur">Sheopur</option>

											<option value="Sherghati">Sherghati</option>

											<option value="Sherkot">Sherkot</option>

											<option value="Shiggaon">Shiggaon</option>

											<option value="Shikaripur">Shikaripur</option>

											<option value="Shikarpur, Bulandshahr">Shikarpur,
												Bulandshahr</option>

											<option value="Shikohabad">Shikohabad</option>

											<option value="Shillong">Shillong</option>

											<option value="Shimla">Shimla</option>

											<option value="Shirdi">Shirdi</option>

											<option value="Shirpur-Warwade">Shirpur-Warwade</option>

											<option value="Shirur">Shirur</option>

											<option value="Shishgarh">Shishgarh</option>

											<option value="Shivamogga">Shivamogga</option>

											<option value="Shivpuri">Shivpuri</option>

											<option value="Sholavandan">Sholavandan</option>

											<option value="Sholingur">Sholingur</option>

											<option value="Shoranur">Shoranur</option>

											<option value="Shrigonda">Shrigonda</option>

											<option value="Shrirampur">Shrirampur</option>

											<option value="Shrirangapattana">Shrirangapattana</option>

											<option value="Shujalpur">Shujalpur</option>

											<option value="Siana">Siana</option>

											<option value="Sibsagar">Sibsagar</option>

											<option value="Siddipet">Siddipet</option>

											<option value="Sidhi">Sidhi</option>

											<option value="Sidhpur">Sidhpur</option>

											<option value="Sidlaghatta">Sidlaghatta</option>

											<option value="Sihor">Sihor</option>

											<option value="Sihora">Sihora</option>

											<option value="Sikanderpur">Sikanderpur</option>

											<option value="Sikandra Rao">Sikandra Rao</option>

											<option value="Sikandrabad">Sikandrabad</option>

											<option value="Sikar">Sikar</option>

											<option value="Silao">Silao</option>

											<option value="Silapathar">Silapathar</option>

											<option value="Silchar">Silchar</option>

											<option value="Siliguri">Siliguri</option>

											<option value="Sillod">Sillod</option>

											<option value="Silvassa">Silvassa</option>

											<option value="Simdega">Simdega</option>

											<option value="Sindagi">Sindagi</option>

											<option value="Sindhagi">Sindhagi</option>

											<option value="Sindhnur">Sindhnur</option>

											<option value="Singrauli">Singrauli</option>

											<option value="Sinnar">Sinnar</option>

											<option value="Sira">Sira</option>

											<option value="Sircilla">Sircilla</option>

											<option value="Sirhind Fatehgarh Sahib">Sirhind
												Fatehgarh Sahib</option>

											<option value="Sirkali">Sirkali</option>

											<option value="Sirohi">Sirohi</option>

											<option value="Sironj">Sironj</option>

											<option value="Sirsa">Sirsa</option>

											<option value="Sirsaganj">Sirsaganj</option>

											<option value="Sirsi">Sirsi</option>

											<option value="Siruguppa">Siruguppa</option>

											<option value="Sitamarhi">Sitamarhi</option>

											<option value="Sitapur">Sitapur</option>

											<option value="Sitarganj">Sitarganj</option>

											<option value="Sivaganga">Sivaganga</option>

											<option value="Sivagiri">Sivagiri</option>

											<option value="Sivakasi">Sivakasi</option>

											<option value="Siwan">Siwan</option>

											<option value="Sohagpur">Sohagpur</option>

											<option value="Sohna">Sohna</option>

											<option value="Sojat">Sojat</option>

											<option value="Solan">Solan</option>

											<option value="Solapur">Solapur</option>

											<option value="Sonamukhi">Sonamukhi</option>

											<option value="Sonepur">Sonepur</option>

											<option value="Songadh">Songadh</option>

											<option value="Sonipat">Sonipat</option>

											<option value="Sopore">Sopore</option>

											<option value="Soro">Soro</option>

											<option value="Soron">Soron</option>

											<option value="Soyagaon">Soyagaon</option>

											<option value="Sri Madhopur">Sri Madhopur</option>

											<option value="Srikakulam">Srikakulam</option>

											<option value="Srikalahasti">Srikalahasti</option>

											<option value="Srinagar">Srinagar</option>

											<option value="Srinagar*">Srinagar*</option>

											<option value="Srinivaspur">Srinivaspur</option>

											<option value="Srirampore">Srirampore</option>

											<option
												value="Srisailam Project (Right Flank Colony) Township">Srisailam
												Project (Right Flank Colony) Township</option>

											<option value="Srivilliputhur">Srivilliputhur</option>

											<option value="Suar">Suar</option>

											<option value="Sugauli">Sugauli</option>

											<option value="Sujangarh">Sujangarh</option>

											<option value="Sujanpur">Sujanpur</option>

											<option value="Sullurpeta">Sullurpeta</option>

											<option value="Sultanganj">Sultanganj</option>

											<option value="Sultanpur">Sultanpur</option>

											<option value="Sumerpur">Sumerpur</option>

											<option value="Sunabeda">Sunabeda</option>

											<option value="Sunam">Sunam</option>

											<option value="Sundargarh">Sundargarh</option>

											<option value="Sundarnagar">Sundarnagar</option>

											<option value="Supaul">Supaul</option>

											<option value="Surandai">Surandai</option>

											<option value="Surapura">Surapura</option>

											<option value="Surat">Surat</option>

											<option value="Suratgarh">Suratgarh</option>

											<option value="Suri">Suri</option>

											<option value="Suriyampalayam">Suriyampalayam</option>

											<option value="Suryapet">Suryapet</option>

											<option value="Tadepalligudem">Tadepalligudem</option>

											<option value="Tadpatri">Tadpatri</option>

											<option value="Taki">Taki</option>

											<option value="Talaja">Talaja</option>

											<option value="Talcher">Talcher</option>

											<option value="Talegaon Dabhade">Talegaon Dabhade</option>

											<option value="Talikota">Talikota</option>

											<option value="Taliparamba">Taliparamba</option>

											<option value="Talode">Talode</option>

											<option value="Talwara">Talwara</option>

											<option value="Tamluk">Tamluk</option>

											<option value="Tanda">Tanda</option>

											<option value="Tandur">Tandur</option>

											<option value="Tanuku">Tanuku</option>

											<option value="Tarakeswar">Tarakeswar</option>

											<option value="Tarana">Tarana</option>

											<option value="Taranagar">Taranagar</option>

											<option value="Taraori">Taraori</option>

											<option value="Tarbha">Tarbha</option>

											<option value="Tarikere">Tarikere</option>

											<option value="Tarn Taran">Tarn Taran</option>

											<option value="Tasgaon">Tasgaon</option>

											<option value="Tehri">Tehri</option>

											<option value="Tekkalakote">Tekkalakote</option>

											<option value="Tenali">Tenali</option>

											<option value="Tenkasi">Tenkasi</option>

											<option value="Tenu dam-cum-Kathhara">Tenu
												dam-cum-Kathhara</option>

											<option value="Terdal">Terdal</option>

											<option value="Tezpur">Tezpur</option>

											<option value="Thakurdwara">Thakurdwara</option>

											<option value="Thammampatti">Thammampatti</option>

											<option value="Thana Bhawan">Thana Bhawan</option>

											<option value="Thane">Thane</option>

											<option value="Thanesar">Thanesar</option>

											<option value="Thangadh">Thangadh</option>

											<option value="Thanjavur">Thanjavur</option>

											<option value="Tharad">Tharad</option>

											<option value="Tharamangalam">Tharamangalam</option>

											<option value="Tharangambadi">Tharangambadi</option>

											<option value="Theni Allinagaram">Theni Allinagaram</option>

											<option value="Thirumangalam">Thirumangalam</option>

											<option value="Thirupuvanam">Thirupuvanam</option>

											<option value="Thiruthuraipoondi">Thiruthuraipoondi</option>

											<option value="Thiruvalla">Thiruvalla</option>

											<option value="Thiruvallur">Thiruvallur</option>

											<option value="Thiruvananthapuram">Thiruvananthapuram</option>

											<option value="Thiruvarur">Thiruvarur</option>

											<option value="Thodupuzha">Thodupuzha</option>

											<option value="Thoubal">Thoubal</option>

											<option value="Thrissur">Thrissur</option>

											<option value="Thuraiyur">Thuraiyur</option>

											<option value="Tikamgarh">Tikamgarh</option>

											<option value="Tilda Newra">Tilda Newra</option>

											<option value="Tilhar">Tilhar</option>

											<option value="Tindivanam">Tindivanam</option>

											<option value="Tinsukia">Tinsukia</option>

											<option value="Tiptur">Tiptur</option>

											<option value="Tirora">Tirora</option>

											<option value="Tiruchendur">Tiruchendur</option>

											<option value="Tiruchengode">Tiruchengode</option>

											<option value="Tiruchirappalli">Tiruchirappalli</option>

											<option value="Tirukalukundram">Tirukalukundram</option>

											<option value="Tirukkoyilur">Tirukkoyilur</option>

											<option value="Tirunelveli">Tirunelveli</option>

											<option value="Tirupathur">Tirupathur</option>

											<option value="Tirupati">Tirupati</option>

											<option value="Tiruppur">Tiruppur</option>

											<option value="Tirur">Tirur</option>

											<option value="Tiruttani">Tiruttani</option>

											<option value="Tiruvannamalai">Tiruvannamalai</option>

											<option value="Tiruvethipuram">Tiruvethipuram</option>

											<option value="Tiruvuru">Tiruvuru</option>

											<option value="Tirwaganj">Tirwaganj</option>

											<option value="Titlagarh">Titlagarh</option>

											<option value="Tittakudi">Tittakudi</option>

											<option value="Todabhim">Todabhim</option>

											<option value="Todaraisingh">Todaraisingh</option>

											<option value="Tohana">Tohana</option>

											<option value="Tonk">Tonk</option>

											<option value="Tuensang">Tuensang</option>

											<option value="Tuljapur">Tuljapur</option>

											<option value="Tulsipur">Tulsipur</option>

											<option value="Tumkur">Tumkur</option>

											<option value="Tumsar">Tumsar</option>

											<option value="Tundla">Tundla</option>

											<option value="Tuni">Tuni</option>

											<option value="Tura">Tura</option>

											<option value="Uchgaon">Uchgaon</option>

											<option value="Udaipur">Udaipur</option>

											<option value="Udaipurwati">Udaipurwati</option>

											<option value="Udgir">Udgir</option>

											<option value="Udhagamandalam">Udhagamandalam</option>

											<option value="Udhampur">Udhampur</option>

											<option value="Udumalaipettai">Udumalaipettai</option>

											<option value="Udupi">Udupi</option>

											<option value="Ujhani">Ujhani</option>

											<option value="Ujjain">Ujjain</option>

											<option value="Umarga">Umarga</option>

											<option value="Umaria">Umaria</option>

											<option value="Umarkhed">Umarkhed</option>

											<option value="Umbergaon">Umbergaon</option>

											<option value="Umred">Umred</option>

											<option value="Umreth">Umreth</option>

											<option value="Una">Una</option>

											<option value="Unjha">Unjha</option>

											<option value="Unnamalaikadai">Unnamalaikadai</option>

											<option value="Unnao">Unnao</option>

											<option value="Upleta">Upleta</option>

											<option value="Uran">Uran</option>

											<option value="Uran Islampur">Uran Islampur</option>

											<option value="Uravakonda">Uravakonda</option>

											<option value="Urmar Tanda">Urmar Tanda</option>

											<option value="Usilampatti">Usilampatti</option>

											<option value="Uthamapalayam">Uthamapalayam</option>

											<option value="Uthiramerur">Uthiramerur</option>

											<option value="Utraula">Utraula</option>

											<option value="Vadakkuvalliyur">Vadakkuvalliyur</option>

											<option value="Vadalur">Vadalur</option>

											<option value="Vadgaon Kasba">Vadgaon Kasba</option>

											<option value="Vadipatti">Vadipatti</option>

											<option value="Vadnagar">Vadnagar</option>

											<option value="Vadodara">Vadodara</option>

											<option value="Vaijapur">Vaijapur</option>

											<option value="Vaikom">Vaikom</option>

											<option value="Valparai">Valparai</option>

											<option value="Valsad">Valsad</option>

											<option value="Vandavasi">Vandavasi</option>

											<option value="Vaniyambadi">Vaniyambadi</option>

											<option value="Vapi">Vapi</option>

											<option value="Varanasi">Varanasi</option>

											<option value="Varkala">Varkala</option>

											<option value="Vasai-Virar">Vasai-Virar</option>

											<option value="Vatakara">Vatakara</option>

											<option value="Vedaranyam">Vedaranyam</option>

											<option value="Vellakoil">Vellakoil</option>

											<option value="Vellore">Vellore</option>

											<option value="Venkatagiri">Venkatagiri</option>

											<option value="Veraval">Veraval</option>

											<option value="Vidisha">Vidisha</option>

											<option value="Vijainagar, Ajmer">Vijainagar, Ajmer</option>

											<option value="Vijapur">Vijapur</option>

											<option value="Vijayapura">Vijayapura</option>

											<option value="Vijayawada">Vijayawada</option>

											<option value="Vijaypur">Vijaypur</option>

											<option value="Vikarabad">Vikarabad</option>

											<option value="Vikramasingapuram">Vikramasingapuram</option>

											<option value="Viluppuram">Viluppuram</option>

											<option value="Vinukonda">Vinukonda</option>

											<option value="Viramgam">Viramgam</option>

											<option value="Virudhachalam">Virudhachalam</option>

											<option value="Virudhunagar">Virudhunagar</option>

											<option value="Visakhapatnam">Visakhapatnam</option>

											<option value="Visnagar">Visnagar</option>

											<option value="Viswanatham">Viswanatham</option>

											<option value="Vita">Vita</option>

											<option value="Vizianagaram">Vizianagaram</option>

											<option value="Vrindavan">Vrindavan</option>

											<option value="Vyara">Vyara</option>

											<option value="Wadgaon Road">Wadgaon Road</option>

											<option value="Wadhwan">Wadhwan</option>

											<option value="Wadi">Wadi</option>

											<option value="Wai">Wai</option>

											<option value="Wanaparthy">Wanaparthy</option>

											<option value="Wani">Wani</option>

											<option value="Wankaner">Wankaner</option>

											<option value="Wara Seoni">Wara Seoni</option>

											<option value="Warangal">Warangal</option>

											<option value="Wardha">Wardha</option>

											<option value="Warhapur">Warhapur</option>

											<option value="Warisaliganj">Warisaliganj</option>

											<option value="Warora">Warora</option>

											<option value="Warud">Warud</option>

											<option value="Washim">Washim</option>

											<option value="Wokha">Wokha</option>

											<option value="Yadgir">Yadgir</option>

											<option value="Yamunanagar">Yamunanagar</option>

											<option value="Yanam">Yanam</option>

											<option value="Yavatmal">Yavatmal</option>

											<option value="Yawal">Yawal</option>

											<option value="Yellandu">Yellandu</option>

											<option value="Yemmiganur">Yemmiganur</option>

											<option value="Yerraguntla">Yerraguntla</option>

											<option value="Yevla">Yevla</option>

											<option value="Zaidpur">Zaidpur</option>

											<option value="Zamania">Zamania</option>

											<option value="Zira">Zira</option>

											<option value="Zirakpur">Zirakpur</option>

											<option value="Zunheboto">Zunheboto</option>

										</select>
									</div>
								</div>
								<div class="form-actions">
									<input type="submit" value="Register" class="btn btn-info">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="/travel/theme/dashboard/js/jquery.min.js"></script>
	<script src="/travel/theme/dashboard/js/jquery-ui.custom.js"></script>
	<script src="/travel/theme/dashboard/js/bootstrap.min.js"></script>
	<script src="/travel/theme/dashboard/js/jquery.icheck.min.js"></script>
	<script src="/travel/theme/dashboard/js/select2.min.js"></script>
	<script src="/travel/theme/dashboard/js/jquery.validate.js"></script>

	<script src="/travel/theme/dashboard/js/jquery.nicescroll.min.js"></script>
	<script src="/travel/theme/dashboard/js/unicorn.js"></script>
	<script src="/travel/theme/dashboard/js/unicorn.form_validation.js"></script>
	<script src="/travel/assest/plugin/numeric/numeric.min.js"></script>
</body>
<script type="text/javascript">
    $('.ctPhoneNoTxt').numeric();
</script>