package com.example.m100_demoserial;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import M100_Package.M100_Serial;

public class ReadMagCard extends Activity {


	//public static EditText Track1_Data;
	//public static EditText Track2_Data;
	//public static EditText Track3_Data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readmagcard);


	}

	public static void mmmm(Context c)
	{

	}

	public static void OnClickReadEnterCard(Context c)
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
				MainActivity.ShowMessage("前端立即进卡命令执行失败");
			else
				MainActivity.ShowMessage("Front Enter Card Untime Excute Failed");
		}
	}

	public static void OnClickReadEject(Context c)
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
				MainActivity.ShowMessage("前端弹出命令执行失败");
			else
				MainActivity.ShowMessage("Eject card Excute Failed");
		}
	}

	public static void OnClickReadMagcard(Context c)/*注：此处显示轨道信息时候有问题Track1_Data.setText("")，可能是因为控件不属于MainActivity类*/
	{
		int nRet;
		byte[] _BlockData=new byte[500];
		byte[] Track1_Buf=new byte[100];
		byte[] Track2_Buf=new byte[200];
		byte[] Track3_Buf=new byte[200];
		int[] _DataLen=new int[2];
		String str="";

		nRet = M100_Serial.M100_ReadMagcardDecode((byte)0x36, _DataLen, _BlockData);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("读磁卡命令执行成功");
			else
				MainActivity.ShowMessage("Read Magcard Excute Success");

			switch(_BlockData[0])
			{
				case 0x60:
					MainActivity.CopyByte(Track1_Buf, _BlockData, 6, _BlockData[1]);
					str = MainActivity.ByteToString(Track1_Buf);
					MainActivity.Track1_Data.setText(str);
					break;
				case 0x61:
					if(MainActivity.m_English)
						MainActivity.Track1_Data.setText("错误代码：0x61, SS错误");
					else
						MainActivity.Track1_Data.setText("Error Code: 0x61, SS Error");
					break;
				case 0x62:
					if(MainActivity.m_English)
						MainActivity.Track1_Data.setText("错误代码：0x62, ES错误");
					else
						MainActivity.Track1_Data.setText("Error Code: 0x62, ES Error");
					break;
				case 0x63:
					if(MainActivity.m_English)
						MainActivity.Track1_Data.setText("错误代码：0x63, P错误");
					else
						MainActivity.Track1_Data.setText("Error Code: 0x63, P Error");
					break;
				case 0x64:
					if(MainActivity.m_English)
						MainActivity.Track1_Data.setText("错误代码：0x64, LRC错误");
					else
						MainActivity.Track1_Data.setText("Error Code: 0x64, LRC Error");
					break;
				case 0x65:
					if(MainActivity.m_English)
						MainActivity.Track1_Data.setText("错误代码：0x65, 空白轨道");
					else
						MainActivity.Track1_Data.setText("Error Code: 0x65, Blank Track");
					break;
			}

			switch(_BlockData[2])
			{
				case 0x60:
					MainActivity.CopyByte(Track2_Buf, _BlockData, 6 + _BlockData[1], _BlockData[3]);
					str = MainActivity.ByteToString(Track2_Buf);
					MainActivity.Track2_Data.setText(str);
					break;
				case 0x61:
					if(MainActivity.m_English)
						MainActivity.Track2_Data.setText("错误代码：0x61, SS错误");
					else
						MainActivity.Track2_Data.setText("Error Code: 0x61, SS Error");
					break;
				case 0x62:
					if(MainActivity.m_English)
						MainActivity.Track2_Data.setText("错误代码：0x62, ES错误");
					else
						MainActivity.Track2_Data.setText("Error Code: 0x62, ES Error");
					break;
				case 0x63:
					if(MainActivity.m_English)
						MainActivity.Track2_Data.setText("错误代码：0x63, P错误");
					else
						MainActivity.Track2_Data.setText("Error Code: 0x63, P Error");
					break;
				case 0x64:
					if(MainActivity.m_English)
						MainActivity.Track2_Data.setText("错误代码：0x64, LRC错误");
					else
						MainActivity.Track2_Data.setText("Error Code: 0x64, LRC Error");
					break;
				case 0x65:
					if(MainActivity.m_English)
						MainActivity.Track2_Data.setText("错误代码：0x65, 空白轨道");
					else
						MainActivity.Track2_Data.setText("Error Code: 0x65, Blank Track");
					break;
			}

			switch(_BlockData[4])
			{
				case 0x60:
					MainActivity.CopyByte(Track3_Buf, _BlockData, 6 + _BlockData[1] + _BlockData[3], _BlockData[5]);
					str = MainActivity.ByteToString(Track3_Buf);
					MainActivity.Track3_Data.setText(str);
					break;
				case 0x61:
					if(MainActivity.m_English)
						MainActivity.Track3_Data.setText("错误代码：0x61, SS错误");
					else
						MainActivity.Track3_Data.setText("Error Code: 0x61, SS Error");
					break;
				case 0x62:
					if(MainActivity.m_English)
						MainActivity.Track3_Data.setText("错误代码：0x62, ES错误");
					else
						MainActivity.Track3_Data.setText("Error Code: 0x62, ES Error");
					break;
				case 0x63:
					if(MainActivity.m_English)
						MainActivity.Track3_Data.setText("错误代码：0x63, P错误");
					else
						MainActivity.Track3_Data.setText("Error Code: 0x63, P Error");
					break;
				case 0x64:
					if(MainActivity.m_English)
						MainActivity.Track3_Data.setText("错误代码：0x64, LRC错误");
					else
						MainActivity.Track3_Data.setText("Error Code: 0x64, LRC Error");
					break;
				case 0x65:
					if(MainActivity.m_English)
						MainActivity.Track3_Data.setText("错误代码：0x65, 空白轨道");
					else
						MainActivity.Track3_Data.setText("Error Code: 0x65, Blank Track");
					break;
			}

			/*str = "Track1: ";
			switch(_BlockData[0])
			{
			case 0x60:
				MainActivity.CopyByte(Track1_Buf, _BlockData, 6, _BlockData[1]);
				str += MainActivity.ByteToString(Track1_Buf);
				break;
			case 0x61:
				if(MainActivity.m_English)
					str += "错误代码：0x61, SS错误";
				else
					str += "Error Code: 0x61, SS Error";
				break;
			case 0x62:
				if(MainActivity.m_English)
					str += "错误代码：0x62, ES错误";
				else
					str += "Error Code: 0x62, ES Error";
				break;
			case 0x63:
				if(MainActivity.m_English)
					str +=  "错误代码：0x63, P错误";
				else
					str += "Error Code: 0x63, P Error";
				break;
			case 0x64:
				if(MainActivity.m_English)
					str += "错误代码：0x64, LRC错误";
				else
					str += "Error Code: 0x64, LRC Error";
				break;
			case 0x65:
				if(MainActivity.m_English)
					str += "错误代码：0x65, 空白轨道";
				else
					str += "Error Code: 0x65, Blank Track";
				break;
			}

			str += "\r\nTrack2: ";
			switch(_BlockData[2])
			{
			case 0x60:
				MainActivity.CopyByte(Track2_Buf, _BlockData, 6 + _BlockData[1], _BlockData[3]);
				str += MainActivity.ByteToString(Track2_Buf);
				break;
			case 0x61:
				if(MainActivity.m_English)
					str += "错误代码：0x61, SS错误";
				else
					str += "Error Code: 0x61, SS Error";
				break;
			case 0x62:
				if(MainActivity.m_English)
					str += "错误代码：0x62, ES错误";
				else
					str += "Error Code: 0x62, ES Error";
				break;
			case 0x63:
				if(MainActivity.m_English)
					str += "错误代码：0x63, P错误";
				else
					str += "Error Code: 0x63, P Error";
				break;
			case 0x64:
				if(MainActivity.m_English)
					str += "错误代码：0x64, LRC错误";
				else
					str += "Error Code: 0x64, LRC Error";
				break;
			case 0x65:
				if(MainActivity.m_English)
					str += "错误代码：0x65, 空白轨道";
				else
					str += "Error Code: 0x65, Blank Track";
				break;
			}
			str += "\r\nTrack3: ";
			switch(_BlockData[4])
			{
			case 0x60:
				MainActivity.CopyByte(Track3_Buf, _BlockData, 6 + _BlockData[1] + _BlockData[3], _BlockData[5]);
				str += MainActivity.ByteToString(Track3_Buf);
				break;
			case 0x61:
				if(MainActivity.m_English)
					str += "错误代码：0x61, SS错误";
				else
					str += "Error Code: 0x61, SS Error";
				break;
			case 0x62:
				if(MainActivity.m_English)
					str += "错误代码：0x62, ES错误";
				else
					str += "Error Code: 0x62, ES Error";
				break;
			case 0x63:
				if(MainActivity.m_English)
					str += "错误代码：0x63, P错误";
				else
					str += "Error Code: 0x63, P Error";
				break;
			case 0x64:
				if(MainActivity.m_English)
					str += "错误代码：0x64, LRC错误";
				else
					str += "Error Code: 0x64, LRC Error";
				break;
			case 0x65:
				if(MainActivity.m_English)
					str += "错误代码：0x65, 空白轨道";
				else
					str += "Error Code: 0x65, Blank Track";
				break;
			}
			Toast.makeText(c, str, Toast.LENGTH_LONG).show();*/
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("读磁卡命令执行失败");
			else
				MainActivity.ShowMessage("Read Magcard Excute Failed");
		}
	}

	public static void OnClickClearMagcardData(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_ClearMagCardData();
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("清除磁卡缓冲区数据命令执行成功");
			else
				MainActivity.ShowMessage("Clear Magcard Buffer Data Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("清除磁卡缓冲区数据命令执行失败");
			else
				MainActivity.ShowMessage("Clear Magcard Buffer Data Excute Failed");
		}
	}
}
