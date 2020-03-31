$(document).ready(function () {
	$('[data-toggle="tooltip"]').tooltip();
});

function onSignIn(googleUser) {
	let idToken = googleUser.getAuthResponse().id_token;

	var form = $("<form action='Login' method='post'>"
		+ "<input type='text' name='token' value='" + idToken
		+ "'/></form>");
	$('body').append(form);
	form.submit();

	let auth2 = gapi.auth2.getAuthInstance();
	auth2.disconnect();
	auth2.signOut();
}