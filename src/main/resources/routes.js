import cardsTemplate from './static/app/view-sets/sets.view.html';

export default function ( $stateProvider, $urlRouterProvider, $locationProvider ) {
  //'ngInject'; // TODO: use ng-annotate

  $locationProvider.html5Mode( {
    enabled: true,
    requireBase: false
  } );

  $urlRouterProvider.otherwise( '/main' );

  $stateProvider
    .state( 'app', {
      url: '/main',
      //abstract: true, // TODO: read about this param
      template: cardsTemplate
      //controller: 'cardsController'
    } )

}
