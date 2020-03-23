package com.chessland.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;

import com.chessland.model.dao.mappers.UserMapper;
import com.chessland.model.pojo.User;

import ch.qos.logback.classic.Logger;

public class UserDAO {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserDAO.class);

	public List<User> getAll() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			MyBatisUtil.closeSession(sqlSession);
		}
	}

	public User checkUser(String email) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.checkUser(email);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}finally {
			MyBatisUtil.closeSession(sqlSession);
		}
	}
	
	public void insertUser(User user) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			userMapper.insertUser(user);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			MyBatisUtil.rollbackSession(sqlSession);
			throw e;
		}finally {
			
		}
	}
}
