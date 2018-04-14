/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2018 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.livequery;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;

@Kroll.module(name = "TitaniumParseLiveQuery", id = "ti.livequery")
public class TitaniumParseLiveQueryModule extends KrollModule
{
	// Standard Debugging variables
	private static final String LCAT = "TitaniumParseLiveQueryModule";
	private static final boolean DBG = TiConfig.LOGD;

	// Methods

	@Kroll.method
	public void initialize(KrollDict args)
	{
		String applicationId = (String) args.get("applicationId");
		String clientKey = (String) args.get("clientKey");
		String server = (String) args.get("server");
		Boolean localDatastoreEnabled = TiConvert.toBoolean(args.get("localDatastoreEnabled"), false);

		Parse.Configuration.Builder builder =
			new Parse.Configuration.Builder(TiApplication.getInstance().getCurrentActivity())
				.applicationId(applicationId)
				.clientKey(clientKey)
				.server(server);

		if (localDatastoreEnabled) {
			builder = builder.enableLocalDataStore();
		}

		Parse.initialize(builder.build());
	}

	@Kroll.method
	public void saveObject(KrollDict object)
	{
		String className = (String) object.get("className");
		KrollDict parameters = (KrollDict) object.get("parameters");
		KrollFunction callback = (KrollFunction) object.get("callback");

		ParseObject newObject = new ParseObject(className);

		for (String key : parameters.keySet()) {
			newObject.put(key, parameters.get(key));
		}

		newObject.saveInBackground();
	}

	@Kroll.method
	public void clearAllCachedResults()
	{
		ParseQuery.clearAllCachedResults();
	}
}