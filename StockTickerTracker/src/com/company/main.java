package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class main {
	
	public static void main(String[] args) {
		
		List<String> newList = null;
		dummyFileGen newFile = new dummyFileGen();

		try {
			newList = unzipUtil.unzip("C:\\Users\\John\\Downloads\\dummyZip.zip", "C:\\Users\\John\\Downloads\\dummyDirtest");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newList.forEach(item->System.out.println(item));
		
		System.out.println("end");

	}

}
