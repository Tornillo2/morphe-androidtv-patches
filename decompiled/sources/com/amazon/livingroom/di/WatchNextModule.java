package com.amazon.livingroom.di;

import android.content.Context;
import androidx.tvprovider.media.tv.PreviewChannelHelper;
import dagger.Module;
import dagger.Provides;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Module
public interface WatchNextModule {

    /* JADX INFO: renamed from: com.amazon.livingroom.di.WatchNextModule$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    public final /* synthetic */ class CC {
        @ApplicationScope
        @Provides
        public static PreviewChannelHelper providePreviewChannelHelper(@ApplicationContext Context context) {
            return new PreviewChannelHelper(context);
        }
    }
}
