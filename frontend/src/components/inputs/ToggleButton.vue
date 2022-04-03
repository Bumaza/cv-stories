<template>
  <div>
    <SwitchGroup v-if="type === 'right'" as="div" class="flex items-center">
      <Switch v-model="enabled"
              :class="[enabled ? 'bg-primary-medium' : 'bg-gray-200', 'switch']">
      <span aria-hidden="true"
            :class="[enabled ? 'translate-x-5' : 'translate-x-0', 'switch-span']"/>
      </Switch>
      <SwitchLabel as="span" class="ml-3">
        <span class="switch-label">{{ label }} </span>
        <span class="switch-description">{{ description }}</span>
      </SwitchLabel>
    </SwitchGroup>

    <SwitchGroup v-else-if="type === 'left'" as="div" class="flex items-center justify-between">
      <span class="flex-grow flex flex-col">
        <SwitchLabel as="span" class="switch-label" passive>{{ label }}</SwitchLabel>
        <SwitchDescription as="span" class="switch-description">{{description }}</SwitchDescription>
      </span>
      <Switch v-model="enabled"
              :class="[enabled ? 'bg-primary-medium dark:primary-medium' : 'bg-gray-200', 'switch']">
        <span aria-hidden="true"
              :class="[enabled ? 'translate-x-5' : 'translate-x-0', 'switch-span']"/>
      </Switch>
    </SwitchGroup>

    <Switch v-else-if="type === 'plain'"  v-model="enabled" :class="[enabled ? 'bg-primary-medium' : 'bg-gray-200', 'switch']">
      <span class="sr-only">Use setting</span>
      <span aria-hidden="true" :class="[enabled ? 'translate-x-5' : 'translate-x-0', 'switch-span']" />
    </Switch>

  </div>

</template>

<script>

import {ref} from 'vue'
import {Switch, SwitchGroup, SwitchLabel, SwitchDescription} from '@headlessui/vue'

export default {
  name: "ToggleButton",

  components: {
    Switch, SwitchGroup, SwitchLabel, SwitchDescription
  },

  props: {
    label: String,
    description: String,
    name: String,
    type: {
      default: 'plain'
    },
    disabled: {
      default: true
    }
  },

  data: () => ({
    enableClasses: [],

    spanClass: 'pointer-events-none inline-block h-5 w-5 rounded-full bg-white shadow transform ring-0 transition ease-in-out duration-200',
    switchClass: 'relative inline-flex flex-shrink-0 h-6 w-11 border-2 border-transparent rounded-full cursor-pointer transition-colors ease-in-out duration-200 dark:bg-box-dark dark:border-box-border-dark',
  }),

  setup() {
    const enabled = ref(false)

    return {
      enabled,
    }
  },

}
</script>

<style scoped>

</style>