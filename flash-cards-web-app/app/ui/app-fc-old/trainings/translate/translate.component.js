import template from './translate.view.html';
import controller from './translate.controller.js';

export default {
  url       : '/training/translate?id',
  controller,
  template,
  params: {
      id: {
        value: '@id'
      }
    },
  controllerAs: 'translateCtrl'
}