Ext.define('Docs.view.cabinet.CabinetCard',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.cabinet-card',
    requires: ['Ext.layout.container.HBox','Docs.view.cabinet.CabinetTree','Docs.view.cabinet.CabinetForm'],
    layout: 'hbox',
    items: [
        {
            xtype: 'cabinet-tree',
            flex: 1
        },
        {
            xtype: 'cabinet-form',
            flex: 2
        }
    ]

});