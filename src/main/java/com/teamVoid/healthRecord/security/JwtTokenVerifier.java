package com.teamVoid.healthRecord.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if (Strings.isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try {
            String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";

            @SuppressWarnings("deprecation")
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(key.getBytes())).parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            @SuppressWarnings("unchecked")
			var authorities = (List<Map<String, String>>)body.get("authorities");

            List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(
                    m -> new SimpleGrantedAuthority(m.get("authority"))
            ).collect(Collectors.toList());

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException("token " + token + " invalid");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
