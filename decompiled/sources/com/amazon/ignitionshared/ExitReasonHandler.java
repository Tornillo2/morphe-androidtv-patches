package com.amazon.ignitionshared;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.os.Build;
import android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0;
import android.system.OsConstants;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat$$ExternalSyntheticOutline0;
import androidx.work.impl.utils.ForceStopRunnable$$ExternalSyntheticApiModelOutline0;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import com.amazon.livingroom.mediapipelinebackend.ResultHolder;
import com.amazon.minerva.client.thirdparty.transport.UploadResult;
import com.amazon.reporting.Log;
import java.io.FileNotFoundException;
import java.util.List;
import javax.inject.Inject;
import kotlin.Pair;
import kotlin.collections.EmptyList;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class ExitReasonHandler {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String EXIT_REASON_FILE_NAME = "exit-reason";

    @NotNull
    public static final String LOG_TAG;
    public static final int MIN_API_VERSION = 30;

    @NotNull
    public final ActivityManager activityManager;

    @NotNull
    public final Context context;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AvLastExitReason {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ AvLastExitReason[] $VALUES;
        public final int code;
        public static final AvLastExitReason AV_EXIT_UNKNOWN = new AvLastExitReason("AV_EXIT_UNKNOWN", 0, 0);
        public static final AvLastExitReason AV_EXIT_USER = new AvLastExitReason("AV_EXIT_USER", 1, 1);
        public static final AvLastExitReason AV_EXIT_OUT_OF_MEMORY = new AvLastExitReason("AV_EXIT_OUT_OF_MEMORY", 2, 2);
        public static final AvLastExitReason AV_EXIT_RUNTIME_ERROR = new AvLastExitReason("AV_EXIT_RUNTIME_ERROR", 3, 3);
        public static final AvLastExitReason AV_EXIT_SYSTEM_OTHER = new AvLastExitReason("AV_EXIT_SYSTEM_OTHER", 4, 4);
        public static final AvLastExitReason AV_EXIT_EXCESSIVE_WAIT = new AvLastExitReason("AV_EXIT_EXCESSIVE_WAIT", 5, 5);

        public static final /* synthetic */ AvLastExitReason[] $values() {
            return new AvLastExitReason[]{AV_EXIT_UNKNOWN, AV_EXIT_USER, AV_EXIT_OUT_OF_MEMORY, AV_EXIT_RUNTIME_ERROR, AV_EXIT_SYSTEM_OTHER, AV_EXIT_EXCESSIVE_WAIT};
        }

        static {
            AvLastExitReason[] avLastExitReasonArr$values = $values();
            $VALUES = avLastExitReasonArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(avLastExitReasonArr$values);
        }

        public AvLastExitReason(String str, int i, int i2) {
            this.code = i2;
        }

        @NotNull
        public static EnumEntries<AvLastExitReason> getEntries() {
            return $ENTRIES;
        }

        public static AvLastExitReason valueOf(String str) {
            return (AvLastExitReason) Enum.valueOf(AvLastExitReason.class, str);
        }

        public static AvLastExitReason[] values() {
            return (AvLastExitReason[]) $VALUES.clone();
        }

        public final int getCode() {
            return this.code;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class StatusCode {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ StatusCode[] $VALUES;
        public final int code;
        public static final StatusCode SUCCESS = new StatusCode(UploadResult.SUCCESS, 0, 0);
        public static final StatusCode FETCH_EXCEPTION = new StatusCode("FETCH_EXCEPTION", 1, 1);
        public static final StatusCode UNSUPPORTED_API = new StatusCode("UNSUPPORTED_API", 2, 2);

        public static final /* synthetic */ StatusCode[] $values() {
            return new StatusCode[]{SUCCESS, FETCH_EXCEPTION, UNSUPPORTED_API};
        }

        static {
            StatusCode[] statusCodeArr$values = $values();
            $VALUES = statusCodeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(statusCodeArr$values);
        }

        public StatusCode(String str, int i, int i2) {
            this.code = i2;
        }

        @NotNull
        public static EnumEntries<StatusCode> getEntries() {
            return $ENTRIES;
        }

        public static StatusCode valueOf(String str) {
            return (StatusCode) Enum.valueOf(StatusCode.class, str);
        }

        public static StatusCode[] values() {
            return (StatusCode[]) $VALUES.clone();
        }

        public final int getCode() {
            return this.code;
        }
    }

    static {
        String simpleName = ((ClassReference) Reflection.factory.getOrCreateKotlinClass(ExitReasonHandler.class)).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        LOG_TAG = simpleName;
    }

    @Inject
    public ExitReasonHandler(@ApplicationContext @NotNull Context context, @NotNull ActivityManager activityManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityManager, "activityManager");
        this.context = context;
        this.activityManager = activityManager;
    }

    @RequiresApi(api = 30)
    public final AvLastExitReason convertAndroidReason(int i) {
        switch (i) {
            case 0:
            case 1:
                return AvLastExitReason.AV_EXIT_UNKNOWN;
            case 2:
            case 8:
            case 12:
            case 13:
                return AvLastExitReason.AV_EXIT_SYSTEM_OTHER;
            case 3:
            case 9:
                return AvLastExitReason.AV_EXIT_OUT_OF_MEMORY;
            case 4:
            case 5:
            case 6:
            case 7:
                return AvLastExitReason.AV_EXIT_RUNTIME_ERROR;
            case 10:
            case 11:
                return AvLastExitReason.AV_EXIT_USER;
            default:
                return AvLastExitReason.AV_EXIT_UNKNOWN;
        }
    }

    @RequiresApi(api = 30)
    public final Pair<List<ApplicationExitInfo>, StatusCode> fetchLastExitReason() {
        try {
            return new Pair<>(this.activityManager.getHistoricalProcessExitReasons(null, 0, 1), StatusCode.SUCCESS);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error retrieving exit reasons", e);
            return new Pair<>(EmptyList.INSTANCE, StatusCode.FETCH_EXCEPTION);
        }
    }

    @RequiresApi(api = 30)
    public final String getExitReasonName(int i) {
        switch (i) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "EXIT_SELF";
            case 2:
                return "SIGNALED";
            case 3:
                return "LOW_MEMORY";
            case 4:
                return "CRASH";
            case 5:
                return "CRASH_NATIVE";
            case 6:
                return "ANR";
            case 7:
                return "INITIALIZATION_FAILURE";
            case 8:
                return "PERMISSION_CHANGE";
            case 9:
                return "EXCESSIVE_RESOURCE_USAGE";
            case 10:
                return "USER_REQUESTED";
            case 11:
                return "USER_STOPPED";
            case 12:
                return "DEPENDENCY_DIED";
            case 13:
                return "OTHER";
            default:
                return MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("UNKNOWN_REASON_", i);
        }
    }

    @RequiresApi(api = 30)
    public final String getImportanceName(int i) {
        return i != 100 ? i != 125 ? i != 200 ? i != 230 ? i != 300 ? i != 325 ? i != 350 ? i != 400 ? i != 1000 ? MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("UNKNOWN_IMPORTANCE_", i) : "GONE" : "BACKGROUND/CACHED" : "CANT_SAVE_STATE" : "TOP_SLEEPING" : "SERVICE" : "PERCEPTIBLE" : "VISIBLE" : "FOREGROUND_SERVICE" : "FOREGROUND";
    }

    @CalledFromNative
    @NotNull
    public final ResultHolder<ExitReason> getLastExitReason() {
        if (hasExcessiveBackgroundWaitTerminationOccurred()) {
            Log.i(LOG_TAG, "Exit reason: EXCESSIVE_WAIT (captured internally)");
            return ResultHolder.fromResult(new ExitReason(AvLastExitReason.AV_EXIT_EXCESSIVE_WAIT.code, "Exit reason: EXCESSIVE_WAIT (captured internally)"));
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 30) {
            String strM = MediaBrowserCompat$MediaBrowserImplBase$$ExternalSyntheticOutline0.m("Minimum API Version: 30, Actual API Version: ", i);
            Log.w(LOG_TAG, "Device does not support exit reasons (" + strM + ")");
            return ResultHolder.fromErrorCode(StatusCode.UNSUPPORTED_API.code);
        }
        Pair<List<ApplicationExitInfo>, StatusCode> pairFetchLastExitReason = fetchLastExitReason();
        List<ApplicationExitInfo> list = pairFetchLastExitReason.first;
        StatusCode statusCode = pairFetchLastExitReason.second;
        if (statusCode != StatusCode.SUCCESS) {
            return ResultHolder.fromErrorCode(statusCode.code);
        }
        if (list.isEmpty()) {
            Log.i(LOG_TAG, "No historical exit reasons found");
            return ResultHolder.fromResult(new ExitReason(AvLastExitReason.AV_EXIT_UNKNOWN.code, "No historical exit reasons found"));
        }
        ApplicationExitInfo applicationExitInfoM = ForceStopRunnable$$ExternalSyntheticApiModelOutline0.m(list.get(0));
        int reason = applicationExitInfoM.getReason();
        if (reason == 2 && applicationExitInfoM.getStatus() == OsConstants.SIGKILL && !ActivityManager.isLowMemoryKillReportSupported()) {
            reason = 3;
        }
        String exitReasonName = getExitReasonName(reason);
        String importanceName = getImportanceName(applicationExitInfoM.getImportance());
        int importance = applicationExitInfoM.getImportance();
        double pss = applicationExitInfoM.getPss() / 1024.0d;
        double rss = applicationExitInfoM.getRss() / 1024.0d;
        long timestamp = applicationExitInfoM.getTimestamp();
        int pid = applicationExitInfoM.getPid();
        String processName = applicationExitInfoM.getProcessName();
        StringBuilder sb = new StringBuilder("Exit reason: ");
        sb.append(exitReasonName);
        sb.append(" (");
        sb.append(reason);
        sb.append("), Importance: ");
        sb.append(importanceName);
        sb.append(" (");
        sb.append(importance);
        sb.append("), PSS: ");
        sb.append(pss);
        sb.append("MB, RSS: ");
        sb.append(rss);
        sb.append("MB, Timestamp: ");
        sb.append(timestamp);
        sb.append(", PID: ");
        sb.append(pid);
        String strM2 = ActivityCompat$$ExternalSyntheticOutline0.m(sb, ", Process: ", processName);
        Log.i(LOG_TAG, "[ExitReasonHandler] " + strM2);
        return ResultHolder.fromResult(new ExitReason(convertAndroidReason(reason).code, strM2));
    }

    public final boolean hasExcessiveBackgroundWaitTerminationOccurred() {
        try {
            this.context.openFileInput(EXIT_REASON_FILE_NAME).close();
            this.context.deleteFile(EXIT_REASON_FILE_NAME);
            Log.i(LOG_TAG, "Exit reason file found");
            return true;
        } catch (FileNotFoundException unused) {
            Log.i(LOG_TAG, "Exit reason file has not been written to");
            return false;
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to read from file: exit-reason", e);
            return false;
        }
    }

    public final void saveExcessiveBackgroundWaitTermination() {
        try {
            this.context.openFileOutput(EXIT_REASON_FILE_NAME, 0).close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Failed to write to file: exit-reason", e);
        }
    }
}
