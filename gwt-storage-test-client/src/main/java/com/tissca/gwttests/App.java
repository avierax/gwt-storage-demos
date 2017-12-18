package com.tissca.gwttests;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
//import com.google.gwt.storage.client.*;
import org.gwtproject.storage.client.*;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

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
