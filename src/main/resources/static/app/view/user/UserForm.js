Ext.define('Docs.view.user.UserForm',{
     extend: 'Ext.form.Panel',
     alias: 'widget.user-form',
     reference: 'userForm',
     layout: {
         type: 'anchor'
     },
    padding: 10,
    fieldDefaults: {
        anchor: '100%',
        xtype: 'textfield'
    },
    items: [
        {
            xtype: 'hiddenfield',
            name: 'userId',
            bind: '{current.user.userId}'
        },
        {
            xtype: 'combo',
            fieldLabel: 'Group',
            name: 'group',
            bind: {
                store: '{groups}',
                value: '{current.user.groupId}'
            },
            queryMode: 'local',
            valueField: 'id',
            displayField: 'name'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Username',
            name: 'username',
            bind: '{current.user.username}'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'First Name',
            name: 'firstName',
            bind: '{current.user.firstName}'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Surname',
            name: 'surname',
            bind: '{current.user.surname}'
        },
        {
            xtype: 'textfield',
            fieldLabel: 'Password',
            inputType: 'password',
            name: 'password',
            bind: '{current.user.password}'
        },
        /*{
            xtype: 'checkboxfield'
        },*/
        {
            xtype: 'checkboxgroup',
            fieldLabel: 'Activation',
            // Arrange checkboxes into two columns, distributed vertically
            columns: 2,
            vertical: true,
            items: [
                {
                    boxLabel: 'Locked',
                    name: 'locked',
                    // inputValue: 'Y',
                    // uncheckedValue: 'N',
                    bind: '{current.user.locked}'
                },
                {
                    boxLabel: 'Expired',
                    name: 'expired',
                    // inputValue: 'Y',
                    // uncheckedValue: 'N',
                    bind: '{current.user.expired}'
                }
            ]

        }
    ],
    bbar: {
         overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-save',
                formBind: true,
                text: 'Save',
                handler: 'doSaveUser'
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-close',
                text: 'Exit',
                handler: 'closeUserWindow'
            }
        ]
    }
});
