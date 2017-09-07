package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class main {
	
	public static void main(String[] args) {
		
		String url = "http://www.nyxdata.com/nysedata/asp/factbook/table_export_csv.asp?mode=table&key=3141";
		String zipFilePath = "C:\\Users\\John\\Downloads\\dummyZip";
		String destination = "C:\\Users\\John\\Downloads\\dummyDirtesttest.csv";
		
		try {
			List<String> unZippedFile = unzipUtil.download_And_Unzip(url, zipFilePath, destination);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("end");

	}

}
