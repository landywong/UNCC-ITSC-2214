import itsc2214.PackageInfo;

/**
 * Main program for a hello world application in Java. Simple
 * project that can be used to setup other smaller projects
 * and explorations.
 */
public class Main {
    /**
     * main method, declared static so that it doesn't require
     * instantiating this class.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String msg = getMessage(args);
        System.out.println(msg);
    }

    /**
     * The getMessage() method returns a specific hello message based on the args
     * 
     * @param args contains arguments to the method.
     */
    public static String getMessage(String[] args) {
        // returns a hello message
        if (args == null) {
            return "Hola!";
        } else if (args.length > 0) {
            return "Hello, " + args[0] + "!";
        } else {
            return "Hello World!";
        }

    }

    public static String getVersion() {
        return PackageInfo.version; // returns v.# where # is the version number
        // You can also print version information for the itsc2214 package.
        // PackageInfo.printInfo();

    }
}
