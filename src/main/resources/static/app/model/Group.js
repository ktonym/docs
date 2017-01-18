Ext.define('Docs.model.Group', {
    extend: 'Docs.model.Base',
    
    fields: [
        { name: 'id', type: 'int', useNull: true },
        { name: 'name', type: 'string' },
        { name: 'description', type: 'string' }

    ]
});
