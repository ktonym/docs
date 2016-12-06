Ext.define('Docs.view.cabinet.CabinetTree',{
    extend: 'Ext.tree.Panel',
    alias: 'widget.cabinet-tree',
    // reference: 'cabTree',
    title: 'Document manager',
    requires: ['Docs.store.CabinetTree'],
    bind: {
        store: '{cabinetTree}'
    },
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
        flex: 1,
        align: 'left'
    }],
    listeners: {
        itemclick : 'onSelectTreeItem',
        itemcontextmenu: 'onRightClick',
        activate: 'doAfterActivate'
    }
});