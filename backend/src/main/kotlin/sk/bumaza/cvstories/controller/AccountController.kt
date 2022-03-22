package sk.bumaza.cvstories.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import sk.bumaza.cvstories.constant.AccountType
import sk.bumaza.cvstories.dto.request.ForgotPasswordRequest
import sk.bumaza.cvstories.dto.request.ResetPasswordRequest
import sk.bumaza.cvstories.dto.request.UpdateAccountRequest
import sk.bumaza.cvstories.security.services.User
import sk.bumaza.cvstories.service.AccountService
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/account")
class AccountController(
    val accountService: AccountService
) {

    @GetMapping("/{id}")
    fun getDetail(@PathVariable(value = "id") accountId: Long,
                  @AuthenticationPrincipal user: User
    ) = accountService.getAccountDetail(accountId, user)

    @PutMapping("/{id}")
    fun updateAccount(@PathVariable(value = "id") accountId: Long, @AuthenticationPrincipal user: User,
                      @Valid @RequestBody request: UpdateAccountRequest
    ) = accountService.updateAccount(accountId, user, request)

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable(value = "id") accountId: Long,
                      @AuthenticationPrincipal user: User) = accountService.deleteAccount(accountId, user)

    @GetMapping("/logged")
    fun logged() = "You are authorized"

    @GetMapping("/types")
    fun getAccountTypes() = AccountType.values()

    @PostMapping("/reset-password")
    fun updatePassword(@Valid @RequestBody request: ResetPasswordRequest) = accountService.resetPassword(request)

    @PostMapping("/forgot-password")
    fun forgotPassword(@Valid @RequestBody request: ForgotPasswordRequest) = accountService.forgotPassword(request)

}