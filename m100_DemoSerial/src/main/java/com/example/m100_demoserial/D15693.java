package com.example.m100_demoserial;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import M100_Package.*;

public class D15693 extends Activity {

	public static byte[] UID=new byte[8];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.d1569);
	}



	public static void GetUid(Context c)
	{
		int nRet;
		nRet = M100_Serial.M100_15693GetUid(UID);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片获取Uid成功");
			else
				MainActivity.ShowMessage("15693 Card Get Uid Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片获取Uid失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Get Uid Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void ReadBlock(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		try {
			BlockAddress = Byte.parseByte(MainActivity.BlockAddressText.getText().toString());
			BlockLen = Byte.parseByte(MainActivity.BlockLengthText.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		nRet = M100_Serial.M100_15693ReadData(true, UID, BlockAddress, BlockLen, _BlockData, ReadBlockLen);
		if(nRet == MainActivity.OK)
		{
			MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片读取数据成功");
			else
				MainActivity.ShowMessage("15693 Card Read Block Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片读取数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Read Block Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void WriteBlock(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		String str="";
		try {
			BlockAddress = Byte.parseByte(MainActivity.BlockAddressText.getText().toString());
			BlockLen = Byte.parseByte(MainActivity.BlockLengthText.getText().toString());
			str = MainActivity.BlockDataText.getText().toString();
			if(str.length() != 8)
			{
				MainActivity.ShowMessage("输入数据长度有误，请重新输入...（8个字符）");
				return;
			}
			_BlockData = MainActivity.StringToHex(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		nRet = M100_Serial.M100_15693WriteData(true, UID, BlockAddress, BlockLen, _BlockData, ReadBlockLen);
		if(nRet == MainActivity.OK)
		{
			//MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片写入数据成功");
			else
				MainActivity.ShowMessage("15693 Card Write Block Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片写入数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Write Block Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void LockBlock(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte LockAddress = 0;
		try {
			LockAddress = Byte.parseByte(MainActivity.BlockAddressText.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		nRet = M100_Serial.M100_15693LockBlock(true, UID, LockAddress);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片锁定块成功");
			else
				MainActivity.ShowMessage("15693 Card Lock Block Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片锁定块失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Lock Block Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void WriteAFI(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		String str="";
		try {
			str = MainActivity.BlockDataText.getText().toString();
			if(str.length() != 2)
			{
				MainActivity.ShowMessage("输入数据长度有误，请重新输入...（2个字符）");
				return;
			}
			_BlockData = MainActivity.StringToHex(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		nRet = M100_Serial.M100_15693WriteAFI(true, UID, _BlockData[0]);
		if(nRet == MainActivity.OK)
		{
			//MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片写入AFI数据成功");
			else
				MainActivity.ShowMessage("15693 Card Write AFI Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片写入AFI数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Write AFI Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void LockAFI(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		String str="";

		nRet = M100_Serial.M100_15693LockAFI(true, UID);
		if(nRet == MainActivity.OK)
		{
			//MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片锁定AFI数据成功");
			else
				MainActivity.ShowMessage("15693 Card Lock AFI Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片锁定AFI数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Lock AFI Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void WriteDSFID(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		String str="";
		try {
			str = MainActivity.BlockDataText.getText().toString();
			if(str.length() != 2)
			{
				MainActivity.ShowMessage("输入数据长度有误，请重新输入...（2个字符）");
				return;
			}
			_BlockData = MainActivity.StringToHex(str);
		} catch (Exception e) {
			// TODO: handle exception
		}
		nRet = M100_Serial.M100_15693WriteDSFID(true, UID, _BlockData[0]);
		if(nRet == MainActivity.OK)
		{
			//MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片写入DSFID数据成功");
			else
				MainActivity.ShowMessage("15693 Card Write DSFID Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片写入DSFID数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Write DSFID Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void LockDSFID(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		String str="";

		nRet = M100_Serial.M100_15693LockDSFID(true, UID);
		if(nRet == MainActivity.OK)
		{
			//MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片锁定DSFID数据成功");
			else
				MainActivity.ShowMessage("15693 Card Lock DSFID Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片锁定DSFID数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Lock DSFID Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void ReadSafeBit(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		try {
			BlockAddress = Byte.parseByte(MainActivity.BlockAddressText.getText().toString());
			BlockLen = Byte.parseByte(MainActivity.BlockLengthText.getText().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		nRet = M100_Serial.M100_15693ReadSafeBit(true, UID, BlockAddress, BlockLen, ReadBlockLen, _BlockData);
		if(nRet == MainActivity.OK)
		{
			MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片读取安全位数据成功");
			else
				MainActivity.ShowMessage("15693 Card Read Safe bit Data Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片读取安全位数据失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Read Safe bit Data Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public static void ChooseCard(Context c)
	{
		int nRet;
		//byte[] UID=new byte[8];
		byte BlockAddress = 0, BlockLen  =0;
		byte[] _BlockData=new byte[200];
		byte[] ReadBlockLen=new byte[2];
		String str="";

		nRet = M100_Serial.M100_15693ChooseCard(true, UID);
		if(nRet == MainActivity.OK)
		{
			//MainActivity.BlockDataText.setText(MainActivity.ByteArrayToString(_BlockData, ReadBlockLen[0]));
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片选择成功");
			else
				MainActivity.ShowMessage("15693 Card Choose Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("15693卡片选择失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("15693 Card Choose Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}


}
