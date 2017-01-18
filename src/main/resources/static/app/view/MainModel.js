/**
 * Created by user on 22-Oct-16.
 */
Ext.define('Docs.view.MainModel',{

    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.main',
    data: {
        current: {
            cabinet: null,
            cabinetRow: null,
            category: null,
            volume: null,
            client: null,
            user: null,
            roles: null,
            group: null
        }
    }
});
