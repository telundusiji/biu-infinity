package site.teamo.biu.infinity.common.util;

/**
 * @author 爱做梦的锤子
 * @create 2020/11/27
 */

/**
 * 三元元组
 *
 * @param <T1>
 * @param <T2>
 */
public class Tuple3<T1, T2, T3> {

    public final T1 _1;
    public final T2 _2;
    public final T3 _3;

    private Tuple3(T1 t1, T2 t2, T3 t3) {
        this._1 = t1;
        this._2 = t2;
        this._3 = t3;
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
        return new Tuple3(t1, t2, t3);
    }
}
