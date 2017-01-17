export default ($resource) => {
  /* @ngInject */
//settings
  const mainUrl = '/api/card-set/:id/cards';
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

 //var conf;  EXAMPLE

//methods
  service.getAll = (id, page, size) => {
    return service.query({ id: id, page: page , size: size }).$promise; //.then(function (res){ EXAMPLE
      //conf = res.data;
      //defer.resolve();
    //});
  };

  service.addCards = (id, cards) => {
    return service.saveButch({id: id}, cards);
  };

  return service;

};