export default class matchingController {
  /* @ngInject */
  constructor(cardsService, selectCardSet, $state, $q, $window) {
    this.$state = $state;
    this.$state = $state;
    this.$q = $q;
    this.$window = $window;

    this._cardsService = cardsService;
    this._selectCardSet = selectCardSet;

    this.initialize();
  }


  initialize() {
    this.setId = this.$state.params.setId;
    this._cardsService.getAll(this.setId, 0, 9999) //TODO - remove 0 and 9999
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

  goBack() {
    this.$window.history.back();
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
      if(this._isFinished()) {
        this.finished = true
      }
    } else if(selectedWord && selectedTranslation) {
      this._clearWords()
      this._clearTranslations()
    }
  }

  _isFinished() {
    return this.cards.filter(card => !card.guessed).length == 0;
  }

}
