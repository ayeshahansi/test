package com.aeturnum.scs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	
	public static Properties getProperties(String file){
		Properties prop = new Properties();
	    try {
	    	String root = System.getProperty("user.dir");
	    	System.out.println(root);
	    	String filepath = "\\src\\test\\resources\\" + file;
	    	String abspath = root+filepath;
	        InputStream input = new FileInputStream(abspath);
	        prop.load(input);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return prop;
	  }

}
