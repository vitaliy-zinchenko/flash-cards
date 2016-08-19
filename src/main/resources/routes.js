//import cardsComponent from './static/app/view-sets';

export default function ( $stateProvider, $urlRouterProvider, $locationProvider ) {
  //'ngInject'; // TODO: use ng-annotate

  $locationProvider.html5Mode( {
    enabled: true,
    requireBase: false
  } );

  $urlRouterProvider.otherwise( '/main' );

  $stateProvider
/*    .state( 'sets', {
      url: '/main',
      //abstract: true, // TODO: read about this param
      template: cardsComponent
    } )*/
    .state( 'card-set', {
      url: '/card-set?page=0&size=10',
      //abstract: true, // TODO: read about this param
      template: '<h1>SET</h1>'
      //controller: 'cardsController'
    } )

}
