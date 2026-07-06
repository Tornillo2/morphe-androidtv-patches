package com.amazon.ignitionshared;

import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.CalledFromNative;
import java.util.List;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class DeviceAttestationService {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final Attestor attestor;

    @NotNull
    public final CoroutineScope scope;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public interface Attestor {
        @Nullable
        Object attestDevice(@NotNull String str, @NotNull List<String> list, @NotNull Continuation<? super DeviceAttestationResult> continuation);
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        @JvmStatic
        public final void onFail(int i, @NotNull String str) {
            DeviceAttestationService.onFail(i, str);
        }

        @JvmStatic
        public final void onSuccess(int i) {
            DeviceAttestationService.onSuccess(i);
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* JADX INFO: renamed from: com.amazon.ignitionshared.DeviceAttestationService$attestDevice$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @DebugMetadata(c = "com.amazon.ignitionshared.DeviceAttestationService$attestDevice$1", f = "DeviceAttestationService.kt", i = {}, l = {48}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ int $cookie;
        public final /* synthetic */ List<String> $deviceAttestationOriginUrls;
        public final /* synthetic */ String $videoMaterialType;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, List<String> list, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$videoMaterialType = str;
            this.$deviceAttestationOriginUrls = list;
            this.$cookie = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeviceAttestationService.this.new AnonymousClass1(this.$videoMaterialType, this.$deviceAttestationOriginUrls, this.$cookie, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Attestor attestor = DeviceAttestationService.this.attestor;
                    String str = this.$videoMaterialType;
                    List<String> list = this.$deviceAttestationOriginUrls;
                    this.label = 1;
                    obj = attestor.attestDevice(str, list, this);
                    if (obj == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                DeviceAttestationResult deviceAttestationResult = (DeviceAttestationResult) obj;
                if (deviceAttestationResult.getSuccess()) {
                    Companion companion = DeviceAttestationService.Companion;
                    int i2 = this.$cookie;
                    companion.getClass();
                    DeviceAttestationService.onSuccess(i2);
                } else {
                    Companion companion2 = DeviceAttestationService.Companion;
                    int i3 = this.$cookie;
                    String errorMessage = deviceAttestationResult.getErrorMessage();
                    companion2.getClass();
                    DeviceAttestationService.onFail(i3, errorMessage);
                }
            } catch (CancellationException e) {
                throw e;
            } catch (Exception e2) {
                Companion companion3 = DeviceAttestationService.Companion;
                companion3.getClass();
                DeviceAttestationService.onFail(this.$cookie, "Error during attestation: " + e2);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Inject
    public DeviceAttestationService(@NotNull Attestor attestor) {
        Intrinsics.checkNotNullParameter(attestor, "attestor");
        this.attestor = attestor;
        this.scope = CoroutineScopeKt.CoroutineScope(CoroutineContext.Element.DefaultImpls.plus((JobSupport) SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null), Dispatchers.getIO()));
    }

    @JvmStatic
    public static final native void onFail(int i, @NotNull String str);

    @JvmStatic
    public static final native void onSuccess(int i);

    @CalledFromNative
    public final void attestDevice(int i, @NotNull String videoMaterialType, @NotNull List<String> deviceAttestationOriginUrls) {
        Intrinsics.checkNotNullParameter(videoMaterialType, "videoMaterialType");
        Intrinsics.checkNotNullParameter(deviceAttestationOriginUrls, "deviceAttestationOriginUrls");
        BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new AnonymousClass1(videoMaterialType, deviceAttestationOriginUrls, i, null), 2, null);
    }
}
