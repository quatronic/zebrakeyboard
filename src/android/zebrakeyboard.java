
import android.content.Intent;
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
		if ("selectTemplate".equals(action)) {
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
			this.selectTemplate(layoutGroupName,layout);  
			  // Send a positive result to the callbackContext		
			return true;
		}
		return false;  // Returning false results in a "MethodNotFound" error.
	}
	
	private void selectTemplate(String layoutGroupName, String layout) {
		if (layoutGroupName != null && layoutGroupName.length() > 0 && layout != null && layout.length() > 0) {
				Intent intent = new Intent();
				intent.setAction(ACTION);
				intent.setPackage(PACKAGE);
				intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
				intent.putExtra("CURRENT_LAYOUT_GROUP", layoutGroupName);
				intent.putExtra("CURRENT_LAYOUT_NAME", layout);
				sendBroadcast(intent);
				callbackContext.success();
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}
}
