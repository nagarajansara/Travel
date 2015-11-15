function ctLoginStatus(loginStatus)
{
	loginStatus = loginStatus ? parseInt(loginStatus) : 0;
	if(loginStatus && loginStatus == -1)
	{
		
		$('.ctErrorSpan').show();
		$('.ctOpenModal').trigger('click');
	}
}
