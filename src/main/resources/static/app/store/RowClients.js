/**
 * Created by anthony.kipkoech on 1/17/2017.
 */
Ext.define('Docs.store.RowClients',{
    extend: 'Ext.data.Store',
    alias: 'store.row-clients',
    requires: ['Docs.model.Client'],
    model: 'Docs.model.Client',
    proxy: {
        type: 'ajax',
        url:'client/findByRow.json',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },
    doFindByRow: function(rowId){
        this.load({
            params: {
                rowId: rowId
            }
        });
    }
});