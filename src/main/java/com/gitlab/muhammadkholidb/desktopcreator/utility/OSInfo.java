package com.gitlab.muhammadkholidb.desktopcreator.utility;

/**
 *
 * @author muhammad
 */
public class OSInfo {

    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_VERSION = System.getProperty("os.version");
    public static final String OS_ARCH = System.getProperty("os.arch");

    public static boolean isWindows() {
        return (OS_NAME.contains("win"));
    }

    public static boolean isMac() {
        return (OS_NAME.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS_NAME.contains("nix") || OS_NAME.contains("nux") || OS_NAME.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS_NAME.contains("sunos"));
    }
}
