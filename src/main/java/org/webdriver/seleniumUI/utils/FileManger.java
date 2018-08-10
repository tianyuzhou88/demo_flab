package org.webdriver.seleniumUI.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManger extends TestBaseCase {

	/**
	 
	 * @param file
	 */
	private void deleteFile(File file)
	{
		if (file.isDirectory()) {
			File[] files=file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		}
		file.delete();
	}
	/**
	 * @param workspaceRootPath 
	 */
	public  void clearFile(String workspaceRootPath)
	{
		File file=new File(workspaceRootPath);
		if (file.exists()) {
			deleteFile(file);
		}

	}

    /**
     *
     */
	public void writeWithEncode(String path,String encode,boolean append,String content)
	{
		File file=new File(path);
		if (file.exists())
		{
			file.delete();
		}
		try {
			file.createNewFile();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter( new FileOutputStream(file,append),encode));
            bufferedWriter.write(content);
            bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    /**
     *
     */
    public void writeWithEncode(File file,String encode,boolean append,String content)
    {
        try {
            file.createNewFile();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter( new FileOutputStream(file,append),encode));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	public  void readFileToString(String path)
	{
		try {
			FileInputStream fileInputStream=new FileInputStream(new File(path));
			StringBuffer sb=new StringBuffer();
			byte[] b=new byte[1024];
			int startbyte=-1;
			while ((startbyte= fileInputStream.read(b)) != -1) {
				sb.append(b);
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 */
	public void copyFile(String sourceRelativePath,String targetRelativePath)
	{
		FileInputStream fileInputStream=null;
		OutputStream imageOutputStream=null;
		try {
			fileInputStream=new FileInputStream(new File(sourceRelativePath));
			imageOutputStream=new FileOutputStream(targetRelativePath);
			byte[] b = new byte[1024];
			int startbyte = -1;
			while ((startbyte= fileInputStream.read(b)) != -1) {
				imageOutputStream.write(b, 0, startbyte);
			}
			System.out.println("successfully copy files");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			if (fileInputStream!=null) {

				try {
					fileInputStream.close();

				} catch (IOException e2) {
					// TODO: handle exception
					System.out.println("failed to IO");
				}
			}
			if (imageOutputStream!=null) {

				try {
					imageOutputStream.close();
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("failed to IO");
				}

			}
		}
	}

	public String findFile(String partialFileName) throws IOException {
		String filePath = "";
		Path path = Paths.get(workingDir);
		DirectoryStream<Path> directoryStream;
		directoryStream = Files.newDirectoryStream(path);
		for (Path file : directoryStream) {
			if (file.getFileName().toString().contains(partialFileName)) {
				filePath =  file.getFileName().toString();
				break;
			}
		}
		directoryStream.close();
		return workingDir + filePath;
	}

	public void deleteFile(String fileName) throws IOException {
		Path path = Paths.get(workingDir);
		DirectoryStream<Path> directoryStream;
		directoryStream = Files.newDirectoryStream(path);
		for (Path file : directoryStream) {
			if (file.getFileName().toString().contains(fileName)) {
				Path fileToDelete =  Paths.get(workingDir + file.getFileName());
				Files.delete(fileToDelete);
			}
		}
		directoryStream.close();
	}

	public String filePath(String grid, int index, String partialFileName) throws IOException {
		String file = "";
		if (grid.equals("yes")) {
			String gridApi = "https://seleniumbox.cisco.com/e34/api/downloads?session=" + gridSession;
			ExecuteGet executeGet = new ExecuteGet(gridApi, "***", "***");
			executeGet.execute();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(executeGet.getResponseBody());
			file = jsonNode.get(index).path("url").asText();
		} else if (grid.equals("no")) {
			FileManger fileManger = new FileManger();
			file = fileManger.findFile(partialFileName);
		}
		return file;
	}

	public static void main(String[] args) {
		//

	}


}
