package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

public class FileManager {
	
	private static FileManager instance = null;
	private static String separator = "/";
	
	public static FileManager getInstance(){
		if(instance== null){
			instance = new FileManager();
		}
		return instance;
	}
	
	public void copy(String originalPath,String destinationPath) throws IOException{
		
		 File destinationDirectory = new File(destinationPath);
		 if(!destinationDirectory.exists())
			 destinationDirectory.mkdirs();
		 
		 File originalFile = new File(originalPath); 
		 File copyFile = new File(destinationPath + separator + originalFile.getName());
		 
		 //Si el destino existe, lo borro y vuelvo a generar vacío
		 if(copyFile.exists()){
			 if(copyFile.delete()){
				 copyFile = new File(destinationPath + separator + originalFile.getName());
				 copyFile.createNewFile();
			 }
		 }
		 
		 /*Si el archivo existe, me fijo si es un directorio
		  * TRUE: copio los hijos dentro del directorio
		  * FALSE: copio el archivo
		  */
		 
		 if(originalFile.exists()){
			 if(!originalFile.isDirectory()){
				copyFiles(originalFile, copyFile);
			 	copyFile.renameTo(new File(destinationDirectory, originalFile.getName()));
			 }else{
				File[] directoryFiles = originalFile.listFiles(); 
				for(File file : directoryFiles){
					copy(file.getAbsolutePath(),copyFile.getAbsolutePath());
				}
			 }
		 }
	}

	private void copyFiles(File original, File copy) throws FileNotFoundException, IOException {
		
		 InputStream in = new FileInputStream(original);
		 OutputStream out = new FileOutputStream(copy);
		 
		 byte[] buf = new byte[1024];
		 int len = 0;
		 while ((len = in.read(buf))!= -1) {
			 out.write(buf, 0, len);
		 }
		 in.close();
		 out.close();
	}
	
	public void deleteFiles(String directory){
		File folderOrFile = new File(directory);
		if(folderOrFile.exists() && folderOrFile.isDirectory()){
			for(File file : folderOrFile.listFiles())
				deleteFiles(file.getAbsolutePath());
		}
		folderOrFile.delete();
	}

	public void saveSOInfo(String folder) throws IOException {
		File file = new File(folder+"\\SOInfo.txt");
		file.createNewFile();
		file.setWritable(true);
		Writer out = new BufferedWriter(new FileWriter(file));
		out.write("SO: " + System.getProperty("os.name") + " , ");
		out.write("Architecture: " + System.getProperty("os.arch") + " , ");
		out.write("SO version: " +System.getProperty("os.version") + " , ");
		out.close();
	}
}