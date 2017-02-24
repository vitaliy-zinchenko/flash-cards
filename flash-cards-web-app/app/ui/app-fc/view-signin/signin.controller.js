export default class signinController {
  /* @ngInject */
  constructor($scope, $auth, $state) {
    this.auth = $auth
    this.state = $state
  }

  signIn() {
    console.log(this.user)
    this.auth.login(this.user)
        .then(() => {
          console.log("logged in + ")
          this.state.go('sets');
        })
        .catch(() => {
          console.log("logged in - ")
        })
  }

  signUp() {
    console.log(this.user)
    this.auth.signup(this.user)
        .then((response) => {
          this.auth.setToken(response)
          this.state.go('sets');
        })
        .catch((data) => {
          console.log("logged in - :" + data)
        })
  }

  authenticate(providerName) {
      var s = this
      this.auth.authenticate(providerName)
        .then(function() {
          console.log("+ : isAuthenticated = " + s.auth.isAuthenticated())
          s.state.go('sets');
        })
        .catch(function(response) {
          console.log("-")
        });
  }


}
