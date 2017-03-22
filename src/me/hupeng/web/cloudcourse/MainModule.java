package me.hupeng.web.cloudcourse;


import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;

import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    "*anno", "me.hupeng.web.cloudcourse",
    "*tx", // 事务拦截 aop
    "*async"}) // 异步执行aop
@IocBean
@Modules(scanPackage=true)
public class MainModule {
	
}