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
	public dummyFileGen(String fileToZipName, String dummyName) {
		fileToZip = new File(fileToZipName);
		this.dummyName = dummyName;
		zipDir();
		//fillFile(dummyName);
		
	}
	
	private void fillFile(ZipOutputStream zip) {
		//FileOutputStream newOutputStream = new FileOutputStream()
		for(int i = 1; i < 1000; ++i) {
			String fileName = dummyName + "_" + i;
			ZipEntry newFile = new ZipEntry(fileName+".txt");
			
			try {
				zip.putNextEntry(newFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				zip.closeEntry();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	private void zipDir() {
		FileOutputStream dest = null;
		try {
			dest = new FileOutputStream(fileToZip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ZipOutputStream zippedFi = new ZipOutputStream(new BufferedOutputStream(dest));
		fillFile(zippedFi);
		try {
			dest.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
