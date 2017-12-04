package au.com.translatorss;

public class Solution5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int [] {2, 4, 3, 5, 1};
		System.out.println(firstDuplicate(a));
	}

	private static int firstDuplicate(int[] a) {
		int[] b = new int [a.length] ;
		for (int i = 0; i < a.length; i++) {
	            b[i] = a[i];
	    }
		
		int aux=-1;
		int currentPosition=a.length;
		for(int i =0; i<a.length;i++) {
			int value = isDuplicated(a[i],i,b,currentPosition);
			if(value != -1) {
				aux=a[i];
				currentPosition=value;
			}
		}

		return aux;
	}

	private static int isDuplicated(int value, int position, int[] b, int currentPosition) {
		for(int i =position+1; i<b.length;i++) {
			if(b[i]==value && currentPosition>i) {
				return i;
			}
		}
		return -1;
	}

	
}
