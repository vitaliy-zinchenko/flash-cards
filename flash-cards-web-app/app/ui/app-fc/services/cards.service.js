module.exports = function($resource) {

    var resource = $resource('/api/card-set/:setId/cards',
        {setId: '@setId', cardId: '@cardId', cardsId: '@cardsId', page:'@page', size:'@size'},
        {
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

    this.getAll = function(setId, page, size) {
      return resource.query({ setId: setId, page: page , size: size }).$promise;
    };

    this.create = function(setId, card) {
      return resource.save({setId: setId}, card).$promise;
    };

    this.update = function(setId, card) {
      return resource.change({setId: setId}, card).$promise;
    };

    this.remove = function(setId, cardId) {
      return resource.delete({setId: setId, cardId: cardId}).$promise;
    };

    this.byCardIds = function(setId, cardIds) {
      return resource.byIds({setId: setId, cardIds: cardIds});
    };

    this.sendProgress = function(cardsProgresses) {
      return resource._progress(cardsProgresses);
    };

    this.listToLearn = function(setId) {
      return resource._listToLearn({setId: setId});
    };

}

/*

export default ($resource) => {
  */
/* @ngInject *//*


  const service = $resource('/api/card-set/:setId/cards',
                            {setId: '@setId', cardId: '@cardId', cardsId: '@cardsId', page:'@page', size:'@size'},
                            {
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

};*/
