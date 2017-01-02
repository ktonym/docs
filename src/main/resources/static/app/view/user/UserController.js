/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
Ext.define('Docs.view.user.UserController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.user',
    
    doAddGroup: function () {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            rec = Ext.create('Docs.model.security.Group',{
                description: 'Enter brief description here.'
            }),
            win = Ext.create({
                xtype: 'window',
                height: 300,
                width: 450,
                title: 'New group',
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
    
    onGrpDblClick: function ( grid , record , item , index , e , eOpts) {
        var me = this,
            vw = me.getView(),
            vm = me.getViewModel(),
            win = Ext.create({
                xtype: 'window',
                height: 300,
                width: 450,
                title: 'Edit group',
                bodyPadding: '20 20 20 20',
                items: [
                    { xtype: 'group-form'}
                ]
            });
        vw.add([win]);
        win.show();
    },
    
    doAddUser: function () {
        
    },
    
    doSaveUser: function () {
        
    },

    closeWindow: function () {
        var me = this,
            vw = me.getView(),
            win = vw.down('window'),
            vm = me.getViewModel(),
            rec = vm.get('current.group');

        if(rec.dirty){
            Ext.Msg.confirm('Revert changes?','The window has unsaved changes, do you want to close and lose them?',
                function (btn) {
                    if(btn==='yes'){
                        rec.reject();
                        if(win){
                            vm.set('current.group',null);
                            win.close();
                        }
                    }
                }
            );
        } else {
            if(win){
                vm.set('current.group',null);
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
