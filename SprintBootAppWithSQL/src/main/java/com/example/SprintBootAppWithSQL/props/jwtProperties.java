package com.example.SprintBootAppWithSQL.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class jwtProperties {
    @Getter
    @Setter
    @Value("${jwt.private.key}")
    private String privateKey;

    @Getter
    @Setter
    @Value("${jwt.audience}")
    private String audience;

    @Getter
    @Setter
    @Value("${jwt.issuer}")
    private String issuer;

    @Getter
    @Setter
    @Value("${jwt.expiry}")
    private Integer expiry;

    @Getter
    @Setter
    @Value("${jwt.cookie.name}")
    private String cookieName;
}
