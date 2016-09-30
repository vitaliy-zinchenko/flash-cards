import angular from 'angular';

import viewComponent    from './sets.component.js';
import setsService from './../services/sets.service.js';

export default angular.module('viewSets', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('sets', viewComponent);
  })
  .factory('setsService', setsService)
  .name;
