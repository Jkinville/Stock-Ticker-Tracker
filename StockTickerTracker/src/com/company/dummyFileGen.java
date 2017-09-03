package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
public class dummyFileGen {
	File fileToZip;
	
	public dummyFileGen(String fileToZipName, String dummyName) {
		fileToZip = new File(fileToZipName);
		
		fileToZip.mkdir();
		fillFile(dummyName);
		zipDir();
	}
	
	private void fillFile(String dummyName) {
		//FileOutputStream newOutputStream = new FileOutputStream()
		for(int i = 1; i < 1000; ++i) {
			String fileName = dummyName + "_" + i;
			File newFile = new File(fileToZip, fileName+".txt");
			if(!newFile.exists()) {
				try {
					newFile.createNewFile();
				}catch (IOException e) {};
			}
		}
		
	}
	private void zipDir() {
		FileOutputStream dest = null;
		try {
			dest = new FileOutputStream(fileToZip + ".zip");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ZipOutputStream newZip = new ZipOutputStream(new BufferedOutputStream(dest));
		try {
			dest.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
