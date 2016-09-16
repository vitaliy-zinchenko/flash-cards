export default class setTitleController {
  /* @ngInject */
  constructor(setService, $state) {
    var vm = this;
    //vm.name = 'setTitleController';

    cardsetCtrl.apply(vm, arguments);

    vm.parentName = vm.name;


    var id = $state.params.id;

    //create new set
    vm.saveSet = (set) => {
      /*setService.createSet(set).$promise.then(data => {
        console.log('Create new set:');
        console.log(data);
        debugger;
        $parent.currentSet = data;
        id = data.id; //TODO this is hotfix. without this one new cards for new cards set are sent to /api/card-set/new/cards/batch
      });*/
    };


    };
}
