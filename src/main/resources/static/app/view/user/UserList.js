Ext.define('Docs.view.user.UserList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.user-list',
    viewConfig: {
        markDirty: false,
        emptyText: 'There are no user records to display...'
    },
    store: 'User',
    title: 'Users Listing',
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
        }
    ]
});
