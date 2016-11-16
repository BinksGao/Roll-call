package com.button.anlaiye;

import com.example.anlaiye.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * @author 高小黑
 *
 * 2016年5月13日上午10:35:30
 */
public class ImageTextButton extends Button {
	  private int resourceId = 0;
	  private Bitmap bitmap;
	  public ImageTextButton(Context context) {
	    super(context,null);
	  }
	  public ImageTextButton(Context context,AttributeSet attributeSet) {
	    super(context, attributeSet);
	    this.setClickable(true);
	    resourceId = R.drawable.ic_launcher;
	    bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
	  }
	  public void setIcon(int resourceId) 
	  {
	    this.bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
	    invalidate();
	  }
	  @Override
	  protected void onDraw(Canvas canvas) {
	    // TODO Auto-generated method stub
	    // 图片顶部居中显示
	    int x = (this.getMeasuredWidth() - bitmap.getWidth())/2;
	    int y = 0;
	    canvas.drawBitmap(bitmap, x, y, null);
	    // 坐标需要转换，因为默认情况下Button中的文字居中显示
	    // 这里需要让文字在底部显示
	    canvas.translate(0,(this.getMeasuredHeight()/2) - (int) this.getTextSize());
	    super.onDraw(canvas);
	  }
	}