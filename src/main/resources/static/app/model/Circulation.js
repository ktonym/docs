Ext.define('Docs.model.Circulation', {
    extend: 'Docs.model.Base',
    
    fields: [
        { name: 'id', type: 'number' },
        { name: 'borrowed', type: 'date' },
        { name: 'duration', type: 'number' }

    ]
});
