package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

public class readFile {

	public static void main(String[] args) throws Exception {

		FileInputStream file = new FileInputStream(new File("Data.properties")); // can give path

		Properties datapro = new Properties();
		datapro.load(file);
		System.out.println(datapro.getProperty("username"));
		
		readText();

	}
	
	public static void readText() throws Exception {
		Scanner sc = new Scanner(new File("text_dummy"));
		while (sc.hasNextLine())
			System.out.println(sc.nextLine());
	}

}
