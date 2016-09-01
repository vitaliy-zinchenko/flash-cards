export default class cardsetController {
  constructor(cardsService) {
    this.name = 'cardsetController';

    var id = 3;
    var page = 0;
    var size = 10;

    this.cards = cardsService.query({ id:  id, size: size, page: page });


  }

}
