export default  ($resource) => {
//settings
  const mainUrl = 'api/card-set/:id/cards';
  const saveButchUrl = '/api/card-set/:id/cards/batch';
  const params = {id: '@id', page:'@page', size:'@size'};

  const actions = {
    'saveButch': {
      url: saveButchUrl,
      method: 'POST',
      isArray: true
    }
  };

  const service = $resource(mainUrl, params, actions);

//methods
  service.getAll = (id, page, size) => {
    return service.query({ id: id, page: page , size: size });
  };

  service.addCards = (id, cards) => {
    return service.saveButch({id: id}, cards);
  };

  return service;

};