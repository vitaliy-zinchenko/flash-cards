import angular from 'angular';

import viewComponent    from './sets-page.component.js';

import setsService from './../services/sets.service.js';
import selectCardSet from './../services/selectCardSet.service.js'


export default angular.module('viewSets', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('sets', viewComponent);
  })
  .factory('setsService', setsService)
  .factory('selectCardSet', selectCardSet)
  .name;
