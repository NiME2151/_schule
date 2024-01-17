package de.szut.springboot_auth_service_demo.service;

import de.szut.springboot_auth_service_demo.exception.JwtAuthenticationException;
import de.szut.springboot_auth_service_demo.security.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtTokenService {

    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public static final String TOKEN_EMPTY = "Token is empty.";
    public static final String USER_EMPTY = "No user details available.";
    public static final String USER_NOT_VALID = "User is not valid.";
    private final String tokenBearer = "Authorization";
    private final String bearer = "Bearer ";

    public JwtToken generateToken(UserDetails userDetails) throws JwtAuthenticationException {
        if (userDetails == null) {
            throw new JwtAuthenticationException("Generating token not possible: " + USER_EMPTY);
        }
        Date createdDate = new Date();
        Date expirationDate = calculateExpirationDate(createdDate);
        String token = Jwts.builder()
                .setClaims(new HashMap<>())
                .setIssuer(issuer)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
        return new JwtToken(token);
    }

    public String extractToken(HttpServletRequest request) {
        String requestHeader = request.getHeader(tokenBearer);
        if (StringUtils.isNotEmpty(requestHeader) && requestHeader.startsWith(bearer)) {
            return requestHeader.substring(bearer.length());
        }
        else {
            return null;
        }
    }

    public String extractUsername(String token) throws JwtAuthenticationException {
        if (StringUtils.isEmpty(token)) {
            return null;

        }
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) throws JwtAuthenticationException {
        if (StringUtils.isEmpty(token)) {
            throw new JwtAuthenticationException(TOKEN_EMPTY);
        }
        Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) throws JwtAuthenticationException {
        if (StringUtils.isEmpty(token) || isTokenExpired(token)) {
            return false;
        }
        else if (userDetails == null) {
            throw new JwtAuthenticationException(USER_EMPTY);
        }
        String username = extractUsername(token);
        if (username.equals(userDetails.getUsername())) {
            return true;
        }
        else {
            throw new JwtAuthenticationException(USER_NOT_VALID);
        }
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) throws JwtAuthenticationException {
        if (StringUtils.isEmpty(token)) {
            throw new JwtAuthenticationException(TOKEN_EMPTY);
        }
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey()).build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date extractExpirationDate(String token) throws JwtAuthenticationException {
        if (StringUtils.isEmpty(token)) {
            throw new JwtAuthenticationException(TOKEN_EMPTY);
        }
        return extractAllClaims(token).getExpiration();
    }
}
