<template>
  <div class="site-bg">

    <TitleHeader title="Forgot your password ?" />

    <div class="box-wrapper">
      <div class="box-bg">
        <form class="space-y-6" action="#" method="POST">

          <p class="mt-2 text-left text-lg text-primary-text dark:text-primary-text-dark">
            Don't fret! Just type in your email and we will send you a code to reset your password!
          </p>

          <InputField label="Email address" name="email" type="email" autocomplete="email" />
          <PrimaryButton title="Reset password" />

        </form>
      </div>
    </div>
  </div>
</template>

<script>

import PrimaryButton from "@/components/buttons/PrimaryButton";
import InputField from "@/components/inputs/InputField";
import TitleHeader from "@/components/onboarding/TitleHeader";

export default {
  name: "ForgotPasswordView",
  components: {PrimaryButton, InputField, TitleHeader}
}
</script>

<style scoped>

</style>