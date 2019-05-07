package com.stu.stu

import android.app.Application
import android.content.Intent
import android.util.Log
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.neovisionaries.ws.client.*
import java.io.IOException
import java.lang.Thread.sleep

/**
 * Created by Xuezhi on 2019/4/17.
 * Email : xuezhi123go@163.cn
 *
 */
class MyApp : Application() {
    var url: String = "ws://192.168.1.126:8081/ws/app/"
    var CONNECT_TIMEOUT: Int = 10000
    var FRAME_QUEUE_SIZE: Int = 5
    private var mThread: Thread? = null
    var mInterrupt: Boolean = false
    //var json: String = "{'message':{'problemid': problemid, 'answer': answer,'img_dir':img_dir,'studentname': studentname, 'answertime': answertime}}"

    override fun onCreate() {
        super.onCreate()
        startWebSocket()
    }

    fun startWebSocket() {
        try {
            //var ws =
            Log.e("WebSocketActivity", "startWebSocket")
            WebSocketFactory()
                    .createSocket(url, CONNECT_TIMEOUT) //ws地址，和设置超时时间
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(WsListener())//添加回调监听
                    .connectAsynchronously()//异步连接

        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("WebSocketActivity", e.message)
        }
    }

    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    internal inner class WsListener : WebSocketAdapter() {
        @Throws(Exception::class)
        override fun onTextMessage(websocket: WebSocket?, text: String?) {
            super.onTextMessage(websocket, text)
            /*logger.info(text)
            val msgs = text!!.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (msgs.size >= 2) {
                NotificationShow(msgs[0], msgs[1])
                sendReceiveMessageBroadcast(msgs[0], msgs[1])
            }*/
            if (null != text) {
                Log.e("WebSocketActivity", "收到的消息是：" + text)
                toKotson(text)
            }

        }

        @Throws(Exception::class)
        override fun onConnected(websocket: WebSocket?, headers: Map<String, List<String>>?) {
            super.onConnected(websocket, headers)
            Log.e("WebSocketActivity", "连接成功")
            mInterrupt = true
        }

        @Throws(Exception::class)
        override fun onConnectError(websocket: WebSocket?, exception: WebSocketException?) {
            super.onConnectError(websocket, exception)
            Log.e("WebSocketActivity", "连接错误：" + exception!!.message)
        }

        @Throws(Exception::class)
        override fun onDisconnected(
                websocket: WebSocket?,
                serverCloseFrame: WebSocketFrame?,
                clientCloseFrame: WebSocketFrame?,
                closedByServer: Boolean
        ) {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer)
            Log.e("WebSocketActivity", "断开连接")
            mInterrupt = false
            startThread()
        }

        fun startThread() {
            // Stop the previous session by interrupting the thread.
            if (mThread != null) {
                mThread?.interrupt()
            }
            //Report analyzer working thread
            mThread = Thread(Runnable {
                try {
                    while (!mInterrupt) {
                        startWebSocket()
                        sleep(2000)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (mInterrupt) mThread?.interrupt()
            }, "ThreadRunnable")

            //start the service
            mThread?.start()
        }

        fun toKotson(url: String) {
            try {
                var s: UrlBean = Gson().fromJson(url)
                var message: String = s.message
                var a: Message = Gson().fromJson(message)
                // var s = Gson().fromJson<A1>(jsonString)

                Log.e("WebSocketActivity", "url是：" + a.problemurl)
                if (null != a.problemurl) {
                    showPopup(a.problemurl)
                }

                //var sarray = Gson().fromJson<List<A1>>(jsonArrayString)
                //var sarray:List<A1> = Gson().fromJson(jsonArray)

                //println("s = $s   sarray=$sarray")
            } catch (e: Exception) {
                println(e)
                Log.e("WebSocketActivity", "异常：" + e.message)
            }

        }

    }

    fun showPopup(url: String) {

        val intent = Intent(this, PopupActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
        //cordova.startActivityForResult(this,intent,1);
        //MyApplication.myApplication.startActivity(intent);

    }
}