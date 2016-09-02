export default class cardsetController {
  constructor(cardsService, $state) {
    this.name = 'cardsetController';

    var page = 0;
    var size = 10;
    var id = $state.params.id;

    this.cards = cardsService.getAll(id , page, size);

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
