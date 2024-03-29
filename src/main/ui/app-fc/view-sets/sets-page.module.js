import angular from 'angular';

import viewComponent    from './sets-page.component.js';
import sets    from './sets';

import setsService from './../services/sets.service.js';
import selectCardSet from './../services/selectCardSet.service.js'


export default angular.module('viewSets', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('sets', viewComponent);
  })
  .component('sets', sets)
  .factory('setsService', setsService)
  .factory('selectCardSet', selectCardSet)
  .name;
