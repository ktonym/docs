Ext.define('Docs.view.LogonController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.logon',
    mixins: ['Docs.mixins.Logger'],

    onLogin: function (btn,e,eOpts) {
        var me=this;
        if(me.lookupReference('form').isValid()){
            me.doLogin;
        }
    },
    
    doLogin: function () {
        var me = this,
            form = me.lookupReference('form');
        me.getView().mask('Authenticating... Please wait...');
        // form.submit({
        //     clientValidation: true,
        //     url: 'docs/security/logon',
        //     scope: me,
        //     success: 'onLoginSuccess',
        //     failure: 'onLoginFailure'
        // });
        me.onLoginSuccess();
    },
    
    onLoginFailure: function () {
        
    },
    onLoginSuccess: function () {
        this.getView().unmask();
        this.getView().close();
        
       Ext.widget('app-main');
    }
});