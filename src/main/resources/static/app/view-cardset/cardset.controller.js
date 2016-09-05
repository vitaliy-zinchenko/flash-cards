export default class cardsetController {
  constructor(cardsService, setService, $state) {
    this.name = 'cardsetController';

    var page = 0;
    var size = 9999999;
    var id = $state.params.id;

    if( id && id != 'new') {
      this.cards = cardsService.getAll(id, page, size);
    }

//create new set
    this.saveSet = (set) => {
      setService.createSet(set).$promise.then(data => {
        console.log('Create new set:');
        console.log(data.content);
      });
    };

//cards
    this.deleteCard = () => {
      console.log('delete card');
    };

    this.addCard = () => {
      console.log('new card');
    };

    this.saveCard = () => {
      console.log('save card');
    }
  }

}
