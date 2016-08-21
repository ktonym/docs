Ext.define('Docs.view.Main',{
   extend: 'Ext.container.Container',
    requires: ['Docs.view.MainHeader','Docs.view.MainCards','Ext.layout.container.VBox'],
    plugins: 'viewport',
    padding: 5,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'main-header',
            height: 80
        },
        {
            xtype: 'main-cards',
            flex: 1
        }
    ]
});
