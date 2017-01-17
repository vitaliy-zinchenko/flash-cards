export default class setsPageController  {
  /* @ngInject */
  constructor($state) {
    console.log('setsPageController');

// TODO: get "page" and "size" from URL (or where from?)
    this.$state = $state;
    this.initialize();
  }

//  goToTranslate() {
//    this.$state.go('training-translate');
//  }

  initialize() {

  }
}
