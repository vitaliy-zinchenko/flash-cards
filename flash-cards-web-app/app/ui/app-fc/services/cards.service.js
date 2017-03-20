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

  return service;

};