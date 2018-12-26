package com.example.m100_demoserial;

import M100_Package.M100_Serial;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class S70 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	public static void S70DetectCardOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_S70DetectCard();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70寻卡成功");
			else
				MainActivity.ShowMessage("S70 Detect Card Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70寻卡失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Detect Card Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70GetCardIDOnClick(Context c)
	{
		int nRet;
		byte[] CardID=new byte[20];

		nRet = M100_Serial.M100_S70GetCardID(CardID);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70获取序列号成功");
			else
				MainActivity.ShowMessage("S70 Get CardID Success");
			MainActivity.S70CardIDData.setText(MainActivity.ByteArrayToString(CardID, 4));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70获取序列号失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Get CardID Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70VerifyPassWordOnClick(Context c)
	{
		int nRet;
		byte[] PassWord=new byte[20];
		String str="";

		str = MainActivity.S70PassWordData.getText().toString();
		if(str.length() != 12)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的密码长度无效，请重新输入...例如'FFFFFFFFFFFF'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter PassWord length is Invalid, please Check it...For Example 'FFFFFFFFFFFF'", Toast.LENGTH_LONG).show();
			return;
		}

		PassWord = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S70LoadSecKey(MainActivity.S70PassWordSector, (byte)(MainActivity.S70PassWordType + 0x30), PassWord);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70密码验证成功");
			else
				MainActivity.ShowMessage("S70 Verify PassWord Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70密码验证失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Verify PassWord Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70HaltOnClick(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_S70Halt();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70停止激活成功");
			else
				MainActivity.ShowMessage("S70 Halt Card Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70停止激活失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Halt Card Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70InitDataOnClick(Context c)
	{
		int nRet;
		byte[] InitData=new byte[20];
		String str="";

		str = MainActivity.S70InitData.getText().toString();
		if(str.length() != 8)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的初始化数据长度无效，请重新输入...例如'00000000'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Init Data length is Invalid, please Check it...For Example'00000000'", Toast.LENGTH_LONG).show();
			return;
		}

		InitData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S70InitValue((byte)(MainActivity.S70SectorAddr * 4 + MainActivity.S70BlockAddr), InitData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70初始化数据成功");
			else
				MainActivity.ShowMessage("S70 Init Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70初始化数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Init Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70DereaseOnClick(Context c)
	{
		int nRet;
		byte[] DecreaseData=new byte[20];
		String str="";

		str = MainActivity.S70DecreaseData.getText().toString();
		if(str.length() != 8)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的减值数据长度无效，请重新输入...例如'00000001'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Decrease Data length is Invalid, please Check it...For Example'00000001'", Toast.LENGTH_LONG).show();
			return;
		}

		DecreaseData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S70Decrement((byte)(MainActivity.S70SectorAddr * 4 + MainActivity.S70BlockAddr), DecreaseData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70减值成功");
			else
				MainActivity.ShowMessage("S70 Decrease Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70减值失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Decrease Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70IncreaseOnClick(Context c)
	{
		int nRet;
		byte[] IncreaseData=new byte[20];
		String str="";

		str = MainActivity.S70IncreaseData.getText().toString();
		if(str.length() != 8)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的增值数据长度无效，请重新输入...例如'00000001'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Increase Data length is Invalid, please Check it...For Example'00000001'", Toast.LENGTH_LONG).show();
			return;
		}

		IncreaseData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S70Increment((byte)(MainActivity.S70SectorAddr * 4 + MainActivity.S70BlockAddr), IncreaseData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70增值成功");
			else
				MainActivity.ShowMessage("S70 Increase Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70增值失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Increase Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70WriteBlockDataOnClick(Context c)
	{
		int nRet;
		byte[] WriteData=new byte[20];
		String str="";

		str = MainActivity.S70WriteData.getText().toString();
		if(str.length() != 32)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的写入数据长度无效，请重新输入...例如'112233445566778899AABBCCDDEEFF00'", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter Write Data length is Invalid, please Check it...For Example'112233445566778899AABBCCDDEEFF00'", Toast.LENGTH_LONG).show();
			return;
		}

		WriteData = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_S70WriteBlock((byte)(MainActivity.S70SectorAddr * 4 + MainActivity.S70BlockAddr), WriteData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70写入数据成功");
			else
				MainActivity.ShowMessage("S70 Write Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70写入数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Write Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void S70ReadBlockDataOnClick(Context c)
	{
		int nRet;
		byte[] ReadData=new byte[20];
		String str="";

		nRet = M100_Serial.M100_S70ReadBlock((byte)(MainActivity.S70SectorAddr * 4 + MainActivity.S70BlockAddr), ReadData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70读出数据成功");
			else
				MainActivity.ShowMessage("S70 Read Data Success");

			MainActivity.S70WriteData.setText(MainActivity.ByteArrayToString(ReadData, 16));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("S70读出数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("S70 Read Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}
}
