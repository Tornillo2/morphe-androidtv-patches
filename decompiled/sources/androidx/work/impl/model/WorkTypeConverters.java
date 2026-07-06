package androidx.work.impl.model;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import androidx.room.TypeConverter;
import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class WorkTypeConverters {

    /* JADX INFO: renamed from: androidx.work.impl.model.WorkTypeConverters$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$BackoffPolicy;
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$NetworkType;
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$OutOfQuotaPolicy;
        public static final /* synthetic */ int[] $SwitchMap$androidx$work$WorkInfo$State;

        static {
            int[] iArr = new int[OutOfQuotaPolicy.values().length];
            $SwitchMap$androidx$work$OutOfQuotaPolicy = iArr;
            try {
                iArr[OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$work$OutOfQuotaPolicy[OutOfQuotaPolicy.DROP_WORK_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[NetworkType.values().length];
            $SwitchMap$androidx$work$NetworkType = iArr2;
            try {
                iArr2[NetworkType.NOT_REQUIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.UNMETERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.NOT_ROAMING.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$androidx$work$NetworkType[NetworkType.METERED.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[BackoffPolicy.values().length];
            $SwitchMap$androidx$work$BackoffPolicy = iArr3;
            try {
                iArr3[BackoffPolicy.EXPONENTIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$androidx$work$BackoffPolicy[BackoffPolicy.LINEAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr4 = new int[WorkInfo.State.values().length];
            $SwitchMap$androidx$work$WorkInfo$State = iArr4;
            try {
                iArr4[WorkInfo.State.ENQUEUED.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.SUCCEEDED.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.BLOCKED.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$androidx$work$WorkInfo$State[WorkInfo.State.CANCELLED.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface BackoffPolicyIds {
        public static final int EXPONENTIAL = 0;
        public static final int LINEAR = 1;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface NetworkTypeIds {
        public static final int CONNECTED = 1;
        public static final int METERED = 4;
        public static final int NOT_REQUIRED = 0;
        public static final int NOT_ROAMING = 3;
        public static final int TEMPORARILY_UNMETERED = 5;
        public static final int UNMETERED = 2;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface OutOfPolicyIds {
        public static final int DROP_WORK_REQUEST = 1;
        public static final int RUN_AS_NON_EXPEDITED_WORK_REQUEST = 0;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface StateIds {
        public static final int BLOCKED = 4;
        public static final int CANCELLED = 5;
        public static final String COMPLETED_STATES = "(2, 3, 5)";
        public static final int ENQUEUED = 0;
        public static final int FAILED = 3;
        public static final int RUNNING = 1;
        public static final int SUCCEEDED = 2;
    }

    @TypeConverter
    public static int backoffPolicyToInt(BackoffPolicy backoffPolicy) {
        int i = AnonymousClass1.$SwitchMap$androidx$work$BackoffPolicy[backoffPolicy.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new IllegalArgumentException("Could not convert " + backoffPolicy + " to int");
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.room.TypeConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.work.ContentUriTriggers byteArrayToContentUriTriggers(byte[] r6) throws java.lang.Throwable {
        /*
            androidx.work.ContentUriTriggers r0 = new androidx.work.ContentUriTriggers
            r0.<init>()
            if (r6 != 0) goto L8
            goto L4e
        L8:
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r6)
            r6 = 0
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L44
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L44
            int r6 = r2.readInt()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
        L17:
            if (r6 <= 0) goto L2f
            java.lang.String r3 = r2.readUTF()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            boolean r4 = r2.readBoolean()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            r0.add(r3, r4)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2d
            int r6 = r6 + (-1)
            goto L17
        L2b:
            r6 = move-exception
            goto L4f
        L2d:
            r6 = move-exception
            goto L48
        L2f:
            r2.close()     // Catch: java.io.IOException -> L33
            goto L37
        L33:
            r6 = move-exception
            r6.printStackTrace()
        L37:
            r1.close()     // Catch: java.io.IOException -> L3b
            goto L4e
        L3b:
            r6 = move-exception
            r6.printStackTrace()
            goto L4e
        L40:
            r0 = move-exception
            r2 = r6
            r6 = r0
            goto L4f
        L44:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
        L48:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L2b
            if (r2 == 0) goto L37
            goto L2f
        L4e:
            return r0
        L4f:
            if (r2 == 0) goto L59
            r2.close()     // Catch: java.io.IOException -> L55
            goto L59
        L55:
            r0 = move-exception
            r0.printStackTrace()
        L59:
            r1.close()     // Catch: java.io.IOException -> L5d
            goto L61
        L5d:
            r0 = move-exception
            r0.printStackTrace()
        L61:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.byteArrayToContentUriTriggers(byte[]):androidx.work.ContentUriTriggers");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:5|46|43|6|(5:55|7|(2:10|8)|57|16)|45|51|20|31|32) */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
    
        r4 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0051, code lost:
    
        r4.printStackTrace();
     */
    @androidx.room.TypeConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] contentUriTriggersToByteArray(androidx.work.ContentUriTriggers r4) throws java.lang.Throwable {
        /*
            java.util.Set<androidx.work.ContentUriTriggers$Trigger> r0 = r4.mTriggers
            int r0 = r0.size()
            r1 = 0
            if (r0 != 0) goto La
            return r1
        La:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            java.util.Set<androidx.work.ContentUriTriggers$Trigger> r1 = r4.mTriggers     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            int r1 = r1.size()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            r2.writeInt(r1)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            java.util.Set<androidx.work.ContentUriTriggers$Trigger> r4 = r4.mTriggers     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
        L23:
            boolean r1 = r4.hasNext()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            if (r1 == 0) goto L44
            java.lang.Object r1 = r4.next()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            androidx.work.ContentUriTriggers$Trigger r1 = (androidx.work.ContentUriTriggers.Trigger) r1     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            android.net.Uri r3 = r1.mUri     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            r2.writeUTF(r3)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            boolean r1 = r1.mTriggerForDescendants     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            r2.writeBoolean(r1)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L41
            goto L23
        L3e:
            r4 = move-exception
            r1 = r2
            goto L66
        L41:
            r4 = move-exception
            r1 = r2
            goto L58
        L44:
            r2.close()     // Catch: java.io.IOException -> L48
            goto L4c
        L48:
            r4 = move-exception
            r4.printStackTrace()
        L4c:
            r0.close()     // Catch: java.io.IOException -> L50
            goto L61
        L50:
            r4 = move-exception
            r4.printStackTrace()
            goto L61
        L55:
            r4 = move-exception
            goto L66
        L57:
            r4 = move-exception
        L58:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L4c
            r1.close()     // Catch: java.io.IOException -> L48
            goto L4c
        L61:
            byte[] r4 = r0.toByteArray()
            return r4
        L66:
            if (r1 == 0) goto L70
            r1.close()     // Catch: java.io.IOException -> L6c
            goto L70
        L6c:
            r1 = move-exception
            r1.printStackTrace()
        L70:
            r0.close()     // Catch: java.io.IOException -> L74
            goto L78
        L74:
            r0 = move-exception
            r0.printStackTrace()
        L78:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.WorkTypeConverters.contentUriTriggersToByteArray(androidx.work.ContentUriTriggers):byte[]");
    }

    @TypeConverter
    public static BackoffPolicy intToBackoffPolicy(int value) {
        if (value == 0) {
            return BackoffPolicy.EXPONENTIAL;
        }
        if (value == 1) {
            return BackoffPolicy.LINEAR;
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Could not convert ", value, " to BackoffPolicy"));
    }

    @TypeConverter
    public static NetworkType intToNetworkType(int value) {
        if (value == 0) {
            return NetworkType.NOT_REQUIRED;
        }
        if (value == 1) {
            return NetworkType.CONNECTED;
        }
        if (value == 2) {
            return NetworkType.UNMETERED;
        }
        if (value == 3) {
            return NetworkType.NOT_ROAMING;
        }
        if (value == 4) {
            return NetworkType.METERED;
        }
        if (Build.VERSION.SDK_INT < 30 || value != 5) {
            throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Could not convert ", value, " to NetworkType"));
        }
        return NetworkType.TEMPORARILY_UNMETERED;
    }

    @NonNull
    @TypeConverter
    public static OutOfQuotaPolicy intToOutOfQuotaPolicy(int value) {
        if (value == 0) {
            return OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
        }
        if (value == 1) {
            return OutOfQuotaPolicy.DROP_WORK_REQUEST;
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Could not convert ", value, " to OutOfQuotaPolicy"));
    }

    @TypeConverter
    public static WorkInfo.State intToState(int value) {
        if (value == 0) {
            return WorkInfo.State.ENQUEUED;
        }
        if (value == 1) {
            return WorkInfo.State.RUNNING;
        }
        if (value == 2) {
            return WorkInfo.State.SUCCEEDED;
        }
        if (value == 3) {
            return WorkInfo.State.FAILED;
        }
        if (value == 4) {
            return WorkInfo.State.BLOCKED;
        }
        if (value == 5) {
            return WorkInfo.State.CANCELLED;
        }
        throw new IllegalArgumentException(ObjectListKt$$ExternalSyntheticOutline1.m("Could not convert ", value, " to State"));
    }

    @TypeConverter
    public static int networkTypeToInt(NetworkType networkType) {
        int i = AnonymousClass1.$SwitchMap$androidx$work$NetworkType[networkType.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        if (i == 5) {
            return 4;
        }
        if (Build.VERSION.SDK_INT >= 30 && networkType == NetworkType.TEMPORARILY_UNMETERED) {
            return 5;
        }
        throw new IllegalArgumentException("Could not convert " + networkType + " to int");
    }

    @TypeConverter
    public static int outOfQuotaPolicyToInt(@NonNull OutOfQuotaPolicy policy) {
        int i = AnonymousClass1.$SwitchMap$androidx$work$OutOfQuotaPolicy[policy.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new IllegalArgumentException("Could not convert " + policy + " to int");
    }

    @TypeConverter
    public static int stateToInt(WorkInfo.State state) {
        switch (AnonymousClass1.$SwitchMap$androidx$work$WorkInfo$State[state.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            default:
                throw new IllegalArgumentException("Could not convert " + state + " to int");
        }
    }
}
