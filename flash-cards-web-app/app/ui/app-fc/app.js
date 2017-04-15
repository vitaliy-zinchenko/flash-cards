require('angular');
require('angular-resource');
require('angular-ui-router');
require('angular-offline');
require('angularindexeddb/angular-indexed-db');
require('angular-local-storage');

require('./styles/css/bootstrap.min.css');
require('./styles/css/all.css');

require('./pages');
require('./directives');

angular
    .module('fc-app', ['ui.router', 'LocalStorageModule', 'fc.ctrl', 'fc.dir'])
    .controller("T1", function() {
        console.log("t1")
    })
    .config(require('./config.js'));

fetchAppConfig().then(bootstrapApplication);

function bootstrapApplication() {
    angular.element(document).ready(function() {
        angular.bootstrap(document, ["fc-app"]);
        console.log("Bootstrapped App")
    });
}

function fetchAppConfig() {
    var initInjector = angular.injector(["ng"]);
    var $http = initInjector.get("$http");

    return $http.get("/api/config/app").then(function(response) {
      // service.config = response.data; TODO: maybe save to service or another store
      var config = JSON.stringify(response.data);
      localStorage.setItem('appConfig', config);

    }, function(errorResponse) {
      console.log("Error during loading configuration in bootstrap")
    });
}

Array.prototype.randomize = function() {
    var m = this.length, i, t;

    while(m) {
        i = Math.floor(Math.random() * m--)
        t = this[m]
        this[m] = this[i]
        this[i] = t
    }

}