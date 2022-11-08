<script setup>
import { ref } from 'vue'

defineProps({
  msg: String
})

const count = ref(0);

function apiCall() {
    fetch("/api/counter")
        .then((response) => response.json())
        .then((data) => count.value = data.counter);
}

setInterval(apiCall, 1000);
</script>
4
<template>
  <h1>{{ msg }}</h1>
  <button type="button" @click="count++">count is: {{ count }}</button>
</template>

<style scoped>
a {
  color: #42b983;
}
</style>
