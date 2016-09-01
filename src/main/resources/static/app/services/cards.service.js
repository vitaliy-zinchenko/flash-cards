export default class cardsService {
  constructor ($resource) {
    var service = $resource('/card-set/:id/cards?page=:page&size=:size', {id: '@id', size:'@size', page:'page'});
    return service;
  }
};