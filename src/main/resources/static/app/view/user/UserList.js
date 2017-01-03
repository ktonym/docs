Ext.define('Docs.view.user.UserList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.user-list',
    reference: 'userList',
    // viewConfig: {
    //     markDirty: false,
    //     emptyText: 'There are no user records to display...'
    // },
    // store: 'User', TODO create user store
    bind: {
        store: '{users}',
        selection: '{current.user}'
    },
    listeners: {
        itemdblclick: 'onUsrDblClick'
    },
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-plus',
                itemId: 'addUserBtn',
                text: 'Add',
                handler: 'doAddUser'
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-remove',
                itemId: 'deleteBtn',
                bind: {
                    disabled: '{!userList.selection}'
                },
                text: 'Delete',
                handler: 'doDelUser'
            }
        ]
    },

    title: 'Users Listing',
    iconCls: 'x-fa fa-user',
    columns: [
        {
            dataIndex: 'userId',
            text: 'User Id',
            flex: 1
        },
        {
            dataIndex: 'firstName',
            text: 'First Name',
            flex: 4
        },
        {
            dataIndex: 'surname',
            text: 'Surname',
            flex: 4
        },
        {
            dataIndex: 'expired',
            text: 'Expired',
            flex: 2
        },
        {
            dataIndex: 'locked',
            text: 'Locked',
            flex: 2
        }
    ]
});
