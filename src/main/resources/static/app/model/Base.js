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
                create: '{entityName:lowercase}/create.json',
                read: '{entityName:lowercase}/findAll.json',
                update: '{entityName:lowercase}/update.json',
                destroy: '{entityName:lowercase}/delete.json'
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
