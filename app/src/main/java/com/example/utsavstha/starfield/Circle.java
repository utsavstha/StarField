package com.example.utsavstha.starfield;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

public class Circle {

    private  int mProgram, mPositionHandle, mColorHandle, mMVPMatrixHandle ;
    private FloatBuffer mVertexBuffer;
    private float positionX = 0.5f;
    private float positionY = 0.5f;


    private float vertex[];
    float color[] = { 0.96078431372f, 0.21568627451f, 0.05882352941f, 1.0f };

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    /*private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";*/

    private final String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec2 screenSize;\n" +
                    "uniform vec4 vColor;" +
                    "uniform float time;\n" +
                    "\n" +
                    "void main(void) {\n" +
                    "    // Scale to UV space coords\n" +
                    "    vec2 uv = gl_FragCoord.xy / screenSize;\n" +
                    "\n" +
                    "    // Transform to [(-1.0, -1.0), (1.0, 1.0)] range\n" +
                    "    uv = 2.0 * uv - 1.0;\n" +
                    "\n" +
                    "    // Have something to vary the radius (can also just be a linear counter (time))\n" +
                    "    float wave = sin(time);\n" +
                    "\n" +
                    "    // Calculate how near to the center (0.0) or edge (1.0) this fragment is\n" +
                    "    float circle = uv.x * uv.x + uv.y * uv.y;\n" +
                    "\n" +
                    "    // Put it all together\n" +
                    "    gl_FragColor = vec4(vec3(circle + wave),1.0) * vColor;\n" +
                    "}";
    Circle(float[] vertices){
        /*vertices[0] = positionX;
        vertices[1] = positionY;
        vertices[2] = 0;

        for(int i =1; i <364; i++){
            vertices[(i * 3)+ 0] = (float) (diameter * Math.cos((3.14/180) * (float)i ) + vertices[0]);
            vertices[(i * 3)+ 1] = (float) (diameter * Math.sin((3.14/180) * (float)i ) + vertices[1]);
            vertices[(i * 3)+ 2] = 0;
        }*/

        //this.vertex = new float[vertices.length];
        Log.v("Thread",""+vertices[0]+","+vertices[1]+","+vertices[2]);
        ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = vertexByteBuffer.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(mProgram);

    }

    public static int loadShader(int type, String shaderCode){

        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }


    public void draw (float[] mvpMatrix){

        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, 3,
                GLES20.GL_FLOAT, false,12
                ,mVertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");



        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);



        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_LINES, 0, 100 * MainActivity.count);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }


}
