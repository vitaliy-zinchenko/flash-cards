import angular from 'angular';

import viewComponent    from './translate.component.js';

export default angular.module('translate', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('training-translate', viewComponent);
  })
  .name;
