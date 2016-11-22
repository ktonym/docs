Ext.define('Docs.view.cabinet.CabinetTree',{
    extend: 'Ext.tree.Panel',
    alias: 'widget.cabinet-tree',
    title: 'Shelf -> Rows -> Clients',
    // requires: ['Docs.store.CabinetTree'],
    store: Ext.data.StoreManager.lookup('cabinetTreeStore'),
    lines: true,
    // rootVisible: false,
    hideHeaders: true,
    viewConfig: {
        preserveScrollOnRefresh: true
    },
    tools: [{
        type: 'expand',
        qtip: 'Expand All'
    },{
        type: 'collapse',
        qtip: 'Collapse All'
    },{
        type: 'refresh',
        qtip: 'Refresh Tree'
    }],
    columns: [{
        xtype: 'treecolumn',
        dataIndex: 'text',
        flex: 1
    }]
});