package com.mainacad.service;

import java.io.*;
import java.nio.file.Files;

public class FileService {

	public static final String MAIN_DIR = System.getProperty("user.dir");
	public static final String SEP = System.getProperty("file.separator");
	public static final String MUSICFILES_DIR = MAIN_DIR + SEP + "musiñfiles";

	private static void checkTargetDir() {
		File file = new File(MUSICFILES_DIR);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	private static void checkTargetDir(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static void writeBytesToFile(byte[] bytes, String fileName) {
		checkTargetDir();
		try (FileOutputStream fileOutputStream = new FileOutputStream(MUSICFILES_DIR + SEP + fileName)) {
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeBytesToFile(byte[] bytes, File file) {
		checkTargetDir();
		try (FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath())) {
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static byte[] getBytesFromFile(String fileNames) {
		File file = new File(MUSICFILES_DIR + SEP + fileNames);

		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	public static void copyFile(String sourceName, String targetName) {
		byte[] bytes = getBytesFromFile(sourceName);
		writeBytesToFile(bytes, targetName);
	}

	public static void moveFileToFolder(String sourceName, String folderTarget) {
		byte[] bytes = getBytesFromFile(sourceName);

		File fileDir = new File(MAIN_DIR + SEP + folderTarget);
		checkTargetDir(fileDir);
		fileDir = new File(MAIN_DIR + SEP + folderTarget + SEP + sourceName);

		writeBytesToFile(bytes, fileDir);
		File sourceFile = new File(MUSICFILES_DIR + SEP + sourceName);
		sourceFile.delete();
	}

}