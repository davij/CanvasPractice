package com.example.canvaspractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CanvasMainActivity extends ActionBarActivity {

	RelativeLayout mContainer;
	Bitmap mBitmap;
	Paint paint;
	boolean test;
	boolean test2;

	GestureDetector gd;

	View mView;

	int masterContainerWidth, masterContainerHeight;
	float posX, posY;

	int onDrawCalled = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContainer = (RelativeLayout) findViewById(R.id.objectMainContainer);

		TextView mTV = new TextView(this);
		mTV.setBackgroundColor(Color.CYAN);
		
		mContainer.addView(mTV);
		
		mContainer.post(new Runnable() {

			@Override
			public void run() {
				Log.e("run()width: " + mContainer.getWidth(), "height: " + mContainer.getHeight());
			}
		});

		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.startbtn);

		final ObjectToDrawInCanvas objectView = new ObjectToDrawInCanvas(this, 50, 40);
		mContainer.addView(objectView);

		gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				Toast.makeText(CanvasMainActivity.this, "taped!", Toast.LENGTH_SHORT).show();

				posX = e.getX();
				posY = e.getY();

				// mContainer.invalidate();
				objectView.moveObject();
				return true;

			}
		});

	}// onCreate()

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// delegate the touch to the gesture detector we declared earlier
		gd.onTouchEvent(event);

		return true;
	}

	public class ObjectToDrawInCanvas extends View {

		int objectViewWidth, objectViewHeight; // , posX, posY;

		Bitmap scaledBitmap = Bitmap.createScaledBitmap(mBitmap, 80, 60, false);

		Paint p = new Paint();

		// constructor
		public ObjectToDrawInCanvas(Context context, int viewWidth, int viewHeigth) {
			super(context);
			// scaledBitmap.thi
			objectViewWidth = viewWidth;
			objectViewHeight = viewHeigth;

			// invalidate();

			Log.e("constructor", "called");

		}

		public void moveObject() {
			post(new Runnable() {

				@Override
				public void run() {
					Log.e("run()", "and moving object");
					// mContainer.invalidate();
				}
			});
		}

		@Override
		protected void onDraw(Canvas canvas) {

			onDrawCalled += 1;
			// super.onDraw(canvas);

			Log.e("onDraw()", "called: " + onDrawCalled);

			canvas.drawColor(Color.parseColor("#7fff00"));
			// canvas.drawBitmap(scaledBitmap, mContainer.getWidth() / 2, mContainer.getHeight() -
			// 50, null);
			canvas.drawBitmap(scaledBitmap, posX, posY, null);

			p.setColor(Color.LTGRAY);
			p.setStrokeWidth(10);

			canvas.drawLine(0, 500, 700, 500, p);

			invalidate();

		}
	}// end ObjectToDrawInCanvas class

} // end onCreate()
