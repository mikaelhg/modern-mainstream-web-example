<script setup>
import { ref } from 'vue'

defineProps({
  msg: String
})

const count = ref(0);
const apiCall = () => {
    fetch("/api/counter")
        .then((response) => response.json())
        .then((data) => count.value = data.counter);
}

setInterval(apiCall, 1000);
</script>

<template>
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
            <div class="col-lg-6 col-md-8 mx-auto">
                <h1 class="fw-light">{{ msg }}</h1>
                <p class="lead text-muted">
                    The count is: {{ count }}.
                </p>
            </div>
        </div>
    </section>
</template>

<style scoped>
</style>
