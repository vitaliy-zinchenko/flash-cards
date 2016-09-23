export default class setTitleController {
  /* @ngInject */
  constructor($state, setService) {
    var vm = this;
    console.log(this.currentSet)
    vm.name = 'setTitleController';

    var id = $state.params.id;

    //create new set
    vm.saveSet = (set) => {
      console.log(set);
      console.log('saveSet');
      debugger;
      console.log(vm.currentSet + ` - from title controller`);

      vm.currentSet = set;


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
