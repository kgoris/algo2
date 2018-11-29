import java.util.*;

public class Main {



    /*
        Exercice 10
     */
    /*
        @ requires a != null && a.length > 0;
        @ requires n > 0 && n <= a.length;
        @ ensures \result == (\exists j ; j>=0 && j <a.length - n; n == (\num_of i; i>=j && i<j+n; a[i] == a[i+1]));
     */
    public static boolean enLigne(String[] a, int n, int x){
        //do something
        return false;
    }

    /*
        Exercice 12
     */
    /*
        @ requires a != null && a.length > 0;
        @ requires l>0 && l<=a.length;
        @ ensures \result == (\sum int i; i>=0 && i<= l; a[i]);
     */
    public int somme(int[] a, int l){
        int somme = 0;
        int i=0;
        /*
            @loop_invariant i>=0 && i<=l;
            @loop_invariant somme = (\sum int j; j>=0 && j<=i; array[j]);
            @decreases l-i
         */
        while (i<l){
            somme = somme + a[i];
            ++i;
        }
        return somme;
    }

    /*
        Exercice 15
     */
    /*
     @ requires true;
     @ ensures \result == a ==> a>b ||\result == b ==> b>a;
     */
    int max(int a, int b){
        if(a>b)
            return a;
        else
            return b;
    }

    /*
        Exercice 17
     */
    public class Loop1{
        /*
        @public invariant
        @ ensures x>=0;
         */
        public int x;

        /*
            @public normal_behavior
            @ requires true;
            @ ensures \result == x * x;
         */
        public /*@pure@*/ int method(){
            int y=x;
            int z=0;
            /*
             @loop_invariant z==x*y;
             @decreases y;
             @assignable z,y;
             */
            while(y>0){
                z=z+x;
                y=y-1;
            }
            return z;
        }
    }
    /*
        Exercice 22
     */
    public class Tri{
        /*
        @ requires a!=null && a.length > 0;
        @ ensures \forall int i; i>=0 && i<a.length-1 ; a[i] <= a[i+1];
         */
        void tri(int[]a){
            boolean trie;
            /*
                @assignable trie;
                @loop_invariant trie false
             */
            do{

                trie=true;
                /*
                    @assignable tmp
                    @ \forall j; j>=0 && j<i; a[j] < a[j+1]
                 */
                for(int i=0; i<a.length−1; ++i){
                    if (a[i] > a[i + 1]) {
                        int tmp = a[i];
                        a[i] = a[i + 1];
                        a[i + 1] = tmp;
                        trie = false;
                    }
                }
            } while(!trie);
        }
    }
    public class MainTri{
        public void main(String[]argv)
        {
            int[] a={5,4,3,2,1};
            for(int i=0;i<a.length;i++)

                System.out.print(a[i] + "");
            System.out.println();
            new Tri().tri(a);
            for(int i=0;i<a.length;i++)
                System.out.print(a[i]+  "");
            System.out.println();
        }
    }
    public static void main(String[] args) {

    }
}
