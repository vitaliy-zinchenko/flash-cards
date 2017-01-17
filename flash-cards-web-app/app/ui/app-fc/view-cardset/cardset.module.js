import angular from 'angular';

import viewComponent    from './cardset.component.js';
import cardsetNewComponent    from './cardset-new.component.js';

import setTitle from './set-title';
import languageChoice from './language-choice';
import cards from './cards';

import cardsService from './../services/cards.service.js';
import setService from '../services/sets.service.js';
import selectCardSet from './../services/selectCardSet.service.js'


export default angular.module('viewCardSet', [])
  .config(/* @ngInject */ $stateProvider => {
    $stateProvider
      .state('cardset', viewComponent)
      .state('cardset-new', cardsetNewComponent);
  })
  .component('setTitle', setTitle)
  .component('languageChoice', languageChoice)
  .component('cards', cards)

  .factory('cardsService', cardsService)
  .factory('setService', setService)
  .factory('selectCardSet', selectCardSet)
  .name;
