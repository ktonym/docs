Ext.define('Docs.store.CabinetTree',{
    extend: 'Ext.data.TreeStore',
    storeId: 'cabinetTreeStore',
    // proxy: {
    //     type: 'ajax',
    //     url: 'cabinet/treenode.json'
    // },

    //TODO construct a tree structure to represent a cabinet tree

    data: { cabinets: [
        { cabinetId : 1, cabinetType : "OPEN", text: '1',
            cabinetRows: [
                { rowNumber: 1, text: 'Row 1',
                    clients:[
                    {clientId: '1',text:'Jabu Investments Ltd',tel: '0898879', pin: 'P123456789L'},
                    {clientId: '2',text:'Jaql Limited',tel: '787686', pin: 'P987654321L'}
                    ]
                },
                { rowNumber: 2, text: 'Row 2',
                    clients:[
                        {clientId: '3',text:'Wananchi Online',tel: '43329', pin: 'P875209822K'},
                        {clientId: '4',text:'Facebook Inc.',tel: '432345', pin: 'P432898007H'}
                    ]
                },
                { rowNumber: 3},
                { rowNumber: 4}
            ] 
        },
        { cabinetId : 2, text: '2', cabinetType : "CLOSED",
            cabinetRows: [{rowNumber: 1,text: 'Row 1'},{rowNumber: 2, text: 'Row 2'},{rowNumber: 3, text: 'Row 3'},{rowNumber: 4, text: 'Row 4'}] },
        { cabinetId : 3, text: '3', cabinetType : "OPEN",
            cabinetRows: [{rowNumber: 1,text: 'Row 1'},{rowNumber: 2, text: 'Row 2'},{rowNumber: 3, text: 'Row 3'},{rowNumber: 4, text: 'Row 4'}] },
        { cabinetId : 4, text: '4', cabinetType : "OPEN",
            cabinetRows: [{rowNumber: 1,text: 'Row 1'},{rowNumber: 2, text: 'Row 2'},{rowNumber: 3, text: 'Row 3'},{rowNumber: 4, text: 'Row 4'}] }
    ]},

    proxy: {
        type: 'memory',
        reader: {
            type: 'json',
            rootProperty: 'cabinets'
        }
    }
});