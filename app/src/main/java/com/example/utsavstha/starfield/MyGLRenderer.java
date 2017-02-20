package com.example.utsavstha.starfield;

import android.app.Activity;
import android.content.Context;
import android.graphics.Camera;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.DisplayMetrics;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.xml.transform.Transformer;

/**
 * Created by utsav on 12/24/2016.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    public volatile float mAngle;
    private Circle circle;
    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    //matrix for rotation
    float width, height;
    private float[] mRotationMatrix = new float[16];
    private Context context;
    public MyGLRenderer(Context context) {
        this.context = context;
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
       circle = new Circle(MainActivity.vertices);

    }

    public void onDrawFrame(GL10 unused) {
        float[] scratch = new float[16];
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // Create a rotation transformation for the triangle
        //long time = SystemClock.uptimeMillis() % 4000L;
        //float angle = 0.090f * ((int) time);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);

        // Combine the rotation matrix with the projection and camera view
        // Note that the mMVPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        // mCube.draw(scratch);
        // line.draw(scratch);
        // mCube.draw(scratch);
        circle.draw(scratch);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        this.width = 50;
        this.height = 50;
        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
       // Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
        //Matrix.frustumM(mProjectionMatrix, 0, 0, width, 0.0f, height, 3, 7);
        Matrix.orthoM(mProjectionMatrix, 0, -50, 50, -1, 1, 3, 7);
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }
    public float[] getWorldCoords(float x, float y)
    {
        // Initialize auxiliary variables.

        // SCREEN height & width (ej: 320 x 480)
        float screenW = width;
        float screenH = height;

        // Auxiliary matrix and vectors
        // to deal with ogl.
        float[] invertedMatrix, transformMatrix,
                normalizedInPoint, outPoint;
        invertedMatrix = new float[16];
        transformMatrix = new float[16];
        normalizedInPoint = new float[4];
        outPoint = new float[4];

        // Invert y coordinate, as android uses
        // top-left, and ogl bottom-left.
        int oglTouchY = (int) (screenH - y);

       /* Transform the screen point to clip
       space in ogl (-1,1) */
        normalizedInPoint[0] =
                (float) (x * 2.0f / screenW - 1.0);
        normalizedInPoint[1] =
                (float) ((oglTouchY) * 2.0f / screenH - 1.0);
        normalizedInPoint[2] = - 1.0f;
        normalizedInPoint[3] = 1.0f;

       /* Obtain the transform matrix and
       then the inverse. */
        //Matrix.setIdentityM(mMVMatrix, 0);
        Matrix.multiplyMM(
                transformMatrix, 0,
                mProjectionMatrix, 0,
                mViewMatrix, 0);
        Matrix.invertM(invertedMatrix, 0,
                transformMatrix, 0);

       /* Apply the inverse to the point
       in clip space */
        Matrix.multiplyMV(
                outPoint, 0,
                invertedMatrix, 0,
                normalizedInPoint, 0);

        float[] value = new float[]{outPoint[0] / outPoint[3] , outPoint[1] / outPoint[3], 0.0f};

        return value;
    }

}
