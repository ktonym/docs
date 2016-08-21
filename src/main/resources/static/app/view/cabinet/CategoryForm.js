Ext.define('Docs.view.cabinet.CategoryForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.category-form',
    layout: 'anchor',
    bodyPadding: 10,
    border: false,
    autoScroll: true,
    items: [
        {
            xtype: 'fieldset',
            padding: 10,
            fieldDefaults: {
                anchor: '100%'
            },
            title: 'Category Entry',
            items: [
                {
                    xtype: 'hiddenfield',
                    name: 'categoryId'
                },
                {
                    fieldLabel: 'textarea',
                    name: 'description'
                },
                {
                    xtype: 'toolbar',
                    ui: 'footer',
                    layout: {
                        pack: 'end',
                        type: 'hbox'
                    },
                    items: [{
                        xtype: 'button',
                        iconCls: 'delete',
                        itemId: 'deleteBtn',
                        disabled: true,
                        text: 'Delete'
                    },{
                        xtype: 'button',
                        iconCls: 'add',
                        itemId: 'addFileBtn',
                        disabled: true,
                        text: 'Add File'
                    },{
                        xtype: 'button',
                        iconCls: 'save',
                        itemId: 'saveBtn',
                        text: 'Save'
                    }]
                }
            ]
        }
    ]
});