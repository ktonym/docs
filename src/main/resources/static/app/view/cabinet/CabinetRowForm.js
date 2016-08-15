Ext.define('Docs.view.cabinet.CabinetRowForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.cabinet-row-form',
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
            title: 'Shelf Row Entry',
            items: [
                {
                    xtype: 'numberfield',
                    fieldLabel: 'Row Number',
                    name: 'rowNumber'
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
                        itemId: 'addClientBtn',
                        disabled: true,
                        text: 'Add Client'
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
