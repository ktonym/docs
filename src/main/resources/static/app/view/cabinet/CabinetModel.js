
Ext.define('Docs.view.cabinet.CabinetModel',{

    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.cabinet',
    requires: ['Docs.model.Cabinet','Docs.model.CabinetRow'],
    data: {},
    stores:{

        cabinetTree: {
            type: 'tree',
            autoLoad: true,
            root: {
                // name: 'root',
                expanded: true
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
            model: 'CabinetRow',
            autoLoad: false,
            proxy: {
                type: 'ajax',
                url: '/cabinetrow/findAll',
                extraParams: {
                    cabinetId: '{current.cabinet.cabinetId}'
                }
            }
        },
        allCabinetRows: {
            model: 'CabinetRow',
            autoLoad: true,
            proxy: {
                type: 'ajax',
                url: '/cabinetrow/findZote'
            }
        },
        clients: {
            model: 'Client',
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