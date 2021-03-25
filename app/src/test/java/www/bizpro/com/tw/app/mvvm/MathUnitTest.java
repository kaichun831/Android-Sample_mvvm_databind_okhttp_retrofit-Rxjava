package www.bizpro.com.tw.app.mvvm;

import android.util.Log;

import androidx.databinding.ObservableInt;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import java.util.regex.Matcher;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.*;

public class MathUnitTest {

    @Test
    public  void equalNumber(){
        int i = 10;
        int x = 5;
        assertEquals(i,x+5);
    }
    @Test
    public void MatchNumber(){
        int answer = 2;
        int testItem = 1 + 1;
        MatcherAssert.assertThat(answer, Matchers.is(testItem));
    }
    @Test
    public void array_hasItems_element_1_5(){
        List<Integer> integerArray = Arrays.asList(1, 2, 3, 5);

        MatcherAssert.assertThat(integerArray, Matchers.hasItem(5));
    }



    // =======Mock測試=======
    @Mock
    private Logger mockLogger = new Logger();

    @Test
    public void sample_log_call_once(){
        //呼叫使用這個測試類別
        MockitoAnnotations.initMocks(this);

        //action
        MyMath myMath =new MyMath(mockLogger);
        myMath.add(-1,-2);
        //assert
        //verify 驗證  代操mockLogger的class 使用內部的方法log
        Mockito.verify(mockLogger).log(Mockito.anyString());
    }

    @Test
    public void sample_logger_call_three_times(){
        MockitoAnnotations.initMocks(this);

        int expectCallTimes = 3;  //呼叫次數
        MyMath myMath = new MyMath(mockLogger);
        //小於0的算一次,add方法內寫到logger的方法
        myMath.add(-1, 2);  //F
        myMath.add(1, -2);  //T
        myMath.add(3, 100); //F
        myMath.add(0, -2); //T
        myMath.add(0,-2);  //T

        //verify 是驗證函示有被呼叫與否
        Mockito.verify(mockLogger, Mockito.times(expectCallTimes)).log(Mockito.anyString());
    }





}
