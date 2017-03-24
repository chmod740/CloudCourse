package me.hupeng.web.cloudcourse;


import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup{
	public static Ioc ioc;
	@Override
	public void destroy(NutConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(NutConfig conf) {
		
		/**
		 * 初始化的时候自动建表
		 * */
		MainSetup.ioc = conf.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "me.hupeng.web.cloudcourse", false);
	}
	
}