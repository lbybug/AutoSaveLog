package com.example.m100_demoserial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import M100_Package.*;

public class S50 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.s50);
	}

	public static void S50DetectCardOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_S50DetectCard();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50寻卡成功");
			else
				MainActivity.ShowMessage("S50 Detect Card Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50寻卡失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Detect Card Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50GetCardIDOnClick(Context c)
	{
		int nRet;
		byte[] CardID=new byte[20];

		nRet = M100_Serial.M100_S50GetCardID(CardID);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50获取序列号成功");
			else
				MainActivity.ShowMessage("S50 Get CardID Success");
			MainActivity.S50CardIDData.setText(MainActivity.ByteArrayToString(CardID, 4));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50获取序列号失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Get CardID Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50VerifyPassWordOnClick(Context c)
	{
		int nRet;
		byte[] PassWord=new byte[20];
		String str="";

		str = MainActivity.S50PassWordData.getText().toString();
		if(str.length() != 12)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的密码长度无效，请重新输入...例如'FFFFFFFFFFFF'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter PassWord length is Invalid, please Check it...For Example 'FFFFFFFFFFFF'", Toast.LENGTH_LONG).show();
			return;
		}

		PassWord = MainActivity.StringToHex(str);
		nRet = M100_Serial.M100_S50LoadSecKey(MainActivity.S50PassWordSector, (byte)(MainActivity.S50PassWordType + 0x30), PassWord);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50密码验证成功");
			else
				MainActivity.ShowMessage("S50 Verify PassWord Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50密码验证失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Verify PassWord Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50HaltOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_S50Halt();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50停止激活成功");
			else
				MainActivity.ShowMessage("S50 Halt Card Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50停止激活失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Halt Card Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50InitDataOnClick(Context c)
	{
		int nRet;
		byte[] InitData=new byte[20];
		String str="";

		str = MainActivity.S50InitData.getText().toString();
		if(str.length() != 8)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的初始化数据长度无效，请重新输入...例如'00000000'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Init Data length is Invalid, please Check it...For Example'00000000'", Toast.LENGTH_LONG).show();
			return;
		}

		InitData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S50InitValue((byte)(MainActivity.S50SectorAddr * 4 + MainActivity.S50BlockAddr), InitData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50初始化数据成功");
			else
				MainActivity.ShowMessage("S50 Init Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50初始化数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Init Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50DereaseOnClick(Context c)
	{
		int nRet;
		byte[] DecreaseData=new byte[20];
		String str="";

		str = MainActivity.S50DecreaseData.getText().toString();
		if(str.length() != 8)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的减值数据长度无效，请重新输入...例如'00000001'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Decrease Data length is Invalid, please Check it...For Example'00000001'", Toast.LENGTH_LONG).show();
			return;
		}

		DecreaseData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S50Decrement((byte)(MainActivity.S50SectorAddr * 4 + MainActivity.S50BlockAddr), DecreaseData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50减值成功");
			else
				MainActivity.ShowMessage("S50 Decrease Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50减值失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Decrease Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50IncreaseOnClick(Context c)
	{
		int nRet;
		byte[] IncreaseData=new byte[20];
		String str="";

		str = MainActivity.S50IncreaseData.getText().toString();
		if(str.length() != 8)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的增值数据长度无效，请重新输入...例如'00000001'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Increase Data length is Invalid, please Check it...For Example'00000001'", Toast.LENGTH_LONG).show();
			return;
		}

		IncreaseData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S50Increment((byte)(MainActivity.S50SectorAddr * 4 + MainActivity.S50BlockAddr), IncreaseData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50增值成功");
			else
				MainActivity.ShowMessage("S50 Increase Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50增值失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Increase Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50WriteBlockDataOnClick(Context c)
	{
		int nRet;
		byte[] WriteData=new byte[20];
		String str="";

		str = MainActivity.S50WriteData.getText().toString();
		if(str.length() != 32)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的写入数据长度无效，请重新输入...例如'112233445566778899AABBCCDDEEFF00'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Write Data length is Invalid, please Check it...For Example'112233445566778899AABBCCDDEEFF00'", Toast.LENGTH_LONG).show();
			return;
		}

		WriteData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S50WriteBlock((byte)(MainActivity.S50SectorAddr * 4 + MainActivity.S50BlockAddr), WriteData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50写入数据成功");
			else
				MainActivity.ShowMessage("S50 Write Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50写入数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Write Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S50ReadBlockDataOnClick(Context c)
	{
		int nRet;
		byte[] ReadData=new byte[20];
		String str="";

		nRet = M100_Serial.M100_S50ReadBlock((byte)(MainActivity.S50SectorAddr * 4 + MainActivity.S50BlockAddr), ReadData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50读出数据成功");
			else
				MainActivity.ShowMessage("S50 Read Data Success");

			MainActivity.S50WriteData.setText(MainActivity.ByteArrayToString(ReadData, 16));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S50读出数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S50 Read Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

}
