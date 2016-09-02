export default  ($resource) => {
  const service = $resource('/card-set/:id/cards?page=:page&size=:size', {id: '@id', page:'page', size:'@size'});

  service.getAll = (id, page, size) => {
    return service.query({ page: page , size: size });
  };

  return service;

};