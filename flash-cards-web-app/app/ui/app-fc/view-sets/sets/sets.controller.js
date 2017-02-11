export default class setsController  {
  /* @ngInject */
  constructor(setService, $state, localStorageService, selectCardSet) {
    console.log('setsController');

// TODO: get "page" and "size" from URL (or where from?)
    this._setService = setService;
    this.$state = $state;
    this.localStorageService = localStorageService;
    this.selectCardSet = selectCardSet;
    this.initialize();
  }

  selectChange(set) {
    if(set.selected) {
      this.selectCardSet.selectCardSet(set.id);
    } else {
      this.selectCardSet.unSelectCardSet(set.id);
    }

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
    var self = this;
    this._setService.getAll(this.page, this.size)
      .then(sets => {
        self._markSelectedSets(sets);
        this.sets = sets;
      });
  }

  goToSet(set) {
    this.$state.go('cardset', {id: set.id});
  }

  goToTranslate() {
    this.$state.go('training-translate');
  }

  goToCards() {
    this.$state.go('cards-training');
  }

  _markSelectedSets(sets) {
    var selected = this.selectCardSet.getSelectedCardSet();
    _.each(sets, (set) => {
      if(_.includes(selected, set.id)) {
        set.selected = true
      }
    });
  }

}

