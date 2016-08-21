Ext.define('Docs.view.MainCards',{
    extend: 'Ext.container.Container',
    alias: 'widget.main-cards',
    requires: ['Docs.view.cabinet.CabinetCard','Docs.view.circulation.CirculationCard',
        'Docs.view.client.ClientCard','Docs.view.user.UserCard'],
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
