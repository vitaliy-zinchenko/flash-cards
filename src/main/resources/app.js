'use strict';
import  './static/styles/all.css';

import angular from 'angular';
import uiRouter from 'angular-ui-router';
import ngResource from 'angular-resource';

import viewSets from './static/app/view-sets';
import viewCardSet from './static/app/view-cardset';

import routes from './routes';
import footer from './static/app/components/footer';
import header from './static/app/components/header';


export default angular
  .module( 'fcApp', [
    uiRouter,
    ngResource,
    viewSets,
    viewCardSet,
    footer,
    header
  ] )
  .config(routes)
  .controller('mainController', mainController);

function mainController() { //test code
  console.log('main app');

  let welcome = require('./module1');
  welcome("flashcard");
}



