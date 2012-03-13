package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipManager {
	
	private static ZipManager instance = null;
	
	public static ZipManager getInstance(){
		if(instance == null)
			instance = new ZipManager();
		
		return instance;
	}
	
	public void zip(String dir) throws Exception {
	    File directory = new File(dir);
	    ZipOutputStream out = new ZipOutputStream(new FileOutputStream("DSHBKP_" + getDate() + ".zip"));
	    addDir(directory, out);
	    out.close();
	  }

	//Gets childs of a Folder directory and zips them recursively.
	private static void addDir(File directory, ZipOutputStream out) throws IOException {
	    File[] files = directory.listFiles();
	    byte[] tmpBuf = new byte[1024];

	    for (File file : files) {
	      if(file.isDirectory()) {
	        addDir(file, out);
	        continue;
	      }
	      
	      FileInputStream in = new FileInputStream(file.getPath());
	      out.putNextEntry(new ZipEntry(file.getPath()));
	      int len;
	      while ((len = in.read(tmpBuf)) > 0) {
	        out.write(tmpBuf, 0, len);
	      }
	      out.closeEntry();
	      in.close();
	    }
	}
	
	private String getDate(){
		Calendar calendar = Calendar.getInstance();
		String dia = Integer.toString(calendar.get(Calendar.DATE));
		String mes = Integer.toString(calendar.get(Calendar.MONTH)+1);
		String annio = Integer.toString(calendar.get(Calendar.YEAR));
		String hora = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		String minuto = Integer.toString(calendar.get(Calendar.MINUTE));
		String segundo = Integer.toString(calendar.get(Calendar.SECOND));
		
		return annio+mes+dia+"_"+hora+"-"+minuto+"-"+segundo;
	}
	
}
