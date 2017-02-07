package com.hrocloud.tiangong.verifycode.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hanzhihua on 2017/1/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/application-common.xml","classpath:META-INF/spring/application-datasource.xml"
,"classpath:META-INF/spring/application-export.xml","classpath:META-INF/spring/application-service.xml"})
public abstract class AbsractTest {
}
