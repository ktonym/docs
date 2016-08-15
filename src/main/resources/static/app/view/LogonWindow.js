Ext.define('Docs.view.LogonWindow',{
    extend: 'Ext.panel.Window',
        xtype: 'logon-window',
        width: 300,
        height: 200,
        title: 'Please login to continue',
        items: [{
            xtype: 'textfield',
            fieldLabel: 'User Name',
            name: 'username',
            allowBlank: false,
            validateOnBlur: true,
            emptyText: 'Enter a Username'
        }, {
            xtype: 'textfield',
            name: 'password',
            fieldLabel: 'Password',
            inputType: 'password',
            validateOnBlur: true,
            allowBlank: false
        },{
            xtype: 'toolbar',
            ui:'footer',
            layout: {
                pack: 'end',
                type: 'hbox'
            },
            items: [{
                xtype: 'button',
                text: 'Logon'
            }]
        }]
},
    function(){
        Ext.Msg.alert("Login Window Loaded");
    }
);