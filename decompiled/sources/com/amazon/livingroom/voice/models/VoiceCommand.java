package com.amazon.livingroom.voice.models;

import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Serializable
public abstract class VoiceCommand {

    @NotNull
    public static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;

    @JvmField
    @NotNull
    public static final Lazy<KSerializer<Object>>[] $childSerializers;

    @NotNull
    public static final Companion Companion = new Companion();

    @NotNull
    public final VoiceCommandName name;

    @NotNull
    public final VoiceCommandNamespace namespace;

    @NotNull
    public final String namespaceVersion;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Companion {
        public Companion() {
        }

        public final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer) VoiceCommand.$cachedSerializer$delegate.getValue();
        }

        @NotNull
        public final KSerializer<VoiceCommand> serializer() {
            return get$cachedSerializer();
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    static {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        $childSerializers = new Lazy[]{LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new VoiceCommand$$ExternalSyntheticLambda0()), null, LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new VoiceCommand$$ExternalSyntheticLambda1())};
        $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new VoiceCommand$$ExternalSyntheticLambda2());
    }

    public /* synthetic */ VoiceCommand(VoiceCommandNamespace voiceCommandNamespace, String str, VoiceCommandName voiceCommandName, DefaultConstructorMarker defaultConstructorMarker) {
        this(voiceCommandNamespace, str, voiceCommandName);
    }

    public static final KSerializer _init_$_anonymous_$1() {
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(VoiceCommand.class);
        ReflectionFactory reflectionFactory = Reflection.factory;
        return new SealedClassSerializer("com.amazon.livingroom.voice.models.VoiceCommand", orCreateKotlinClass, new KClass[]{reflectionFactory.getOrCreateKotlinClass(DisableCaptionsCommand.class), reflectionFactory.getOrCreateKotlinClass(EnableCaptionsCommand.class), reflectionFactory.getOrCreateKotlinClass(FastForwardVoiceCommand.class), reflectionFactory.getOrCreateKotlinClass(PauseCommand.class), reflectionFactory.getOrCreateKotlinClass(PlayCommand.class), reflectionFactory.getOrCreateKotlinClass(RewindVoiceCommand.class), reflectionFactory.getOrCreateKotlinClass(SeekVoiceCommand.class), reflectionFactory.getOrCreateKotlinClass(SkipToNextCommand.class), reflectionFactory.getOrCreateKotlinClass(StopCommand.class)}, new KSerializer[]{DisableCaptionsCommand$$serializer.INSTANCE, EnableCaptionsCommand$$serializer.INSTANCE, FastForwardVoiceCommand$$serializer.INSTANCE, PauseCommand$$serializer.INSTANCE, PlayCommand$$serializer.INSTANCE, RewindVoiceCommand$$serializer.INSTANCE, SeekVoiceCommand$$serializer.INSTANCE, SkipToNextCommand$$serializer.INSTANCE, StopCommand$$serializer.INSTANCE}, new Annotation[0]);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self(VoiceCommand voiceCommand, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, lazyArr[0].getValue(), voiceCommand.namespace);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, voiceCommand.namespaceVersion);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 2, lazyArr[2].getValue(), voiceCommand.name);
    }

    @NotNull
    public final VoiceCommandName getName() {
        return this.name;
    }

    @NotNull
    public final VoiceCommandNamespace getNamespace() {
        return this.namespace;
    }

    @NotNull
    public final String getNamespaceVersion() {
        return this.namespaceVersion;
    }

    public /* synthetic */ VoiceCommand(int i, VoiceCommandNamespace voiceCommandNamespace, String str, VoiceCommandName voiceCommandName, SerializationConstructorMarker serializationConstructorMarker) {
        this.namespace = voiceCommandNamespace;
        this.namespaceVersion = str;
        this.name = voiceCommandName;
    }

    public VoiceCommand(VoiceCommandNamespace voiceCommandNamespace, String str, VoiceCommandName voiceCommandName) {
        this.namespace = voiceCommandNamespace;
        this.namespaceVersion = str;
        this.name = voiceCommandName;
    }
}
