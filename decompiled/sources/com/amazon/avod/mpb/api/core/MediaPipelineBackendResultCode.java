package com.amazon.avod.mpb.api.core;

import androidx.appcompat.app.AppCompatDelegate;
import com.amazon.livingroom.mediapipelinebackend.ErrorCode;
import com.android.billingclient.api.ProxyBillingActivity;
import com.google.android.gms.location.LocationRequest;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.time.GmtTimeZone;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaPipelineBackendResultCode {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ MediaPipelineBackendResultCode[] $VALUES;
    public final int resultCode;
    public static final MediaPipelineBackendResultCode AV_MPB_SUCCESS = new MediaPipelineBackendResultCode("AV_MPB_SUCCESS", 0, 0);
    public static final MediaPipelineBackendResultCode AV_MPB_NOT_INITIALIZED = new MediaPipelineBackendResultCode("AV_MPB_NOT_INITIALIZED", 1, 50008);
    public static final MediaPipelineBackendResultCode AV_MPB_ALREADY_INITIALIZED = new MediaPipelineBackendResultCode("AV_MPB_ALREADY_INITIALIZED", 2, 50012);
    public static final MediaPipelineBackendResultCode AV_MPB_IS_DESTROYED = new MediaPipelineBackendResultCode("AV_MPB_IS_DESTROYED", 3, 50031);
    public static final MediaPipelineBackendResultCode AV_MPB_KEY_NOT_FOUND = new MediaPipelineBackendResultCode("AV_MPB_KEY_NOT_FOUND", 4, ErrorCode.MPB_KEY_NOT_FOUND);
    public static final MediaPipelineBackendResultCode AV_MPB_VALUE_NOT_SUPPORTED = new MediaPipelineBackendResultCode("AV_MPB_VALUE_NOT_SUPPORTED", 5, ErrorCode.MPB_VALUE_NOT_SUPPORTED);
    public static final MediaPipelineBackendResultCode AV_MPB_CONCURRENT_INSTANCE_CREATION_FAILED = new MediaPipelineBackendResultCode("AV_MPB_CONCURRENT_INSTANCE_CREATION_FAILED", 6, 50042);
    public static final MediaPipelineBackendResultCode AV_MPB_OVERFLOW = new MediaPipelineBackendResultCode("AV_MPB_OVERFLOW", 7, 50043);
    public static final MediaPipelineBackendResultCode AV_MPB_NOT_MUTABLE = new MediaPipelineBackendResultCode("AV_MPB_NOT_MUTABLE", 8, 50044);
    public static final MediaPipelineBackendResultCode AV_MPB_PICTURE_MODE_NOT_SET = new MediaPipelineBackendResultCode("AV_MPB_PICTURE_MODE_NOT_SET", 9, ErrorCode.MPB_PICTURE_MODE_NOT_SET);
    public static final MediaPipelineBackendResultCode AV_MPB_INVALID_BACKGROUND_OPERATION = new MediaPipelineBackendResultCode("AV_MPB_INVALID_BACKGROUND_OPERATION", 10, ErrorCode.INVALID_BACKGROUND_OPERATION);
    public static final MediaPipelineBackendResultCode AV_MPB_BUFFER_FULL = new MediaPipelineBackendResultCode("AV_MPB_BUFFER_FULL", 11, 50802);
    public static final MediaPipelineBackendResultCode AV_MPB_NOT_READY_TO_RECEIVE_SAMPLES = new MediaPipelineBackendResultCode("AV_MPB_NOT_READY_TO_RECEIVE_SAMPLES", 12, 50803);
    public static final MediaPipelineBackendResultCode AV_MPB_INSUFFICIENT_OUTPUT_PROTECTION = new MediaPipelineBackendResultCode("AV_MPB_INSUFFICIENT_OUTPUT_PROTECTION", 13, ErrorCode.INSUFFICIENT_OUTPUT_PROTECTION);
    public static final MediaPipelineBackendResultCode AV_MPB_DISPLAY_DISCONNECTED = new MediaPipelineBackendResultCode("AV_MPB_DISPLAY_DISCONNECTED", 14, ErrorCode.DISPLAY_DISCONNECTED);
    public static final MediaPipelineBackendResultCode AV_MPB_INVALID_STATE = new MediaPipelineBackendResultCode("AV_MPB_INVALID_STATE", 15, GmtTimeZone.MILLISECONDS_PER_MINUTE);
    public static final MediaPipelineBackendResultCode AV_MPB_INTERNAL_ERROR = new MediaPipelineBackendResultCode("AV_MPB_INTERNAL_ERROR", 16, 60001);
    public static final MediaPipelineBackendResultCode AV_MPB_UNSUPPORTED_TRACK_CONFIG = new MediaPipelineBackendResultCode("AV_MPB_UNSUPPORTED_TRACK_CONFIG", 17, 60002);
    public static final MediaPipelineBackendResultCode AV_MPB_INVALID_CAPABILITY_CONFIG = new MediaPipelineBackendResultCode("AV_MPB_INVALID_CAPABILITY_CONFIG", 18, 60003);
    public static final MediaPipelineBackendResultCode AV_MPB_SURFACE_MULTIPLE_LISTENERS = new MediaPipelineBackendResultCode("AV_MPB_SURFACE_MULTIPLE_LISTENERS", 19, 60004);
    public static final MediaPipelineBackendResultCode AV_MPB_ERROR_AFTER_LIFECYCLE_TRANSITION = new MediaPipelineBackendResultCode("AV_MPB_ERROR_AFTER_LIFECYCLE_TRANSITION", 20, 60005);
    public static final MediaPipelineBackendResultCode AV_MPB_UNSUPPORTED_DAR_VALUE = new MediaPipelineBackendResultCode("AV_MPB_UNSUPPORTED_DAR_VALUE", 21, 60006);
    public static final MediaPipelineBackendResultCode AV_MPB_TASK_THREAD_CRASHED = new MediaPipelineBackendResultCode("AV_MPB_TASK_THREAD_CRASHED", 22, 60007);
    public static final MediaPipelineBackendResultCode AV_MPB_SAMPLE_ADAPTION_FAILURE = new MediaPipelineBackendResultCode("AV_MPB_SAMPLE_ADAPTION_FAILURE", 23, 60100);
    public static final MediaPipelineBackendResultCode AV_MPB_SAMPLE_ADAPTION_FAILURE_DECODER_BUFFER_SIZE_TOO_SMALL = new MediaPipelineBackendResultCode("AV_MPB_SAMPLE_ADAPTION_FAILURE_DECODER_BUFFER_SIZE_TOO_SMALL", 24, 60101);
    public static final MediaPipelineBackendResultCode AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_TOO_SMALL = new MediaPipelineBackendResultCode("AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_TOO_SMALL", 25, 60102);
    public static final MediaPipelineBackendResultCode AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_ZERO = new MediaPipelineBackendResultCode("AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_ZERO", 26, 60103);
    public static final MediaPipelineBackendResultCode AV_MPB_SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SAMPLE_BUFFER_SIZE = new MediaPipelineBackendResultCode("AV_MPB_SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SAMPLE_BUFFER_SIZE", 27, 60104);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_UNSUPPORTED_FREQUENCY = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_UNSUPPORTED_FREQUENCY", 28, 60105);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_UNSUPPORTED_CHANNEL_COUNT = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_UNSUPPORTED_CHANNEL_COUNT", 29, 60106);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_INVALID_PARAMETER_SET = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_INVALID_PARAMETER_SET", 30, 60107);
    public static final MediaPipelineBackendResultCode AV_MPB_READ_SAMPLE_DATA_FAILED_EMPTY_QUEUE = new MediaPipelineBackendResultCode("AV_MPB_READ_SAMPLE_DATA_FAILED_EMPTY_QUEUE", 31, 60108);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_RENDERER_ERROR_TRACK_INIT_FAILED = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_RENDERER_ERROR_TRACK_INIT_FAILED", 32, 62000);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED", 33, 62001);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_RENDERER_ERROR_RESTART_IN_STEREO = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_RENDERER_ERROR_RESTART_IN_STEREO", 34, 62002);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_RENDERER_ERROR_RESTART_IN_STEREO_NON_TUNNEL = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_RENDERER_ERROR_RESTART_IN_STEREO_NON_TUNNEL", 35, 62003);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_DECODER_ERROR = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_DECODER_ERROR", 36, 62004);
    public static final MediaPipelineBackendResultCode AV_MPB_UNKNOWN_AUDIO_CODEC_TYPE = new MediaPipelineBackendResultCode("AV_MPB_UNKNOWN_AUDIO_CODEC_TYPE", 37, 62005);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_DECODER_NOT_FOUND = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_DECODER_NOT_FOUND", 38, 62006);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_DECODER_INITIALIZE_ERROR = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_DECODER_INITIALIZE_ERROR", 39, 62007);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECODER_ERROR = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECODER_ERROR", 40, 62008);
    public static final MediaPipelineBackendResultCode AV_MPB_UNKNOWN_VIDEO_CODEC_TYPE = new MediaPipelineBackendResultCode("AV_MPB_UNKNOWN_VIDEO_CODEC_TYPE", 41, 62009);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECODER_NOT_FOUND = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECODER_NOT_FOUND", 42, 62010);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECODER_INITIALIZE_ERROR = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECODER_INITIALIZE_ERROR", 43, 62011);
    public static final MediaPipelineBackendResultCode AV_MPB_NULL_OR_INVALID_VIDEO_SURFACE = new MediaPipelineBackendResultCode("AV_MPB_NULL_OR_INVALID_VIDEO_SURFACE", 44, 62012);
    public static final MediaPipelineBackendResultCode AV_MPB_SET_VIDEO_OUTPUT_SURFACE_ERROR = new MediaPipelineBackendResultCode("AV_MPB_SET_VIDEO_OUTPUT_SURFACE_ERROR", 45, 62013);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECODER_STALL = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECODER_STALL", 46, 62014);
    public static final MediaPipelineBackendResultCode AV_MPB_EXTERNAL_SURFACE_KEY_INVALID = new MediaPipelineBackendResultCode("AV_MPB_EXTERNAL_SURFACE_KEY_INVALID", 47, 62015);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_TRACK_INVALID_SAMPLE_RATE = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_TRACK_INVALID_SAMPLE_RATE", 48, 62016);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_TRACK_REDUNDANT_CONFIGURE = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_TRACK_REDUNDANT_CONFIGURE", 49, 62017);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_DECRYPTOR_ERROR = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_DECRYPTOR_ERROR", 50, 60300);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_DECRYPTOR_ERROR_MISSING_KEY = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_DECRYPTOR_ERROR_MISSING_KEY", 51, 60301);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_DECRYPTOR_ERROR_EXPIRED_KEY = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_DECRYPTOR_ERROR_EXPIRED_KEY", 52, 60302);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION", 53, 60304);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECRYPTOR_ERROR = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECRYPTOR_ERROR", 54, 60305);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECRYPTOR_ERROR_MISSING_KEY = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECRYPTOR_ERROR_MISSING_KEY", 55, 60306);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECRYPTOR_ERROR_EXPIRED_KEY = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECRYPTOR_ERROR_EXPIRED_KEY", 56, 60307);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION", 57, 60309);
    public static final MediaPipelineBackendResultCode AV_MPB_DECRYPTION_IV_SIZE_ERROR = new MediaPipelineBackendResultCode("AV_MPB_DECRYPTION_IV_SIZE_ERROR", 58, 60310);
    public static final MediaPipelineBackendResultCode AV_MPB_DECRYPTION_MALFORMED_ENCRYPTION_INFO = new MediaPipelineBackendResultCode("AV_MPB_DECRYPTION_MALFORMED_ENCRYPTION_INFO", 59, 60311);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_OUTPUT_BUFFER_NULL = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_OUTPUT_BUFFER_NULL", 60, 60312);
    public static final MediaPipelineBackendResultCode AV_MPB_AUDIO_PIPELINE_INIT_FAILED = new MediaPipelineBackendResultCode("AV_MPB_AUDIO_PIPELINE_INIT_FAILED", 61, 60400);
    public static final MediaPipelineBackendResultCode AV_MPB_VIDEO_PIPELINE_INIT_FAILED = new MediaPipelineBackendResultCode("AV_MPB_VIDEO_PIPELINE_INIT_FAILED", 62, 60401);
    public static final MediaPipelineBackendResultCode AV_MPB_SET_VIDEO_OUTPUT_POSITION_FAILED = new MediaPipelineBackendResultCode("AV_MPB_SET_VIDEO_OUTPUT_POSITION_FAILED", 63, 60500);
    public static final MediaPipelineBackendResultCode AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_KEY_FORMAT = new MediaPipelineBackendResultCode("AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_KEY_FORMAT", 64, 60600);
    public static final MediaPipelineBackendResultCode AV_MPB_DECODER_QUERY_EXCEPTION_MISSING_VALUE = new MediaPipelineBackendResultCode("AV_MPB_DECODER_QUERY_EXCEPTION_MISSING_VALUE", 65, 60601);
    public static final MediaPipelineBackendResultCode AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_QUERY = new MediaPipelineBackendResultCode("AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_QUERY", 66, 60602);
    public static final MediaPipelineBackendResultCode AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_ATTRIBUTE_KEYS = new MediaPipelineBackendResultCode("AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_ATTRIBUTE_KEYS", 67, 60603);
    public static final MediaPipelineBackendResultCode AV_MPB_DRM_CAPABILITIES_QUERY_EXCEPTION = new MediaPipelineBackendResultCode("AV_MPB_DRM_CAPABILITIES_QUERY_EXCEPTION", 68, 60604);
    public static final MediaPipelineBackendResultCode AV_MPB_PICTURE_MODE_NOT_SUPPORTED = new MediaPipelineBackendResultCode("AV_MPB_PICTURE_MODE_NOT_SUPPORTED", 69, 60700);
    public static final MediaPipelineBackendResultCode AV_MPB_CALIBRATED_MODE_PANEL_TYPE_NOT_FOUND = new MediaPipelineBackendResultCode("AV_MPB_CALIBRATED_MODE_PANEL_TYPE_NOT_FOUND", 70, 60701);
    public static final MediaPipelineBackendResultCode AV_MPB_CALIBRATED_MODE_DEVICE_CAPABILITY_NOT_AVAILABLE = new MediaPipelineBackendResultCode("AV_MPB_CALIBRATED_MODE_DEVICE_CAPABILITY_NOT_AVAILABLE", 71, 60702);
    public static final MediaPipelineBackendResultCode AV_MPB_CALIBRATED_MODE_CALIBRATION_CONFIG_NOT_FOUND = new MediaPipelineBackendResultCode("AV_MPB_CALIBRATED_MODE_CALIBRATION_CONFIG_NOT_FOUND", 72, 60703);
    public static final MediaPipelineBackendResultCode AV_MPB_CONFIG_SET_BUT_CALIBRATED_MODE_DISABLED_BY_USER = new MediaPipelineBackendResultCode("AV_MPB_CONFIG_SET_BUT_CALIBRATED_MODE_DISABLED_BY_USER", 73, 60704);
    public static final MediaPipelineBackendResultCode AV_MPB_SET_CALIBRATED_MODE_FAILED = new MediaPipelineBackendResultCode("AV_MPB_SET_CALIBRATED_MODE_FAILED", 74, 60705);
    public static final MediaPipelineBackendResultCode AV_MPB_SET_REFRESH_RATE_FAILED = new MediaPipelineBackendResultCode("AV_MPB_SET_REFRESH_RATE_FAILED", 75, 607020);
    public static final MediaPipelineBackendResultCode AV_MPB_RESET_REFRESH_RATE_FAILED = new MediaPipelineBackendResultCode("AV_MPB_RESET_REFRESH_RATE_FAILED", 76, 607021);
    public static final MediaPipelineBackendResultCode DRM_CREATE_SYSTEM_UNKNOWN_SCHEME = new MediaPipelineBackendResultCode("DRM_CREATE_SYSTEM_UNKNOWN_SCHEME", 77, 70000);
    public static final MediaPipelineBackendResultCode DRM_CREATE_SYSTEM_UNSUPPORTED_SCHEME = new MediaPipelineBackendResultCode("DRM_CREATE_SYSTEM_UNSUPPORTED_SCHEME", 78, ErrorCode.MPB_INIT_ALREADY_INITIALIZED);
    public static final MediaPipelineBackendResultCode DRM_CREATE_SYSTEM_FAILED_TO_CREATE_MEDIA_DRM = new MediaPipelineBackendResultCode("DRM_CREATE_SYSTEM_FAILED_TO_CREATE_MEDIA_DRM", 79, ErrorCode.MPB_INIT_INTERRUPTED);
    public static final MediaPipelineBackendResultCode DRM_FAILED_TO_CREATE_MEDIA_CRYPTO = new MediaPipelineBackendResultCode("DRM_FAILED_TO_CREATE_MEDIA_CRYPTO", 80, ErrorCode.MPB_INIT_EXCEPTION);
    public static final MediaPipelineBackendResultCode DRM_FAILED_TO_SET_MEDIA_CRYPTO_SESSION = new MediaPipelineBackendResultCode("DRM_FAILED_TO_SET_MEDIA_CRYPTO_SESSION", 81, 70004);
    public static final MediaPipelineBackendResultCode DRM_NULL_MEDIA_DRM_SESSION_ID = new MediaPipelineBackendResultCode("DRM_NULL_MEDIA_DRM_SESSION_ID", 82, 70005);
    public static final MediaPipelineBackendResultCode DRM_OPEN_SESSION_UNKNOWN_FAILURE = new MediaPipelineBackendResultCode("DRM_OPEN_SESSION_UNKNOWN_FAILURE", 83, 70006);
    public static final MediaPipelineBackendResultCode DRM_CLOSE_SESSION_FAILED = new MediaPipelineBackendResultCode("DRM_CLOSE_SESSION_FAILED", 84, 70007);
    public static final MediaPipelineBackendResultCode DRM_SESSION_NOT_MAPPED = new MediaPipelineBackendResultCode("DRM_SESSION_NOT_MAPPED", 85, 70008);
    public static final MediaPipelineBackendResultCode DRM_SESSION_ALREADY_MAPPED = new MediaPipelineBackendResultCode("DRM_SESSION_ALREADY_MAPPED", 86, 70009);
    public static final MediaPipelineBackendResultCode DRM_PERSISTENT_SESSION_ID_NOT_FOUND = new MediaPipelineBackendResultCode("DRM_PERSISTENT_SESSION_ID_NOT_FOUND", 87, 70010);
    public static final MediaPipelineBackendResultCode DRM_CREATE_SESSION_NOT_PROVISIONED = new MediaPipelineBackendResultCode("DRM_CREATE_SESSION_NOT_PROVISIONED", 88, 70011);
    public static final MediaPipelineBackendResultCode DRM_CREATE_MASTER_SESSION_NOT_PROVISIONED = new MediaPipelineBackendResultCode("DRM_CREATE_MASTER_SESSION_NOT_PROVISIONED", 89, 70012);
    public static final MediaPipelineBackendResultCode DRM_GENERATE_KEY_REQUEST_NOT_PROVISIONED = new MediaPipelineBackendResultCode("DRM_GENERATE_KEY_REQUEST_NOT_PROVISIONED", 90, 70013);
    public static final MediaPipelineBackendResultCode DRM_GENERATE_KEY_REQUEST_UNKNOWN_FAILURE = new MediaPipelineBackendResultCode("DRM_GENERATE_KEY_REQUEST_UNKNOWN_FAILURE", 91, 70014);
    public static final MediaPipelineBackendResultCode DRM_PROCESS_RESPONSE_KEY_REQUEST_DENIED = new MediaPipelineBackendResultCode("DRM_PROCESS_RESPONSE_KEY_REQUEST_DENIED", 92, 70015);
    public static final MediaPipelineBackendResultCode DRM_PROCESS_KEY_RESPONSE_NOT_PROVISIONED = new MediaPipelineBackendResultCode("DRM_PROCESS_KEY_RESPONSE_NOT_PROVISIONED", 93, 70016);
    public static final MediaPipelineBackendResultCode DRM_PROCESS_KEY_RESPONSE_UNKNOWN_FAILURE = new MediaPipelineBackendResultCode("DRM_PROCESS_KEY_RESPONSE_UNKNOWN_FAILURE", 94, 70017);
    public static final MediaPipelineBackendResultCode DRM_INVALID_KEY_RESPONSE = new MediaPipelineBackendResultCode("DRM_INVALID_KEY_RESPONSE", 95, 70018);
    public static final MediaPipelineBackendResultCode DRM_RELEASE_KEYS_FAILED = new MediaPipelineBackendResultCode("DRM_RELEASE_KEYS_FAILED", 96, 70019);
    public static final MediaPipelineBackendResultCode DRM_REMOVE_KEYS_FAILED = new MediaPipelineBackendResultCode("DRM_REMOVE_KEYS_FAILED", 97, 70020);
    public static final MediaPipelineBackendResultCode DRM_KEY_RELEASE_SESSION_CLOSE_FAILED = new MediaPipelineBackendResultCode("DRM_KEY_RELEASE_SESSION_CLOSE_FAILED", 98, 70021);
    public static final MediaPipelineBackendResultCode DRM_KEY_REMOVE_SESSION_CLOSE_FAILED = new MediaPipelineBackendResultCode("DRM_KEY_REMOVE_SESSION_CLOSE_FAILED", 99, 70022);
    public static final MediaPipelineBackendResultCode DRM_PROVISION_REQUEST_FAILED = new MediaPipelineBackendResultCode("DRM_PROVISION_REQUEST_FAILED", 100, 70023);
    public static final MediaPipelineBackendResultCode DRM_PROVISION_DENIED_BY_SERVER = new MediaPipelineBackendResultCode("DRM_PROVISION_DENIED_BY_SERVER", ProxyBillingActivity.REQUEST_CODE_IN_APP_MESSAGE_FLOW, 70024);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_NOT_PROVISIONED = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_NOT_PROVISIONED", 102, 70025);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_UNKNOWN_FAILURE = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_UNKNOWN_FAILURE", 103, 70026);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_MALFORMED_RIGHTS_MAP = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_MALFORMED_RIGHTS_MAP", 104, 70027);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_INVALID_DURATIONS = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_INVALID_DURATIONS", LocationRequest.PRIORITY_NO_POWER, 70028);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_LICENSE_EXPIRED = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_LICENSE_EXPIRED", 106, 70029);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_PLAYBACK_CLOCK_EXPIRED = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_PLAYBACK_CLOCK_EXPIRED", 107, 70030);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_LICENSE_CLOCK_EXPIRED = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_LICENSE_CLOCK_EXPIRED", AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 70031);
    public static final MediaPipelineBackendResultCode DRM_LICENSE_STATUS_INVALID_LICENSE_TYPE = new MediaPipelineBackendResultCode("DRM_LICENSE_STATUS_INVALID_LICENSE_TYPE", AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 70032);
    public static final MediaPipelineBackendResultCode DRM_SHUTDOWN_FAILED_TO_RELEASE_MEDIA_DRM = new MediaPipelineBackendResultCode("DRM_SHUTDOWN_FAILED_TO_RELEASE_MEDIA_DRM", ProxyBillingActivity.REQUEST_CODE_FIRST_PARTY_PURCHASE_FLOW, 70033);
    public static final MediaPipelineBackendResultCode DRM_RESOURCE_BUSY = new MediaPipelineBackendResultCode("DRM_RESOURCE_BUSY", 111, 70034);
    public static final MediaPipelineBackendResultCode DRM_SYSTEM_CLOSED = new MediaPipelineBackendResultCode("DRM_SYSTEM_CLOSED", 112, 70035);
    public static final MediaPipelineBackendResultCode DRM_SYSTEM_RESET_REQUIRED = new MediaPipelineBackendResultCode("DRM_SYSTEM_RESET_REQUIRED", 113, 70036);
    public static final MediaPipelineBackendResultCode DRM_KEY_SET_ID_ENCODING_FAILED = new MediaPipelineBackendResultCode("DRM_KEY_SET_ID_ENCODING_FAILED", 114, 70037);
    public static final MediaPipelineBackendResultCode DRM_PERSISTENT_SESSION_ID_DECODING_FAILED = new MediaPipelineBackendResultCode("DRM_PERSISTENT_SESSION_ID_DECODING_FAILED", 115, 70038);
    public static final MediaPipelineBackendResultCode DRM_LOAD_SESSION_RESTORE_KEYS_FAILED = new MediaPipelineBackendResultCode("DRM_LOAD_SESSION_RESTORE_KEYS_FAILED", 116, 70039);
    public static final MediaPipelineBackendResultCode AV_MPB_CANARY_FAILOVER = new MediaPipelineBackendResultCode("AV_MPB_CANARY_FAILOVER", AbstractJsonLexerKt.ESC2C_MAX, 80000);
    public static final MediaPipelineBackendResultCode AV_MPB_FATAL_FAILOVER = new MediaPipelineBackendResultCode("AV_MPB_FATAL_FAILOVER", 118, 80001);
    public static final MediaPipelineBackendResultCode AV_MPB_PERFORMANCE_FAILOVER = new MediaPipelineBackendResultCode("AV_MPB_PERFORMANCE_FAILOVER", 119, 80002);
    public static final MediaPipelineBackendResultCode AV_MPB_HFR_FAILOVER = new MediaPipelineBackendResultCode("AV_MPB_HFR_FAILOVER", 120, 80003);

    public static final /* synthetic */ MediaPipelineBackendResultCode[] $values() {
        return new MediaPipelineBackendResultCode[]{AV_MPB_SUCCESS, AV_MPB_NOT_INITIALIZED, AV_MPB_ALREADY_INITIALIZED, AV_MPB_IS_DESTROYED, AV_MPB_KEY_NOT_FOUND, AV_MPB_VALUE_NOT_SUPPORTED, AV_MPB_CONCURRENT_INSTANCE_CREATION_FAILED, AV_MPB_OVERFLOW, AV_MPB_NOT_MUTABLE, AV_MPB_PICTURE_MODE_NOT_SET, AV_MPB_INVALID_BACKGROUND_OPERATION, AV_MPB_BUFFER_FULL, AV_MPB_NOT_READY_TO_RECEIVE_SAMPLES, AV_MPB_INSUFFICIENT_OUTPUT_PROTECTION, AV_MPB_DISPLAY_DISCONNECTED, AV_MPB_INVALID_STATE, AV_MPB_INTERNAL_ERROR, AV_MPB_UNSUPPORTED_TRACK_CONFIG, AV_MPB_INVALID_CAPABILITY_CONFIG, AV_MPB_SURFACE_MULTIPLE_LISTENERS, AV_MPB_ERROR_AFTER_LIFECYCLE_TRANSITION, AV_MPB_UNSUPPORTED_DAR_VALUE, AV_MPB_TASK_THREAD_CRASHED, AV_MPB_SAMPLE_ADAPTION_FAILURE, AV_MPB_SAMPLE_ADAPTION_FAILURE_DECODER_BUFFER_SIZE_TOO_SMALL, AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_TOO_SMALL, AV_MPB_SAMPLE_ADAPTION_FAILURE_SAMPLE_BUFFER_SIZE_ZERO, AV_MPB_SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SAMPLE_BUFFER_SIZE, AV_MPB_AUDIO_UNSUPPORTED_FREQUENCY, AV_MPB_AUDIO_UNSUPPORTED_CHANNEL_COUNT, AV_MPB_VIDEO_INVALID_PARAMETER_SET, AV_MPB_READ_SAMPLE_DATA_FAILED_EMPTY_QUEUE, AV_MPB_AUDIO_RENDERER_ERROR_TRACK_INIT_FAILED, AV_MPB_AUDIO_RENDERER_ERROR_TRACK_WRITE_FAILED, AV_MPB_AUDIO_RENDERER_ERROR_RESTART_IN_STEREO, AV_MPB_AUDIO_RENDERER_ERROR_RESTART_IN_STEREO_NON_TUNNEL, AV_MPB_AUDIO_DECODER_ERROR, AV_MPB_UNKNOWN_AUDIO_CODEC_TYPE, AV_MPB_AUDIO_DECODER_NOT_FOUND, AV_MPB_AUDIO_DECODER_INITIALIZE_ERROR, AV_MPB_VIDEO_DECODER_ERROR, AV_MPB_UNKNOWN_VIDEO_CODEC_TYPE, AV_MPB_VIDEO_DECODER_NOT_FOUND, AV_MPB_VIDEO_DECODER_INITIALIZE_ERROR, AV_MPB_NULL_OR_INVALID_VIDEO_SURFACE, AV_MPB_SET_VIDEO_OUTPUT_SURFACE_ERROR, AV_MPB_VIDEO_DECODER_STALL, AV_MPB_EXTERNAL_SURFACE_KEY_INVALID, AV_MPB_AUDIO_TRACK_INVALID_SAMPLE_RATE, AV_MPB_AUDIO_TRACK_REDUNDANT_CONFIGURE, AV_MPB_AUDIO_DECRYPTOR_ERROR, AV_MPB_AUDIO_DECRYPTOR_ERROR_MISSING_KEY, AV_MPB_AUDIO_DECRYPTOR_ERROR_EXPIRED_KEY, AV_MPB_AUDIO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION, AV_MPB_VIDEO_DECRYPTOR_ERROR, AV_MPB_VIDEO_DECRYPTOR_ERROR_MISSING_KEY, AV_MPB_VIDEO_DECRYPTOR_ERROR_EXPIRED_KEY, AV_MPB_VIDEO_DECRYPTOR_ERROR_UNSUPPORTED_OPERATION, AV_MPB_DECRYPTION_IV_SIZE_ERROR, AV_MPB_DECRYPTION_MALFORMED_ENCRYPTION_INFO, AV_MPB_AUDIO_OUTPUT_BUFFER_NULL, AV_MPB_AUDIO_PIPELINE_INIT_FAILED, AV_MPB_VIDEO_PIPELINE_INIT_FAILED, AV_MPB_SET_VIDEO_OUTPUT_POSITION_FAILED, AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_KEY_FORMAT, AV_MPB_DECODER_QUERY_EXCEPTION_MISSING_VALUE, AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_QUERY, AV_MPB_DECODER_QUERY_EXCEPTION_INVALID_ATTRIBUTE_KEYS, AV_MPB_DRM_CAPABILITIES_QUERY_EXCEPTION, AV_MPB_PICTURE_MODE_NOT_SUPPORTED, AV_MPB_CALIBRATED_MODE_PANEL_TYPE_NOT_FOUND, AV_MPB_CALIBRATED_MODE_DEVICE_CAPABILITY_NOT_AVAILABLE, AV_MPB_CALIBRATED_MODE_CALIBRATION_CONFIG_NOT_FOUND, AV_MPB_CONFIG_SET_BUT_CALIBRATED_MODE_DISABLED_BY_USER, AV_MPB_SET_CALIBRATED_MODE_FAILED, AV_MPB_SET_REFRESH_RATE_FAILED, AV_MPB_RESET_REFRESH_RATE_FAILED, DRM_CREATE_SYSTEM_UNKNOWN_SCHEME, DRM_CREATE_SYSTEM_UNSUPPORTED_SCHEME, DRM_CREATE_SYSTEM_FAILED_TO_CREATE_MEDIA_DRM, DRM_FAILED_TO_CREATE_MEDIA_CRYPTO, DRM_FAILED_TO_SET_MEDIA_CRYPTO_SESSION, DRM_NULL_MEDIA_DRM_SESSION_ID, DRM_OPEN_SESSION_UNKNOWN_FAILURE, DRM_CLOSE_SESSION_FAILED, DRM_SESSION_NOT_MAPPED, DRM_SESSION_ALREADY_MAPPED, DRM_PERSISTENT_SESSION_ID_NOT_FOUND, DRM_CREATE_SESSION_NOT_PROVISIONED, DRM_CREATE_MASTER_SESSION_NOT_PROVISIONED, DRM_GENERATE_KEY_REQUEST_NOT_PROVISIONED, DRM_GENERATE_KEY_REQUEST_UNKNOWN_FAILURE, DRM_PROCESS_RESPONSE_KEY_REQUEST_DENIED, DRM_PROCESS_KEY_RESPONSE_NOT_PROVISIONED, DRM_PROCESS_KEY_RESPONSE_UNKNOWN_FAILURE, DRM_INVALID_KEY_RESPONSE, DRM_RELEASE_KEYS_FAILED, DRM_REMOVE_KEYS_FAILED, DRM_KEY_RELEASE_SESSION_CLOSE_FAILED, DRM_KEY_REMOVE_SESSION_CLOSE_FAILED, DRM_PROVISION_REQUEST_FAILED, DRM_PROVISION_DENIED_BY_SERVER, DRM_LICENSE_STATUS_NOT_PROVISIONED, DRM_LICENSE_STATUS_UNKNOWN_FAILURE, DRM_LICENSE_STATUS_MALFORMED_RIGHTS_MAP, DRM_LICENSE_STATUS_INVALID_DURATIONS, DRM_LICENSE_STATUS_LICENSE_EXPIRED, DRM_LICENSE_STATUS_PLAYBACK_CLOCK_EXPIRED, DRM_LICENSE_STATUS_LICENSE_CLOCK_EXPIRED, DRM_LICENSE_STATUS_INVALID_LICENSE_TYPE, DRM_SHUTDOWN_FAILED_TO_RELEASE_MEDIA_DRM, DRM_RESOURCE_BUSY, DRM_SYSTEM_CLOSED, DRM_SYSTEM_RESET_REQUIRED, DRM_KEY_SET_ID_ENCODING_FAILED, DRM_PERSISTENT_SESSION_ID_DECODING_FAILED, DRM_LOAD_SESSION_RESTORE_KEYS_FAILED, AV_MPB_CANARY_FAILOVER, AV_MPB_FATAL_FAILOVER, AV_MPB_PERFORMANCE_FAILOVER, AV_MPB_HFR_FAILOVER};
    }

    static {
        MediaPipelineBackendResultCode[] mediaPipelineBackendResultCodeArr$values = $values();
        $VALUES = mediaPipelineBackendResultCodeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(mediaPipelineBackendResultCodeArr$values);
    }

    public MediaPipelineBackendResultCode(String str, int i, int i2) {
        this.resultCode = i2;
    }

    @NotNull
    public static EnumEntries<MediaPipelineBackendResultCode> getEntries() {
        return $ENTRIES;
    }

    public static MediaPipelineBackendResultCode valueOf(String str) {
        return (MediaPipelineBackendResultCode) Enum.valueOf(MediaPipelineBackendResultCode.class, str);
    }

    public static MediaPipelineBackendResultCode[] values() {
        return (MediaPipelineBackendResultCode[]) $VALUES.clone();
    }

    public final int getResultCode() {
        return this.resultCode;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        return name() + " (" + this.resultCode + ")";
    }
}
