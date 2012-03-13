package main;

public class Exportator {
	
	private static String folder = "C:\\Test";
	
	public static void main(String args[]){
		try {
			
			//Obtain and copy each file in a Temp Folder
			for(String path : ConfigurationManager.getInstance().getPaths()){
				FileManager.getInstance().copy(ConfigurationManager.getInstance().getPathDashboards()+path,folder);
			}
			//Get Machine.config 
			FileManager.getInstance().copy(ConfigurationManager.getInstance().getMachineConfig(),folder);
			
			//Get SO information and save it in a file
			FileManager.getInstance().saveSOInfo(folder);
			
			//Zip folder
			ZipManager.getInstance().zip(folder);
			
			//Delete all copied files
			FileManager.getInstance().deleteFiles(folder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
