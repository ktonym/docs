Ext.define('Docs.model.CabinetRow', {
    extend: 'Docs.model.Base',
    idProperty: 'rowId',
    fields: [
        {name: 'rowId', type: 'int', useNull: true},
        {name: 'rowNumber', type: 'int'},
        {name: 'cabinetId', type: 'int'},
        {
            name: 'cabinet', type: 'string', persist: false,
            convert: function (v, rec) {
                var data = rec.data;
                if (data.cabinet && data.cabinet.description) {
                    return data.cabinet.description;
                }
                return data.cabinetId;
            }
        }
    ],

    hasOne: [
        {
            model: 'Cabinet',
            name: 'cabinet',
            foreignKey: 'cabinetId',
            associationKey: 'cabinetId'
        }
    ],

    validators: {
        rowNumber: 'presence'
    }


});
