import template from './set-title.view.html';
import controller from './set-title.controller.js';

export default {
    controller,
    template,
    bindings: {
      currentSet: '='
    },
    controllerAs: 'csCtrl'
  }
