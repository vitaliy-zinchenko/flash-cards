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


export default angular
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

function mainController() { //test code
  console.log('main app');
}



