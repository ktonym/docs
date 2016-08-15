Ext.define('Docs.store.CabinetTree',{
    extend: 'Ext.data.TreeStore',
    proxy: {
        type: 'ajax',
        url: 'cabinet/treenode.json'
    }
});