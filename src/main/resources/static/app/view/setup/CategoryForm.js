/**
 * Created by anthony.kipkoech on 1/4/2017.
 */
Ext.define('Docs.view.setup.CategoryForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.category-form',
    defaults:{
        xtype: 'textfield',
        anchor: '100%',
        allowBlank: false
    },
    items: [
        {
            xtype: 'hiddenfield',
            name: 'id',
            bind: '{current.category.categoryId}'
        },
        {
            fieldLabel: 'Category Name',
            name: 'name',
            bind: '{current.category.name}'
        },
        {
            xtype: 'textarea',
            fieldLabel: 'Description',
            name: 'description',
            bind: '{current.category.description}'
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
                handler: 'doSaveCat'
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-close',
                text: 'Exit',
                handler: 'closeCatWindow'
            }
        ]
    }
});