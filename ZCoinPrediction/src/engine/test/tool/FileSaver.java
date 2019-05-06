package engine.test.tool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import engine.analysis.alogorithm1.Algorithm1Result;
import engine.dto.DealDTO;

public class FileSaver {
	public static BufferedWriter saveFile = null;
	private static  final String saveFileName = "./result/result.log";
	
	public static void init() {
		try {
			saveFile = new BufferedWriter(new FileWriter(saveFileName));
			saveFile.write("var aValue ='");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveToFile(DealDTO dto, long timeStamp) {
		
		Algorithm1Result result = (Algorithm1Result) dto.getResult();
		if (result == null) 
			return;
		try {
			saveFile.write(timeStamp + ";" + result.getStandard().getA() + ";" + result.getLatest().getA() + ":");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void saveToFile(String str) {
		try {
			saveFile.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void finalizer() {
		try {
			saveFile.write("';");
			saveFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		init();
		saveToFile(null, 0);
		finalizer();
	}
}
