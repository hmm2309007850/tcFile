/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.stu.stu;

import android.content.Intent;
import android.os.Bundle;

import org.apache.cordova.CordovaActivity;

public class MainActivity extends CordovaActivity {


    String baidu = "https://m.baidu.com/";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);

        //showPopup(baidu);
    }

    public void showPopup(String url) {

        Intent intent = new Intent(this, PopupActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        //cordova.startActivityForResult(this,intent,1);
        //MyApplication.myApplication.startActivity(intent);

    }

}
