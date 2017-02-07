package com.hrocloud.tiangong.verifycode.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hrocloud.tiangong.verifycode.enums.CaptchaValueType;
import com.hrocloud.tiangong.verifycode.model.CaptchaTemplate;

public class CaptchaUtil {
	// [0-9]:48-57 [A-Z]:65-90 [A-Z]:97-122
	private static final char[] NUMBER = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };

	private static final char[] UPPERCASE_LOWERCASE = new char[] { 65, 66, 67, 68, 69, 70, 71, 72, 74, 75, 76, 77,
			78, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 106, 107, 
			109, 110, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };

	//remove 0,1,I,O,i,l,o
	private static final char[] NUMBER_UPPERCASE_LOWERCASE = new char[] { 50, 51, 52, 53, 54, 55, 56, 57, 65,
			66, 67, 68, 69, 70, 71, 72, 74, 75, 76, 77, 78, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98,
			99, 100, 101, 102, 103, 104, 106, 107, 109, 110, 112, 113, 114, 115, 116, 117, 118, 119, 120,
			121, 122 };

	private static Random random = new Random();

	public static String createCaptchaValue(int length, CaptchaValueType valueType) {
		char[] chars = getTpye(valueType);
		Integer[] offsets = new Integer[length];
		for (int i = 0; i < offsets.length; i++) {
			offsets[i] = random.nextInt(chars.length);
		}
		return generaterCode(offsets, chars);
	}

	/**
	 * 
	 * @param length
	 *            验证码文本位数
	 * @param type
	 *            文本类型
	 * @param total
	 *            总共需要生成的数量
	 * @return
	 */
	public static List<String> createCaptchaValues(int length, CaptchaValueType type, int total) {
		List<String> result = new ArrayList<String>();
		char[] chars = getTpye(type);
		int maxStep = getMaxStep(length, total, chars.length);
		Integer[] offsets = new Integer[length];
		for (int i = 0; i < offsets.length; i++) {
			offsets[i] = random.nextInt(chars.length);
		}
		for (int i = 0; i < total; i++) {
			updateOffsets(offsets, chars.length, maxStep);
			StringBuilder msb = new StringBuilder();
			for (int j = 0; j < length; j++) {
				msb.append(chars[offsets[j]]);
			}
			result.add(msb.toString());
		}
		return result;
	}

	/**
	 * 
	 * @param length
	 *            验证码文本位数
	 * @param type
	 *            文本类型
	 * @param number
	 *            此次需要生成的数量
	 * @param total
	 *            总共需要生成的数量
	 * @return
	 */
	public static List<String> createCaptchaValues(int length, CaptchaValueType type, int number, int total) {
		List<String> result = new ArrayList<String>();
		char[] chars = getTpye(type);
		int maxStep = getMaxStep(length, total, chars.length);
		Integer[] offsets = new Integer[length];
		for (int i = 0; i < offsets.length; i++) {
			offsets[i] = random.nextInt(chars.length);
		}
		for (int i = 0; i < number; i++) {
			updateOffsets(offsets, chars.length, maxStep);
			StringBuilder msb = new StringBuilder();
			for (int j = 0; j < length; j++) {
				msb.append(chars[offsets[j]]);
			}
			result.add(msb.toString());
		}
		return result;
	}

	/**
	 * 
	 * @param length
	 *            验证码文本位数
	 * @param type
	 *            文本类型
	 * @param number
	 *            本次需要生成的数量
	 * @param total
	 *            总共需要生成的数量
	 * @param beginCode
	 *            起始验证码文本
	 * @return
	 */
	public static List<String> createCaptchaValues(int length, CaptchaValueType type, int number, int total,
			String beginCode) {
		if (null == beginCode || beginCode.trim().length() == 0) {
			return createCaptchaValues(length, type, number, total);
		}
		List<String> result = new ArrayList<String>();
		char[] chars = getTpye(type);
		int maxStep = getMaxStep(length, total, chars.length);
		Integer[] offsets = transferToOffset(beginCode, chars);
		for (int i = 0; i < number; i++) {
			updateOffsets(offsets, chars.length, maxStep);
			StringBuilder msb = new StringBuilder();
			for (int j = 0; j < offsets.length; j++) {
				msb.append(chars[offsets[j]]);
			}
			result.add(msb.toString());
		}
		return result;
	}

	public static BufferedImage createCaptchaImage(CaptchaTemplate captchaTemplate, String captchaValue) {
		Integer width = captchaTemplate.getWidth();
		Integer height = captchaTemplate.getHeight();
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics gd = buffImg.getGraphics();

		// 填充背景色
		gd.setColor(createColor(captchaTemplate.getBgColor(), Color.WHITE));
		gd.fillRect(0, 0, width, height);

		// 设置字体
		Font fontObj = new Font(captchaTemplate.getFont(), Font.BOLD, captchaTemplate.getFontHeight());
		gd.setFont(fontObj);

		// 边框颜色
		gd.setColor(createColor(captchaTemplate.getBorderColor(), Color.BLACK));
		gd.drawRect(0, 0, width - 1, height - 1);

		// 干扰线
		int max = captchaTemplate.getInterferingMax();
		int min = captchaTemplate.getInterferingMin();
		int interferingCount = random.nextInt(max - min) + max;
		createInterferingLine(gd, random, interferingCount, width, height);

		int red = 0, green = 0, blue = 0;
		int codeX = width / captchaValue.length() - (captchaValue.length() * 2);
		int codeY = height / 2 + (captchaValue.length() * 2);
		// 绘制图像
		for (int i = 0; i < captchaValue.length(); i++) {
			String code = String.valueOf(captchaValue.charAt(i));
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			gd.setColor(new Color(red, green, blue));
			gd.drawString(code, (i + 1) * codeX, codeY);
		}

		return buffImg;
	}

	private static Color createColor(String color, Color defaultColor) {
		String[] colorStr = color.split(",");
		if (colorStr.length == 3) {
			return new Color(Integer.valueOf(colorStr[0]), Integer.valueOf(colorStr[1]), Integer.valueOf(colorStr[2]));
		}
		return defaultColor;
	}

	private static Graphics createInterferingLine(Graphics gd, Random random, int interferingCount, int width,
			int height) {
		for (int i = 0; i < interferingCount; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
		return gd;
	}

	private static void updateOffsets(Integer[] offsets, int length, int maxStep) {
		updateFromLastPosition(offsets, length, maxStep);
	}

	private static void updateFromLastPosition(Integer[] offsets, int length, int maxStep) {
		int offset = random.nextInt(maxStep) + 1;
		int divisor = (offset + offsets[offsets.length - 1]) / length;
		int remainder = (offset + offsets[offsets.length - 1]) % length;
		for (int i = 1, n = offsets.length; i <= n; i++) {
			offsets[offsets.length - i] = remainder;
			if (divisor == 0) {
				break;
			}
			if (i == n) {
				offsets[offsets.length - i] = (offsets[offsets.length - i] + divisor) % length;
				return;
			}
			remainder = (offsets[offsets.length - i - 1] + divisor) % length;
			divisor = (offsets[offsets.length - i - 1] + divisor) / length;
		}
	}

	private static String generaterCode(Integer[] offsets, char[] chars) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < offsets.length; j++) {
			sb.append(chars[offsets[j]]);
		}
		return sb.toString();
	}

	private static char[] getTpye(CaptchaValueType type) {
		char[] chars = null;
		switch (type) {
		case DIGIT:
			chars = NUMBER;
			break;
		case CHARACTER:
			chars = UPPERCASE_LOWERCASE;
			break;
		case CHARACTER_AND_DIGIT:
			chars = NUMBER_UPPERCASE_LOWERCASE;
			break;
		default:
			chars = NUMBER_UPPERCASE_LOWERCASE;
		}
		return chars;
	}

	private static Integer[] transferToOffset(String beginCode, char[] chars) {
		char[] code = beginCode.toCharArray();
		int length = code.length;
		Integer[] offset = new Integer[length];
		for (int i = 0; i < length; i++) {
			offset[i] = calcIndexOfType(code[i], chars);
		}
		return offset;
	}

	/**
	 * 
	 * @param length
	 *            验证码文本位数
	 * @param total
	 *            总共需要生成的验证码数量
	 * @param charSize
	 *            数组长度
	 * @return
	 */
	private static int getMaxStep(int length, int total, int charSize) {
		long maxValue = 1;
		for (int i = 0; i < length; i++) {
			maxValue = maxValue * charSize;
		}
		long availableStep = (long) (maxValue / total);
		int maxStep = availableStep >= (int) (Integer.MAX_VALUE / charSize) ? (int) (Integer.MAX_VALUE / charSize)
				: (int) availableStep;
		// maxStep==0 exception
		return maxStep == 0 ? 1 : maxStep;
	}

	private static Integer calcIndexOfType(char code, char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == code) {
				return i;
			}
		}
		// not find in char[] exception
		return -1;
	}
}
