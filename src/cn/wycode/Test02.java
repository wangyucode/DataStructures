package cn.wycode;

public class Test02 {

    public static void main(String[] args) {
        // write your code here
        Test02 t = new Test02();
        System.out.println(t.f1(1.1));
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            t.f1(1.1);
        }
        System.out.println("time=" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            t.f2(1.1);
        }
        System.out.println("time=" + (System.currentTimeMillis() - time));
    }

    private double f1(double x) {
        double v = 1f;
        for (int i = 1; i <= 100; i++) {
            //v += Math.pow(x,i)/i; 替换为自己写的pow函数测试
            v += pow(x, i) / i;
        }
        return v;
    }

    //f(x) = 1+x(1/1+x(1/2+x(1/3+....x(1/100))))
    private double f2(double x) {
        double v = x / 100;
        int i = 100;
        while (i > 1) {
            v = x * (1f / (i - 1) + v);
            i--;
        }
        return v + 1;
    }

    private double pow(double x, int y) {
        if(y==0){
            return 1;
        }
        if(y==1){
            return x;
        }
        double v = x;
        for(int i=2;i<=y;i++){
            v *=x;
        }

        return v;
    }
}
