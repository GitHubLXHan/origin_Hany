
public class TRY {
	public static void main(String[] args){

		try{
			String a="������";
		String b="������";
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
