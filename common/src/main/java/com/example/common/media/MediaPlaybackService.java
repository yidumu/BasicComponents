package com.example.common.media;

import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.MediaBrowserServiceCompat;

import com.example.common.library.MusicSource;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector;
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator;

import java.util.ArrayList;
import java.util.List;

public class MediaPlaybackService extends MediaBrowserServiceCompat {
    private static final String TAG = "MediaPlaybackService";
    private static final String MY_MEDIA_ROOT_ID = "media_root_id";
    private static final String MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id";
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        initializePlayer();

        // Create a MediaSessionCompat
        mediaSession = new MediaSessionCompat(this, TAG);

        // Enable callbacks from MediaButtons and TransportControls
        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mediaSession.setPlaybackState(stateBuilder.build());

        // Set the session's token so that client activities can communicate with it.
        setSessionToken(mediaSession.getSessionToken());

        //ExoPlayer will manage the MediaSession for us.
        MediaSessionConnector mediaSessionConnector = new MediaSessionConnector(mediaSession);
        mediaSessionConnector.setPlaybackPreparer(new UampPlaybackPreparer());
        mediaSessionConnector.setQueueNavigator(new UampQueueNavigator(mediaSession));
        mediaSessionConnector.setPlayer(exoPlayer);
    }

    //????????????
    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        return new BrowserRoot(MY_MEDIA_ROOT_ID, null);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaItem>> result) {
        List<MediaItem> mediaItems = new ArrayList<>();
        result.sendResult(mediaItems);
    }

    private SimpleExoPlayer exoPlayer;

    private void initializePlayer() {
        exoPlayer = new SimpleExoPlayer.Builder(this).build();
        exoPlayer.addListener(mEventListener);
    }


    private void preparePlaylist(boolean playWhenReady) {
        exoPlayer.setPlayWhenReady(playWhenReady);
        exoPlayer.stop(true);
        //exoPlayer.addMediaItem();
        exoPlayer.prepare();

    }

    /**
     * ExoPlayer Event ??????
     */
    Player.EventListener mEventListener = new Player.EventListener() {
        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.d(TAG, "onPlayerError() called with: error = [" + error + "]");
        }

        @Override
        public void onPlaybackStateChanged(int state) {
            Log.d(TAG, "onPlaybackStateChanged() called with: state = [" + state + "]");
            switch (state) {
                case Player.STATE_IDLE:
                case Player.STATE_BUFFERING:
                case Player.STATE_READY:
                case Player.STATE_ENDED:
            }
        }

        @Override
        public void onMediaItemTransition(@Nullable com.google.android.exoplayer2.MediaItem mediaItem, int reason) {
            Log.d(TAG, "onMediaItemTransition() called with: mediaItem = [" + mediaItem + "], reason = [" + reason + "]");
        }

        @Override
        public void onPositionDiscontinuity(int reason) {
            Log.d(TAG, "onPositionDiscontinuity() called with: reason = [" + reason + "]");
        }

        @Override
        public void onIsPlayingChanged(boolean isPlaying) {
            Log.d(TAG, "onIsPlayingChanged() called with: isPlaying = [" + isPlaying + "]");
        }
    };

    private class UampPlaybackPreparer implements MediaSessionConnector.PlaybackPreparer {

        @Override
        public long getSupportedPrepareActions() {
            return PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID |
                    PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID |
                    PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH |
                    PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }

        @Override
        public void onPrepare(boolean playWhenReady) {
            Log.d(TAG, "onPrepare() called with: playWhenReady = [" + playWhenReady + "]");
            onPrepareFromMediaId(null, playWhenReady, null);
        }

        @Override
        public void onPrepareFromMediaId(String mediaId, boolean playWhenReady, @Nullable Bundle extras) {
            Log.d(TAG, "onPrepareFromMediaId: ");
            preparePlaylist(playWhenReady);
        }

        @Override
        public void onPrepareFromSearch(String query, boolean playWhenReady, @Nullable Bundle extras) {
            Log.d(TAG, "onPrepareFromSearch: ");
        }

        @Override
        public void onPrepareFromUri(Uri uri, boolean playWhenReady, @Nullable Bundle extras) {
            Log.d(TAG, "onPrepareFromUri: ");
        }

        @Override
        public boolean onCommand(Player player, ControlDispatcher controlDispatcher, String command, @Nullable Bundle extras, @Nullable ResultReceiver cb) {
            Log.d(TAG, "onCommand: ");
            return false;
        }
    }

    private class UampQueueNavigator extends TimelineQueueNavigator {

        public UampQueueNavigator(MediaSessionCompat mediaSession) {
            super(mediaSession);
        }

        @Override
        public MediaDescriptionCompat getMediaDescription(Player player, int windowIndex) {
            return null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        exoPlayer.removeListener(mEventListener);
        exoPlayer.release();
    }
}

