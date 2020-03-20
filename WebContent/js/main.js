$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

function onSignIn(googleUser) {
	let idToken = googleUser.getAuthResponse().id_token;
	console.log(idToken);
	let request = new XMLHttpRequest();
	request.open('POST', 'Login');
	request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request.send('token=' + idToken);
}