/**
 * Created by anthony.kipkoech on 1/9/2017.
 */
Ext.define('Docs.view.cabinet.VolumeList',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.volume-list',
    reference: 'volumeList',
    listeners: {
        itemdblclick: 'onVolDblClick'
    },
    bind: {
        store: '{clientVolumes}',
        selection: '{current.volume}'
    },
    columns: [
        {
            dataIndex: 'volumeId',
            text: '#',
            width: 50
        },
        {
            dataIndex: 'volumeNo',
            text: 'Volume No',
            flex: 2
        },
        {
            dataIndex: 'year',
            text: 'Year',
            flex: 3
        }
    ],
    tbar: {
        overflowHandler: 'menu',
        items: [
            '->',
            {
                xtype: 'button',
                iconCls: 'x-fa fa-plus',
                itemId: 'addVolumeBtn',
                // disabled: true,
                text: 'Add',
                handler: 'onAddVolume'
            }
        ]
    }
});