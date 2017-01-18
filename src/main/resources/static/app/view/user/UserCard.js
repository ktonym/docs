Ext.define('Docs.view.user.UserCard',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.user-card',
    reference: 'userCard',
    requires: ['Docs.view.user.UserList','Docs.view.user.UserForm',
        'Docs.view.user.GroupList','Docs.view.user.GroupForm',
        'Docs.view.user.UserModel','Docs.view.user.UserController',
        'Docs.view.setup.CategoryList','Docs.view.setup.CategoryForm'],
    controller: 'user',
    viewModel: 'user',
    layout: 'border',
    items: [
        {
            region: 'west',
            width: 300,
            //split: true,
            reference: 'treelistContainer',
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            border: false,
            scrollable: 'y',
            items: [{
                xtype: 'treelist',
                reference: 'treelist',
                title: 'Admin Menu',
                iconCls: 'x-fa fa-gears',
                bind: '{navItems}',
                listeners: {
                    selectionchange: 'onNavTreeSelectionChange'
                }
            }]
        },
        {
            region: 'center',
            xtype: 'container',
            layout: 'card',
            reference: 'settingsCard',
            bodyPadding: 10,
            activeItem: 0,
            items:[
                {
                    xtype: 'group-list',
                    itemId: 'groupList'
                },
                {
                    xtype: 'user-list',
                    itemId: 'userList'
                },
                {
                    xtype: 'category-list',
                    itemId: 'categoryList'
                }
            ]

        }
    ]
});