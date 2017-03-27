export default class cardsetController {
  /* @ngInject */
  constructor(cardsService, setService, selectCardSet, $state, $q) {
    this._cardsService = cardsService;
    this._setService = setService;
    this._selectCardSet = selectCardSet;
    this.$state = $state;
    this.$q = $q;

    this.initialize();
  }


  initialize() {
    this.cards = [];
    this.currentSet = null;
    this.id = this.$state.params.id;
    if( this.id && this.id != 'new') {

    }

    this.id = this.$state.params.id;

    //get existing cards
    if( this.id ) { //TODO is it possible to avoid chis check?
      this._setService.get(this.id)
        .then(set => {
          this.currentSet = set;
        });
      this._cardsService.getAll(this.id, 0, 99999)
        .then(cards => {
          this._markSelectedCards(cards);
          this.cards = cards;
        });
    }

  }

  // ****************************
  //         UI handlers
  // ****************************

  saveSet(set) {
    if(this.id) {
      this._setService.update(set).then(data => {
        console.log("updated")
      });
    } else {
      this._setService.createSet(set).then(data => {
        this.currentSet = data;
        this.id = data.id;
      });
    }
  };

  newCard() {
    this.cards.push({});
  };

  saveCard(card) {
    if(!card.word || !card.translation) {
      return
    }
    if(card.id) {
      this._cardsService.update(this.id, card).then(response => {
        console.log("updated")
      })
    } else {
      this._cardsService.create(this.id, card).then(response => {
        card.id = response.id
      })
    }
  };

  deleteCard(card) {
    if(card.id) {
        this._cardsService.remove(this.id, card.id).then(response => {
          this.cards.splice(this.cards.indexOf(card), 1);
        })
    } else {
        this.cards.splice(this.cards.indexOf(card), 1);
    }
  };

  selectChange(card) {
    if(card.selected) {
      this._selectCardSet.selectCard(this.id, card.id);
    } else {
      this._selectCardSet.unSelectCard(this.id, card.id);
    }
  }

  goToCards() {
    this.$state.go('cards-training');
  }

  goToTranslate() {
    this.$state.go('training-translate');
  }

  goToMatching() {
    this.$state.go('training-matching', {setId: this.id});
  }

  // ****************************
  //         Methods
  // ****************************

  _markSelectedCards(cards) {
    var selected = this._selectCardSet.getSelectCards(this.id);
    _.each(cards, (card) => {
      if(_.includes(selected, card.id)) {
        card.selected = true
      }
    });
  }

}
