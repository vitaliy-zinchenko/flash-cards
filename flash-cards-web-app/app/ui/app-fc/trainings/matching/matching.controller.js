const MATCHING_SIZE = 3;

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
            cards.randomize();
            this.allCards = cards;
            this.inProgressCards = this._getInProgressCards(this.allCards);
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

  _check() {
    var selectedWord = this.inProgressCards.find(card => card.selectedWord)
    var selectedTranslation = this.inProgressCards.find(card => card.selectedTranslation)
    if(selectedWord == selectedTranslation) {
      selectedWord.guessed = true
      selectedWord.rightCount++
      this._clearWords()
      this._clearTranslations()
      if(this._isFinishedRound()) {
        this.inProgressCards = this._getInProgressCards(this.allCards);
        if(this.inProgressCards.length == 0) {
          this.finished = true
        }
      }
    } else if(selectedWord && selectedTranslation) {
      selectedWord.wrongCount++
      selectedWord.rightCount--
      this._clearWords()
      this._clearTranslations()
    }
  }

  _clearWords() {
    this.inProgressCards.forEach(card => card.selectedWord = false)
  }

  _clearTranslations() {
    this.inProgressCards.forEach(card => card.selectedTranslation = false)
  }


  _isFinishedRound() {
    return this.inProgressCards.filter(card => !card.guessed).length == 0;
  }

  _getInProgressCards(allCards) {
    var isFirstRound = (card) => !card.rightCount && !card.wrongCount;
    var isReTryRound = (card) => card.wrongCount - card.rightCount > 0;
    var inProgress = [];
    for(var i = 0; inProgress.length < MATCHING_SIZE && allCards[i]; i++) {
        var card = allCards[i];
        if(isFirstRound(card) || isReTryRound(card)) {
            card.guessed = false;
            card.rightCount = 0;
            card.wrongCount = 0;
            inProgress.push(card);
        }
    }
    return inProgress;
  }

}
