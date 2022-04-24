package web.javaLearn.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.javaLearn.model.LoginRequest;
import web.javaLearn.model.RegisterRequest;
import web.javaLearn.repository.UserRepository;
import web.javaLearn.service.AuthenticationService;
import web.javaLearn.service.JwtRefreshTokenService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserRepository userRepository;
    private final JwtRefreshTokenService jwtRefreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()){
            return new ResponseEntity<>("Username taken", HttpStatus.CONFLICT);
        }
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration successful", HttpStatus.OK);
    }

    @PostMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws Exception {
        if(authService.verifyAccount(token)) {
            return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Given token is not valid", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")// api/auth/login
    public ResponseEntity<?> signIn(@RequestBody LoginRequest loginRequest)
    {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestParam String token){
        return ResponseEntity.ok(jwtRefreshTokenService.generateAccesTokenFromRefreshToken(token));
    }
}
