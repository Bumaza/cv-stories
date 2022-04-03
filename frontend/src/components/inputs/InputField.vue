<template>
  <div>
    <label :for="name" class="input-text-label"> {{label}} </label>
    <div class="mt-1">
      <input :id="name" :name="name" :type="type" :autocomplete="autocomplete" :required="isRequired"
             class="input-text-field" />
    </div>
  </div>
</template>

<script>
export default {
  name: "InputField",

  props: {
    label: String,
    type: String,
    name: String,
    autocomplete: String,
    isRequired: {
      type: Boolean,
      default: true
    }
  }
}
</script>

<style scoped>

</style>