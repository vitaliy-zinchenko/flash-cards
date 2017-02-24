import angular from 'angular';

import satellizer from 'satellizer';

import viewComponent    from './component.js';

export default angular.module('viewSighup', [satellizer]) // TODO refactor, it looks not like others
  .config(/* @ngInject */($stateProvider, $authProvider) => {
    $stateProvider
      .state('signup', viewComponent);
  })
  .component('viewComponent', viewComponent)
  .name;


