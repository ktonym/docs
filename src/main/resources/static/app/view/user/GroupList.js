/**
 * Created by anthony.kipkoech on 12/29/2016.
 */
Ext.define('Docs.view.user.GroupList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.group-list',
    reference: 'groupList',
    title: 'Groups Listing',
    iconCls: 'x-fa fa-group',
    bind: {
        store: '{groups}',
        selection: '{current.group}'
    },
    listeners: {
        itemdblclick: 'onGrpDblClick',
        itemclick: 'onGroupItemClick',
        selectGroup: 'onGroupSelect'
    },
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-plus',
                itemId: 'addGroupBtn',
                text: 'Add',
                handler: 'doAddGroup'
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-remove',
                itemId: 'deleteBtn',
                bind: {
                    disabled: '{!groupList.selection}'
                },
                text: 'Delete',
                handler: 'doDelGroup'
            }
        ]
    },

    columns: [
        {
            dataIndex: 'id',
            text: '#',
            flex: 1
        },
        {
            dataIndex: 'name',
            text: 'Group Name',
            flex: 3
        },
        {
            dataIndex: 'description',
            text: 'Description',
            flex: 6
        }
    ]
});
