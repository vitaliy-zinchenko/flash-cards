module.exports = function($scope, $state, Sets) {
  console.log("sets ctrl")

  var self = this;

  var page = 0;
  var size = 99999;
  Sets.getAll(page, size)
    .then(function(sets) {
      $scope.sets = sets;
    });

  $scope.goToTranslate = function() {
    $state.go('training-translate');
  }

  $scope.goToSet = function(set) {
    $state.go('cards', {setId: set.id});
  }

  $scope.goToTranslate = function() {
    $state.go('training-translate');
  }

  $scope.goToCards = function() {
    $state.go('cards-training');
  }

  $scope.remove = function(cardSet) {
    Sets.remove(cardSet.id).then(function(response) {
      $scope.sets.splice($scope.sets.indexOf(cardSet), 1);
    });
  };

}

/*

export default class setsPageController  {
  constructor(setService, $state, localStorageService, selectCardSet) {
    console.log('setsPageController');

    this._setService = setService;
    this.$state = $state;
    this.localStorageService = localStorageService;
    this.selectCardSet = selectCardSet;
    this.initialize();
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

    selectChange(set) {
      if(set.selected) {
        this.selectCardSet.selectCardSet(set.id);
      } else {
        this.selectCardSet.unSelectCardSet(set.id);
      }

    }

    goToTranslate() {
      this.selectCardSet.selectCard({qw: 11})
      console.log(this.selectCardSet.getSelectedCards())
      this.localStorageService.set('test', 'test');
      this.$state.go('training-translate');
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

    remove(cardSet) {
      this._setService.remove(cardSet.id).then(response => {
        this.sets.splice(this.sets.indexOf(cardSet), 1);
      })
    };

    _markSelectedSets(sets) {
      var selected = this.selectCardSet.getSelectedCardSet();
      _.each(sets, (set) => {
        if(_.includes(selected, set.id)) {
          set.selected = true
        }
      });
    }
}
*/
