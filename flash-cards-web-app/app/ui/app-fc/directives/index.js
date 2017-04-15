'use strict';

var angular = require('angular');

angular
    .module('fc.dir', [])
    .directive("header", function() {
        return {
            restrict: 'E',
            controller: require('./header/header.js'),
            template: require('./header/header.html')
        };
    })
