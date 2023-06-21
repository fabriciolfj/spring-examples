package com.github.fabriciolfj.javaexamples.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/*
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

    /* O método supports é utilizado pelo Spring Security para determinar se o provedor de autenticação
     (RequestHeaderAuthenticationProvider) é adequado para processar uma determinada instância de Authentication.

      O Spring Security possui uma lista de provedores de autenticação configurados e,
      ao receber uma requisição de autenticação, ele itera por esses provedores para encontrar aquele que
       é capaz de autenticar a instância de Authentication fornecida
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
/*
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }*/
//}
