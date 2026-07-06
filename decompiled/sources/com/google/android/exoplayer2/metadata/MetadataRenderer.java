package com.google.android.exoplayer2.metadata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final class MetadataRenderer extends BaseRenderer implements Handler.Callback {
    public static final int MSG_INVOKE_RENDERER = 0;
    public static final String TAG = "MetadataRenderer";
    public final MetadataInputBuffer buffer;

    @Nullable
    public MetadataDecoder decoder;
    public final MetadataDecoderFactory decoderFactory;
    public boolean inputStreamEnded;
    public final MetadataOutput output;

    @Nullable
    public final Handler outputHandler;
    public final boolean outputMetadataEarly;
    public boolean outputStreamEnded;
    public long outputStreamOffsetUs;

    @Nullable
    public Metadata pendingMetadata;
    public long subsampleOffsetUs;

    public MetadataRenderer(MetadataOutput metadataOutput, @Nullable Looper looper) {
        this(metadataOutput, looper, MetadataDecoderFactory.DEFAULT, false);
    }

    public final void decodeWrappedMetadata(Metadata metadata, List<Metadata.Entry> list) {
        int i = 0;
        while (true) {
            Metadata.Entry[] entryArr = metadata.entries;
            if (i >= entryArr.length) {
                return;
            }
            Format wrappedMetadataFormat = entryArr[i].getWrappedMetadataFormat();
            if (wrappedMetadataFormat == null || !this.decoderFactory.supportsFormat(wrappedMetadataFormat)) {
                list.add(metadata.entries[i]);
            } else {
                MetadataDecoder metadataDecoderCreateDecoder = this.decoderFactory.createDecoder(wrappedMetadataFormat);
                byte[] wrappedMetadataBytes = metadata.entries[i].getWrappedMetadataBytes();
                wrappedMetadataBytes.getClass();
                this.buffer.clear();
                this.buffer.ensureSpaceForWrite(wrappedMetadataBytes.length);
                ByteBuffer byteBuffer = this.buffer.data;
                Util.castNonNull(byteBuffer);
                byteBuffer.put(wrappedMetadataBytes);
                this.buffer.flip();
                Metadata metadataDecode = metadataDecoderCreateDecoder.decode(this.buffer);
                if (metadataDecode != null) {
                    decodeWrappedMetadata(metadataDecode, list);
                }
            }
            i++;
        }
    }

    @Override // com.google.android.exoplayer2.Renderer, com.google.android.exoplayer2.RendererCapabilities
    public String getName() {
        return "MetadataRenderer";
    }

    @SideEffectFree
    public final long getPresentationTimeUs(long j) {
        Assertions.checkState(j != -9223372036854775807L);
        Assertions.checkState(this.outputStreamOffsetUs != -9223372036854775807L);
        return j - this.outputStreamOffsetUs;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            throw new IllegalStateException();
        }
        invokeRendererInternal((Metadata) message.obj);
        return true;
    }

    public final void invokeRenderer(Metadata metadata) {
        Handler handler = this.outputHandler;
        if (handler != null) {
            handler.obtainMessage(0, metadata).sendToTarget();
        } else {
            invokeRendererInternal(metadata);
        }
    }

    public final void invokeRendererInternal(Metadata metadata) {
        this.output.onMetadata(metadata);
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // com.google.android.exoplayer2.Renderer
    public boolean isReady() {
        return true;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.pendingMetadata = null;
        this.decoder = null;
        this.outputStreamOffsetUs = -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) {
        this.pendingMetadata = null;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
    }

    @Override // com.google.android.exoplayer2.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j, long j2) {
        this.decoder = this.decoderFactory.createDecoder(formatArr[0]);
        Metadata metadata = this.pendingMetadata;
        if (metadata != null) {
            this.pendingMetadata = metadata.copyWithPresentationTimeUs((metadata.presentationTimeUs + this.outputStreamOffsetUs) - j2);
        }
        this.outputStreamOffsetUs = j2;
    }

    public final boolean outputMetadata(long j) {
        boolean z;
        Metadata metadata = this.pendingMetadata;
        if (metadata == null || (!this.outputMetadataEarly && metadata.presentationTimeUs > getPresentationTimeUs(j))) {
            z = false;
        } else {
            invokeRenderer(this.pendingMetadata);
            this.pendingMetadata = null;
            z = true;
        }
        if (this.inputStreamEnded && this.pendingMetadata == null) {
            this.outputStreamEnded = true;
        }
        return z;
    }

    public final void readMetadata() {
        if (this.inputStreamEnded || this.pendingMetadata != null) {
            return;
        }
        this.buffer.clear();
        FormatHolder formatHolder = getFormatHolder();
        int source = readSource(formatHolder, this.buffer, 0);
        if (source != -4) {
            if (source == -5) {
                Format format = formatHolder.format;
                format.getClass();
                this.subsampleOffsetUs = format.subsampleOffsetUs;
                return;
            }
            return;
        }
        if (this.buffer.getFlag(4)) {
            this.inputStreamEnded = true;
            return;
        }
        MetadataInputBuffer metadataInputBuffer = this.buffer;
        metadataInputBuffer.subsampleOffsetUs = this.subsampleOffsetUs;
        metadataInputBuffer.flip();
        MetadataDecoder metadataDecoder = this.decoder;
        Util.castNonNull(metadataDecoder);
        Metadata metadataDecode = metadataDecoder.decode(this.buffer);
        if (metadataDecode != null) {
            ArrayList arrayList = new ArrayList(metadataDecode.entries.length);
            decodeWrappedMetadata(metadataDecode, arrayList);
            if (arrayList.isEmpty()) {
                return;
            }
            this.pendingMetadata = new Metadata(getPresentationTimeUs(this.buffer.timeUs), arrayList);
        }
    }

    @Override // com.google.android.exoplayer2.Renderer
    public void render(long j, long j2) {
        boolean zOutputMetadata = true;
        while (zOutputMetadata) {
            readMetadata();
            zOutputMetadata = outputMetadata(j);
        }
    }

    @Override // com.google.android.exoplayer2.RendererCapabilities
    public int supportsFormat(Format format) {
        if (this.decoderFactory.supportsFormat(format)) {
            return RendererCapabilities.CC.create(format.cryptoType == 0 ? 4 : 2, 0, 0);
        }
        return RendererCapabilities.CC.create(0, 0, 0);
    }

    public MetadataRenderer(MetadataOutput metadataOutput, @Nullable Looper looper, MetadataDecoderFactory metadataDecoderFactory) {
        this(metadataOutput, looper, metadataDecoderFactory, false);
    }

    public MetadataRenderer(MetadataOutput metadataOutput, @Nullable Looper looper, MetadataDecoderFactory metadataDecoderFactory, boolean z) {
        super(5);
        metadataOutput.getClass();
        this.output = metadataOutput;
        this.outputHandler = looper == null ? null : Util.createHandler(looper, this);
        metadataDecoderFactory.getClass();
        this.decoderFactory = metadataDecoderFactory;
        this.outputMetadataEarly = z;
        this.buffer = new MetadataInputBuffer();
        this.outputStreamOffsetUs = -9223372036854775807L;
    }
}
