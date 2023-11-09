package com.ferri.userapp.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ferri.userapp.R
import com.ferri.userapp.utils.Constants.BASE_URL
import com.ferri.userapp.utils.LoadingDialog
import com.ferri.userapp.utils.mylog
//import com.github.barteksc.pdfviewer.PDFView
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class ViewInvoiceActivity : AppCompatActivity() {

    var ivBack: ImageView? = null
//    var pdfView: PDFView? = null
    private val TAG="ViewInvoiceActivity"

    var url="$BASE_URL/users/invoice/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viwe_invoice)
        ivBack=findViewById(R.id.ivBack)
        ivBack!!.setOnClickListener { finish() }

//        pdfView = findViewById(R.id.idPDFView)


        try {
            if (intent != null) {
               val pnrNo = intent.getStringExtra("pnrNo").toString()
                Log.i(TAG, "onCreate: pnrNo=$pnrNo")
                url=url+pnrNo
            }
        } catch (e: Exception) {
            mylog(TAG, "onCreate: error=${e.localizedMessage}")
        }

//        loadInvoice(url)

    }

    /*private fun loadInvoice(url: String) {
        try {
            val executor: ExecutorService = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())

            LoadingDialog.showLoadingDialog(this,"Loading....")
            executor.execute {
                var inputStream: InputStream? = null
                try {
                    val url = URL(url)
                    val urlConnection = url.openConnection() as HttpURLConnection
                    if (urlConnection.getResponseCode() === 200) {
                        inputStream = BufferedInputStream(urlConnection.getInputStream())
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                handler.post {
                    pdfView!!.fromStream(inputStream).load()
                    LoadingDialog.cancelLoading()
                }
            }
        }catch (e:Exception){
            Log.i(TAG, "loadInvoice: Error=${e.localizedMessage}")}
    }*/

}