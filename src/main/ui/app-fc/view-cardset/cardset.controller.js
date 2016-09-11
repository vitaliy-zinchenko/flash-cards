export default class cardsetController {
  constructor(cardsService, setService, $state) {
    this.name = 'cardsetController';

    this.currentSet = null;
    this.newCards = [];
    this.cards = []; //TODO hotfix without this angular.js:13920 TypeError: Cannot read property 'push' of undefined. During saving new card in new card set

    var page = 0;
    var size = 9999999;
    var id = $state.params.id;

//get existing cards
    if( id && id != 'new') { //TODO is it possible to avoid chis check?
      this.currentSet = 'set'; //TODO: get particular set by id
      this.cards = cardsService.getAll(id, page, size);
    }

//create new set
    this.saveSet = (set) => {
      setService.createSet(set).$promise.then(data => {
        console.log('Create new set:');
        console.log(data);
        this.currentSet = data;
        id = data.id; //TODO this is hotfix. without this one new cards for new cards set are sent to /api/card-set/new/cards/batch
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
// send temp array to server
      cardsService.addCards( id, cards ).$promise.then(data => {
        data.forEach((item, i) => {
          this.cards.push(item);
        })
      });
// and clean temp array
      this.newCards = [];
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
