package com.amazon.livingroom.di;

import android.view.SurfaceView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.ignitionshared.MainActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes.dex */
@Module(includes = {Bindings.class})
public class MainActivityModule {
    public final MainActivity activity;
    public final SurfaceView igniteSurfaceView;

    /* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
    @Module
    public interface Bindings {
        @Binds
        @Named(Names.IGNITE_SURFACE)
        View bindIgniteView(@Named(Names.IGNITE_SURFACE) SurfaceView surfaceView);
    }

    public MainActivityModule(MainActivity mainActivity, SurfaceView surfaceView) {
        this.activity = mainActivity;
        this.igniteSurfaceView = surfaceView;
    }

    @Provides
    public AppCompatActivity provideActivity() {
        return this.activity;
    }

    @Provides
    @Named(Names.IGNITE_SURFACE)
    public SurfaceView provideIgniteSurfaceView() {
        return this.igniteSurfaceView;
    }
}
