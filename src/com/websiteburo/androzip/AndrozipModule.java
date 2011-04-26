package com.websiteburo.androzip;

import java.io.*;
import java.util.zip.*;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;

@Kroll.module(name="Androzip", id="com.websiteburo.androzip")
public class AndrozipModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "AndrozipModule";
	private static final boolean DBG = TiConfig.LOGD;
	
	public AndrozipModule(TiContext tiContext) {
		super(tiContext);
	}

	// Methods
	@Kroll.method
	public void extract(String sourcefile, String dirout) throws IOException {
		int plen = "file://".length();
		sourcefile = sourcefile.substring(plen);
		dirout = dirout.substring(plen);
		if(!new File(sourcefile).exists()){
	    	Log.d(LCAT, "File not exist : " + sourcefile + "!");
	    }
	    else {
	    	ZipInputStream in = new ZipInputStream(new FileInputStream(sourcefile));
	    	ZipEntry ze = null;
	    	byte[] buf = new byte[8096];
	    	int a = 0;
	      
	    	while((ze = in.getNextEntry()) != null) {
	    		String name = ze.getName();
		    	if(name.length() > 0) {
					if (DBG) {
						Log.d(LCAT, "Extracting " + name);
					}
					if (ze.isDirectory()) {
						File d = new File(dirout, name);
						d.mkdirs();
						if (DBG) {
							Log.d(LCAT, "Created directory " + d.toString());
						}
						d = null;
					} else {
						FileOutputStream fos = null;
						try {
							fos = new FileOutputStream(new File(dirout,name));
							int read = 0;
							while((read = in.read(buf)) != -1) {
								fos.write(buf, 0, read);
							}
							a = a + 1;
						} finally {
							if (fos != null) {
								try {
									fos.close();
								} catch (Throwable t) {
									//Ignore
								}
							}
						}
					}
		    	}
		    	in.closeEntry();
	      }
	      
	      if(a > 0) Log.d(LCAT, Integer.toString(a) +  "Files unzipped.");
	      in.close();
	    }
	}
}
