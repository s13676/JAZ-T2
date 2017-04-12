package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.User;

public class UserMapper implements IMapResultSetToEntity<User> {
	public User map(ResultSet rs) throws SQLException {
		User u = new User();
		
		u.setId(rs.getInt("id"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		u.setEmail(rs.getString("email"));
		u.setPremium(rs.getBoolean("premium"));
		u.setAdmin(rs.getBoolean("admin"));
		
		return u;
	}
}
