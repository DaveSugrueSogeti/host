package ie.sugrue.repository;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCConnectionTemplate {

	protected DataSource	dataSource;
	protected JdbcTemplate	jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

}
