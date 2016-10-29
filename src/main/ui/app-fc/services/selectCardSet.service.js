export default (localStorageService) => {
  /* @ngInject */
  const service = {};

  const SELECTED_CARD_SET_IDS_KEY = "selectedCardSetIds";

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

  return service;
}