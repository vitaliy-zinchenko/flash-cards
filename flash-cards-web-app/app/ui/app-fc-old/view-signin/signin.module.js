import angular from 'angular';

import satellizer from 'satellizer';

import viewComponent    from './signin.component.js';

export default angular.module('viewSighin', [satellizer])
  .config(/* @ngInject */($stateProvider, $authProvider) => {
    $stateProvider
      .state('signin', viewComponent);
  })
  .component('viewComponent', viewComponent)
  .name;


