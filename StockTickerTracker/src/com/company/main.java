package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class main {
	
	public static void main(String[] args) {
		//Initialize the objects needed for the program to operate with the 2 file paths pointing to a file in the
		//program folder.
		String url = null;
		String zipFilePath = "..\\dummyZip";
		String destination = "..\\dummyDirtesttest.csv";
		List<String> unZippedFile = null;

		//Get the url path for the CSV zip from the user
		System.out.println("Enter the URL to download the stock data from");
		Scanner scn = new Scanner(System.in);
		url = scn.nextLine();

		//Try to download and unzip the file, if fail print the error to the console
		try {
			unZippedFile = unzipUtil.download_And_Unzip(url, zipFilePath, destination);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		//If the file unzipped successfully load it into memory and perform a sort on it to get our results for the day.
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
