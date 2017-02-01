/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
Ext.define('Docs.view.user.UserController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.user',

    onGroupSelect: function(id){
        var me = this,
            vm = me.getViewModel(),
            grid = me.lookupReference('groupList'),
            store = vm.getStore('groups'),
            record = store.findRecord('id',id);
        if(record){
            grid.getSelectionModel().select(record);
        }
    },

    onGroupItemClick: function(view,record,item,index,e,eOpts){
        this.redirectTo('groups/',+record.get('id'));
    },
    
    doAddGroup: function () {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            rec = Ext.create('Docs.model.Group',{
                description: 'Enter brief description here.'
            }),
            win = Ext.create({
                xtype: 'window',
                height: 300,
                width: 450,
                title: 'New group',
                iconCls: 'x-fa fa-group',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'group-form'}
                ]
            });
        vm.set('current.group',rec);

        vw.add([win]);
        win.show();
    },
    
    doSaveGroup: function () {
        var me = this,
            vm = me.getViewModel(),
            store = vm.getStore('groups'),
            rec = vm.get('current.group');

        rec.save({
           success: function (record,operation) {
               Docs.util.Util.showToast('Group saved successfully.');
               if(record.store===undefined){
                   store.add(record);
               }
           },
           failure: function (record,operation) {
               Docs.util.Util.showErrorMsg(operation.responseText);
           }
        });

    },
    doDelGroup: function () {
        Ext.Msg.alert('Not allowed',"Deletion of groups has not been configured yet.");
    },
    
    onGrpDblClick: function ( grid , record , item , index , e , eOpts) {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 300,
                width: 450,
                title: 'Edit group',
                iconCls: 'x-fa fa-group',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'group-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },
    
    doAddUser: function () {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            rec = Ext.create('Docs.model.User',{
                groupId: '{current.group.id}'
            }),
            win = Ext.create({
                xtype: 'window',
                height: 400,
                width: 450,
                title: 'New user',
                iconCls: 'x-fa fa-user',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'user-form'}
                ]
            });
        vm.set('current.user',rec);

        vw.add([win]);
        win.show();
    },
    
    doSaveUser: function () {
        var me = this,
            vm = me.getViewModel(),
            store = vm.getStore('users'),
            rec = vm.get('current.user');

        rec.save({
            success: function (record,operation) {
                Docs.util.Util.showToast('User saved successfully.');
                if(record.store===undefined){
                    store.add(record);
                }
            },
            failure: function (record,operation) {
                Docs.util.Util.showErrorMsg(operation.responseText);
            }
        });
    },


    onUsrDblClick: function (grid , record , item , index , e , eOpts) {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 400,
                width: 450,
                title: 'Edit group',
                iconCls: 'x-fa fa-user',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'user-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },

    doAddCat: function () {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            rec = Ext.create('Docs.model.Category',{
            }),
            win = Ext.create({
                xtype: 'window',
                height: 250,
                width: 400,
                title: 'New Category',
                iconCls: 'x-fa fa-object-group',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'category-form'}
                ]
            });
        vm.set('current.category',rec);

        vw.add([win]);
        win.show();
    },

    doSaveCat: function () {
        var me = this,
            vm = me.getViewModel(),
            store = vm.getStore('categories'),
            rec = vm.get('current.category');

        rec.save({
            success: function (record,operation) {
                Docs.util.Util.showToast('Category saved successfully.');
                if(record.store===undefined){
                    store.add(record);
                }
            },
            failure: function (record,operation) {
                Docs.util.Util.showErrorMsg(operation.responseText);
            }
        });
    },

    onCatDblClick: function (grid , record , item , index , e , eOpts) {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 250,
                width: 400,
                title: 'Edit Category',
                iconCls: 'x-fa fa-object-group',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'category-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },

    closeWindow: function () {
        var me = this,
            vw = me.getView(),
            win = vw.down('window'),
            vm = me.getViewModel(),
            rec = vm.get('current.group');

        me.checkDirty(rec,win);

    },

    closeUsrWindow: function () {
        var me = this,
            vw = me.getView(),
            win = vw.down('window'),
            vm = me.getViewModel(),
            rec = vm.get('current.user');
        me.checkDirty(rec,win);
    },

    closeCatWindow: function () {
        var me = this,
            vw = me.getView(),
            win = vw.down('window'),
            vm = me.getViewModel(),
            rec = vm.get('current.category');
        me.checkDirty(rec,win);
    },

    checkDirty: function (rec,win) {
        if(rec.dirty){
            Ext.Msg.confirm('Revert changes?','The window has unsaved changes, do you want to close and lose them?',
                function (btn) {
                    if(btn==='yes'){
                        rec.reject();
                        if(win){
                            win.close();
                        }
                    }
                }
            );
        } else {
            if(win){
                win.close();
            }
        }
    },

    onNavTreeSelectionChange: function (tree, node){
       /* if( node && node.get('view')){
            this.redirectTo(node.get("routeId"));
        }*/
       if(node && node.get('routeId')){
           var me = this,
               vw = me.getView(),
               place = vw.lookupReference('settingsCard');
           place.getLayout().setActiveItem(node.get("routeId"));

       }
    }

});
