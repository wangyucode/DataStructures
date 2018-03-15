package cn.wycode;

public class Test01 {

    public static void main(String[] args) {
	// write your code here
        Test01 t = new Test01();
        long time = System.currentTimeMillis();
        t.printN(10000);
        System.out.println("time="+(System.currentTimeMillis()-time));
        time = System.currentTimeMillis();
        t.printNR(10000);
        System.out.println("time="+(System.currentTimeMillis()-time));
    }

    private void printNR(int n) {
        if(n>0) {
            printNR(n - 1);
            System.out.println(n);
        }
    }

    private void printN(int n){
        for(int i= 1;i<=n;i++){
            System.out.println(i);
        }
    }
}
