package com.chessland.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.chessland.model.dao.mappers.UserMapper;
import com.chessland.model.pojo.User;

public class UserDAO {

	public List<User> getAll(){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			return userMapper.getAll();
		} finally {
			MyBatisUtil.closeSession(sqlSession);
		}
	}
	
}
