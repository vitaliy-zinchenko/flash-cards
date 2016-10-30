export default class cardsetController {
  /* @ngInject */
  constructor(cardsService, setService, $state, $q) {
    this._cardsService = cardsService;
    this._setService = setService;
    this.$state = $state;
    this.$q = $q;

    this.initialize();
  }


  initialize() {
    this.name = 'cardsetController';
    this.currentSet = null;
    this.id = this.$state.params.id;
    if( this.id && this.id != 'new') {
      this._setService.get(this.id)
        .then(set => {
          this.currentSet = set;
        });
    }
  }

}
