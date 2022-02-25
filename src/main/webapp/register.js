
const unorderedList = document.getElementById('userTypes');

fetch('http://localhost:8080/test-app/userType')
	.then(response => {
		return response.json();
	})
	.then(userTypes => {
		console.log(userTypes);
		for(const type of userTypes) {
			const myOption = document.createElement('option');
			myOption.value = type.id;
			myOption.textContent = type.name;
			unorderedList.appendChild(myOption);
		}
	});