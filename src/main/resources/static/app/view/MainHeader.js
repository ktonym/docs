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
                    iconCls: 'x-fa fa-database',
                    text: 'Cabinets and Shelves'
                },
                {
                    xtype: 'button',
                    itemId: 'circulationBtn',
                    iconCls: 'x-fa fa-refresh',
                    text: 'Circulation'
                    // bind: {
                    //     hidden: {}
                    // }
                },
                {
                    xtype: 'button',
                    itemId: 'usersBtn',
                    iconCls: 'x-fa fa-users',
                    text: 'Users'
                },'->',
                {
                    xtype: 'button',
                    itemId: 'logoutBtn',
                    iconCls: 'x-fa fa-sign-out',
                    text: 'Logout'
                }
            ]
        }
    ]
});
