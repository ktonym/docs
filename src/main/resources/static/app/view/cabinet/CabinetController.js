/**
 * Created by user on 22-Oct-16.
 */
Ext.define('Docs.view.cabinet.CabinetController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.cabinet',
    mixins: {
        ctlMixin: 'Docs.util.ControllerMixin'
    },

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
    onCloseClientForm: function(){
      var me = this,
          vw = me.getView(),
          win = vw.down('window'),
          vm = me.getViewModel(),
          rec = vm.get('current.client');
      ctlMixin.checkDirtyBeforeClose(rec,win);
    },
    onAddCabinet: function () {
        var me = this,
            view = me.getView(),
            vm = me.getViewModel(),
            panel = view.lookupReference('cabinetPanel'),
            //store = vm.getStore('cabinets'),
            //grid = view.lookupReference('cabinetGrid'),
            form = panel.down('cabinet-form'),
            editor,
            r = Ext.create('Docs.model.Cabinet',{
                id: null
            });
        // if(grid){
        //     editor = grid.getPlugin('editing');
        //     editor.cancelEdit();
        //     editor.startEdit(0,0);
        // }

        //store.insert(0, r);
        vm.set('current.cabinet', r);
        panel.getLayout().setActiveItem(form);

    },
    onSaveBtnClick: function () {
        this.onCommit();
    },
    onSaveCabinet: function () {
        var me = this,
            vm = me.getViewModel(),
            cab = vm.get('current.cabinet');
        //console.log(cab);
        cab.save({
            callback:  function(record, operation, success) {
                var result = Docs.util.Util.decodeJSON(operation._response.responseText);
                if (success) {
                    Docs.util.Util.showToast('Success! Record saved.');
                    me.doRefreshTree();
                } else {
                    Docs.util.Util.showErrorMsg(result.msg);
                }
            }
        });
    },
    onAddRow: function (cabNode) {
        var me = this,
            vm = me.getViewModel(),
            vw = me.getView(),
            //panel = vw.lookupReference('cabinetPanel'),
            cabId = vm.get('current.cabinet.cabinetId'),
            rec = Ext.create('Docs.model.CabinetRow',{
                cabinetId : cabId ? cabId : Ext.Number.from(cabNode.getId().split('_')[1]),
                rowId: null
            }),
            win = Ext.create({
                xtype: 'window',
                height: 165,
                width: 400,
                title: 'New Cabinet Row',
                iconCls: 'x-fa fa-edit',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'cabinet-row-form'}
                ]
            });

        vm.set('current.row',rec);
        vw.add([win]);
        win.show();
    },
    onSaveRow: function () {
        var me = this,
            vw = me.getView(),
            panel = vw.lookupReference('cabinetPanel'),
            vm = me.getViewModel(),
            row = vm.get('current.row'),
            grid = panel.down('row-list');
            //cabinetId = vm.get('current.cabinet.cabinetId');
        row.save({
            callback: function(record, operation, success) {

                var result = Docs.util.Util.decodeJSON(operation._response.responseText);
                if (success) {
                    Docs.util.Util.showToast('Success! Record saved.');
                    if(record.store === undefined){
                        grid.getStore().add(record);
                    }
                    vm.getStore('allCabinetRows').load();
                    me.doRefreshTree();
                    vw.down('window').close();
                } else {
                    Docs.util.Util.showErrorMsg(result.msg);
                }
            }
        });
    },
    onRowDblClick: function (grid , record , item , index , e , eOpts) {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 165,
                width: 400,
                title: 'Edit Row',
                iconCls: 'x-fa fa-edit',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'cabinet-row-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },
    onRowDetailClick: function(grid,rowIndex, colIndex){
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            store = vm.getStore('rowClients'),
            panel = vw.lookupReference('cabinetPanel'),
            target = panel.down('client-list'),
            rec = grid.getStore().getAt(rowIndex);

        store.doFindByRow(rec.get('rowId'));

        panel.getLayout().setActiveItem(target);

    },
    onAddClient: function (rowNode) {

        var me = this,
            vm = me.getViewModel(),
            vw = me.getView(),
            panel = vw.lookupReference('cabinetPanel'),
            rowId = vm.get('current.row.rowId'),
            tab = panel.down('client-tab'),
            rec,
            win = Ext.create({
                xtype: 'window',
                height: 300,
                width: 375,
                title: 'New Client',
                iconCls: 'x-fa fa-plus',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'client-form'}
                ]
            });
        if(rowId) {
            rec = Ext.create('Docs.model.Client', {
                rowId: rowId ? rowId : Ext.Number.from(rowNode.getId().split('_')[1]),
                clientId: null
            });
            vm.set('current.client',rec);
            vw.add([win]);
            win.show();
        } else {
            Ext.Msg.alert('Missing field', 'Kindly select a cabinet row before adding a client.');
        }
    },
    onSaveClient: function () {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            panel = vw.lookupReference('cabinetPanel'),
            grid = panel.down('client-list'),
            client = vm.get('current.client');
        client.save({
            callback:  function(record, operation, success) {
                var result = Docs.util.Util.decodeJSON(operation._response.responseText);
                if (success) {
                    Docs.util.Util.showToast('Client successfully saved.');
                    me.doRefreshTree();
                    if(record.store === undefined){
                        grid.getStore().add(record);
                    }
                    vm.getStore('clients').load();
                    Ext.Msg.confirm('Navigate to client?','Do you wish to navigate to the client management interface?',
                        function (btn) {
                            if(btn==='yes'){
                                panel.getLayout().setActiveItem('client-tab');
                            }
                        }
                    );
                } else {
                    Docs.util.Util.showErrorMsg(result.msg);
                }
            }
        });
    },
    onClientDblClick: function (grid , record , item , index , e , eOpts) {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 300,
                width: 375,
                title: 'Edit Client',
                iconCls: 'x-fa fa-edit',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'client-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },
    onClientDetailClick: function(grid,rowIndex, colIndex){
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            store = vm.getStore('clientVolumes'),
            panel = vw.lookupReference('cabinetPanel'),
            target = panel.down('client-tab'),
            rec = grid.getStore().getAt(rowIndex);

        store.doFindByClient(rec.get('clientId'));

        panel.getLayout().setActiveItem(target);
    },
    onAddVolume: function(){
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            rec = Ext.create('Docs.model.Volume',{
                clientId: vm.get('current.client.clientId')
            }),
            win = Ext.create({
                xtype: 'window',
                height: 220,
                width: 400,
                title: 'New volume',
                iconCls: 'x-fa fa-plus',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'volume-form'}
                ]
            });
        vm.set('current.volume',rec);
        // Need to load categoryRefs
        //vm.getStore('categoryRefs').load();
        vw.add([win]);
        win.show();
    },
    onSaveVolume: function () {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            panel = vw.lookupReference('con'),
            rec = vm.get('current.volume');

        rec.save({
            callback:  function(record, operation, success) {
                var result = Docs.util.Util.decodeJSON(operation._response.responseText);
                if (success) {
                    Docs.util.Util.showToast('Volume saved successfully.');
                    if(record.store===undefined){
                        vm.getStore('clientVolumes').add(record);
                    }
                } else {
                    Docs.util.Util.showErrorMsg(result.msg);
                }
            }
        });

    },
    onVolDblClick: function (grid , record , item , index , e , eOpts) {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 220,
                width: 400,
                title: 'Edit Volume',
                iconCls: 'x-fa fa-edit',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'volume-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },
    onAddFile: function(){
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            rec = Ext.create('Docs.model.File',{
                volumeId: vm.get('current.volume.volumeId')
            }),
            win = Ext.create({
                xtype: 'window',
                height: 400,
                width: 400,
                title: 'New File',
                iconCls: 'x-fa fa-plus',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'file-form'}
                ]
            });
        vm.set('current.file',rec);
        // Need to load categoryRefs
        //vm.getStore('categoryRefs').load();
        vw.add([win]);
        win.show();
    },
    doSaveFile: function(){

        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            //panel = vw.lookupReference('con'),
            rec = vm.get('current.file');

        rec.save({
            callback:  function(record, operation, success) {
                var result = Docs.util.Util.decodeJSON(operation._response.responseText);
                if (success) {
                    Docs.util.Util.showToast('File saved successfully.');
                    if(record.store===undefined){
                        vm.getStore('volumeFiles').add(record);
                    }
                } else {
                    Docs.util.Util.showErrorMsg(result.msg);
                }
            }
        });
    },
    onFileDblClick: function(){
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 400,
                width: 400,
                title: 'Edit File',
                iconCls: 'x-fa fa-edit',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'file-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },
    doAfterActivate: function(){
        var me = this,
            vm = me.getViewModel();
        vm.get('cabinetTree').on('append' , me.doSetTreeIcon, me);
        // me.getCompanyTree().getView().on('beforedrop' , me.isDropAllowed, me);
        // me.getCompanyTree().getView().on('drop', me.doChangeParent, me);
        // me.getCompanyTreeStore().load();

    },
    onSelectTreeItem: function(tree,record){
        var me = this,
            vw = me.getView(),
            panel = vw.lookupReference('cabinetPanel'),
            vm = me.getViewModel(),
            recIdSplit = record.getId().split('_');
        console.log(recIdSplit);
        if (recIdSplit[0]==='S') {
            var cabinetId = Ext.Number.from(recIdSplit[1]),
                store = vm.getStore('cabinetRows'),
                rec = vm.get('cabinets').findRecord('cabinetId',cabinetId),
                target = panel.down('row-list');
            if (!Ext.isEmpty(rec)) {
                vm.set('current.cabinet',rec);
                store.doFindByCabinet(cabinetId);
                panel.getLayout().setActiveItem(target);
            }
        } else if (recIdSplit[0]==='R') {
            var rowId = Ext.Number.from(recIdSplit[1]),
                store = vm.getStore('rowClients'),
                rec = vm.get('allCabinetRows').findRecord('rowId', rowId),
                target = panel.down('client-list');
            debugger;
            console.log(vm.get('allCabinetRows'));
            if (!Ext.isEmpty(rec)) {
                vm.set('current.row', rec);
                store.doFindByRow(rowId);
                panel.getLayout().setActiveItem(target);
            }
        } else if (recIdSplit[0]==='C') {
            var clientId = Ext.Number.from(recIdSplit[1]),
                rec = vm.get('clients').findRecord('clientId', clientId),
                store = vm.getStore('volumes'),
                target = panel.down('client-tab'); //was 'client-form'
            /*console.log(rec);
            debugger;*/
            if (!Ext.isEmpty(rec)) {
                vm.set('current.client', rec);
                store.load();
                panel.getLayout().setActiveItem(target);
                console.info('Showing current client clientId');
                console.log(vm.get('current.client.clientId'));
            }
        } else {
            console.log('Invalid record selected?');
        }
    },
    doSetTreeIcon: function(store, node, refNode, eOpts) {
        var nodeType = node.getId().substring(0, 1);
        if (nodeType === 'S') {
            node.set('iconCls', 'x-fa fa-database');
        } else if (nodeType === 'R') {
            node.set('iconCls', 'x-fa fa-th');
        } else if (nodeType === 'C') {
            node.set('iconCls', 'task');
        }
    },
    onRightClick: function(tree, record, item, index, e, eOpts ){
        //var cabItems [];
        var me = this,
            menu_grid = new Ext.menu.Menu({ items:
            [
                {   text: 'More details',
                    iconCls: 'x-fa fa-newspaper-o',
                    handler: function(){
                        console.log("More details");
                        console.info(record);
                    }
                },
                {
                    text: 'Add',
                    iconCls: 'x-fa fa-plus',
                    handler: function () {
                        var nodeType = record.getId().substring(0,1);
                        switch(nodeType) {
                            case 'S':
                                me.onAddRow(record);
                                break;
                            case 'R':
                                me.onAddClient(record);
                                break;
                            case 'C':
                                me.onAddVolume(record);
                                break;
                            default:
                                me.onAddCabinet(); // root node
                        }

                    }
                },
                {
                    text: 'Delete',
                    iconCls: 'x-fa fa-remove',
                    handler: function () {
                        console.log("Delete");
                    }
                }
            ]
        });
        var position = [e.getX(),e.getY()];
        e.stopEvent();
        menu_grid.showAt(position);
    },
    doRefreshTree: function () {
        var me = this,
            vm = me.getViewModel(),
            treeStore = vm.getStore('cabinetTree');
        treeStore.load();
        vm.getStore('allCabinetRows').load();
    }
});
