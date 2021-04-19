package com.tc99.echo_music.fragments

import android.app.Activity
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import com.cleveroad.audiovisualization.AudioVisualization
import com.cleveroad.audiovisualization.GLAudioVisualizationView
import com.tc99.echo_music.CurrentSongHelper
import com.tc99.echo_music.Songs
import com.tc99.echo_music.databases.EchoDatabase
import java.util.ArrayList
import java.util.concurrent.TimeUnit


class SongPlayingFragment {
    object Statified {
        var myActivity: Activity? = null
        var mediaPlayer: MediaPlayer? = null

        var startTimeText: TextView? = null
        var endTimeText: TextView? = null
        var playPauseImageButton: ImageButton? = null
        var previousImageButton: ImageButton? = null
        var nextImageButton: ImageButton? = null
        var loopImageButton: ImageButton? = null
        var shuffleImageButton: ImageButton? = null
        var seekbar: SeekBar? = null
        var songArtistView: TextView? = null
        var songTitleView: TextView? = null

        var currentPosition: Int = 0
        var fetchSongs: ArrayList<Songs>? = null
        var currentSongHelper: CurrentSongHelper? = null
        var audioVisualization: AudioVisualization? = null
        var glView: GLAudioVisualizationView? = null

        var fab: ImageButton? = null
        var favoriteContent: EchoDatabase? = null
        var mSensorManager: SensorManager? = null
        var mSensorListener: SensorEventListener? = null
        var MY_PREFS_NAME = "ShakeFeature"

        var updateSongTime = object : Runnable {
            override fun run() {
                val getcurrent = mediaPlayer?.currentPosition
                startTimeText?.setText(
                    String.format(
                        "%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes(getcurrent?.toLong() as Long),
                        TimeUnit.MILLISECONDS.toSeconds(getcurrent?.toLong() as Long) -
                                TimeUnit.MILLISECONDS.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(
                                        getcurrent?.toLong() as Long
                                    )
                                )
                    )
                )
                Handler().postDelayed(this, 1000)
            }

        }

    }

}
