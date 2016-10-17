export default class signinController {
  /* @ngInject */
  constructor($scope, $auth, $state) {
    console.log('signin.js');

    this.authenticate = function(provider) {
      console.log('authenticate start');

      $auth.authenticate(provider).then(function(response) {
          // Signed in with Google.
          console.log(response);
          $state.go('sets');
        })
        .catch(function(response) {
          // Something went wrong.
        });
    };

    this.signOut = function(provider) {
      console.log('signOut');


    };

  }


}
