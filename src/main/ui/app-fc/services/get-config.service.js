export default () => {
  /* @ngInject */
  var service = {};

  service.fetchAppConfig = () => {
    var initInjector = angular.injector(["ng"]);
    var $http = initInjector.get("$http");

    return $http.get("/api/config/app").then(function(response) {
      console.log('---configService:');
      console.log(response.data);
      service.config = response.data;
    }, function(errorResponse) {
      //TODO handle
      console.log("Error during loading configuration in bootstrap")
    });
  };


  return service;
}