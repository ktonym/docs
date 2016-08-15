Ext.define('Docs.view.user.UserForm',{
     extend: 'Ext.form.Panel',
     alias: 'widget.user-form',
     layout: {
         type: 'anchor'
     },
    items: [
        {
            xtype: 'fieldset',
            padding: 10,
            fieldDefaults: {
                anchor: '100%',
                xtype: 'textfield'
            },
            title: 'User Entry',
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'userId'
                },
                {
                    fieldLabel: 'First Name',
                    name: 'firstName'
                },
                {
                    fieldLabel: 'Surname',
                    name: 'surname'
                },
                {
                    xtype: 'toolbar',
                    ui: 'footer',
                    layout: {
                        pack: 'end',
                        type: 'hbox'
                    },
                    items: [{
                        xtype: 'button',
                        iconCls: 'delete',
                        itemId: 'deleteBtn',
                        disabled: true,
                        text: 'Delete'
                    },{
                        xtype: 'button',
                        iconCls: 'save',
                        itemId: 'saveBtn',
                        text: 'Save'
                    }]
                }
            ]
        }
    ]
});
