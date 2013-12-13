package im.dadoo.fileserver.util;

import java.util.StringTokenizer;

public class Interval<T> {

	public T v1;
	public T v2;
	
	@SuppressWarnings("unchecked")
	public Interval(String strv) {
		StringTokenizer st = new StringTokenizer(strv, ",");
		this.v1 = (T)st.nextElement();
		this.v2 = (T)st.nextElement();
	}
	
	public Interval(T v1, T v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Interval() {
		this.v1 = null;
		this.v2 = null;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Interval:{");
		buf.append("v1:" + v1 + " ");
		buf.append("v2:" + v2);
		buf.append("}");
		return buf.toString();
	}
}
