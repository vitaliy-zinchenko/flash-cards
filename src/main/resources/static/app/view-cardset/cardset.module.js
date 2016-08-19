import angular from 'angular';

import viewComponent    from './cardset.component';

export default angular.module('viewCardSet', [])
  .config($stateProvider => {
    $stateProvider
      .state('cardset', viewComponent);
  })
  .name;
