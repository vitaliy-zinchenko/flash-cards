//import setsService from './../services/sets.service';

export default class setsController  {
  constructor($resource) {
    this.name = 'SETS';

    let res  = $resource('/card-set?page=0&size=10');
    this.data = res.query();
  }

  changeName() {
    this.name = 'angular-tips';
  }
}

