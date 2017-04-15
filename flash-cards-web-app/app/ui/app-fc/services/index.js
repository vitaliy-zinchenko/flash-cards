'use strict';

var angular = require('angular');
require('angular-resource');

angular
    .module('fc.serv', ['ngResource'])
    .service('Sets', require('./sets.service.js'))
    .service('Cards', require('./cards.service.js'));
