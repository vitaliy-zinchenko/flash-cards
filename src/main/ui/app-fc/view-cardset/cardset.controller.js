export default class cardsetController {
  /* @ngInject */
  constructor(cardsService, $state, $q) {
    this._cardsService = cardsService;
    this.$state = $state;
    this.$q = $q;

    this.initialize();
  }


  initialize() {
    this.name = 'cardsetController';
    this.currentSet = null;
  }

}
