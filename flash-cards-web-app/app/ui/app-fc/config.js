module.exports = function($httpProvider,
                          $stateProvider,
                          $urlRouterProvider,
                          $locationProvider,
                          localStorageServiceProvider,
                          $authProvider) {

  $urlRouterProvider.otherwise('/sets');

  $locationProvider.html5Mode({
    enabled: true,
    requireBase: false
  });

  localStorageServiceProvider.setPrefix('fc');

  var config = JSON.parse(localStorage.getItem('appConfig'))

  $authProvider.httpInterceptor = function() { return true; },
  $authProvider.withCredentials = false;
  $authProvider.tokenRoot = null;
  $authProvider.baseUrl = '/';
  $authProvider.loginUrl = '/auth/signIn';
  $authProvider.signupUrl = '/auth/signUp';
  $authProvider.unlinkUrl = '/auth/unlink/';
  $authProvider.tokenName = 'token';
  $authProvider.tokenPrefix = 'satellizer';
  $authProvider.tokenHeader = 'Authorization';
  $authProvider.tokenType = 'Bearer';
  $authProvider.storageType = 'localStorage';

  $authProvider.google({
    clientId: config.googleClientId,
    url: '/auth/google',
    scope: ['profile', 'email'],
    scopePrefix: 'openid',
    scopeDelimiter: ' ',
    requiredUrlParams: ['scope'],
    optionalUrlParams: ['display'],
    display: 'popup',
    type: '2.0',
    popupOptions: { width: 580, height: 400 }
  });

  $httpProvider.interceptors.push(function($q, $injector) {
    return {
      request: function(request) {
        // Add auth token for Silhouette if user is authenticated
        var $auth = $injector.get('$auth');
        if ($auth.isAuthenticated()) {
          request.headers['X-Auth-Token'] = $auth.getToken();
        }

        // Add CSRF token for the Play CSRF filter
        var cookies = $injector.get('$cookies');
        var token = cookies.get('PLAY_CSRF_TOKEN');
        if (token) {
          // Play looks for a token with the name Csrf-Token
          // https://www.playframework.com/documentation/2.4.x/ScalaCsrf
          request.headers['Csrf-Token'] = token;
        }

        return request;
      },

      responseError: function(rejection) {
        if (rejection.status === 401) {
          var $auth = $injector.get('$auth');
          $auth.logout();
          $injector.get('$state').go('signin');
        }
        return $q.reject(rejection);
      }
    };
  });

}
