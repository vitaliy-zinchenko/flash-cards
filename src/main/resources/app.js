'use strict';

//fcApp

import angular from 'angular';
import uirouter from 'angular-ui-router';

import routes from './static/app/view-cards/cards.routes';
import cards from './static/app/view-cards';


angular
  .module('fcApp', [uirouter])
  .controller('mainController', mainController)
  .config(routes);

  function mainController() {
    console.log('main app');

    let welcome = require('./module1');

    welcome("flashcard");
  }



