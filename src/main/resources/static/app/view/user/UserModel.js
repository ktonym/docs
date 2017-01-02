/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
Ext.define('Docs.view.user.UserModel',{
   extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.user',
    requires: ['Docs.model.security.Group'],
    data: {},
    stores: {
        groups: {
            model: 'Group',
            autoLoad: true
        },
        navItems: {
            type: 'tree',
            root: {
                expanded: true,
                children: [
                    {
                        text: 'Home',
                        iconCls: 'x-fa fa-home',
                        children: [{
                            text: 'Messages',
                            iconCls: 'x-fa fa-inbox',
                            leaf: true
                        }]
                    },
                    {
                        text: 'Users',
                        iconCls: 'x-fa fa-user',
                        children: [{
                            text: 'Active',
                            iconCls: 'x-fa fa-tag',
                            routeId: 'userList',
                            leaf: true
                        },{
                            text: 'Inactive',
                            iconCls: 'x-fa fa-trash',
                            leaf: true
                        }]
                    },
                    {
                        text: 'Groups',
                        iconCls: 'x-fa fa-group',
                        routeId: 'groupList',
                        leaf: true
                    },
                    {
                        text: 'Settings',
                        iconCls: 'x-fa fa-wrench',
                        children: [{
                            text: 'Circulation',
                            iconCls: 'x-fa fa-share-alt',
                            leaf: true
                        },{
                            text: 'Notifications',
                            iconCls: 'x-fa fa-flag',
                            leaf: true
                        }]
                    }
                ]
            }
        }
    }
});