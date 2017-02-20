package com.example.utsavstha.starfield;

import android.content.Context;
import android.content.res.AssetManager;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by utsavstha on 12/25/16.
 */


/*
* This class is used to read and write files in the storage of the device,
* used for caching data offline.
* */
public class FileHandler {
    /**
     * @param fileName : file name for the file to be created or written data in
     * @param data : data to be written in the file
     *
     */

    public void writeToFile(String fileName,String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            //AppLog.e("Exception", "File write failed: " + e.toString());
        }
    }

    public String readFromFile(String fileName,Context context) {

        String ret = "";
        AssetManager am = context.getAssets();
        try {
            InputStream inputStream = am.open(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            //AppLog.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
           // AppLog.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
