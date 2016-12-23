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
        console.log(cab);
        cab.save({
            failure: function (record, operation) {
                Docs.util.Util.showErrorMsg(operation.responseText);
            },
            success: function (record, operation) {
                Docs.util.Util.showToast('Shelf successfully saved.');
                me.doRefreshTree();
            }
        });
    },
    onAddRow: function (cabNode) {
        var me = this,
            vm = me.getViewModel(),
            vw = me.getView(),
            panel = vw.lookupReference('cabinetPanel'),
            cabId = vm.get('current.cabinet.cabinetId'),
            form = panel.down('cabinet-row-form'),
            rec;
        rec = Ext.create('Docs.model.CabinetRow',{
            cabinetId : cabId ? cabId : Ext.Number.from(cabNode.getId().split('_')[1]),
            rowId: null
        });

        vm.set('current.row',rec);
        panel.getLayout().setActiveItem(form);
    },
    onSaveRow: function () {
        var me = this,
            vw = me.getView(),
            panel = vw.lookupReference('cabinetPanel'),
            form = panel.down('cabinet-row-form'),
            delBtn = form.down('#deleteBtn'),
            addClientBtn = form.down('#addClientBtn'),
            vm = me.getViewModel(),
            row = vm.get('current.row'),
            cabinetId = vm.get('current.cabinet.cabinetId');
            //store = vm.get('cabinetTree');
        row.save({
            failure: function (record, operation) {
                Docs.util.Util.showErrorMsg(operation.responseText);
            },
            success: function (record, operation) {
                Docs.util.Util.showToast('Row successfully saved.');
                delBtn.enable();
                addClientBtn.enable();
                // var nodeId = 'S_' + cabinetId,
                //     tree = vw.lookupReference('cabinetPanel').up().down('cabinet-tree'),
                //     parentNode = tree.getNodeById(nodeId);
                // parentNode.appendChild({
                //     leaf: true,
                //     expanded: false,
                //     text: 'Row x'
                // });
                vm.getStore('allCabinetRows').load();
                me.doRefreshTree();
            }
        });
    },
    onAddClient: function (rowNode) {

        var me = this,
            vm = me.getViewModel(),
            vw = me.getView(),
            panel = vw.lookupReference('cabinetPanel'),
            rowId = vm.get('current.row.rowId'),
            form = panel.down('client-form'),
            rec;
        rec = Ext.create('Docs.model.Client',{
            rowId : rowId ? rowId : Ext.Number.from(rowNode.getId().split('_')[1]),
            clientId: null
        });

        vm.set('current.client',rec);
        panel.getLayout().setActiveItem(form);

    },
    onSaveClient: function () {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            client = vm.get('current.client');
            //cabinetId = vm.get('current.cabinet.cabinetId');
        //store = vm.get('cabinetTree');
        client.save({
            failure: function (record, operation) {
                Docs.util.Util.showErrorMsg(operation.responseText);
            },
            success: function (record, operation) {
                Docs.util.Util.showToast('Client successfully saved.');
                console.log(record);
                me.doRefreshTree();
                vm.getStore('clients').load();
            }
        });
    },
    onAddCategory: function(record){

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
                rec = vm.get('cabinets').findRecord('cabinetId',cabinetId),
                form = panel.down('cabinet-form');
            if (!Ext.isEmpty(rec)) {
                vm.set('current.cabinet',rec);
                panel.getLayout().setActiveItem(form);
            }
        } else if (recIdSplit[0]==='R') {
            var rowId = Ext.Number.from(recIdSplit[1]),
                rec = vm.get('allCabinetRows').findRecord('rowId', rowId),
                form = panel.down('cabinet-row-form');
            console.log(rec);
            debugger;
            if (!Ext.isEmpty(rec)) {
                vm.set('current.row', rec);
                panel.getLayout().setActiveItem(form);
            }
        } else if (recIdSplit[0]==='C') {
            var clientId = Ext.Number.from(recIdSplit[1]),
                rec = vm.get('clients').findRecord('clientId', clientId),
                form = panel.down('client-form');
            console.log(rec);
            debugger;
            if (!Ext.isEmpty(rec)) {
                vm.set('current.client', rec);
                panel.getLayout().setActiveItem(form);
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
                                me.onAddCategory(record);
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
