package com.example.m100_demoserial;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import M100_Package.M100_Serial;

public class MainActivity extends Activity {

	/***********************MainActivity******************************/
	public static EditText CommandStatesInfo;
	public static Button	ConnectButton;
	public static Button	DisConnectButton;
	public static TextView CommandInfo;
	public static int OK = 0;
	public static boolean m_English = true;
	public static int MachineType = 0;//0--M100, 1--R300
	private static final String[] StrComPort={"ttyS0","ttyS1","ttyS2","ttyS3","ttyS4","ttyS5","ttyUSB0","ttyUSB1","Enter Serial Name"};
	private Spinner ComSpinner;
	private ArrayAdapter<String> Comadapter;
	private static int Port = 0;
	public static View view;
	public static RadioButton ChineseButton;
	public static RadioButton EnglishButton;
	public static RadioGroup LanguaheGroup;
	public static TabWidget tabWidget;
	public static EditText PortName;

	/*****************************Control*******************************/
	public static Button ResetVersionButton;
	public static Button ResetEjectButton;
	public static Button ResetReenterButton;
	public static Button ResetRecycleButton;
	public static Button EnterCardUntimeButton;
	public static Button EnterMagcardUntimeButton;
	public static Button EnterCardButton;
	public static Button EnterMagCardButton;
	public static Button MoveRFCardPositionButton;
	public static Button MoveICCardPositionButton;
	public static Button EjectButton;
	public static Button RecycleButton;
	public static Button CheckCardPositionButton;
	public static Button CheckVoltageButton;
	public static Button CheckSensorButton;
	public static Button GetIDButton;

	/*****************************ReadMagCard*******************************/
	public static Button ReadEnterCardButton;
	public static Button ReadEjectButton;
	public static Button ReadMagcardButton;
	public static Button ClearMagcardDataButton;

	public static EditText Track1_Data;
	public static EditText Track2_Data;
	public static EditText Track3_Data;

	public static TextView Track1_Text;
	public static TextView Track2_Text;
	public static TextView Track3_Text;
	/*****************************ContactIC*******************************/

	/******************************15693***********************************/
	public static Button GetUidButton;
	public static Button ReadBlockDataButton;
	public static Button WriteBlockDataButton;
	public static Button LockBlockDataButton;
	public static Button WriteAFIButton;
	public static Button LockAFIButton;
	public static Button WriteDSFIDButton;
	public static Button LockDSFIDButton;
	public static Button ReadSafeBitButton;
	public static Button ChooseCardButton;
	public static Button SystemInfoButton;

	public static EditText BlockAddressText;
	public static EditText BlockLengthText;
	public static EditText BlockDataText;

	/*****************************CPU*******************************/
	public static EditText SetCPUSAPDU;
	public static EditText SetCPURAPDU;
	public static EditText SetCPUATR;

	public static Button ContactICCPUPowerOn;
	public static Button ContactICCPUPowerOff;
	public static Button ContactICCPUT0APDU;
	public static Button ContactICCPUT1APDU;
	public static Button ContactICCPUGetICCard;
	public static Button ContactICCPUGetSocInfor;
	public static Button ContactICCPUGet55Data;
	/*****************************NonContactIC*******************************/
	public static TabHost Nonth;
	public static TabHost th;
	public static View NonView;
	public static TabWidget NontabWidget;

	public static final String[] StrPassWordType = {"KeyA", "KeyB"};
	public static Button NonContactICEnterCardButton;
	public static Button NonContactICMoveRFPositionButton;
	public static Button NonContactICEjectButton;
	/*****************************S50*******************************/
	public static final String[] S50StrSector = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F"};
	public static final String[] S50StrBlock = {"00", "01", "02", "03"};
	public static EditText S50CardIDData;
	public static EditText S50PassWordData;
	public static EditText S50IncreaseData;
	public static EditText S50DecreaseData;
	public static EditText S50InitData;
	public static EditText S50WriteData;
	public static Spinner S50PassWordTypeSpinner;
	public static Spinner S50PassWordSectorSpinner;
	public static Spinner S50SectorAddrSpinner;
	public static Spinner S50BlockAddrSpinner;
	private ArrayAdapter<String> S50PassWordTypeadapter;
	public static byte S50PassWordType = 0;
	private ArrayAdapter<String> S50PassWordSectoradapter;
	public static byte S50PassWordSector = 0;
	private ArrayAdapter<String> S50SectorAddradapter;
	public static byte S50SectorAddr = 0;
	private ArrayAdapter<String> S50BlockAddradapter;
	public static byte S50BlockAddr = 0;

	public static Button S50DetectCardButton;
	public static Button S50GetCardIDButton;
	public static Button S50VerifyPassWordButton;
	public static Button S50HaltButton;
	public static Button S50ReadBlockDataButton;
	public static Button S50WriteBlockDataButton;
	public static Button S50InCreaseButton;
	public static Button S50DeCreaseButton;
	public static Button S50InitDataButton;

	public static TextView NonContactICS50CardIDText;
	public static TextView NonContactICS50PassWordText;
	public static TextView NonContactICS50PassWordTypeText;
	public static TextView NonContactICS50PassWordSectorText;
	public static TextView NonContactICS50SectorAddrText;
	public static TextView NonContactICS50BlockAddrText;
	public static TextView NonContactICS50IncreaseText;
	public static TextView NonContactICS50DecreaseText;
	public static TextView NonContactICS50InitText;
	public static TextView NonContactICS50WriteText;
	/*****************************S70*******************************/
	public static final String[] S70StrSector = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D", "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27"};
	public static final String[] S70StrBlock = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F"};
	public static EditText S70CardIDData;
	public static EditText S70PassWordData;
	public static EditText S70IncreaseData;
	public static EditText S70DecreaseData;
	public static EditText S70InitData;
	public static EditText S70WriteData;
	public static Spinner S70PassWordTypeSpinner;
	public static Spinner S70PassWordSectorSpinner;
	public static Spinner S70SectorAddrSpinner;
	public static Spinner S70BlockAddrSpinner;
	private ArrayAdapter<String> S70PassWordTypeadapter;
	public static byte S70PassWordType = 0;
	private ArrayAdapter<String> S70PassWordSectoradapter;
	public static byte S70PassWordSector = 0;
	private ArrayAdapter<String> S70SectorAddradapter;
	public static byte S70SectorAddr = 0;
	private ArrayAdapter<String> S70BlockAddradapter;
	public static byte S70BlockAddr = 0;

	public static Button S70DetectCardButton;
	public static Button S70GetCardIDButton;
	public static Button S70VerifyPassWordButton;
	public static Button S70HaltButton;
	public static Button S70ReadBlockDataButton;
	public static Button S70WriteBlockDataButton;
	public static Button S70InCreaseButton;
	public static Button S70DeCreaseButton;
	public static Button S70InitDataButton;

	public static TextView NonContactICS70CardIDText;
	public static TextView NonContactICS70PassWordText;
	public static TextView NonContactICS70PassWordTypeText;
	public static TextView NonContactICS70PassWordSectorText;
	public static TextView NonContactICS70SectorAddrText;
	public static TextView NonContactICS70BlockAddrText;
	public static TextView NonContactICS70IncreaseText;
	public static TextView NonContactICS70DecreaseText;
	public static TextView NonContactICS70InitText;
	public static TextView NonContactICS70WriteText;
	/*****************************UL*******************************/
	public static Button ULDetectCardButton;
	public static Button ULHaltButton;
	public static Button ULGetCardIDButton;
	public static Button ULReadBlockDataButton;
	public static Button ULWriteBlockDataButton;

	public static EditText ULCardIDDataText;
	public static EditText ULWriteDataText;

	public static TextView ULCardIDtextView;
	public static TextView ULSectorAddrtextView;
	public static TextView ULWriteDataTextView;

	public static Spinner ULSectorAddrSpinner;
	private ArrayAdapter<String> ULSectorAddradapter;
	public static final String[] ULStrSector = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F"};
	public static byte ULSectorAddr = 0;
	/*****************************TypeA*******************************/
	public static Button NonContactICTypeAPowerOn;
	public static Button NonContactICTypeAAPDU;
	public static Button NonContactICTypeAGetICCard;

	public static EditText SetTypeASAPDU;
	public static EditText SetTypeARAPDU;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("M100");

		/***********************MainActivity******************************/
		ConnectButton = (Button)findViewById(R.id.Connect);
		DisConnectButton = (Button)findViewById(R.id.DisConnect);
		CommandStatesInfo = (EditText)findViewById(R.id.StatesInfo);
		CommandStatesInfo.setText("");
		ComSpinner=(Spinner)findViewById(R.id.spinner1);
		CommandInfo = (TextView)findViewById(R.id.textViewInfo);
		PortName = (EditText)findViewById(R.id.autoCompleteTextView1);

		Comadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,StrComPort);
		Comadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ComSpinner.setAdapter(Comadapter);
		ComSpinner.setVisibility(View.VISIBLE);
		ComSpinner.setOnItemSelectedListener(new ComPortListener());

		ChineseButton = (RadioButton)findViewById(R.id.ChineseRadio);
		EnglishButton = (RadioButton)findViewById(R.id.EnglishRadio);
		LanguaheGroup = (RadioGroup)findViewById(R.id.radioGroup1);

		ChineseButton.setChecked(true);
		EnglishButton.setChecked(false);

		LanguaheGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(ChineseButton.getId() == arg1)
				{
					ShowChinese();
				}
				else if(EnglishButton.getId() == arg1)
				{
					ShowEnglish();
				}
			}
		});

		LocalCat localcat=new LocalCat(MainActivity.this, "/storage/emulated/0/ttce_log");
		localcat.start();

		InitDemoClass(MainActivity.m_English);

		InitControlClass(view);

		InitReadMagCardClass(view);
		Init15693Class(view);
		InitICContactClass(view);
		InitICContactCPUClass(view);
		InitICContactSIMClass(view);
		InitICContactSLE4428Class(view);
		InitICContactSLE4442Class(view);
		InitICContactAT102Class(view);
		InitICContactAT1604Class(view);
		InitICContactAT1608Class(view);
		InitICContactAT24XXClass(view);
		InitICContactAT45041Class(view);

		InitNonContactICClass(view);
		InitS50Class(NonView);
		InitS70Class(NonView);
		InitULClass(NonView);
		InitTypeAClass(NonView);
	}

	public void UpdateTabHost(TabHost tabhos)
	{
		for(int i = 0; i < tabhos.getTabWidget().getChildCount(); i++)
		{
			View v = tabhos.getTabWidget().getChildAt(i);
			TextView TV = (TextView)v.findViewById(android.R.id.title);
			TV.setTextSize(16);
			TV.setTypeface(Typeface.SERIF, 2);
			if(tabhos.getCurrentTab() == i)
			{
				//v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ttce));
				TV.setTextColor(this.getResources().getColorStateList(android.R.color.holo_green_dark));
			}
			else
			{
				//v.setBackgroundDrawable(getResources().getDrawable(R.drawable.ttce));
				TV.setTextColor(this.getResources().getColorStateList(android.R.color.holo_green_dark));
			}
		}
	}

	public void InitDemoClass(boolean states)
	{
		th = (TabHost)findViewById(R.id.tabhost);
		th.setup();
		tabWidget = th.getTabWidget();

		LayoutInflater lif = LayoutInflater.from(this);
		lif.inflate(R.layout.control, th.getTabContentView());
		lif.inflate(R.layout.readmagcard, th.getTabContentView());
		lif.inflate(R.layout.d1569, th.getTabContentView());
		lif.inflate(R.layout.iccardconact, th.getTabContentView());
		lif.inflate(R.layout.iccardnonconact, th.getTabContentView());

		if(states)
		{
			th.addTab(th.newTabSpec("control").setIndicator("控制命令", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.Controltab));
			th.addTab(th.newTabSpec("readmagcard").setIndicator("读磁卡", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.ReadMagCardtab));
			th.addTab(th.newTabSpec("d15693").setIndicator("15693", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.D15693tab));
			th.addTab(th.newTabSpec("iccardconact").setIndicator("接触式IC卡", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.ICCardContacttab));
			th.addTab(th.newTabSpec("iccardnonconact").setIndicator("非接触式IC卡", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.ICCardNonContacttab));
			//th.addTab(th.newTabSpec("typea").setIndicator("非接触式IC卡", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.TypeAtab));
			CommandInfo.setText("命令状态：");
			ConnectButton.setText("连接");
			DisConnectButton.setText("断开");
			ConnectButton.setEnabled(true);
			DisConnectButton.setEnabled(false);
		}
		else
		{
			th.addTab(th.newTabSpec("control").setIndicator("Control").setContent(R.id.Controltab));
			th.addTab(th.newTabSpec("readmagcard").setIndicator("Read MagCard").setContent(R.id.ReadMagCardtab));
			th.addTab(th.newTabSpec("iccardconact").setIndicator("Contact IC Card").setContent(R.id.ICCardContacttab));
			th.addTab(th.newTabSpec("iccardnonconact").setIndicator("Non-Contact IC Card").setContent(R.id.ICCardNonContacttab));
			CommandInfo.setText("Command States: ");
			ConnectButton.setText("Connect");
			DisConnectButton.setText("DisConnect");
			ConnectButton.setEnabled(true);
			DisConnectButton.setEnabled(false);
		}
		for(int i = 0; i < tabWidget.getChildCount(); i++)
		{
			tabWidget.getChildAt(i).getLayoutParams().height = 30;
			tabWidget.getChildAt(i).getLayoutParams().width = 70;
		}

		th.setCurrentTab(0);
		UpdateTabHost(th);
		view = th.getTabContentView();
	}

	public void InitControlClass(View Controlview)
	{
		ResetVersionButton = (Button)Controlview.findViewById(R.id.ResetVersion);
		ResetEjectButton = (Button)Controlview.findViewById(R.id.ResetEject);
		ResetReenterButton = (Button)Controlview.findViewById(R.id.ResetReenter);
		ResetRecycleButton = (Button)Controlview.findViewById(R.id.ResetRecycle);
		EnterCardUntimeButton = (Button)Controlview.findViewById(R.id.EnterCardUntime);
		EnterMagcardUntimeButton = (Button)Controlview.findViewById(R.id.EnterMagcardUntime);
		EnterCardButton = (Button)Controlview.findViewById(R.id.EnterCard);
		EnterMagCardButton = (Button)Controlview.findViewById(R.id.EnterMagCard);
		MoveRFCardPositionButton = (Button)Controlview.findViewById(R.id.MoveRFCardPosition);
		MoveICCardPositionButton = (Button)Controlview.findViewById(R.id.MoveICCardPosition);
		EjectButton = (Button)Controlview.findViewById(R.id.Eject);
		RecycleButton = (Button)Controlview.findViewById(R.id.Recycle);
		CheckCardPositionButton = (Button)Controlview.findViewById(R.id.CheckCardPosition);
		CheckVoltageButton = (Button)Controlview.findViewById(R.id.CheckVoltage);
		CheckSensorButton = (Button)Controlview.findViewById(R.id.CheckSensor);
		GetIDButton = (Button)Controlview.findViewById(R.id.Button01);
	}

	public void InitReadMagCardClass(View ReadMagCardview)
	{
		ReadEnterCardButton = (Button)ReadMagCardview.findViewById(R.id.ReadEnterCard);
		ReadEjectButton = (Button)ReadMagCardview.findViewById(R.id.ReadEject);
		ReadMagcardButton = (Button)ReadMagCardview.findViewById(R.id.ReadMagcard);
		ClearMagcardDataButton = (Button)ReadMagCardview.findViewById(R.id.ClearMagcardData);
		Track1_Data = (EditText)ReadMagCardview.findViewById(R.id.ReadTrack1Data);
		Track2_Data = (EditText)ReadMagCardview.findViewById(R.id.ReadTrack2Data);
		Track3_Data = (EditText)ReadMagCardview.findViewById(R.id.ReadTrack3Data);
		Track1_Text = (TextView)ReadMagCardview.findViewById(R.id.ReadTrack1);
		Track2_Text = (TextView)ReadMagCardview.findViewById(R.id.ReadTrack2);
		Track3_Text = (TextView)ReadMagCardview.findViewById(R.id.ReadTrack3);
	}

	/********************************************15693*********************************************************/
	public void Init15693Class(View D15693View)
	{
		GetUidButton = (Button)D15693View.findViewById(R.id.GetUidButton);
		ReadBlockDataButton = (Button)D15693View.findViewById(R.id.ReadBlockButton);
		WriteBlockDataButton = (Button)D15693View.findViewById(R.id.WriteBlockButton);
		LockBlockDataButton = (Button)D15693View.findViewById(R.id.LockBlockButton);
		WriteAFIButton = (Button)D15693View.findViewById(R.id.WriteAFIButton);
		LockAFIButton = (Button)D15693View.findViewById(R.id.LockAFIButton);
		WriteDSFIDButton = (Button)D15693View.findViewById(R.id.WriteDSFIDButton);
		LockDSFIDButton = (Button)D15693View.findViewById(R.id.LockDSFIDButton);
		ReadSafeBitButton = (Button)D15693View.findViewById(R.id.ReadSafeBitButton);
		ChooseCardButton = (Button)D15693View.findViewById(R.id.ChooseCardButton);
		SystemInfoButton = (Button)D15693View.findViewById(R.id.SystemInfoButton);

		BlockAddressText = (EditText)D15693View.findViewById(R.id.BlockAddressText);
		BlockLengthText = (EditText)D15693View.findViewById(R.id.BlockLengthText);
		BlockDataText = (EditText)D15693View.findViewById(R.id.BlockDataText);
	}


	/********************************************CONTACT IC*********************************************************/
	public void InitICContactClass(View ICContactview)
	{

	}

	public void InitICContactCPUClass(View ICContactCPUview)
	{
		ContactICCPUPowerOn = (Button)ICContactCPUview.findViewById(R.id.CPUActive);
		ContactICCPUPowerOff = (Button)ICContactCPUview.findViewById(R.id.CPUInAvtive);
		ContactICCPUT0APDU = (Button)ICContactCPUview.findViewById(R.id.CPUT0APDU);
		ContactICCPUT1APDU = (Button)ICContactCPUview.findViewById(R.id.CPUT1APDU);
		ContactICCPUGetICCard = (Button)ICContactCPUview.findViewById(R.id.ReadICCardNum);
		ContactICCPUGetSocInfor = (Button)ICContactCPUview.findViewById(R.id.ReadSocialSecurity);
		ContactICCPUGet55Data = (Button)ICContactCPUview.findViewById(R.id.Get55Data);
		SetCPUSAPDU = (EditText)ICContactCPUview.findViewById(R.id.CPUSAPDU);
		SetCPURAPDU = (EditText)ICContactCPUview.findViewById(R.id.CPURAPDU);
		SetCPUATR = (EditText)ICContactCPUview.findViewById(R.id.CPUATR);
		SetCPUSAPDU.setText("0084000008");
	}

	public void InitICContactSIMClass(View ICContactSIMview)
	{

	}

	public void InitICContactSLE4442Class(View ICContactSLE4442view)
	{

	}

	public void InitICContactSLE4428Class(View ICContactSLE4428view)
	{

	}

	public void InitICContactAT102Class(View ICContactAT102view)
	{

	}

	public void InitICContactAT1604Class(View ICContactAT1604view)
	{

	}

	public void InitICContactAT1608Class(View ICContactAT1608view)
	{

	}

	public void InitICContactAT24XXClass(View ICContactAT24XXview)
	{

	}

	public void InitICContactAT45041Class(View ICContactAT45041view)
	{

	}

	/********************************************NONCONTACT IC*********************************************************/
	public void InitNonContactICClass(View NonContactICview)
	{
		Nonth = (TabHost)NonContactICview.findViewById(R.id.NonContactTabHost);
		Nonth.setup();
		NontabWidget = Nonth.getTabWidget();

		LayoutInflater Nonlif = LayoutInflater.from(this);
		Nonlif.inflate(R.layout.s50, Nonth.getTabContentView());
		Nonlif.inflate(R.layout.s70, Nonth.getTabContentView());
		Nonlif.inflate(R.layout.ul, Nonth.getTabContentView());
		Nonlif.inflate(R.layout.typea, Nonth.getTabContentView());

		Nonth.addTab(Nonth.newTabSpec("s50").setIndicator("S50", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.S50tab));
		Nonth.addTab(Nonth.newTabSpec("s70").setIndicator("S70", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.S70tab));
		Nonth.addTab(Nonth.newTabSpec("ul").setIndicator("UL", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.ULtab));
		Nonth.addTab(Nonth.newTabSpec("typea").setIndicator("TypeA", getResources().getDrawable(R.drawable.ic_launcher)).setContent(R.id.TypeAtab));
		for(int i = 0; i < NontabWidget.getChildCount(); i++)
		{
			NontabWidget.getChildAt(i).getLayoutParams().height = 20;
			NontabWidget.getChildAt(i).getLayoutParams().width = 70;
		}
		Nonth.setCurrentTab(0);
		NonView = th.getTabContentView();

		NonContactICEnterCardButton = (Button)NonView.findViewById(R.id.NonContactEnterCardUntime);
		NonContactICEjectButton = (Button)NonView.findViewById(R.id.NonContactEject);
		NonContactICMoveRFPositionButton = (Button)NonView.findViewById(R.id.NonContactMoveToRFPosition);
	}

	public void InitS50Class(View S50view)
	{
		S50PassWordTypeadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,StrPassWordType);
		S50PassWordTypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S50PassWordTypeSpinner = (Spinner)S50view.findViewById(R.id.S50PassWordTypeSpinner1);
		S50PassWordTypeSpinner.setAdapter(S50PassWordTypeadapter);
		S50PassWordTypeSpinner.setOnItemSelectedListener(new S50PassWordTypeListener());

		S50PassWordSectoradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,S50StrSector);
		S50PassWordSectoradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S50PassWordSectorSpinner = (Spinner)S50view.findViewById(R.id.S50PassWordSectorSpinner1);
		S50PassWordSectorSpinner.setAdapter(S50PassWordSectoradapter);
		S50PassWordSectorSpinner.setOnItemSelectedListener(new S50PassWordSectorListener());

		S50SectorAddradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,S50StrSector);
		S50SectorAddradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S50SectorAddrSpinner = (Spinner)S50view.findViewById(R.id.S50SectorAddrSpinner1);
		S50SectorAddrSpinner.setAdapter(S50SectorAddradapter);
		S50SectorAddrSpinner.setOnItemSelectedListener(new S50SectorAddrListener());

		S50BlockAddradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,S50StrBlock);
		S50BlockAddradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S50BlockAddrSpinner = (Spinner)S50view.findViewById(R.id.S50BlockAddrSpinner1);
		S50BlockAddrSpinner.setAdapter(S50BlockAddradapter);
		S50BlockAddrSpinner.setOnItemSelectedListener(new S50BlockAddrListener());

		S50CardIDData = (EditText)S50view.findViewById(R.id.S50CardIDDataText);
		S50PassWordData = (EditText)S50view.findViewById(R.id.S50PassWordDataText);
		S50IncreaseData = (EditText)S50view.findViewById(R.id.S50IncreaseDataText);
		S50DecreaseData = (EditText)S50view.findViewById(R.id.S50DecreaseDataText);
		S50InitData = (EditText)S50view.findViewById(R.id.S50InitDataText);
		S50WriteData = (EditText)S50view.findViewById(R.id.S50WriteDataText);

		S50DetectCardButton = (Button)S50view.findViewById(R.id.S50DetectCard);
		S50GetCardIDButton = (Button)S50view.findViewById(R.id.S50GetCardID);
		S50VerifyPassWordButton = (Button)S50view.findViewById(R.id.S50VerifyPassWord);
		S50HaltButton = (Button)S50view.findViewById(R.id.S50Halt);
		S50ReadBlockDataButton = (Button)S50view.findViewById(R.id.S50ReadBlockData);
		S50WriteBlockDataButton = (Button)S50view.findViewById(R.id.S50WriteBlockData);
		S50InCreaseButton = (Button)S50view.findViewById(R.id.S50Increase);
		S50DeCreaseButton = (Button)S50view.findViewById(R.id.S50Derease);
		S50InitDataButton = (Button)S50view.findViewById(R.id.S50InitData);


		NonContactICS50CardIDText = (TextView)S50view.findViewById(R.id.S50CardIDtextView);
		NonContactICS50PassWordText = (TextView)S50view.findViewById(R.id.S50PassWordtextView);
		NonContactICS50PassWordTypeText = (TextView)S50view.findViewById(R.id.S50PassWordTypetextView);
		NonContactICS50PassWordSectorText = (TextView)S50view.findViewById(R.id.S50PassWordTypeSectortextView);
		NonContactICS50SectorAddrText = (TextView)S50view.findViewById(R.id.S50SectorAddrtextView);
		NonContactICS50BlockAddrText = (TextView)S50view.findViewById(R.id.S50BlockAddrtextView);
		NonContactICS50IncreaseText = (TextView)S50view.findViewById(R.id.S50IncreaseDatatextView);
		NonContactICS50DecreaseText = (TextView)S50view.findViewById(R.id.S50DecreaseDatatextView);
		NonContactICS50InitText = (TextView)S50view.findViewById(R.id.S50InitDatatextView);
		NonContactICS50WriteText = (TextView)S50view.findViewById(R.id.S50WriteDatatextView);
	}

	public void InitS70Class(View S70view)
	{
		S70PassWordTypeadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,StrPassWordType);
		S70PassWordTypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S70PassWordTypeSpinner = (Spinner)S70view.findViewById(R.id.S70PassWordTypeSpinner1);
		S70PassWordTypeSpinner.setAdapter(S70PassWordTypeadapter);
		S70PassWordTypeSpinner.setOnItemSelectedListener(new S70PassWordTypeListener());

		S70PassWordSectoradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,S70StrSector);
		S70PassWordSectoradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S70PassWordSectorSpinner = (Spinner)S70view.findViewById(R.id.S70PassWordSectorSpinner1);
		S70PassWordSectorSpinner.setAdapter(S70PassWordSectoradapter);
		S70PassWordSectorSpinner.setOnItemSelectedListener(new S70PassWordSectorListener());

		S70SectorAddradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,S70StrSector);
		S70SectorAddradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S70SectorAddrSpinner = (Spinner)S70view.findViewById(R.id.S70SectorAddrSpinner1);
		S70SectorAddrSpinner.setAdapter(S70SectorAddradapter);
		S70SectorAddrSpinner.setOnItemSelectedListener(new S70SectorAddrListener());

		S70BlockAddradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,S70StrBlock);
		S70BlockAddradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		S70BlockAddrSpinner = (Spinner)S70view.findViewById(R.id.S70BlockAddrSpinner1);
		S70BlockAddrSpinner.setAdapter(S70BlockAddradapter);
		S70BlockAddrSpinner.setOnItemSelectedListener(new S70BlockAddrListener());

		S70CardIDData = (EditText)S70view.findViewById(R.id.S70CardIDDataText);
		S70PassWordData = (EditText)S70view.findViewById(R.id.S70PassWordDataText);
		S70IncreaseData = (EditText)S70view.findViewById(R.id.S70IncreaseDataText);
		S70DecreaseData = (EditText)S70view.findViewById(R.id.S70DecreaseDataText);
		S70InitData = (EditText)S70view.findViewById(R.id.S70InitDataText);
		S70WriteData = (EditText)S70view.findViewById(R.id.S70WriteDataText);

		S70DetectCardButton = (Button)S70view.findViewById(R.id.S70DetectCard);
		S70GetCardIDButton = (Button)S70view.findViewById(R.id.S70GetCardID);
		S70VerifyPassWordButton = (Button)S70view.findViewById(R.id.S70VerifyPassWord);
		S70HaltButton = (Button)S70view.findViewById(R.id.S70Halt);
		S70ReadBlockDataButton = (Button)S70view.findViewById(R.id.S70ReadBlockData);
		S70WriteBlockDataButton = (Button)S70view.findViewById(R.id.S70WriteBlockData);
		S70InCreaseButton = (Button)S70view.findViewById(R.id.S70Increase);
		S70DeCreaseButton = (Button)S70view.findViewById(R.id.S70Derease);
		S70InitDataButton = (Button)S70view.findViewById(R.id.S70InitData);

		NonContactICS70CardIDText = (TextView)S70view.findViewById(R.id.S70CardIDtextView);
		NonContactICS70PassWordText = (TextView)S70view.findViewById(R.id.S70PassWordtextView);
		NonContactICS70PassWordTypeText = (TextView)S70view.findViewById(R.id.S70PassWordTypetextView);
		NonContactICS70PassWordSectorText = (TextView)S70view.findViewById(R.id.S70PassWordTypeSectortextView);
		NonContactICS70SectorAddrText = (TextView)S70view.findViewById(R.id.S70SectorAddrtextView);
		NonContactICS70BlockAddrText = (TextView)S70view.findViewById(R.id.S70BlockAddrtextView);
		NonContactICS70IncreaseText = (TextView)S70view.findViewById(R.id.S70IncreaseDatatextView);
		NonContactICS70DecreaseText = (TextView)S70view.findViewById(R.id.S70DecreaseDatatextView);
		NonContactICS70InitText = (TextView)S70view.findViewById(R.id.S70InitDatatextView);
		NonContactICS70WriteText = (TextView)S70view.findViewById(R.id.S70WriteDatatextView);
	}

	public void InitULClass(View ULview)
	{
		ULDetectCardButton = (Button)ULview.findViewById(R.id.ULDetectCard);
		ULHaltButton = (Button)ULview.findViewById(R.id.ULHalt);
		ULGetCardIDButton = (Button)ULview.findViewById(R.id.ULGetCardID);
		ULReadBlockDataButton = (Button)ULview.findViewById(R.id.ULReadBlockData);
		ULWriteBlockDataButton = (Button)ULview.findViewById(R.id.ULWriteBlockData);

		ULCardIDDataText = (EditText)ULview.findViewById(R.id.ULCardIDDataText1);
		ULWriteDataText = (EditText)ULview.findViewById(R.id.ULWriteDataText1);

		ULCardIDtextView = (TextView)ULview.findViewById(R.id.ULCardIDtextView1);
		ULSectorAddrtextView = (TextView)ULview.findViewById(R.id.ULSectorAddrtextView1);
		ULWriteDataTextView = (TextView)ULview.findViewById(R.id.ULWriteDataTextView1);

		ULSectorAddradapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,ULStrSector);
		ULSectorAddradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ULSectorAddrSpinner = (Spinner)ULview.findViewById(R.id.ULSectorAddrSpinner1);
		ULSectorAddrSpinner.setAdapter(ULSectorAddradapter);
		ULSectorAddrSpinner.setOnItemSelectedListener(new ULSectorAddrListener());
	}

	public void InitTypeAClass(View TypeAview)
	{
		NonContactICTypeAPowerOn = (Button)TypeAview.findViewById(R.id.TypeAActive);
		NonContactICTypeAAPDU = (Button)TypeAview.findViewById(R.id.TypeAAPDU);
		NonContactICTypeAGetICCard = (Button)TypeAview.findViewById(R.id.TypeAICCardNum);

		SetTypeASAPDU = (EditText)TypeAview.findViewById(R.id.TypeASAPDU);
		SetTypeARAPDU = (EditText)TypeAview.findViewById(R.id.TypeARAPDU);
		SetTypeASAPDU.setText("0084000008");
	}

	private class ComPortListener implements OnItemSelectedListener
	{
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			Port = arg2;
			if(Port == 8)
			{
				//System.out.println("8888888888888");
				PortName.setFocusable(true);
				PortName.setFocusableInTouchMode(true);
				PortName.requestFocus();
				MainActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
			}
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	}

	private class S50PassWordTypeListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S50PassWordType = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class S50PassWordSectorListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S50PassWordSector = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class S50SectorAddrListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S50SectorAddr = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class S50BlockAddrListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S50BlockAddr = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class S70PassWordTypeListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S70PassWordType = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class S70PassWordSectorListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S70PassWordSector = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class S70SectorAddrListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S70SectorAddr = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class S70BlockAddrListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			S70BlockAddr = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class ULSectorAddrListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
								   long arg3) {
			// TODO Auto-generated method stub
			ULSectorAddr = (byte) arg2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}

	}

	public void OnClickConnect(View V)
	{
		int nRet;
		byte[] Version=new byte[50];
		String strVersion = "";
		//String StrPort = "";
		String StrPortText = "";
		//String StrPort = "/dev/s3c2410_serial3";
		String StrPort = "/dev/ttyS" + Port;
		//String StrPort = "/dev/ttyHSL0";
		//int Baudate = 9600;
		StrPortText = PortName.getText().toString();
		if(!StrPortText.equals(""))
		{
			StrPort = "/dev/" + StrPortText;
		}
		else
		{
			StrPort = "/dev/ttyS0";
			if(Port >= 0 && Port <= 5)
				//StrPort = "/dev/ttyMT" + Port;
				StrPort = "/dev/ttyS" + Port;
			else
				StrPort = "/dev/ttyUSB" + (Port - 6);
		}
		StrPort = "/dev/s3c2410_serial3";
		nRet = M100_Serial.M100_CommOpenWithBaud(StrPort, 9600);
		if(nRet == OK)
		{
			nRet = M100_Serial.M100_Reset((byte)0x30, Version);
			if(nRet == OK)
			{
				ConnectButton.setEnabled(false);
				DisConnectButton.setEnabled(true);
				strVersion = ByteToString(Version);
				setTitle(strVersion);
				strVersion = "ttce_m100_v6.59";
				if(strVersion.contains("m100"))
				{
					MachineType = 0;
					ButtonEnable(true);
				}
				else
				{
					MachineType = 1;
					ButtonEnable(false);
				}

				if(m_English)
					ShowMessage("复位命令执行成功");
				else
					ShowMessage("Reset Command Excute Success");
			}
			else
			{
				M100_Serial.M100_CommClose();
				if(m_English)
					ShowMessage("复位命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
				else
					ShowMessage("Reset Command Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
			}
		}
		else
		{
			if(m_English)
				ShowMessage("打开串口命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				ShowMessage("Open Serial Command Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	public void OnClickDisConnect(View V)
	{
		int nRet;

		nRet = M100_Serial.M100_CommClose();
		if(nRet == OK)
		{
			ConnectButton.setEnabled(true);
			DisConnectButton.setEnabled(false);
			setTitle("M100_Serial_Demo");
			if(m_English)
			{
				ShowMessage("串口关闭命令执行成功");
			}
			else
			{
				ShowMessage("Close Serial Command Excute Success");
			}
		}
		else
		{
			if(m_English)
				ShowMessage("关闭串口命令执行失败，错误信息：" + M100_Serial.ErrorCode(nRet, 0));
			else
				ShowMessage("Closed Serial Command Excute Failed, Error Information: " + M100_Serial.ErrorCode(nRet, 1));
		}
	}

	/*************************Control*****************************/
	public void OnClickResetVersion(View V)
	{
		Control.OnClickResetVersion(this);
	}

	public void OnClickResetEject(View V)
	{
		Control.OnClickResetEject(this);
	}

	public void OnClickResetReenter(View V)
	{
		Control.OnClickResetReenter(this);
	}

	public void OnClickResetRecycle(View V)
	{
		Control.OnClickResetRecycle(this);
	}

	public void OnClickEnterCardUntime(View V)
	{
		Control.OnClickEnterCardUntime(this);
	}

	public void OnClickEnterMagcardUntime(View V)
	{
		Control.OnClickEnterMagcardUntime(this);
	}

	public void OnClickEnterCard(View V)
	{
		Control.OnClickEnterCard(this);
	}

	public void OnClickEnterMagCard(View V)
	{
		Control.OnClickEnterMagCard(this);
	}

	public void OnClickMoveRFCardPosition(View V)
	{
		Control.OnClickMoveRFCardPosition(this);
	}

	public void OnClickMoveICCardPosition(View V)
	{
		Control.OnClickMoveICCardPosition(this);
	}

	public void OnClickEject(View V)
	{
		Control.OnClickEject(this);
	}

	public void OnClickRecycle(View V)
	{
		Control.OnClickRecycle(this);
	}

	public void OnClickCheckCardPosition(View V)
	{
		Control.OnClickCheckCardPosition(this);
	}

	public void OnClickCheckSensor(View V)
	{
		Control.OnClickCheckSensor(this);
	}

	public void OnClickCheckVoltage(View V)
	{
		Control.OnClickCheckVoltage(this);
	}

	public void OnClickGetID(View V)
	{
		Control.OnClickGetID(this);
	}
	/*********************************************************/

	/************************15693****************************/
	public void OnClickGetUid(View V)
	{
		D15693.GetUid(this);
	}

	public void OnClickReadBlock(View V)
	{
		D15693.ReadBlock(this);
	}

	public void OnClickWriteBlock(View V)
	{
		D15693.WriteBlock(this);
	}

	public void OnClickLockBlock(View V)
	{
		D15693.LockBlock(this);
	}

	public void OnClickWriteAFI(View V)
	{
		D15693.WriteAFI(this);
	}

	public void OnClickLockAFI(View V)
	{
		D15693.LockAFI(this);
	}

	public void OnClickReadSafeBit(View V)
	{
		D15693.ReadSafeBit(this);
	}

	public void OnClickChooseCard(View V)
	{
		D15693.ChooseCard(this);
	}

	public void OnClickLockDSFID(View V)
	{
		D15693.LockDSFID(this);
	}

	public void OnClickSystemInfo(View V)
	{

	}

	public void OnClickWriteDSFID(View V)
	{
		D15693.WriteDSFID(this);
	}

	/*********************************************************/


	/**********************Read MagCard***********************/
	public void OnClickReadEnterCard(View V)
	{
		ReadMagCard.OnClickReadEnterCard(this);
	}

	public void OnClickReadEject(View V)
	{
		ReadMagCard.OnClickReadEject(this);
	}

	public void OnClickReadMagcard(View V)
	{
		//ReadMagCard.mmmm(this);
		ReadMagCard.OnClickReadMagcard(this);
	}

	public void OnClickClearMagcardData(View V)
	{
		ReadMagCard.OnClickClearMagcardData(this);
	}
	/*********************************************************/


	/********************Contact IC*************************/
	public void OnClickCPUActive(View V)
	{
		ICCardContact.OnClickCPUActive(this);
	}

	public void OnClickCPUT0APDU(View V)
	{
		ICCardContact.OnClickCPUT0APDU(this);
	}

	public void OnClickCPUT1APDU(View V)
	{
		ICCardContact.OnClickCPUT1APDU(this);
	}

	public void OnClickReadICCardNum(View V)
	{
		ICCardContact.OnClickReadICCardNum(this);
	}

	public void OnClickReadSocialSecurity(View V)
	{
		ICCardContact.OnClickReadSocialSecurity(this);
	}

	public void OnClickCPUInAvtive(View V)
	{
		ICCardContact.OnClickCPUInAvtive(this);
	}

	public void OnClickGet55Data(View V)
	{
		ICCardContact.OnClickGet55Data(this);
	}

	/*********************************************************/

	/********************Non-Contact IC************************/
	public void NonContactEnterCardUntimeOnClick(View V)
	{
		ICCardNonContact.NonContactEnterCardUntimeOnClick(this);
	}

	public void NonContactMoveToRFPositionOnClick(View V)
	{
		ICCardNonContact.NonContactMoveToRFPositionOnClick(this);
	}

	public void NonContactEjectOnClick(View V)
	{
		ICCardNonContact.NonContactEjectOnClick(this);
	}

	/********************S50************************/
	public void S50DetectCardOnClick(View V)
	{
		S50.S50DetectCardOnClick(this);
	}

	public void S50GetCardIDOnClick(View V)
	{
		S50.S50GetCardIDOnClick(this);
	}

	public void S50VerifyPassWordOnClick(View V)
	{
		S50.S50VerifyPassWordOnClick(this);
	}

	public void S50HaltOnClick(View V)
	{
		S50.S50HaltOnClick(this);
	}

	public void S50InitDataOnClick(View V)
	{
		S50.S50InitDataOnClick(this);
	}

	public void S50DereaseOnClick(View V)
	{
		S50.S50DereaseOnClick(this);
	}

	public void S50IncreaseOnClick(View V)
	{
		S50.S50IncreaseOnClick(this);
	}

	public void S50WriteBlockDataOnClick(View V)
	{
		S50.S50WriteBlockDataOnClick(this);
	}

	public void S50ReadBlockDataOnClick(View V)
	{
		S50.S50ReadBlockDataOnClick(this);
	}


	/********************S70************************/
	public void S70DetectCardOnClick(View V)
	{
		S70.S70DetectCardOnClick(this);
	}

	public void S70GetCardIDOnClick(View V)
	{
		S70.S70GetCardIDOnClick(this);
	}

	public void S70VerifyPassWordOnClick(View V)
	{
		S70.S70VerifyPassWordOnClick(this);
	}

	public void S70HaltOnClick(View V)
	{
		S70.S70HaltOnClick(this);
	}

	public void S70InitDataOnClick(View V)
	{
		S70.S70InitDataOnClick(this);
	}

	public void S70DereaseOnClick(View V)
	{
		S70.S70DereaseOnClick(this);
	}

	public void S70IncreaseOnClick(View V)
	{
		S70.S70IncreaseOnClick(this);
	}

	public void S70WriteBlockDataOnClick(View V)
	{
		S70.S70WriteBlockDataOnClick(this);
	}

	public void S70ReadBlockDataOnClick(View V)
	{
		S70.S70ReadBlockDataOnClick(this);
	}

	/********************TypeA************************/
	public void OnClickTypeAActive(View V)
	{
		TypeA.OnClickTypeAActive(this);
	}

	public void OnClickTypeAAPDU(View V)
	{
		TypeA.OnClickTypeAAPDU(this);
	}

	public void OnClickTypeAICCardNum(View V)
	{
		TypeA.OnClickTypeAICCardNum(this);
	}

	/*********************************************************/
	public void ButtonEnable(boolean states)
	{
		/************Control**************/
		ResetEjectButton.setEnabled(states);
		ResetReenterButton.setEnabled(states);
		ResetRecycleButton.setEnabled(states);
		EnterCardUntimeButton.setEnabled(states);
		EnterMagcardUntimeButton.setEnabled(states);
		EnterCardButton.setEnabled(states);
		EnterMagCardButton.setEnabled(states);
		MoveRFCardPositionButton.setEnabled(states);
		MoveICCardPositionButton.setEnabled(states);
		EjectButton.setEnabled(states);
		RecycleButton.setEnabled(states);
		CheckCardPositionButton.setEnabled(states);
		CheckVoltageButton.setEnabled(states);
		CheckSensorButton.setEnabled(states);
		/************Read MagCard**************/
		ReadEnterCardButton.setEnabled(states);
		ReadEjectButton.setEnabled(states);
		/************NonContactIC**************/
		NonContactICEnterCardButton.setEnabled(states);
		NonContactICMoveRFPositionButton.setEnabled(states);
		NonContactICEjectButton.setEnabled(states);
	}

	public void ShowChinese()
	{
		m_English = true;

		/************************Demo********************************/
		ConnectButton.setText("连接");
		DisConnectButton.setText("断开");
		CommandInfo.setText("命令状态: ");
		/************************Control********************************/
		ResetVersionButton.setText("复位获取版本");
		ResetEjectButton.setText("复位-弹卡");
		ResetReenterButton.setText("复位-重入卡");
		ResetRecycleButton.setText("复位-吞卡");
		EnterCardUntimeButton.setText("立即 进卡");
		EnterMagcardUntimeButton.setText("立即进磁卡");
		EnterCardButton.setText("等待进卡");
		EnterMagCardButton.setText("等待进磁卡");
		MoveRFCardPositionButton.setText("磁卡位置");
		MoveICCardPositionButton.setText("IC卡位置");
		EjectButton.setText("前端弹出");
		RecycleButton.setText("回收");
		CheckCardPositionButton.setText("查询位置");
		CheckVoltageButton.setText("查询电压");
		CheckSensorButton.setText("查询状态");
		/************************ReadMagCard********************************/
		ReadEnterCardButton.setText("进卡");
		ReadEjectButton.setText("弹卡");
		ReadMagcardButton.setText("读磁卡");
		ClearMagcardDataButton.setText("清空缓冲区数据");

		Track1_Text.setText("一轨：");
		Track2_Text.setText("二轨：");
		Track3_Text.setText("三轨：");
		/************************Contact IC********************************/
		/************************CPU********************************/
		ContactICCPUPowerOn.setText("CPU卡激活");
		ContactICCPUPowerOff.setText("CPU卡下电");
		ContactICCPUT0APDU.setText("T = 0 APDU");
		ContactICCPUT1APDU.setText("T = 1 APDU");
		ContactICCPUGetICCard.setText("获取银行卡号");
		ContactICCPUGetSocInfor.setText("获取社保卡信息");
		/************************SIM********************************/
		/************************SLE4442********************************/
		/************************SLE4428********************************/
		/************************AT102********************************/
		/************************AT1604********************************/
		/************************AT1608********************************/
		/************************AT24XX********************************/
		/************************AT45041********************************/
		/************************NonContact IC********************************/
		NonContactICEnterCardButton.setText("进卡");
		NonContactICMoveRFPositionButton.setText("射频卡位置");
		NonContactICEjectButton.setText("弹卡");
		/************************S50********************************/
		S50DetectCardButton.setText("寻卡");
		S50GetCardIDButton.setText("获取序列号");
		S50VerifyPassWordButton.setText("检验密码");
		S50HaltButton.setText("停机");
		S50ReadBlockDataButton.setText("读数据");
		S50WriteBlockDataButton.setText("写数据");
		S50InCreaseButton.setText("增值");
		S50DeCreaseButton.setText("减值");
		S50InitDataButton.setText("初始化");

		NonContactICS50CardIDText.setText("序列号:");
		NonContactICS50PassWordText.setText("密码:");
		NonContactICS50PassWordTypeText.setText("密码类型:");
		NonContactICS50PassWordSectorText.setText("扇区:");
		NonContactICS50SectorAddrText.setText("扇区:");
		NonContactICS50BlockAddrText.setText("块:");
		NonContactICS50IncreaseText.setText("增值数:");
		NonContactICS50DecreaseText.setText("减值数:");
		NonContactICS50InitText.setText("初始化值:");
		NonContactICS50WriteText.setText("数据:");
		/************************S70********************************/
		S70DetectCardButton.setText("寻卡");
		S70GetCardIDButton.setText("获取序列号");
		S70VerifyPassWordButton.setText("检验密码");
		S70HaltButton.setText("停机");
		S70ReadBlockDataButton.setText("读数据");
		S70WriteBlockDataButton.setText("写数据");
		S70InCreaseButton.setText("增值");
		S70DeCreaseButton.setText("减值");
		S70InitDataButton.setText("初始化");

		NonContactICS70CardIDText.setText("序列号:");
		NonContactICS70PassWordText.setText("密码:");
		NonContactICS70PassWordTypeText.setText("密码类型:");
		NonContactICS70PassWordSectorText.setText("扇区:");
		NonContactICS70SectorAddrText.setText("扇区:");
		NonContactICS70BlockAddrText.setText("块:");
		NonContactICS70IncreaseText.setText("增值数:");
		NonContactICS70DecreaseText.setText("减值数:");
		NonContactICS70InitText.setText("初始化值:");
		NonContactICS70WriteText.setText("数据:");
		/************************UL********************************/
		/************************TypeA********************************/
		NonContactICTypeAPowerOn.setText("卡片激活");
		NonContactICTypeAAPDU.setText("APDU命令");
		NonContactICTypeAGetICCard.setText("获取银行卡号");
		/************************Move Card********************************/
	}

	public void ShowEnglish()
	{
		m_English = false;

		/************************Demo********************************/
		System.out.println("Demo");
		ConnectButton.setText("Connect");
		DisConnectButton.setText("DisConnect");
		CommandInfo.setText("Command State:");
		/************************Control********************************/
		System.out.println("Control");
		ResetVersionButton.setText("Get Vesion");
		ResetEjectButton.setText("Reset-Eject");
		ResetReenterButton.setText("Reset-ReEnter");
		ResetRecycleButton.setText("Reset-Recycle");
		EnterCardUntimeButton.setText("EnterCardUntime");
		EnterMagcardUntimeButton.setText("EnterMagCardUntime");
		EnterCardButton.setText("EnterCard");
		EnterMagCardButton.setText("EnterMagCard");
		MoveRFCardPositionButton.setText("RFPosition");
		MoveICCardPositionButton.setText("ICPosition");
		EjectButton.setText("Eject");
		RecycleButton.setText("Recycle");
		CheckCardPositionButton.setText("CardPosition");
		CheckVoltageButton.setText("Voltage");
		CheckSensorButton.setText("SensorStates");
		/************************ReadMagCard********************************/
		System.out.println("ReadMagCard");
		ReadEnterCardButton.setText("EnterCard");
		ReadEjectButton.setText("Eject");
		ReadMagcardButton.setText("ReadMagCard");
		ClearMagcardDataButton.setText("ClearData");
		Track1_Text.setText("Track1:");
		Track2_Text.setText("Track2:");
		Track3_Text.setText("Track3:");

		/************************Contact IC********************************/
		/************************CPU********************************/
		System.out.println("CPU");
		ContactICCPUPowerOn.setText("PowerOn");
		ContactICCPUPowerOff.setText("PowerOff");
		ContactICCPUT0APDU.setText("T = 0 APDU");
		ContactICCPUT1APDU.setText("T = 1 APDU");
		ContactICCPUGetICCard.setText("ICCardID");
		ContactICCPUGetSocInfor.setText("SocialInformation");
		/************************SIM********************************/
		/************************SLE4442********************************/
		/************************SLE4428********************************/
		/************************AT102********************************/
		/************************AT1604********************************/
		/************************AT1608********************************/
		/************************AT24XX********************************/
		/************************AT45041********************************/
		/************************NonContact IC********************************/
		System.out.println("NonContact");
		NonContactICEnterCardButton.setText("EnterCard");
		NonContactICMoveRFPositionButton.setText("RFPosition");
		NonContactICEjectButton.setText("Eject");
		/************************S50********************************/
		System.out.println("S50");
		S50DetectCardButton.setText("DetectCard");
		S50GetCardIDButton.setText("GetCardID");
		S50VerifyPassWordButton.setText("VerifyPassword");
		S50HaltButton.setText("Halt");
		S50ReadBlockDataButton.setText("Read");
		S50WriteBlockDataButton.setText("Write");
		S50InCreaseButton.setText("Increase");
		S50DeCreaseButton.setText("Decrease");
		S50InitDataButton.setText("Init");

		NonContactICS50CardIDText.setText("CardID:");
		NonContactICS50PassWordText.setText("Password:");
		NonContactICS50PassWordTypeText.setText("PasswordType:");
		NonContactICS50PassWordSectorText.setText("Sector:");
		NonContactICS50SectorAddrText.setText("Sector:");
		NonContactICS50BlockAddrText.setText("Block:");
		NonContactICS50IncreaseText.setText("IncreaseData:");
		NonContactICS50DecreaseText.setText("DecreaseData:");
		NonContactICS50InitText.setText("InitData:");
		NonContactICS50WriteText.setText("Data:");
		/************************S70********************************/
		System.out.println("S70");
		S70DetectCardButton.setText("DetectCard");
		S70GetCardIDButton.setText("GetCardID");
		S70VerifyPassWordButton.setText("VerifyPassword");
		S70HaltButton.setText("Halt");
		S70ReadBlockDataButton.setText("Read");
		S70WriteBlockDataButton.setText("Write");
		S70InCreaseButton.setText("Increase");
		S70DeCreaseButton.setText("Decrease");
		S70InitDataButton.setText("Init");

		NonContactICS70CardIDText.setText("CardID:");
		NonContactICS70PassWordText.setText("Password:");
		NonContactICS70PassWordTypeText.setText("PasswordType:");
		NonContactICS70PassWordSectorText.setText("Sector:");
		NonContactICS70SectorAddrText.setText("Sector:");
		NonContactICS70BlockAddrText.setText("Block:");
		NonContactICS70IncreaseText.setText("IncreaseData:");
		NonContactICS70DecreaseText.setText("DecreaseData:");
		NonContactICS70InitText.setText("InitData:");
		NonContactICS70WriteText.setText("Data:");
		/************************UL********************************/
		/************************TypeA********************************/
		System.out.println("TypeA");
		NonContactICTypeAPowerOn.setText("PowerOn");
		NonContactICTypeAAPDU.setText("APDU Command");
		NonContactICTypeAGetICCard.setText("ICCardID");
		/************************Move Card********************************/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static void ShowMessage(String str){
		CommandStatesInfo.setText(str);
	}

	public static void CopyByte(byte[] dest,byte[] ByteArray,int offset,int length)
	{
		for(int i=0;i<length;i++)
			dest[i]=ByteArray[i+offset];
	}

	public static String ByteToString(byte[] by)//把数组转换为字符串
	{
		String str="";
		char ch='\0';
		//for(int i=0;by[i]!='\0';i++)
		for(int i=0;by[i] != '\0';i++)
		{
			ch=(char)by[i];
			str+=ch;
		}
		return str;
	}

	public static String ByteArrayToString(byte[] by, int length)//把数据以十六进制显示
	{
		String str="";
		for(int i=0;i<length;i++)
		{
			String hex = Integer.toHexString(by[i] & 0xFF);
			if(hex.length() == 1)
				hex="0" + hex;
			str += hex.toUpperCase();
		}
		return str;
	}

	public static String HexToStr(byte by)
	{
		String hex = Integer.toHexString(by & 0xFF);
		if(hex.length() == 1)
			hex="0" + hex;
		return hex.toUpperCase();
	}

	public static byte String2Hex(byte src0,byte src1)
	{
		byte _bo=Byte.decode("0x" + new String(new byte[] {src0})).byteValue();
		_bo = (byte)(_bo << 4);

		byte _b1=Byte.decode("0x" + new String(new byte[] {src1})).byteValue();

		byte by = (byte)(_bo ^ _b1);

		return by;
	}


	public static byte[] StringToHex(String str)
	{
		byte[] by=new byte[255];
		byte[] temp=str.getBytes();
		int length = str.length();
		for(int i = 0; i < length / 2; i++)
		{
			by[i] = String2Hex(temp[2 * i], temp[2 * i + 1]);
		}
		return by;
	}

	public static void showMessage(Context context, String str){
		AlertDialog.Builder builder  = new Builder(context);
		builder.setTitle("Message" );
		builder.setMessage(str);
		builder.setPositiveButton("OK" ,  null );
		builder.show();
	}

}
