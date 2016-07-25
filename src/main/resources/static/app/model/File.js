Ext.define('Docs.model.File', {
    extend: 'Docs.model.Base',
    
    fields: [
        { name: 'id', type: 'number' },
        { name: 'code', type: 'string' },
        { name: 'status', type: 'string' }

    ]
});
