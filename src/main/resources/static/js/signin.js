console.log('signin.js');

function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail());
  console.log('id_token: ' + googleUser.getAuthResponse().id_token);
  $.ajax({
            type: "POST",
            url: '/user/login/google',
            dataType: 'json',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify({"idToken": googleUser.getAuthResponse().id_token}),
            success: function () {
                alert("Thanks!");
            }
        });
}


function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }