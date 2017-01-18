/**
 * Created by anthony.kipkoech on 1/4/2017.
 */
Ext.define('Docs.view.setup.CategoryList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.category-list',
    reference: 'categoryList',
    title: 'Category References',
    iconCls: 'x-fa fa-object-group',
    listeners: {
        itemdblclick: 'onCatDblClick'
    },
    bind: {
        store: '{categories}',
        selection: '{current.category}'
    },
    columns: [
        {
            dataIndex: 'categoryId',
            text: 'Id',
            flex: 1
        },
        {
            dataIndex: 'name',
            text: 'Name',
            flex: 3
        },
        {
            dataIndex: 'description',
            text: 'Description',
            flex: 3
        }
    ],
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-plus',
                itemId: 'addBtn',
                text: 'Add',
                handler: 'doAddCat'
            }
        ]
    }
});