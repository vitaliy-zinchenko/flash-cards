import angular from 'angular';

import viewComponent    from './cardset.component';
import cardsService from './../services/cards.service';

export default angular.module('viewCardSet', [])
  .config($stateProvider => {
    $stateProvider
      .state('cardset', viewComponent);
  })
  .service('cardsService', cardsService)
  .name;
