package org.apache.commons.lang3;

import java.io.File;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class SystemUtils {
    public static final String AWT_TOOLKIT;
    public static final String FILE_ENCODING;

    @Deprecated
    public static final String FILE_SEPARATOR;
    public static final boolean IS_JAVA_10;
    public static final boolean IS_JAVA_11;
    public static final boolean IS_JAVA_12;
    public static final boolean IS_JAVA_13;
    public static final boolean IS_JAVA_1_1;
    public static final boolean IS_JAVA_1_2;
    public static final boolean IS_JAVA_1_3;
    public static final boolean IS_JAVA_1_4;
    public static final boolean IS_JAVA_1_5;
    public static final boolean IS_JAVA_1_6;
    public static final boolean IS_JAVA_1_7;
    public static final boolean IS_JAVA_1_8;

    @Deprecated
    public static final boolean IS_JAVA_1_9;
    public static final boolean IS_JAVA_9;
    public static final boolean IS_OS_400;
    public static final boolean IS_OS_AIX;
    public static final boolean IS_OS_FREE_BSD;
    public static final boolean IS_OS_HP_UX;
    public static final boolean IS_OS_IRIX;
    public static final boolean IS_OS_LINUX;
    public static final boolean IS_OS_MAC;
    public static final boolean IS_OS_MAC_OSX;
    public static final boolean IS_OS_MAC_OSX_CHEETAH;
    public static final boolean IS_OS_MAC_OSX_EL_CAPITAN;
    public static final boolean IS_OS_MAC_OSX_JAGUAR;
    public static final boolean IS_OS_MAC_OSX_LEOPARD;
    public static final boolean IS_OS_MAC_OSX_LION;
    public static final boolean IS_OS_MAC_OSX_MAVERICKS;
    public static final boolean IS_OS_MAC_OSX_MOUNTAIN_LION;
    public static final boolean IS_OS_MAC_OSX_PANTHER;
    public static final boolean IS_OS_MAC_OSX_PUMA;
    public static final boolean IS_OS_MAC_OSX_SNOW_LEOPARD;
    public static final boolean IS_OS_MAC_OSX_TIGER;
    public static final boolean IS_OS_MAC_OSX_YOSEMITE;
    public static final boolean IS_OS_NET_BSD;
    public static final boolean IS_OS_OPEN_BSD;
    public static final boolean IS_OS_OS2;
    public static final boolean IS_OS_SOLARIS;
    public static final boolean IS_OS_SUN_OS;
    public static final boolean IS_OS_UNIX;
    public static final boolean IS_OS_WINDOWS;
    public static final boolean IS_OS_WINDOWS_10;
    public static final boolean IS_OS_WINDOWS_2000;
    public static final boolean IS_OS_WINDOWS_2003;
    public static final boolean IS_OS_WINDOWS_2008;
    public static final boolean IS_OS_WINDOWS_2012;
    public static final boolean IS_OS_WINDOWS_7;
    public static final boolean IS_OS_WINDOWS_8;
    public static final boolean IS_OS_WINDOWS_95;
    public static final boolean IS_OS_WINDOWS_98;
    public static final boolean IS_OS_WINDOWS_ME;
    public static final boolean IS_OS_WINDOWS_NT;
    public static final boolean IS_OS_WINDOWS_VISTA;
    public static final boolean IS_OS_WINDOWS_XP;
    public static final boolean IS_OS_ZOS;
    public static final String JAVA_AWT_FONTS;
    public static final String JAVA_AWT_GRAPHICSENV;
    public static final String JAVA_AWT_HEADLESS;
    public static final String JAVA_AWT_PRINTERJOB;
    public static final String JAVA_CLASS_PATH;
    public static final String JAVA_CLASS_VERSION;
    public static final String JAVA_COMPILER;
    public static final String JAVA_ENDORSED_DIRS;
    public static final String JAVA_EXT_DIRS;
    public static final String JAVA_HOME;
    public static final String JAVA_HOME_KEY = "java.home";
    public static final String JAVA_IO_TMPDIR;
    public static final String JAVA_IO_TMPDIR_KEY = "java.io.tmpdir";
    public static final String JAVA_LIBRARY_PATH;
    public static final String JAVA_RUNTIME_NAME;
    public static final String JAVA_RUNTIME_VERSION;
    public static final String JAVA_SPECIFICATION_NAME;
    public static final String JAVA_SPECIFICATION_VENDOR;
    public static final String JAVA_SPECIFICATION_VERSION;
    public static final JavaVersion JAVA_SPECIFICATION_VERSION_AS_ENUM;
    public static final String JAVA_UTIL_PREFS_PREFERENCES_FACTORY;
    public static final String JAVA_VENDOR;
    public static final String JAVA_VENDOR_URL;
    public static final String JAVA_VERSION;
    public static final String JAVA_VM_INFO;
    public static final String JAVA_VM_NAME;
    public static final String JAVA_VM_SPECIFICATION_NAME;
    public static final String JAVA_VM_SPECIFICATION_VENDOR;
    public static final String JAVA_VM_SPECIFICATION_VERSION;
    public static final String JAVA_VM_VENDOR;
    public static final String JAVA_VM_VERSION;

    @Deprecated
    public static final String LINE_SEPARATOR;
    public static final String OS_ARCH;
    public static final String OS_NAME;
    public static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    public static final String OS_VERSION;

    @Deprecated
    public static final String PATH_SEPARATOR;
    public static final String USER_COUNTRY;
    public static final String USER_DIR;
    public static final String USER_DIR_KEY = "user.dir";
    public static final String USER_HOME;
    public static final String USER_HOME_KEY = "user.home";
    public static final String USER_LANGUAGE;
    public static final String USER_NAME;
    public static final String USER_TIMEZONE;

    static {
        String property;
        String property2;
        String property3;
        String property4;
        String property5;
        String property6;
        String property7;
        String property8;
        String property9;
        String property10;
        String property11;
        String property12;
        String property13;
        String property14;
        String property15;
        String property16;
        String property17;
        String property18;
        String property19;
        String property20;
        String property21;
        String property22;
        String property23;
        String property24;
        String property25;
        String property26;
        String property27;
        String property28;
        String property29;
        String property30;
        String property31;
        String property32;
        String property33;
        String property34;
        String property35;
        String property36;
        String property37;
        String property38;
        String property39;
        String property40;
        String property41;
        String property42;
        String property43 = null;
        try {
            property = System.getProperty("awt.toolkit");
        } catch (SecurityException unused) {
            property = null;
        }
        AWT_TOOLKIT = property;
        try {
            property2 = System.getProperty("file.encoding");
        } catch (SecurityException unused2) {
            property2 = null;
        }
        FILE_ENCODING = property2;
        try {
            property3 = System.getProperty("file.separator");
        } catch (SecurityException unused3) {
            property3 = null;
        }
        FILE_SEPARATOR = property3;
        try {
            property4 = System.getProperty("java.awt.fonts");
        } catch (SecurityException unused4) {
            property4 = null;
        }
        JAVA_AWT_FONTS = property4;
        try {
            property5 = System.getProperty("java.awt.graphicsenv");
        } catch (SecurityException unused5) {
            property5 = null;
        }
        JAVA_AWT_GRAPHICSENV = property5;
        try {
            property6 = System.getProperty("java.awt.headless");
        } catch (SecurityException unused6) {
            property6 = null;
        }
        JAVA_AWT_HEADLESS = property6;
        try {
            property7 = System.getProperty("java.awt.printerjob");
        } catch (SecurityException unused7) {
            property7 = null;
        }
        JAVA_AWT_PRINTERJOB = property7;
        try {
            property8 = System.getProperty("java.class.path");
        } catch (SecurityException unused8) {
            property8 = null;
        }
        JAVA_CLASS_PATH = property8;
        try {
            property9 = System.getProperty("java.class.version");
        } catch (SecurityException unused9) {
            property9 = null;
        }
        JAVA_CLASS_VERSION = property9;
        try {
            property10 = System.getProperty("java.compiler");
        } catch (SecurityException unused10) {
            property10 = null;
        }
        JAVA_COMPILER = property10;
        try {
            property11 = System.getProperty("java.endorsed.dirs");
        } catch (SecurityException unused11) {
            property11 = null;
        }
        JAVA_ENDORSED_DIRS = property11;
        try {
            property12 = System.getProperty("java.ext.dirs");
        } catch (SecurityException unused12) {
            property12 = null;
        }
        JAVA_EXT_DIRS = property12;
        try {
            property13 = System.getProperty(JAVA_HOME_KEY);
        } catch (SecurityException unused13) {
            property13 = null;
        }
        JAVA_HOME = property13;
        try {
            property14 = System.getProperty(JAVA_IO_TMPDIR_KEY);
        } catch (SecurityException unused14) {
            property14 = null;
        }
        JAVA_IO_TMPDIR = property14;
        try {
            property15 = System.getProperty("java.library.path");
        } catch (SecurityException unused15) {
            property15 = null;
        }
        JAVA_LIBRARY_PATH = property15;
        try {
            property16 = System.getProperty("java.runtime.name");
        } catch (SecurityException unused16) {
            property16 = null;
        }
        JAVA_RUNTIME_NAME = property16;
        try {
            property17 = System.getProperty("java.runtime.version");
        } catch (SecurityException unused17) {
            property17 = null;
        }
        JAVA_RUNTIME_VERSION = property17;
        try {
            property18 = System.getProperty("java.specification.name");
        } catch (SecurityException unused18) {
            property18 = null;
        }
        JAVA_SPECIFICATION_NAME = property18;
        try {
            property19 = System.getProperty("java.specification.vendor");
        } catch (SecurityException unused19) {
            property19 = null;
        }
        JAVA_SPECIFICATION_VENDOR = property19;
        try {
            property20 = System.getProperty("java.specification.version");
        } catch (SecurityException unused20) {
            property20 = null;
        }
        JAVA_SPECIFICATION_VERSION = property20;
        JAVA_SPECIFICATION_VERSION_AS_ENUM = JavaVersion.get(property20);
        try {
            property21 = System.getProperty("java.util.prefs.PreferencesFactory");
        } catch (SecurityException unused21) {
            property21 = null;
        }
        JAVA_UTIL_PREFS_PREFERENCES_FACTORY = property21;
        try {
            property22 = System.getProperty("java.vendor");
        } catch (SecurityException unused22) {
            property22 = null;
        }
        JAVA_VENDOR = property22;
        try {
            property23 = System.getProperty("java.vendor.url");
        } catch (SecurityException unused23) {
            property23 = null;
        }
        JAVA_VENDOR_URL = property23;
        try {
            property24 = System.getProperty("java.version");
        } catch (SecurityException unused24) {
            property24 = null;
        }
        JAVA_VERSION = property24;
        try {
            property25 = System.getProperty("java.vm.info");
        } catch (SecurityException unused25) {
            property25 = null;
        }
        JAVA_VM_INFO = property25;
        try {
            property26 = System.getProperty("java.vm.name");
        } catch (SecurityException unused26) {
            property26 = null;
        }
        JAVA_VM_NAME = property26;
        try {
            property27 = System.getProperty("java.vm.specification.name");
        } catch (SecurityException unused27) {
            property27 = null;
        }
        JAVA_VM_SPECIFICATION_NAME = property27;
        try {
            property28 = System.getProperty("java.vm.specification.vendor");
        } catch (SecurityException unused28) {
            property28 = null;
        }
        JAVA_VM_SPECIFICATION_VENDOR = property28;
        try {
            property29 = System.getProperty("java.vm.specification.version");
        } catch (SecurityException unused29) {
            property29 = null;
        }
        JAVA_VM_SPECIFICATION_VERSION = property29;
        try {
            property30 = System.getProperty("java.vm.vendor");
        } catch (SecurityException unused30) {
            property30 = null;
        }
        JAVA_VM_VENDOR = property30;
        try {
            property31 = System.getProperty("java.vm.version");
        } catch (SecurityException unused31) {
            property31 = null;
        }
        JAVA_VM_VERSION = property31;
        try {
            property32 = System.getProperty("line.separator");
        } catch (SecurityException unused32) {
            property32 = null;
        }
        LINE_SEPARATOR = property32;
        try {
            property33 = System.getProperty("os.arch");
        } catch (SecurityException unused33) {
            property33 = null;
        }
        OS_ARCH = property33;
        try {
            property34 = System.getProperty("os.name");
        } catch (SecurityException unused34) {
            property34 = null;
        }
        OS_NAME = property34;
        try {
            property35 = System.getProperty("os.version");
        } catch (SecurityException unused35) {
            property35 = null;
        }
        OS_VERSION = property35;
        try {
            property36 = System.getProperty("path.separator");
        } catch (SecurityException unused36) {
            property36 = null;
        }
        PATH_SEPARATOR = property36;
        try {
            property37 = System.getProperty("user.country");
        } catch (SecurityException unused37) {
            property37 = null;
        }
        try {
            property38 = System.getProperty(property37 == null ? "user.region" : "user.country");
        } catch (SecurityException unused38) {
            property38 = null;
        }
        USER_COUNTRY = property38;
        try {
            property39 = System.getProperty(USER_DIR_KEY);
        } catch (SecurityException unused39) {
            property39 = null;
        }
        USER_DIR = property39;
        try {
            property40 = System.getProperty(USER_HOME_KEY);
        } catch (SecurityException unused40) {
            property40 = null;
        }
        USER_HOME = property40;
        try {
            property41 = System.getProperty("user.language");
        } catch (SecurityException unused41) {
            property41 = null;
        }
        USER_LANGUAGE = property41;
        try {
            property42 = System.getProperty("user.name");
        } catch (SecurityException unused42) {
            property42 = null;
        }
        USER_NAME = property42;
        try {
            property43 = System.getProperty("user.timezone");
        } catch (SecurityException unused43) {
        }
        USER_TIMEZONE = property43;
        String str = JAVA_SPECIFICATION_VERSION;
        IS_JAVA_1_1 = isJavaVersionMatch(str, "1.1");
        IS_JAVA_1_2 = isJavaVersionMatch(str, "1.2");
        IS_JAVA_1_3 = isJavaVersionMatch(str, "1.3");
        IS_JAVA_1_4 = isJavaVersionMatch(str, "1.4");
        IS_JAVA_1_5 = isJavaVersionMatch(str, "1.5");
        IS_JAVA_1_6 = isJavaVersionMatch(str, "1.6");
        IS_JAVA_1_7 = isJavaVersionMatch(str, "1.7");
        IS_JAVA_1_8 = isJavaVersionMatch(str, "1.8");
        IS_JAVA_1_9 = isJavaVersionMatch(str, "9");
        IS_JAVA_9 = isJavaVersionMatch(str, "9");
        IS_JAVA_10 = isJavaVersionMatch(str, "10");
        IS_JAVA_11 = isJavaVersionMatch(str, "11");
        IS_JAVA_12 = isJavaVersionMatch(str, "12");
        IS_JAVA_13 = isJavaVersionMatch(str, "13");
        String str2 = OS_NAME;
        boolean zIsOSNameMatch = isOSNameMatch(str2, "AIX");
        IS_OS_AIX = zIsOSNameMatch;
        boolean zIsOSNameMatch2 = isOSNameMatch(str2, "HP-UX");
        IS_OS_HP_UX = zIsOSNameMatch2;
        IS_OS_400 = isOSNameMatch(str2, "OS/400");
        boolean zIsOSNameMatch3 = isOSNameMatch(str2, "Irix");
        IS_OS_IRIX = zIsOSNameMatch3;
        boolean z = true;
        boolean z2 = isOSNameMatch(str2, "Linux") || isOSNameMatch(str2, "LINUX");
        IS_OS_LINUX = z2;
        IS_OS_MAC = isOSNameMatch(str2, "Mac");
        boolean zIsOSNameMatch4 = isOSNameMatch(str2, "Mac OS X");
        IS_OS_MAC_OSX = zIsOSNameMatch4;
        IS_OS_MAC_OSX_CHEETAH = getOsMatches("Mac OS X", "10.0");
        IS_OS_MAC_OSX_PUMA = getOsMatches("Mac OS X", "10.1");
        IS_OS_MAC_OSX_JAGUAR = getOsMatches("Mac OS X", "10.2");
        IS_OS_MAC_OSX_PANTHER = getOsMatches("Mac OS X", "10.3");
        IS_OS_MAC_OSX_TIGER = getOsMatches("Mac OS X", "10.4");
        IS_OS_MAC_OSX_LEOPARD = getOsMatches("Mac OS X", "10.5");
        IS_OS_MAC_OSX_SNOW_LEOPARD = getOsMatches("Mac OS X", "10.6");
        IS_OS_MAC_OSX_LION = getOsMatches("Mac OS X", "10.7");
        IS_OS_MAC_OSX_MOUNTAIN_LION = getOsMatches("Mac OS X", "10.8");
        IS_OS_MAC_OSX_MAVERICKS = getOsMatches("Mac OS X", "10.9");
        IS_OS_MAC_OSX_YOSEMITE = getOsMatches("Mac OS X", "10.10");
        IS_OS_MAC_OSX_EL_CAPITAN = getOsMatches("Mac OS X", "10.11");
        boolean zIsOSNameMatch5 = isOSNameMatch(str2, "FreeBSD");
        IS_OS_FREE_BSD = zIsOSNameMatch5;
        boolean zIsOSNameMatch6 = isOSNameMatch(str2, "OpenBSD");
        IS_OS_OPEN_BSD = zIsOSNameMatch6;
        boolean zIsOSNameMatch7 = isOSNameMatch(str2, "NetBSD");
        IS_OS_NET_BSD = zIsOSNameMatch7;
        IS_OS_OS2 = isOSNameMatch(str2, "OS/2");
        boolean zIsOSNameMatch8 = isOSNameMatch(str2, "Solaris");
        IS_OS_SOLARIS = zIsOSNameMatch8;
        boolean zIsOSNameMatch9 = isOSNameMatch(str2, "SunOS");
        IS_OS_SUN_OS = zIsOSNameMatch9;
        if (!zIsOSNameMatch && !zIsOSNameMatch2 && !zIsOSNameMatch3 && !z2 && !zIsOSNameMatch4 && !zIsOSNameMatch8 && !zIsOSNameMatch9 && !zIsOSNameMatch5 && !zIsOSNameMatch6 && !zIsOSNameMatch7) {
            z = false;
        }
        IS_OS_UNIX = z;
        IS_OS_WINDOWS = isOSNameMatch(str2, OS_NAME_WINDOWS_PREFIX);
        IS_OS_WINDOWS_2000 = isOSNameMatch(str2, "Windows 2000");
        IS_OS_WINDOWS_2003 = isOSNameMatch(str2, "Windows 2003");
        IS_OS_WINDOWS_2008 = isOSNameMatch(str2, "Windows Server 2008");
        IS_OS_WINDOWS_2012 = isOSNameMatch(str2, "Windows Server 2012");
        IS_OS_WINDOWS_95 = isOSNameMatch(str2, "Windows 95");
        IS_OS_WINDOWS_98 = isOSNameMatch(str2, "Windows 98");
        IS_OS_WINDOWS_ME = isOSNameMatch(str2, "Windows Me");
        IS_OS_WINDOWS_NT = isOSNameMatch(str2, "Windows NT");
        IS_OS_WINDOWS_XP = isOSNameMatch(str2, "Windows XP");
        IS_OS_WINDOWS_VISTA = isOSNameMatch(str2, "Windows Vista");
        IS_OS_WINDOWS_7 = isOSNameMatch(str2, "Windows 7");
        IS_OS_WINDOWS_8 = isOSNameMatch(str2, "Windows 8");
        IS_OS_WINDOWS_10 = isOSNameMatch(str2, "Windows 10");
        IS_OS_ZOS = isOSNameMatch(str2, "z/OS");
    }

    public static String getEnvironmentVariable(String str, String str2) {
        try {
            String str3 = System.getenv(str);
            return str3 == null ? str2 : str3;
        } catch (SecurityException unused) {
            return str2;
        }
    }

    public static String getHostName() {
        return System.getenv(IS_OS_WINDOWS ? "COMPUTERNAME" : "HOSTNAME");
    }

    public static File getJavaHome() {
        return new File(System.getProperty(JAVA_HOME_KEY));
    }

    public static File getJavaIoTmpDir() {
        return new File(System.getProperty(JAVA_IO_TMPDIR_KEY));
    }

    public static boolean getJavaVersionMatches(String str) {
        return isJavaVersionMatch(JAVA_SPECIFICATION_VERSION, str);
    }

    public static boolean getOsMatches(String str, String str2) {
        return isOSMatch(OS_NAME, OS_VERSION, str, str2);
    }

    public static boolean getOsMatchesName(String str) {
        return isOSNameMatch(OS_NAME, str);
    }

    public static String getSystemProperty(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static File getUserDir() {
        return new File(System.getProperty(USER_DIR_KEY));
    }

    public static File getUserHome() {
        return new File(System.getProperty(USER_HOME_KEY));
    }

    public static boolean isJavaAwtHeadless() {
        return Boolean.TRUE.toString().equals(JAVA_AWT_HEADLESS);
    }

    public static boolean isJavaVersionAtLeast(JavaVersion javaVersion) {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atLeast(javaVersion);
    }

    public static boolean isJavaVersionAtMost(JavaVersion javaVersion) {
        return JAVA_SPECIFICATION_VERSION_AS_ENUM.atMost(javaVersion);
    }

    public static boolean isJavaVersionMatch(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    public static boolean isOSMatch(String str, String str2, String str3, String str4) {
        return str != null && str2 != null && str.startsWith(str3) && isOSVersionMatch(str2, str4);
    }

    public static boolean isOSNameMatch(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.startsWith(str2);
    }

    public static boolean isOSVersionMatch(String str, String str2) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        String[] strArrSplit = str2.split("\\.");
        String[] strArrSplit2 = str.split("\\.");
        for (int i = 0; i < Math.min(strArrSplit.length, strArrSplit2.length); i++) {
            if (!strArrSplit[i].equals(strArrSplit2[i])) {
                return false;
            }
        }
        return true;
    }
}
