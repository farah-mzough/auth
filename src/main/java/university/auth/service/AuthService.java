package university.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import university.auth.dto.AuthResponse;
import university.auth.dto.LoginRequest;
import university.auth.dto.RegisterRequest;
import university.auth.entity.User;
import university.auth.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req) {

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setRole(req.getRole());

        repo.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse("User registered successfully!", token);
    }

    public AuthResponse login(LoginRequest req) {

        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse("Login successful!", token);
    }
}
