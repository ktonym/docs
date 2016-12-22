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
                    xtype: 'hiddenfield',
                    bind: '{current.row.cabinetId}',
                    name: 'cabinetId'
                },
                {
                    xtype: 'hiddenfield',
                    bind: '{current.row.rowId}',
                    name: 'cabinetId'
                },
                {
                    xtype: 'numberfield',
                    fieldLabel: 'Row Number',
                    bind: '{current.row.rowNumber}',
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
                        iconCls: 'x-fa fa-remove',
                        itemId: 'deleteBtn',
                        disabled: true,
                        text: 'Delete'
                    },{
                        xtype: 'button',
                        itemId: 'addClientBtn',
                        iconCls: 'x-fa fa-plus',
                        disabled: true,
                        text: 'Add Client',
                        handler: 'onAddClient'
                    },{
                        xtype: 'button',
                        iconCls: 'x-fa fa-save',
                        itemId: 'saveBtn',
                        text: 'Save',
                        handler: 'onSaveRow'
                    }]
                }
            ]
        }
    ]
});
