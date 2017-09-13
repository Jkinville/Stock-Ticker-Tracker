package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
	
	public static void main(String[] args) {
		
		String url = "http://www.nyxdata.com/nysedata/asp/factbook/table_export_csv.asp?mode=table&key=3141";
		String zipFilePath = "C:\\Users\\John\\Downloads\\dummyZip";
		String destination = "C:\\Users\\John\\Downloads\\dummyDirtesttest.csv";
		List<String> unZippedFile = null;
		 
		
		try {
			unZippedFile = unzipUtil.download_And_Unzip(url, zipFilePath, destination);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		if(unZippedFile != null) {
			String csvFile = unZippedFile.get(0);
			OnDayMarketAction odma = new OnDayMarketAction(csvFile);
			
			List<OnDayMarketAction.oneTickerDay> listOfMovement = odma.getMovers();
			for(OnDayMarketAction.oneTickerDay otod: listOfMovement) {
				System.out.println("Ticker " + otod.getM_ticker()+ " moved by " + otod.getChange() * 100);
			}
		}
		System.out.println("end");

	}

}
