export default class matchingController {
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

    this._cardsService.test(setId, selected[setId]).$promise
        .then(cards => {
            cards.forEach(card => {
                card.wordPosition = Math.random()
                card.translationPosition = Math.random()
            })
            this.cards = cards;
        })
  }

  selectWord(card) {
    this._clearWords()
    card.selectedWord = true
    this._check()
  }

  selectTranslation(card) {
    this._clearTranslations()
    card.selectedTranslation = true
    this._check()
  }

  _clearWords() {
    this.cards.forEach(card => card.selectedWord = false)
  }

  _clearTranslations() {
    this.cards.forEach(card => card.selectedTranslation = false)
  }

  _check() {
    var selectedWord = this.cards.find(card => card.selectedWord)
    var selectedTranslation = this.cards.find(card => card.selectedTranslation)
    if(selectedWord == selectedTranslation) {
      selectedWord.guessed = true
      this._clearWords()
      this._clearTranslations()
    } else if(selectedWord && selectedTranslation) {
      this._clearWords()
      this._clearTranslations()
    }
  }

}
