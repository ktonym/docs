/**
 * Created by anthony.kipkoech on 1/16/2017.
 */
Ext.define('Docs.store.CabinetRow',{
    extend: 'Ext.data.Store',
    alias: 'store.cabinet-row',
    requires: ['Docs.model.CabinetRow'],
    model: 'Docs.model.CabinetRow',
    proxy: {
        type:'ajax',
        url:'cabinetrow/findAll.json',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },
    doFindByCabinet: function(cabinetId){
        this.load({
            params: {
                cabinetId: cabinetId
            }
        });
    }
});