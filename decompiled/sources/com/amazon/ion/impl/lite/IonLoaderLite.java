package com.amazon.ion.impl.lite;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonException;
import com.amazon.ion.IonLoader;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonSystem;
import com.amazon.ion.impl._Private_IncrementalReader;
import com.amazon.ion.impl._Private_IonWriterBase;
import com.amazon.ion.impl._Private_IonWriterFactory;
import com.amazon.ion.system.IonReaderBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
public final class IonLoaderLite implements IonLoader {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public final IonCatalog _catalog;
    public final IonReaderBuilder _readerBuilder;
    public final IonSystemLite _system;

    public IonLoaderLite(IonSystemLite ionSystemLite, IonCatalog ionCatalog) {
        this._system = ionSystemLite;
        this._catalog = ionCatalog;
        if (ionCatalog == ionSystemLite._catalog) {
            this._readerBuilder = ionSystemLite.myReaderBuilder;
        } else {
            this._readerBuilder = ionSystemLite.myReaderBuilder.withCatalog(ionCatalog).immutable();
        }
    }

    @Override // com.amazon.ion.IonLoader
    public IonCatalog getCatalog() {
        return this._catalog;
    }

    @Override // com.amazon.ion.IonLoader
    public IonSystem getSystem() {
        return this._system;
    }

    @Override // com.amazon.ion.IonLoader
    public IonDatagram load(File file) throws IOException, IonException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return load(fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    public final IonDatagramLite load_helper(IonReader ionReader) throws IOException {
        IonDatagramLite ionDatagramLite = new IonDatagramLite(this._system, this._catalog);
        ((_Private_IonWriterBase) _Private_IonWriterFactory.makeWriter(ionDatagramLite._system._catalog, ionDatagramLite)).writeValues(ionReader);
        return ionDatagramLite;
    }

    @Override // com.amazon.ion.IonLoader
    public IonDatagram load(String str) throws IonException {
        try {
            return load_helper(this._readerBuilder.build(str));
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.ion.IonLoader
    public IonDatagram load(Reader reader) throws IOException, IonException {
        try {
            return load_helper(this._readerBuilder.build(reader));
        } catch (IonException e) {
            IOException iOException = (IOException) e.causeOfType(IOException.class);
            if (iOException != null) {
                throw iOException;
            }
            throw e;
        }
    }

    @Override // com.amazon.ion.IonLoader
    public IonDatagram load(byte[] bArr) throws IonException {
        IonReader ionReaderBuild = this._readerBuilder.build(bArr, 0, bArr.length);
        try {
            IonDatagram ionDatagramLoad = load(ionReaderBuild);
            try {
                ionReaderBuild.close();
                return ionDatagramLoad;
            } catch (IOException e) {
                throw new IonException(e.getMessage(), e);
            }
        } catch (Throwable th) {
            try {
                ionReaderBuild.close();
                throw th;
            } catch (IOException e2) {
                throw new IonException(e2.getMessage(), e2);
            }
        }
    }

    @Override // com.amazon.ion.IonLoader
    public IonDatagram load(InputStream inputStream) throws IOException, IonException {
        IonReader ionReaderBuild = null;
        try {
            try {
                ionReaderBuild = this._readerBuilder.build(inputStream);
                return load(ionReaderBuild);
            } catch (IonException e) {
                IOException iOException = (IOException) e.causeOfType(IOException.class);
                if (iOException != null) {
                    throw iOException;
                }
                throw e;
            }
        } finally {
            if (this._readerBuilder.isIncrementalReadingEnabled() && (ionReaderBuild instanceof _Private_IncrementalReader)) {
                ((_Private_IncrementalReader) ionReaderBuild).requireCompleteValue();
            }
        }
    }

    @Override // com.amazon.ion.IonLoader
    public IonDatagram load(IonReader ionReader) throws IonException {
        try {
            return load_helper(ionReader);
        } catch (IOException e) {
            throw new IonException(e.getMessage(), e);
        }
    }
}
