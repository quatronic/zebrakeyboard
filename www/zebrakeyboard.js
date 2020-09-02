// Empty constructor
function zebraKeyboard() {}

// The function that passes work along to native shells
// layoutGroupName is a string (default: "Vanboxtel_Null_Keyboard", layout is a string (default: "Null_Keyboard")
zebraKeyboard.prototype.selectTemplate = function(layout, layoutGroupName, successCallback, errorCallback) {
  var options = {};
  options.layout = layout;
  options.layoutGroupName = layoutGroupName;
  cordova.exec(successCallback, errorCallback, 'ZebraKeyboardPlugin', 'selectTemplate', [options]);
}

// Installation constructor that binds ZebraKeyboardPlugin to window
zebraKeyboard.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.zebraKeyboard = new zebraKeyboard();
  return window.plugins.zebraKeyboard;
};
cordova.addConstructor(zebraKeyboard.install);