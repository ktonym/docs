
Ext.define('Docs.view.cabinet.CabinetGrid',{
   
    extend: 'Ext.grid.Panel',
    alias: 'widget.cabinet-grid',
    reference: 'cabinetGrid',
    bind: { 
        store: '{cabinets}',
        selection: '{current.cabinet}'
    },
    plugins: [
        {
            ptype: 'rowediting',
            clicksToEdit: 2,
            pluginId: 'editing',
            listeners: {
                beforeedit: 'onBeforeEdit',
                canceledit: 'onCancelEdit'
            }
        }
    ],
    showBorder: true,
    padding: 10,
    selType: 'rowmodel',
    columns: [
        {
            dataIndex: 'cabinetId',
            text: 'Cabinet No.',
            flex: 1
            // ,
            // editor: {
            //     xtype: 'numberfield',
            //     allowBlank: false
            // }
        },{
            dataIndex: 'cabinetType',
            text: 'Cabinet Type',
            flex: 2,
            editor: {
                xtype: 'combo',
                store: ['OPEN','CLOSED']
            }
        }],
    dockedItems: [
        {
            xtype: 'toolbar',
            dock: 'top',
            ui: 'footer',
            items: [
                { xtype: 'button', handler: 'onAddBtnClick', iconCls: 'fa fa-plus', text: 'Add' },
                { xtype: 'button', handler: 'onSaveBtnClick', iconCls: 'fa fa-save', text: 'Save' }
            ]
        }
    ]
});