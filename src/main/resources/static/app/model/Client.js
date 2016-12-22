Ext.define('Docs.model.Client', {
    extend: 'Docs.model.Base',
    idProperty: 'clientId',
    fields: [
        { name: 'clientId', type: 'number' },
        { name: 'clientName', type: 'string' },
        { name: 'tel', type: 'string' },
        { name: 'pin', type: 'string' },
        { name: 'cabinetId', type: 'int'},
        { name: 'rowNumber', type: 'int'},
        { name: 'cabinet', type: 'string', persist: false, 
            convert: function(v,rec){
                var data = rec.data;
                if(data&&data.cabinetNo){
                    return data.cabinetNo;
                }
            }
        }
    ]
});
