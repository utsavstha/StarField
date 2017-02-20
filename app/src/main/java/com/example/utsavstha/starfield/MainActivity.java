package com.example.utsavstha.starfield;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    /*private String data = "{\n" +
            "\t\"myarray\": [{\n" +
            "\t\t\"starName\": \"Sol\",\n" +
            "\t\t\"id\": 101,\n" +
            "\t\t\"mass\": 1.9891e+30,\n" +
            "\t\t\"diameter\": 1392000,\n" +
            "\t\t\"galX\": 0,\n" +
            "\t\t\"galY\": 0,\n" +
            "\t\t\"galZ\": 0,\n" +
            "\t\t\"dist\": 0,\n" +
            "\t\t\"starType\": \"G2(V)\",\n" +
            "\t\t\"temp\": 5760,\n" +
            "\t\t\"color\": 16774636\n" +
            "\t}, {\n" +
            "\t\t\"starName\": \"Proxima Centauri\",\n" +
            "\t\t\"id\": 203,\n" +
            "\t\t\"mass\": 2.446593e+29,\n" +
            "\t\t\"diameter\": 1453,\n" +
            "\t\t\"galX\": 0.972,\n" +
            "\t\t\"galY\": -0.994,\n" +
            "\t\t\"galZ\": -0.077,\n" +
            "\t\t\"dist\": 4.22,\n" +
            "\t\t\"starType\": \"M5(V)\",\n" +
            "\t\t\"temp\": 2680,\n" +
            "\t\t\"color\": 16764277\n" +
            "\t}]}";*/

    private GLSurfaceView mGLView;
   // private List<StarDto.MyarrayBean> starDtos = new ArrayList<>();
    private List<Float> vertex = new ArrayList<>();
    List<Float> vertexData = new ArrayList<>();
    private float diameter = 0.1f/50f;
    public static int count = 0;
    static float[] vertices = new float[364 * 3];
    FileHandler fileHandler = new FileHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String data = "";

        data = fileHandler.readFromFile("onek", getApplicationContext());
        Gson gson = new GsonBuilder().create();
       // StarDto r = gson.fromJson(data, StarDto.class);
        OneKStar r = gson.fromJson(data, OneKStar.class);
        for(OneKStar.MyarrayBean d : r.getMyarray()){
            vertex.add(d.getGalX());
            vertex.add(d.getGalY());
            vertex.add(d.getGalZ());
        }
        //vertices = new float[6];

        for(int j = 0; j < 1000; j++){
            count++;
            float x,y;
            /*if(1){*/
                x = r.getMyarray().get(j).getGalX();
                y = r.getMyarray().get(j).getGalY();
                vertexData.add(x);
                vertexData.add(y);
                vertexData.add(0f);
            /*else {
                x = r.getMyarray().get(j).getGalX() / 8f;
                y = r.getMyarray().get(j).getGalY() / 8f;
                vertexData.add(x);
                vertexData.add(y);
                vertexData.add(0f);
            }*/

            /*vertices[0] = 0;
            vertices[1] = 0;
            vertices[2] = 0;*/
             for(int i =1; i < 400; i++){
                vertexData.add((float) (diameter * Math.cos((3.14/180) * (float)i ) + x));
                vertexData.add((float) (diameter * Math.sin((3.14/180) * (float)i ) + y));
                vertexData.add(0f);
               /* vertices[(i * 3)+ 0] = (float) (diameter * Math.cos((3.14/180) * (float)i ) + vertices[0]);
                vertices[(i * 3)+ 1] = (float) (diameter * Math.sin((3.14/180) * (float)i ) + vertices[1]);
                vertices[(i * 3)+ 2] = 0;*/
            }
        }
        vertices = new float[vertexData.size()];
        int i = 0;
        for(Float f: vertexData){
            vertices[i++] = f;
        }
        //vertices = vertexData.toArray();
        mGLView = new MyGLSurfaceView(this, r);
        setContentView(mGLView);

    }




}
