import angular from 'angular';

import viewComponent    from './cardset.component.js';
import cardsetNewComponent    from './cardset-new.component.js';

import setTitle from './set-title';

import cardsService from './model-cards.service.js';
import setService from '../view-sets/model-sets.service.js';


export default angular.module('viewCardSet', [setTitle])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('cardset', viewComponent)
      .state('cardset-new', cardsetNewComponent);
  })
  .factory('cardsService', cardsService)
  .factory('setService', setService)
  .name;
