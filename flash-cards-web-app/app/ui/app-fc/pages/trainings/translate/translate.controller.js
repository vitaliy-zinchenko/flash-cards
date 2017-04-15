export default class translateController {
  /* @ngInject */
  constructor(cardsService, selectCardSet, $state, $q) {
    this.$state = $state;
    this.$q = $q;

    this._cardsService = cardsService;
    this._selectCardSet = selectCardSet;

    this.initialize();
  }


  initialize() {
    this.id = this.$state.params.id;
    console.log(this.id);

    var selected = this._selectCardSet.getSelectedCards()
    var setId = Object.keys(selected)[0]

    this.cards = this._cardsService.test(setId, selected[setId])

    this.currentCardIndex = 0

    this.cards.$promise.then(cards => {
        this.currentCard = cards[this.currentCardIndex]
    })
  }

  check() {
    if(this.currentCard.word == this.answer) {
        if(this.currentCardIndex == this.cards.length - 1) {
            this.finished = true;
        } else {
            this.next()
            this.answer = ''
        }
    } else {
        this.answer = ''
    }
  }

  next() {
    this.currentCard = this.cards[++this.currentCardIndex]
    this.show = false
  }

}
