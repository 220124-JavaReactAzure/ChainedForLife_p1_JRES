const unorderedList = document.getElementById('resourceTypes');

fetch('http://localhost:8080/test-app/resourceType')
	.then(response => {
		return response.json();
	})
	.then(resourceTypes => {
		console.log(resourceTypes);
		for (const type of resourceTypes) {
			const myOption = document.createElement('option');
			myOption.value = type.id;
			myOption.textContent = type.name;
			unorderedList.appendChild(myOption);
		}
	});

function myFunction() {
	console.log("beep");
	const formData = new FormData(document.getElementById('registerForm'));

	var json = JSON.stringify(Object.fromEntries(formData));

	console.log(json);

	const responseData = fetch('http://localhost:8080/test-app/resource/register', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: json,
	})
		.then(response => response.json())
		.then(json => {
			console.log('Success:', json);
		});

	console.log(responseData);

}