package au.com.translatorss;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;


public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path newFilePath = Paths.get("src/main/resources/file/newFile_jdk7.txt");
		try {
			Files.createFile(newFilePath);
			File file = new File(newFilePath.getFileName().toString());
			System.out.println("sdfsdfsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getFileWithUtil(String fileName) {

		String result = "";
		ClassLoader classLoader = getClass().getClassLoader();
		try {
		    result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	  }
}
