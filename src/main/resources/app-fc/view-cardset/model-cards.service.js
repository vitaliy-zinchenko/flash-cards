export default  ($resource) => {
  const service = $resource('api/card-set/:id/cards', {id: '@id', page:'@page', size:'@size'});

  service.getAll = (id, page, size) => {
    return service.query({ id: id, page: page , size: size });
  };

  service.createCard = (card) => {
    return service.save({ content: card });
  };

  return service;

};