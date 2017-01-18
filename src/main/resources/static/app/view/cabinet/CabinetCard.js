Ext.define('Docs.view.cabinet.CabinetCard',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.cabinet-card',
    reference: 'cabinetCard',
    requires: ['Ext.layout.container.HBox','Docs.view.cabinet.ClientTab','Docs.view.cabinet.CabinetTree','Docs.view.client.ClientList',
        'Docs.view.cabinet.CabinetForm','Docs.view.cabinet.RowList','Docs.view.cabinet.CabinetController','Docs.view.cabinet.CabinetModel',
        'Docs.view.cabinet.CabinetGrid'],
    viewModel: 'cabinet',
    controller: 'cabinet',
    layout: 'border',
    // padding: 5,
    showBorder: true,
    items: [
        {
            xtype: 'cabinet-tree',
            region: 'west',
            width: 300,
            showBorder: true
        },
        {
            xtype: 'panel',
            reference: 'cabinetPanel',
            region: 'center',
            layout: {
                type: 'card'
            },
            activeItem: 0,
            defaults: {
              padding: 10
            },
            items: [
                {
                    xtype: 'panel',
                    padding: 10,
                    html: '<br><br><h3>Welcome, <br>to proceed please select an item on the <b>left</b></h3>'
                },
                {
                    xtype: 'cabinet-grid'
                },
                {
                    xtype: 'cabinet-form'
                },
                {
                    xtype: 'row-list'
                },
                {
                    xtype: 'client-list'
                },
                {
                    xtype: 'client-tab'
                }
            ]

        }

    ]

});