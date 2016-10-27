import angular from 'angular';

import satellizer from 'satellizer';

import viewComponent    from './signin.component.js';

export default angular.module('viewSighin', [satellizer])
  .config(/* @ngInject */($stateProvider, $authProvider) => {
    $stateProvider
      .state('signin', viewComponent);

    $authProvider.google({
      clientId: getId(),
      url: '/api/user/login/google'
    });

  })
  .component('viewComponent', viewComponent)
  .name;

//TODO review. I think you have to use config service instead of this function
function getId() {
  var config = localStorage.getItem('appConfig');
  var id = config ? JSON.parse(config).googleClientId : null;

  return id;
}