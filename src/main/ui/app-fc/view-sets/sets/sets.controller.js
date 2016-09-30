export default class setsController  {
  /* @ngInject */
  constructor(setService, $state) {
    console.log('setsController');

// TODO: get "page" and "size" from URL (or where from?)
    this._setService = setService;
    this.$state = $state;
    this.initialize();
  }

  goToSet(set) {
    this.$state.go('cardset', {id: set.id});
  }


  initialize() {
    this.page = 0;
    this.size = 99999;
    this._setService.getAll(this.page, this.size)
      .then(data => this.sets = data);

  }
}

