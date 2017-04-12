import angular from 'angular';

import viewComponent    from './cards-training.component.js';

import selectCardSet from './../../services/selectCardSet.service.js'

export default angular.module('cards-training', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('cards-training', viewComponent);
  })
  .factory('selectCardSet', selectCardSet)
  .name;
