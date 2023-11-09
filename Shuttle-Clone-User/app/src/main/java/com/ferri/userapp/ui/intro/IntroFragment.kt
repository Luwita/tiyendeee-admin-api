package com.ferri.userapp.ui.intro


import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ferri.userapp.R
import com.ferri.userapp.ui.activity.SplashActivity
import com.ferri.userapp.ui.events.ActionEvents
import com.ferri.userapp.utils.Constants
import com.ferri.userapp.utils.savePreference
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class IntroFragment : Fragment() {

    private val TAG = "IntroFragment"

    private var fragmentPosition = 0
    private var introBackgroundIMG: ImageView? = null
    private var introAnimIMG: android.widget.ImageView? = null
    private var animIMG: Animation? = null
    private var animBgIMG: Animation? = null
    private var animTV: Animation? = null
    private var animBTN: Animation? = null

    private lateinit var letsGoBTN: Button

    var introTextView: TextView? = null
    private val introText = arrayOf(
        "Welcome To ",
        "Estimate Time Of Bus",
        "Live Location Of Buses",
        "Find Route In Local City"
    )

    fun getInstance(position: Int): Fragment? {
        val f = IntroFragment()
        val args = Bundle()
        args.putInt("position", position)
        f.arguments = args
        return f
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Inflate the layout for this fragment

        fragmentPosition = requireArguments().getInt("position")

        // Log.i(IntroFragment.TAG, "onCreateView: fragmentPosition=$fragmentPosition")

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        introAnimIMG = view.findViewById(R.id.intro_anim_img)
        introBackgroundIMG = view.findViewById(R.id.intro_bg_img)
        introTextView = view.findViewById(R.id.intro_tv)
        letsGoBTN = view.findViewById(R.id.letsgo_btn)

        letsGoBTN.setOnClickListener(View.OnClickListener {

            savePreference(requireActivity(), Constants.FirstTimeUser, "NO")
            requireActivity().startActivity(
                Intent(
                    activity,
                    SplashActivity::class.java
                )
            )
            requireActivity().finish()

        })

        if (fragmentPosition == 0) {
            Glide.with(requireActivity()).load(R.drawable.anim_bus).into(introAnimIMG!!)
            Glide.with(requireActivity()).load(R.drawable.screen_1).into(introBackgroundIMG!!)
            introTextView?.setText(introText[0]+" ${getString(R.string.app_name)}")
            animIMG = AnimationUtils.loadAnimation(
                activity,
                R.anim.slide_in_left
            ) // Create the animation.
            introAnimIMG?.startAnimation(animIMG)


            animTV = AnimationUtils.loadAnimation(
                activity,
                R.anim.fade_in
            ) // Create the animation.
            introTextView?.startAnimation(animTV)
        }


    }

    fun startAnimation(position: Int) {
        when (position) {
            0 -> {
                introAnimIMG!!.visibility = View.VISIBLE
                introTextView!!.text = introText[0]+" ${getString(R.string.app_name)}"
                Glide.with(requireActivity()).load(R.drawable.anim_bus).into(introAnimIMG!!)
                Glide.with(requireActivity()).load(R.drawable.screen_1)
                    .into(introBackgroundIMG!!)
                letsGoBTN.visibility = View.GONE

                animIMG = AnimationUtils.loadAnimation(
                        activity,
                        R.anim.slide_in_left
                ) // Create the animation.
                introAnimIMG!!.startAnimation(animIMG)
            }
            1 -> {
                introAnimIMG!!.visibility = View.GONE
                introTextView!!.text = introText[1]
//                Glide.with(requireActivity()).load(R.drawable.anim_bus).into(introAnimIMG!!)
                Glide.with(requireActivity()).load(R.drawable.screen_2)
                    .into(introBackgroundIMG!!)
                letsGoBTN.visibility = View.GONE

            }
            2 -> {
                introAnimIMG!!.visibility = View.GONE
                introTextView!!.text = introText[2]
//                Glide.with(requireActivity()).load(R.drawable.anim_bus).into(introAnimIMG!!)
                Glide.with(requireActivity()).load(R.drawable.screen_3)
                    .into(introBackgroundIMG!!)
                letsGoBTN.visibility = View.GONE

            }
            3 -> {
                introAnimIMG!!.visibility = View.GONE
                introTextView!!.text = introText[3]
//                Glide.with(requireActivity()).load(R.drawable.anim_bus).into(introAnimIMG!!)
                Glide.with(requireActivity()).load(R.drawable.screen_4)
                    .into(introBackgroundIMG!!)
                letsGoBTN.visibility = View.VISIBLE
                animBTN = AnimationUtils.loadAnimation(
                    activity,
                    R.anim.slide_in_up
                ) // Create the animation.
                letsGoBTN.startAnimation(animBTN)

            }
        }

        animTV = AnimationUtils.loadAnimation(
            activity,
            R.anim.fade_in
        ) // Create the animation.
        introTextView!!.startAnimation(animTV)
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(pusher: ActionEvents) {
        when (pusher.getActionEvent()) {
            0 -> startAnimation(pusher.getActionEvent())
            1 -> startAnimation(pusher.getActionEvent())
            2 -> startAnimation(pusher.getActionEvent())
            3 -> startAnimation(pusher.getActionEvent())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

}
