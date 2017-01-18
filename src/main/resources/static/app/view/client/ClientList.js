Ext.define('Docs.view.client.ClientList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.client-list',
    viewConfig: {
        markDirty: true,
        emptyText: 'There are no client records to display...'
    },
    bind: {
        store: '{rowClients}',
        selection: '{current.client}'
    },
    listeners:{
        itemdblclick: 'onClientDblClick'
    },
    //title: 'Client Listing',
    columns: [
        {
            dataIndex: 'clientId',
            text: '#',
            flex: 1
        },
        {
            dataIndex: 'clientName',
            text: 'Client Name',
            flex: 4
        },
        {
            dataIndex: 'tel',
            text: 'Telephone',
            flex: 2
        },
        {
            dataIndex: 'pin',
            text: 'KRA PIN',
            flex: 2
        },
        {
            xtype: 'actioncolumn',
            text: 'Actions',
            flex: 1,
            items: [
                {
                    xtype: 'button',
                    iconCls: 'x-fa fa-chevron-right',
                    handler: 'onClientDetailClick',
                    tooltip: 'View details'
                },'-',
                {
                    xtype: 'button',
                    iconCls: 'x-fa fa-close',
                    handler: 'onClientDelete',
                    tooltip: 'Remove client'
                }
            ]
        }
    ],
    bbar:{
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-plus',
                text: 'Add Client',
                handler: 'onAddClient'
            }
        ]
    }
});