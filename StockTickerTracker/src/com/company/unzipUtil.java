package com.company;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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
	//Set the byte size for the header.
	private static final int S_BYTE_SIZE = 4096;

	//Function to download and unzip the file.
	public static List<String> download_And_Unzip(String urlString, String zipFilePath, String directory) throws IOException{
		URL AMEXurl = new URL(urlString);
		//Initialize the browser agent for downloads.
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
		"AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
		java.net.URLConnection con = AMEXurl.openConnection();

		//Start the connection
		con.setRequestProperty("User_Agent", userAgent);
		ReadableByteChannel zipChannel = Channels.newChannel(con.getInputStream());
		
		String fieldValue = con.getHeaderField("Content-Disposition");

		//Start the download using a byte stream
		String[] holder = fieldValue.split("\\.");
		//If the header does not indicate it is a zip skip unzipping
		if(holder[1] != "zip") {
			
			FileOutputStream fos = new FileOutputStream(zipFilePath + ".csv");
			fos.getChannel().transferFrom(zipChannel, 0, Long.MAX_VALUE);
			List<String> unzippedFilelist = new ArrayList<>();
			unzippedFilelist.add(zipFilePath + ".csv");
			fos.close();
			return unzippedFilelist;
		}
		
		//Unzip the file
		else {
			
			FileOutputStream fos = new FileOutputStream(zipFilePath + ".zip");
			fos.getChannel().transferFrom(zipChannel, 0, Long.MAX_VALUE);
			fos.close();
			return unzip(zipFilePath, directory);
		}
		
	}
	//Function to unzip the file
	public static List<String> unzip(String zipFilePath, String Directory) throws IOException{
	
		List<String> unzippedFilelist = new ArrayList<>();
		File destDir = new File(Directory);

		//If the target directory does not exist, create it
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

	//Function to extarct the file using a byte stream
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
