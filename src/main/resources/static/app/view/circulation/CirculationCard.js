Ext.define('Docs.view.circulation.CirculationCard',{
   extend: 'Ext.panel.Panel',
    alias: 'widget.circulation-card',
    requires: ['Ext.layout.container.HBox','Docs.view.circulation.CirculationList',
        'Docs.view.circulation.CirculationForm'],
    layout: 'hbox',
    items: [
        {
            xtype: 'circulation-list',
            flex: 3
        },
        {
            xtype: 'circulation-form',
            flex: 1
        }
    ]
});