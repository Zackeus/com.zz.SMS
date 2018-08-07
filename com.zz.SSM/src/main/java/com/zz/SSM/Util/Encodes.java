package com.zz.SSM.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 
 * @Title:Encodes
 * @Description:TODO(封装各种格式的编码解码工具类.)
 * 1.Commons-Codec的 hex/base64 编码
 * 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * @Company: 
 * @author zhou.zhang
 * @date 2018年8月7日 上午10:46:42
 */
@SuppressWarnings("deprecation")
public class Encodes {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * 
	 * @Title：encodeHex
	 * @Description: TODO(Hex编码.)
	 * @see：
	 * @param input
	 * @return
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}

	/**
	 * 
	 * @Title：decodeHex
	 * @Description: TODO(Hex解码.)
	 * @see：
	 * @param input
	 * @return
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * 
	 * @Title：encodeBase64
	 * @Description: TODO(Base64编码.)
	 * @see：
	 * @param input
	 * @return
	 */
	public static String encodeBase64(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}
	
	/**
	 * 
	 * @Title：encodeBase64
	 * @Description: TODO(Base64编码.)
	 * @see：
	 * @param input
	 * @return
	 */
	public static String encodeBase64(String input) {
		try {
			return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 
	 * @Title：decodeBase64
	 * @Description: TODO(Base64解码.)
	 * @see：
	 * @param input
	 * @return
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input.getBytes());
	}
	
	/**
	 * 
	 * @Title：decodeBase64String
	 * @Description: TODO(Base64解码.)
	 * @see：
	 * @param input
	 * @return
	 */
	public static String decodeBase64String(String input) {
		try {
			return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 
	 * @Title：encodeBase62
	 * @Description: TODO(Base62编码。)
	 * @see：
	 * @param input
	 * @return
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}

	/**
	 * 
	 * @Title：escapeHtml
	 * @Description: TODO(Html 转码.)
	 * @see：
	 * @param html
	 * @return
	 */
	public static String escapeHtml(String html) {
		return escapeHtml(html);
	}

	/**
	 * 
	 * @Title：unescapeHtml
	 * @Description: TODO(Html 解码.)
	 * @see：
	 * @param htmlEscaped
	 * @return
	 */
	public static String unescapeHtml(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * 
	 * @Title：escapeXml
	 * @Description: TODO(Xml 转码.)
	 * @see：
	 * @param xml
	 * @return
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml10(xml);
	}

	/**
	 * 
	 * @Title：unescapeXml
	 * @Description: TODO(Xml 解码.)
	 * @see：
	 * @param xmlEscaped
	 * @return
	 */
	public static String unescapeXml(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}
	
	/**
	 * 
	 * @Title：urlEncode
	 * @Description: TODO(URL 编码, Encode默认为UTF-8.)
	 * @see：
	 * @param part
	 * @return
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * 
	 * @Title：urlDecode
	 * @Description: TODO(URL 解码, Encode默认为UTF-8)
	 * @see：
	 * @param part
	 * @return
	 */
	public static String urlDecode(String part) {

		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}
}
