package com.google.android.exoplayer2;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-11d7710e1e89b9f435e4c01ffffd6a5bc78c9d6db2bbad6c6777697ebd4119c9 */
/* JADX INFO: loaded from: classes3.dex */
public abstract class BasePlayer implements Player {
    public final Timeline.Window window = new Timeline.Window();

    @Override // com.google.android.exoplayer2.Player
    public final void addMediaItem(int i, MediaItem mediaItem) {
        addMediaItems(i, ImmutableList.of(mediaItem));
    }

    @Override // com.google.android.exoplayer2.Player
    public final void addMediaItems(List<MediaItem> list) {
        addMediaItems(Integer.MAX_VALUE, list);
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean canAdvertiseSession() {
        return true;
    }

    @Override // com.google.android.exoplayer2.Player
    public final void clearMediaItems() {
        removeMediaItems(0, Integer.MAX_VALUE);
    }

    @Override // com.google.android.exoplayer2.Player
    public final int getBufferedPercentage() {
        long bufferedPosition = getBufferedPosition();
        long duration = getDuration();
        if (bufferedPosition == -9223372036854775807L || duration == -9223372036854775807L) {
            return 0;
        }
        if (duration == 0) {
            return 100;
        }
        return Util.constrainValue((int) ((bufferedPosition * 100) / duration), 0, 100);
    }

    @Override // com.google.android.exoplayer2.Player
    public final long getContentDuration() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -9223372036854775807L;
        }
        return Util.usToMs(currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window, 0L).durationUs);
    }

    @Override // com.google.android.exoplayer2.Player
    public final long getCurrentLiveOffset() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty() || currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window, 0L).windowStartTimeMs == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return (Util.getNowUnixTimeMs(this.window.elapsedRealtimeEpochOffsetMs) - this.window.windowStartTimeMs) - getContentPosition();
    }

    @Override // com.google.android.exoplayer2.Player
    @Nullable
    public final Object getCurrentManifest() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return null;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window, 0L).manifest;
    }

    @Override // com.google.android.exoplayer2.Player
    @Nullable
    public final MediaItem getCurrentMediaItem() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return null;
        }
        return currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window, 0L).mediaItem;
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final int getCurrentWindowIndex() {
        return getCurrentMediaItemIndex();
    }

    @Override // com.google.android.exoplayer2.Player
    public final MediaItem getMediaItemAt(int i) {
        return getCurrentTimeline().getWindow(i, this.window, 0L).mediaItem;
    }

    @Override // com.google.android.exoplayer2.Player
    public final int getMediaItemCount() {
        return getCurrentTimeline().getWindowCount();
    }

    @Override // com.google.android.exoplayer2.Player
    public final int getNextMediaItemIndex() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -1;
        }
        return currentTimeline.getNextWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final int getNextWindowIndex() {
        return getNextMediaItemIndex();
    }

    @Override // com.google.android.exoplayer2.Player
    public final int getPreviousMediaItemIndex() {
        Timeline currentTimeline = getCurrentTimeline();
        if (currentTimeline.isEmpty()) {
            return -1;
        }
        return currentTimeline.getPreviousWindowIndex(getCurrentMediaItemIndex(), getRepeatModeForNavigation(), getShuffleModeEnabled());
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final int getPreviousWindowIndex() {
        return getPreviousMediaItemIndex();
    }

    public final int getRepeatModeForNavigation() {
        int repeatMode = getRepeatMode();
        if (repeatMode == 1) {
            return 0;
        }
        return repeatMode;
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final boolean hasNext() {
        return hasNextMediaItem();
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean hasNextMediaItem() {
        return getNextMediaItemIndex() != -1;
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final boolean hasNextWindow() {
        return hasNextMediaItem();
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final boolean hasPrevious() {
        return hasPreviousMediaItem();
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean hasPreviousMediaItem() {
        return getPreviousMediaItemIndex() != -1;
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final boolean hasPreviousWindow() {
        return hasPreviousMediaItem();
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean isCommandAvailable(int i) {
        return getAvailableCommands().contains(i);
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean isCurrentMediaItemDynamic() {
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window, 0L).isDynamic;
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean isCurrentMediaItemLive() {
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window, 0L).isLive();
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean isCurrentMediaItemSeekable() {
        Timeline currentTimeline = getCurrentTimeline();
        return !currentTimeline.isEmpty() && currentTimeline.getWindow(getCurrentMediaItemIndex(), this.window, 0L).isSeekable;
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final boolean isCurrentWindowDynamic() {
        return isCurrentMediaItemDynamic();
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final boolean isCurrentWindowLive() {
        return isCurrentMediaItemLive();
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final boolean isCurrentWindowSeekable() {
        return isCurrentMediaItemSeekable();
    }

    @Override // com.google.android.exoplayer2.Player
    public final boolean isPlaying() {
        return getPlaybackState() == 3 && getPlayWhenReady() && getPlaybackSuppressionReason() == 0;
    }

    @Override // com.google.android.exoplayer2.Player
    public final void moveMediaItem(int i, int i2) {
        if (i != i2) {
            moveMediaItems(i, i + 1, i2);
        }
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final void next() {
        seekToNextMediaItem();
    }

    @Override // com.google.android.exoplayer2.Player
    public final void pause() {
        setPlayWhenReady(false);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void play() {
        setPlayWhenReady(true);
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final void previous() {
        seekToPreviousMediaItem();
    }

    @Override // com.google.android.exoplayer2.Player
    public final void removeMediaItem(int i) {
        removeMediaItems(i, i + 1);
    }

    public final void repeatCurrentMediaItem(int i) {
        seekTo(getCurrentMediaItemIndex(), -9223372036854775807L, i, true);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekBack() {
        seekToOffset(-getSeekBackIncrement(), 11);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekForward() {
        seekToOffset(getSeekForwardIncrement(), 12);
    }

    @VisibleForTesting(otherwise = 4)
    public abstract void seekTo(int i, long j, int i2, boolean z);

    @Override // com.google.android.exoplayer2.Player
    public final void seekTo(long j) {
        seekToCurrentItem(j, 5);
    }

    public final void seekToCurrentItem(long j, int i) {
        seekTo(getCurrentMediaItemIndex(), j, i, false);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekToDefaultPosition() {
        seekToDefaultPositionInternal(getCurrentMediaItemIndex(), 4);
    }

    public final void seekToDefaultPositionInternal(int i, int i2) {
        seekTo(i, -9223372036854775807L, i2, false);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekToNext() {
        if (getCurrentTimeline().isEmpty() || isPlayingAd()) {
            return;
        }
        if (hasNextMediaItem()) {
            seekToNextMediaItemInternal(9);
        } else if (isCurrentMediaItemLive() && isCurrentMediaItemDynamic()) {
            seekToDefaultPositionInternal(getCurrentMediaItemIndex(), 9);
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekToNextMediaItem() {
        seekToNextMediaItemInternal(8);
    }

    public final void seekToNextMediaItemInternal(int i) {
        int nextMediaItemIndex = getNextMediaItemIndex();
        if (nextMediaItemIndex == -1) {
            return;
        }
        if (nextMediaItemIndex == getCurrentMediaItemIndex()) {
            repeatCurrentMediaItem(i);
        } else {
            seekToDefaultPositionInternal(nextMediaItemIndex, i);
        }
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final void seekToNextWindow() {
        seekToNextMediaItem();
    }

    public final void seekToOffset(long j, int i) {
        long currentPosition = getCurrentPosition() + j;
        long duration = getDuration();
        if (duration != -9223372036854775807L) {
            currentPosition = Math.min(currentPosition, duration);
        }
        seekToCurrentItem(Math.max(currentPosition, 0L), i);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekToPrevious() {
        if (getCurrentTimeline().isEmpty() || isPlayingAd()) {
            return;
        }
        boolean zHasPreviousMediaItem = hasPreviousMediaItem();
        if (isCurrentMediaItemLive() && !isCurrentMediaItemSeekable()) {
            if (zHasPreviousMediaItem) {
                seekToPreviousMediaItemInternal(7);
            }
        } else if (!zHasPreviousMediaItem || getCurrentPosition() > getMaxSeekToPreviousPosition()) {
            seekToCurrentItem(0L, 7);
        } else {
            seekToPreviousMediaItemInternal(7);
        }
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekToPreviousMediaItem() {
        seekToPreviousMediaItemInternal(6);
    }

    public final void seekToPreviousMediaItemInternal(int i) {
        int previousMediaItemIndex = getPreviousMediaItemIndex();
        if (previousMediaItemIndex == -1) {
            return;
        }
        if (previousMediaItemIndex == getCurrentMediaItemIndex()) {
            repeatCurrentMediaItem(i);
        } else {
            seekToDefaultPositionInternal(previousMediaItemIndex, i);
        }
    }

    @Override // com.google.android.exoplayer2.Player
    @Deprecated
    public final void seekToPreviousWindow() {
        seekToPreviousMediaItem();
    }

    @Override // com.google.android.exoplayer2.Player
    public final void setMediaItem(MediaItem mediaItem) {
        setMediaItems(ImmutableList.of(mediaItem), true);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void setMediaItems(List<MediaItem> list) {
        setMediaItems(list, true);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void setPlaybackSpeed(float f) {
        setPlaybackParameters(getPlaybackParameters().withSpeed(f));
    }

    @Override // com.google.android.exoplayer2.Player
    public final void addMediaItem(MediaItem mediaItem) {
        addMediaItems(ImmutableList.of(mediaItem));
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekTo(int i, long j) {
        seekTo(i, j, 10, false);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void seekToDefaultPosition(int i) {
        seekToDefaultPositionInternal(i, 10);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void setMediaItem(MediaItem mediaItem, long j) {
        setMediaItems(ImmutableList.of(mediaItem), 0, j);
    }

    @Override // com.google.android.exoplayer2.Player
    public final void setMediaItem(MediaItem mediaItem, boolean z) {
        setMediaItems(ImmutableList.of(mediaItem), z);
    }
}
