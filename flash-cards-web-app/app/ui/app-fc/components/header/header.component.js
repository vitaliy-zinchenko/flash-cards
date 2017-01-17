import angular from 'angular';
import template from './header.tmpl.html';

export default angular.module('header', [])
  .component('header', {
    template: template
  })
  .name;

