/*!
 * UI development toolkit for HTML5 (OpenUI5)
 * (c) Copyright 2009-2016 SAP SE or an SAP affiliate company.
 * Licensed under the Apache License, Version 2.0 - see LICENSE.txt.
 */

// Provides default renderer for JSONView
sap.ui.define(['jquery.sap.global', './ViewRenderer'],
	function(jQuery, ViewRenderer) {
	"use strict";


	/**
	 * @class JSONView renderer.
	 * @static
	 * @alias sap.ui.core.mvc.JSONViewRenderer
	 */
	var JSONViewRenderer = {
	};


	/**
	 * Renders the HTML for the given control, using the provided {@link sap.ui.core.RenderManager}.
	 *
	 * @param {sap.ui.core.RenderManager} oRenderManager the RenderManager that can be used for writing to the Render-Output-Buffer
	 * @param {sap.ui.core.Control} oControl an object representation of the control that should be rendered
	 */
	JSONViewRenderer.render = function(oRenderManager, oControl){
		// convenience variable
		var rm = oRenderManager;

		// write the HTML into the render manager
		rm.write("<div");
		rm.writeControlData(oControl);
		rm.addClass("sapUiView");
		rm.addClass("sapUiJSONView");
		ViewRenderer.addDisplayClass(rm, oControl);
		if (oControl.getWidth()) {
			rm.addStyle("width", oControl.getWidth());
		}
		if (oControl.getHeight()) {
			rm.addStyle("height", oControl.getHeight());
		}
		rm.writeStyles();
		rm.writeClasses();
		rm.write(">");

		var content = oControl.getContent();
		if (content) {
			if (content.length && !(content instanceof sap.ui.core.Control)) {
				// looks like an Array
				for (var i = 0; i < content.length; i++) {
					rm.renderControl(content[i]);
				}

			} else {
				// should be a Control
				rm.renderControl(oControl.getContent());
			}
		}

		rm.write("</div>");
	};


	return JSONViewRenderer;

}, /* bExport= */ true);
