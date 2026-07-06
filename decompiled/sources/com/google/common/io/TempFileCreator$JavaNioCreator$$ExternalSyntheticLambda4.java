package com.google.common.io;

import com.google.common.io.TempFileCreator;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class TempFileCreator$JavaNioCreator$$ExternalSyntheticLambda4 implements TempFileCreator.JavaNioCreator.PermissionSupplier {
    @Override // com.google.common.io.TempFileCreator.JavaNioCreator.PermissionSupplier
    public final FileAttribute get() {
        return PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rw-------"));
    }
}
