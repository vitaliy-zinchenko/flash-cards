import angular from 'angular';

import viewComponent    from './matching.component.js';

import selectCardSet from './../../services/selectCardSet.service.js'

export default angular.module('matching', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('training-matching', viewComponent);
  })
  .factory('selectCardSet', selectCardSet)
  .name;
