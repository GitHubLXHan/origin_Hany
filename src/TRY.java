
public class TRY {
	public static void main(String[] args){

		try{
			String a="–Ìﬁ»¡·";
		String b="–Ìﬁ»¡·";
		String[] c = null;
		if(a.equals(b)){
			c=new String[]{"ss","dd","hh"};
		}

		for(int i=0;i<c.length;i++){
			System.out.println(c[i]);
		}
		}catch (Exception e) {
			System.out.println("ss");
		}
	}
}
