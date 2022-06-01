package kr.codefty.config.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	/** 데이터페이스 접속 객체 */
	private static SqlSessionFactory sqlSessionFactory;
	
	/** XML에 명시된 접속 정보를 읽어온다. */
	static {
		// 접속정보를 명시하고 있는 XML의 경로 읽기
		try {
			// mybatis-config.xml 파일의 경로 지정 
			Reader reader = Resources.getResourceAsReader("kr/codefty/config/mybatis/mybatis-config.xml");
			
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 데이터베이스 접속 객체를 통해 DATABASE에 접속한 세션을 리턴한다. */
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}