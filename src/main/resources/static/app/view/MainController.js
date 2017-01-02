/**
 * Created by anthony.kipkoech on 12/29/2016.
 */
Ext.define('Docs.view.MainController',{
    extend: 'Ext.app.ViewController',
    alias: 'controller.main',

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
