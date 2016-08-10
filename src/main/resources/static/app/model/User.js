Ext.define('Docs.model.User', {
    extend: 'Docs.model.Base',
    
    fields: [
        { name: 'id', type: 'number' },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' }

    ]
});
