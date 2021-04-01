package fr.core.os.system;

public class SystemOS {
	
	public static String getNameOS() {
		String operating_system = null;
		operating_system = "Edition: "+ System.getProperty("os.name");
		return operating_system;
	}
	
	public static String getVersionOS() {
		String version_os = null;
		version_os = "Version: "+ System.getProperty("os.version");
		return version_os;
	}
	
	public static String getArchOS() {
		String arch_os = null;
		arch_os = "Architecture: "+ System.getProperty("os.arch");
		return arch_os;
	}
	
	public static String getUserOS() {
        String user_os = null;
        user_os = "User: "+ System.getProperty("user.name");
        return user_os;
    }

    public static String getUserLanguage() {
        String user_language = null;
        user_language = "Language: "+ System.getProperty("user.language");
        return user_language;
    }

    public static String getUserCountry() {
        String user_country = null;
        user_country = "Country: "+ System.getProperty("user.country");
        return user_country;
    }

    public static String getUserHome() {
        String user_home = null;
        user_home = "User Home: "+ System.getProperty("user.home");
        return user_home;
    }
    
    public static String getJavaName() {
        String java_name = null;
        java_name = "Java: "+ System.getProperty("java.vm.name");
        return java_name;
    }

    public static String getJavaVersion() {
        String version_java = null;
        version_java = "Java Version: "+ System.getProperty("java.version");
        return version_java;
    }

    public static String getJavaVM() {
        String java_vm = null;
        java_vm = "Java VM: "+ System.getProperty("java.vm.vendor");
        return java_vm;
    }

    public static String getJavaURL() {
        String java_url = null;
        java_url = System.getProperty("java.vendor.url");
        return java_url;
    }
}
