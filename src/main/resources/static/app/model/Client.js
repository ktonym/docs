Ext.define('Docs.model.Client', {
    extend: 'Docs.model.Base',
    idProperty: 'clientId',
    fields: [
        {name: 'clientId', type: 'int', useNull: true},
        {name: 'clientName', type: 'string'},
        {name: 'tel', type: 'string'},
        {name: 'pin', type: 'string'},
        {name: 'email', type: 'string'}
        /*,{
            name: 'cabinet', type: 'string', persist: false,
            convert: function (v, rec) {
                var data = rec.data;
                if (data && data.cabinetNo) {
                    return data.cabinetNo;
                }
            }
        }*/
    ],
    validators: {
        email: [
            { type: 'email'}
        ],
        clientName: [
            { type: 'presence', message: 'This field is mandatory'}
        ]
    }

});
