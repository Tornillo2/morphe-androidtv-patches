package org.apache.commons.lang3.exception;

import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes4.dex */
public class ExceptionUtils {
    public static final String[] CAUSE_METHOD_NAMES = {"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};
    public static final String WRAPPED_MARKER = " [wrapped] ";

    @Deprecated
    public static Throwable getCause(Throwable th) {
        return getCause(th, null);
    }

    public static Throwable getCauseUsingMethodName(Throwable th, String str) {
        Method method;
        try {
            method = th.getClass().getMethod(str, null);
        } catch (NoSuchMethodException | SecurityException unused) {
            method = null;
        }
        if (method != null && Throwable.class.isAssignableFrom(method.getReturnType())) {
            try {
                return (Throwable) method.invoke(th, null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused2) {
            }
        }
        return null;
    }

    @Deprecated
    public static String[] getDefaultCauseMethodNames() {
        return (String[]) ArrayUtils.clone(CAUSE_METHOD_NAMES);
    }

    public static String getMessage(Throwable th) {
        if (th == null) {
            return "";
        }
        String shortClassName = ClassUtils.getShortClassName(th, null);
        String message = th.getMessage();
        StringBuilder sbM = MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection$$ExternalSyntheticOutline0.m(shortClassName, ": ");
        sbM.append(message != null ? message : "");
        return sbM.toString();
    }

    public static Throwable getRootCause(Throwable th) {
        ArrayList arrayList = (ArrayList) getThrowableList(th);
        if (arrayList.isEmpty()) {
            return null;
        }
        return (Throwable) arrayList.get(arrayList.size() - 1);
    }

    public static String getRootCauseMessage(Throwable th) {
        Throwable rootCause = getRootCause(th);
        if (rootCause != null) {
            th = rootCause;
        }
        return getMessage(th);
    }

    public static String[] getRootCauseStackTrace(Throwable th) {
        List<String> stackFrameList;
        if (th == null) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        Throwable[] throwables = getThrowables(th);
        int length = throwables.length;
        ArrayList arrayList = new ArrayList();
        int i = length - 1;
        List<String> stackFrameList2 = getStackFrameList(throwables[i]);
        while (true) {
            int i2 = length - 1;
            if (i2 < 0) {
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            if (i2 != 0) {
                stackFrameList = getStackFrameList(throwables[length - 2]);
                removeCommonFrames(stackFrameList2, stackFrameList);
            } else {
                stackFrameList = stackFrameList2;
            }
            if (i2 == i) {
                arrayList.add(throwables[i2].toString());
            } else {
                arrayList.add(WRAPPED_MARKER + throwables[i2].toString());
            }
            arrayList.addAll(stackFrameList2);
            stackFrameList2 = stackFrameList;
            length = i2;
        }
    }

    public static List<String> getStackFrameList(Throwable th) {
        StringTokenizer stringTokenizer = new StringTokenizer(getStackTrace(th), System.lineSeparator());
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf("at");
            if (iIndexOf != -1 && strNextToken.substring(0, iIndexOf).trim().isEmpty()) {
                arrayList.add(strNextToken);
                z = true;
            } else if (z) {
                break;
            }
        }
        return arrayList;
    }

    public static String[] getStackFrames(Throwable th) {
        return th == null ? ArrayUtils.EMPTY_STRING_ARRAY : getStackFrames(getStackTrace(th));
    }

    public static String getStackTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        return stringWriter.getBuffer().toString();
    }

    public static int getThrowableCount(Throwable th) {
        return ((ArrayList) getThrowableList(th)).size();
    }

    public static List<Throwable> getThrowableList(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = th.getCause();
        }
        return arrayList;
    }

    public static Throwable[] getThrowables(Throwable th) {
        ArrayList arrayList = (ArrayList) getThrowableList(th);
        return (Throwable[]) arrayList.toArray(new Throwable[arrayList.size()]);
    }

    public static boolean hasCause(Throwable th, Class<? extends Throwable> cls) {
        if (th instanceof UndeclaredThrowableException) {
            th = th.getCause();
        }
        return cls.isInstance(th);
    }

    public static int indexOf(Throwable th, Class<?> cls, int i, boolean z) {
        if (th != null && cls != null) {
            if (i < 0) {
                i = 0;
            }
            Throwable[] throwables = getThrowables(th);
            if (i >= throwables.length) {
                return -1;
            }
            if (z) {
                while (i < throwables.length) {
                    if (cls.isAssignableFrom(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            } else {
                while (i < throwables.length) {
                    if (cls.equals(throwables[i].getClass())) {
                        return i;
                    }
                    i++;
                }
            }
        }
        return -1;
    }

    public static int indexOfThrowable(Throwable th, Class<?> cls) {
        return indexOf(th, cls, 0, false);
    }

    public static int indexOfType(Throwable th, Class<?> cls) {
        return indexOf(th, cls, 0, true);
    }

    public static void printRootCauseStackTrace(Throwable th) {
        printRootCauseStackTrace(th, System.err);
    }

    public static void removeCommonFrames(List<String> list, List<String> list2) {
        if (list == null || list2 == null) {
            throw new IllegalArgumentException("The List must not be null");
        }
        int size = list.size() - 1;
        for (int size2 = list2.size() - 1; size >= 0 && size2 >= 0; size2--) {
            if (list.get(size).equals(list2.get(size2))) {
                list.remove(size);
            }
            size--;
        }
    }

    public static <R> R wrapAndThrow(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new UndeclaredThrowableException(th);
    }

    @Deprecated
    public static Throwable getCause(Throwable th, String[] strArr) {
        Throwable causeUsingMethodName;
        if (th == null) {
            return null;
        }
        if (strArr == null) {
            Throwable cause = th.getCause();
            if (cause != null) {
                return cause;
            }
            strArr = CAUSE_METHOD_NAMES;
        }
        for (String str : strArr) {
            if (str != null && (causeUsingMethodName = getCauseUsingMethodName(th, str)) != null) {
                return causeUsingMethodName;
            }
        }
        return null;
    }

    public static int indexOfThrowable(Throwable th, Class<?> cls, int i) {
        return indexOf(th, cls, i, false);
    }

    public static int indexOfType(Throwable th, Class<?> cls, int i) {
        return indexOf(th, cls, i, true);
    }

    public static void printRootCauseStackTrace(Throwable th, PrintStream printStream) {
        if (th == null) {
            return;
        }
        Validate.isTrue(printStream != null, "The PrintStream must not be null", new Object[0]);
        for (String str : getRootCauseStackTrace(th)) {
            printStream.println(str);
        }
        printStream.flush();
    }

    public static String[] getStackFrames(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, System.lineSeparator());
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void printRootCauseStackTrace(Throwable th, PrintWriter printWriter) {
        if (th == null) {
            return;
        }
        Validate.isTrue(printWriter != null, "The PrintWriter must not be null", new Object[0]);
        for (String str : getRootCauseStackTrace(th)) {
            printWriter.println(str);
        }
        printWriter.flush();
    }

    public static <R> R rethrow(Throwable th) throws Throwable {
        throw th;
    }

    public static <R, T extends Throwable> R typeErasure(Throwable th) throws Throwable {
        throw th;
    }
}
