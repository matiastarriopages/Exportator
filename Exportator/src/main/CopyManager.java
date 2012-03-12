package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyManager {
	
	private static CopyManager instance = null;
	private static String separator = "/";
	
	public static CopyManager getInstance(){
		if(instance== null){
			instance = new CopyManager();
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
}