Ext.define('Docs.view.client.ClientCard',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.client-card',
    requires: ['Docs.view.client.ClientList','Docs.view.cabinet.ClientForm'],
    layout: 'hbox',
    items: [
        {
            xtype:'client-list',
            flex: 2
        },
        {
            xtype: 'client-form',
            flex: 1
        }
    ]
});