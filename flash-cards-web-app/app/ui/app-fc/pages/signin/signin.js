module.exports = function ($scope, $auth, $state) {

  $scope.signIn = function() {
    $auth.login($scope.user)
        .then(function() {
          console.log("logged in + ")
          $state.go('sets');
        })
        .catch(function() {
          console.log("logged in - ")
        })
  }

  $scope.authenticate = function(providerName) {
        $auth.authenticate(providerName)
          .then(function() {
            console.log("+ : isAuthenticated = " + $auth.isAuthenticated())
            $state.go('sets');
          })
          .catch(function(response) {
            console.log("-")
          });
    }

}
