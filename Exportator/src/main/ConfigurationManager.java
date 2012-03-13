package main;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class ConfigurationManager {
	
	private static ConfigurationManager instance = null;
	private static String ruta = "./config.xml";
	private static String separator = "/";
	
	private String pathDashboards = "C:/Users/Matias/Desktop/DashboardServerWeb";
	
	//DSH DLL
	private String dashboardsDLL = "/bin/DashboardServer.dll";
	
	//CONFIG
	private String configDir = "/config";
	
	//LOGS
	private String logs = "/logs";
	private String exceptionLog = "DashboardException.log";
	private String sqlLog = "DashboardSQL.log";
	private String traceLog = "DashboardTrace.log";
	
	//PANELS
	private String dashboardsDir = "/data/public";
	private String tablesDir = "/data/public/tables";
	
	//WEB.CONFIG
	private String webConfig = "Web.Config";
	
	//CUSTOM ICONS
	private String customIcons = "/images/icons/userCustom";
	
	//***************METHODS*************//
	
	public static ConfigurationManager getInstance(){
		if(instance == null)
			instance = new ConfigurationManager();
		
		return instance;
	}
	
	public ConfigurationManager(){
		try {
			this.cargarConfiguracion();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getPaths(){
		List<String> paths = new ArrayList<String>();
		paths.add(getConfigDir());
		
		paths.add(getCustomIcons());
		
		paths.add(getDashboardsDir());
		paths.add(getTablesDir());
		
		paths.add(getDashboardsDLL());
		
		paths.add(getExceptionLog());
		paths.add(getSqlLog());
		paths.add(getTraceLog());
		
		paths.add(getWebConfig());
		
		return paths;
	}
	
	public void guardarConfiguracion() throws Exception{
		Element configElement = new Element("configuration");
		Document configDocument = new Document(configElement);

		configElement.addContent(new Element("pathDashboards").addContent(this.getPathDashboards()));
		
		configElement.addContent(new Element("config").addContent(this.getConfigDir()));
		configElement.addContent(new Element("customIcons").addContent(this.getCustomIcons()));
		configElement.addContent(new Element("dashboardsPanels").addContent(this.getDashboardsDir()));
		configElement.addContent(new Element("tables").addContent(this.getTablesDir()));
		configElement.addContent(new Element("DashboardsDLL").addContent(this.getDashboardsDLL()));
		configElement.addContent(new Element("logs").addContent(this.getLogs()));
		configElement.addContent(new Element("exceptionLog").addContent(this.getExceptionLog()));
		configElement.addContent(new Element("sqlLog").addContent(this.getSqlLog()));
		configElement.addContent(new Element("traceLog").addContent(this.getTraceLog()));
		configElement.addContent(new Element("webConfig").addContent(this.getWebConfig()));
		
		XMLOutputter outputter = new XMLOutputter();
		try {
		    File file = new File(ruta);
		    if(!file.exists()){
		    	file.createNewFile();
		    }
		    
		    FileWriter writer = new FileWriter(ruta);
			outputter.output(configDocument, writer);
			writer.flush();
            writer.close();
            
		} catch (java.io.IOException e) {
			throw new Exception("Error al escribir el archivo de configuracion. Detalle: " + e.getMessage());
		}
		
		instance = null;
	}
	
	public void cargarConfiguracion() throws Exception{
		
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(ruta);
			if(!xmlFile.exists()){
				this.guardarConfiguracion();
			}
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			this.setPathDashboards(rootNode.getChildText("pathDashboards"));
			
			this.setConfigDir(rootNode.getChildText("config"));
			this.setCustomIcons(rootNode.getChildText("customIcons"));
			this.setDashboardsDir(rootNode.getChildText("dashboardsPanels"));
			this.setTablesDir(rootNode.getChildText("tables"));
			this.setDashboardsDLL(rootNode.getChildText("DashboardsDLL"));
			this.setLogs(rootNode.getChildText("logs"));
			this.setExceptionLog(rootNode.getChildText("exceptionLog"));
			this.setSqlLog(rootNode.getChildText("sqlLog"));
			this.setTraceLog(rootNode.getChildText("traceLog"));
			this.setWebConfig(rootNode.getChildText("webConfig"));
			
		  } catch (Exception e) {
			  throw new Exception("Error al leer el archivo de configuracion. Detalle: " + e.getMessage());
		  }
	}
	
	public String getPathDashboards() {
		return pathDashboards;
	}
	
	public void setPathDashboards(String pathDashboards) {
		this.pathDashboards = pathDashboards;
	}
	
	public String getConfigDir() {
		return configDir;
	}
	
	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}
	
	public String getLogs() {
		return logs;
	}
	
	public void setLogs(String logs) {
		this.logs = logs;
	}
	
	public String getExceptionLog() {
		return getLogs()+separator+exceptionLog;
	}
	
	public void setExceptionLog(String exceptionLog) {
		this.exceptionLog = exceptionLog;
	}
	
	public String getSqlLog() {
		return getLogs()+separator+sqlLog;
	}
	
	public void setSqlLog(String sqlLog) {
		this.sqlLog = sqlLog;
	}
	
	public String getTraceLog() {
		return getLogs()+separator+traceLog;
	}
	
	public void setTraceLog(String traceLog) {
		this.traceLog = traceLog;
	}
	
	public String getDashboardsDir() {
		return dashboardsDir;
	}
	
	public void setDashboardsDir(String dashboardsDir) {
		this.dashboardsDir = dashboardsDir;
	}
	
	public String getTablesDir() {
		return tablesDir;
	}
	
	public void setTablesDir(String tablesDir) {
		this.tablesDir = tablesDir;
	}

	public void setWebConfig(String webConfig) {
		this.webConfig = webConfig;
	}

	public String getWebConfig() {
		return webConfig;
	}

	public void setDashboardsDLL(String dashboardsDLL) {
		this.dashboardsDLL = dashboardsDLL;
	}

	public String getDashboardsDLL() {
		return dashboardsDLL;
	}

	public void setCustomIcons(String customIcons) {
		this.customIcons = customIcons;
	}

	public String getCustomIcons() {
		return customIcons;
	}	
}
