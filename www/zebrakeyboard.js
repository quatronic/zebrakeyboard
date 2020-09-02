// Empty constructor
function ZebraKeyboardPlugin() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
ZebraKeyboardPlugin.prototype.show = function(message, duration, successCallback, errorCallback) {
  var options = {};
  options.message = message;
  options.duration = duration;
  cordova.exec(successCallback, errorCallback, 'ZebraKeyboardPlugin', 'hideKeyboard', [options]);
}

// Installation constructor that binds ZebraKeyboardPlugin to window
ZebraKeyboardPlugin.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.ZebraKeyboardPlugin = new ZebraKeyboardPlugin();
  return window.plugins.ZebraKeyboardPlugin;
};
cordova.addConstructor(ZebraKeyboardPlugin.install);