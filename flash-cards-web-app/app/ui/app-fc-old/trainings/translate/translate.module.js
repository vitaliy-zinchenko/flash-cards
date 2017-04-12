import angular from 'angular';

import viewComponent    from './translate.component.js';

import selectCardSet from './../../services/selectCardSet.service.js'

export default angular.module('translate', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('training-translate', viewComponent);
  })
  .factory('selectCardSet', selectCardSet)
  .name;
