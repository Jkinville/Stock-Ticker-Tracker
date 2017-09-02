package com.company;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/**
 * 
 * @author Johnathon Kinville
 *
 */
public class unzipUtil {
	private static final int S_BYTE_SIZE = 4096;
	
	public static List<String> download_And_Unzip(String urlString, String zipFilePath, String directory) throws IOException{
		return null;
	}
	
	public static List<String> unzip(String zipFilePath, String Directory) throws IOException{
	
		List<String> unzippedFilelist = new ArrayList<>();
		File destDir = new File(Directory);
		
		if (!(destDir.exists())) {
			destDir.mkdir();
		}
		
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		
		
		for(ZipEntry zipEntry = zipIn.getNextEntry(); zipEntry != null; zipEntry = zipIn.getNextEntry()) {
			String filePath = Directory+File.separator+zipEntry.getName();
			
			if(!zipEntry.isDirectory()) {
				String oneUnZippedFile = extractFile(zipIn, filePath);
				unzippedFilelist.add(oneUnZippedFile);
			}
			else {
				File dir = new File(filePath);
				dir.mkdir();
			}
			
		}
		zipIn.close();
		return  unzippedFilelist;
	}
	
	private static String extractFile(ZipInputStream zipIn, String filePath) throws IOException{
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[S_BYTE_SIZE];
		int read = 0;
		while((read=zipIn.read(bytesIn))!= -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
		return filePath;
	}
}
