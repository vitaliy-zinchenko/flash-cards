module.exports = function($scope, $auth, $state) {

  $scope.isAuthenticated = function() {
    return $auth.isAuthenticated()
  }

  $scope.logOut = function() {
    $auth.logout()
    $state.go("signin")
  }

}