Ext.define('Docs.view.cabinet.CabinetCard',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.cabinet-card',
    reference: 'cabinetCard',
    requires: ['Ext.layout.container.HBox',/*'Docs.view.cabinet.CabinetGrid',*/'Docs.view.cabinet.CabinetTree',
        'Docs.view.cabinet.CabinetForm','Docs.view.cabinet.CabinetController','Docs.view.cabinet.CabinetModel'],
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
            items: [
                {
                    xtype: 'panel',
                    html: '<br><br><h4>Welcome, to proceed please select an item on the <b>left</b></h4>'
                },
                {
                    xtype: 'cabinet-form',
                    // flex: 3,
                    padding: 10
                },
                {
                    xtype: 'cabinet-row-form',
                    // flex: 3,
                    padding: 10
                },
                {
                    xtype: 'client-form',
                    // flex: 3,
                    padding: 10
                }
            ]

        }

    ]

});