Ext.define('Docs.view.cabinet.ClientForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.client-form',
    layout: {
        type: 'anchor'
    },
    items: [
        {
            xtype: 'fieldset',
            padding: 10,
            fieldDefaults: {
                anchor: '100%'
            },
            title: 'Client Entry',
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'clientId',
                    bind: '{current.client.clientId}'
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Client Name',
                    name: 'clientName',
                    bind: '{current.client.clientName}'
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'PIN',
                    name: 'pin',
                    bind: '{current.client.pin}'
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Tel. No',
                    name: 'tel',
                    bind: '{current.client.tel}'
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
                        itemId: 'addCategoryBtn',
                        disabled: true,
                        text: 'Add Category'
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