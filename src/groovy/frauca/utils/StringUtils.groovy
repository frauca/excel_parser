package frauca.utils

class StringUtils  {

	static String replaceLast(String string, String substring, String replacement) {
		int index = string.lastIndexOf(substring);
		if (index == -1)
			return string;
		return string.substring(0, index) + replacement
		+ string.substring(index+substring.length());
	}
	
	static String removeAnyReplacements(String orig,String[] replacements){
		String res=orig
		replacements.each {
			res=(res =~ it).replaceAll("")
		}
		return res
	}
}
