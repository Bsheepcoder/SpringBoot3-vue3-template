public class Main {
    public static void main(String[] args) {
        qxd(100);
        MyCode(100,100);
    }
    public static void qxd(int n){
        System.out.println("我是开发者qxd，下面是我写的代码");
        int sum = 0;
        for(int i  = 0 ; i< n ; i++){
            sum = sum + i;
        }
        System.out.println(sum);
        System.out.println("-----------------------------");
    }
    public static void MyCode(int m, int n){
        System.out.println("我是开发者my，下面是我写的代码");
        int count = 0;
        for(int i = 0; i < m; i++){
            count = count + n;
        }
        System.out.println(count);
        System.out.println("-----------------------------");
    }

}



