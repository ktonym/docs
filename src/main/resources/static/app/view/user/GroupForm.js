/**
 * Created by anthony.kipkoech on 12/29/2016.
 */
Ext.define('Docs.view.user.GroupForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.group-form',
    reference: 'groupForm',
    defaults:{
        xtype: 'textfield',
        anchor: '100%',
        allowBlank: false
    },
    items: [
        {
            xtype: 'hiddenfield',
            name: 'id',
            bind: '{current.group.id}'
        },
        {
            fieldLabel: 'Group Name',
            name: 'name',
            bind: '{current.group.name}'
        },
        {
            xtype: 'textarea',
            fieldLabel: 'Description',
            name: 'description',
            bind: '{current.group.description}'
        }
    ],
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                text: 'Save',
                formBind: true,
                /*bind: {
                    disabled: '{!current.group.dirty}'
                },*/
                handler: 'doSaveGroup'
            },
            {
                xtype: 'button',
                text: 'Exit',
                handler: 'closeWindow'
            }
        ]
    }
});
