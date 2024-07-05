package com.houstondirectauto.refurb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.houstondirectauto.refurb.util.Constants.*;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
    name = BEARER_AUTH,
    type = SecuritySchemeType.HTTP,
    bearerFormat = JWT_BEARER_FORMAT,
    scheme = JWT_BEARER_SCHEME
)
public class SwaggerConfiguration {

	/**
	 * Api Info
	 * @return
	 */
	@Bean
	public OpenAPI apiOpenAPI() {
		return new OpenAPI().components(new Components())
				.info(new Info().title(APP_TITLE).description(APP_DESCRIPTION).version(APP_VERSION)
				.license(new License().name(APP_LICENCESE_URL).url(APP_LICENCESE_URL)));
	}

}
