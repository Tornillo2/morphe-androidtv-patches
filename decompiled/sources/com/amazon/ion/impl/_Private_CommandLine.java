package com.amazon.ion.impl;

import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import com.amazon.ion.IonType;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.ion.util.JarInfo;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class _Private_CommandLine {
    public static final int argid_HELP = 3;
    public static final int argid_INVALID = -1;
    public static final int argid_VERSION = 2;
    public static String errorMessage = null;
    public static JarInfo info = null;
    public static boolean printHelp = false;
    public static boolean printVersion = false;

    public static void doPrintHelp() {
        System.out.println("ion-java -- Copyright (c) 2007-" + info.ourBuildTime.getYear() + " Amazon.com");
        System.out.println("usage: java -jar <jar> <options>");
        System.out.println("options:");
        System.out.println("version\t\tprints current version entry");
        System.out.println("help\t\tprints this helpful message");
        if (errorMessage != null) {
            System.out.println();
            System.out.println(errorMessage);
        }
    }

    public static void doPrintVersion() throws IOException {
        IonTextWriterBuilder ionTextWriterBuilderPretty = IonTextWriterBuilder.pretty();
        ionTextWriterBuilderPretty.setCharset(IonTextWriterBuilder.ASCII);
        IonWriterUser ionWriterUser = (IonWriterUser) ionTextWriterBuilderPretty.build((Appendable) System.out);
        ionWriterUser.stepIn(IonType.STRUCT);
        ionWriterUser.setFieldName("version");
        ionWriterUser.writeString(info.ourProjectVersion);
        ionWriterUser.setFieldName("build_time");
        ionWriterUser.writeTimestamp(info.ourBuildTime);
        ionWriterUser.stepOut();
        ionWriterUser.finish();
        System.out.println();
    }

    public static int getArgumentId(String str) {
        if (str.startsWith("-") && str.length() == 2) {
            char cCharAt = str.charAt(1);
            if (cCharAt == '?' || cCharAt == 'h') {
                return 3;
            }
            return cCharAt != 'v' ? -1 : 2;
        }
        if (str.startsWith("--") && str.length() > 2) {
            if (str.equals("--help")) {
                return 3;
            }
            if (str.equals("--version")) {
                return 2;
            }
        }
        return -1;
    }

    public static void invalid_arg(int i, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(errorMessage);
        sb.append("\narg[");
        sb.append(i);
        sb.append("] \"");
        errorMessage = ActivityCompat$$ExternalSyntheticOutline0.m(sb, str, "\" is unrecognized or invalid.");
        printHelp = true;
    }

    public static void main(String[] strArr) throws IOException {
        process_command_line(strArr);
        info = new JarInfo();
        if (printVersion) {
            doPrintVersion();
        }
        if (printHelp) {
            doPrintHelp();
        }
    }

    public static void process_command_line(String[] strArr) {
        if (strArr.length == 0) {
            printHelp = true;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str != null && str.length() >= 1) {
                int argumentId = getArgumentId(str);
                if (argumentId == 2) {
                    printVersion = true;
                } else if (argumentId != 3) {
                    invalid_arg(i, str);
                } else {
                    printHelp = true;
                }
            }
        }
    }
}
