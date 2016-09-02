import angular from 'angular';

import viewComponent    from './cardset.component';
import cardsService from './cards.service.js';

export default angular.module('viewCardSet', [])
  .config($stateProvider => {
    $stateProvider
      .state('cardset', viewComponent);
  })
  .factory('cardsService', cardsService)
  .name;
