package utils;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtils {

	public static String getFileContentType(String filePath) throws Exception {

		try {

			if (filePath == null || filePath.isEmpty()) {
				throw new NullPointerException("Error: File path found either null or empty");
			} else {
				URL url = new URL(filePath);
				URLConnection urlConnection = url.openConnection();
				return urlConnection.getContentType();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public static String getFileContentType(File file) throws Exception {
		return getFileContentType(file.getAbsolutePath());
	}

	public static String getFileEncoding(String filePath) throws Exception {

		try {

			if (filePath == null || filePath.isEmpty()) {
				throw new NullPointerException("Error: File path found either null or empty");
			} else {
				URL url = new URL(filePath);
				URLConnection urlConnection = url.openConnection();

				String encoding = urlConnection.getContentEncoding();
				return encoding;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static int getContentLength(String filePath) throws Exception {

		try {

			if (filePath == null || filePath.isEmpty()) {
				throw new NullPointerException("Error: File path found either null or empty");
			} else {
				URL url = new URL(filePath);
				URLConnection urlConnection = url.openConnection();
				return urlConnection.getContentLength();
			}
		} catch (Exception e) {
			throw e;
		}

	}

	public static long getContentLengthInLong(String filePath) throws Exception {

		try {

			if (filePath == null || filePath.isEmpty()) {
				throw new NullPointerException("Error: File path found either null or empty");
			} else {
				URL url = new URL(filePath);
				URLConnection urlConnection = url.openConnection();
				return urlConnection.getContentLengthLong();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean checkForFile(File file) throws Exception {

		try {

			if (file == null) {
				throw new NullPointerException("Error: File found null.");
			} else if (!file.exists()) {
				throw new NullPointerException("Error: File not exists.");
			} else if (!file.isFile()) {
				throw new Exception("Error: Not a file.");
			} else
				return true;

		} catch (Exception e) {
			throw e;
		}

	}

	public static boolean checkForDirectory(File file) throws Exception {

		try {

			if (file == null) {
				throw new NullPointerException("Error: Directory found null.");
			} else if (!file.exists()) {
				throw new NullPointerException("Error: Directory not exists.");
			} else if (file.isFile()) {
				throw new Exception("Error: Not a directory.");
			} else
				return true;

		} catch (Exception e) {
			throw e;
		}
	}

	public static String readFileData(File file) throws Exception {

		try {

			checkForFile(file);

			List<String> lines = Files.readAllLines(file.toPath());

			String data = new String();
			for (String string : lines) {
				data = data + string;
			}

			return data;

		} catch (Exception e) {
			throw e;
		}

	}
	
	public static byte[] readFileBytes(File file) throws Exception {

		try {

			checkForFile(file);
			return Files.readAllBytes(file.toPath());

		} catch (Exception e) {
			throw e;
		}

	}


	public static void writeFileDataInString(File file, String data) throws Exception{

		try {
			checkForFile(file);
			
			if (file.exists()) {
				file.delete();
			}
			
			Files.write(file.toPath(), data.getBytes(), StandardOpenOption.CREATE_NEW);

		} catch (Exception e) {
			throw e;
		}
	}

	public static void writeFileDataInByte(File file, byte[] data) throws Exception{

		
		try {

			checkForFile(file);
			
			if (file.exists()) {
				file.delete();
			}
			
			Files.write(file.toPath(), data, StandardOpenOption.CREATE_NEW);

		} catch (Exception e) {
			throw e;
		}
	}

}
