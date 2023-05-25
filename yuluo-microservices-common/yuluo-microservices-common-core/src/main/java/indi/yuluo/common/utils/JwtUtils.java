package indi.yuluo.common.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import indi.yuluo.common.constant.SecurityConstants;
import indi.yuluo.common.constant.TokenConstants;
import indi.yuluo.common.text.Convert;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: yuluo
 * @date: 2023/5/19 17:13
 * @description: JWT 工具类
 */

public class JwtUtils {

	public static String SECRET_KEY = TokenConstants.SECRET;

	/**
	 * 通过 userDetails 生成 Token 信息
	 * @param userDetails UserDetail 对象
	 * @return Token
	 */
	public String generateToken(UserDetails userDetails) {

		return generateToken(new HashMap<>(), userDetails);
	}

	/**
	 * 通过 Map信息 + userDetails 生成 Token 信息
	 * @param userDetails UserDetail 对象
	 * @return Token
	 */
	public static String generateToken(
			Map<String, Object> extraClaims,
			UserDetails userDetails
	) {
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	/**
	 * 从令牌中获取数据声明
	 *
	 * @param token 令牌
	 * @return 数据声明
	 */
	public static Claims parseToken(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	/**
	 * 根据令牌获取用户标识
	 *
	 * @param token 令牌
	 * @return 用户ID
	 */
	public static String getUserKey(String token) {
		Claims claims = parseToken(token);
		return getValue(claims, SecurityConstants.USER_KEY);
	}

	/**
	 * 根据令牌获取用户标识
	 *
	 * @param claims 身份信息
	 * @return 用户ID
	 */
	public static String getUserKey(Claims claims) {
		return getValue(claims, SecurityConstants.USER_KEY);
	}

	/**
	 * 根据令牌获取用户ID
	 *
	 * @param token 令牌
	 * @return 用户ID
	 */
	public static String getUserId(String token) {
		Claims claims = parseToken(token);
		return getValue(claims, SecurityConstants.DETAILS_USER_ID);
	}

	/**
	 * 根据身份信息获取用户ID
	 *
	 * @param claims 身份信息
	 * @return 用户ID
	 */
	public static String getUserId(Claims claims) {
		return getValue(claims, SecurityConstants.DETAILS_USER_ID);
	}

	/**
	 * 根据令牌获取用户名
	 *
	 * @param token 令牌
	 * @return 用户名
	 */
	public static String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	public static <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private static Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	/**
	 * 根据身份信息获取用户名
	 *
	 * @param claims 身份信息
	 * @return 用户名
	 */
	public static String getUserName(Claims claims) {
		return getValue(claims, SecurityConstants.DETAILS_USERNAME);
	}

	/**
	 * 根据身份信息获取键值
	 *
	 * @param claims 身份信息
	 * @param key 键
	 * @return 值
	 */
	public static String getValue(Claims claims, String key) {

		// System.out.println(claims.get("username"));
		return Convert.toStr(claims.get(key), "");
	}

	private static Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * 判断 Token 是否过期
	 * @param token Token
	 * @return 是否过期标志
	 */
	public static boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private static Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
}
