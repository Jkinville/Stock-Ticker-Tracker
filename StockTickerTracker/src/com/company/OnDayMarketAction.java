package com.company;

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

		public String getM_ticker() {
			return m_ticker;
		}

		public void setM_ticker(String m_ticker) {
			this.m_ticker = m_ticker;
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
	}

}
