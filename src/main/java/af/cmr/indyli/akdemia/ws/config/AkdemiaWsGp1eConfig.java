package af.cmr.indyli.akdemia.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import af.cmr.indyli.akdemia.business.config.AkdemiaBusinessGp1eConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Configuration
@OpenAPIDefinition(info = @Info(title = "akdemia", version = "1.0", description = "akdemia", contact = @Contact(name = "akdemia")), security = {@SecurityRequirement(name = "bearerToken")})
@Import(AkdemiaBusinessGp1eConfig.class)
@ComponentScan(basePackages = {"af.cmr.indyli.akdemia.ws.*"})
public class AkdemiaWsGp1eConfig {

}