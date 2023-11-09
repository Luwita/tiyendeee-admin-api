package com.ferri.driver.Adapters

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ferri.driver.Activity.NotificationActivity
import com.ferri.driver.Model.NotificationDataItem
import com.ferri.driver.R
import com.ferri.driver.Util.clickWithThrottle

class NotificationListAdapter(
    val mContext: Context,
    val listner: NotificationActivity,
    val notificationData: List<NotificationDataItem>
) : RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {

    val TAG="NotificationAdaptor"

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cvNotifications = view.findViewById<CardView>(R.id.cvNotifications)
        val tvNotificationTitle = view.findViewById<TextView>(R.id.tvNotificationTitle)
        val tvNotificationBody = view.findViewById<TextView>(R.id.tvNotificationBody)
        val viewReadIndicator = view.findViewById<View>(R.id.viewReadIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.notification_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            try {
                tvNotificationTitle.text=notificationData.get(position)!!.content
                tvNotificationBody.text=notificationData.get(position)!!.content
                if (notificationData.get(position).read==1){viewReadIndicator.setBackgroundColor(listner.resources.getColor(R.color.light_grey))}
                else viewReadIndicator.setBackgroundColor(listner.resources.getColor(R.color.colorAccent))

                cvNotifications.clickWithThrottle {

                    if (notificationData.get(position).read!=1)listner.updateNotificationStatus(notificationData.get(position).id)

                    openNotification(notificationData.get(position))
                }
            }catch (e:Exception){
                Log.i(TAG, "onBindViewHolder: Error=${e.localizedMessage}")}

        }

    }

    private fun openNotification(data: NotificationDataItem) {
        try {
            val view: View = listner.layoutInflater.inflate(R.layout.default_dailog_layout, null)
            val dialog = Dialog(listner, R.style.CustomBottomSheetDialogTheme)

            var title = data.content
            var body = data.content

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.findViewById<TextView>(R.id.tvHeading).text = Html.fromHtml(
                    title,
                    Html.FROM_HTML_MODE_COMPACT
                )
                view.findViewById<TextView>(R.id.tvBody).text = Html.fromHtml(
                    body,
                    Html.FROM_HTML_MODE_COMPACT
                )
            } else {
                view.findViewById<TextView>(R.id.tvHeading).text = Html.fromHtml(title)
                view.findViewById<TextView>(R.id.tvBody).text = Html.fromHtml(body)
            }

            view.findViewById<ImageView>(R.id.imgClose)?.clickWithThrottle {
                dialog.dismiss()
            }

            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()

        }catch (e:Exception){
            Log.i(TAG, "openNotification: Error=${e.localizedMessage}")}
    }

    override fun getItemCount(): Int {
        return notificationData!!.size
    }
}