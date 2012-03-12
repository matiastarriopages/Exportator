package main;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {
	
	private static ConfigurationManager instance = null;
	private String pathDashboards = "E:/Workspace/Dashboards/Dashboards_2.0_HF/DashboardServerWeb";
	
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
	
	private static String separator = "/";
	
	//***************METHODS*************//
	
	public static ConfigurationManager getInstance(){
		if(instance == null)
			instance = new ConfigurationManager();
		
		return instance;
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
	
	public String getPathDashboards() {
		return pathDashboards;
	}
	
	public void setPathDashboards(String pathDashboards) {
		this.pathDashboards = pathDashboards;
	}
	
	public String getConfigDir() {
		return getPathDashboards()+configDir;
	}
	
	public void setConfigDir(String configDir) {
		this.configDir = configDir;
	}
	
	public String getLogs() {
		return getPathDashboards()+logs;
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
		return getPathDashboards()+dashboardsDir;
	}
	
	public void setDashboardsDir(String dashboardsDir) {
		this.dashboardsDir = dashboardsDir;
	}
	
	public String getTablesDir() {
		return getPathDashboards()+tablesDir;
	}
	
	public void setTablesDir(String tablesDir) {
		this.tablesDir = tablesDir;
	}

	public void setWebConfig(String webConfig) {
		this.webConfig = webConfig;
	}

	public String getWebConfig() {
		return getPathDashboards()+webConfig;
	}

	public void setDashboardsDLL(String dashboardsDLL) {
		this.dashboardsDLL = dashboardsDLL;
	}

	public String getDashboardsDLL() {
		return getPathDashboards()+dashboardsDLL;
	}

	public void setCustomIcons(String customIcons) {
		this.customIcons = customIcons;
	}

	public String getCustomIcons() {
		return getPathDashboards()+customIcons;
	}
	
}
