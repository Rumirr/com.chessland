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

async function changePhoto() {
	const { value: file } = await Swal.fire({
		title: 'Seleccione una foto',
		input: 'file',
		inputAttributes: {
			'accept': 'image/*',
			'aria-label': 'Upload your profile picture'
		}
	})

	if (file) {
		console.log(file);

		let formData = new FormData();
		formData.append('img', file);

		console.log('formData -> ');
		console.log(formData);


		let request = new XMLHttpRequest();
		request.onreadystatechange = function () {
			console.log('Status -> ' + this.status);
			console.log('State -> ' + this.readyState);
			console.log('Response -> ' + this.responseText);
		}
		request.open('POST', 'Perfil', true);
		request.send(formData);

	}

}