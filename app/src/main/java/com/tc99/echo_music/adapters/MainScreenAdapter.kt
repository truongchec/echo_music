package com.tc99.echo_music.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.tc99.echo_music.R
import com.tc99.echo_music.Songs
import com.tc99.echo_music.fragments.SongPlayingFragment

class MainScreenAdapter(_songDetails: ArrayList<Songs>, _context: Context) :
    RecyclerView.Adapter<MainScreenAdapter.MyViewHolder>() {
    var songDetails: ArrayList<Songs>? = null
    var mContext: Context? = null

    init {
        this.songDetails = _songDetails
        this.mContext = _context
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainScreenAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_custom_mainscreen_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainScreenAdapter.MyViewHolder, position: Int) {
      val songObject=songDetails?.get(position)
        holder?.trackTitle?.text=songObject?.songTitle
        holder?.trackArtist?.text = songObject?.artist
        holder?.contentHolder?.setOnClickListener({
            SongPlayingFragment.Statified.mediaPlayer?.pause()

            val songPlayingFragment=SongPlayingFragment()
            val args=Bundle()
            args.putString("songArtist",songObject?.artist)
            args.putString("songTitle", songObject?.songTitle)
            args.putString("path", songObject?.songData)
            args.putInt("songId",songObject?.songId?.toInt() as Int)

            args.putInt("songPosition", position)
            args.putParcelableArrayList("songData", songDetails)
            songPlayingFragment.arguments=args
            (mContext as FragmentActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.details_fragment, songPlayingFragment)
                .addToBackStack("SongPlayingFragment")
                .commit()


        })


    }

    override fun getItemCount(): Int {
        if (songDetails == null) {
            return 0
        } else {
            return (songDetails as ArrayList<Songs>).size
        }
        TODO("Not yet implemented")
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var trackTitle: TextView? = null
        var trackArtist: TextView? = null
        var contentHolder: RelativeLayout? = null

        init {
            trackTitle = itemView?.findViewById<TextView>(R.id.trackTitle)
            trackArtist = itemView?.findViewById<TextView>(R.id.trackArtist)
            contentHolder = itemView?.findViewById<RelativeLayout>(R.id.contentRow)

        }
    }
}