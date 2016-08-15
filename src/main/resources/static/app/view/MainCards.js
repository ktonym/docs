Ext.define('Docs.view.MainCards',{
    extend: 'Ext.container.Container',
    alias: 'widget.main-cards',
    requires: [''],
    layout: 'card',
    items: [
        {
            xtype: 'cabinet-card',
            itemId: 'cabinetCard'
        },
        {
            xtype: 'circulation-card',
            itemId: 'circulationCard'
        },
        {
            xtype: 'client-card',
            itemId: 'clientCard'
        },
        {
            xtype: 'user-card',
            itemId: 'userCard'
        }
    ]
});
