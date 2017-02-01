/**
 * Created by anthony.kipkoech on 1/20/2017.
 */
Ext.define('Docs.store.ClientVolumes',{
    extend: 'Ext.data.Store',
    alias: 'store.client-volumes',
    requires: ['Docs.model.Volume'],
    model: 'Docs.model.Volume',
    proxy: {
        type: 'ajax',
        url: '/volume/findByClient',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    },
    doFindByClient: function(clientId){
        this.load({
           params: {
               clientId: clientId
           }
        });
    }
});