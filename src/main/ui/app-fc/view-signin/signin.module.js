import angular from 'angular';

import viewComponent    from './signin.component.js';

export default angular.module('viewSighin', [])
  .config($stateProvider => {
    $stateProvider
      .state('signin', viewComponent)
  })
  .name;
