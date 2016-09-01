export default class setsController  {
  constructor(setsService, $state) {
// TODO: get "page" and "size" from URL (or where from?)
    var page = 0;
    var size = 10;

    this.sets = setsService.query({ size: size, page: page });

    this.goToSet = (set) => {
      $state.go('cardset', {id: set.id});
    }
  }
}

