/**
 * Created by anthony.kipkoech on 1/18/2017.
 */
Ext.define('Docs.util.ControllerMixin',{
    extend: 'Ext.Mixin',
    mixinConfig: {
        id: 'controllerMixin'
    },


    saveRecord: function(record){
        record.save({
            callback:  function(record, operation, success) {
                var result = Docs.util.Util.decodeJSON(operation._response.responseText);
                if (success) {
                    Docs.util.Util.showToast('Success! Record saved.');
                    //me.onCancel();
                    //me.refresh();
                } else {
                    Docs.util.Util.showErrorMsg(result.msg);
                }
            }
        });
    },

    checkDirtyBeforeClose: function (rec,win) {
        if(rec.dirty){
            Ext.Msg.confirm('Revert changes?','The window has unsaved changes, do you want to close and lose them?',
                function (btn) {
                    if(btn==='yes'){
                        rec.reject();
                        if(win){
                            win.close();
                        }
                    }
                }
            );
        } else {
            if(win){
                win.close();
            }
        }
    }

});