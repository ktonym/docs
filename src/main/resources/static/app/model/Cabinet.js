Ext.define('Docs.model.Cabinet', {
    extend: 'Docs.model.Base',
    
    fields: [
        { name: 'id', type: 'number' },
        { name: 'cabinetType', type: 'string' },
        { name: 'shelfNumber', type: 'number' }

    ]
});
