Ext.define('Docs.view.user.UserCard',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.user-card',
    requires: ['Docs.view.user.UserList','Docs.view.user.UserForm'],
    layout: 'hbox',
    items: [
        {
            xtype:'user-list',
            flex: 2
        },
        {
            xtype: 'user-form',
            flex: 1
        }
    ]
});