/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
Ext.define('Docs.model.security.Base', {
    extend: 'Ext.data.Model',

    schema: {
        id: 'secSchema',
        namespace: 'Docs.model.security',
        urlPrefix: 'docs',
        proxy: {
            type: 'ajax',
            api : {
                create: 'security/{entityName:lowercase}/create.json',
                read: 'security/{entityName:lowercase}/findAll.json',
                update: 'security/{entityName:lowercase}/update.json',
                destroy: 'security/{entityName:lowercase}/delete.json'
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
