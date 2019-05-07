package com.stu.stu

/**
 * Created by Xuezhi on 2019/4/18.
 * Email : xuezhi123go@163.cn
 *
 */
data class UrlBean(
        val message: String
)

data class Message(
        val answer: String,
        val answertime: String,
        val img_dir: String,
        val problemid: String,
        val studentname: String,
        val problemurl: String,
        val command: String
)