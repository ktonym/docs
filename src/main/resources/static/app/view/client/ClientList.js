Ext.define('Docs.view.client.ClientList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.client-list',
    viewConfig: {
        markDirty: false,
        emptyText: 'There are no client records to display...'
    },
    store: 'Client',
    title: 'Client Listing',
    columns: [
        {
            dataIndex: 'name',
            text: 'Client Name',
            flex: 4
        },
        {
            dataIndex: 'id',
            name: 'Client Id',
            flex: 1
        },
        {
            dataIndex: 'tel',
            name: 'Telephone',
            flex: 2
        },
        {
            dataIndex: 'pin',
            name: 'KRA PIN',
            flex: 2
        }
    ]
});