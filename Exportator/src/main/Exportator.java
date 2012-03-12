package main;

public class Exportator {
	
	private static String folder = "C:\\Test";
	
	public static void main(String args[]){
		try {
			for(String path : ConfigurationManager.getInstance().getPaths()){
				CopyManager.getInstance().copy(path,folder);
			}
			
			ZipManager.getInstance().zip(folder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
