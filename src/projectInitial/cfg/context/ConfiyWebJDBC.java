package projectInitial.cfg.context;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Order(1)
@Configuration
@PropertySource(value = "WEB-INF/config/jdbc.properties", ignoreResourceNotFound = true)  
public class ConfiyWebJDBC {
	@Autowired  
	Environment env; 
	
	@Bean(name="dataSource" , destroyMethod="close")
	public ComboPooledDataSource getDataSource() throws PropertyVetoException 
	{
		
		ComboPooledDataSource r = new ComboPooledDataSource();
		r.setDriverClass(env.getProperty("jdbc.driverClassName"));
		r.setJdbcUrl(env.getProperty("jdbc.url"));
		r.setUser(env.getProperty("jdbc.username"));
		r.setPassword(env.getProperty("jdbc.password"));
		r.setAcquireIncrement(Integer.parseInt(env.getProperty("c3p0.acquireIncrement")));
		r.setInitialPoolSize(Integer.parseInt(env.getProperty("c3p0.initialPoolSize")));
		r.setMinPoolSize(Integer.parseInt(env.getProperty("c3p0.minPoolSize")));
		r.setMaxPoolSize(Integer.parseInt(env.getProperty("c3p0.maxPoolSize")));
		r.setMaxIdleTime(Integer.parseInt(env.getProperty("c3p0.maxIdleTime")));
		r.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("c3p0.idleConnectionTestPeriod")));
		r.setMaxStatements(Integer.parseInt(env.getProperty("c3p0.maxStatements")));
		r.setAcquireRetryAttempts(Integer.parseInt(env.getProperty("c3p0.acquireRetryAttempts")));
		r.setBreakAfterAcquireFailure(Boolean.parseBoolean(env.getProperty("c3p0.breakAfterAcquireFailure")));
		r.setTestConnectionOnCheckout(Boolean.parseBoolean(env.getProperty("c3p0.testConnectionOnCheckout")));
		return r;
	}
	
	@Autowired
	private ComboPooledDataSource ds = null;
	@Bean(name="txManager")
	public DataSourceTransactionManager dataTrans()
	{
		DataSourceTransactionManager r = new DataSourceTransactionManager();
		r.setDataSource(ds);
		return r;
	}
}
