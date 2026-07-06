package com.google.android.exoplayer2.util;

import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public class Logger {
    public static final int[] enabledModules;
    public int mModule;
    public String mTag;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum Module {
        Unknown,
        AudioVideoCommon,
        Audio,
        Video,
        AudioVideo,
        Text,
        Source,
        Manifest,
        Player,
        All
    }

    static {
        int[] iArr = new int[Module.All.ordinal()];
        enabledModules = iArr;
        Arrays.fill(iArr, 4);
    }

    public Logger(Module module, String str) {
        this.mTag = "UNKNOWN";
        this.mModule = Module.Unknown.ordinal();
        if (str == null) {
            throw new IllegalArgumentException("Null Tag");
        }
        this.mTag = str;
        this.mModule = module.ordinal();
    }

    public static void setLogLevel(Module module, int i) {
        if (module.compareTo(Module.All) == 0) {
            Arrays.fill(enabledModules, i);
        } else {
            enabledModules[module.ordinal()] = i;
        }
        Module module2 = Module.Audio;
        if (module.compareTo(module2) >= 0 && module.compareTo(Module.AudioVideo) <= 0) {
            enabledModules[Module.AudioVideoCommon.ordinal()] = i;
        }
        if (module.compareTo(Module.AudioVideo) == 0) {
            int[] iArr = enabledModules;
            iArr[module2.ordinal()] = i;
            iArr[Module.Video.ordinal()] = i;
        }
    }

    public boolean allowDebug() {
        return enabledModules[this.mModule] <= 3;
    }

    public boolean allowVerbose() {
        return enabledModules[this.mModule] == 2;
    }

    public void d(String str) {
        if (enabledModules[this.mModule] <= 3) {
            android.util.Log.d(this.mTag, str);
        }
    }

    public void e(String str) {
        android.util.Log.e(this.mTag, str);
    }

    public void i(String str) {
        android.util.Log.i(this.mTag, str);
    }

    public void setModule(Module module) {
        this.mModule = module.ordinal();
    }

    public void setTAG(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Null Tag");
        }
        this.mTag = str;
    }

    public void v(String str) {
        if (enabledModules[this.mModule] == 2) {
            android.util.Log.v(this.mTag, str);
        }
    }

    public void w(String str) {
        android.util.Log.w(this.mTag, str);
    }

    public void e(String str, Throwable th) {
        android.util.Log.e(this.mTag, str, th);
    }
}
