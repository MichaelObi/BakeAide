package xyz.michaelobi.bakeaide.presentation.recipeStep;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.data.models.Step;
import xyz.michaelobi.bakeaide.databinding.FragmentRecipeStepBinding;

/**
 * BakeAide
 * Michael Obi
 * 24 06 2017 7:16 PM
 */

public class RecipeStepFragment extends Fragment implements ExoPlayer.EventListener {

    SimpleExoPlayer exoPlayer;
    Step step;
    private FragmentRecipeStepBinding binding;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;

    public static RecipeStepFragment newInstance(Step step) {
        Bundle arguments = new Bundle();
        arguments.putParcelable("step", step);
        RecipeStepFragment fragment = new RecipeStepFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_step, container,
                false);
        View view = binding.getRoot();
        step = getArguments().getParcelable("step");
        binding.recipeStepDesc.setText(step.getDescription());
        if (step.getThumbnailURL() != null && !step.getThumbnailURL().isEmpty()) {
            binding.recipeStepImage.setVisibility(View.VISIBLE);
            Picasso.with(getActivity())
                    .load(step.getThumbnailURL())
                    .into(binding.recipeStepImage);
        } else {
            binding.recipeStepImage.setVisibility(View.GONE);
        }
        if (step.getVideoURL() != null && !step.getVideoURL().isEmpty()) {
            initializeVideoPlayer(Uri.parse(step.getVideoURL()));
        } else {
            binding.recipeStepVideo.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    private void initializeMediaSession() {
        mediaSession = new MediaSessionCompat(getContext(), this.getClass().getSimpleName());
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY | PlaybackStateCompat.ACTION_PAUSE |
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                exoPlayer.seekTo(0);
            }
        });
        mediaSession.setActive(true);
    }

    private void initializeVideoPlayer(Uri videoUri) {
        initializeMediaSession();
        if (exoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            binding.recipeStepVideo.setPlayer(exoPlayer);
            exoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getContext(), "StepVideo");
            MediaSource mediaSource = new ExtractorMediaSource(videoUri,
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(),
                    null,
                    null);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == ExoPlayer.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, exoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == ExoPlayer.STATE_READY)) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        Toast.makeText(getActivity(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositionDiscontinuity() {
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }
}
