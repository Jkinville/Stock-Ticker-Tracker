package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class main {
	
	public static void main(String[] args) {
		
		List<String> newList = null;
		try {
			newList = unzipUtil.unzip("C:\\Users\\John\\Downloads\\lab1-3.zip", "lab 1-3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newList.forEach(item->System.out.println(item));
		
		System.out.println("end");

	}

}
