package com.test.cppglsurfaceviewtrans;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* C++ */
        NativeFunc.create(0);

        GLSurfaceView glsurfaceview = (GLSurfaceView)findViewById(R.id.glsurface);
        glsurfaceview.setEGLContextClientVersion(2);

        /* 透過設定 */
        glsurfaceview.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        glsurfaceview.setEGLConfigChooser(8,8,8,8,0,0);
        glsurfaceview.setZOrderOnTop(true);

        glsurfaceview.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                NativeFunc.surfaceCreated(0);
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                NativeFunc.surfaceChanged(0, width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl) {
                try { Thread.sleep(10); } catch (InterruptedException e) { }
                NativeFunc.onDrawFrame(0);
            }
        });
    }
}
