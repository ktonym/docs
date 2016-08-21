Ext.define('Docs.view.LogonWindow',{
        extend: 'Ext.window.Window',
        xtype: 'logon-window',
        requires: ['Docs.view.LogonController'],
        controller: 'logon',
        closable: false,
        width: 300,
        height: 200,
        title: 'Please login to continue',
        bodyPadding: 10,
        iconCls: 'fa fa-key fa-lg',
        items: [
            {
                xtype: 'form',
                reference: 'form',
                bodyPadding: 15,
                defaults:{
                    xtype: 'textfield',
                    anchor: '100%',
                    allowBlank: false
                },
                msgTarget: 'under',
                items: [{
                    fieldLabel: 'User Name',
                    name: 'username',
                    validateOnBlur: true,
                    emptyText: 'Enter a Username'
                }, {
                    name: 'password',
                    fieldLabel: 'Password',
                    inputType: 'password',
                    validateOnBlur: true
                },{
                    xtype: 'toolbar',
                    ui:'footer',
                    layout: {
                        pack: 'end',
                        type: 'hbox'
                    },
                    items: [{
                        xtype: 'button',
                        text: 'Logon',
                        formBind: true,
                        handler: 'doLogin'
                    }]
                }]
            }

        ]
        
},
    function(){
        console.log('Logon window loaded..');
    }
);