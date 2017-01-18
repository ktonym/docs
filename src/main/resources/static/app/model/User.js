Ext.define('Docs.model.User', {
    extend: 'Docs.model.Base',
    idProperty: 'userId',
    fields: [
        { name: 'userId', type: 'int', useNull: true },
        { name: 'username', type: 'string' },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' },
        { name: 'password', type: 'string' },
        { name: 'fullName', type: 'string', persist: false },
        { name: 'locked', type: 'boolean' },
        { name: 'expired', type: 'boolean' },
        { name: 'groupId', type: 'int', reference: 'Group' }
    ],

    validators: {
        username: 'presence',
        firstName: 'presence',
        surname: 'presence'
    }

});
