import angular from 'angular';
import template from './header.tmpl.html';
import controller from './header.controller.js';

export default angular.module('header', [])
  .component('header', {
    controller: controller,
    template: template,
    controllerAs: 'headerCtrl'
  })
  .name;

