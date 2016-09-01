import angular from 'angular';

import viewComponent    from './sets.component';
import setsService from './../services/sets.service';


export default angular.module('viewSets', [])
  .config($stateProvider => {
    $stateProvider
      .state('sets', viewComponent);
  })
  .service('setsService', setsService)
  .name;
