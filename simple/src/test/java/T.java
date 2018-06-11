import java.io.UnsupportedEncodingException;

/**
 * Created by huangrongyou@yixin.im on 2018/6/5.
 */
public class T {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "\uD840\uDE56";
        System.out.println(s.getBytes("UTF-16").length);
        System.out.println("请问".getBytes("UTF-16").length);
        System.out.println(s);
    }
}
