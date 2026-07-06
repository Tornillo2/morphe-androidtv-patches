package com.google.android.gms.internal.ads;

import androidx.concurrent.futures.AbstractResolvableFuture$$ExternalSyntheticOutline1;
import javax.annotation.CheckForNull;
import org.apache.commons.lang3.SystemUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public enum zzfpn {
    JAVA_VERSION("java.version"),
    JAVA_VENDOR("java.vendor"),
    JAVA_VENDOR_URL("java.vendor.url"),
    JAVA_HOME(SystemUtils.JAVA_HOME_KEY),
    JAVA_VM_SPECIFICATION_VERSION("java.vm.specification.version"),
    JAVA_VM_SPECIFICATION_VENDOR("java.vm.specification.vendor"),
    JAVA_VM_SPECIFICATION_NAME("java.vm.specification.name"),
    JAVA_VM_VERSION("java.vm.version"),
    JAVA_VM_VENDOR("java.vm.vendor"),
    JAVA_VM_NAME("java.vm.name"),
    JAVA_SPECIFICATION_VERSION("java.specification.version"),
    JAVA_SPECIFICATION_VENDOR("java.specification.vendor"),
    JAVA_SPECIFICATION_NAME("java.specification.name"),
    JAVA_CLASS_VERSION("java.class.version"),
    JAVA_CLASS_PATH("java.class.path"),
    JAVA_LIBRARY_PATH("java.library.path"),
    JAVA_IO_TMPDIR(SystemUtils.JAVA_IO_TMPDIR_KEY),
    JAVA_COMPILER("java.compiler"),
    JAVA_EXT_DIRS("java.ext.dirs"),
    OS_NAME("os.name"),
    OS_ARCH("os.arch"),
    OS_VERSION("os.version"),
    FILE_SEPARATOR("file.separator"),
    PATH_SEPARATOR("path.separator"),
    LINE_SEPARATOR("line.separator"),
    USER_NAME("user.name"),
    USER_HOME(SystemUtils.USER_HOME_KEY),
    USER_DIR(SystemUtils.USER_DIR_KEY);

    public final String zzD;

    zzfpn(String str) {
        this.zzD = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        String str = this.zzD;
        return AbstractResolvableFuture$$ExternalSyntheticOutline1.m(str, "=", System.getProperty(str));
    }

    @CheckForNull
    public final String zza() {
        return System.getProperty(this.zzD);
    }
}
