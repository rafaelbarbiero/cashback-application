package br.com.rbarbiero.feign;

import br.com.rbarbiero.dto.authentication.AccessToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "authenticationClient", url = "${spotify.api.authorization.address}")
public interface SpotifyAuthenticationFeignClient {
    @PostMapping(value = "/token")
    AccessToken getBearerToken(@RequestParam(value = "grant_type") String grantType,
                               @RequestHeader(value = "Content-Type") String contentType,
                               @RequestHeader(value = "Authorization") String authorization);
}
