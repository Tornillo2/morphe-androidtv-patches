package kotlinx.coroutines.debug;

import android.annotation.SuppressLint;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.AgentInstallationType;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.misc.Signal;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"all"})
@IgnoreJRERequirement
public final class AgentPremain {

    @NotNull
    public static final AgentPremain INSTANCE = new AgentPremain();
    public static final boolean enableCreationStackTraces;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class DebugProbesTransformer implements ClassFileTransformer {

        @NotNull
        public static final DebugProbesTransformer INSTANCE = new DebugProbesTransformer();

        @Nullable
        public byte[] transform(@NotNull ClassLoader classLoader, @NotNull String str, @Nullable Class<?> cls, @NotNull ProtectionDomain protectionDomain, @Nullable byte[] bArr) {
            if (!Intrinsics.areEqual(str, "kotlin/coroutines/jvm/internal/DebugProbesKt")) {
                return null;
            }
            AgentInstallationType.INSTANCE.getClass();
            AgentInstallationType.isInstalledStatically = true;
            return ByteStreamsKt.readBytes(classLoader.getResourceAsStream("DebugProbesKt.bin"));
        }
    }

    static {
        Object objCreateFailure;
        boolean zBooleanValue;
        try {
            String property = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
            objCreateFailure = property != null ? Boolean.valueOf(Boolean.parseBoolean(property)) : null;
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
        }
        Boolean bool = (Boolean) (objCreateFailure instanceof Result.Failure ? null : objCreateFailure);
        if (bool != null) {
            zBooleanValue = bool.booleanValue();
        } else {
            DebugProbesImpl.INSTANCE.getClass();
            zBooleanValue = DebugProbesImpl.enableCreationStackTraces;
        }
        enableCreationStackTraces = zBooleanValue;
    }

    /* JADX INFO: renamed from: installSignalHandler$lambda-1, reason: not valid java name */
    public static final void m2107installSignalHandler$lambda1(Signal signal) {
        DebugProbesImpl debugProbesImpl = DebugProbesImpl.INSTANCE;
        if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
            debugProbesImpl.dumpCoroutines(System.out);
        } else {
            System.out.println((Object) "Cannot perform coroutines dump, debug probes are disabled");
        }
    }

    @JvmStatic
    public static final void premain(@Nullable String str, @NotNull Instrumentation instrumentation) {
        AgentInstallationType.INSTANCE.getClass();
        AgentInstallationType.isInstalledStatically = true;
        instrumentation.addTransformer(DebugProbesTransformer.INSTANCE);
        DebugProbesImpl debugProbesImpl = DebugProbesImpl.INSTANCE;
        boolean z = enableCreationStackTraces;
        debugProbesImpl.getClass();
        DebugProbesImpl.enableCreationStackTraces = z;
        debugProbesImpl.install();
        INSTANCE.installSignalHandler();
    }

    public final void installSignalHandler() {
        try {
            Signal.handle(new Signal("TRAP"), new AgentPremain$$ExternalSyntheticLambda0());
        } catch (Throwable unused) {
        }
    }
}
