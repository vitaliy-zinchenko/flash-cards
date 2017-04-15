'use strict';

var angular = require('angular');
require('angular-ui-router');
require('angular-cookies');
require('satellizer');
require('./../services');

angular
    .module('fc.ctrl', ['ngCookies', 'ui.router', 'satellizer', 'fc.serv'])
    .config(function($stateProvider) {

      $stateProvider.state({
        name: 'signin',
        url: '/signin',
        controller:  require('./signin/signin.js'),
        template: require('./signin/signin.html')
      });

      $stateProvider.state({
        name: 'signup',
        url: '/signup',
        controller:  require('./signup/signup.js'),
        template: require('./signup/signup.html')
      });

      $stateProvider.state({
        name: 'sets',
        url: '/sets',
        controller:  require('./sets/sets.js'),
        template: require('./sets/sets.html')
      });

      $stateProvider.state({
        name: 'cards',
        url: '/cards/:setId',
        controller:  require('./cards/cards.js'),
        template: require('./cards/cards.html')
      });

      $stateProvider.state({
        name: 'new-card-set',
        url: '/new-card-set',
        controller:  require('./cards/cards.js'),
        template: require('./cards/cards.html')
      });

      $stateProvider.state({
        name: 'matching',
        url: '/training/matching/:setId',
        controller:  require('./trainings/matching/matching.js'),
        template: require('./trainings/matching/matching.html')
      });

    });