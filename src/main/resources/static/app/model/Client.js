Ext.define('Docs.model.Client', {
    extend: 'Docs.model.Base',
    
    fields: [
        { name: 'id', type: 'number' },
        { name: 'name', type: 'string' },
        { name: 'tel', type: 'string' },
        { name: 'pin', type: 'string' }

    ]
});
