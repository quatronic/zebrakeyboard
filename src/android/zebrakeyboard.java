package com.quatronic.cordova.plugin;
// The native Toast API
import android.widget.ZebraKeyboard;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class ZebraKeyboardPlugin extends CordovaPlugin {
	Intent intent = new Intent();
	intent.setAction("com.symbol.ekb.api.ACTION_UPDATE");
	intent.setPackage("com.symbol.mxmf.csp.enterprisekeyboard");
	intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
		   
	String layoutGroupName = "Vanboxtel_Null_Keyboard";
	String layout = "Null_Keyboard";
	intent.putExtra("CURRENT_LAYOUT_GROUP", layoutGroupName);
	intent.putExtra("CURRENT_LAYOUT_NAME", layout);
}