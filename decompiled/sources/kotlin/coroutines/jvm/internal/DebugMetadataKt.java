package kotlin.coroutines.jvm.internal;

import androidx.core.app.NotificationCompatJellybean;
import java.lang.reflect.Field;
import java.util.ArrayList;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SourceDebugExtension({"SMAP\nDebugMetadata.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,134:1\n37#2:135\n36#2,3:136\n*S KotlinDebug\n*F\n+ 1 DebugMetadata.kt\nkotlin/coroutines/jvm/internal/DebugMetadataKt\n*L\n131#1:135\n131#1:136,3\n*E\n"})
public final class DebugMetadataKt {
    public static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

    public static final void checkDebugMetadataVersion(int i, int i2) {
        if (i2 <= i) {
            return;
        }
        throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + i + ", got " + i2 + ". Please update the Kotlin standard library.").toString());
    }

    public static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl baseContinuationImpl) {
        return (DebugMetadata) baseContinuationImpl.getClass().getAnnotation(DebugMetadata.class);
    }

    public static final int getLabel(BaseContinuationImpl baseContinuationImpl) {
        try {
            Field declaredField = baseContinuationImpl.getClass().getDeclaredField(NotificationCompatJellybean.KEY_LABEL);
            declaredField.setAccessible(true);
            Object obj = declaredField.get(baseContinuationImpl);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            return (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            return -1;
        }
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "getSpilledVariableFieldMapping")
    @Nullable
    public static final String[] getSpilledVariableFieldMapping(@NotNull BaseContinuationImpl baseContinuationImpl) {
        Intrinsics.checkNotNullParameter(baseContinuationImpl, "<this>");
        DebugMetadata debugMetadataAnnotation = getDebugMetadataAnnotation(baseContinuationImpl);
        if (debugMetadataAnnotation == null) {
            return null;
        }
        checkDebugMetadataVersion(1, debugMetadataAnnotation.v());
        ArrayList arrayList = new ArrayList();
        int label = getLabel(baseContinuationImpl);
        int[] iArrI = debugMetadataAnnotation.i();
        int length = iArrI.length;
        for (int i = 0; i < length; i++) {
            if (iArrI[i] == label) {
                arrayList.add(debugMetadataAnnotation.s()[i]);
                arrayList.add(debugMetadataAnnotation.n()[i]);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "getStackTraceElement")
    @Nullable
    public static final StackTraceElement getStackTraceElement(@NotNull BaseContinuationImpl baseContinuationImpl) {
        String strC;
        Intrinsics.checkNotNullParameter(baseContinuationImpl, "<this>");
        DebugMetadata debugMetadataAnnotation = getDebugMetadataAnnotation(baseContinuationImpl);
        if (debugMetadataAnnotation == null) {
            return null;
        }
        checkDebugMetadataVersion(1, debugMetadataAnnotation.v());
        int label = getLabel(baseContinuationImpl);
        int i = label < 0 ? -1 : debugMetadataAnnotation.l()[label];
        String moduleName = ModuleNameRetriever.INSTANCE.getModuleName(baseContinuationImpl);
        if (moduleName == null) {
            strC = debugMetadataAnnotation.c();
        } else {
            strC = moduleName + '/' + debugMetadataAnnotation.c();
        }
        return new StackTraceElement(strC, debugMetadataAnnotation.m(), debugMetadataAnnotation.f(), i);
    }
}
