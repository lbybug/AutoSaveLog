package com.example.m100_demoserial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import M100_Package.M100_Serial;

public class ICCardNonContact extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iccardnonconact);

	}

	public static void NonContactEnterCardUntimeOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_EnterCardUntime((byte)0x34);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端立即进卡命令执行成功");
			else
				MainActivity.ShowMessage("Front Enter Card Untime Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端立即进卡命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Front Enter Card Untime Excute Failed, Error Informatio: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void NonContactMoveToRFPositionOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_MoveCard((byte)0x30);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("移动卡片到射频卡位置命令执行成功");
			else
				MainActivity.ShowMessage("Move card to rf card position Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("移动卡片到射频卡位置命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Move card to rf card position Excute Failed, Error Informatio: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void NonContactEjectOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_MoveCard((byte)0x34);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端弹出命令执行成功");
			else
				MainActivity.ShowMessage("Eject card Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端弹出命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Eject card Excute Failed, Error Informatio: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}
}
