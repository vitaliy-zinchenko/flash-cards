export default class translateController {
  /* @ngInject */
  constructor($state, $q) {
    this.$state = $state;
    this.$q = $q;

    this.initialize();
  }


  initialize() {
    this.testValue = 'ttt';
    this.id = this.$state.params.id;
    console.log(this.id);
  }

}
