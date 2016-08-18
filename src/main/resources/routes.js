import cardsTemplate from './static/app/view-cards/cards.view.html';

export default function ( $stateProvider, $urlRouterProvider, $locationProvider ) {
  'ngInject';

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
