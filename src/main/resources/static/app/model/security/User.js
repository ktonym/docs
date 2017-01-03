Ext.define('Docs.model.security.User', {
    extend: 'Docs.model.security.Base',
    idProperty: 'userId',
    fields: [
        { name: 'userId', type: 'int', useNull: true },
        { name: 'username', type: 'string' },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' },
        { name: 'password', type: 'string' },
        { name: 'fullName', type: 'string', persist: false },
        { name: 'locked', type: 'boolean'
            // ,
            // convert: function (v, record) {
            //     var data = record.getData();
            //     return ( data.locked == "Y" ? true : false )
            // }
        },
        { name: 'expired', type: 'boolean'
            // ,convert: function (v, record) {
            //     var data = record.getData();
            //     return ( data.expired == "Y" ? true : false )
            // }
        },
        { name: 'groupId', type: 'int', reference: 'Group' }
    ],

    validators: {
        username: 'presence',
        firstName: 'presence',
        surname: 'presence'
    }

});
