package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl.lite.IonSystemLite;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.ion.system.IonTextWriterBuilder;
import com.amazon.ion.system.SimpleCatalog;
import com.amazon.ion.util._Private_FastAppendable;
import java.io.OutputStream;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public class _Private_IonTextWriterBuilder extends IonTextWriterBuilder {
    public static final CharSequence SPACE_CHARACTER = StringUtils.SPACE;
    public static _Private_IonTextWriterBuilder STANDARD = new _Private_IonTextWriterBuilder(new Mutable());
    public boolean _blob_as_string;
    public _Private_CallbackBuilder _callback_builder;
    public boolean _clob_as_string;
    public boolean _decimal_as_float;
    public boolean _float_nan_and_inf_as_null;
    public boolean _pretty_print;
    public boolean _sexp_as_list;
    public boolean _skip_annotations;
    public boolean _string_as_json;
    public boolean _symbol_as_string;
    public boolean _timestamp_as_millis;
    public boolean _timestamp_as_string;
    public boolean _untyped_nulls;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public static final class Mutable extends _Private_IonTextWriterBuilder {
        public Mutable(_Private_IonTextWriterBuilder _private_iontextwriterbuilder) {
            super(_private_iontextwriterbuilder);
        }

        @Override // com.amazon.ion.impl._Private_IonTextWriterBuilder, com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public IonTextWriterBuilder copy() {
            return new Mutable(this);
        }

        @Override // com.amazon.ion.impl._Private_IonTextWriterBuilder, com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public _Private_IonTextWriterBuilder immutable() {
            return new _Private_IonTextWriterBuilder(this);
        }

        @Override // com.amazon.ion.impl._Private_IonTextWriterBuilder, com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public _Private_IonTextWriterBuilder mutable() {
            return this;
        }

        public Mutable(_Private_IonTextWriterBuilder _private_iontextwriterbuilder, AnonymousClass1 anonymousClass1) {
            super(_private_iontextwriterbuilder);
        }

        @Override // com.amazon.ion.impl._Private_IonTextWriterBuilder, com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public IonTextWriterBuilder mutable() {
            return this;
        }

        public Mutable() {
        }

        @Override // com.amazon.ion.impl._Private_IonTextWriterBuilder, com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
        public IonTextWriterBuilder immutable() {
            return new _Private_IonTextWriterBuilder(this);
        }

        public Mutable(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.amazon.ion.system.IonWriterBuilderBase
        public void mutationCheck() {
        }
    }

    public static _Private_IonTextWriterBuilder standard() {
        return new Mutable();
    }

    public final IonWriter build(_Private_FastAppendable _private_fastappendable) {
        IonCatalog catalog = getCatalog();
        SymbolTable[] imports = getImports();
        IonSystem ionSystemBuild = IonSystemBuilder.STANDARD.withCatalog(catalog).build();
        SymbolTable symbolTable = ((IonSystemLite) ionSystemBuild)._system_symbol_table;
        _Private_CallbackBuilder _private_callbackbuilder = this._callback_builder;
        return new IonWriterUser(catalog, ionSystemBuild, _private_callbackbuilder == null ? new IonWriterSystemText(symbolTable, this, _private_fastappendable) : new IonWriterSystemTextMarkup(symbolTable, this, _private_callbackbuilder.build(_private_fastappendable)), _Private_Utils.initialSymtab(((_Private_ValueFactory) ionSystemBuild).getLstFactory(), symbolTable, imports));
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public final _Private_IonTextWriterBuilder copy() {
        return new Mutable(this);
    }

    public final _Private_IonTextWriterBuilder fillDefaults() {
        Mutable mutable = new Mutable(this);
        if (mutable.myCatalog == null) {
            mutable.setCatalog(new SimpleCatalog());
        }
        if (mutable.myCharset == null) {
            mutable.setCharset(IonTextWriterBuilder.UTF8);
        }
        if (mutable.myNewLineType == null) {
            mutable.setNewLineType(IonTextWriterBuilder.NewLineType.PLATFORM_DEPENDENT);
        }
        return new _Private_IonTextWriterBuilder(mutable);
    }

    public final _Private_CallbackBuilder getCallbackBuilder() {
        return this._callback_builder;
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public _Private_IonTextWriterBuilder immutable() {
        return this;
    }

    public final boolean isPrettyPrintOn() {
        return this._pretty_print;
    }

    public final CharSequence lineSeparator() {
        return this._pretty_print ? this.myNewLineType.charSequence : SPACE_CHARACTER;
    }

    public void setCallbackBuilder(_Private_CallbackBuilder _private_callbackbuilder) {
        mutationCheck();
        this._callback_builder = _private_callbackbuilder;
    }

    public final CharSequence topLevelSeparator() {
        return this.myTopLevelValuesOnNewLines ? this.myNewLineType.charSequence : lineSeparator();
    }

    public final _Private_IonTextWriterBuilder withCallbackBuilder(_Private_CallbackBuilder _private_callbackbuilder) {
        _Private_IonTextWriterBuilder _private_iontextwriterbuilderMutable = mutable();
        _private_iontextwriterbuilderMutable.setCallbackBuilder(_private_callbackbuilder);
        return _private_iontextwriterbuilderMutable;
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder
    public final IonTextWriterBuilder withJsonDowngrade() {
        _Private_IonTextWriterBuilder _private_iontextwriterbuilderMutable = mutable();
        _private_iontextwriterbuilderMutable.withMinimalSystemData();
        this._blob_as_string = true;
        this._clob_as_string = true;
        this._decimal_as_float = true;
        this._float_nan_and_inf_as_null = true;
        this._sexp_as_list = true;
        this._skip_annotations = true;
        this._string_as_json = true;
        this._symbol_as_string = true;
        this._timestamp_as_string = true;
        this._timestamp_as_millis = false;
        this._untyped_nulls = true;
        return _private_iontextwriterbuilderMutable;
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder
    public final IonTextWriterBuilder withPrettyPrinting() {
        _Private_IonTextWriterBuilder _private_iontextwriterbuilderMutable = mutable();
        _private_iontextwriterbuilderMutable._pretty_print = true;
        return _private_iontextwriterbuilderMutable;
    }

    public _Private_IonTextWriterBuilder(_Private_IonTextWriterBuilder _private_iontextwriterbuilder) {
        super(_private_iontextwriterbuilder);
        this._callback_builder = _private_iontextwriterbuilder._callback_builder;
        this._pretty_print = _private_iontextwriterbuilder._pretty_print;
        this._blob_as_string = _private_iontextwriterbuilder._blob_as_string;
        this._clob_as_string = _private_iontextwriterbuilder._clob_as_string;
        this._decimal_as_float = _private_iontextwriterbuilder._decimal_as_float;
        this._float_nan_and_inf_as_null = _private_iontextwriterbuilder._float_nan_and_inf_as_null;
        this._sexp_as_list = _private_iontextwriterbuilder._sexp_as_list;
        this._skip_annotations = _private_iontextwriterbuilder._skip_annotations;
        this._string_as_json = _private_iontextwriterbuilder._string_as_json;
        this._symbol_as_string = _private_iontextwriterbuilder._symbol_as_string;
        this._timestamp_as_millis = _private_iontextwriterbuilder._timestamp_as_millis;
        this._timestamp_as_string = _private_iontextwriterbuilder._timestamp_as_string;
        this._untyped_nulls = _private_iontextwriterbuilder._untyped_nulls;
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public _Private_IonTextWriterBuilder mutable() {
        return new Mutable(this);
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder, com.amazon.ion.system.IonWriterBuilderBase
    public IonTextWriterBuilder copy() {
        return new Mutable(this);
    }

    @Override // com.amazon.ion.system.IonTextWriterBuilder
    public final IonWriter build(Appendable appendable) {
        return fillDefaults().build((_Private_FastAppendable) new AppendableFastAppendable(appendable));
    }

    public _Private_IonTextWriterBuilder() {
    }

    @Override // com.amazon.ion.system.IonWriterBuilder
    public final IonWriter build(OutputStream outputStream) {
        return fillDefaults().build((_Private_FastAppendable) new OutputStreamFastAppendable(outputStream));
    }

    public _Private_IonTextWriterBuilder(AnonymousClass1 anonymousClass1) {
    }
}
