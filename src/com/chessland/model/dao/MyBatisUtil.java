package com.chessland.model.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * Clase de utilidad para obtener una configuración de Mybatis.
 * 
 * @author gustavo
 *
 */
public class MyBatisUtil {

	private static SqlSessionFactory factory;

	private static final Logger logger = (Logger) LoggerFactory.getLogger(MyBatisUtil.class);

	/**
	 * Estático para que sólo se configure MyBatis una vez
	 */
	static {
		Reader reader = null;

		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}

	/**
	 * En caso de que la {@link SqlSession} no sea nula se hara un rollback.
	 * 
	 * @param sqlSession {@link SqlSession} en la cual se producirá el rollback
	 */
	public static void rollbackSession(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.rollback();
		}
	}

	/**
	 * En caso de que la {@link SqlSession} no sea nula se cerrá.
	 * 
	 * @param sqlSession {@link SqlSession} que tiene que cerrarse.
	 */
	public static void closeSession(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
}
