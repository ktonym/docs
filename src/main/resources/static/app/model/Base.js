Ext.define('Docs.model.Base', {
    extend: 'Ext.data.Model',
    
    //fields: [
    //    { name: 'lastUpdate', type: 'date' }
    //
    //],
    
    schema: {
        id: 'docsSchema',
        namespace: 'Docs.model',
        urlPrefix: 'docs',
        proxy: {
            type: 'ajax',
            api : {
                create: '{prefix}/{entityName:lowercase}/store.json',
                read: '{prefix}/{entityName:lowercase}/findAll.json',
                update: '{prefix}/{entityName:lowercase}/store.json',
                destroy: '{prefix}/{entityName:lowercase}/delete.json'
            },
            reader: {
                type: 'json',
                rootProperty: 'data',
                totalProperty: 'results'
            },
            writer: {
                type: 'json',
                writeAllFields: true,
                rootProperty: 'data',
                allowSingle: true,
                encode: true
            }
        }
    }
    
});
