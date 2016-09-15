export default class setsController  {
  /* @ngInject */
  constructor(setsService, $state) {
// TODO: get "page" and "size" from URL (or where from?)
    var page = 0;
    var size = 99999;

    this.sets = setsService.getAll(page, size);

    this.goToSet = (set) => {
      $state.go('cardset', {id: set.id});
    }
  }
}

