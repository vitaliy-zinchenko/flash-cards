export default function ( $stateProvider, $urlRouterProvider, $locationProvider ) {
  //'ngInject'; // TODO: use ng-annotate

  $locationProvider.html5Mode( {
    enabled: true,
    requireBase: false
  } );

  $urlRouterProvider.otherwise( '/main' );

}
