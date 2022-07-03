package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"SELECT * FROM member WHERE email = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regDate").toLocalDateTime());
						member.setId(rs.getLong("id"));
						return member;
					}
				}, 
				email);
					
		return results.isEmpty() ? null : results.get(0);
	}
		
	public void insert(Member member) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement pstmt = conn.prepareStatement(
							"INSERT INTO member (email, password, name, regDate) VALUES (?, ?, ?, ?)",
							new String[] { "id" });
					pstmt.setString(1,  member.getEmail());
					pstmt.setString(2,  member.getPassword());
					pstmt.setString(3, member.getName());
					pstmt.setTimestamp(4,  
							Timestamp.valueOf(member.getRegisterDateTime()));
					
					return pstmt;
				}
			}, keyHolder);
			
			Number keyValue = keyHolder.getKey();
			member.setId(keyValue.longValue());
	}
		
	public void update(Member member) {
		jdbcTemplate.update(
			"UPDATE member SET name = ?, password = ? WHERE email = ?",
			member.getName(), member.getPassword(), member.getEmail());
	}
		
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("SELECT * FROM member",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
							Member member = new Member(
								rs.getString("email"),
								rs.getString("password"),
								rs.getString("name"),
								rs.getTimestamp("regDate").toLocalDateTime());
							member.setId(rs.getLong("id"));
							return member;
						}
				});
		return results;
	}
	
	public int count() {
		Integer count = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM member", Integer.class);
		return count;
	}
}