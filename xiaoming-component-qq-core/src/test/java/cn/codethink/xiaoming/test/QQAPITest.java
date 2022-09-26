package cn.codethink.xiaoming.test;

import cn.codethink.xiaoming.api.QQAPI;
import cn.codethink.xiaoming.api.QQAPIFactory;
import org.junit.jupiter.api.Test;

public class QQAPITest {
    
    @Test
    void testLoad() {
        final QQAPI instance = QQAPIFactory.getInstance();
    }
}
