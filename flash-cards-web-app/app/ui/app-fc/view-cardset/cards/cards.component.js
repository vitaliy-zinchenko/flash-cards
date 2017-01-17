import template from './cards.view.html';
import controller from './cards.controller.js';

export default {
    controller,
    template,
    bindings: {
        currentSet: '='
    },
    controllerAs: 'cardsCtrl'
  };
