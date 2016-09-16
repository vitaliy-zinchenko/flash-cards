export default class cardsetController {
  /* @ngInject */
  constructor(cardsService, $state) {
    var vm = this;

    vm.name = 'cardsetController';

    vm.currentSet = null;
    vm.newCards = [];
    vm.cards = []; //TODO hotfix without this angular.js:13920 TypeError: Cannot read property 'push' of undefined. During saving new card in new card set

    var page = 0;
    var size = 9999999;
    var id = $state.params.id;

//get existing cards
    if( id && id != 'new') { //TODO is it possible to avoid chis check?
      vm.currentSet = 'set'; //TODO: get particular set by id
      vm.cards = cardsService.getAll(id, page, size);
    }

//cards
    vm.deleteCard = () => {
      console.log('delete card');
    };

    // save terms to temporary array (this.newCards)
    vm.writeTerm = (newTerm) => {
      console.log(newTerm);
      var obj = this.copyObj(newTerm);
      vm.newCards.push(obj);
      console.log(this.newCards);
      vm.newterm = null;
    };

    vm.newCard = () => {
      console.log('new card');
    };

    vm.saveCards = (cards) => {
// send temp array to server
      cardsService.addCards( id, cards ).$promise.then(data => {
        vm.newCards = [];
        Array.push.apply(this.cards, data);
      });
// and clean temp array
    };

// TODO: helper function. Need refactor
    vm.copyObj = (obj) => {
      var copy = {};
      for (var key in obj) {
        copy[key] = obj[key];
      }
      return copy;
    }
  }
}
