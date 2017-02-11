export default class cardsTrainingController {
  /* @ngInject */
  constructor(cardsService, selectCardSet, $state, $q) {
    this.$state = $state;
    this.$q = $q;

    this._cardsService = cardsService;
    this._selectCardSet = selectCardSet;

    this.initialize();
  }


  initialize() {
    var selected = this._selectCardSet.getSelectedCards()
    var setId = Object.keys(selected)[0]

    this.cards = this._cardsService.test(setId, selected[setId])

    this.currentCardIndex = 0

    this.cards.$promise.then(cards => {
        this.currentCard = cards[this.currentCardIndex]
    })

    this.turned = false
  }

  next() {
    this.turned = false
    this.currentCard = this.cards[++this.currentCardIndex]
  };

  previous() {
    this.turned = false
    this.currentCard = this.cards[--this.currentCardIndex]
  };

  turn() {
    this.turned = !this.turned
  };

  right() {
    console.log("right")
  };

  wrong() {
    console.log("wrong")
  };

}