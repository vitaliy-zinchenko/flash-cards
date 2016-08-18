import angular from 'angular';
import template from './footer.tmpl.html';

export default angular.module('footer', [])
  .component('footer', {
    template: template
  })
  .name;

