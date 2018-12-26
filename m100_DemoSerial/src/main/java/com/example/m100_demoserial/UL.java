package com.example.m100_demoserial;

import M100_Package.M100_Serial;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class UL extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public static void ULDetectCardOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_ULDetectCard();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL寻卡成功");
			else
				MainActivity.ShowMessage("UL Detect Card Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL寻卡失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("UL Detect Card Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void ULHaltOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_ULHalt();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL停止激活成功");
			else
				MainActivity.ShowMessage("UL Halt Card Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL停止激活失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("UL Halt Card Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void ULGetCardIDOnClick(Context c)
	{
		int nRet;
		byte[] CardID=new byte[20];

		nRet = M100_Serial.M100_ULGetCardID(CardID);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL获取序列号成功");
			else
				MainActivity.ShowMessage("UL Get CardID Success");
			MainActivity.ULCardIDDataText.setText(MainActivity.ByteArrayToString(CardID, 4));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL获取序列号失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("UL Get CardID Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void ULReadBlockDataOnClick(Context c)
	{
		int nRet;
		byte[] ReadData=new byte[20];
		String str="";

		nRet = M100_Serial.M100_ULReadBlock((byte)(MainActivity.ULSectorAddr), ReadData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL读出数据成功");
			else
				MainActivity.ShowMessage("UL Read Data Success");

			MainActivity.ULWriteDataText.setText(MainActivity.ByteArrayToString(ReadData, 16));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL读出数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("UL Read Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void ULWriteBlockDataOnClick(Context c)
	{
		int nRet;
		byte[] WriteData=new byte[20];
		String str="";

		str = MainActivity.ULWriteDataText.getText().toString();
		if(str.length() != 32)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的写入数据长度无效，请重新输入...例如'112233445566778899AABBCCDDEEFF00'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Write Data length is Invalid, please Check it...For Example'112233445566778899AABBCCDDEEFF00'", Toast.LENGTH_LONG).show();
			return;
		}

		WriteData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_ULWriteBlock((byte)(MainActivity.ULSectorAddr), WriteData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL写入数据成功");
			else
				MainActivity.ShowMessage("UL Write Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("UL写入数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("UL Write Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}


}
