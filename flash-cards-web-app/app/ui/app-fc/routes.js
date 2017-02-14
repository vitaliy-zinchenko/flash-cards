export default function ( $stateProvider, $urlRouterProvider, $locationProvider, localStorageServiceProvider ) {
  /* @ngInject */  // TODO: use ng-annotate

  $locationProvider.html5Mode( {
    enabled: true,
    requireBase: false
  } );

  $urlRouterProvider.otherwise( '/sets' );

  localStorageServiceProvider.setPrefix('fc');

}
