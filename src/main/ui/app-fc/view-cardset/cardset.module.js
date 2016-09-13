import angular from 'angular';

import viewComponent    from './cardset.component.js';
import cardsetNewComponent    from './cardset-new.component.js';
import cardsService from './model-cards.service.js';
import setService from '../view-sets/model-sets.service.js';


export default angular.module('viewCardSet', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('cardset', viewComponent)
      .state('cardset-new', cardsetNewComponent);
  })
  .factory('cardsService', cardsService)
  .factory('setService', setService)
  .name;
