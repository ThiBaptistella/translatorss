package au.com.translatorss;
import java.util.List.*;


public class Solution2 {


    public static void main(String[] args){
        Solution2 sol = new Solution2();

        int[] spam = new int[] { 1, 4, -1,3, 2};

        int result = sol.solution(spam);
        System.out.println(result);
    }

    public int solution(int[] A) {

        int value = A[0];
        int cont =0;
        int current=0;
        while( value != -1){
            value = searchnextValue( current , A );
            current = value;
            cont++;
        }
        return cont;
    }

    private int searchnextValue( int i ,int[] A ){
        int value = A[i];
        return   value;
    }


}
