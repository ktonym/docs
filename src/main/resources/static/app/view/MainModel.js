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
            client: null,
            category: null,
            user: null,
            roles: null,
            group: null
        }
    }
});
