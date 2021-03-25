package www.bizpro.com.tw.app.mvvm;



public class MyMath {

    private Logger logger;

    public MyMath(Logger logger){
        this.logger = logger;
    }
    public int add(int first, int second){
        int sum = first + second;
        if(sum < 0)
            logger.log(String.valueOf(sum));
        return first + second;
    }
}

