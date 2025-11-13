package org.uv.DAPPWEBPractica07;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/me")
    public Authentication authMe(Authentication authentication) {
        return authentication;
    }
}
