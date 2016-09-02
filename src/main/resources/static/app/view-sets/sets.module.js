import angular from 'angular';

import viewComponent    from './sets.component';
import setsService from './sets.service.js';

export default angular.module('viewSets', [])
  .config($stateProvider => {
    $stateProvider
      .state('sets', viewComponent);
  })
  .factory('setsService', setsService)
  .name;
