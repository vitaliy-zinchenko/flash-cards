import template from './cardset.view.html';
import controller from './cardset.controller.js';

export default {
  url       : '/card-set?page=0&size=10',
  controller,
  template,
  controllerAs: 'cardsetCtrl'
}