import angular from 'angular';

import viewComponent    from './sets.component';

export default angular.module('viewSets', [])
  .config($stateProvider => {
    $stateProvider
      .state('sets', viewComponent);
  })
  .name;
