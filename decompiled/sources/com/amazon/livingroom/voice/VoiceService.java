package com.amazon.livingroom.voice;

import com.amazon.ignitionshared.GMBMessageProcessor;
import com.amazon.ignitionshared.GMBMessageSender;
import com.amazon.ignitionshared.ServiceInitializer;
import com.amazon.ignitionshared.metrics.MinervaConstants;
import com.amazon.ignitionshared.metrics.MinervaMetrics;
import com.amazon.livingroom.deviceproperties.DeviceProperties;
import com.amazon.livingroom.di.ApplicationScope;
import com.amazon.livingroom.voice.models.GoogleAssistantMissingException;
import com.amazon.livingroom.voice.models.ResultCode;
import com.amazon.livingroom.voice.models.VoiceCommand;
import com.amazon.livingroom.voice.models.VoiceCommandKt;
import com.amazon.livingroom.voice.models.VoiceMessage;
import com.amazon.livingroom.voice.models.VoiceModelFactory;
import com.amazon.livingroom.voice.models.VoiceResult;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.reporting.Log;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@ApplicationScope
@SourceDebugExtension({"SMAP\nVoiceService.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VoiceService.kt\ncom/amazon/livingroom/voice/VoiceService\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,182:1\n205#2:183\n*S KotlinDebug\n*F\n+ 1 VoiceService.kt\ncom/amazon/livingroom/voice/VoiceService\n*L\n74#1:183\n*E\n"})
public final class VoiceService implements ServiceInitializer {

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public static final String GOOGLE_ASSISTANT_REGISTER_PAYLOAD = "{\"supportedVoiceAssistants\":[{\"voiceAssistant\":\"GoogleAssistant\", \"isBiDirectionalCommunicationEnabled\": false}], \"version\":\"1.0.0\", \"messageTrackerId\":\"unique-id-for-voice-registration\"}";

    @NotNull
    public static final String LOG_TAG = "PV-VoiceService";

    @NotNull
    public static final String METRIC_MESSAGE_SUCCESS = "Voice.Message.Success";

    @NotNull
    public static final String METRIC_RESULT_SUCCESS = "Voice.Result.Success";

    @NotNull
    public static final String METRIC_STARTUP_ERROR_ASSISTANT_MISSING = "Voice.Startup.Error.GoogleAssistantMissing";

    @NotNull
    public static final String METRIC_STARTUP_ERROR_SERIALISATION = "Voice.Startup.Error.Serialisation";

    @NotNull
    public static final String METRIC_STARTUP_SUCCESS = "Voice.Startup.Success";

    @NotNull
    public static final String REGISTRATION_MESSAGE_ID = "unique-id-for-voice-registration";

    @NotNull
    public static final String VOICE_GMB_PRIME_VIDEO_MESSAGE_TYPE = "gmb.voice.primevideo.message";

    @NotNull
    public static final String VOICE_GMB_REGISTER_MESSAGE_TYPE = "gmb.voice.register";

    @NotNull
    public static final String VOICE_GMB_RESULT_MESSAGE_TYPE = "gmb.voice.device.result";

    @NotNull
    public static final String VOICE_GMB_STARTUP_MESSAGE_TYPE = "gmb.voice.startup";

    @NotNull
    public final DeviceProperties deviceProperties;

    @NotNull
    public final GMBMessageProcessor gmbMessageProcessor;

    @NotNull
    public final GMBMessageSender gmbMessageSender;

    @NotNull
    public AtomicBoolean isVoiceEnabled;

    @NotNull
    public final MinervaMetrics minervaMetrics;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Inject
    public VoiceService(@NotNull GMBMessageProcessor gmbMessageProcessor, @NotNull GMBMessageSender gmbMessageSender, @NotNull DeviceProperties deviceProperties, @NotNull MinervaMetrics minervaMetrics) {
        Intrinsics.checkNotNullParameter(gmbMessageProcessor, "gmbMessageProcessor");
        Intrinsics.checkNotNullParameter(gmbMessageSender, "gmbMessageSender");
        Intrinsics.checkNotNullParameter(deviceProperties, "deviceProperties");
        Intrinsics.checkNotNullParameter(minervaMetrics, "minervaMetrics");
        this.gmbMessageProcessor = gmbMessageProcessor;
        this.gmbMessageSender = gmbMessageSender;
        this.deviceProperties = deviceProperties;
        this.minervaMetrics = minervaMetrics;
        this.isVoiceEnabled = new AtomicBoolean(false);
    }

    public static final void initialize$lambda$2$lambda$0(VoiceService voiceService, String str) {
        Intrinsics.checkNotNull(str);
        voiceService.handleDeviceStartupMessage(str);
    }

    public static final void initialize$lambda$2$lambda$1(VoiceService voiceService, String str) {
        Intrinsics.checkNotNull(str);
        voiceService.handleDeviceResult(str);
    }

    public final void handleDeviceResult(String str) {
        Log.d(LOG_TAG, "Received voice result: " + str);
        try {
            VoiceResult voiceResultCreateVoiceResult = VoiceModelFactory.Companion.createVoiceResult(str);
            if (Intrinsics.areEqual(voiceResultCreateVoiceResult.messageTrackerId, REGISTRATION_MESSAGE_ID) && voiceResultCreateVoiceResult.result == ResultCode.SUCCESS) {
                this.isVoiceEnabled.set(true);
            }
            reportMetric(METRIC_RESULT_SUCCESS, 1L);
        } catch (SerializationException e) {
            Log.e(LOG_TAG, "Failed to serialise the voice device result message: " + e.getLocalizedMessage());
            reportMetric(METRIC_RESULT_SUCCESS, 0L);
        }
    }

    public final void handleDeviceStartupMessage(String str) {
        Log.d(LOG_TAG, "Received voice startup message, responding with registration message " + str);
        try {
            if (!ArraysKt___ArraysKt.contains(VoiceModelFactory.Companion.createVoiceStartup(str).supportedVoiceAssistants, VoiceCommandKt.GOOGLE_ASSISTANT)) {
                throw new GoogleAssistantMissingException("Google Assistant missing in startup message.");
            }
            GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, VOICE_GMB_REGISTER_MESSAGE_TYPE, GOOGLE_ASSISTANT_REGISTER_PAYLOAD, 0L, 4, null);
            reportMetric(METRIC_STARTUP_SUCCESS, 1L);
        } catch (GoogleAssistantMissingException e) {
            Log.e(LOG_TAG, "Failed to serialise the voice startup message: " + e.getLocalizedMessage());
            reportErrorMetricWithRate(METRIC_STARTUP_SUCCESS, METRIC_STARTUP_ERROR_ASSISTANT_MISSING);
        } catch (SerializationException e2) {
            Log.e(LOG_TAG, "Failed to serialise the voice startup message: " + e2.getLocalizedMessage());
            reportErrorMetricWithRate(METRIC_STARTUP_SUCCESS, METRIC_STARTUP_ERROR_SERIALISATION);
        }
    }

    @Override // com.amazon.ignitionshared.ServiceInitializer
    public void initialize() {
        if (!((Boolean) this.deviceProperties.get(DeviceProperties.VOICE_SERVICE_ENABLED)).booleanValue()) {
            Log.d(LOG_TAG, "Voice service disabled by device properties.");
            return;
        }
        GMBMessageProcessor gMBMessageProcessor = this.gmbMessageProcessor;
        gMBMessageProcessor.subscribeMessageHandler(VOICE_GMB_STARTUP_MESSAGE_TYPE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.livingroom.voice.VoiceService$$ExternalSyntheticLambda0
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                VoiceService.initialize$lambda$2$lambda$0(this.f$0, str);
            }
        });
        gMBMessageProcessor.subscribeMessageHandler(VOICE_GMB_RESULT_MESSAGE_TYPE, new GMBMessageProcessor.GMBMessageHandler() { // from class: com.amazon.livingroom.voice.VoiceService$$ExternalSyntheticLambda1
            @Override // com.amazon.ignitionshared.GMBMessageProcessor.GMBMessageHandler
            public final void process(String str) {
                VoiceService.initialize$lambda$2$lambda$1(this.f$0, str);
            }
        });
    }

    public final boolean isVoiceEnabled() {
        Object obj = this.deviceProperties.get(DeviceProperties.VOICE_SERVICE_ENABLED);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Boolean) obj).booleanValue() && this.isVoiceEnabled.get();
    }

    public final void reportErrorMetricWithRate(String str, String str2) {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.VOICE_SERVICE_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(str, 0L);
        metricEventCreateMetricEvent.addLong(str2, 1L);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }

    public final void reportMetric(String str, long j) {
        MetricEvent metricEventCreateMetricEvent = this.minervaMetrics.createMetricEvent(MinervaConstants.VOICE_SERVICE_SCHEMA_ID);
        metricEventCreateMetricEvent.addLong(str, j);
        this.minervaMetrics.record(metricEventCreateMetricEvent);
    }

    public final void sendVoiceCommand(@NotNull VoiceCommand voiceCommand) {
        Intrinsics.checkNotNullParameter(voiceCommand, "voiceCommand");
        if (!isVoiceEnabled()) {
            Log.d(LOG_TAG, "Voice not enabled. Startup handshake has either not taken place or was un-successful.");
            return;
        }
        try {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            VoiceMessage voiceMessageCreateVoiceMessage = VoiceModelFactory.Companion.createVoiceMessage(voiceCommand, string);
            Json.Default r5 = Json.Default;
            r5.getClass();
            String strEncodeToString = r5.encodeToString(VoiceMessage.Companion.serializer(), voiceMessageCreateVoiceMessage);
            Log.d(LOG_TAG, "Sending voice message with ID " + string + " to client.");
            GMBMessageSender.sendGMBMessageToClient$default(this.gmbMessageSender, VOICE_GMB_PRIME_VIDEO_MESSAGE_TYPE, strEncodeToString, 0L, 4, null);
            reportMetric(METRIC_MESSAGE_SUCCESS, 1L);
        } catch (SerializationException unused) {
            Log.e(LOG_TAG, "Error serialising voice command. Namespace " + voiceCommand.namespace + " and name: " + voiceCommand.name);
            reportMetric(METRIC_MESSAGE_SUCCESS, 0L);
        }
    }
}
