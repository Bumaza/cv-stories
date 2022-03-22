package sk.bumaza.cvstories.controller

import org.springframework.web.bind.annotation.*
import sk.bumaza.cvstories.dto.request.LoginRequest
import sk.bumaza.cvstories.dto.request.SignupRequest
import sk.bumaza.cvstories.dto.response.TokenRefreshResponse
import sk.bumaza.cvstories.service.AuthService
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/auth")
class AuthController(val authService: AuthService) {

    @GetMapping("/hello")
    fun hello() = "Auth, say hello."

    @PostMapping("/signin")
    fun authenticateAccount(@RequestBody loginRequest: @Valid LoginRequest?) = authService.login(loginRequest)

    @PostMapping("/signup")
    fun registerAccount(@RequestBody signUpRequest: @Valid SignupRequest?) = authService.createAccount(signUpRequest)

    @PostMapping("/refreshtoken")
    fun refreshToken(@RequestBody request: @Valid TokenRefreshResponse?) = authService.createNewToken(request)
}