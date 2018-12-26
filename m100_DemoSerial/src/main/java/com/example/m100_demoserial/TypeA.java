package com.example.m100_demoserial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import M100_Package.M100_Serial;

public class TypeA extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iccardnonconact);

	}


	public static void OnClickTypeAActive(Context c)
	{
		int nRet;
		byte[] _RstData=new byte[500];

		nRet = M100_Serial.M100_CPUCardPowerOn(_RstData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡片激活命令执行成功");
			else
				MainActivity.ShowMessage("CPU Card Cold Reset Excute Success");

			//SetTypeARAPDU.setText(MainActivity.ByteArrayToString(_RstData, 16));
			Toast.makeText(c, MainActivity.ByteArrayToString(_RstData, 16), Toast.LENGTH_LONG).show();
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡片激活命令执行失败");
			else
				MainActivity.ShowMessage("CPU Card Cold Reset Excute Failed");
		}
	}

	public static void OnClickTypeAAPDU(Context c)
	{
		int nRet;
		byte[] SendApdu=new byte[300];
		byte[] RecvApdu=new byte[300];
		int[] _exdataLen=new int[2];
		byte[] RCH=new byte[2];
		String str="";

		str = MainActivity.SetTypeASAPDU.getText().toString();
		if((str.length() % 2) != 0)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的APDU命令集长度无效，请重新输入...", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter APDU Command length is Invalid, please Check it...", Toast.LENGTH_LONG).show();
			return;
		}

		SendApdu = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_CPUAPDU((byte)0x00, str.length() / 2, SendApdu, RCH, RecvApdu, _exdataLen);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("TypeA卡T=0 APDU命令执行成功");
			else
				MainActivity.ShowMessage("TypeA Card T=0 APDU Command Excute Success");

			MainActivity.SetTypeARAPDU.setText(MainActivity.ByteArrayToString(RecvApdu, (int)_exdataLen[0]));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("TypeA卡T=0 APDU命令执行失败");
			else
				MainActivity.ShowMessage("TypeA Card T=0 APDU Command Excute Failed");
		}
	}

	public static void OnClickTypeAICCardNum(Context c)
	{
		int nRet, i;
		byte[] ICCard=new byte[50];
		int[] len=new int[2];
		String str="", str1="";

		nRet = M100_Serial.M100_CPUTypeAReadICCardNum(len, ICCard);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("获取银行卡卡号命令执行成功");
			else
				MainActivity.ShowMessage("Get IC Card Number Command Excute Success");

			if((len[0] % 2) == 0)
			{
				for(i = 0; i < len[0] / 2; i++)
				{
					str += MainActivity.HexToStr(ICCard[i]);
				}
			}
			else
			{
				for(i = 0; i < len[0] / 2; i++)
				{
					str += MainActivity.HexToStr(ICCard[i]);
				}
				str += Integer.toHexString(((ICCard[(len[0] - 1) / 2] & 0xF0) / 16) & 0xFF).toUpperCase();
			}
			MainActivity.SetTypeARAPDU.setText(str);
			Toast.makeText(c, str, Toast.LENGTH_LONG).show();
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("获取银行卡卡号命令执行失败");
			else
				MainActivity.ShowMessage("Get IC Card Number Command Excute Failed");
		}
	}
}
