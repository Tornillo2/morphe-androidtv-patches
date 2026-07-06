package com.google.common.hash;

import com.google.common.annotations.Beta;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
@Beta
public final class Funnels {

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum IntegerFunnel implements Funnel<Integer> {
        INSTANCE;

        public static /* synthetic */ IntegerFunnel[] $values() {
            return new IntegerFunnel[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.integerFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Integer from, PrimitiveSink into) {
            into.putInt(from.intValue());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LongFunnel implements Funnel<Long> {
        INSTANCE;

        public static /* synthetic */ LongFunnel[] $values() {
            return new LongFunnel[]{INSTANCE};
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.longFunnel()";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Long from, PrimitiveSink into) {
            into.putLong(from.longValue());
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SequentialFunnel<E> implements Funnel<Iterable<? extends E>> {
        public final Funnel<E> elementFunnel;

        public SequentialFunnel(Funnel<E> elementFunnel) {
            elementFunnel.getClass();
            this.elementFunnel = elementFunnel;
        }

        public boolean equals(Object o) {
            if (o instanceof SequentialFunnel) {
                return this.elementFunnel.equals(((SequentialFunnel) o).elementFunnel);
            }
            return false;
        }

        public int hashCode() {
            return SequentialFunnel.class.hashCode() ^ this.elementFunnel.hashCode();
        }

        public String toString() {
            return "Funnels.sequentialFunnel(" + this.elementFunnel + ")";
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(Iterable<? extends E> from, PrimitiveSink into) {
            Iterator<? extends E> it = from.iterator();
            while (it.hasNext()) {
                this.elementFunnel.funnel(it.next(), into);
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class SinkAsStream extends OutputStream {
        public final PrimitiveSink sink;

        public SinkAsStream(PrimitiveSink sink) {
            sink.getClass();
            this.sink = sink;
        }

        public String toString() {
            return "Funnels.asOutputStream(" + this.sink + ")";
        }

        @Override // java.io.OutputStream
        public void write(int b) {
            this.sink.putByte((byte) b);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bytes) {
            this.sink.putBytes(bytes);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bytes, int off, int len) {
            this.sink.putBytes(bytes, off, len);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class StringCharsetFunnel implements Funnel<CharSequence> {
        public final Charset charset;

        /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
        public static class SerializedForm implements Serializable {
            public static final long serialVersionUID = 0;
            public final String charsetCanonicalName;

            public SerializedForm(Charset charset) {
                this.charsetCanonicalName = charset.name();
            }

            public final Object readResolve() {
                return new StringCharsetFunnel(Charset.forName(this.charsetCanonicalName));
            }
        }

        public StringCharsetFunnel(Charset charset) {
            charset.getClass();
            this.charset = charset;
        }

        public boolean equals(Object o) {
            if (o instanceof StringCharsetFunnel) {
                return this.charset.equals(((StringCharsetFunnel) o).charset);
            }
            return false;
        }

        public int hashCode() {
            return StringCharsetFunnel.class.hashCode() ^ this.charset.hashCode();
        }

        public final void readObject(ObjectInputStream stream) throws InvalidObjectException {
            throw new InvalidObjectException("Use SerializedForm");
        }

        public String toString() {
            return "Funnels.stringFunnel(" + this.charset.name() + ")";
        }

        public Object writeReplace() {
            return new SerializedForm(this.charset);
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(CharSequence from, PrimitiveSink into) {
            into.putString(from, this.charset);
        }
    }

    public static OutputStream asOutputStream(PrimitiveSink sink) {
        return new SinkAsStream(sink);
    }

    public static Funnel<byte[]> byteArrayFunnel() {
        return ByteArrayFunnel.INSTANCE;
    }

    public static Funnel<Integer> integerFunnel() {
        return IntegerFunnel.INSTANCE;
    }

    public static Funnel<Long> longFunnel() {
        return LongFunnel.INSTANCE;
    }

    public static <E> Funnel<Iterable<? extends E>> sequentialFunnel(Funnel<E> elementFunnel) {
        return new SequentialFunnel(elementFunnel);
    }

    public static Funnel<CharSequence> stringFunnel(Charset charset) {
        return new StringCharsetFunnel(charset);
    }

    public static Funnel<CharSequence> unencodedCharsFunnel() {
        return UnencodedCharsFunnel.INSTANCE;
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum ByteArrayFunnel implements Funnel<byte[]> {
        INSTANCE;

        public static /* synthetic */ ByteArrayFunnel[] $values() {
            return new ByteArrayFunnel[]{INSTANCE};
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(byte[] from, PrimitiveSink into) {
            into.putBytes(from);
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.byteArrayFunnel()";
        }

        /* JADX INFO: renamed from: funnel, reason: avoid collision after fix types in other method */
        public void funnel2(byte[] from, PrimitiveSink into) {
            into.putBytes(from);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum UnencodedCharsFunnel implements Funnel<CharSequence> {
        INSTANCE;

        public static /* synthetic */ UnencodedCharsFunnel[] $values() {
            return new UnencodedCharsFunnel[]{INSTANCE};
        }

        @Override // com.google.common.hash.Funnel
        public void funnel(CharSequence from, PrimitiveSink into) {
            into.putUnencodedChars(from);
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Funnels.unencodedCharsFunnel()";
        }

        /* JADX INFO: renamed from: funnel, reason: avoid collision after fix types in other method */
        public void funnel2(CharSequence from, PrimitiveSink into) {
            into.putUnencodedChars(from);
        }
    }
}
