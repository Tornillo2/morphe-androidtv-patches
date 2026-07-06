package com.amazon.livingroom.di;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.WindowManager;
import androidx.appcompat.widget.ActivityChooserModel;
import com.amazon.avod.mpb.media.drm.DrmKeyStatusNotifier;
import com.amazon.ignitionshared.IgniteAllocator;
import com.amazon.ignitionshared.IgniteRenderer;
import com.amazon.ignitionshared.NativeAllocator;
import com.amazon.ignitionshared.NativeAllocatorMessageHandler;
import com.amazon.ignitionshared.Renderer;
import com.amazon.ignitionshared.RendererManager;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.ignitionshared.network.VolleyModule;
import com.amazon.livingroom.deviceproperties.DefaultDeviceProperties;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.deviceproperties.LocalOverridesProvider;
import com.amazon.livingroom.deviceproperties.OverridableDeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformDeviceProperties;
import com.amazon.livingroom.deviceproperties.PlatformProperty;
import com.amazon.livingroom.deviceproperties.RemoteOverridesProvider;
import com.amazon.livingroom.deviceproperties.expression.ExpressionEvaluator;
import com.amazon.livingroom.mediapipelinebackend.AudioFocusManager;
import com.amazon.livingroom.mediapipelinebackend.DrmKeyStatusNotifierImpl;
import com.amazon.livingroom.mediapipelinebackend.DrmKeyStatusNotifierNotSupported;
import com.amazon.livingroom.mediapipelinebackend.MediaSessionCallback;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.minerva.client.thirdparty.api.AmazonMinervaAndroidClientBuilder;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.sony.dtv.picturequalitycontrol.PictureQualityController;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import java.util.List;
import javax.inject.Named;
import javax.inject.Provider;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Module(includes = {VolleyModule.class})
public interface CoreModule {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @SourceDebugExtension({"SMAP\nCoreModule.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoreModule.kt\ncom/amazon/livingroom/di/CoreModule$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,357:1\n1869#2,2:358\n37#3:360\n36#3,3:361\n*S KotlinDebug\n*F\n+ 1 CoreModule.kt\ncom/amazon/livingroom/di/CoreModule$Companion\n*L\n333#1:358,2\n353#1:360\n353#1:361,3\n*E\n"})
    public static final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public static /* synthetic */ boolean $r8$lambda$H25UwvP03yeo0wIQzIjf6TvKo94() {
            return true;
        }

        public static /* synthetic */ boolean $r8$lambda$RKwndW3gbgFWox5isRnaubitbr8() {
            return false;
        }

        public static /* synthetic */ String $r8$lambda$kx8utZFo7fZ_3OdKeocGfUbWp00() throws Exception {
            provideMinervaClient$lambda$2();
            throw null;
        }

        public static final boolean provideMinervaClient$lambda$0() {
            return false;
        }

        public static final boolean provideMinervaClient$lambda$1() {
            return true;
        }

        public static final String provideMinervaClient$lambda$2() throws Exception {
            throw new Exception("No access token for recording metrics using minerva");
        }

        @Provides
        @NotNull
        public final ActivityManager provideActivityManager(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            return (ActivityManager) systemService;
        }

        @Provides
        @NotNull
        public final ApplicationInfo provideApplicationInfo(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
            return applicationInfo;
        }

        @Provides
        @Named(Names.APPLICATION_PACKAGE_NAME)
        @NotNull
        public final String provideApplicationPackageName(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String packageName = context.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
            return packageName;
        }

        @Provides
        @NotNull
        public final AudioManager provideAudioManager(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("audio");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            return (AudioManager) systemService;
        }

        @ApplicationScope
        @Provides
        public final ContentResolver provideContentResolver(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return context.getContentResolver();
        }

        @Provides
        @NotNull
        public final DisplayManager provideDisplayManager(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("display");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
            return (DisplayManager) systemService;
        }

        @ApplicationScope
        @Provides
        @NotNull
        public final DrmKeyStatusNotifier provideDrmKeyStatusNotifier() {
            return Build.VERSION.SDK_INT >= 23 ? new DrmKeyStatusNotifierImpl() : new DrmKeyStatusNotifierNotSupported();
        }

        @Provides
        @Named(Names.GET_DTID_RETRY_POLICY)
        @NotNull
        public final RetryPolicy provideDtidRequestRetryPolicy() {
            return new DefaultRetryPolicy(2500, 2, 2.0f);
        }

        @Provides
        @Named(Names.DEVICE_INFO)
        @NotNull
        public final SharedPreferences provideDtidSharedPreferences(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(Names.DEVICE_INFO, 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
            return sharedPreferences;
        }

        @Provides
        @Named(Names.GET_APP_STARTUP_CONFIG_RETRY_POLICY)
        @NotNull
        public final RetryPolicy provideGascRetryPolicy() {
            return new DefaultRetryPolicy(2500, 3, 2.0f);
        }

        @Provides
        @Named(Names.IGNITIONX_STARTUP_ARGUMENTS)
        @NotNull
        public final String[] provideIgnitionXStartupArguments(@NotNull DeviceProperties deviceProperties, @Named(Names.IGNITION_APPLICATION_ID) @NotNull String applicationId) {
            Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
            Intrinsics.checkNotNullParameter(applicationId, "applicationId");
            DeviceProperties.Property<String> IGNITIONX_CLIENT_CONFIG = DeviceProperties.IGNITIONX_CLIENT_CONFIG;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_CLIENT_CONFIG, "IGNITIONX_CLIENT_CONFIG");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--client-config", IGNITIONX_CLIENT_CONFIG);
            DeviceProperties.Property<String> IGNITIONX_DEVICE_LABEL = DeviceProperties.IGNITIONX_DEVICE_LABEL;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_DEVICE_LABEL, "IGNITIONX_DEVICE_LABEL");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument2 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--device-label", IGNITIONX_DEVICE_LABEL);
            DeviceProperties.Property<String> IGNITIONX_DEVICE_PROXY_URL = DeviceProperties.IGNITIONX_DEVICE_PROXY_URL;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_DEVICE_PROXY_URL, "IGNITIONX_DEVICE_PROXY_URL");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument3 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--device-proxy-url", IGNITIONX_DEVICE_PROXY_URL);
            DeviceProperties.Property<String> IGNITIONX_BLAST_URL = DeviceProperties.IGNITIONX_BLAST_URL;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_BLAST_URL, "IGNITIONX_BLAST_URL");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument4 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--blast-url", IGNITIONX_BLAST_URL);
            DeviceProperties.Property<String> IGNITIONX_BLUR_URI_PREFIX = DeviceProperties.IGNITIONX_BLUR_URI_PREFIX;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_BLUR_URI_PREFIX, "IGNITIONX_BLUR_URI_PREFIX");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument5 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--blur-uri-prefix", IGNITIONX_BLUR_URI_PREFIX);
            DeviceProperties.Property<String> IGNITIONX_REACT_URI_PREFIX = DeviceProperties.IGNITIONX_REACT_URI_PREFIX;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_REACT_URI_PREFIX, "IGNITIONX_REACT_URI_PREFIX");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument6 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--react-uri-prefix", IGNITIONX_REACT_URI_PREFIX);
            DeviceProperties.Property<String> IGNITIONX_APP_STARTUP_MODE = DeviceProperties.IGNITIONX_APP_STARTUP_MODE;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_APP_STARTUP_MODE, "IGNITIONX_APP_STARTUP_MODE");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument7 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--app-startup-mode", IGNITIONX_APP_STARTUP_MODE);
            DeviceProperties.Property<String> IGNITIONX_HTTP_PROXY_SERVER = DeviceProperties.IGNITIONX_HTTP_PROXY_SERVER;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_HTTP_PROXY_SERVER, "IGNITIONX_HTTP_PROXY_SERVER");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument8 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--http-proxy-server", IGNITIONX_HTTP_PROXY_SERVER);
            DeviceProperties.Property<String> IGNITIONX_WEBSOCKET_PROXY_SERVER = DeviceProperties.IGNITIONX_WEBSOCKET_PROXY_SERVER;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_WEBSOCKET_PROXY_SERVER, "IGNITIONX_WEBSOCKET_PROXY_SERVER");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument9 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--websocket-proxy-server", IGNITIONX_WEBSOCKET_PROXY_SERVER);
            DeviceProperties.Property<String> IGNITIONX_LOG_LEVEL = DeviceProperties.IGNITIONX_LOG_LEVEL;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_LOG_LEVEL, "IGNITIONX_LOG_LEVEL");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument10 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--log-level", IGNITIONX_LOG_LEVEL);
            DeviceProperties.Property<String> IGNITIONX_LOG_EVENT_BUFFER_SIZE = DeviceProperties.IGNITIONX_LOG_EVENT_BUFFER_SIZE;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_LOG_EVENT_BUFFER_SIZE, "IGNITIONX_LOG_EVENT_BUFFER_SIZE");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument11 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--log-event-buffer-size", IGNITIONX_LOG_EVENT_BUFFER_SIZE);
            DeviceProperties.Property<String> IGNITIONX_LOCAL_NATIVE_MODULE_PATH = DeviceProperties.IGNITIONX_LOCAL_NATIVE_MODULE_PATH;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_LOCAL_NATIVE_MODULE_PATH, "IGNITIONX_LOCAL_NATIVE_MODULE_PATH");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument12 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--local-native-module-path", IGNITIONX_LOCAL_NATIVE_MODULE_PATH);
            DeviceProperties.Property<String> IGNITIONX_NO_YIELD_JS_ENGINE = DeviceProperties.IGNITIONX_NO_YIELD_JS_ENGINE;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_NO_YIELD_JS_ENGINE, "IGNITIONX_NO_YIELD_JS_ENGINE");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument13 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--set-default-js-engine", IGNITIONX_NO_YIELD_JS_ENGINE);
            DeviceProperties.Property<String> IGNITIONX_WASM_ENGINE = DeviceProperties.IGNITIONX_WASM_ENGINE;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_WASM_ENGINE, "IGNITIONX_WASM_ENGINE");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument14 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--set-default-wasm-engine", IGNITIONX_WASM_ENGINE);
            DeviceProperties.Property<String> IGNITIONX_LOCAL_WASM_PATH = DeviceProperties.IGNITIONX_LOCAL_WASM_PATH;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_LOCAL_WASM_PATH, "IGNITIONX_LOCAL_WASM_PATH");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument15 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--local-wasm-path", IGNITIONX_LOCAL_WASM_PATH);
            DeviceProperties.Property<String> IGNITIONX_LOCAL_WASM_MODULE_PATH = DeviceProperties.IGNITIONX_LOCAL_WASM_MODULE_PATH;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_LOCAL_WASM_MODULE_PATH, "IGNITIONX_LOCAL_WASM_MODULE_PATH");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument16 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--local-wasm-module-path", IGNITIONX_LOCAL_WASM_MODULE_PATH);
            DeviceProperties.Property<Boolean> IGNITIONX_USE_LOCAL_LUA = DeviceProperties.IGNITIONX_USE_LOCAL_LUA;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_USE_LOCAL_LUA, "IGNITIONX_USE_LOCAL_LUA");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument17 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--use-local-lua", IGNITIONX_USE_LOCAL_LUA);
            DeviceProperties.Property<Boolean> IGNITIONX_USE_LOCAL_JS = DeviceProperties.IGNITIONX_USE_LOCAL_JS;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_USE_LOCAL_JS, "IGNITIONX_USE_LOCAL_JS");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument18 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--use-local-js", IGNITIONX_USE_LOCAL_JS);
            DeviceProperties.Property<Boolean> IGNITIONX_BYPASS_BLUR_SERVER = DeviceProperties.IGNITIONX_BYPASS_BLUR_SERVER;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_BYPASS_BLUR_SERVER, "IGNITIONX_BYPASS_BLUR_SERVER");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument19 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--bypass-blur-server", IGNITIONX_BYPASS_BLUR_SERVER);
            DeviceProperties.Property<Boolean> IGNITIONX_DISABLE_SSL_CERT = DeviceProperties.IGNITIONX_DISABLE_SSL_CERT;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_DISABLE_SSL_CERT, "IGNITIONX_DISABLE_SSL_CERT");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument20 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--disable-ssl-cert", IGNITIONX_DISABLE_SSL_CERT);
            DeviceProperties.Property<Boolean> IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT = DeviceProperties.IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT, "IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument21 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--allow-ws-self-signed-cert", IGNITIONX_ALLOW_WS_SELF_SIGNED_CERT);
            DeviceProperties.Property<Boolean> IGNITIONX_DISABLE_STDOUT_LOGS = DeviceProperties.IGNITIONX_DISABLE_STDOUT_LOGS;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_DISABLE_STDOUT_LOGS, "IGNITIONX_DISABLE_STDOUT_LOGS");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument22 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--disable-stdout-log", IGNITIONX_DISABLE_STDOUT_LOGS);
            DeviceProperties.Property<Boolean> IGNITIONX_ENABLE_WAMR_DEBUGGER = DeviceProperties.IGNITIONX_ENABLE_WAMR_DEBUGGER;
            Intrinsics.checkNotNullExpressionValue(IGNITIONX_ENABLE_WAMR_DEBUGGER, "IGNITIONX_ENABLE_WAMR_DEBUGGER");
            CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument23 = new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--enable-wamr-debugger", IGNITIONX_ENABLE_WAMR_DEBUGGER);
            DeviceProperties.Property<Boolean> DTID_REQUEST_FAILED = DeviceProperties.DTID_REQUEST_FAILED;
            Intrinsics.checkNotNullExpressionValue(DTID_REQUEST_FAILED, "DTID_REQUEST_FAILED");
            List<CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument> listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument[]{coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument2, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument3, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument4, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument5, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument6, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument7, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument8, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument9, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument10, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument11, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument12, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument13, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument14, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument15, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument16, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument17, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument18, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument19, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument20, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument21, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument22, coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument23, new CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument("--start-with-internet-connection-error-screen", DTID_REQUEST_FAILED)});
            List listCreateListBuilder = CollectionsKt__CollectionsJVMKt.createListBuilder();
            ListBuilder listBuilder = (ListBuilder) listCreateListBuilder;
            listBuilder.add("app_process");
            for (CoreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument24 : listListOf) {
                Object obj = deviceProperties.get(coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument24.property);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                boolean z = obj instanceof String;
                if (!z || ((CharSequence) obj).length() <= 0) {
                    boolean z2 = obj instanceof Boolean;
                    if (z2 && ((Boolean) obj).booleanValue()) {
                        listBuilder.add(coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument24.flag);
                    } else if (!z && !z2) {
                        throw new RuntimeException("Unexpected device property type for command line argument " + coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument24);
                    }
                } else {
                    listBuilder.add(coreModule$Companion$provideIgnitionXStartupArguments$CommandLineArgument24.flag + "=" + obj);
                }
            }
            if (applicationId.length() > 0) {
                listBuilder.add("--application-id=".concat(applicationId));
            }
            return (String[]) ((ListBuilder) CollectionsKt__CollectionsJVMKt.build(listCreateListBuilder)).toArray(new String[0]);
        }

        @Provides
        @Named("local")
        @NotNull
        public final ExpressionEvaluator provideLocalExpressionEvaluator(@NotNull DeviceProperties deviceProperties, @Named(Names.REMOTE) @NotNull OverridableDeviceProperties defaultProperties) {
            Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
            Intrinsics.checkNotNullParameter(defaultProperties, "defaultProperties");
            return new ExpressionEvaluator(deviceProperties, defaultProperties);
        }

        @Provides
        @Named("local")
        @NotNull
        public final OverridableDeviceProperties provideLocallyOverridableDeviceProperties(@Named(Names.REMOTE) @NotNull OverridableDeviceProperties remoteProperties, @NotNull LocalOverridesProvider localOverridesProvider, @Named("local") @NotNull Provider<ExpressionEvaluator> expressionEvaluator) {
            Intrinsics.checkNotNullParameter(remoteProperties, "remoteProperties");
            Intrinsics.checkNotNullParameter(localOverridesProvider, "localOverridesProvider");
            Intrinsics.checkNotNullParameter(expressionEvaluator, "expressionEvaluator");
            return new OverridableDeviceProperties(remoteProperties, localOverridesProvider, expressionEvaluator);
        }

        @ApplicationScope
        @Provides
        @NotNull
        public final Handler provideMainHandler(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new Handler(context.getMainLooper());
        }

        @ApplicationScope
        @Provides
        @NotNull
        public final AmazonMinerva provideMinervaClient(@ApplicationContext @NotNull Context context, @NotNull PlatformDeviceProperties deviceProperties) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
            Object obj = deviceProperties.get(DeviceProperties.DEVICE_TYPE_ID, deviceProperties);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            CoreModule$Companion$$ExternalSyntheticLambda0 coreModule$Companion$$ExternalSyntheticLambda0 = new CoreModule$Companion$$ExternalSyntheticLambda0();
            CoreModule$Companion$$ExternalSyntheticLambda1 coreModule$Companion$$ExternalSyntheticLambda1 = new CoreModule$Companion$$ExternalSyntheticLambda1();
            CoreModule$Companion$$ExternalSyntheticLambda2 coreModule$Companion$$ExternalSyntheticLambda2 = new CoreModule$Companion$$ExternalSyntheticLambda2();
            AmazonMinervaAndroidClientBuilder amazonMinervaAndroidClientBuilderStandard = AmazonMinervaAndroidClientBuilder.standard(context);
            amazonMinervaAndroidClientBuilderStandard.mRegion = "us-east-1";
            amazonMinervaAndroidClientBuilderStandard.withDeviceType((String) obj);
            amazonMinervaAndroidClientBuilderStandard.withOAuthProvider(coreModule$Companion$$ExternalSyntheticLambda2);
            amazonMinervaAndroidClientBuilderStandard.withChildProfileVerifier(coreModule$Companion$$ExternalSyntheticLambda0);
            amazonMinervaAndroidClientBuilderStandard.withUserControlVerifier(coreModule$Companion$$ExternalSyntheticLambda1);
            return amazonMinervaAndroidClientBuilderStandard.build();
        }

        @Provides
        @NotNull
        public final PackageManager providePackageManager(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
            return packageManager;
        }

        @ApplicationScope
        @Provides
        @NotNull
        public final PictureQualityController providePictureQualityController() {
            return new PictureQualityController();
        }

        @Provides
        @NotNull
        public final PlatformDeviceProperties providePlatformDeviceProperties(@NotNull DefaultDeviceProperties deviceProperties, @Named(Names.PLATFORM_PROPERTIES) @NotNull List<PlatformProperty<?>> platformProperties) {
            Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
            Intrinsics.checkNotNullParameter(platformProperties, "platformProperties");
            return new PlatformDeviceProperties(deviceProperties, platformProperties);
        }

        @Provides
        @Named(Names.RECOMMENDATION_PARAMETERS)
        @NotNull
        public final SharedPreferences provideRecommendationsSharedPreferences(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName() + ".recommendations", 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
            return sharedPreferences;
        }

        @Provides
        @Named(Names.REMOTE)
        @NotNull
        public final ExpressionEvaluator provideRemoteExpressionEvaluator(@NotNull DeviceProperties deviceProperties, @NotNull DefaultDeviceProperties defaultProperties) {
            Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
            Intrinsics.checkNotNullParameter(defaultProperties, "defaultProperties");
            return new ExpressionEvaluator(deviceProperties, defaultProperties);
        }

        @Provides
        @Named(Names.REMOTE)
        @NotNull
        public final OverridableDeviceProperties provideRemotelyOverridableDeviceProperties(@NotNull PlatformDeviceProperties defaultProperties, @NotNull RemoteOverridesProvider remoteOverridesProvider, @Named(Names.REMOTE) @NotNull Provider<ExpressionEvaluator> expressionEvaluator) {
            Intrinsics.checkNotNullParameter(defaultProperties, "defaultProperties");
            Intrinsics.checkNotNullParameter(remoteOverridesProvider, "remoteOverridesProvider");
            Intrinsics.checkNotNullParameter(expressionEvaluator, "expressionEvaluator");
            return new OverridableDeviceProperties(defaultProperties, remoteOverridesProvider, expressionEvaluator);
        }

        @Provides
        @NotNull
        public final RendererManager.ExitCallback provideRendererManagerExitCallback() {
            return new CoreModule$Companion$provideRendererManagerExitCallback$1();
        }

        @Provides
        @Named(Names.RECOMMENDATION_REQUEST_STRUCTURE_CONTENT_PROVIDER)
        @NotNull
        public final SharedPreferences provideRequestStructureContentProviderPreference(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName() + ".recommendationRequestStructureProvider", 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
            return sharedPreferences;
        }

        @Provides
        @Named(Names.SUPPORTED_LOCALE_LANGUAGE_TAGS)
        @NotNull
        public final List<String> provideSupportedLocaleLanguageTags() {
            return CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"de_DE", "en_US", "es_ES", "fr_FR", "it_IT", "nl_NL", "pl_PL", "pt_BR", "pt_PT", "ms_MY", "fil_PH", "hi_IN", "ta_IN", "te_IN", "nb_NO", "sv_SE", "da_DK", "zh_CN", "zh_TW", "ko_KR", "th_TH", "fi_FI", "tr_TR", "id_ID", "ru_RU", "ja_JP", "es_US"});
        }

        @Provides
        @NotNull
        public final WindowManager provideWindowManager(@ApplicationContext @NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Object systemService = context.getSystemService("window");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
            return (WindowManager) systemService;
        }
    }

    @Binds
    @NotNull
    AudioFocusManager.Callback bindAudioFocusManagerCallback(@NotNull MediaSessionCallback mediaSessionCallback);

    @Binds
    @NotNull
    MediaSessionCompat.Callback bindMediaSessionCallback(@NotNull MediaSessionCallback mediaSessionCallback);

    @Binds
    @Named(Names.NON_OVERRIDABLE)
    @NotNull
    DeviceProperties bindNonOverridableDeviceProperties(@NotNull DefaultDeviceProperties defaultDeviceProperties);

    @Binds
    @NotNull
    Renderer bindRenderer(@NotNull IgniteRenderer igniteRenderer);

    @Binds
    @NotNull
    DeviceProperties bindUnqualifiedDeviceProperties(@Named("local") @NotNull OverridableDeviceProperties overridableDeviceProperties);

    @Binds
    @NotNull
    NativeAllocator provideNativeAllocator(@NotNull IgniteAllocator igniteAllocator);

    @ApplicationScope
    @Binds
    @IntoSet
    @NotNull
    ServiceInitializer provideNativeAllocatorMessageHandler(@NotNull NativeAllocatorMessageHandler nativeAllocatorMessageHandler);
}
