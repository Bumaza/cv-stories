<template>
  <div>
    <button type="submit" class="main-btn">{{ title }}</button>
  </div>
</template>

<script>
export default {
name: "PrimaryButton",

  props: {
    title: String
  }
}
</script>

<style scoped>

</style>