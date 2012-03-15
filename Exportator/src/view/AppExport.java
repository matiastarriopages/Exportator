package view;

import javax.swing.*;

import main.Exportator;

import java.awt.event.*;
import java.awt.*;
import java.io.File;

@SuppressWarnings("serial")
public class AppExport extends JPanel implements ActionListener {

	TextArea textArea;
    JFileChooser chooser;
   
      public static void main(String s[]) {
		  JFrame frame = new JFrame("Exportator");
		  AppExport panel = new AppExport();
		  
		  frame.addWindowListener(
			new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			      System.exit(0);
			      }
			    }
			);
		  Image im = Toolkit.getDefaultToolkit().getImage("images/ExportIco.jpg");
		  frame.setIconImage(im);
		    
		  frame.getContentPane().add(panel,"Center");
		  frame.setSize(panel.getPreferredSize());
		  frame.setVisible(true);
		  frame.setResizable(false);
		  frame.setLocationRelativeTo(null);
	  }
    
	  public AppExport() {
		JLabel label = new JLabel("Choose Dashboard's Web directory:               ");
		add(label);
		
	   	JButton go = new JButton("Browse...");
	   	go.addActionListener(this);
	   	add(go);
	   	
	   	textArea = new TextArea("",20,100,TextArea.SCROLLBARS_NONE);
	   	textArea.setEditable(false);
	   	add(textArea);
	  }

	  public void actionPerformed(ActionEvent e) {
	        
	    chooser = new JFileChooser(); 
	    chooser.setCurrentDirectory(new File("C:\\Program Files (x86)\\Tango04\\Dashboards\\Web"));
	    chooser.setDialogTitle("Browse...");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	
	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
	    	textArea.setText("");
	    	new Exportator().startExporting(chooser.getSelectedFile().getPath(),textArea);
	    }else {
	    	textArea.setText("No Selection");
	    }
	    
	  }
	   
	  public Dimension getPreferredSize(){
		  return new Dimension(800, 400);
	  }
	    
	  
}
