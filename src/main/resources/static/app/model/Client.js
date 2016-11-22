Ext.define('Docs.model.Client', {
    extend: 'Docs.model.Base',
    
    fields: [
        { name: 'id', type: 'number' },
        { name: 'name', type: 'string' },
        { name: 'tel', type: 'string' },
        { name: 'pin', type: 'string' },
        { name: 'cabinetId', type: 'int'},
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
