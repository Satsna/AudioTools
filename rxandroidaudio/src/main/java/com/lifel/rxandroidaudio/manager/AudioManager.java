package com.lifel.rxandroidaudio.manager;

import com.github.piasy.rxandroidaudio.PlayConfig;
import com.github.piasy.rxandroidaudio.RxAudioPlayer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;

public class AudioManager {

    private static AudioManager manager;

    private RxAudioPlayer mRxAudioPlayer;
    private CompositeDisposable compositeDisposable;
    private AudioListener audioListener;

    private AudioManager() {
        init();
    }

    private void init() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        if (mRxAudioPlayer == null) {
            mRxAudioPlayer = RxAudioPlayer.getInstance();
        }
    }

    public static synchronized AudioManager getInstance() {
        if (manager == null) {
            manager = new AudioManager();
        }
        return manager;
    }

    /**
     * 播放音频
     */
    public void play(PlayConfig playConfig) {
        compositeDisposable
                .add(mRxAudioPlayer.play(playConfig)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(Functions.emptyConsumer(), Throwable::printStackTrace,
                                this::onComplete));
    }

    /**
     * 播放音频
     */
    public void play(PlayConfig playConfig, AudioListener audioListener) {
        setAudioListener(audioListener);
        play(playConfig);
    }

    /**
     * 停止音频
     */
    public void stop() {
        mRxAudioPlayer.stopPlay();
    }

    public void onComplete() {
        if (audioListener != null) {
            audioListener.onComplete();
        }
    }


    public AudioListener getAudioListener() {
        return audioListener;
    }

    public void setAudioListener(AudioListener audioListener) {
        this.audioListener = audioListener;
    }

    public interface AudioListener {
        void onComplete();
    }
}
