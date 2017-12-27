package com.tissca.gwttests;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
//import com.google.gwt.storage.client.*;
import elemental2.core.Global;
import elemental2.dom.DomGlobal;
import elemental2.webstorage.WebStorageWindow;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.gwtproject.storage.client.*;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {


		Storage storage = Storage.getLocalStorageIfSupported();
		storage.setItem("tsiseFoo", "tsiseBarOld");

		StorageEvent.Handler handler = new StorageEvent.Handler() {
			public void onStorageChange(StorageEvent event) {
				Window.alert("event got called");
			}
		};
		storage.addStorageEventHandler(handler);
		storage.setItem("tsiseFoo", "tsiseBarNew");
	}
}
