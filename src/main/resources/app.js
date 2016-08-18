'use strict';
import  './static/styles/all.css';

import angular from 'angular';
import uiRouter from 'angular-ui-router';

import routes from './routes';
//import footer from './static/app/components/footer';


export default angular
  .module( 'fcApp', [
    uiRouter,
    footer
  ] )
  .config( routes)
  .controller('mainController', mainController);

function mainController() { //test code
  console.log('main app');

  let welcome = require('./module1');
  welcome("flashcard");
}



