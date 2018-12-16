package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnDayMarketAction {
	public static class oneTickerDay{
		
		private String m_ticker;
		private String m_series;
		private double m_open;
		private double m_close;
		private double m_high;
		private double m_low;
		private double m_prevClose;

		public oneTickerDay(String[] quote) {
			
			setM_ticker(quote[0]);
			setM_series(quote[1]);
			setM_open(Double.parseDouble(quote[2]));
			setM_close(Double.parseDouble(quote[3]));
			setM_high(Double.parseDouble(quote[4]));
			setM_low(Double.parseDouble(quote[5]));
			setM_prevClose(Double.parseDouble(quote[6]));
			
		}

		//Getters and setters
		public String getM_ticker() {
			return m_ticker;
		}

		public void setM_ticker(String m_ticker) {
			System.out.println(m_ticker);
		}

		public String getM_series() {
			return m_series;
		}

		public void setM_series(String m_series) {
			this.m_series = m_series;
		}

		public double getM_open() {
			return m_open;
		}

		public void setM_open(double m_open) {
			this.m_open = m_open;
		}

		public double getM_close() {
			return m_close;
		}

		public void setM_close(double m_close) {
			this.m_close = m_close;
		}

		public double getM_high() {
			return m_high;
		}

		public void setM_high(double m_high) {
			this.m_high = m_high;
		}

		public double getM_low() {
			return m_low;
		}

		public void setM_low(double m_low) {
			this.m_low = m_low;
		}

		public double getM_prevClose() {
			return m_prevClose;
		}

		public void setM_prevClose(double m_prevClose) {
			this.m_prevClose = m_prevClose;
		}
		
		public double getChange(){
			if(this.getM_prevClose() != 0) {
				return (this.getM_close() - this.getM_prevClose())/this.getM_prevClose();
			}
			return Double.NaN;
		}
	}
	
	private Map<String, oneTickerDay> mapOfTickers = new HashMap<>();
	
	private String m_fileName;

	//Using a red green tree map and sorting by value, sort the value by their daily change.
	public OnDayMarketAction(String csvFile){
		
		this.m_fileName = csvFile;
		BufferedReader br = null;
		String line = null;
		String csvSplitBy = ",";
		int lineNum = 0;

		//Try to load the CSV file
		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			while((line = br.readLine()) != null) {
				lineNum++;
				if(lineNum>1) {
					
					String[] currentQuote = line.split(csvSplitBy);
					
					OnDayMarketAction.oneTickerDay od = new OnDayMarketAction.oneTickerDay(currentQuote);

					//If the value is higher than the others, add it to the front
					if(od.getM_series().compareTo("EQ") == 0){
						
						mapOfTickers.put(currentQuote[0], od);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close the IO stream
			try {
				br.close();
			} catch (IOException e) {
				
			}
		}
		
	}
	//Function to compare how much a stock had moved
	public static class StockMoveComparator implements Comparator<oneTickerDay>{
		public int compare(oneTickerDay stock1, oneTickerDay stock2) {
			double change1 = stock1.getChange(), chang2 = stock2.getChange();
			if(change1 > chang2)
				return 1;
			else if(chang2 > change1) 
				return -1;
			else
				return 0;
		}
	}

	//Function to finally get the stocks that have moved the most
	public List<oneTickerDay> getMovers(){
		
		List<oneTickerDay> ListOfAction = new ArrayList<>(this.mapOfTickers.values());
		Collections.sort(ListOfAction, new StockMoveComparator());
		
		return ListOfAction;
		
	}
}
