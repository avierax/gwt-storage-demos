package com.tissca.gwttests;

import com.google.gwt.core.client.EntryPoint;
import elemental2.core.JsDate;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLInputElement;
import org.apache.tapestry.wml.Do;
import org.gwtproject.storage.client.Storage;

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DomGlobal.window.console.log("version 0.2");

		loadPreviouslyStoredValues();

		DomGlobal.document.querySelector("#session").addEventListener("keyup", new EventListener() {
			public void handleEvent(Event evt) {
				Storage.getSessionStorageIfSupported().setItem("value", ((HTMLInputElement) evt.target).value);
				Storage.getSessionStorageIfSupported().setItem("timestamp", String.valueOf(JsDate.now()));
			}
		});

		DomGlobal.document.querySelector("#local").addEventListener("keyup", new EventListener() {
			public void handleEvent(Event evt) {
				Storage.getLocalStorageIfSupported().setItem("value", ((HTMLInputElement) evt.target).value);
				Storage.getLocalStorageIfSupported().setItem("timestamp", String.valueOf(JsDate.now()));
			}
		});
	}

	private void loadPreviouslyStoredValues() {
		String[] storageNames = new String[] {"local", "session"};
		for(String storageName:storageNames) {
			Storage storage = storageName.equals("local") ? Storage.getLocalStorageIfSupported() : Storage.getSessionStorageIfSupported();
			String value = storage.getItem("value");
			String formattedString = storageName+"Storage is empty";
			if(value!=null) {
				double delta = (JsDate.now() - Double.parseDouble(storage.getItem("timestamp"))) / 1000;
				formattedString = storageName + "Storage: " + value + " (last updated: " + delta + "s ago)";
			}
			Element li = DomGlobal.document.createElement("li");
			li.innerHTML = formattedString;
			DomGlobal.document.querySelector("#previous").appendChild(li);

		}
	}
}
