console.log('app');

'use strict';
import styles from './styles/all.css';

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import ngResource from 'angular-resource';

import viewSets from './view-sets';
import viewCardSet from './view-cardset';
import viewSignin from './view-signin';

import routes from './routes';
import footer from './components/footer';
import header from './components/header';


var fcApp = angular
  .module( 'fcApp', [
    uiRouter,
    ngResource,
    viewSets,
    viewCardSet,
    viewSignin,
    footer,
    header
  ] )
  .config(routes)
  .controller('mainController', mainController);

fetchAppConfig().then(bootstrapApplication);

function mainController() { //test code
  console.log('main app');
}

function fetchAppConfig() {
    var initInjector = angular.injector(["ng"]);
    var $http = initInjector.get("$http");

    return $http.get("/api/config/app").then(function(response) {
        fcApp.constant("config", response.data);
        console.log(response.data)
    }, function(errorResponse) {
        //TODO handle
        console.log("Error during loading configuration in bootstrap")
    });
}

function bootstrapApplication() {
    angular.element(document).ready(function() {
        console.log("Bootstrapping App...");
        angular.bootstrap(document, ["fcApp"]);
        console.log("Bootstrapped App")
    });
}


