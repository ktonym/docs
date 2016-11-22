/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('Docs.Application', {
    extend: 'Ext.app.Application',
    name: 'Docs',
    requires: ['Docs.view.LogonWindow','Docs.view.Main'],
    stores: [
        'Docs.store.CabinetTree'
    ],
    
    launch: function () {
        var me = this;
        me.logonWindow = Ext.widget('logon-window');
        me.logonWindow.show();
    },

    doAfterLogon: function(userObj){
        var me = this;
        me.getUser = function(){
            return userObj;
        },
        me.isAdmin = function(){
            return userObj.adminRole === 'Y';
        },
        Ext.create('Docs.view.Main');
        me.logonWindow.hide();
    },

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
