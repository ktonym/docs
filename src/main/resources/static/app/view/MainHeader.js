Ext.define('Docs.view.MainHeader',{
    extend: 'Ext.container.Container',
    alias: 'widget.main-header',
    requires: ['Ext.toolbar.Toolbar'],
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'container',
            width: 300
        },
        {
            xtype: 'toolbar',
            ui: 'footer',
            flex: 1,
            layout: {
                type: 'hbox',
                pack: 'end',
                padding: '20 20 0 0'
            },
            items: [
                {
                    xtype: 'button',
                    itemId: 'cabinetBtn',
                    text: 'Cabinets and Shelves'
                },
                {
                    xtype: 'button',
                    itemId: 'circulationBtn',
                    text: 'Circulation'
                    // bind: {
                    //     hidden: {}
                    // }
                },
                {
                    xtype: 'button',
                    itemId: 'usersBtn',
                    text: 'Users'
                },'->',
                {
                    xtype: 'button',
                    itemId: 'logoutBtn',
                    text: 'Logout'
                }
            ]
        }
    ]
});
