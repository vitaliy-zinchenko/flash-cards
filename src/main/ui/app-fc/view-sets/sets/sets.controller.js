

export default class setsController  {
  /* @ngInject */
  constructor(setService, $state, $sessionStorage) {
    console.log('setsController');

// TODO: get "page" and "size" from URL (or where from?)
    this._setService = setService;
    this.$state = $state;
    this.$sessionStorage = $sessionStorage;
    this.initialize();
  }

  goToSet(set) {
    this.$state.go('cardset', {id: set.id});
  }

  goToTranslate() {
    this.$sessionStorage.test = "ttt"
    this.$state.go('training-translate');
  }

  initialize() {
    this.page = 0;
    this.size = 99999;
    this._setService.getAll(this.page, this.size)
      .then(data => this.sets = data);

  }

  _getMarkedIds() {
    return [40]
  }
}

