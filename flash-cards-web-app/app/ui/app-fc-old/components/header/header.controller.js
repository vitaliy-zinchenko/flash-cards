export default class headerController {
  /* @ngInject */
  constructor($scope, $auth, $state) {
    this.auth = $auth
    this.state = $state
  }

  isAuthenticated() {
    return this.auth.isAuthenticated()
  }

  logOut() {
    this.auth.logout()
    this.state.go("signin")
  }
}
