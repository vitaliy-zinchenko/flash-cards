export default class cardsController {
  /* @ngInject */
  constructor(cardsService, selectCardSet, $state, $q) {
    this.name = 'cardsController';

    this._cardsService = cardsService;
    this._selectCardSet = selectCardSet;
    this.$state = $state;
    this.$q = $q;

    this.initialize();
  }


  initialize() {
    this.cards = []; //TODO hotfix without this angular.js:13920 TypeError: Cannot read property 'push' of undefined. During saving new card in new card set
    this.page = 0;
    this.size = 9999999;
    this.id = this.$state.params.id;

    //get existing cards
    if( this.id && this.id != 'new') { //TODO is it possible to avoid chis check?
      this._cardsService.getAll(this.id, this.page, this.size)
        .then(cards => {
          this._markSelectedCards(cards);
          this.cards = cards;
        });
    }
  }


  //cards
  newCard() {
    var card = {
      word: "",
      translation: ""
    };
    this.cards.push(card);
  };

  createCard(card) {
    var sendData = this.tempPrepare(card); // TODO: remove this when update backend method
    this.saveCards(sendData).then(
      response => card = response
    );
  };

  deleteCard() {
    console.log('delete card');
  };


  // temp function for convert obj to array
  tempPrepare(obj) { // TODO: remove this when update backend method
    return [obj];
  };

  // save card on server
  saveCards(cards) { //TODO: implement errorhandler
    return this.$q( (resolve, reject) => {
      this._cardsService.addCards( this.id, cards ).$promise.then(data => {
        resolve(data[0]);
      });
    });
  };

  selectChange(card) {
    if(card.selected) {
      this._selectCardSet.selectCard(this.id, card.id);
    } else {
      this._selectCardSet.unSelectCard(this.id, card.id);
    }
  }

  _markSelectedCards(cards) {
    var selected = this._selectCardSet.getSelectCards(this.id);
    _.each(cards, (card) => {
      if(_.includes(selected, card.id)) {
        card.selected = true
      }
    });
  }

}
