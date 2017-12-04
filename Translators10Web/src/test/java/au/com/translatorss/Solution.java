package au.com.translatorss;
// you can also use imports, for example:
// import java.util.*;
//10000010001
// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {


    public static void main(String[] args){
        Solution sol = new Solution();
        int result = sol.solution(74);
        System.out.println(result);
    }


    public int solution(int N) {
        String binary = Integer.toBinaryString(N);
        int cont=0;
        int aux =0;
        for(int i =0 ; i < binary.length(); i++){
            char character = binary.charAt(i);
            if('1'==character){
                aux = getZero(i+1, binary);
                if(aux > cont){
                    cont =aux;
                    aux=0;
                }
            }
        }
        return cont;
    }

    int getZero(int position, String binary){
        if(position >= binary.length()){
            return 0;
        }
        int cont = 0;
        for(int i = position; i<binary.length() && '0'==binary.charAt(i) ;i++){
            cont++;
        }
        return cont;
    }



}
