Ext.define('Docs.view.cabinet.VolumeForm',{
    extend: 'Ext.form.Panel',
    alias: 'widget.volume-form',
    layout: 'anchor',
    bodyPadding: 10,
    border: false,
    autoScroll: true,
    fieldDefaults: {
        anchor: '100%'
    },
    items: [
        {
            xtype: 'hiddenfield',
            name: 'clientId',
            bind: '{current.volume.clientId}'
        },
        {
            xtype: 'hiddenfield',
            name: 'volumeId',
            bind: '{current.volume.volumeId}'
        },
        {
            fieldLabel: 'Volume No',
            xtype: 'numberfield',
            name: 'volumeNo',
            bind: '{current.volume.volumeNo}'
        },
        {
            fieldLabel: 'Year',
            xtype: 'datefield',
            name: 'year',
            dateFormat: 'Y',
            bind: '{current.volume.year}'
        }
        /*{
            xtype: 'combo',
            fieldLabel: 'Category Type',
            name: 'categoryRef',
            bind: {
                store: '{categoryRefs}',
                value: '{current.category.categoryRefId}'
            },
            queryMode: 'local',
            valueField: 'categoryRefId',
            displayField: 'name'
        },*/
    ],
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-delete',
                // itemId: 'deleteBtn',
                // disabled: true,
                text: 'Delete',
                formBind: true,
                handler: 'onDelVolume'
            },{
                xtype: 'button',
                iconCls: 'x-fa fa-add',
                itemId: 'addFileBtn',
                // disabled: true,
                text: 'Add File',
                formBind: true,
                handler: 'onAddFile'
            },{
                xtype: 'button',
                iconCls: 'x-fa fa-save',
                itemId: 'saveBtn',
                text: 'Save',
                formBind: true,
                handler: 'onSaveVolume'
            }
        ]
    }
});