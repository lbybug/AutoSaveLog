package com.example.m100_demoserial;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import M100_Package.*;

public class Control extends Activity {

	public Button ResetVersionButton;
	public Button ResetEjectButton;
	public Button ResetReenterButton;
	public Button ResetRecycleButton;
	public Button CheckCardPositionButton;
	public Button EnterCardUntimeButton;
	public Button EnterMagcardUntimeButton;
	public Button EnterCardButton;
	public Button EnterMagcardButton;
	public Button CheckSensorButton;
	public Button MoveRFCardPositionButton;
	public Button MoveICCardPositionButton;
	public Button EjectButton;
	public Button RecycleButton;
	public Button CheckVoltageButton;
	public Button GetIDButton;

	public static void showdemo(Activity activity)
	{
		Intent intent = new Intent(activity, Control.class);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control);

		ResetVersionButton = (Button)findViewById(R.id.ResetVersion);
	}

	public static void OnClickResetVersion(Context c)
	{
		int nRet;
		String str="";
		byte[] Version = new byte[50];

		nRet = M100_Serial.M100_Reset((byte)0x30, Version);
		if(nRet == MainActivity.OK)
		{
			str = MainActivity.ByteToString(Version);
			Toast.makeText(c, str, Toast.LENGTH_LONG).show();
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位上传版本命令执行成功");
			else
				MainActivity.ShowMessage("Reset And get Version Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位上传版本命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Reset And get Version Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickResetEject(Context c)
	{
		int nRet;
		byte[] Version = new byte[50];

		nRet = M100_Serial.M100_Reset((byte)0x31, Version);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位弹卡命令执行成功");
			else
				MainActivity.ShowMessage("Reset And Eject Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位弹卡命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Reset And Eject Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickResetReenter(Context c)
	{
		int nRet;
		byte[] Version = new byte[50];

		nRet = M100_Serial.M100_Reset((byte)0x33, Version);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位重进卡命令执行成功");
			else
				MainActivity.ShowMessage("Reset And ReEnter Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位重进卡命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Reset And ReEnter Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickResetRecycle(Context c)
	{
		int nRet;
		byte[] Version = new byte[50];

		nRet = M100_Serial.M100_Reset((byte)0x32, Version);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位回收卡命令执行成功");
			else
				MainActivity.ShowMessage("Reset And Recycle Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("复位回收卡命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Reset And Recycle Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickEnterCardUntime(Context c)
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
				MainActivity.ShowMessage("Front Enter Card Untime Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickEnterMagcardUntime(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_EnterCardUntime((byte)0x35);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端立即进磁卡命令执行成功");
			else
				MainActivity.ShowMessage("Front Enter MagCard Untime Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端立即进磁卡命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Front Enter MagCard Untime Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickEnterCard(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_EnterCard((byte)0x30, 10000);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端等待进卡命令执行成功");
			else
				MainActivity.ShowMessage("Front Enter Card Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端等待进卡命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Front Enter Card Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickEnterMagCard(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_EnterCard((byte)0x31, 0);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端等待进磁卡命令执行成功");
			else
				MainActivity.ShowMessage("Front Enter MagCard Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("前端等待进磁卡命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Front Enter MagCard Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickMoveRFCardPosition(Context c)
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
				MainActivity.ShowMessage("Move card to rf card position Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickMoveICCardPosition(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_MoveCard((byte)0x31);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("移动卡片到IC卡位置命令执行成功");
			else
				MainActivity.ShowMessage("Move card to IC card position Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("移动卡片到IC卡位置命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Move card to IC card position Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickEject(Context c)
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
				MainActivity.ShowMessage("Eject card Excute Failed");

		}
	}

	public static void OnClickRecycle(Context c)
	{
		int nRet;

		nRet = M100_Serial.M100_MoveCard((byte)0x35);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("回收命令执行成功");
			else
				MainActivity.ShowMessage("Recycle card Excute Success");
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("回收命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Recycle card Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickCheckCardPosition(Context c)
	{
		int nRet;
		String str="";
		byte[] CardPosition=new byte[4];

		nRet = M100_Serial.M100_CheckCardPosition(CardPosition);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
			{
				MainActivity.ShowMessage("查询卡片位置命令执行成功");
				switch(CardPosition[0])
				{
					case 0x30:
						str += "\r\n通道状态值 = 0x30：卡片在前端不夹卡位置\r\n";
						break;
					case 0x31:
						str += "\r\n通道状态值 = 0x31：卡片在前端夹卡位置\r\n";
						break;
					case 0x32:
						str += "\r\n通道状态值 = 0x32：卡片在射频卡位置\r\n";
						break;
					case 0x33:
						str += "\r\n通道状态值 = 0x33：卡片在IC卡位置\r\n";
						break;
					case 0x34:
						str += "\r\n通道状态值 = 0x34：卡片在后端夹卡位置\r\n";
						break;
					case 0x35:
						str += "\r\n通道状态值 = 0x35：读卡器内部无卡\r\n";
						break;
					case 0x36:
						str += "\r\n通道状态值 = 0x36：卡片不在标准位置\r\n";
						break;
					default:
						str += "\r\n通道状态值 = 0x" + MainActivity.HexToStr(CardPosition[0]) + "：错误状态值\r\n";
						break;
				}

			}
			else
			{
				MainActivity.ShowMessage("Check Card Position Excute Success");
				switch(CardPosition[0])
				{
					case 0x30:
						str += "\r\nChannel States = 0x30: Card In Front Non-Clip Position\r\n";
						break;
					case 0x31:
						str += "\r\nChannel States = 0x31: Card In Front Clip Position\r\n";
						break;
					case 0x32:
						str += "\r\nChannel States = 0x32: Card In RF Card Position\r\n";
						break;
					case 0x33:
						str += "\r\nChannel States = 0x33: Card In IC Card Position\r\n";
						break;
					case 0x34:
						str += "\r\nChannel States = 0x34: Card In Back Clip Position\r\n";
						break;
					case 0x35:
						str += "\r\nChannel States = 0x35: Have no Card\r\n";
						break;
					case 0x36:
						str += "\r\nChannel States = 0x36: Card In not Standard Position\r\n";
						break;
					default:
						str += "\r\nChannel States = 0x" + MainActivity.HexToStr(CardPosition[0]) + ": Wrong Value\r\n";
						break;
				}

			}
			//MainActivity.ShowMessage(c, str);
			Toast.makeText(c, str, Toast.LENGTH_LONG).show();
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("查询卡片位置命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Check Card Position Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickCheckSensor(Context c)
	{
		int nRet;
		String str="";
		byte[] States = new byte[30];

		nRet = M100_Serial.M100_CheckSensorStates(States);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
			{
				MainActivity.ShowMessage("查询传感器状态命令执行成功");

				switch(States[0])
				{
					case 0x30:
						str += "\r\n传感器：PSS1 = 0x30：无卡\r\n";
						break;
					case 0x31:
						str += "\r\n传感器：PSS1 = 0x31：有卡\r\n";
						break;
					default:
						str += "\r\n传感器：PSS1 = 0x" + MainActivity.HexToStr(States[0]) + "：错误状态值\r\n";
						break;
				}

				switch(States[1])
				{
					case 0x30:
						str += "\r\n传感器：PSS2 = 0x30：无卡\r\n";
						break;
					case 0x31:
						str += "\r\n传感器：PSS2 = 0x31：有卡\r\n";
						break;
					default:
						str += "\r\n传感器：PSS2 = 0x" + MainActivity.HexToStr(States[1]) + "：错误状态值\r\n";
						break;
				}

				switch(States[2])
				{
					case 0x30:
						str += "\r\n传感器：PSS3 = 0x30：无卡\r\n";
						break;
					case 0x31:
						str += "\r\n传感器：PSS3 = 0x31：有卡\r\n";
						break;
					default:
						str += "\r\n传感器：PSS3 = 0x" + MainActivity.HexToStr(States[2]) + "：错误状态值\r\n";
						break;
				}

				switch(States[3])
				{
					case 0x30:
						str += "\r\n传感器：PSS4 = 0x30：无卡\r\n";
						break;
					case 0x31:
						str += "\r\n传感器：PSS4 = 0x31：有卡\r\n";
						break;
					default:
						str += "\r\n传感器：PSS4 = 0x" + MainActivity.HexToStr(States[3]) + "：错误状态值\r\n";
						break;
				}

				switch(States[4])
				{
					case 0x30:
						str += "\r\n传感器：PSS5 = 0x30：无卡\r\n";
						break;
					case 0x31:
						str += "\r\n传感器：PSS5 = 0x31：有卡\r\n";
						break;
					default:
						str += "\r\n传感器：PSS5 = 0x" + MainActivity.HexToStr(States[4]) + "：错误状态值\r\n";
						break;
				}

				switch(States[5])
				{
					case 0x30:
						str += "\r\n传感器：CTKSW = 0x30：无卡\r\n";
						break;
					case 0x31:
						str += "\r\n传感器：CTKSW = 0x31：有卡\r\n";
						break;
					default:
						str += "\r\n传感器：CTKSW = 0x" + MainActivity.HexToStr(States[5]) + "：错误状态值\r\n";
						break;
				}

				switch(States[6])
				{
					case 0x30:
						str += "\r\n传感器：KSW = 0x30：无卡\r\n";
						break;
					case 0x31:
						str += "\r\n传感器：KSW = 0x31：有卡\r\n";
						break;
					default:
						str += "\r\n传感器：KSW = 0x" + MainActivity.HexToStr(States[6]) + "：错误状态值\r\n";
						break;
				}

			}
			else
			{
				MainActivity.ShowMessage("Check Sensor States Excute Success");
				switch(States[0])
				{
					case 0x30:
						str += "\r\nSensor: PSS1 = 0x30: Have no Card\r\n";
						break;
					case 0x31:
						str += "\r\nSensor: PSS1 = 0x31: Have Card\r\n";
						break;
					default:
						str += "\r\nSensor: PSS1 = 0x" + MainActivity.HexToStr(States[0]) + ": Wrong Value\r\n";
						break;
				}

				switch(States[1])
				{
					case 0x30:
						str += "\r\nSensor: PSS2 = 0x30: Have no Card\r\n";
						break;
					case 0x31:
						str += "\r\nSensor: PSS2 = 0x31: Have Card\r\n";
						break;
					default:
						str += "\r\nSensor: PSS2 = 0x" + MainActivity.HexToStr(States[1]) + ": Wrong Value\r\n";
						break;
				}

				switch(States[2])
				{
					case 0x30:
						str += "\r\nSensor: PSS3 = 0x30: Have no Card\r\n";
						break;
					case 0x31:
						str += "\r\nSensor: PSS3 = 0x31: Have Card\r\n";
						break;
					default:
						str += "\r\nSensor: PSS3 = 0x" + MainActivity.HexToStr(States[2]) + ": Wrong Value\r\n";
						break;
				}

				switch(States[3])
				{
					case 0x30:
						str += "\r\nSensor: PSS4 = 0x30: Have no Card\r\n";
						break;
					case 0x31:
						str += "\r\nSensor: PSS4 = 0x31: Have Card\r\n";
						break;
					default:
						str += "\r\nSensor: PSS4 = 0x" + MainActivity.HexToStr(States[3]) + ": Wrong Value\r\n";
						break;
				}

				switch(States[4])
				{
					case 0x30:
						str += "\r\nSensor: PSS5 = 0x30: Have no Card\r\n";
						break;
					case 0x31:
						str += "\r\nSensor: PSS5 = 0x31: Have Card\r\n";
						break;
					default:
						str += "\r\nSensor: PSS5 = 0x" + MainActivity.HexToStr(States[4]) + ": Wrong Value\r\n";
						break;
				}

				switch(States[5])
				{
					case 0x30:
						str += "\r\nSensor: CTKSW = 0x30: Have no Card\r\n";
						break;
					case 0x31:
						str += "\r\nSensor: CTKSW = 0x31: Have Card\r\n";
						break;
					default:
						str += "\r\nSensor: CTKSW = 0x" + MainActivity.HexToStr(States[5]) + ": Wrong Value\r\n";
						break;
				}

				switch(States[6])
				{
					case 0x30:
						str += "\r\nSensor: KSW = 0x30: Have no Card\r\n";
						break;
					case 0x31:
						str += "\r\nSensor: KSW = 0x31: Have Card\r\n";
						break;
					default:
						str += "\r\nSensor: KSW = 0x" + MainActivity.HexToStr(States[6]) + ": Wrong Value\r\n";
						break;
				}

			}
			Toast.makeText(c, str, Toast.LENGTH_LONG).show();
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("查询传感器状态命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Check Sensor States Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}

	public static void OnClickCheckVoltage(Context c)
	{
		int nRet;
		byte[] _Voltage=new byte[50];
		String str="";
		double value = 0.00;
		double Voltage = 0.00;
		DecimalFormat df = new DecimalFormat("0.00");

		nRet = M100_Serial.M100_CheckSensorVoltage(_Voltage);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
			{
				MainActivity.ShowMessage("查询传感器电压命令执行成功");
			}
			else
			{
				MainActivity.ShowMessage("Check Sensor Voltage Excute Success");
			}

			if(_Voltage[0] < 0 && _Voltage[1] < 0)
				value = (_Voltage[0] + 256) + (_Voltage[1] + 256) * 256;
			else if(_Voltage[0] >= 0 && _Voltage[1] < 0)
				value = _Voltage[0] + (_Voltage[1] + 256) * 256;
			else if(_Voltage[0] < 0 && _Voltage[1] >= 0)
				value = (_Voltage[0] + 256) + _Voltage[1] * 256;
			else
				value = _Voltage[0] + _Voltage[1] * 256;

			if(value != 0)
			{
				Voltage = (double)5 / (double)1024 * value;
				if(MainActivity.m_English)
					str += "传感器：PSS1 电压值 = " + df.format(Voltage) + "\r\n";
				else
					str += "Sensor: PSS1 Voltage Value = " + df.format(Voltage) + "\r\n";
			}

			if(_Voltage[2] < 0 && _Voltage[3] < 0)
				value = (_Voltage[2] + 256) + (_Voltage[3] + 256) * 256;
			else if(_Voltage[2] >= 0 && _Voltage[3] < 0)
				value = _Voltage[2] + (_Voltage[3] + 256) * 256;
			else if(_Voltage[2] < 0 && _Voltage[3] >= 0)
				value = (_Voltage[2] + 256) + _Voltage[3] * 256;
			else
				value = _Voltage[2] + _Voltage[3] * 256;

			if(value != 0)
			{
				Voltage = (double)5 / (double)1024 * value;
				if(MainActivity.m_English)
					str += "传感器：PSS2 电压值 = " + df.format(Voltage) + "\r\n";
				else
					str += "Sensor: PSS2 Voltage Value = " + df.format(Voltage) + "\r\n";
			}

			if(_Voltage[4] < 0 && _Voltage[5] < 0)
				value = (_Voltage[4] + 256) + (_Voltage[5] + 256) * 256;
			else if(_Voltage[4] >= 0 && _Voltage[5] < 0)
				value = _Voltage[4] + (_Voltage[5] + 256) * 256;
			else if(_Voltage[4] < 0 && _Voltage[5] >= 0)
				value = (_Voltage[4] + 256) + _Voltage[5] * 256;
			else
				value = _Voltage[4] + _Voltage[5] * 256;

			if(value != 0)
			{
				Voltage = (double)5 / (double)1024 * value;
				if(MainActivity.m_English)
					str += "传感器：PSS3 电压值 = " + df.format(Voltage) + "\r\n";
				else
					str += "Sensor: PSS3 Voltage Value = " + df.format(Voltage) + "\r\n";
			}

			if(_Voltage[6] < 0 && _Voltage[7] < 0)
				value = (_Voltage[6] + 256) + (_Voltage[7] + 256) * 256;
			else if(_Voltage[6] >= 0 && _Voltage[7] < 0)
				value = _Voltage[6] + (_Voltage[7] + 256) * 256;
			else if(_Voltage[6] < 0 && _Voltage[7] >= 0)
				value = (_Voltage[6] + 256) + _Voltage[7] * 256;
			else
				value = _Voltage[6] + _Voltage[7] * 256;

			if(value != 0)
			{
				Voltage = (double)5 / (double)1024 * value;
				if(MainActivity.m_English)
					str += "传感器：PSS4 电压值 = " + df.format(Voltage) + "\r\n";
				else
					str += "Sensor: PSS4 Voltage Value = " + df.format(Voltage) + "\r\n";
			}

			if(_Voltage[8] < 0 && _Voltage[9] < 0)
				value = (_Voltage[8] + 256) + (_Voltage[9] + 256) * 256;
			else if(_Voltage[8] >= 0 && _Voltage[9] < 0)
				value = _Voltage[8] + (_Voltage[9] + 256) * 256;
			else if(_Voltage[8] < 0 && _Voltage[9] >= 0)
				value = (_Voltage[8] + 256) + _Voltage[9] * 256;
			else
				value = _Voltage[8] + _Voltage[9] * 256;

			if(value != 0)
			{
				Voltage = (double)5 / (double)1024 * value;
				if(MainActivity.m_English)
					str += "传感器：PSS5 电压值 = " + df.format(Voltage) + "\r\n";
				else
					str += "Sensor: PSS5 Voltage Value = " + df.format(Voltage) + "\r\n";
			}

			Toast.makeText(c, str, Toast.LENGTH_LONG).show();
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("查询传感器电压命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Check Sensor Voltage Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));

		}
	}


	public static void OnClickGetID(Context c)
	{
		int nRet;
		String[] str=new String[2];

		nRet = M100_Serial.M100_GetIdCardNo(str);
		if(nRet == MainActivity.OK)
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("获取设备ID号命令执行成功");
			else
				MainActivity.ShowMessage("Get Machine ID Excute Success");
			Toast.makeText(c, str[0], Toast.LENGTH_LONG).show();
		}
		else
		{
			if(MainActivity.m_English)
				MainActivity.ShowMessage("获取设备ID号命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				MainActivity.ShowMessage("Get Machine ID Excute Failed");

		}
	}

}
