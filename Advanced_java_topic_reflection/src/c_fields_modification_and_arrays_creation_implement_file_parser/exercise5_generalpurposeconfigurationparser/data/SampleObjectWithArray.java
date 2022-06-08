package c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.data;

import java.util.Arrays;

public class SampleObjectWithArray {
	
	private String titleColor;
	private String footerText;
	private short titleFontSize;
	private short[] footerFontSize;
	private String[] arrayNames;
	
	public String getTitleColor() {
		return titleColor;
	}
	public String getFooterText() {
		return footerText;
	}
	public short getTitleFontSize() {
		return titleFontSize;
	}
	public short[] getFooterFontSize() {
		return footerFontSize;
	}
	
	public String[] getArrayNames() {
		return arrayNames;
	}
	@Override
	public String toString() {
		return "SampleObjectWithArray [titleColor=" + titleColor + ", footerText=" + footerText + ", titleFontSize="
				+ titleFontSize + ", footerFontSize=" + Arrays.toString(footerFontSize) + ", arrayNames="
				+ Arrays.toString(arrayNames) + "]";
	}

	
	
	
	
	

}
