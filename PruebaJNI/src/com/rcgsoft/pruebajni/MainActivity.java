package com.rcgsoft.pruebajni;

import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawView vTest=(drawView )findViewById(R.id.drawView1);
		vTest.setMainAct(this);
		vTest.setDuplicateParentStateEnabled(false);
		vTest.setDrawingCacheEnabled(false);
		vTest.setClickable(false);
		vTest.setLongClickable(false);
		vTest.setWillNotDraw(true);
/*		View vTest=(View )findViewById(R.id.view1);
		vTest.setOnTouchListener(new OnTouchListener() {
			long lAnterior=0;
			float tMedio=0;
			long nEventos=0;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				long lActual=System.currentTimeMillis();
				int act=event.getActionMasked();
				float eventX = event.getRawX();
			    float eventY = event.getRawY();
				if (act==event.ACTION_DOWN){
					System.out.println("ACTION_DOWN");
					lAnterior=lActual;
					JNIinitFB();
				}else if (act==event.ACTION_UP){
					System.out.println("ACTION_UP");
					JNIfinishFB();
					lAnterior=0;
					TextView tX=(TextView) findViewById(R.id.txtX);
					TextView tY=(TextView) findViewById(R.id.txtY);
					tX.setText("T="+tMedio  +"  X:" + eventX);
					tY.setText("E="+nEventos+"  Y:" + eventY);
					View vTest=(View )findViewById(R.id.view1);
				}else if (act==event.ACTION_MOVE){
//					System.out.println("ACTION_MOVE");
					if (lAnterior>0) {
						long tActo=lActual-lAnterior;
						if (tMedio==0) {
							tMedio=tActo;
						} else {
							tMedio=(tActo+(tMedio*nEventos))/(nEventos+1);	
						}
						nEventos++;
					}
					lAnterior=lActual;
					JNIpaintFB((int)eventX,(int)eventY);
				}
				return true;
			}
		});


/*		Button b1=(Button) findViewById(R.id.button1);
		b1.setText(stringFromJNI());
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		b1=(Button) findViewById(R.id.button2);
		b1.setText("Actualizar");
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button b1=(Button) findViewById(R.id.button2);
				b1.setText(CallIOCTL());
			}
		});
			  */
		 new Thread(new Runnable() {
			    public void run() {
			    	if (false) {
			    	int iVueltas=1;
			    	while (iVueltas-- >0) {
			    		try {
			    			System.out.println("Esperando 3 segundos:"+iVueltas);
					    	Thread.currentThread().sleep(1000);
			    			System.out.println("Esperando 2 segundos:"+iVueltas);
					    	Thread.currentThread().sleep(1000);
			    			System.out.println("Esperando 1 segundos:"+iVueltas);
					    	Thread.currentThread().sleep(1000);
			    			System.out.println("Fin de la espera YA!:"+iVueltas);
						} catch (Exception e) {
							// TODO: handle exception
			    		}
		    			System.out.println("Llamando a ioctl");
						System.out.println(CallIOCTL());
		    			System.out.println("Fin Llamada a ioctl");
		    			quitClick();
			    	}
			    	}
			    }
			  }).start();
	}
	public void log(String sLog){
		System.out.println(sLog);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void quitClick() {
		finish();
	}
	
	
    /** A native method that is implemented by the
    * ‘hello-jni’ native library, which is packaged
    * with this application.
    */
    public native String stringFromJNI();
    public native String CallIOCTL();
    public native String JNIinitFB();
    public native String JNIfinishFB();
    public native String JNIpaintFB(int laX,int laY);
    /** Load the native library where the native method
    * is stored.
    */
    static {
/*		  try {
			Runtime.getRuntime().exec("su");
			Runtime.getRuntime().exec("chmod 777 /dev/graphics/fb0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
          System.loadLibrary("PruebaJNI");
    }

}
