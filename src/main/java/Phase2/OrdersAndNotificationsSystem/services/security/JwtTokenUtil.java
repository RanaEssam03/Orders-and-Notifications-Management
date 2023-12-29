package Phase2.OrdersAndNotificationsSystem.services.security;

import Phase2.OrdersAndNotificationsSystem.models.exceptions.GeneralException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil  {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long  JWT_TOKEN_VALIDITY = 5 * 60 * 60 ;

    @Value("${jwt.secret}")
    private String secret ;


    public String getUsernameFromToken(String token) throws GeneralException {
        return getClaimFromToken(token, Claims::getSubject);
    }


    public Date getExpirationDateFromToken(String token) throws GeneralException {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws GeneralException {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) throws GeneralException {
        if (token == null)
            throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is null");
        if(Jwts.parser() != null)
        {
            JwtParser parser = Jwts.parser().setSigningKey(secret).build();
            try {
                Jws<Claims> x = parser.parseClaimsJws(token);
                return x.getBody();
            } catch (ExpiredJwtException e) {
                throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is expired");
            } catch (UnsupportedJwtException e) {
                throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is unsupported");
            } catch (MalformedJwtException e) {
                throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is malformed");
            } catch (SignatureException e) {
                throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is invalid");
            } catch (IllegalArgumentException e) {
                throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is null");
            }

        }
        throw new GeneralException(HttpStatus.UNAUTHORIZED, "Token is null");

    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) throws GeneralException {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY * 1000*1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, String resUsername) throws GeneralException {
        final String username = getUsernameFromToken(token);
        return (username.equals(resUsername) && !isTokenExpired(token));
    }
}