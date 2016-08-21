Ext.define('Docs.view.circulation.CirculationForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.circulation-form',
    layout: 'anchor',
    bodyPadding: 10,
    border: false,
    autoScroll: true,
    items: [
        {
            xtype: 'fieldset',
            padding: 10,
            fieldDefaults: {
                anchor: '100%'
            },
            title: 'Circulation Entry',
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'circulationId'
                },
                {
                    xtype: 'combobox',
                    fieldLabel: 'User',
                    name: 'user',
                    queryMode: 'local',
                    store: 'User',
                    valueField: 'userId',
                    listConfig: {
                        minWidth: 300
                    },
                    tpl: Ext.create('Ext.XTemplate','<tpl for=".">',
                        '<div class="x-boundlist-item"><b>{fullName}</b> </div>','</tpl>'),
                    displayTpl: Ext.create('Ext.XTemplate','<tpl for=".">', '{fullName}', '</tpl>')
                },
                {
                    xtype: 'combobox',
                    fieldLabel: 'Client',
                    name: 'client',
                    queryMode: 'local',
                    store: 'Client',
                    valueField: 'clientId',
                    listConfig: {
                        minWidth: 300
                    },
                    tpl: Ext.create('Ext.XTemplate','<tpl for=".">',
                        '<div class="x-boundlist-item"><b>Shelf No:{cabinetId} Row:{rowNumber}</b>{name}</div>'),
                    displayTpl: Ext.create('Ext.XTemplate','<tpl for=".">','{name}','</tpl>')
                },
                {
                    xtype: 'combobox',
                    fieldLabel: 'File',
                    name: 'file',
                    queryMode: 'local',
                    store: 'File',
                    valueField: 'fileId',
                    listConfig: {
                        minWidth: 300
                    },
                    tpl: Ext.create('Ext.XTemplate','<tpl for=".">',
                        '<div class="x-boundlist-item">{code}<b>{status}</b></div>'),
                    displayTpl: Ext.create('Ext.XTemplate','<tpl for=".">','{code}','</tpl>')
                },
                {
                    xtype: 'datefield',
                    fieldLabel: 'Borrowed',
                    name: 'borrowed',
                    format: 'Ymd'
                },
                {
                    xtype: 'numberfield',
                    fieldLabel: 'Duration (days)',
                    name: 'duration'
                },
                {
                    xtype: 'datefield',
                    fieldLabel: 'Returned',
                    name: 'returned',
                    format: 'Ymd'
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