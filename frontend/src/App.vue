<template>
  <div class="bg-primary-bg dark:bg-primary-bg-dark">
    <LoginView />
    <ForgotPasswordView class="hidden" />
    <ResetPasswordView class="hidden" />
  </div>
</template>

<script>
import LoginView from "@/views/onboarding/LoginView";
import ForgotPasswordView from "@/views/onboarding/ForgotPasswordView";
import ResetPasswordView from "@/views/onboarding/ResetPasswordView";

export default {
  name: 'App',
  components: {
    ResetPasswordView,
    ForgotPasswordView,
    LoginView
  },

  created() {
    localStorage.theme = 'dark';
  }
}
</script>

<style>

</style>
