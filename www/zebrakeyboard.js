// Empty constructor
function zebraKeyboard() {}

// The function that passes work along to native shells
// layoutGroupName is a string (default: "Vanboxtel_Null_Keyboard", layout is a string (default: "Null_Keyboard")
zebraKeyboard.prototype.selectLayout = function(successCallback, errorCallback,layoutGroupName,layout) {
  var options = {};
  options.layoutGroupName = String(layoutGroupName);
  options.layout = String(layout);
  cordova.exec(successCallback, errorCallback, 'ZebraKeyboard', 'selectLayout', [options]);
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
