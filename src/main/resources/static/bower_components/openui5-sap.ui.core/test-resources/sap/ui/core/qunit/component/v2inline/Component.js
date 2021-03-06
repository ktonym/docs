jQuery.sap.declare("sap.ui.test.v2inline.Component");
jQuery.sap.require("sap.ui.core.UIComponent");

sap.ui.core.UIComponent.extend("sap.ui.test.v2inline.Component", {

	metadata: {

		"manifest": {

			"name": "sap.ui.test.v2inline.Component",

			"sap.app": {
				"id": "sap.ui.test.v2inline",
				"applicationVersion": {
					"version": "1.0.0"
				}
			},

			"sap.ui5": {

				"resources": {
					"js": [
						{
							"uri": "script.js"
						},
						{}
					],
					"css": [
						{
							"uri": "style.css",
							"id": "mystyle"
						},
						{}
					]
				},

				"dependencies": {
					"minUI5Version": "1.22.5",
					"libs": {
						"sap.ui.commons": {
							"minVersion": "1.22.0"
						}
					},
					"components": {
						"sap.ui.test.other": {
							"optional": true,
							"minVersion": "1.0.1"
						}
					}
				},

				"models": {
					"i18n": {
						"type": "sap.ui.model.resource.ResourceModel",
						"settings": {
							"uri": "i18n/i18n.properties"
						}
					},
					"sfapi": {
						"type": "sap.ui.model.odata.ODataModel",
						"settings": {
							"uri": "/sap/opu/odata/snce/PO_S_SRV/"
						}
					}
				},

				"rootView": "sap.ui.test.view.Main",

				"config": {
					"any1": {
						"entry": "configuration"
					},
					"any2": {
						"anyobject": {
							"key1": "value1"
						}
					},
					"any3": {
						"anyarray": [1, 2, 3]
					}
				},

				"routing": {
					"config": {
						"viewType" : "XML",
						"viewPath": "NavigationWithoutMasterDetailPattern.view",
						"targetParent": "myViewId",
						"targetControl": "app",
						"targetAggregation": "pages",
						"clearTarget": false
					},
					"routes": [
						{
							"name" : "myRouteName1",
							"pattern" : "FirstView/{from}",
							"view" : "myViewId"
						}
					]
				},

				"extends": {
					"extensions": {
						"sap.ui.viewReplacements": {
							"sap.ui.test.view.Main": {
								"viewName": "sap.ui.test.view.Main",
								"type": "XML"
							}
						},
						"sap.ui.controllerReplacements": {
							"sap.ui.test.view.Main": "sap.ui.test.view.Main"
						},
						"sap.ui.viewExtensions": {
							"sap.ui.test.view.Main": {
								"extension": {
									"name": "sap.xx.new.Fragment",
									"type": "sap.ui.core.XMLFragment"
								}
							}
						},
						"sap.ui.viewModification": {
							"sap.ui.test.view.Main": {
								"myControlId": {
									"text": "{i18n_custom>mytext}"
								}
							}
						}
					}
				}

			}

		},

		"custom.entry": {
			"key1": "value1",
			"key2": "value2",
			"key3": {
				"subkey1": "subvalue1",
				"subkey2": "subvalue2"
			},
			"key4": ["value1", "value2"]
		}

	}

});
