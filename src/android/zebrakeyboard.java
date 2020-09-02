package com.quatronic.plugin;
// The native Toast API
import android.widget.ZebraKeyboard;
import android.content.Intent;
// Cordova-required packages
import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class calls a specific template from the zebra keyboard.
	https://developer.android.com/reference/android/content/Intent
	https://www.javatpoint.com/android-intent-tutorial
 */
public class ZebraKeyboardPlugin extends Plugin {

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
		if ("hideKeyboard".equals(action)) {
			this.selectTemplate("Vanboxtel_Null_Keyboard",	"Null_Keyboard");   
			return true;
		}
		return false;  // Returning false results in a "MethodNotFound" error.
	}
	
	private void selectTemplate(String layoutGroupName, String layout) {
		if (layoutGroupName != null && layoutGroupName.length() > 0 && layout != null && layout.length() > 0) {

				callbackContext.success();
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}
}
