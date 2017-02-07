package com.hrocloud.tiangong.verifycode.model;

public class CaptchaTemplate extends Entity {
	private static final long serialVersionUID = 8537332320662425982L;
	private Long id;
	private Integer length;
	private Integer valueType;
	private Integer height;
	private Integer width;
	private String font;
	private Integer fontHeight;
	private String bgColor;
	private String borderColor;
	private Integer interferingStyle;
	private Integer interferingMax;
	private Integer interferingMin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getValueType() {
		return valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public Integer getFontHeight() {
		return fontHeight;
	}

	public void setFontHeight(Integer fontHeight) {
		this.fontHeight = fontHeight;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Integer getInterferingStyle() {
		return interferingStyle;
	}

	public void setInterferingStyle(Integer interferingStyle) {
		this.interferingStyle = interferingStyle;
	}

	public Integer getInterferingMax() {
		return interferingMax;
	}

	public void setInterferingMax(Integer interferingMax) {
		this.interferingMax = interferingMax;
	}

	public Integer getInterferingMin() {
		return interferingMin;
	}

	public void setInterferingMin(Integer interferingMin) {
		this.interferingMin = interferingMin;
	}

	@Override
	public String toString() {
		return "CaptchaTemplate [id=" + id + ", length=" + length + ", valueType=" + valueType + ", height=" + height
				+ ", width=" + width + ", font=" + font + ", fontHeight=" + fontHeight + ", bgColor=" + bgColor
				+ ", borderColor=" + borderColor + ", interferingStyle=" + interferingStyle + ", interferingMax="
				+ interferingMax + ", interferingMin=" + interferingMin + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaptchaTemplate that = (CaptchaTemplate) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (length != null ? !length.equals(that.length) : that.length != null) return false;
		if (valueType != null ? !valueType.equals(that.valueType) : that.valueType != null) return false;
		if (height != null ? !height.equals(that.height) : that.height != null) return false;
		if (width != null ? !width.equals(that.width) : that.width != null) return false;
		if (font != null ? !font.equals(that.font) : that.font != null) return false;
		if (fontHeight != null ? !fontHeight.equals(that.fontHeight) : that.fontHeight != null) return false;
		if (bgColor != null ? !bgColor.equals(that.bgColor) : that.bgColor != null) return false;
		if (borderColor != null ? !borderColor.equals(that.borderColor) : that.borderColor != null) return false;
		if (interferingStyle != null ? !interferingStyle.equals(that.interferingStyle) : that.interferingStyle != null)
			return false;
		if (interferingMax != null ? !interferingMax.equals(that.interferingMax) : that.interferingMax != null)
			return false;
		return interferingMin != null ? interferingMin.equals(that.interferingMin) : that.interferingMin == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (length != null ? length.hashCode() : 0);
		result = 31 * result + (valueType != null ? valueType.hashCode() : 0);
		result = 31 * result + (height != null ? height.hashCode() : 0);
		result = 31 * result + (width != null ? width.hashCode() : 0);
		result = 31 * result + (font != null ? font.hashCode() : 0);
		result = 31 * result + (fontHeight != null ? fontHeight.hashCode() : 0);
		result = 31 * result + (bgColor != null ? bgColor.hashCode() : 0);
		result = 31 * result + (borderColor != null ? borderColor.hashCode() : 0);
		result = 31 * result + (interferingStyle != null ? interferingStyle.hashCode() : 0);
		result = 31 * result + (interferingMax != null ? interferingMax.hashCode() : 0);
		result = 31 * result + (interferingMin != null ? interferingMin.hashCode() : 0);
		return result;
	}
}
