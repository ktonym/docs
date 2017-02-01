
Ext.define('Docs.view.cabinet.CabinetModel',{

    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.cabinet',
    requires: ['Docs.model.Cabinet','Docs.model.CabinetRow','Docs.store.CabinetRow','Docs.model.Category',
        'Docs.store.RowClients','Docs.store.ClientVolumes','Docs.model.Client','Docs.model.Volume'],
    data: {},
    stores:{

        cabinetTree: {
            type: 'tree',
            autoLoad: true,
            root: {
                // name: 'root',
                expanded: true,
                text: 'Library'
            },
            fields: [
                { name: 'id', type: 'auto'}, { name: 'text'}
            ],
            proxy: {
                type: 'ajax',
                url: 'cabinet/tree.json',
                reader: {
                    type: 'json'
                }
            }
        },
        cabinets: {
            model: 'Cabinet',
            autoLoad: true
        },
        cabinetRows: {
           type: 'cabinet-row'
        },
        allCabinetRows: {
            model: 'CabinetRow',
            autoLoad: true,
            proxy: {
                type: 'ajax',
                url: '/cabinetrow/findZote'
            }
        },
        rowClients: {
            type: 'row-clients'
        },
        clients: {
            model: 'Client',
            autoLoad: true
        },
        volumes: {
            model: 'Volume',
            autoLoad: true
        },
        clientVolumes: {
            type: 'client-volumes'
        },
        categories: {
            model: 'Category',
            autoLoad: true
        }
    },
    formulas: {
        currentCabinet: {
            bind: '{cabinetGrid.selection}',
            get: function (cabinet) {
                this.set('current.cabinet', cabinet);
                return cabinet;
            }
        }
    }
});