Ext.define('Docs.view.Main',{
   extend: 'Ext.container.Viewport',
    alias: 'widget.app-main',
    requires: ['Docs.view.MainHeader','Docs.view.MainCards','Docs.view.MainModel','Ext.layout.container.VBox'],
    viewModel: 'main',
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
