module.exports = function ($scope, $auth, $state) {
  console.log("signup ctrl")

  $scope.signUp = function() {
      console.log(this.user)
      $auth.signup(this.user)
          .then(function(response) {
            $auth.setToken(response)
            $state.go('sets');
          })
          .catch(function(data) {
            console.log("logged in - :" + data)
          })
    }

}