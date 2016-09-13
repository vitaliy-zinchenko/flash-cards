import angular from 'angular';

import viewComponent    from './signin.component.js';

export default angular.module('viewSighin', [])
  .config(/* @ngInject */$stateProvider => {
    $stateProvider
      .state('signin', viewComponent)
  })
  .name;
