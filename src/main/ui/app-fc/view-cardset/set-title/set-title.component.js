import template from './set-title.view.html';
import controller from './set-title.controller.js';

export default angular.module('setTitle', [])
  .component('setTitle', {
    controller,
    template,
    controllerAs: 'csCtrl'
  })
  .name;
