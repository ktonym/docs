Ext.define('Docs.model.Circulation', {
    extend: 'Docs.model.Base',
    idProperty: 'circulationId',
    fields: [
        { name: 'circulationId', type: 'int', useNull: true },
        { name: 'borrowed', type: 'date', dateFormat:'Ymd' },
        { name: 'duration', type: 'int' },
        { name: 'returned', type: 'date', dateFormat:'Ymd'},
        { name: 'userId', type: 'int'},
        { name: 'user', type: 'string', persist:false, convert:function(value, record){
            var data = record.data;
            if(data&&data.fullName){
                return data.fullName;
            } else{
                return data.userId;
            }
        }},
        { name: 'fileId', type: 'int'},
        { name: 'file', type: 'string', persist:false, convert:function(value, record){
            var data = record.data;
            if(data&&data.code){
                return data.code;
            } else {
                return data.fileId;
            }
        }}
    ],
    hasOne: [
        {
            model: 'File',
            name: 'file',
            foreignKey: 'fileId',
            associationKey: 'fileId'
        },
        {
            model: 'User',
            name: 'user',
            foreignKey: 'userId',
            associationKey: 'userId'
        }
    ]
});
