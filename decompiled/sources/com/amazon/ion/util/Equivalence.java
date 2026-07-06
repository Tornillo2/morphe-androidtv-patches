package com.amazon.ion.util;

import com.amazon.ion.IonException;
import com.amazon.ion.IonLob;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.impl._Private_IonConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class Equivalence {
    public static final Configuration NON_STRICT_CONFIGURATION;
    public static final boolean PUBLIC_COMPARISON_API = false;
    public static final Configuration STRICT_CONFIGURATION;
    public final Configuration configuration;

    /* JADX INFO: renamed from: com.amazon.ion.util.Equivalence$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BOOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DECIMAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.TIMESTAMP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.BLOB.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.CLOB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.STRUCT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DATAGRAM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Builder {
        public boolean isStrict = true;
        public Double epsilon = null;

        public Equivalence build() {
            return new Equivalence(new Configuration(this));
        }

        public Builder withEpsilon(double d) {
            this.epsilon = Double.valueOf(d);
            return this;
        }

        public Builder withStrict(boolean z) {
            this.isStrict = z;
            return this;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Configuration {
        public final Double epsilon;
        public final boolean isStrict;

        public Configuration(Builder builder) {
            this.isStrict = builder.isStrict;
            this.epsilon = builder.epsilon;
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class Field {
        public final Configuration configuration;
        public final String name;
        public int occurrences;
        public final IonValue value;

        public Field(IonValue ionValue, Configuration configuration) {
            SymbolToken fieldNameSymbol = ionValue.getFieldNameSymbol();
            String text = fieldNameSymbol.getText();
            if (text == null) {
                text = _Private_IonConstants.UNKNOWN_SYMBOL_TEXT_PREFIX + fieldNameSymbol.getSid();
            }
            this.name = text;
            this.value = ionValue;
            this.configuration = configuration;
            this.occurrences = 0;
        }

        public static /* synthetic */ int access$308(Field field) {
            int i = field.occurrences;
            field.occurrences = i + 1;
            return i;
        }

        public static /* synthetic */ int access$310(Field field) {
            int i = field.occurrences;
            field.occurrences = i - 1;
            return i;
        }

        public boolean equals(Object obj) {
            Field field = (Field) obj;
            return this.name.equals(field.name) && Equivalence.ionEqualsImpl(this.value, field.value, this.configuration);
        }

        public int hashCode() {
            return this.name.hashCode();
        }
    }

    static {
        Builder builder = new Builder();
        builder.isStrict = true;
        STRICT_CONFIGURATION = new Configuration(builder);
        Builder builder2 = new Builder();
        builder2.isStrict = false;
        NON_STRICT_CONFIGURATION = new Configuration(builder2);
    }

    public /* synthetic */ Equivalence(Configuration configuration, AnonymousClass1 anonymousClass1) {
        this(configuration);
    }

    public static int compareAnnotations(SymbolToken[] symbolTokenArr, SymbolToken[] symbolTokenArr2) {
        int length = symbolTokenArr.length;
        int length2 = length - symbolTokenArr2.length;
        if (length2 == 0) {
            for (int i = 0; length2 == 0 && i < length; i++) {
                length2 = compareSymbolTokens(symbolTokenArr[i], symbolTokenArr2[i]);
            }
        }
        return length2;
    }

    public static int compareLobContents(IonLob ionLob, IonLob ionLob2) {
        int iByteSize = ionLob.byteSize() - ionLob2.byteSize();
        if (iByteSize != 0) {
            return iByteSize;
        }
        InputStream inputStreamNewInputStream = ionLob.newInputStream();
        InputStream inputStreamNewInputStream2 = ionLob2.newInputStream();
        while (iByteSize == 0) {
            try {
                try {
                    try {
                        int i = inputStreamNewInputStream.read();
                        int i2 = inputStreamNewInputStream2.read();
                        if (i == -1 || i2 == -1) {
                            if (i != -1) {
                                iByteSize = 1;
                            }
                            if (i2 != -1) {
                                iByteSize = -1;
                            }
                            inputStreamNewInputStream.close();
                            inputStreamNewInputStream2.close();
                            return iByteSize;
                        }
                        iByteSize = i - i2;
                    } catch (Throwable th) {
                        inputStreamNewInputStream2.close();
                        throw th;
                    }
                } catch (IOException e) {
                    throw new IonException(e.getMessage(), e);
                }
            } catch (Throwable th2) {
                inputStreamNewInputStream.close();
                throw th2;
            }
        }
        inputStreamNewInputStream.close();
        inputStreamNewInputStream2.close();
        return iByteSize;
    }

    public static int compareSequences(IonSequence ionSequence, IonSequence ionSequence2, Configuration configuration) {
        int size = ionSequence.size() - ionSequence2.size();
        if (size == 0) {
            Iterator<IonValue> it = ionSequence.iterator();
            Iterator<IonValue> it2 = ionSequence2.iterator();
            while (it.hasNext() && (size = ionCompareToImpl(it.next(), it2.next(), configuration)) == 0) {
            }
        }
        return size;
    }

    public static int compareStructs(IonStruct ionStruct, IonStruct ionStruct2, Configuration configuration) {
        int i;
        int size = ionStruct.size() - ionStruct2.size();
        if (size == 0) {
            Map<Field, Field> mapConvertToMultiSet = convertToMultiSet(ionStruct, configuration);
            Iterator<IonValue> it = ionStruct2.iterator();
            while (it.hasNext()) {
                Field field = (Field) ((HashMap) mapConvertToMultiSet).get(new Field(it.next(), configuration));
                if (field == null || (i = field.occurrences) == 0) {
                    return -1;
                }
                field.occurrences = i - 1;
            }
        }
        return size;
    }

    public static int compareSymbolTokens(SymbolToken symbolToken, SymbolToken symbolToken2) {
        int sid;
        int sid2;
        String text = symbolToken.getText();
        String text2 = symbolToken2.getText();
        if (text != null && text2 != null) {
            return text.compareTo(text2);
        }
        if (text != null) {
            return 1;
        }
        if (text2 == null && (sid = symbolToken.getSid()) >= (sid2 = symbolToken2.getSid())) {
            return sid > sid2 ? 1 : 0;
        }
        return -1;
    }

    public static final Map<Field, Field> convertToMultiSet(IonStruct ionStruct, Configuration configuration) {
        HashMap map = new HashMap();
        Iterator<IonValue> it = ionStruct.iterator();
        while (it.hasNext()) {
            Field field = new Field(it.next(), configuration);
            Field field2 = (Field) map.put(field, field);
            if (field2 != null) {
                field.occurrences = field2.occurrences;
            }
            field.occurrences++;
        }
        return map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int ionCompareToImpl(com.amazon.ion.IonValue r10, com.amazon.ion.IonValue r11, com.amazon.ion.util.Equivalence.Configuration r12) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.util.Equivalence.ionCompareToImpl(com.amazon.ion.IonValue, com.amazon.ion.IonValue, com.amazon.ion.util.Equivalence$Configuration):int");
    }

    public static boolean ionEquals(IonValue ionValue, IonValue ionValue2) {
        return ionEqualsImpl(ionValue, ionValue2, STRICT_CONFIGURATION);
    }

    public static boolean ionEqualsByContent(IonValue ionValue, IonValue ionValue2) {
        return ionEqualsImpl(ionValue, ionValue2, NON_STRICT_CONFIGURATION);
    }

    public static boolean ionEqualsImpl(IonValue ionValue, IonValue ionValue2, Configuration configuration) {
        return ionCompareToImpl(ionValue, ionValue2, configuration) == 0;
    }

    public boolean ionValueEquals(IonValue ionValue, IonValue ionValue2) {
        return ionEqualsImpl(ionValue, ionValue2, this.configuration);
    }

    public Equivalence(Configuration configuration) {
        this.configuration = configuration;
    }
}
