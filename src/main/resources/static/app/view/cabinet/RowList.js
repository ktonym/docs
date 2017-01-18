/**
 * Created by anthony.kipkoech on 1/10/2017.
 */
Ext.define('Docs.view.cabinet.RowList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.row-list',
    reference: 'rowList',
    bind: {
        store: '{cabinetRows}',
        selection: '{current.row}'
    },
    listeners: {
        itemdblclick: 'onRowDblClick'
    },
    bbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-plus',
                handler: 'onAddRow',
                text: 'Add Row'
            }
        ]
    },
    columns: [
        {
            dataIndex: 'rowId',
            text: '#',
            width: 50
        },
        {
            xtype: 'templatecolumn',
            dataIndex:'rowNumber',
            text: 'Row Number',
            width: 200,
            tpl: 'Row Number {rowNumber}'
        },
        {
            dataIndex: 'cabinetType',
            text: 'Cabinet Type',
            flex: 1
        },
        {
            xtype: 'actioncolumn',
            text: 'Actions',
            flex: 1,
            items: [
                {
                    xtype: 'button',
                    iconCls: 'x-fa fa-chevron-right',
                    handler: 'onRowDetailClick',
                    tooltip: 'View details'
                },'-',
                {
                    xtype: 'button',
                    iconCls: 'x-fa fa-close',
                    handler: 'onRowDelete',
                    tooltip: 'Remove row'
                }
            ]
        }
    ]
});