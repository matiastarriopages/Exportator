package main;

import java.awt.TextArea;

public class Exportator {
	
	private static String folder = "Dashboards";
	
	public void startExporting(String DashboardsPath, TextArea textArea){
		try {
			
			//Obtain and copy each file in a Temp Folder
			for(String path : ConfigurationManager.getInstance().getPaths()){
				FileManager.getInstance().copy(DashboardsPath + path,folder);
				textArea.setText(textArea.getText() + "\nCopied: " + DashboardsPath + path);
			}
			
			//Get Machine.config 
			FileManager.getInstance().copy(ConfigurationManager.getInstance().getMachineConfig(),folder);
			textArea.setText(textArea.getText() + "\nCopied: machine.config");
			
			//Get SO information and save it in a file
			FileManager.getInstance().saveSOInfo(folder);
			textArea.setText(textArea.getText() + "\nCopied: Operating System information");
			
			//Zip folder
			ZipManager.getInstance().zip(folder);
			textArea.setText(textArea.getText() + "\n...Zipping data...");
			
			//Delete all copied files
			FileManager.getInstance().deleteFiles(folder);
			textArea.setText(textArea.getText() + "\nDeleting temp files...");
			
			textArea.setText(textArea.getText() + "\nExport succeed");
			
		} catch (Exception e) {
			textArea.setText(e.getMessage());
		}
	}
}
	
