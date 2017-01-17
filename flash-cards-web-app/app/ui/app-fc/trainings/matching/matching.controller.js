export default class matchingController {
  /* @ngInject */
  constructor($state, $q) {
    this.$state = $state;
    this.$q = $q;

    this.initialize();
  }


  initialize() {
    this.testValue = 'ttt';
  }

}
