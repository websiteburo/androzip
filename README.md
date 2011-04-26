# androzip Module

## Description

Titanium Android module to extract Zip files

## Accessing the androzip Module

To access this module from JavaScript, you would do the following:

	var androzip = require("com.websiteburo.androzip");

The androzip variable is a reference to the Module object.	

## Reference

Install
=======

1. Download or build from source androzip module.
2. Place com.websiteburo.androzip-android-x.y.z.zip file to /Library/Application Support/Titanium and unzip it.

How to use
==========

Register the androzip module with your application by editing 'tiapp.xml' and adding the module:

	
	<modules>
		<module version="0.1">com.websiteburo.androzip</module>
	</modules>


Put this into your code : 

	var androzip = require("com.websiteburo.androzip");
	androzip.extract(path_to_zip_file, dir_to_extract)


Example
=======

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
	
## Author

Henri Bourcereau - Websiteburo
http://www.websiteburo.com

## License

MIT License




