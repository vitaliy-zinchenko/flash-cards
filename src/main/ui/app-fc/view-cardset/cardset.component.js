import template from './cardset.view.html';
import controller from './cardset.controller.js';

export default {
  url       : '/card-set/:id',
  controller,
  template,
  params: {
    id: {
      value: '@id'
    }
  },
  controllerAs: 'cardsetCtrl'
}