export default class setsService {
  constructor ($resource) {
    var service = $resource('/card-set?page=:page&size=:size', {size:'@size', page:'page'});
    return service;
  }
};
