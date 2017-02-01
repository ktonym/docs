/**
 * Created by anthony.kipkoech on 1/20/2017.
 */
Ext.define('Docs.store.Files',{
    extend: 'Ext.data.Store',
    alias: 'store.files',
    requires: ['Docs.model.File'],
    model: 'Docs.model.File',
    proxy: {
        type: 'ajax',
        url: '/volume/findByVolume',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },
    doFindByVolume: function(volumeId){
        this.load({
            params: {
                volumeId: volumeId
            }
        });
    }
});