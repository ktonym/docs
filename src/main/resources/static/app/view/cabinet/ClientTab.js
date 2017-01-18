/**
 * Created by anthony.kipkoech on 1/10/2017.
 */
Ext.define('Docs.view.cabinet.ClientTab',{
    extend: 'Ext.tab.Panel',
    alias: 'widget.client-tab',
    reference: 'clientTab',
    requires: ['Docs.view.cabinet.VolumeForm','Docs.view.cabinet.VolumeList'/*,'Docs.view.cabinet.FileList'*/],
    headerPosition: 'Bottom',
    items:[
        {
            title: 'Volumes',
            xtype: 'volume-list'
        },
        {
            title: 'Reports',
            bind: {
                disabled: '{!volumeList.selection}'
            }

            //xtype: 'file-list'
        }
    ]
});