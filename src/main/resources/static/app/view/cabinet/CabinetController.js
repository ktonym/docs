/**
 * Created by user on 22-Oct-16.
 */
Ext.define('Docs.view.cabinet.CabinetController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.cabinet',

    onReject: function () {
        var cab = this.getViewModel().get('current.cabinet');
        cab.reject();
    },
    onCommit: function () {
        var cab = this.getViewModel().get('current.cabinet');
        cab.commit();
    },
    onBeforeEdit: function () {
        console.log('Editing...');
    },
    onCancelEdit: function () {
        console.log('Cancelling..');
    },
    onAddBtnClick: function () {
        var me = this,
            view = me.getView(),
            vm = me.getViewModel(),
            store = vm.getStore('cabinets'),
            grid = view.lookupReference('cabinetGrid'),
            editor = grid.getPlugin('editing'),
            r = Ext.create('Docs.model.Cabinet',{});

        editor.cancelEdit();
        store.insert(0, r);
        vm.set('currentCabinet', r);
        editor.startEdit(0,0);
        debugger;
    },
    onSaveBtnClick: function () {
        this.onCommit();
    }
});
