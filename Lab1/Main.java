import itsc2214.*;

/**
 * Run this program and copy the text output from the TERMINAL
 * and submit it in the canvas assignment.
 */
public class Main {
    public static void main(String[] args) {
        String jarVersion = PackageInfo.version;
        String javaVersion = System.getProperty("java.version");
        String userName = System.getProperty("user.name");

        System.out.println("--- Copy From Here ---");
        System.out.println("User name: " + userName);
        System.out.println("Java/JDK Version: " + javaVersion);
        System.out.println("ITSC2214 jar version: " + jarVersion);
        System.out.println("Built : " + PackageInfo.buildDate);
        System.out.println("-----  To Here  -----");
    }
}