
Ext.define('Docs.view.cabinet.CabinetModel',{

    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.cabinet',
    requires: ['Docs.model.Cabinet'],
    data: {},
    stores:{
        cabinets: {
            model: 'Cabinet',
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