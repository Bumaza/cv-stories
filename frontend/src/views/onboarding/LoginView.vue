<template>
  <div class="site-bg">

    <TitleHeader title="Sign in to your account" />

    <div class="box-wrapper">
      <div class="box-bg">
        <form class="space-y-6" action="#" method="POST">

          <InputField label="Email address" name="email"
                      type="email" autocomplete="email" />

          <InputField label="Password" name="password"
                      type="password" autocomplete="current-password" />

          <div class="flex items-center justify-between">
            <Checkbox title="Remember me" name="remember-me" />
            <TextButton title="Forgot your password?" />
          </div>

          <ToggleButton type="left" label="Label" description="Moj description" />

          <PrimaryButton title="Sign in"/>
        </form>
      </div>
    </div>
  </div>
</template>


<script>


import ToggleButton from "@/components/inputs/ToggleButton";
import Checkbox from "@/components/inputs/Checkbox";
import TextButton from "@/components/buttons/TextButton";
import PrimaryButton from "@/components/buttons/PrimaryButton";
import InputField from "@/components/inputs/InputField";
import TitleHeader from "@/components/onboarding/TitleHeader";
// import { useI18n } from "vue-i18n";

export default {
  name: "LoginView",
  components: {ToggleButton, Checkbox, TextButton, PrimaryButton, InputField, TitleHeader},

  // setup() {
  //   const {t, locale} = useI18n();
  //   return { t, locale }
  // },
  //
  // created() {
  //   console.log(this.t('onboarding.login.title'))
  // }
}
</script>

<style scoped>

</style>