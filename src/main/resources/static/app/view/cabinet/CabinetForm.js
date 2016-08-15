Ext.define('Docs.view.cabinet.CabinetForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.cabinet-form',
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
            title: 'Shelf Entry',
            items: [
                {
                    xtype: 'numberfield',
                    name: 'cabinetId',
                    fieldLabel: 'Cabinet Number'
                },
                {
                    xtype: 'combobox',
                    name: 'cabinetType',
                    queryMode: 'local',
                    store: 'CabinetType',
                    valueField: 'userId',
                    listConfig: {
                        minWidth: 300
                    },
                    tpl: Ext.create('Ext.XTemplate','<tpl for=".">',
                        '<div class="x-boundlist-item"><b>{cabinetType}</b> </div>','</tpl>'),
                    displayTpl: Ext.create('Ext.XTemplate','<tpl for=".">', '{cabinetType}', '</tpl>')
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
                        itemId: 'addRowBtn',
                        disabled: true,
                        text: 'Add Row'
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