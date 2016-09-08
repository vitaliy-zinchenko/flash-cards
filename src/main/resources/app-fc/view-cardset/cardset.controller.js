export default class cardsetController {
  constructor(cardsService, setService, $state) {
    this.name = 'cardsetController';

    this.currentSet = null;
    this.newCards = [];

    var page = 0;
    var size = 9999999;
    var id = $state.params.id;

//get existing cards
    if( id && id != 'new') {
      this.currentSet = 'set'; //TODO: get particular set by id
      this.cards = cardsService.getAll(id, page, size);
    }

//create new set
    this.saveSet = (set) => {
      setService.createSet(set).$promise.then(data => {
        console.log('Create new set:');
        console.log(data);
        this.currentSet = data;
      });
    };

//cards
    this.deleteCard = () => {
      console.log('delete card');
    };

    // save terms to temporary array (this.newCards)
    this.writeTerm = (newTerm) => {
      console.log(newTerm);
      var obj = this.copyObj(newTerm);
      this.newCards.push(obj);
      console.log(this.newCards);
      this.newterm = null;
    };

    this.newCard = () => {
      console.log('new card');
    };

    this.saveCards = (cards) => {
      console.log(cards);
// send temp array to server
      cardsService.addCards( id, cards ).$promise.then(data => {
        console.log(data);
      });

      // and clean temp array
      this.newCards = [];

      // than add responce objects to existing cards
      var respData = [ {
        "id" : 10,
        "word" : "word1",
        "translation" : "translation1"
      } ];

      respData.forEach((item, i) => {
        this.cards.push(item);
      })
    };

// TODO: helper function. Need refactor
    this.copyObj = (obj) => {
      var copy = {};
      for (var key in obj) {
        copy[key] = obj[key];
      }
      return copy;
    }
  }
}
