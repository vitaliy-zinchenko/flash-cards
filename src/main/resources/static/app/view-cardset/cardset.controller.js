export default class cardsetController {
  constructor(cardsService, $state) {
    this.name = 'cardsetController';

    var page = 0;
    var size = 10;

    this.cards = cardsService.query({
      id:  $state.params.id,
      size: size,
      page: page
    });

    this.deleteCard = () => {
      console.log('delete card');
    }
  }

}
