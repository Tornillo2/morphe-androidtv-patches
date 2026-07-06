package com.amazon.ion.impl;

import com.amazon.avod.mpb.media.ExternalFourCCMapper;
import com.amazon.ion.IonException;
import com.amazon.ion.IonTextReader;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.IonReaderTextRawTokensX;
import com.amazon.ion.impl.UnifiedSavePointManagerX;
import com.amazon.ion.impl._Private_ScalarConversions;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public abstract class IonReaderTextRawX implements IonTextReader {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ACTION_EAT_COMMA = 11;
    public static final int ACTION_EOF = 15;
    public static final int ACTION_FINISH_CONTAINER = 12;
    public static final int ACTION_FINISH_DATAGRAM = 14;
    public static final int ACTION_FINISH_LOB = 13;
    public static final int ACTION_LOAD_ANNOTATION = 2;
    public static final int ACTION_LOAD_FIELD_NAME = 1;
    public static final int ACTION_LOAD_SCALAR = 8;
    public static final int ACTION_MINUS_INF = 10;
    public static final int ACTION_NOT_DEFINED = 0;
    public static final int ACTION_PLUS_INF = 9;
    public static final int ACTION_START_LIST = 4;
    public static final int ACTION_START_LOB = 6;
    public static final int ACTION_START_SEXP = 5;
    public static final int ACTION_START_STRUCT = 3;
    public static final int ACTION_count = 16;
    public static final int DEFAULT_ANNOTATION_COUNT = 5;
    public static final int DEFAULT_STACK_DEPTH = 10;
    public static final int STATE_AFTER_VALUE_CONTENTS = 10;
    public static final int STATE_BEFORE_ANNOTATION_CONTAINED = 1;
    public static final int STATE_BEFORE_ANNOTATION_DATAGRAM = 0;
    public static final int STATE_BEFORE_ANNOTATION_SEXP = 2;
    public static final int STATE_BEFORE_FIELD_NAME = 3;
    public static final int STATE_BEFORE_VALUE_CONTENT = 4;
    public static final int STATE_BEFORE_VALUE_CONTENT_SEXP = 5;
    public static final int STATE_EOF = 11;
    public static final int STATE_IN_BLOB_CONTENT = 9;
    public static final int STATE_IN_CLOB_DOUBLE_QUOTED_CONTENT = 7;
    public static final int STATE_IN_CLOB_TRIPLE_QUOTED_CONTENT = 8;
    public static final int STATE_IN_LONG_STRING = 6;
    public static final int STATE_MAX = 11;
    public static final int[][] TransitionActions = makeTransitionActionArray();
    public static final int[] TransitionActions2 = makeTransition2ActionArray();
    public static final int UNKNOWN_SIZE = -1;
    public static final boolean _debug = false;
    public int _annotation_count;
    public SymbolToken[] _annotations;
    public boolean _container_is_struct;
    public boolean _container_prohibits_commas;
    public int _container_state_top;
    public StringBuilder _current_value_buffer;
    public boolean _current_value_buffer_loaded;
    public UnifiedSavePointManagerX.SavePoint _current_value_save_point;
    public boolean _current_value_save_point_loaded;
    public boolean _eof;
    public String _field_name;
    public boolean _has_next_called;
    public int _lob_actual_len;
    public byte[] _lob_bytes;
    public LOB_STATE _lob_loaded;
    public int _lob_token;
    public long _lob_value_position;
    public boolean _lob_value_set;
    public IonType _null_type;
    public IonReaderTextRawTokensX _scanner;
    public int _state;
    public int _value_keyword;
    public long _value_start_column;
    public long _value_start_line;
    public long _value_start_offset;
    public IonType _value_type;
    public IonType[] _container_state_stack = new IonType[10];
    public int _field_name_sid = -1;
    public _Private_ScalarConversions.ValueVariant _v = new _Private_ScalarConversions.ValueVariant();
    public IonType _nesting_parent = null;

    /* JADX INFO: renamed from: com.amazon.ion.impl.IonReaderTextRawX$1, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SEXP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.DATAGRAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class IonReaderTextParsingException extends IonException {
        public static final long serialVersionUID = 1;

        public IonReaderTextParsingException(String str) {
            super(str);
        }

        public IonReaderTextParsingException(String str, Exception exc) {
            super(str, exc);
        }

        public IonReaderTextParsingException(Exception exc) {
            super(exc);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public enum LOB_STATE {
        EMPTY,
        READ,
        FINISHED
    }

    public static int[] makeTransition2ActionArray() {
        int[] iArr = new int[324];
        for (int i = 0; i < 12; i++) {
            for (int i2 = 0; i2 < 27; i2++) {
                iArr[(i * 27) + i2] = TransitionActions[i][i2];
            }
        }
        return iArr;
    }

    public static final int[][] makeTransitionActionArray() {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 12, 27);
        int[] iArr2 = iArr[0];
        iArr2[0] = 14;
        iArr2[1] = 8;
        iArr2[2] = 8;
        iArr2[26] = 8;
        iArr2[3] = 8;
        iArr2[4] = 8;
        iArr2[5] = 8;
        iArr2[6] = 9;
        iArr2[7] = 10;
        iArr2[8] = 8;
        iArr2[12] = 8;
        iArr2[13] = 8;
        iArr2[9] = 2;
        iArr2[10] = 2;
        iArr2[18] = 5;
        iArr2[20] = 3;
        iArr2[22] = 4;
        iArr2[24] = 6;
        for (int i = 0; i < 27; i++) {
            int[] iArr3 = iArr[1];
            int[] iArr4 = iArr[0];
            iArr3[i] = iArr4[i];
            iArr[2][i] = iArr4[i];
            iArr[4][i] = iArr4[i];
            iArr[5][i] = iArr4[i];
        }
        int[] iArr5 = iArr[1];
        iArr5[0] = 0;
        iArr5[19] = 12;
        iArr5[21] = 0;
        iArr5[23] = 12;
        int[] iArr6 = iArr[2];
        iArr6[0] = 0;
        iArr6[11] = 8;
        iArr6[14] = 8;
        iArr6[19] = 12;
        iArr6[21] = 12;
        iArr6[23] = 12;
        int[] iArr7 = iArr[4];
        iArr7[0] = 0;
        iArr7[9] = 8;
        iArr7[10] = 8;
        int[] iArr8 = iArr[5];
        iArr8[0] = 0;
        iArr8[9] = 8;
        iArr8[10] = 8;
        iArr8[11] = 8;
        int[] iArr9 = iArr[3];
        iArr9[0] = 0;
        iArr9[9] = 1;
        iArr9[10] = 1;
        iArr9[12] = 1;
        iArr9[13] = 1;
        iArr9[19] = 12;
        iArr9[21] = 12;
        iArr9[23] = 12;
        int[] iArr10 = iArr[10];
        iArr10[15] = 11;
        iArr10[19] = 12;
        iArr10[21] = 12;
        iArr10[23] = 12;
        iArr[7][21] = 13;
        iArr[8][21] = 13;
        iArr[9][21] = 13;
        for (int i2 = 0; i2 < 27; i2++) {
            iArr[11][i2] = 15;
        }
        return iArr;
    }

    public final void append_annotation(SymbolToken symbolToken) {
        SymbolToken[] symbolTokenArr = this._annotations;
        int length = symbolTokenArr.length;
        if (this._annotation_count >= length) {
            SymbolToken[] symbolTokenArr2 = new SymbolToken[length * 2];
            System.arraycopy(symbolTokenArr, 0, symbolTokenArr2, 0, length);
            this._annotations = symbolTokenArr2;
        }
        SymbolToken[] symbolTokenArr3 = this._annotations;
        int i = this._annotation_count;
        this._annotation_count = i + 1;
        symbolTokenArr3[i] = symbolToken;
    }

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public abstract BigInteger bigIntegerValue();

    public final void check_container_close(IonType ionType, int i, int i2) {
        if (i2 == i) {
            return;
        }
        throw new IonException(ionType.toString().toLowerCase() + " closed by " + IonTokenConstsX.describeToken(i2) + this._scanner.input_position());
    }

    public final void clear_annotation_list() {
        this._annotation_count = 0;
    }

    public final void clear_current_value_buffer() {
        if (this._current_value_buffer_loaded) {
            this._current_value_buffer.setLength(0);
            this._current_value_buffer_loaded = false;
        }
        if (this._current_value_save_point_loaded) {
            this._current_value_save_point.clear();
            this._current_value_save_point_loaded = false;
        }
    }

    public final void clear_fieldname() {
        this._field_name = null;
        this._field_name_sid = -1;
    }

    public final void clear_value() {
        this._value_type = null;
        this._null_type = null;
        if (this._lob_value_set) {
            this._lob_value_set = false;
            this._lob_value_position = 0L;
        }
        LOB_STATE lob_state = LOB_STATE.EMPTY;
        if (!lob_state.equals(this._lob_loaded)) {
            this._lob_actual_len = -1;
            this._lob_bytes = null;
            this._lob_loaded = lob_state;
        }
        clear_current_value_buffer();
        this._annotation_count = 0;
        clear_fieldname();
        this._v.clear();
        this._value_start_offset = -1L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._scanner.close();
    }

    public final void current_value_is_bool(boolean z) {
        clear_current_value_buffer();
        this._value_type = IonType.BOOL;
        this._v.setValue(z);
        this._v.setAuthoritativeType(2);
    }

    public final void current_value_is_null(IonType ionType) {
        clear_current_value_buffer();
        this._value_type = this._null_type;
        this._v.setValueToNull(ionType);
        this._v.setAuthoritativeType(1);
    }

    public final void finish_and_save_value() throws IOException {
        if (this._current_value_save_point_loaded) {
            return;
        }
        this._scanner.save_point_start(this._current_value_save_point);
        finish_value(this._current_value_save_point);
        this._current_value_save_point_loaded = true;
    }

    public final void finish_value(UnifiedSavePointManagerX.SavePoint savePoint) throws IOException {
        IonType ionType;
        int i;
        if (this._scanner._unfinished_token) {
            if (savePoint != null && (ionType = this._value_type) != null && ((i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) == 1 || i == 2 || i == 3)) {
                savePoint = null;
            }
            this._scanner.finish_token(savePoint);
            this._state = get_state_after_value();
        }
        this._has_next_called = false;
    }

    public IonType getContainerType() {
        int i = this._container_state_top;
        return i == 0 ? IonType.DATAGRAM : this._container_state_stack[i - 1];
    }

    @Override // com.amazon.ion.IonReader
    public int getDepth() {
        int i = this._container_state_top;
        if (i <= 0) {
            return i;
        }
        IonType ionType = this._container_state_stack[0];
        IonType ionType2 = this._nesting_parent;
        int i2 = (ionType2 != null ? !ionType2.equals(ionType) : !IonType.DATAGRAM.equals(ionType)) ? i : i - 1;
        if (i2 == i) {
            System.err.println("so here's a case where we didn't subtract 1");
        }
        return i2;
    }

    @Override // com.amazon.ion.IonReader
    public int getFieldId() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return -1;
        }
        return this._field_name_sid;
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return null;
        }
        String str = this._field_name;
        if (str != null || this._field_name_sid <= 0) {
            return str;
        }
        throw new UnknownSymbolException(this._field_name_sid);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return null;
        }
        String str = this._field_name;
        int fieldId = getFieldId();
        if (str == null && fieldId == -1) {
            return null;
        }
        return new SymbolTokenImpl(str, fieldId);
    }

    public final String getRawFieldName() {
        if (getDepth() == 0 && is_in_struct_internal()) {
            return null;
        }
        return this._field_name;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return null;
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return this._value_type;
    }

    public final String get_action_name(int i) {
        switch (i) {
            case 0:
                return "ACTION_DO_NOTHING";
            case 1:
                return "ACTION_LOAD_FIELD_NAME";
            case 2:
                return "ACTION_LOAD_ANNOTATION";
            case 3:
                return "ACTION_START_STRUCT";
            case 4:
                return "ACTION_START_LIST";
            case 5:
                return "ACTION_START_SEXP";
            case 6:
                return "ACTION_START_LOB";
            case 7:
            default:
                return "<unrecognized action: " + Integer.toString(i) + ">";
            case 8:
                return "ACTION_LOAD_SCALAR";
            case 9:
                return "ACTION_PLUS_INF";
            case 10:
                return "ACTION_MINUS_INF";
            case 11:
                return "ACTION_EAT_COMMA";
            case 12:
                return "ACTION_FINISH_CONTAINER";
            case 13:
                return "ACTION_FINISH_LOB";
            case 14:
                return "ACTION_FINISH_DATAGRAM";
            case 15:
                return "ACTION_EOF";
        }
    }

    public final int get_state_after_annotation() {
        int i = this._state;
        if (i == 0 || i == 1) {
            return 4;
        }
        if (i == 2) {
            return 5;
        }
        if (i != 10) {
            throw new IonException("invalid state encountered during parsing before the value " + get_state_name(this._state) + this._scanner.input_position());
        }
        IonType ionType = top_state();
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                return 5;
            }
            if (i2 != 3 && i2 != 4) {
                throw new IonException("invalid container type encountered during parsing " + ionType + this._scanner.input_position());
            }
        }
        return 4;
    }

    public final int get_state_after_container() {
        return get_state_after_container(top_state());
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int get_state_after_value() {
        /*
            r2 = this;
            int[] r0 = com.amazon.ion.impl.IonReaderTextRawX.AnonymousClass1.$SwitchMap$com$amazon$ion$IonType
            com.amazon.ion.IonType r1 = r2.getContainerType()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            if (r0 == r1) goto L3b
            r1 = 2
            if (r0 == r1) goto L3d
            r1 = 3
            if (r0 == r1) goto L3b
            r1 = 4
            if (r0 != r1) goto L1a
            r1 = 0
            goto L3d
        L1a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "invalid container type encountered during parsing "
            r0.<init>(r1)
            com.amazon.ion.IonType r1 = r2.getContainerType()
            r0.append(r1)
            com.amazon.ion.impl.IonReaderTextRawTokensX r1 = r2._scanner
            java.lang.String r1 = r1.input_position()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.amazon.ion.IonException r1 = new com.amazon.ion.IonException
            r1.<init>(r0)
            throw r1
        L3b:
            r1 = 10
        L3d:
            com.amazon.ion.IonType r0 = r2._nesting_parent
            if (r0 == 0) goto L4a
            int r0 = r2.getDepth()
            if (r0 != 0) goto L4a
            r0 = 11
            return r0
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawX.get_state_after_value():int");
    }

    public final int get_state_at_container_start(IonType ionType) {
        if (ionType == null) {
            return 0;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 1;
        }
        if (i == 4) {
            return 0;
        }
        throw new IonException("invalid container type encountered during parsing " + ionType + this._scanner.input_position());
    }

    public final int get_state_int() {
        return this._state;
    }

    public final String get_state_name(int i) {
        switch (i) {
            case 0:
                return "STATE_BEFORE_ANNOTATION_DATAGRAM";
            case 1:
                return "STATE_BEFORE_ANNOTATION_CONTAINED";
            case 2:
                return "STATE_BEFORE_ANNOTATION_SEXP";
            case 3:
                return "STATE_BEFORE_FIELD_NAME";
            case 4:
                return "STATE_BEFORE_VALUE_CONTENT";
            case 5:
                return "STATE_BEFORE_VALUE_CONTENT_SEXP";
            case 6:
                return "STATE_IN_LONG_STRING";
            case 7:
                return "STATE_IN_CLOB_DOUBLE_QUOTED_CONTENT";
            case 8:
                return "STATE_IN_CLOB_TRIPLE_QUOTED_CONTENT";
            case 9:
                return "STATE_IN_BLOB_CONTENT";
            case 10:
                return "STATE_AFTER_VALUE_CONTENTS";
            case 11:
                return "STATE_EOF";
            default:
                return "<invalid state: " + Integer.toString(i) + ">";
        }
    }

    @Override // com.amazon.ion.IonReader
    public boolean hasNext() {
        return has_next_raw_value();
    }

    public final boolean has_next_raw_value() {
        if (!this._has_next_called && !this._eof) {
            try {
                finish_value(null);
                clear_value();
                parse_to_next_value();
                this._has_next_called = true;
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        }
        return !this._eof;
    }

    public final void init(UnifiedInputStreamX unifiedInputStreamX, IonType ionType) {
        init(unifiedInputStreamX, ionType, 1L, 1L);
    }

    public final void init_once() {
        this._current_value_buffer = new StringBuilder();
        this._annotations = new SymbolToken[5];
    }

    @Override // com.amazon.ion.IonReader
    public boolean isInStruct() {
        return IonType.STRUCT.equals(getContainerType()) && getDepth() > 0;
    }

    public final boolean is_in_struct_internal() {
        return IonType.STRUCT.equals(getContainerType());
    }

    @Override // com.amazon.ion.IonReader
    public Iterator<String> iterateTypeAnnotations() {
        return _Private_Utils.stringIterator(getTypeAnnotations());
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (!hasNext()) {
            return null;
        }
        if (this._value_type == null) {
            IonReaderTextRawTokensX ionReaderTextRawTokensX = this._scanner;
            if (ionReaderTextRawTokensX._unfinished_token) {
                try {
                    token_contents_load(ionReaderTextRawTokensX._token);
                } catch (IOException e) {
                    throw new IonException(e.getMessage(), e);
                }
            }
        }
        this._has_next_called = false;
        return this._value_type;
    }

    public final SymbolToken parseSymbolToken(String str, StringBuilder sb, int i) throws IOException {
        String string;
        int iDecodeSid = -1;
        if (i == 9) {
            int iKeyword = IonTokenConstsX.keyword(sb, 0, sb.length());
            string = null;
            if (iKeyword == 1 || iKeyword == 2 || iKeyword == 3 || iKeyword == 16) {
                parse_error("Cannot use unquoted keyword " + sb.toString() + " as " + str);
                throw null;
            }
            if (iKeyword != 17) {
                string = sb.toString();
            } else {
                iDecodeSid = IonTokenConstsX.decodeSid(sb);
            }
        } else {
            string = sb.toString();
        }
        return new SymbolTokenImpl(string, iDecodeSid);
    }

    public final void parse_error(String str) {
        throw new IonReaderTextParsingException("Syntax error" + this._scanner.input_position() + ": " + str);
    }

    public final void parse_to_next_value() throws IOException {
        int i;
        this._value_start_offset = this._scanner.getStartingOffset();
        IonReaderTextRawTokensX ionReaderTextRawTokensX = this._scanner;
        this._value_start_line = ionReaderTextRawTokensX._line_count;
        this._value_start_column = ionReaderTextRawTokensX.getLineOffset();
        int iNextToken = this._scanner.nextToken();
        boolean z = false;
        while (true) {
            switch (TransitionActions2[(this._state * 27) + iNextToken]) {
                case 0:
                    IonType ionType = this._nesting_parent;
                    if (ionType != null && ((i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()]) == 1 ? iNextToken == 21 : !(i == 2 ? iNextToken != 19 : !(i == 3 && iNextToken == 23)))) {
                        this._state = 11;
                        this._eof = true;
                        return;
                    }
                    parse_error("invalid syntax [state:" + get_state_name(this._state) + " on token:" + IonTokenConstsX.getTokenName(iNextToken) + "]");
                    throw null;
                case 1:
                    if (!is_in_struct_internal()) {
                        throw new IllegalStateException("field names have to be in structs");
                    }
                    finish_and_save_value();
                    set_fieldname(parseSymbolToken("a field name", token_contents_load(iNextToken), iNextToken));
                    clear_current_value_buffer();
                    int iNextToken2 = this._scanner.nextToken();
                    if (iNextToken2 != 16) {
                        parse_error("field name must be followed by a colon, not a " + IonTokenConstsX.getTokenName(iNextToken2));
                        throw null;
                    }
                    this._scanner.tokenIsFinished();
                    this._state = 1;
                    iNextToken = this._scanner.nextToken();
                    break;
                    break;
                case 2:
                    StringBuilder sb = token_contents_load(iNextToken);
                    boolean zSkip_whitespace = this._scanner.skip_whitespace(IonReaderTextRawTokensX.CommentStrategy.IGNORE);
                    if (this._scanner.skipDoubleColon()) {
                        append_annotation(parseSymbolToken("an annotation", sb, iNextToken));
                        clear_current_value_buffer();
                        iNextToken = this._scanner.nextToken();
                        if (iNextToken != 9 && iNextToken != 10) {
                            this._state = get_state_after_annotation();
                        }
                    } else {
                        this._state = get_state_after_annotation();
                    }
                    z = zSkip_whitespace;
                    break;
                case 3:
                    this._value_type = IonType.STRUCT;
                    this._state = 3;
                    return;
                case 4:
                    this._value_type = IonType.LIST;
                    this._state = 1;
                    return;
                case 5:
                    this._value_type = IonType.SEXP;
                    this._state = 2;
                    return;
                case 6:
                    int iPeekLobStartPunctuation = this._scanner.peekLobStartPunctuation();
                    if (iPeekLobStartPunctuation == 12) {
                        this._state = 7;
                        this._lob_token = 12;
                        this._value_type = IonType.CLOB;
                        return;
                    } else if (iPeekLobStartPunctuation != 13) {
                        this._state = 9;
                        this._lob_token = 24;
                        this._value_type = IonType.BLOB;
                        return;
                    } else {
                        this._state = 8;
                        this._lob_token = 13;
                        this._value_type = IonType.CLOB;
                        return;
                    }
                case 7:
                default:
                    parse_error("unexpected token encountered: " + IonTokenConstsX.getTokenName(iNextToken));
                    throw null;
                case 8:
                    if (iNextToken == 9) {
                        StringBuilder sb2 = token_contents_load(iNextToken);
                        int iKeyword = IonTokenConstsX.keyword(sb2, 0, sb2.length());
                        this._value_keyword = iKeyword;
                        if (iKeyword == 1) {
                            this._value_type = IonType.BOOL;
                            current_value_is_bool(true);
                        } else if (iKeyword == 2) {
                            this._value_type = IonType.BOOL;
                            current_value_is_bool(false);
                        } else if (iKeyword == 3) {
                            int iPeekNullTypeSymbol = z ? 0 : this._scanner.peekNullTypeSymbol();
                            if (iPeekNullTypeSymbol != 0) {
                                switch (iPeekNullTypeSymbol) {
                                    case 3:
                                        this._null_type = IonType.NULL;
                                        break;
                                    case 4:
                                        this._null_type = IonType.BOOL;
                                        break;
                                    case 5:
                                        this._null_type = IonType.INT;
                                        break;
                                    case 6:
                                        this._null_type = IonType.FLOAT;
                                        break;
                                    case 7:
                                        this._null_type = IonType.DECIMAL;
                                        break;
                                    case 8:
                                        this._null_type = IonType.TIMESTAMP;
                                        break;
                                    case 9:
                                        this._null_type = IonType.SYMBOL;
                                        break;
                                    case 10:
                                        this._null_type = IonType.STRING;
                                        break;
                                    case 11:
                                        this._null_type = IonType.BLOB;
                                        break;
                                    case 12:
                                        this._null_type = IonType.CLOB;
                                        break;
                                    case 13:
                                        this._null_type = IonType.LIST;
                                        break;
                                    case 14:
                                        this._null_type = IonType.SEXP;
                                        break;
                                    case 15:
                                        this._null_type = IonType.STRUCT;
                                        break;
                                    default:
                                        parse_error("invalid keyword id (" + iPeekNullTypeSymbol + ") encountered while parsing a null");
                                        throw null;
                                }
                            } else {
                                this._null_type = IonType.NULL;
                            }
                            current_value_is_null(this._null_type);
                        } else if (iKeyword != 16) {
                            if (iKeyword == 17) {
                                this._v.setValue(IonTokenConstsX.decodeSid(sb2));
                                this._v.setAuthoritativeType(3);
                            }
                            this._value_type = IonType.SYMBOL;
                        } else {
                            this._value_type = IonType.FLOAT;
                            clear_current_value_buffer();
                            this._v.setValue(Double.NaN);
                            this._v.setAuthoritativeType(7);
                        }
                    } else if (iNextToken == 14) {
                        this._value_type = IonType.SYMBOL;
                        clear_current_value_buffer();
                        this._v.setValue(ExternalFourCCMapper.CODEC_NAME_SPLITTER);
                        this._v.setAuthoritativeType(8);
                    } else {
                        this._value_type = IonTokenConstsX.ion_type_of_scalar(iNextToken);
                    }
                    this._state = get_state_after_value();
                    return;
                case 9:
                    this._value_type = IonType.FLOAT;
                    clear_current_value_buffer();
                    this._v.setValue(Double.POSITIVE_INFINITY);
                    this._v.setAuthoritativeType(7);
                    this._state = get_state_after_value();
                    return;
                case 10:
                    this._value_type = IonType.FLOAT;
                    clear_current_value_buffer();
                    this._v.setValue(Double.NEGATIVE_INFINITY);
                    this._v.setAuthoritativeType(7);
                    this._state = get_state_after_value();
                    return;
                case 11:
                    if (this._container_prohibits_commas) {
                        parse_error("commas aren't used to separate values in " + getContainerType().toString());
                        throw null;
                    }
                    this._state = this._container_is_struct ? 3 : 1;
                    this._scanner.tokenIsFinished();
                    this._value_start_offset = this._scanner.getStartingOffset();
                    iNextToken = this._scanner.nextToken();
                    break;
                case 12:
                    this._state = get_state_after_container(iNextToken);
                    this._eof = true;
                    return;
                case 13:
                    this._state = get_state_after_value();
                    return;
                case 14:
                    if (getDepth() != 0) {
                        parse_error("state failure end of datagram encounterd with a non-container stack");
                        throw null;
                    }
                    this._state = 11;
                    this._eof = true;
                    return;
                case 15:
                    this._state = 11;
                    this._eof = true;
                    return;
            }
        }
    }

    public final void pop_container_state() {
        this._container_state_top--;
        set_container_flags(top_state());
        this._eof = false;
        this._has_next_called = false;
        this._state = get_state_after_container();
    }

    public final void push_container_state(IonType ionType) {
        IonType[] ionTypeArr = this._container_state_stack;
        int length = ionTypeArr.length;
        if (this._container_state_top >= length) {
            IonType[] ionTypeArr2 = new IonType[length * 2];
            System.arraycopy(ionTypeArr, 0, ionTypeArr2, 0, length);
            this._container_state_stack = ionTypeArr2;
        }
        set_container_flags(ionType);
        IonType[] ionTypeArr3 = this._container_state_stack;
        int i = this._container_state_top;
        this._container_state_top = i + 1;
        ionTypeArr3[i] = ionType;
    }

    public final void re_init(UnifiedInputStreamX unifiedInputStreamX, IonType ionType, long j, long j2) {
        this._state = 0;
        this._container_state_top = 0;
        this._container_is_struct = false;
        this._container_prohibits_commas = false;
        this._has_next_called = false;
        this._value_type = null;
        this._value_keyword = 0;
        this._null_type = null;
        this._field_name = null;
        this._field_name_sid = -1;
        this._annotation_count = 0;
        this._current_value_save_point_loaded = false;
        this._current_value_buffer_loaded = false;
        this._value_start_offset = 0L;
        this._lob_value_set = false;
        this._lob_token = 0;
        this._lob_value_position = 0L;
        this._lob_bytes = null;
        this._lob_actual_len = 0;
        init(unifiedInputStreamX, ionType, j, j2);
        this._nesting_parent = ionType;
        if (IonType.STRUCT.equals(ionType)) {
            this._container_is_struct = true;
        }
    }

    public final void set_container_flags(IonType ionType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i == 1) {
            this._container_is_struct = true;
            this._container_prohibits_commas = false;
            return;
        }
        if (i == 2) {
            this._container_is_struct = false;
            this._container_prohibits_commas = true;
        } else if (i == 3) {
            this._container_is_struct = false;
            this._container_prohibits_commas = false;
        } else if (i == 4) {
            this._container_is_struct = false;
            this._container_prohibits_commas = true;
        } else {
            throw new IllegalArgumentException("type must be a container, not a " + ionType.toString());
        }
    }

    public final void set_fieldname(SymbolToken symbolToken) {
        this._field_name = symbolToken.getText();
        this._field_name_sid = symbolToken.getSid();
    }

    public final void set_state(int i) {
        this._state = i;
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        IonType ionType = this._value_type;
        if (ionType == null || this._eof) {
            throw new IllegalStateException();
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalStateException("Unexpected value type: " + this._value_type);
        }
        this._state = get_state_at_container_start(this._value_type);
        push_container_state(this._value_type);
        this._scanner.tokenIsFinished();
        try {
            finish_value(null);
            if (this._v.hasValueOfType(1)) {
                this._eof = true;
                this._has_next_called = true;
            }
            this._value_type = null;
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        if (getDepth() < 1) {
            throw new IllegalStateException(IonMessages.CANNOT_STEP_OUT);
        }
        try {
            finish_value(null);
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[getContainerType().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            throw new IllegalStateException("Unexpected value type: " + this._value_type);
                        }
                    } else if (!this._eof) {
                        this._scanner.skip_over_list();
                    }
                } else if (!this._eof) {
                    this._scanner.skip_over_sexp();
                }
            } else if (!this._eof) {
                this._scanner.skip_over_struct();
            }
            pop_container_state();
            this._scanner.tokenIsFinished();
            try {
                finish_value(null);
                clear_value();
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        } catch (IOException e2) {
            throw new IonException(e2.getMessage(), e2);
        }
    }

    public void tokenValueIsFinished() {
        this._scanner.tokenIsFinished();
        if (IonType.BLOB.equals(this._value_type) || IonType.CLOB.equals(this._value_type)) {
            this._state = get_state_after_value();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.StringBuilder token_contents_load(int r7) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawX.token_contents_load(int):java.lang.StringBuilder");
    }

    public final IonType top_state() {
        return this._container_state_stack[this._container_state_top - 1];
    }

    public final void init(UnifiedInputStreamX unifiedInputStreamX, IonType ionType, long j, long j2) {
        this._scanner = new IonReaderTextRawTokensX(unifiedInputStreamX, j, j2);
        this._value_start_line = j;
        this._value_start_column = j2;
        this._current_value_save_point = unifiedInputStreamX._save_points.savePointAllocate();
        this._lob_loaded = LOB_STATE.EMPTY;
        this._state = get_state_at_container_start(ionType);
        this._eof = false;
        push_container_state(ionType);
    }

    public final int get_state_after_container(int i) {
        IonType ionType = top_state();
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionType.ordinal()];
        if (i2 == 1) {
            check_container_close(ionType, 21, i);
        } else if (i2 == 2) {
            check_container_close(ionType, 19, i);
        } else if (i2 == 3) {
            check_container_close(ionType, 23, i);
        } else {
            throw new IonException("invalid container type encountered during parsing " + ionType + this._scanner.input_position());
        }
        return get_state_after_container(ionType);
    }

    public final void parse_error(Exception exc) {
        throw new IonReaderTextParsingException("Syntax error at " + this._scanner.input_position() + ": " + exc.getLocalizedMessage(), (Throwable) exc);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int get_state_after_container(com.amazon.ion.IonType r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            int[] r1 = com.amazon.ion.impl.IonReaderTextRawX.AnonymousClass1.$SwitchMap$com$amazon$ion$IonType
            int r2 = r4.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L38
            r2 = 2
            if (r1 == r2) goto L36
            r2 = 3
            if (r1 == r2) goto L38
            r2 = 4
            if (r1 != r2) goto L19
            goto L3a
        L19:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "invalid container type encountered during parsing "
            r0.<init>(r1)
            r0.append(r4)
            com.amazon.ion.impl.IonReaderTextRawTokensX r4 = r3._scanner
            java.lang.String r4 = r4.input_position()
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            com.amazon.ion.IonException r0 = new com.amazon.ion.IonException
            r0.<init>(r4)
            throw r0
        L36:
            r0 = 2
            goto L3a
        L38:
            r0 = 10
        L3a:
            com.amazon.ion.IonType r4 = r3._nesting_parent
            if (r4 == 0) goto L47
            int r4 = r3.getDepth()
            if (r4 != 0) goto L47
            r4 = 11
            return r4
        L47:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl.IonReaderTextRawX.get_state_after_container(com.amazon.ion.IonType):int");
    }

    public final String get_state_name() {
        return get_state_name(this._state);
    }
}
