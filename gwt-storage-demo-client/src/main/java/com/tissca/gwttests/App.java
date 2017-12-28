package com.tissca.gwttests;

import com.google.gwt.core.client.EntryPoint;
import elemental2.core.JsDate;
import elemental2.dom.*;
import org.gwtproject.storage.client.Storage;
import static elemental2.dom.DomGlobal.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

	private final Storage localStorage = Storage.getLocalStorageIfSupported();
	private final Storage sessionStorage = Storage.getSessionStorageIfSupported();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		window.console.log("version 0.3");

		loadPreviouslyStoredValues();

		document.querySelector("#session").addEventListener("keyup", new EventListener() {
			public void handleEvent(Event evt) {
				sessionStorage.setItem("value", ((HTMLInputElement) evt.target).value);
				sessionStorage.setItem("timestamp", String.valueOf(JsDate.now()));
			}
		});

		document.querySelector("#local").addEventListener("keyup", new EventListener() {
			public void handleEvent(Event evt) {
				localStorage.setItem("value", ((HTMLInputElement) evt.target).value);
				localStorage.setItem("timestamp", String.valueOf(JsDate.now()));
			}
		});

		document.querySelector("#clear").addEventListener("click", new EventListener() {
			public void handleEvent(Event evt) {
				sessionStorage.clear();
				localStorage.clear();
				loadPreviouslyStoredValues();
			}
		});
	}

	private void loadPreviouslyStoredValues() {
		String[] storageNames = new String[] {"local", "session"};
		document.querySelector("#previous").innerHTML = "";
		for(String storageName:storageNames) {
			Storage storage = storageName.equals("local") ? Storage.getLocalStorageIfSupported() : Storage.getSessionStorageIfSupported();
			String value = storage.getItem("value");
			String formattedString = storageName+"Storage is empty";
			if(value!=null) {
				double delta = (JsDate.now() - Double.parseDouble(storage.getItem("timestamp"))) / 1000;
				formattedString = storageName + "Storage: " + value + " (last updated: " + delta + "s ago)";
			}
			Element li = document.createElement("li");
			li.innerHTML = formattedString;
			document.querySelector("#previous").appendChild(li);
		}
	}
}
