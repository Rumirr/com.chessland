$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

function onSignIn(googleUser) {
	let idToken = googleUser.getAuthResponse().id_token;
	console.log(idToken);

	let request = new XMLHttpRequest();
	request.open('POST', 'Login');
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');
	request.onreadystatechange = function() {
		if (this.status === 200 && this.readyState === 4) {
			window.location.href = this.responseText;
		}
	}
	request.send('token=' + idToken);
}