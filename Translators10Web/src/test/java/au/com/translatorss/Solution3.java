package au.com.translatorss;

public class Solution3 {

    public static void main(String[] args){
        Solution3 sol = new Solution3();

        //int[] spam = new int[] { 1, 1, 2,3, 3};
        int[] spam = new int[] { 1, 2, 3,4,5,6,7};

        boolean result = sol.solution(spam,7);
        System.out.println(result);
    }

    public boolean solution(int[] A, int K) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] + 1 < A[i + 1])
                return false;
        }
        if (A[0] == 1 || A[n - 1] != K)
            return false;
        else
            return true;
    }

   /* public static boolean solution(int[] A, int K)
    {
        int n = A.Length;
        for (int i = 0; i < A.Length - 1; i++)
        {
            if (A[i] + 1 < A[i + 1])
                return false;
        }
        if (A[0] == 1 || A[n - 1] != K)//Change here && to ||
            return false;
        else
            return true;

    }*/


















//
//    public boolean solution(int[] A, int K) {
//        boolean found=true;
//        for(int i =0;i<=K;i++){
//            found= findElement(A,i);
//        }
//
//      return found;
//    }
////
////    private boolean findElement(int[] A, int K){
////        boolean found=false;
////        for(int i =0; i<A.length;i++){
////            int value = A[i];
////            if(value== K){
////              found =true;
////            }
////        }
////        return found;
////    }*/

}
