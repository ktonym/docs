
Ext.define('Docs.view.cabinet.ClientModel',{
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.client',
    requires: ['Docs.model.Client'],
    stores: {
        clients: {
            model: 'Client',
            proxy: {
                type: 'ajax'
            }
            
        }    
    },
    formulas: {
        
    }
        
});