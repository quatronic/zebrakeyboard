package com.quatronic.plugin;

import android.content.Intent;
import android.content.Context;
// Cordova-required packages
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class calls a specific template from the zebra keyboard.
	https://developer.android.com/reference/android/content/Intent
	https://www.javatpoint.com/android-intent-tutorial
	https://techdocs.zebra.com/enterprise-keyboard/latest/guide/apis/
	https://stackoverflow.com/questions/40375555/start-android-activity-from-cordova-plugin
 */
public class ZebraKeyboard extends CordovaPlugin {

	private static final String ACTION = "com.symbol.ekb.api.ACTION_UPDATE";
	private static final String PACKAGE = "com.symbol.mxmf.csp.enterprisekeyboard";

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action        The action to execute.
     * @param args          JSONArry of arguments for the plugin.
     * @param callbackId    The callback id used when calling back into JavaScript.
     * @return              A PluginResult object with a status and message.
     */
            
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Context context = cordova.getActivity().getApplicationContext();
		if ("selectLayout".equals(action)) {
			String layout;
			String layoutGroupName;
		  try {
			JSONObject options = args.getJSONObject(0);
			layout = options.getString("layout");
			layoutGroupName = options.getString("layoutGroupName");
		  } catch (JSONException e) {
			callbackContext.error("Error encountered: " + e.getMessage());
			return false;
		  }
			this.selectTemplate(layoutGroupName,layout,callbackContext,context);  
			  // Send a positive result to the callbackContext		
			return true;
		}
		return false;  // Returning false results in a "MethodNotFound" error.
	}
	
	private void selectLayout(String layoutGroupName, String layout,CallbackContext callbackContext,Context context) {
		if (layoutGroupName != null && layoutGroupName.length() > 0 && layout != null && layout.length() > 0) {
				Intent intent = new Intent();
				intent.setAction(ACTION);
				intent.setPackage(PACKAGE);
				intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
				intent.putExtra("CURRENT_LAYOUT_GROUP", layoutGroupName);
				intent.putExtra("CURRENT_LAYOUT_NAME", layout);
				this.cordova.getActivity().startActivity(intent);
				// context.sendBroadcast(intent);    trying this for a change
				callbackContext.success();
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}
}
