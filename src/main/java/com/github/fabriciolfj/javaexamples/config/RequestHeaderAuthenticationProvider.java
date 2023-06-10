package com.github.fabriciolfj.javaexamples.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;

@Configuration
public class RequestHeaderAuthenticationProvider implements AuthenticationProvider {

    @Value("${api.secret-key}")
    private String apiAuthSecret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String authSecretKey = String.valueOf(authentication.getPrincipal());

        if (StringUtils.isBlank(authSecretKey) || !authSecretKey.equals(apiAuthSecret)) {
            throw new BadCredentialsException("bad request header credentials");
        }

        return new PreAuthenticatedAuthenticationToken(authentication.getPrincipal(), null, new ArrayList<>());
    }

    /*
    * No caso específico desse código, o método supports verifica se a classe Authentication fornecida
    * é igual a PreAuthenticatedAuthenticationToken. Isso significa que esse provedor de autenticação
    * é responsável por autenticar apenas instâncias de PreAuthenticatedAuthenticationToken,
    * que é a classe usada para autenticação baseada em cabeçalhos pré-autenticados.
    *
    * Ao retornar true no método supports para a classe PreAuthenticatedAuthenticationToken,
    * o Spring Security sabe que esse provedor de autenticação é adequado para processar essa classe específica
    * e chama o método authenticate desse provedor para realizar a autenticação.
    * Caso supports retorne false para a classe Authentication fornecida, o Spring Security passará para
    *  o próximo provedor de autenticação configurado na lista.
    * */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}
