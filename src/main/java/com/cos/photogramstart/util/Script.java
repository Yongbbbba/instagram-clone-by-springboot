package com.cos.photogramstart.util;

public class Script {

	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert(\"");
		sb.append(msg);
		sb.append("\");");
//		sb.append(String.format("alert(\"%s\");", msg));
		sb.append("history.back();");
		sb.append("</script>");
//		System.out.println(sb.toString());
		return sb.toString();
	}
}
