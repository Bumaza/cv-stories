<template>
  <div class="site-bg">

    <TitleHeader title="Reset your password"/>

    <div class="box-wrapper">
      <div class="box-bg">
        <form class="space-y-6" action="#" method="POST">

          <InputField label="New password" name="password" type="password" />

          <InputField label="Confirm New password" name="password-confirm" type="password" />

          <PrimaryButton title="Reset password" />

        </form>
      </div>
    </div>
  </div>
</template>

<script>

import InputField from "@/components/inputs/InputField";
import PrimaryButton from "@/components/buttons/PrimaryButton";
import TitleHeader from "@/components/onboarding/TitleHeader";

export default {
  name: "ResetPasswordView",
  components: {InputField, PrimaryButton, TitleHeader}
}
</script>

<style scoped>

</style>