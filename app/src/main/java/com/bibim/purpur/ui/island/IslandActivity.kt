package com.bibim.purpur.ui.island

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bibim.purpur.R
import com.bibim.purpur.base.BaseActivity
import com.bibim.purpur.databinding.ActivityIslandBinding
import com.bibim.purpur.onlyOneClickListener
import com.bibim.purpur.ui.Loading
import com.bibim.purpur.ui.detail.main.DetailActivity
import com.bibim.purpur.ui.islandSelect.IslandSelectActivity
import kotlinx.android.synthetic.main.activity_island.*
import org.koin.android.viewmodel.ext.android.viewModel


class IslandActivity :BaseActivity<ActivityIslandBinding>() {

    override val layoutResID: Int = R.layout.activity_island
    private val viewModel: IslandViewModel by viewModel()
    lateinit var animalImageList: List<ImageView>
    var idx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Loading.goLoading(this)
        viewDataBinding.vm = viewModel

        idx = intent.getIntExtra("islandIdx", -1)
        viewModel.getIslandInfo(idx)

        animalImageList = listOf(
            viewDataBinding.actIslandIvRabbit,
            viewDataBinding.actIslandIvBear,
            viewDataBinding.actIslandIvFox,
            viewDataBinding.actIslandIvMole,
            viewDataBinding.actIslandIvDuck,
            viewDataBinding.actIslandIvPlant
        )

        setClickListener()
        setMusic()
        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getIslandInfo(idx)
    }

    private fun setMusic(){
        val mediaPlayer = MediaPlayer.create(this, R.raw.purpur_bgm)
        mediaPlayer.isLooping = true
        val manager = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (!manager.isMusicActive) {
            mediaPlayer.start()
        }
    }

    private fun observe() {
        viewModel.islandInfo.observe(this, Observer {
            Log.e("it -> ", it.islandProgress.toString())
            val progressAnimator =
                ObjectAnimator.ofInt(act_main_pb, "progress", 0, it.islandProgress)
            progressAnimator.duration = 1000
            val ll = LinearInterpolator()
            progressAnimator.interpolator = ll
            progressAnimator.start()

            val handler = Handler()
            handler.postDelayed({ Loading.exitLoading() }, 1000)
            Loading.exitLoading()
        })
    }

    private fun setClickListener() {
        for (animalIndex in animalImageList.indices) {
            animalImageList[animalIndex].setOnClickListener {
                val goMission = Intent(this, DetailActivity::class.java)
                Log.e("animalNum",viewModel.animalList.value?.get(animalIndex)!!.animalIdx.toString())
                goMission.putExtra(
                    "animalIdx",
                    viewModel.animalList.value?.get(animalIndex)!!.animalIdx
                )
                startActivity(goMission)
            }
        }

        viewDataBinding.actMainIvSetting.onlyOneClickListener {
            val intent = Intent(this, IslandSelectActivity::class.java)
            startActivity(intent)
        }
    }

}
