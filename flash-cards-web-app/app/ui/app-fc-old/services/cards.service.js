export default ($resource) => {
  /* @ngInject */

  const service = $resource('/api/card-set/:setId/cards',
                            {setId: '@setId', cardId: '@cardId', cardsId: '@cardsId', page:'@page', size:'@size'},
                            {
//                                'saveButch': {
//                                  url: '/api/card-set/:setId/cards/batch',
//                                  method: 'POST',
//                                  isArray: true
//                                },
                                'byIds': {
                                  url: '/api/card-set/:setId/cards/:cardIds',
                                  method: 'GET',
                                  isArray: true
                                },
                                'change': {
                                  url: '/api/card-set/:setId/cards',
                                  method: 'PUT'
                                },
                                'delete': {
                                  url: '/api/card-set/:setId/cards/:cardId',
                                  method: 'DELETE'
                                },
                                '_progress': {
                                  url: '/api/card-set/cards/progress',
                                  method: 'POST'
                                },
                                '_listToLearn': {
                                  url: '/api/card-set/:setId/cards/learn',
                                  method: 'GET',
                                  isArray: true
                                }
                            });

  service.getAll = (setId, page, size) => {
    return service.query({ setId: setId, page: page , size: size }).$promise;
  };

  service.create = (setId, card) => {
    return service.save({setId: setId}, card).$promise;
  };

  service.update = (setId, card) => {
    return service.change({setId: setId}, card).$promise;
  };

  service.remove = (setId, cardId) => {
    return service.delete({setId: setId, cardId: cardId}).$promise;
  };

  service.byCardIds = (setId, cardIds) => {
    return service.byIds({setId: setId, cardIds: cardIds});
  };

  service.sendProgress = (cardsProgresses) => {
    return service._progress(cardsProgresses);
  };

  service.listToLearn = (setId) => {
    return service._listToLearn({setId, setId});
  };

  return service;

};