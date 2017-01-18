Ext.define('Docs.view.cabinet.ClientForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.client-form',
    defaults:{
        xtype: 'textfield',
        anchor: '100%',
        allowBlank: false
    },
    items: [
        {
            xtype: 'hiddenfield',
            name: 'rowId',
            bind: '{current.row.rowId}'
        },
        {
            xtype: 'hiddenfield',
            name: 'clientId',
            bind: '{current.client.clientId}'
        },
        {
            fieldLabel: 'Client Name',
            name: 'clientName',
            bind: '{current.client.clientName}'
        },
        {
            fieldLabel: 'PIN',
            name: 'pin',
            bind: '{current.client.pin}'
        },
        {
            fieldLabel: 'Tel. No',
            name: 'tel',
            bind: '{current.client.tel}'
        },
        {
            fieldLabel: 'email',
            name: 'email',
            bind: '{current.client.email}'
        }
    ],
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-remove',
                itemId: 'deleteBtn',
                disabled: true,
                text: 'Delete'
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-save',
                itemId: 'saveBtn',
                text: 'Save',
                handler: 'onSaveClient',
                formBind: true
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-close',
                text: 'Close',
                handler: 'onCloseClientForm'
            }
        ]
    }

});