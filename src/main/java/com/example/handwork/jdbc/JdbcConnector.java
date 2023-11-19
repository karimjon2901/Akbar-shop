package com.example.handwork.jdbc;

import com.example.handwork.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class JdbcConnector {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    public UserDto getUser(String username1){
        Connection c = null;
        Statement stmt = null;
        UserDto userDto = new UserDto();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url,
                            username, password);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM users u where u.username = '" + username1 + "' ;");
            while ( rs.next() ) {
                userDto.setUsername(rs.getString("username"));
                userDto.setPassword(rs.getString("password"));
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println(userDto.toString());
        return userDto;
    }
}
