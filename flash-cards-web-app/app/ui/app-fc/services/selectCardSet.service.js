export default (localStorageService) => {
  /* @ngInject */
  const service = {};

  const SELECTED_CARD_SET_IDS_KEY = "selectedCardSetIds";
  const SELECTED_CARD_IDS_KEY = "selectedCardIds";

  service.selectCard = (cardId) => {
    localStorageService.set("selectedCardId", cardId)
  };

  service.getSelectedCards = () => {
    return localStorageService.get("selectedCardId")
  };

  service.selectCardSet = (cardSetId) => {
    var selectedCardSetIds = localStorageService.get(SELECTED_CARD_SET_IDS_KEY);
    if(!selectedCardSetIds) {
      selectedCardSetIds = [];
    }
    selectedCardSetIds.push(cardSetId);
    localStorageService.set(SELECTED_CARD_SET_IDS_KEY, selectedCardSetIds);
  };

  service.unSelectCardSet = (cardSetId) => {
    var selectedCardSetIds = localStorageService.get(SELECTED_CARD_SET_IDS_KEY);
    if(!_.isEmpty(selectedCardSetIds)) {
      _.pull(selectedCardSetIds, cardSetId);
      localStorageService.set(SELECTED_CARD_SET_IDS_KEY, selectedCardSetIds);
    }
  };

  service.getSelectedCardSet = () => {
    return localStorageService.get(SELECTED_CARD_SET_IDS_KEY);
  }

  service.selectCard = (cardSetId, cardId) => {
    var selectedCardIds = localStorageService.get(SELECTED_CARD_IDS_KEY);
    if(!selectedCardIds) {
      selectedCardIds = {};
    }
    if(!selectedCardIds[cardSetId]) {
      selectedCardIds[cardSetId] = []
    }
    selectedCardIds[cardSetId].push(cardId);
    localStorageService.set(SELECTED_CARD_IDS_KEY, selectedCardIds);
  };

  service.unSelectCard = (cardSetId, cardId) => {
    var selectedCardIds = localStorageService.get(SELECTED_CARD_IDS_KEY);
    if(!selectedCardIds) {
      return;
    }
    if(!_.isEmpty(selectedCardIds[cardSetId])) {
      _.pull(selectedCardIds[cardSetId], cardId);
      if(_.isEmpty(selectedCardIds[cardSetId])) {
        delete selectedCardIds[cardSetId];
      }
      localStorageService.set(SELECTED_CARD_IDS_KEY, selectedCardIds);
    }
  };

  service.getSelectCards = (cardSetId) => {
    var selectedCardIds = localStorageService.get(SELECTED_CARD_IDS_KEY);
    if(!selectedCardIds) {
      return [];
    }
    return selectedCardIds[cardSetId];
  };

  return service;
}