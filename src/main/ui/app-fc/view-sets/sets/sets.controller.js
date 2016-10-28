export default class setsController  {
  /* @ngInject */
  constructor(setService, $state, localStorageService, selectCardSet, _) {
    console.log('setsController');

// TODO: get "page" and "size" from URL (or where from?)
    this._setService = setService;
    this.$state = $state;
    this.localStorageService = localStorageService;
    this.selectCardSet = selectCardSet;
    this.initialize();
  }

  select(set) {
    console.log('m')
    this.selectCardSet.selectCardSet(set.id);
  }

  goToSet(set) {
    this.$state.go('cardset', {id: set.id});
  }

  goToTranslate() {
    this.selectCardSet.selectCard({qw: 11})
    console.log(this.selectCardSet.getSelectedCards())
    this.localStorageService.set('test', 'test');
    this.$state.go('training-translate');
  }

  initialize() {
    this.page = 0;
    this.size = 99999;
    this._setService.getAll(this.page, this.size)
      .then(sets => {
        _markSelectedSets(sets);
        this.sets = sets;
      });
  }

  goToSet(set) {
    this.$state.go('cardset', {id: set.id});
  }

  goToTranslate() {
    this.localStorageService.set = ("ttt", "test");
    this.$state.go('training-translate');
  }

  _markSelectedSets(sets) {
    var selected = this.selectCardSet.getSelectCardSet();
    _.each(data, (set) => {
      if(_.includes(selected, set.id)) {
        set.selected = true
      }
    });
  }

}

