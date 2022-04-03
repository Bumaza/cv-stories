<template>
  <div class="flex items-center">
    <input :id="name" :disabled="disabled" :name="name" type="checkbox" class="checkbox" />
    <label :for="name" class="checkbox-label"> {{title}} </label>
  </div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Checkbox",

  props: {
    title: String,
    name: String,
    disabled: {
      default: false
    }
  }
}
</script>

<style scoped>

</style>