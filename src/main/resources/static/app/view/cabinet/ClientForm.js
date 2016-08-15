Ext.define('Docs.view.cabinet.ClientForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.client-form',
    layout: 'anchor',
    bodyPadding: 10,
    border: false,
    autoScroll: true,
    items: [
        {
            xtype: 'fieldset',
            padding: 10,
            fieldDefaults: {
                anchor: '100%',
                xtype: 'textfield'
            },
            title: 'Client Entry',
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'clientId'
                },
                {
                    fieldLabel: 'Client Name',
                    name: 'clientName'
                },
                {
                    fieldLabel: 'PIN',
                    name: 'pin'
                },
                {
                    fieldLabel: 'Tel. No',
                    name: 'tel'
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