package com.amazon.livingroom.voice.models;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class NonSerialisableVoiceCommand extends VoiceCommand {
    public NonSerialisableVoiceCommand() {
        super(VoiceCommandNamespace.TRANSPORT_CONTROLS, VoiceCommandKt.TRANSPORT_CONTROLS_PAYLOAD_VERSION, VoiceCommandName.PLAY);
    }
}
