import angular from 'angular';

import satellizer from 'satellizer';

import viewComponent    from './signin.component.js';
import getAppConfig     from '../services/get-config.service';

var appConfig = getAppConfig().config;


export default angular.module('viewSighin', [satellizer])
  .config(/* @ngInject */($stateProvider, $authProvider) => {
    $stateProvider
      .state('signin', viewComponent);

    $authProvider.google({
      clientId: '563875364656-v4t1v5t4agj9p57m35q9t6f9rgrn0r02.apps.googleusercontent.com',
      //clientId: appConfig,
      url: '/api/user/login/google'
    });

  })

  .name;



