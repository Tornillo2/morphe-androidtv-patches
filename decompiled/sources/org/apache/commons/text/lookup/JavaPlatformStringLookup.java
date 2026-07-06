package org.apache.commons.text.lookup;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline0;
import java.io.PrintStream;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public final class JavaPlatformStringLookup extends AbstractStringLookup {
    public static final JavaPlatformStringLookup INSTANCE = new JavaPlatformStringLookup();
    public static final String KEY_HARDWARE = "hardware";
    public static final String KEY_LOCALE = "locale";
    public static final String KEY_OS = "os";
    public static final String KEY_RUNTIME = "runtime";
    public static final String KEY_VERSION = "version";
    public static final String KEY_VM = "vm";

    public static void main(String[] strArr) {
        System.out.println(JavaPlatformStringLookup.class);
        PrintStream printStream = System.out;
        JavaPlatformStringLookup javaPlatformStringLookup = INSTANCE;
        printStream.printf("%s = %s%n", "version", javaPlatformStringLookup.lookup("version"));
        System.out.printf("%s = %s%n", KEY_RUNTIME, javaPlatformStringLookup.lookup(KEY_RUNTIME));
        System.out.printf("%s = %s%n", KEY_VM, javaPlatformStringLookup.lookup(KEY_VM));
        System.out.printf("%s = %s%n", KEY_OS, javaPlatformStringLookup.lookup(KEY_OS));
        System.out.printf("%s = %s%n", "hardware", javaPlatformStringLookup.lookup("hardware"));
        System.out.printf("%s = %s%n", "locale", javaPlatformStringLookup.lookup("locale"));
    }

    public String getHardware() {
        return "processors: " + Runtime.getRuntime().availableProcessors() + ", architecture: " + SystemPropertyStringLookup.INSTANCE.lookup("os.arch") + getSystemProperty("-", "sun.arch.data.model") + getSystemProperty(", instruction sets: ", "sun.cpu.isalist");
    }

    public String getLocale() {
        return "default locale: " + Locale.getDefault() + ", platform encoding: " + SystemPropertyStringLookup.INSTANCE.lookup("file.encoding");
    }

    public String getOperatingSystem() {
        StringBuilder sb = new StringBuilder();
        SystemPropertyStringLookup systemPropertyStringLookup = SystemPropertyStringLookup.INSTANCE;
        sb.append(systemPropertyStringLookup.lookup("os.name"));
        sb.append(StringUtils.SPACE);
        sb.append(systemPropertyStringLookup.lookup("os.version"));
        sb.append(getSystemProperty(StringUtils.SPACE, "sun.os.patch.level"));
        sb.append(", architecture: ");
        sb.append(systemPropertyStringLookup.lookup("os.arch"));
        sb.append(getSystemProperty("-", "sun.arch.data.model"));
        return sb.toString();
    }

    public String getRuntime() {
        StringBuilder sb = new StringBuilder();
        SystemPropertyStringLookup systemPropertyStringLookup = SystemPropertyStringLookup.INSTANCE;
        sb.append(systemPropertyStringLookup.lookup("java.runtime.name"));
        sb.append(" (build ");
        sb.append(systemPropertyStringLookup.lookup("java.runtime.version"));
        sb.append(") from ");
        sb.append(systemPropertyStringLookup.lookup("java.vendor"));
        return sb.toString();
    }

    public final String getSystemProperty(String str) {
        return SystemPropertyStringLookup.INSTANCE.lookup(str);
    }

    public String getVirtualMachine() {
        StringBuilder sb = new StringBuilder();
        SystemPropertyStringLookup systemPropertyStringLookup = SystemPropertyStringLookup.INSTANCE;
        sb.append(systemPropertyStringLookup.lookup("java.vm.name"));
        sb.append(" (build ");
        sb.append(systemPropertyStringLookup.lookup("java.vm.version"));
        sb.append(", ");
        sb.append(systemPropertyStringLookup.lookup("java.vm.info"));
        sb.append(")");
        return sb.toString();
    }

    @Override // org.apache.commons.text.lookup.StringLookup
    public String lookup(String str) {
        if (str == null) {
            return null;
        }
        switch (str) {
            case "locale":
                return getLocale();
            case "os":
                return getOperatingSystem();
            case "vm":
                return getVirtualMachine();
            case "hardware":
                return getHardware();
            case "version":
                return "Java version " + SystemPropertyStringLookup.INSTANCE.lookup("java.version");
            case "runtime":
                return getRuntime();
            default:
                throw new IllegalArgumentException(str);
        }
    }

    public final String getSystemProperty(String str, String str2) {
        String strLookup = SystemPropertyStringLookup.INSTANCE.lookup(str2);
        return StringUtils.isEmpty(strLookup) ? "" : AbstractResolvableFuture$$ExternalSyntheticOutline0.m(str, strLookup);
    }
}
