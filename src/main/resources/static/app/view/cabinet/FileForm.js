/**
 * Created by anthony.kipkoech on 1/20/2017.
 */
Ext.define('Docs.view.cabinet.FileForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.file-form',
    reference: 'fileForm',
    layout: {
        type: 'anchor'
    },
    defaults: {
        xtype: 'textfield'
    },
    items: [
        {
            xtype: 'hiddenfield',
            name: 'fileId',
            bind: '{current.file.fileId}'
        },
        {
            xtype: 'hiddenfield',
            name: 'volumeId',
            bind: '{current.file.volumeId}'
        },
        {
            name: 'code',
            bind: '{current.file.code}',
            fieldLabel: 'Code'
        },
        {
            name: 'location',
            bind: '{current.file.location}',
            fieldLabel: 'Location'
        },
        {
            xtype: 'datefield',
            name: 'date',
            bind: '{current.file.date}',
            dateFormat: 'Ymd',
            fieldLabel: 'Date'
        },
        {
            xtype: 'combo',
            fieldLabel: 'Category',
            name: 'category',
            store: ['CLOSED','OPEN'],
            bind: {
                store: '{categories}',
                value: '{current.file.categoryId}'
            },
            queryMode: 'local',
            valueField: 'categoryId',
            displayField: 'name'
        },
        //{
        //
        //},
        {
            xtype: 'textarea',
            name: 'fileDesc',
            bind: '{current.file.fileDesc}',
            fieldLabel: 'Description'
        },
        {
            xtype: 'numberfield',
            name: 'cabinetId',
            fieldLabel: 'Cabinet Number',
            bind: '{current.cabinet.cabinetId}',
            editable: false
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
                handler: 'doSaveFile'
            },
            {
                xtype: 'button',
                iconCls: 'x-fa fa-close',
                text: 'Exit',
                handler: 'closeFileWindow'
            }
        ]
    }
});