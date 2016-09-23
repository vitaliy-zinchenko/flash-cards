export default class cardsetController {
  /* @ngInject */
  constructor(cardsService, $state, $q) {
    this._cardsService = cardsService;
    this.$state = $state;

    this.initialize();

    var vm = this;

    vm.name = 'cardsetController';


    console.log(vm.currentSet + ` - from set controller(parent)`);

    vm.newCards = [];
    vm.cards = []; //TODO hotfix without this angular.js:13920 TypeError: Cannot read property 'push' of undefined. During saving new card in new card set

    var page = 0;
    var size = 9999999;
    var id = $state.params.id;

//create set
/*    vm.saveSet = (set) => {
      setService.createSet(set).$promise.then(data => {
       console.log('Create new set:');
       console.log(data);
       vm.currentSet = data;
       id = data.id; //TODO this is hotfix. without this one new cards for new cards set are sent to /api/card-set/new/cards/batch
       });
    };*/

//get existing cards
    if( id && id != 'new') { //TODO is it possible to avoid chis check?
      vm.currentSet = 'set'; //TODO: get particular set by id
      vm.cards = cardsService.getAll(id, page, size);
    }

//cards
    vm.deleteCard = () => {
      console.log('delete card');
    };

    vm.newCard = () => {
      var card = {
        word: "",
        translation: ""
      };

      vm.cards.push(card);
    };


    vm.createCard = (card) => {
      var sendData = vm.tempPrepare(card); // TODO: remove this when update backend method
      vm.saveCards(sendData).then(
        response => card = response
      );
    };

    // temp function for convert obj to array // TODO: remove this when update backend method
    vm.tempPrepare = (obj) => {
      return [obj];
    };

    // save card on server
    vm.saveCards = (cards) => {
      return new Promise(function(resolve, reject) { // TODO: use angular promises $q
        cardsService.addCards( id, cards ).$promise.then(data => {
          resolve(data[0]);
        });
      });
    };

  }

  myMethod() {

  }

  initialize() {
    this.currentSet = 'vm.test';
  }

/*  saveCards(cards) {
    return new Promise(function(resolve, reject) {
      this._cardsService.addCards( id, cards ).$promise.then(data => {
        resolve(data[0]);
      });
    });
  }*/

}
