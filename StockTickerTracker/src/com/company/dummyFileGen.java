package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
public class dummyFileGen {
	File fileToZip;
	String dummyName;
	public dummyFileGen() {
		fillFile();
		
		
		//fillFile(dummyName);
		
	}
	
	private void fillFile() {
		//FileOutputStream newOutputStream = new FileOutputStream()
		File test = new File("C:\\Users\\John\\Downloads\\dummyZip.zip");
		FileOutputStream tester = null;
		try {
			tester = new FileOutputStream(test);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ZipOutputStream out = null;
		
		out = new ZipOutputStream(tester);
		for(int i = 0; i < 100000; ++i) {
		ZipEntry newzip =  new ZipEntry("dummyFile" + i +".text");
		try {
			out.putNextEntry(newzip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

}
