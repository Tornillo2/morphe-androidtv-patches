package com.amazon.ion.impl;

import androidx.collection.ObjectListKt$$ExternalSyntheticOutline1;
import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.Timestamp;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.text.lookup.StringLookupFactory;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class _Private_ScalarConversions {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int FNID_FROM_BIGINTEGER_TO_DECIMAL = 33;
    public static final int FNID_FROM_BIGINTEGER_TO_DOUBLE = 37;
    public static final int FNID_FROM_BIGINTEGER_TO_INT = 20;
    public static final int FNID_FROM_BIGINTEGER_TO_LONG = 24;
    public static final int FNID_FROM_BIGINTEGER_TO_STRING = 14;
    public static final int FNID_FROM_BOOLEAN_TO_STRING = 11;
    public static final int FNID_FROM_DATE_TO_STRING = 17;
    public static final int FNID_FROM_DATE_TO_TIMESTAMP = 40;
    public static final int FNID_FROM_DECIMAL_TO_BIGINTEGER = 29;
    public static final int FNID_FROM_DECIMAL_TO_DOUBLE = 38;
    public static final int FNID_FROM_DECIMAL_TO_INT = 21;
    public static final int FNID_FROM_DECIMAL_TO_LONG = 25;
    public static final int FNID_FROM_DECIMAL_TO_STRING = 15;
    public static final int FNID_FROM_DOUBLE_TO_BIGINTEGER = 30;
    public static final int FNID_FROM_DOUBLE_TO_DECIMAL = 34;
    public static final int FNID_FROM_DOUBLE_TO_INT = 22;
    public static final int FNID_FROM_DOUBLE_TO_LONG = 26;
    public static final int FNID_FROM_DOUBLE_TO_STRING = 16;
    public static final int FNID_FROM_INT_TO_BIGINTEGER = 27;
    public static final int FNID_FROM_INT_TO_DECIMAL = 31;
    public static final int FNID_FROM_INT_TO_DOUBLE = 35;
    public static final int FNID_FROM_INT_TO_LONG = 23;
    public static final int FNID_FROM_INT_TO_STRING = 12;
    public static final int FNID_FROM_LONG_TO_BIGINTEGER = 28;
    public static final int FNID_FROM_LONG_TO_DECIMAL = 32;
    public static final int FNID_FROM_LONG_TO_DOUBLE = 36;
    public static final int FNID_FROM_LONG_TO_INT = 19;
    public static final int FNID_FROM_LONG_TO_STRING = 13;
    public static final int FNID_FROM_NULL_TO_STRING = 10;
    public static final int FNID_FROM_STRING_TO_BIGINTEGER = 5;
    public static final int FNID_FROM_STRING_TO_BOOLEAN = 2;
    public static final int FNID_FROM_STRING_TO_DATE = 8;
    public static final int FNID_FROM_STRING_TO_DECIMAL = 6;
    public static final int FNID_FROM_STRING_TO_DOUBLE = 7;
    public static final int FNID_FROM_STRING_TO_INT = 3;
    public static final int FNID_FROM_STRING_TO_LONG = 4;
    public static final int FNID_FROM_STRING_TO_NULL = 1;
    public static final int FNID_FROM_STRING_TO_TIMESTAMP = 9;
    public static final int FNID_FROM_TIMESTAMP_TO_DATE = 39;
    public static final int FNID_FROM_TIMESTAMP_TO_STRING = 18;
    public static int FNID_identity = 0;
    public static int FNID_no_conversion = -1;
    public static int[] from_string_conversion = {-1, 1, 2, 3, 4, 5, 6, 7, 0, 8, 9, -1, -1};
    public static int[] to_string_conversions = {-1, 10, 11, 12, 13, 14, 15, 16, 0, 17, 18, -1, -1};
    public static int[] to_int_conversion = {-1, -1, -1, 0, 19, 20, 21, 22, 3, -1, -1, -1, -1};
    public static int[] to_long_conversion = {-1, -1, -1, 23, 0, 24, 25, 26, 4, -1, -1, -1, -1};
    public static int[] to_bigInteger_conversion = {-1, -1, -1, 27, 28, 0, 29, 30, 5, -1, -1, -1, -1};
    public static int[] to_decimal_conversion = {-1, -1, -1, 31, 32, 33, 0, 34, 6, -1, -1, -1, -1};
    public static int[] to_double_conversion = {-1, -1, -1, 35, 36, 37, 38, 0, 7, -1, -1, -1, -1};

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class AS_TYPE {
        public static final int bigInteger_value = 5;
        public static final int boolean_value = 2;
        public static final int convertable_type = ((1 << (768 - 1)) | (1 << (124 - 1))) | 130;
        public static final int date_value = 9;
        public static final int datetime_types = 768;
        public static final int decimal_value = 6;
        public static final int double_value = 7;
        public static final int int_value = 3;
        public static final int long_value = 4;
        public static final int null_value = 1;
        public static final int numeric_types = 124;
        public static final int string_value = 8;
        public static final int timestamp_value = 10;

        public static final int idx_to_bit_mask(int i) {
            return 1 << (i - 1);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class CantConvertException extends ConversionException {
        public static final long serialVersionUID = 1;

        public CantConvertException(String str) {
            super(str);
        }

        public CantConvertException(String str, Exception exc) {
            super(str, (Throwable) exc);
        }

        public CantConvertException(Exception exc) {
            super((Throwable) exc);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ConversionException extends IonException {
        public static final long serialVersionUID = 1;

        public ConversionException(String str) {
            super(str);
        }

        public ConversionException(String str, Exception exc) {
            super(str, exc);
        }

        public ConversionException(Exception exc) {
            super(exc);
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static class ValueNotSetException extends ConversionException {
        public static final long serialVersionUID = 1;

        public ValueNotSetException(String str) {
            super(str);
        }

        public ValueNotSetException(String str, Exception exc) {
            super(str, (Throwable) exc);
        }

        public ValueNotSetException(Exception exc) {
            super((Throwable) exc);
        }
    }

    public static final String getAllValueTypeNames(int i) {
        StringBuilder sb = new StringBuilder("(");
        int i2 = 1;
        for (int i3 = 0; i3 < 32; i3++) {
            if ((i & i2) != 0) {
                sb.append(getValueTypeName(i2));
                sb.append(' ');
            }
            i2 <<= 1;
        }
        sb.append(')');
        return sb.toString();
    }

    public static final int getConversionFnid(int i, int i2) {
        if (i2 == i) {
            return 0;
        }
        switch (i2) {
            case 1:
                return from_string_conversion[1];
            case 2:
                return from_string_conversion[2];
            case 3:
                return to_int_conversion[i];
            case 4:
                return to_long_conversion[i];
            case 5:
                return to_bigInteger_conversion[i];
            case 6:
                return to_decimal_conversion[i];
            case 7:
                return to_double_conversion[i];
            case 8:
                return to_string_conversions[i];
            case 9:
                return 39;
            case 10:
                return 40;
            default:
                throw new CantConvertException("can't convert from " + getValueTypeName(i) + " to " + getValueTypeName(i2));
        }
    }

    public static IntegerSize getIntegerSize(int i) {
        if (i == 3) {
            return IntegerSize.INT;
        }
        if (i == 4) {
            return IntegerSize.LONG;
        }
        if (i != 5) {
            return null;
        }
        return IntegerSize.BIG_INTEGER;
    }

    public static String getValueTypeName(int i) {
        switch (i) {
            case 1:
                return AbstractJsonLexerKt.NULL;
            case 2:
                return "boolean";
            case 3:
                return "int";
            case 4:
                return "long";
            case 5:
                return "bigInteger";
            case 6:
                return "decimal";
            case 7:
                return "double";
            case 8:
                return "string";
            case 9:
                return StringLookupFactory.KEY_DATE;
            case 10:
                return "timestamp";
            default:
                return "<unrecognized conversion value type: " + Integer.toString(i) + ">";
        }
    }

    public static String get_value_type_name(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return getValueTypeName(i) + "_value";
            default:
                return "<unrecognized conversion value type: " + Integer.toString(i) + ">";
        }
    }

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class ValueVariant {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public int _authoritative_type_idx;
        public BigInteger _bigInteger_value;
        public boolean _boolean_value;
        public Date _date_value;
        public Decimal _decimal_value;
        public double _double_value;
        public int _int_value;
        public boolean _is_null;
        public long _long_value;
        public IonType _null_type;
        public String _string_value;
        public Timestamp _timestamp_value;
        public int _types_set;
        public static final BigInteger min_int_value = BigInteger.valueOf(-2147483648L);
        public static final BigInteger max_int_value = BigInteger.valueOf(2147483647L);
        public static final BigInteger min_long_value = BigInteger.valueOf(Long.MIN_VALUE);
        public static final BigInteger max_long_value = BigInteger.valueOf(Long.MAX_VALUE);
        public static final BigDecimal min_int_decimal_value = BigDecimal.valueOf(-2147483648L);
        public static final BigDecimal max_int_decimal_value = BigDecimal.valueOf(2147483647L);
        public static final BigDecimal min_long_decimal_value = BigDecimal.valueOf(Long.MIN_VALUE);
        public static final BigDecimal max_long_decimal_value = BigDecimal.valueOf(Long.MAX_VALUE);

        public static final boolean isNumericType(int i) {
            return (AS_TYPE.idx_to_bit_mask(i) & AS_TYPE.numeric_types) != 0;
        }

        public final void addValue(boolean z) {
            this._boolean_value = z;
            add_value_type(2);
        }

        public final void addValueToNull(IonType ionType) {
            this._is_null = true;
            this._null_type = ionType;
            add_value_type(1);
        }

        public final void add_value_type(int i) {
            this._types_set = AS_TYPE.idx_to_bit_mask(i) | this._types_set;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:4:0x0007 A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean can_convert(int r4) {
            /*
                r3 = this;
                r0 = 8
                r1 = 1
                r2 = 0
                switch(r4) {
                    case 1: goto L23;
                    case 2: goto L23;
                    case 3: goto L16;
                    case 4: goto L16;
                    case 5: goto L16;
                    case 6: goto L16;
                    case 7: goto L16;
                    case 8: goto L27;
                    case 9: goto L9;
                    case 10: goto L9;
                    default: goto L7;
                }
            L7:
                r1 = 0
                goto L27
            L9:
                int r4 = r3._authoritative_type_idx
                boolean r4 = r3.is_datetime_type(r4)
                if (r4 != 0) goto L27
                int r4 = r3._authoritative_type_idx
                if (r4 != r0) goto L7
                goto L27
            L16:
                int r4 = r3._authoritative_type_idx
                boolean r4 = r3.is_numeric_type(r4)
                if (r4 != 0) goto L27
                int r4 = r3._authoritative_type_idx
                if (r4 != r0) goto L7
                goto L27
            L23:
                int r4 = r3._authoritative_type_idx
                if (r4 != r0) goto L7
            L27:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.ion.impl._Private_ScalarConversions.ValueVariant.can_convert(int):boolean");
        }

        public final void cast(int i) {
            switch (i) {
                case 1:
                    fn_from_string_to_null();
                    return;
                case 2:
                    fn_from_string_to_boolean();
                    return;
                case 3:
                    fn_from_string_to_int();
                    return;
                case 4:
                    fn_from_string_to_long();
                    return;
                case 5:
                    fn_from_string_to_biginteger();
                    return;
                case 6:
                    fn_from_string_to_decimal();
                    return;
                case 7:
                    fn_from_string_to_double();
                    return;
                case 8:
                    fn_from_string_to_date();
                    return;
                case 9:
                    fn_from_string_to_timestamp();
                    return;
                case 10:
                    fn_from_null_to_string();
                    return;
                case 11:
                    fn_from_boolean_to_string();
                    return;
                case 12:
                    fn_from_int_to_string();
                    return;
                case 13:
                    fn_from_long_to_string();
                    return;
                case 14:
                    fn_from_biginteger_to_string();
                    return;
                case 15:
                    fn_from_decimal_to_string();
                    return;
                case 16:
                    fn_from_double_to_string();
                    return;
                case 17:
                    fn_from_date_to_string();
                    return;
                case 18:
                    fn_from_timestamp_to_string();
                    return;
                case 19:
                    fn_from_long_to_int();
                    return;
                case 20:
                    fn_from_biginteger_to_int();
                    return;
                case 21:
                    fn_from_decimal_to_int();
                    return;
                case 22:
                    fn_from_double_to_int();
                    return;
                case 23:
                    fn_from_int_to_long();
                    return;
                case 24:
                    fn_from_biginteger_to_long();
                    return;
                case 25:
                    fn_from_decimal_to_long();
                    return;
                case 26:
                    fn_from_double_to_long();
                    return;
                case 27:
                    fn_from_int_to_biginteger();
                    return;
                case 28:
                    fn_from_long_to_biginteger();
                    return;
                case 29:
                    fn_from_decimal_to_biginteger();
                    return;
                case 30:
                    fn_from_double_to_biginteger();
                    return;
                case 31:
                    fn_from_int_to_decimal();
                    return;
                case 32:
                    fn_from_long_to_decimal();
                    return;
                case 33:
                    fn_from_biginteger_to_decimal();
                    return;
                case 34:
                    fn_from_double_to_decimal();
                    return;
                case 35:
                    fn_from_int_to_double();
                    return;
                case 36:
                    fn_from_long_to_double();
                    return;
                case 37:
                    fn_from_biginteger_to_double();
                    return;
                case 38:
                    fn_from_decimal_to_double();
                    return;
                case 39:
                    fn_from_timestamp_to_date();
                    return;
                case 40:
                    fn_from_date_to_timestamp();
                    return;
                default:
                    throw new ConversionException(ObjectListKt$$ExternalSyntheticOutline1.m("unrecognized conversion fnid [", i, "]invoked"));
            }
        }

        public final void clear() {
            this._authoritative_type_idx = 0;
            this._types_set = 0;
        }

        public final void fn_from_biginteger_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._bigInteger_value);
            add_value_type(6);
        }

        public final void fn_from_biginteger_to_double() {
            this._double_value = this._bigInteger_value.doubleValue();
            add_value_type(7);
        }

        public final void fn_from_biginteger_to_int() {
            if (min_int_value.compareTo(this._bigInteger_value) > 0 || max_int_value.compareTo(this._bigInteger_value) < 0) {
                throw new CantConvertException("bigInteger value is too large to fit in an int");
            }
            this._int_value = this._bigInteger_value.intValue();
            add_value_type(3);
        }

        public final void fn_from_biginteger_to_long() {
            if (min_long_value.compareTo(this._bigInteger_value) > 0 || max_long_value.compareTo(this._bigInteger_value) < 0) {
                throw new CantConvertException("BigInteger is too large to fit in a long");
            }
            this._long_value = this._bigInteger_value.longValue();
            add_value_type(4);
        }

        public final void fn_from_biginteger_to_string() {
            this._string_value = this._bigInteger_value.toString();
            add_value_type(8);
        }

        public final void fn_from_boolean_to_string() {
            this._string_value = this._boolean_value ? "true" : "false";
            add_value_type(8);
        }

        public final void fn_from_date_to_string() {
            if (!hasValueOfType(10)) {
                fn_from_date_to_timestamp();
            }
            fn_from_timestamp_to_string();
        }

        public final void fn_from_date_to_timestamp() {
            this._timestamp_value = new Timestamp(this._date_value.getTime(), (Integer) null);
            add_value_type(10);
        }

        public final void fn_from_decimal_to_biginteger() {
            this._bigInteger_value = this._decimal_value.toBigInteger();
            add_value_type(5);
        }

        public final void fn_from_decimal_to_double() {
            this._double_value = this._decimal_value.doubleValue();
            add_value_type(7);
        }

        public final void fn_from_decimal_to_int() {
            if (min_int_decimal_value.compareTo((BigDecimal) this._decimal_value) > 0 || max_int_decimal_value.compareTo((BigDecimal) this._decimal_value) < 0) {
                throw new CantConvertException("BigDecimal value is too large to fit in an int");
            }
            this._int_value = this._decimal_value.intValue();
            add_value_type(3);
        }

        public final void fn_from_decimal_to_long() {
            if (min_long_decimal_value.compareTo((BigDecimal) this._decimal_value) > 0 || max_long_decimal_value.compareTo((BigDecimal) this._decimal_value) < 0) {
                throw new CantConvertException("BigDecimal value is too large to fit in a long");
            }
            this._long_value = this._decimal_value.longValue();
            add_value_type(4);
        }

        public final void fn_from_decimal_to_string() {
            this._string_value = this._decimal_value.toString();
            add_value_type(8);
        }

        public final void fn_from_double_to_biginteger() {
            this._bigInteger_value = Decimal.valueOf(this._double_value).toBigInteger();
            add_value_type(5);
        }

        public final void fn_from_double_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._double_value);
            add_value_type(6);
        }

        public final void fn_from_double_to_int() {
            double d = this._double_value;
            if (d < -2.147483648E9d || d > 2.147483647E9d) {
                throw new CantConvertException("double is too large to fit in an int");
            }
            this._int_value = (int) d;
            add_value_type(3);
        }

        public final void fn_from_double_to_long() {
            double d = this._double_value;
            if (d < -9.223372036854776E18d || d > 9.223372036854776E18d) {
                throw new CantConvertException("double is too large to fit in a long");
            }
            this._long_value = (long) d;
            add_value_type(4);
        }

        public final void fn_from_double_to_string() {
            this._string_value = Double.toString(this._double_value);
            add_value_type(8);
        }

        public final void fn_from_int_to_biginteger() {
            this._bigInteger_value = BigInteger.valueOf(this._int_value);
            add_value_type(5);
        }

        public final void fn_from_int_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._int_value);
            add_value_type(6);
        }

        public final void fn_from_int_to_double() {
            this._double_value = this._int_value;
            add_value_type(7);
        }

        public final void fn_from_int_to_long() {
            this._long_value = this._int_value;
            add_value_type(4);
        }

        public final void fn_from_int_to_string() {
            this._string_value = Integer.toString(this._int_value);
            add_value_type(8);
        }

        public final void fn_from_long_to_biginteger() {
            this._bigInteger_value = BigInteger.valueOf(this._long_value);
            add_value_type(5);
        }

        public final void fn_from_long_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._long_value);
            add_value_type(6);
        }

        public final void fn_from_long_to_double() {
            this._double_value = this._long_value;
            add_value_type(7);
        }

        public final void fn_from_long_to_int() {
            long j = this._long_value;
            if (j < -2147483648L || j > 2147483647L) {
                throw new CantConvertException("long is too large to fit in an int");
            }
            this._int_value = (int) j;
            add_value_type(3);
        }

        public final void fn_from_long_to_string() {
            this._string_value = Long.toString(this._long_value);
            add_value_type(8);
        }

        public final void fn_from_null_to_string() {
            this._string_value = IonTokenConstsX.getNullImage(this._null_type);
            add_value_type(8);
        }

        public final void fn_from_string_to_biginteger() {
            this._bigInteger_value = new BigInteger(this._string_value);
            add_value_type(5);
        }

        public final void fn_from_string_to_boolean() {
            this._boolean_value = Boolean.parseBoolean(this._string_value);
            add_value_type(2);
        }

        public final void fn_from_string_to_date() {
            if (!hasValueOfType(10)) {
                fn_from_string_to_timestamp();
            }
            this._date_value = new Date(this._timestamp_value.getMillis());
            add_value_type(9);
        }

        public final void fn_from_string_to_decimal() {
            this._decimal_value = Decimal.valueOf(this._string_value);
            add_value_type(6);
        }

        public final void fn_from_string_to_double() {
            this._double_value = Double.parseDouble(this._string_value);
            add_value_type(7);
        }

        public final void fn_from_string_to_int() {
            this._int_value = Integer.parseInt(this._string_value);
            add_value_type(3);
        }

        public final void fn_from_string_to_long() {
            this._long_value = Long.parseLong(this._string_value);
            add_value_type(4);
        }

        public final void fn_from_string_to_null() {
            this._null_type = IonTokenConstsX.getNullType(this._string_value);
            this._is_null = true;
            add_value_type(1);
        }

        public final void fn_from_string_to_timestamp() {
            this._timestamp_value = Timestamp.valueOf(this._string_value);
            add_value_type(10);
            add_value_type(10);
        }

        public final void fn_from_timestamp_to_date() {
            this._date_value = this._timestamp_value.dateValue();
            add_value_type(9);
        }

        public final void fn_from_timestamp_to_string() {
            this._string_value = this._timestamp_value.toString();
            add_value_type(8);
        }

        public final int getAuthoritativeType() {
            return this._authoritative_type_idx;
        }

        public final BigDecimal getBigDecimal() {
            if (hasValueOfType(6)) {
                return Decimal.bigDecimalValue(this._decimal_value);
            }
            throw new ValueNotSetException("BigDecimal value not set");
        }

        public final BigInteger getBigInteger() {
            if (hasValueOfType(5)) {
                return this._bigInteger_value;
            }
            throw new ValueNotSetException("BigInteger value not set");
        }

        public final boolean getBoolean() {
            if (hasValueOfType(2)) {
                return this._boolean_value;
            }
            throw new ValueNotSetException("boolean not set");
        }

        public final Date getDate() {
            if (hasValueOfType(9)) {
                return this._date_value;
            }
            throw new ValueNotSetException("Date value not set");
        }

        public final Decimal getDecimal() {
            if (hasValueOfType(6)) {
                return this._decimal_value;
            }
            throw new ValueNotSetException("BigDecimal value not set");
        }

        public final double getDouble() {
            if (hasValueOfType(7)) {
                return this._double_value;
            }
            throw new ValueNotSetException("double value not set");
        }

        public final int getInt() {
            if (hasValueOfType(3)) {
                return this._int_value;
            }
            throw new ValueNotSetException("int value not set");
        }

        public final long getLong() {
            if (hasValueOfType(4)) {
                return this._long_value;
            }
            throw new ValueNotSetException("long value not set");
        }

        public final IonType getNullType() {
            if (hasValueOfType(1)) {
                return this._null_type;
            }
            throw new ValueNotSetException("null value not set");
        }

        public final String getString() {
            if (hasValueOfType(8)) {
                return this._string_value;
            }
            throw new ValueNotSetException("String value not set");
        }

        public final Timestamp getTimestamp() {
            if (hasValueOfType(10)) {
                return this._timestamp_value;
            }
            throw new ValueNotSetException("timestamp value not set");
        }

        public final int get_conversion_fnid(int i) {
            return _Private_ScalarConversions.getConversionFnid(this._authoritative_type_idx, i);
        }

        public final boolean hasDatetimeType() {
            return (AS_TYPE.datetime_types & this._types_set) != 0;
        }

        public final boolean hasNumericType() {
            return (AS_TYPE.numeric_types & this._types_set) != 0;
        }

        public final boolean hasValueOfType(int i) {
            return (AS_TYPE.idx_to_bit_mask(i) & this._types_set) != 0;
        }

        public final boolean isEmpty() {
            return this._authoritative_type_idx == 0;
        }

        public final boolean isNull() {
            return hasValueOfType(1);
        }

        public final boolean is_datetime_type(int i) {
            return ((1 << (i - 1)) & AS_TYPE.datetime_types) != 0;
        }

        public final boolean is_numeric_type(int i) {
            return ((1 << (i - 1)) & AS_TYPE.numeric_types) != 0;
        }

        public final void setAuthoritativeType(int i) {
            if (this._authoritative_type_idx == i) {
                return;
            }
            if (hasValueOfType(i)) {
                this._authoritative_type_idx = i;
                return;
            }
            throw new IllegalStateException("you must set the " + _Private_ScalarConversions.getValueTypeName(i) + " value before you can set the authoritative type to " + _Private_ScalarConversions.getValueTypeName(i));
        }

        public final void setValue(boolean z) {
            this._boolean_value = z;
            set_value_type(2);
        }

        public final void setValueToNull(IonType ionType) {
            this._is_null = true;
            this._null_type = ionType;
            set_value_type(1);
        }

        public final void set_value_type(int i) {
            this._types_set = AS_TYPE.idx_to_bit_mask(i);
            this._authoritative_type_idx = i;
        }

        public final void addValue(int i) {
            this._int_value = i;
            add_value_type(3);
        }

        public final void setValue(int i) {
            this._int_value = i;
            set_value_type(3);
        }

        public final void addValue(long j) {
            this._long_value = j;
            add_value_type(4);
        }

        public final void setValue(long j) {
            this._long_value = j;
            set_value_type(4);
        }

        public final void addValue(double d) {
            this._double_value = d;
            add_value_type(7);
        }

        public final void setValue(double d) {
            this._double_value = d;
            set_value_type(7);
        }

        public final void addValue(String str) {
            this._string_value = str;
            add_value_type(8);
        }

        public final void setValue(String str) {
            this._string_value = str;
            set_value_type(8);
        }

        public final void addValue(BigInteger bigInteger) {
            this._bigInteger_value = bigInteger;
            add_value_type(5);
        }

        public final void setValue(BigInteger bigInteger) {
            this._bigInteger_value = bigInteger;
            set_value_type(5);
        }

        public final void addValue(BigDecimal bigDecimal) {
            this._decimal_value = (Decimal) bigDecimal;
            add_value_type(6);
        }

        public final void setValue(Decimal decimal) {
            this._decimal_value = decimal;
            set_value_type(6);
        }

        public final void addValue(Decimal decimal) {
            this._decimal_value = decimal;
            add_value_type(6);
        }

        public final void setValue(Date date) {
            this._date_value = date;
            set_value_type(9);
        }

        public final void addValue(Date date) {
            this._date_value = date;
            add_value_type(9);
        }

        public final void setValue(Timestamp timestamp) {
            this._timestamp_value = timestamp;
            set_value_type(10);
        }

        public final void addValue(Timestamp timestamp) {
            this._timestamp_value = timestamp;
            add_value_type(10);
        }
    }
}
