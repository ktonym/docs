Ext.define('Docs.view.cabinet.CabinetForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.cabinet-form',
    reference: 'cabinetForm',
    viewModel: {
        type: 'cabinet'
    },
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
                    fieldLabel: 'Cabinet Number',
                    bind: '{current.cabinet.cabinetId}',
                    editable: false
                },
                {
                    xtype: 'combobox',
                    name: 'cabinetType',
                    fieldLabel: 'Cabinet Type',
                    queryMode: 'local',
                    // store: 'CabinetType',
                    store: ['CLOSED','OPEN'],
                    // valueField: 'userId',
                    listConfig: {
                        minWidth: 300
                    },
                    bind: '{current.cabinet.cabinetType}'
                    // ,tpl: Ext.create('Ext.XTemplate','<tpl for=".">',
                    //     '<div class="x-boundlist-item"><b>{cabinetType}</b> </div>','</tpl>'),
                    // displayTpl: Ext.create('Ext.XTemplate','<tpl for=".">', '{cabinetType}', '</tpl>')
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
                        bind: {
                            disabled: '{!cabinetGrid.selection}'
                        },
                        text: 'Delete'
                    },{
                        xtype: 'button',
                        itemId: 'addRowBtn',
                        iconCls: 'x-fa fa-plus',
                        bind: {
                            disabled: '{!cabinetGrid.selection}'
                        },
                        handler: 'onAddRow',
                        text: 'Add Row',
                        formBind: true
                    },{
                        xtype: 'button',
                        iconCls: 'x-fa fa-save',
                        itemId: 'saveBtn',
                        handler: 'onSaveCabinet',
                        text: 'Save',
                        formBind: true
                    }]
                }
            ]
        }
    ]
});