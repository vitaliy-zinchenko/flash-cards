//TODO I am not sure about the name of file. consider to use 'config.service.js'
export default () => {
  /* @ngInject */
  var service = {};

  service.fetchAppConfig = () => {
    var initInjector = angular.injector(["ng"]);
    var $http = initInjector.get("$http");

    return $http.get("/api/config/app").then(function(response) {
      // service.config = response.data; TODO: maybe save to service or another store
      var config = JSON.stringify(response.data); //TODO not sure about stringify
      localStorage.setItem('appConfig', config);

    }, function(errorResponse) {
      //TODO handle
      console.log("Error during loading configuration in bootstrap")
    });
  };

  return service;
}