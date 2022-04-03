<template>
  <div class="sm:mx-auto sm:w-full sm:max-w-md">
    <img class="mx-auto h-12 w-auto" src="https://tailwindui.com/img/logos/workflow-mark-indigo-600.svg"
         alt="Workflow"/>
    <h2 class="mt-6 text-center text-3xl font-extrabold text-primary-text dark:text-primary-text-dark">{{ title }}</h2>
  </div>
</template>

<script>
export default {
  name: "TitleHeader",

  props: {
    title: String
  }
}
</script>

<style scoped>

</style>