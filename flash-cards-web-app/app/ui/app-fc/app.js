console.log('app');

'use strict';
import styles from './styles/all.css';

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import ngResource from 'angular-resource';
import ngLocalStorage from 'angular-local-storage';
//import ngLodash from 'angular-lodash';


import viewSets from './view-sets';
import viewCardSet from './view-cardset';
import viewSignin from './view-signin';

import translateTraining from './trainings/translate';
import matchingTraining from './trainings/matching';

import routes from './routes';
import footer from './components/footer';
import header from './components/header';

import getConfig from './services/get-config.service';

var fcApp = angular
  .module( 'fcApp', [
    uiRouter,
    ngResource,
    ngLocalStorage,
//    ngLodash,
    viewSets,
    viewCardSet,
    viewSignin,
    translateTraining,
    matchingTraining,
    footer,
    header
  ] )
  .config(routes)
  .controller('mainController', mainController);

// bootstrap Angular after get configuration
getConfig().fetchAppConfig().then(bootstrapApplication);


function mainController() { //test code
  console.log('main app');
}

function bootstrapApplication() {
    angular.element(document).ready(function() {
        angular.bootstrap(document, ["fcApp"]);
        console.log("Bootstrapped App")
    });
}


