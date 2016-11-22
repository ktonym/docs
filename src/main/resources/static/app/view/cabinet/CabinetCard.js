Ext.define('Docs.view.cabinet.CabinetCard',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.cabinet-card',
    requires: ['Ext.layout.container.HBox','Docs.view.cabinet.CabinetGrid',
        'Docs.view.cabinet.CabinetForm','Docs.view.cabinet.CabinetController','Docs.view.cabinet.CabinetModel'],
    viewModel: 'cabinet',
    controller: 'cabinet',
    layout: 'hbox',
    padding: 5,
    showBorder: true,
    items: [
        {
            xtype: 'cabinet-grid',
            flex: 2,
            showBorder: true
        },
        {
            xtype: 'cabinet-form',
            flex: 3,
            padding: 10
        }
    ]

});