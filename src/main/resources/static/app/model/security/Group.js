Ext.define('Docs.model.security.Group', {
    extend: 'Docs.model.security.Base',
    
    fields: [
        { name: 'id', type: 'int', useNull: true },
        { name: 'name', type: 'string' },
        { name: 'description', type: 'string' }

    ]
});
