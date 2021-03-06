Ext.define('Docs.model.File', {
    extend: 'Docs.model.Base',
    idProperty: 'fileId',
    fields: [
        { name: 'fileId', type: 'int', useNull:true },
        { name: 'code', type: 'string' },
        { name: 'status', type: 'string' },
        { name: 'location', type: 'string' },
        { name: 'fileDesc', type: 'string' },
        { name: 'date', type: 'date', dateFormat: 'Ymd' },
        { name: 'volumeId', type: 'int', reference: 'Volume' },
        { name: 'categoryId', type: 'int', reference: 'Category' },
        { name: 'volumeNo', type: 'string', persist: false
            //,convert: function(v,record){
            //    return record.data.volume.volumeNo;
            //}
        },
        { name: 'name', type: 'string', persist: false
            //,convert: function(v,record){
            //    return record.data.category.name;
            //}
        }
    ],
    validators: {
        date:[{ type: 'presence', message: 'This field is mandatory' }],
        status: [{ type: 'presence', message: 'This field is mandatory' }],
        fileDesc: [{ type: 'presence', message: 'This field is mandatory' }],
        volumeId: [{ type: 'presence', message: 'This field is mandatory' }]
    }
});
