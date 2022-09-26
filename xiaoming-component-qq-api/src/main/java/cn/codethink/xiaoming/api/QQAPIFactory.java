package cn.codethink.xiaoming.api;

import cn.codethink.common.util.Exceptions;
import cn.codethink.common.util.Preconditions;
import cn.codethink.xiaoming.annotations.BotInternalAPI;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

/**
 * <h1>小明 QQ API 工厂</h1>
 *
 * <p>存放小明 QQ API 的实例</p>
 *
 * @author Chuanwise
 */
@BotInternalAPI
public final class QQAPIFactory {
    private QQAPIFactory() {
        Exceptions.throwUtilClassInitializeException(APIFactory.class);
    }
    
    private static volatile QQAPI instance;
    
    /**
     * 获取小明 QQ API 实例
     *
     * @return 小明 QQ API 实例
     * @throws NoSuchElementException 没有小明 QQ API 实例
     */
    public static QQAPI getInstance() {
        if (instance == null) {
            synchronized (APIFactory.class) {
                if (instance == null) {
                    // load api
                    final ServiceLoader<QQAPI> serviceLoader = ServiceLoader.load(QQAPI.class);
                    final Iterator<QQAPI> iterator = serviceLoader.iterator();
                    
                    if (!iterator.hasNext()) {
                        throw new NoSuchElementException("no xiaoming qq core present!");
                    }
                    instance = iterator.next();
                }
            }
        }
        return instance;
    }
    
    /**
     * 修改小明 QQ API 实例
     *
     * @param instance 小明 QQ API 实例
     * @return 修改前的小明 QQ API 实例
     * @throws NullPointerException instance 为 null
     */
    public static QQAPI setInstance(QQAPI instance) {
        Preconditions.objectNonNull(instance, "instance");
    
        synchronized (APIFactory.class) {
            final QQAPI beforeSet = QQAPIFactory.instance;
            QQAPIFactory.instance = instance;
            return beforeSet;
        }
    }
}
