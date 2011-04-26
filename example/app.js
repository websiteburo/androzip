//app.js: download zip from DropBox to Documents folder
//        and extact file to Documents folder
 
Titanium.UI.setBackgroundColor('#000');
 
var win1 = Titanium.UI.createWindow({
      title:'AndroZip test',
      backgroundColor:'#000'
});
 
win1.open();
var xhr = Titanium.Network.createHTTPClient();

var androzip = require('com.websiteburo.androzip');
Ti.API.info("module is => " + androzip);

 
xhr.onload = function(){
   var f = Ti.Filesystem.getFile(Ti.Filesystem.applicationDataDirectory, 'test.zip');
   var fdir = Ti.Filesystem.getFile(Ti.Filesystem.applicationDataDirectory);
   f.write(this.responseData);
   androzip.extract(f.nativePath, fdir.nativePath);
};
xhr.open('GET','http://dl.dropbox.com/u/1400234/test.zip');
xhr.send();