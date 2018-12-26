package com.example.m100_demoserial;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import M100_Package.*;

public class ICCardContact extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.iccardconact);

	}


	public static void OnClickCPUActive(Context c)
	{
		int nRet;
		byte[] _RstData=new byte[500];
		byte[] _CPUType=new byte[2];
		byte[] _RstdataLen=new byte[2];
		int length=0;

		nRet = M100_Serial.M100_CpuCardColdReset(_CPUType, _RstData, _RstdataLen);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡片激活命令执行成功");
			else
				MainActivity.ShowMessage("CPU Card Cold Reset Excute Success");

			length = _RstdataLen[0];
			MainActivity.SetCPUATR.setText(MainActivity.ByteArrayToString(_RstData, length));
			//Toast.makeText(c, MainActivity.ByteArrayToString(_RstData, length), Toast.LENGTH_LONG).show();
			if(_CPUType[0] == 0x30)
			{
				if(MainActivity.m_English)
					Toast.makeText(c, "CPU Card T = 0 卡", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(c, "CPU Card T = 0 Card", Toast.LENGTH_LONG).show();
			}
			else if(_CPUType[0] == 0x31)
			{
				if(MainActivity.m_English)
					Toast.makeText(c, "CPU Card T = 1 卡", Toast.LENGTH_LONG).show();
				else
					Toast.makeText(c, "CPU Card T = 1 Card", Toast.LENGTH_LONG).show();
			}
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡片激活命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("CPU Card Cold Reset Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void OnClickCPUT0APDU(Context c)
	{
		int nRet;
		byte[] SendApdu=new byte[300];
		byte[] RecvApdu=new byte[300];
		int[] _exdataLen=new int[2];
		String str="";


		str = MainActivity.SetCPUSAPDU.getText().toString();
		if((str.length() % 2) != 0)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的APDU命令集长度无效，请重新输入...", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter APDU Command length is Invalid, please Check it...", Toast.LENGTH_LONG).show();
			return;
		}

		SendApdu = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_CPUT0APDU(str.length() / 2, SendApdu, RecvApdu, _exdataLen);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡T=0 APDU命令执行成功");
			else
				MainActivity.ShowMessage("CPU Card T=0 APDU Command Excute Success");

			MainActivity.SetCPURAPDU.setText(MainActivity.ByteArrayToString(RecvApdu, (int)_exdataLen[0]));
			//MainActivity.ShowMessage(MainActivity.ByteArrayToString(RecvApdu, (int)_exdataLen[0]));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡T=0 APDU命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("CPU Card T=0 APDU Command Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void OnClickCPUT1APDU(Context c)
	{
		int nRet;
		byte[] SendApdu=new byte[300];
		byte[] RecvApdu=new byte[300];
		int[] _exdataLen=new int[2];
		String str="";

		str = MainActivity.SetCPUSAPDU.getText().toString();
		if((str.length() % 2) != 0)
		{
			if(MainActivity.m_English)
				Toast.makeText(c, "你所输入的APDU命令集长度无效，请重新输入...", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(c, "Enter APDU Command length is Invalid, please Check it...", Toast.LENGTH_LONG).show();
			return;
		}

		SendApdu = MainActivity.StringToHex(str);

		nRet = M100_Serial.M100_CPUT1APDU(str.length() / 2, SendApdu, RecvApdu, _exdataLen);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡T=1 APDU命令执行成功");
			else
				MainActivity.ShowMessage("CPU Card T=1 APDU Command Excute Success");

			MainActivity.SetCPURAPDU.setText(MainActivity.ByteArrayToString(RecvApdu, (int)_exdataLen[0]));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡T=1 APDU命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("CPU Card T=1 APDU Command Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void OnClickReadICCardNum(Context c)
	{
		int nRet, i;
		byte[] ICCard=new byte[50];
		int[] len=new int[2];
		String str="", str1="";

		nRet = M100_Serial.M100_ReadICCardNum(len, ICCard);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("获取银行卡卡号命令执行成功");
			else
				MainActivity.ShowMessage("Get IC Card Number Command Excute Success");
			System.out.println("len[0] = " + len[0] + ", Data = " + MainActivity.ByteArrayToString(ICCard, 50));
			if(false)
			{
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
			}
			else
			{
				for(i = 0; i < len[0]; i++)
				{
					if((ICCard[i] & 0x0D) == 0x0D)
					{
						str += Integer.toHexString(((ICCard[i] & 0xF0) / 16) & 0xFF).toUpperCase();
						break;
					}
					else if((ICCard[i] & 0xD0) == 0xD0)
					{
						break;
					}
					else
					{
						str += MainActivity.HexToStr(ICCard[i]);
					}
				}
			}
			MainActivity.SetCPURAPDU.setText(str);
			Toast.makeText(c, str, Toast.LENGTH_LONG).show();
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("获取银行卡卡号命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Get IC Card Number Command Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void OnClickReadSocialSecurity(Context c)
	{

		byte[] IDNum=new byte[50];
		byte[] Name=new byte[50];
		byte[] Sex=new byte[4];
		byte[] Nation=new byte[4];
		byte[] Regional=new byte[5];
		byte[] BirthDay=new byte[6];
		int nRet;

		String StrIDNum="身份证号码：";
		String StrName="";
		String StrSex="性别：";
		String StrNation="民族：";
		String StrRegional="出生地：";
		String StrBirthDay="出生日期：";

		String str="";

		nRet = M100_Serial.M100_AutoReadSocialSecurityInfo(IDNum, Name, Sex, Nation, Regional, BirthDay);
		if(nRet == MainActivity.OK)
		{
			StrIDNum += MainActivity.ByteToString(IDNum);
			str += StrIDNum+"\r\n";

			String sCardName = "Hello";
			int mk=0;
			for(int i = 0; i < 50; i++)
			{
				if(Name[i] == 0x00)
					break;
				mk++;
			}
			byte[] mkdata=new byte[mk];
			MainActivity.CopyByte(mkdata, Name, 0x00, mk);
			try {
				sCardName = new String(mkdata,"GBK");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			str += "姓名：" + sCardName+"\r\n";

			if(Sex[0] == 0x31)
				StrSex += "男";
			else if(Sex[0] == 0x32)
				StrSex += "女";
			else
				StrSex += "未知性别";
			str += StrSex+"\r\n";

			if(Nation[0] == 0x01)
				StrNation += "汉族";
			else if(Nation[0] == 0x30)
				StrNation += "土族";
			else
				StrNation += "未知民族";
			str += StrNation+"\r\n";

			char[] cCardRegional=Converter.getChars(Regional);
			String sCardRegional = new String(cCardRegional);
			str += "出生地：" + sCardRegional+"\r\n";

			StrBirthDay += MainActivity.ByteArrayToString(BirthDay, 6);
			str += StrBirthDay+"\r\n";

			if(MainActivity.m_English)
				MainActivity.ShowMessage("社保卡获取社保信息命令执行成功");
			else
				MainActivity.ShowMessage("Read Social Security Information Excute Success");

			MainActivity.showMessage(c, str);
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("社保卡获取社保信息命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Read Social Security Information Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void OnClickCPUInAvtive(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_CpuCardPowerOff();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡片停止激活命令执行成功");
			else
				MainActivity.ShowMessage("CPU Card Power off Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡片停止激活命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("CPU Card Power off Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void OnClickGet55Data(Context c)
	{
		byte[] szFiled35 = new byte[200];
		byte[] szFiled55 = new byte[200];
		int[] szFiled35len = new int[2];
		int[] szFiled55len = new int[2];
		byte[] PAN_Data=new byte[2];
		byte[] Time_Data=new byte[20];

		int nRet = M100_Serial.M100_PBOCGet55FieldData((byte)0x00, szFiled35, szFiled35len, szFiled55, szFiled55len, PAN_Data);
		///int nRet = M100_Serial.M100_PBOCGet55FieldData_JD((byte)0x00, szFiled35, szFiled35len, szFiled55, szFiled55len, PAN_Data, Time_Data);
		if(nRet == MainActivity.OK)
		{
			MainActivity.SetCPURAPDU.setText("55Data: " + MainActivity.ByteArrayToString(szFiled55, (int)szFiled55len[0]) + "\r\n Track2 Data: " + MainActivity.ByteArrayToString(szFiled35, (int)szFiled35len[0]) + "\r\n PAN Data: " + MainActivity.ByteArrayToString(PAN_Data, 1) + "\r\n Time Data: " + MainActivity.ByteArrayToString(Time_Data, 3));

			//MainActivity.SetCPURAPDU.setText("55Data: " + MainActivity.ByteArrayToString(szFiled55, (int)szFiled55len[0]) + "\r\n Time Data: " + MainActivity.ByteArrayToString(Time_Data, 3));
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("CPU卡片获取55域数据执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("CPU Card Get 55 Field Data Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}
}
