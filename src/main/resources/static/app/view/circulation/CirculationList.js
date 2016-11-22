Ext.define('Docs.view.circulation.CirculationList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.circulation-list',
    // viewConfig: {
    //     markDirty: false,
    //     emptyText: 'There are no circulation records to display...'
    // },
    // store: 'Circulation', TODO create circulation store
    title: 'File Circulation',
    columns: [
        {
            xtype: 'gridcolumn',
            dataIndex: 'circulationId',
            text: 'Id',
            flex: 1
        },
        {
            xtype: 'gridcolumn',
            dataIndex: 'user',
            text: 'User',
            flex: 5
        },
        {
            xtype: 'gridcolumn',
            dataIndex: 'File',
            name: 'file',
            flex: 5
        },
        {
            xtype: 'datecolumn',
            dataIndex: 'borrowed',
            text: 'Borrowed',
            flex: 4
        },
        {
            xtype: 'datecolumn',
            dataIndex: 'returned',
            text: 'Returned',
            flex: 4
        },
        {
            xtype: 'gridcolumn',
            dataIndex: 'duration',
            text: 'Duration',
            flex: 2
        }
    ]
});