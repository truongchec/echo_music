package com.tc99.echo_music.fragments

import android.app.Activity
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.tc99.echo_music.Songs
import java.util.ArrayList

class MainScreenFragment : Fragment() {

    var nowPlayingBottomBar: RelativeLayout? = null
    var playpauseButton: ImageButton? = null
    var songTitle: TextView? = null
    var visibleLayout: RelativeLayout? = null
    var noSongs: RelativeLayout? = null
    var recyclerView: RecyclerView? = null
    var myActivity: Activity? = null

    var getSongsList: ArrayList<Songs>? = null
    var _mainScreenAdapter: MainScreenAdapter? = null

}