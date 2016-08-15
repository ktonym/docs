Ext.define('Docs.model.User', {
    extend: 'Docs.model.Base',
    identifier: 'userId',
    fields: [
        { name: 'userId', type: 'int', useNull: true },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' },
        { name: 'fullName', type: 'string', persist: false}
    ],

    validations: [
        {firstName: 'presence'},
        {surname: 'presence'}
    ]

});
