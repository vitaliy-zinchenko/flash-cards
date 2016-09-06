export default class cardsetController {
  constructor(cardsService, setService, $state) {
    this.name = 'cardsetController';

    this.currentSet = null;

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

    this.addCard = () => {
      console.log('new card');
    };

    this.saveCard = (cards) => {
      cardsService.createSet( cards ).$promise.then(data => {
        //console.log('Create new set:');
        console.log(data);
      });
    }
  }

}
