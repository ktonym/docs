Ext.define('Docs.view.cabinet.CabinetRowForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.cabinet-row-form',
    layout: {
        type: 'anchor'
    },

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
        }
    ],
    bbar: {
        overflowHandler: 'menu',
        items: ['->',
            {
                xtype: 'button',
                itemId: 'addClientBtn',
                iconCls: 'x-fa fa-plus',
                disabled: true,
                text: 'Add Client',
                handler: 'onAddClient'
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-save',
                itemId: 'saveBtn',
                text: 'Save',
                formBind: true,
                handler: 'onSaveRow'
            }
        ]
    }

});
