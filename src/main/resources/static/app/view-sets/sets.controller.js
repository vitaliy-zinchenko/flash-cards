export default class setsController  {
  constructor(setsService) {
// TODO: get "page" and "size" from ???
    var page = 0;
    var size = 10;

    this.sets = setsService.query({ size: size, page: page });
  }

}

