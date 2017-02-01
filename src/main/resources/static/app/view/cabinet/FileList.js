/**
 * Created by anthony.kipkoech on 1/20/2017.
 */
Ext.define('Docs.view.cabinet.FileList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.file-list',
    reference: 'fileList',
    listeners: {
        itemdbclick: 'onFileDblClick'
    },
    bind: {
        store: '{volumeFiles}',
        selection: '{current.file}'
    },
    columns: [

        {
            dataIndex: 'code',
            text: 'Code',
            flex: 1
        },
        {
            dataIndex: 'status',
            text: 'status',
            flex: 1
        },
        {
            dataIndex: 'location',
            text: 'Location',
            flex: 1
        },
        {
            dataIndex: 'volumeNo',
            text: 'Volume',
            flex: 1
        },
        //{ dataIndex: 'fileDesc', text: 'fileDesc',   },
        //{ dataIndex: 'volumeId', text: 'volumeId',   },
        {
            dataIndex: 'name',
            text: 'Category',
            flex: 1
        },
        {
            xtype: 'datecolumn',
            format: 'Ymd',
            dataIndex: 'date',
            text: 'Date',
            flex: 1
        }
    ],
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-plus',
                itemId: 'addFileBtn',
                // disabled: true,
                text: 'Add',
                handler: 'onAddFile'
            }
        ]
    }
});