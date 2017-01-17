import angular from 'angular';

import viewComponent    from './matching.component.js';

export default angular.module('matching', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('training-matching', viewComponent);
  })
  .name;
