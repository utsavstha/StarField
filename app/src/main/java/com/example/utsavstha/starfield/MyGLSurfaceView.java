package com.example.utsavstha.starfield;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Arrays;

import static java.security.AccessController.getContext;

/**
 * Created by utsavstha on 2/20/17.
 */

public class MyGLSurfaceView extends GLSurfaceView{

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX = 0;
    private float mPreviousY = 0;
    private float mDensity;
    onStarClicked onStarClicked;
    private final MyGLRenderer mRenderer;
    Context context;
    StarDto starDto;
    public MyGLSurfaceView(Context context, OneKStar starDto){
        super(context);
        this.context = context;
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer(getContext());
        //this.starDto = starDto;
        // Render the view only when there is a change in the drawing data.
        // To allow the triangle to rotate automatically, this line is commented out:

        // Set the Renderer for drawing on the GLSurfaceView

        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);


    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();


        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                x = e.getX();

                float[] coordinate = mRenderer.getWorldCoords(e.getX()/8f, e.getY()/8);
               /* coordinate[0] = coordinate[0] /100f;
                coordinate[1] = coordinate[1] /100f;*/
                Log.d("dasfdas", Arrays.toString(coordinate));
               // Log.d("dasfdas", starDto.getMyarray().get(1).getGalX()+","+starDto.getMyarray().get(1).getGalY());
                break;
            case MotionEvent.ACTION_MOVE:
                if(e.getX() != mPreviousX){
                    //mRenderer.redrawLine(mRenderer.GetWorldCoords(mPreviousX, mPreviousY), 0, mRenderer.GetWorldCoords(e.getX(), e.getY()), 0);
                    mPreviousX = e.getX();
                    mPreviousY = e.getY();
                    requestRender();
                }
                break;
            case MotionEvent.ACTION_UP:
                mPreviousX = 0;
                mPreviousY = 0;
                break;



        }

        mPreviousX = x;
        mPreviousY = y;
        return true;
    }
    public interface onStarClicked{
        void onClick(float x, float y);
    }

}
