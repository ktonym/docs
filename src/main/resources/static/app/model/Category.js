Ext.define('Docs.model.Category', {
    extend: 'Docs.model.Base',
    idProperty: 'categoryId',
    fields: [
        { name: 'categoryId', type: 'int', useNull: true },
        { name: 'name', type: 'string' },
        { name: 'description', type: 'string' }

    ],
    validators: {
        description: [
            {type:'presence', message: 'This field is mandatory'}
        ],
        name: [
            { type:'presence', message: 'This field is mandatory'}
        ]
    }
});
