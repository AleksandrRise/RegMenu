let xhr = new XMLHttpRequest();

function validateForm() {

    let username = document.querySelector(".name2").value;
    let password = document.querySelector(".pass2").value;
    let confPassword = document.querySelector(".confPass2").value;

    if (password === confPassword) {
        doPost(username, password);
    } else {
        alert("You typed two different passwords.");
    }

}


function doPost(username, password) {

    let data = {
        username: username,
        password: password
    }
    let theUrl = "/users/register";

    // Posting values into database
    xhr.open("POST", theUrl);
    xhr.setRequestHeader("Content-Type", "application/json");

    // Handle the response
    xhr.onload = () => {

        // If success, reload the page and show a success message
        if (xhr.status === 201) {

            successMessage();
            reload();

        } else if (xhr.status === 400) {

            failMessage();

        } else {
            console.error("Request failed. Returned status of " + xhr.status);
        }

    };

    xhr.send(JSON.stringify(data));
}


function doGet() {

    let username = document.querySelector(".name1").value;
    let password = document.querySelector(".pass1").value;

    let theUrl = `/users/byname/${username}`;

    // Getting values from database
    xhr.open("GET", theUrl);
    xhr.setRequestHeader("Content-Type", "application/json");

    // Handle the response
    xhr.onload = () => {

        // If success, reload the page and show a success message
        if (xhr.status === 200) {

            const data = JSON.parse(xhr.responseText);

            // checking if entered password matches the one in database
            if (password === data.password) {
                alert("Everything matches! You have entered your account.")
            } else {
                matchingFailMessage();
            }

        } else if (xhr.status === 404) {

            userNotFoundMessage();

        } else {
            console.error("Request failed. Returned status of " + xhr.status);
        }

    };

    xhr.send();
}