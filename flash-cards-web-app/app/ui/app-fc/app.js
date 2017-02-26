console.log('app');

'use strict';
import styles from './styles/css/all.css';
import bootstrap from './styles/css/bootstrap.min.css';

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import ngResource from 'angular-resource';
import ngLocalStorage from 'angular-local-storage';
import angularCookies from 'angular-cookies';
//import ngLodash from 'angular-lodash';


import viewSets from './view-sets';
import viewCardSet from './view-cardset';
import viewSignin from './view-signin';
import viewSignup from './view-signup';

import translateTraining from './trainings/translate';
import matchingTraining from './trainings/matching';
import cardsTraining from './trainings/cards-training';

import config from './config';
import footer from './components/footer';
import header from './components/header';

var fcApp = angular
  .module( 'fcApp', [
    uiRouter,
    ngResource,
    ngLocalStorage,
    angularCookies,
//    ngLodash,
    viewSets,
    viewCardSet,
    viewSignin,
    viewSignup,
    translateTraining,
    matchingTraining,
    cardsTraining,
    footer,
    header
  ] )
  .config(config)
  //.controller('mainController', mainController);

// bootstrap Angular after get configuration
fetchAppConfig().then(bootstrapApplication);

function mainController() { //test code
  console.log('main app');
}

function bootstrapApplication() {
    angular.element(document).ready(function() {
        angular.bootstrap(document, ["fcApp"]);
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


