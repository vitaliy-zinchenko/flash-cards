export default class signupController {
  /* @ngInject */
  constructor($scope, $auth, $state) {
    this.auth = $auth
    this.state = $state
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

}
