import angular from 'angular';

import viewComponent    from './cardset.component.js';
import cardsetNewComponent    from './cardset-new.component.js';

import setTitleComponent from './set-title';

import cardsService from './model-cards.service.js';
import setService from '../view-sets/model-sets.service.js';


export default angular.module('viewCardSet', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('cardset', viewComponent)
      .state('cardset-new', cardsetNewComponent);
  })
  .component('setTitle', setTitleComponent)
  .factory('cardsService', cardsService)
  .factory('setService', setService)
  .name;
