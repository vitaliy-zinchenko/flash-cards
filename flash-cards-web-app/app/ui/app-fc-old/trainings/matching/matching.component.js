import template from './matching.view.html';
import controller from './matching.controller.js';

export default {
  url       : '/training/matching/:setId',
  params: {
    setId: {
      value: '@setId'
    }
  },
  controller,
  template,
  controllerAs: 'matchingCtrl'
}