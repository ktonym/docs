/**
 * Created by anthony.kipkoech on 1/9/2017.
 */
Ext.define('Docs.model.Volume',{
   extend: 'Docs.model.Base',
    idProperty: 'volumeId',
    fields:[
        { name: 'volumeId', type: 'int', useNull: true},
        { name: 'volumeNo', type: 'int'},
        { name: 'year', type: 'date', dateFormat: 'Y'},
        { name: 'clientId', type: 'int', reference: 'Client'}
    ],

    validators: {
       clientId: [
           { type: 'presence', message: 'This field is mandatory'}
       ],
        volumeNo: [
            { type: 'presence', message: 'This field is mandatory'}
        ]
    }
});