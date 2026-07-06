package com.amazon.livingroom.mediapipelinebackend;

import android.content.Context;
import com.amazon.livingroom.di.ApplicationContext;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.mediapipelinebackend.HdcpVersionProvider;
import com.amazon.livingroom.voice.models.VoiceCommandKt;
import java.lang.reflect.Method;
import javax.inject.Inject;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
public final class HdcpVersionProvider {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final Context context;

    @Nullable
    public final Function0<String> getFromFireOsService;

    @NotNull
    public final WidevineCapabilitiesProvider widevineCapabilitiesProvider;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public static final String getFireOsHdcpGetter$lambda$0(Method method, Object obj) {
            Integer num = null;
            try {
                num = (Integer) method.invoke(obj, null);
            } catch (Exception e) {
                MpbLog.w("Failed to get HDCP version from Amazon HDMI service", e);
            }
            return HdcpVersionProvider.Companion.mapAmazonHdcpVersionCode(num);
        }

        public final Function0<String> getFireOsHdcpGetter(Context context) {
            if (!context.getPackageManager().hasSystemFeature("com.fireos.sdk.amazon_hdmi_service")) {
                MpbLog.i("Amazon HDMI service not available - it will not be used to check HDCP");
                return null;
            }
            final Object systemService = context.getSystemService("AmazonHdmiService");
            if (systemService == null) {
                MpbLog.w("Amazon HDMI service is reported as available but couldn't be found - it will not be used to check HDCP");
                return null;
            }
            try {
                final Method method = systemService.getClass().getMethod("getCurrentHdcpVersion", null);
                return new Function0() { // from class: com.amazon.livingroom.mediapipelinebackend.HdcpVersionProvider$Companion$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HdcpVersionProvider.Companion.getFireOsHdcpGetter$lambda$0(method, systemService);
                    }
                };
            } catch (Exception e) {
                MpbLog.w("Amazon HDMI service doesn't have a getCurrentHdcpVersion method - it will not be used to check HDCP", e);
                return null;
            }
        }

        public final String mapAmazonHdcpVersionCode(Integer num) {
            if (num == null) {
                MpbLog.w("Null HDCP version code received from Amazon HDMI service, it will not be used");
                return null;
            }
            if (num.intValue() == 0 || num.intValue() == 255) {
                return "0.0";
            }
            if (num.intValue() == 1) {
                return VoiceCommandKt.TRANSPORT_CONTROLS_PAYLOAD_VERSION;
            }
            if (num.intValue() == 2) {
                return "2.0";
            }
            if (num.intValue() == 3) {
                return "2.1";
            }
            if (num.intValue() == 4) {
                return "2.2";
            }
            MpbLog.w("Unknown HDCP version code " + num + " received from Amazon HDMI service, assuming it means at least 2.2");
            return "2.2";
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public HdcpVersionProvider(@ApplicationContext @NotNull Context context, @NotNull WidevineCapabilitiesProvider widevineCapabilitiesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(widevineCapabilitiesProvider, "widevineCapabilitiesProvider");
        this.context = context;
        this.widevineCapabilitiesProvider = widevineCapabilitiesProvider;
        this.getFromFireOsService = Companion.getFireOsHdcpGetter(context);
    }

    @NotNull
    public final String getCurrentHdcpVersion() {
        String strInvoke;
        Function0<String> function0 = this.getFromFireOsService;
        return (function0 == null || (strInvoke = function0.invoke()) == null) ? this.widevineCapabilitiesProvider.getHdcpLevel() : strInvoke;
    }
}
