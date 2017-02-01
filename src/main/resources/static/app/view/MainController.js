/**
 * Created by anthony.kipkoech on 12/29/2016.
 */
Ext.define('Docs.view.MainController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.main',

    defaultToken: 'home',

    routes: {
        'home' : 'onHome',
        'groups': 'onGroups',
        'groups/:id' : {
            action: 'onGroupSelect',
            before: 'onBeforeGroupSelect',
            conditions: {
                ':id' : '([0-9]+)'
            }
        }
    },

    onBeforeGroupSelect: function(id,action){
        var me = this,
            vw = me.getView(),
            main = vw.lookupReference('mainCards'),
            card = main.down('user-card'),
            grid = card.down('group-list'),
            record = grid.getStore().findRecord('id',id);
        if(record){
            action.resume();
        } else {
            action.stop();
        }
    },

    onGroupSelect: function(id){
        var me = this,
            vw = me.getView(),
            main = vw.lookupReference('mainCards'),
            card = main.down('user-card'),
            grid = card.down('group-list');
        grid.fireEvent('selectGroup',id);
    },

    onHome: function(){
        this.defineView(0);
        console.log('Executed onHome route....');
    },

    onGroups: function(){
        this.setViewToUsers();
    },

    defineView: function (conf) {
        var me = this,
            vw = me.getView(),
            //panel = vw.lookupReference('cabinetPanel'),
            cards = vw.lookupReference('mainCards');

        cards.getLayout().setActiveItem(conf);
    },
    setViewToCabinet: function () {
        this.defineView('cabinetCard');
    },
    setViewToUsers: function () {
        this.defineView('userCard');
    },
    setViewToCirculation: function () {
        this.defineView('circulationCard');
    }

});
