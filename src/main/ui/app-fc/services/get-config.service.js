export default () => {
  /* @ngInject */
  var service = {};

  service.fetchAppConfig = () => {
    var initInjector = angular.injector(["ng"]);
    var $http = initInjector.get("$http");

    return $http.get("/api/config/app").then(function(response) {
      // service.config = response.data; TODO: maybe save to service or another store
      var config = JSON.stringify(response.data);
      localStorage.setItem('appConfig', config);

    }, function(errorResponse) {
      //TODO handle
      console.log("Error during loading configuration in bootstrap")
    });
  };

  return service;
}