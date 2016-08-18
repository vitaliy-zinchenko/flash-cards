import angular from 'angular';

export default angular.module('footer', [])
  .component('footer', {
    templateUrl: 'static/components/footer/footer.tmpl.html',
    controller: 'mainController'
  })
  .name;

