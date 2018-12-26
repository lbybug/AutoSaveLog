package com.example.m100_demoserial;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

//[------------------------------ MAIN CLASS ----------------------------------]
//--------------------------------- REVISIONS ----------------------------------
//Date       Name                 Tracking #         Description
//--------   -------------------  -------------      --------------------------
//13SEP2011  James Shen                 	         Initial Creation
////////////////////////////////////////////////////////////////////////////////
/**
 * Convert help class.
 * <hr>
 * <b>&copy; Copyright 2011 Guidebee, Inc. All Rights Reserved.</b>
 *
 * @version 1.00, 13/09/11
 * @author Guidebee Pty Ltd.
 */
public class Converter {

	//Hex help
	private static final byte[] HEX_CHAR_TABLE = { (byte) '0', (byte) '1',
			(byte) '2', (byte) '3', (byte) '4', (byte) '5', (byte) '6',
			(byte) '7', (byte) '8', (byte) '9', (byte) 'A', (byte) 'B',
			(byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F' };

////////////////////////////////////////////////////////////////////////////
//--------------------------------- REVISIONS ------------------------------
//Date       Name                 Tracking #         Description
//---------  -------------------  -------------      ----------------------
//13SEP2011  James Shen                 	          Initial Creation
////////////////////////////////////////////////////////////////////////////
	/**
	 * convert a byte arrary to hex string
	 * @param raw byte arrary
	 * @param len lenght of the arrary.
	 * @return hex string.
	 */
	public static String getHexString(byte[] raw, int len) {
		byte[] hex = new byte[2 * len];
		int index = 0;
		int pos = 0;

		for (byte b : raw) {
			if (pos >= len)
				break;

			pos++;
			int v = b & 0xFF;
			hex[index++] = HEX_CHAR_TABLE[v >>> 4];
			hex[index++] = HEX_CHAR_TABLE[v & 0xF];
		}

		return new String(hex);
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"–> 0xEF
	 *
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] {src0})).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" –> byte[]{0x2B, 0×44, 0xEF,
	 * 0xD9}
	 *
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		if (null == src || 0 == src.length()) {
			return null;
		}
		byte[] ret = new byte[src.length() / 2];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < (tmp.length / 2); i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
	public static long unsigned4BytesToInt(byte[] buf, int pos) {

		int firstByte = 0;

		int secondByte = 0;

		int thirdByte = 0;

		int fourthByte = 0;

		int index = pos;

		firstByte = (0x000000FF & (int) buf[index+3]);

		secondByte = (0x000000FF &((int) buf[index + 2]));

		thirdByte = (0x000000FF & ((int) buf[index + 1]));

		fourthByte = (0x000000FF & ((int) buf[index + 0]));

		index = index + 4;

		return ((long)(firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte)) & 0xFFFFFFFFL;

	}

	public static byte[] intToByteArray(int s) {

		byte[] targets = new byte[4];

		for (int i = 0; i < 4; i++) {

			int offset = (targets.length - 1 - i) * 8;

			targets[i] = (byte)((s >>> offset) & 0xff);

		}

		return targets;

	}

	public static byte[] intToBytes(int n){
		String s = String.valueOf(n);
		return s.getBytes();
	}

	public static int bytesToInt(byte[] b){
		String s = new String(b);
		return Integer.parseInt(s);
	}

	public static byte[] intToBytes2(int n){
		byte[] b = new byte[4];
		for(int i=0;i<4;i++){
			b[i] = (byte)(n>>(24-i*8));
		}
		return b;
	}

	public static int byteToInt2(byte[] b){
		return (((int)b[0])<<24) + (((int)b[1])<<16) + (((int)b[2])<<8) + b[3];
	}


	public static byte[] getBytes(char[] chars){
		Charset cs=Charset.forName("GBK");
		CharBuffer cb=CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb=cs.encode(cb);
		return bb.array();
	}

	public static char[] getChars(byte[] bytes){
		Charset cs=Charset.forName("GBK");
		ByteBuffer bb=ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb=cs.decode(bb);
		return cb.array();
	}
}

