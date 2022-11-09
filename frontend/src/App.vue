<script setup>
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap-icons/font/bootstrap-icons.css"
import "bootstrap/dist/js/bootstrap.esm"
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>

    <header>
        <nav class="navbar navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <i class="bi bi-globe"></i>
                    Example
                </a>
            </div>
        </nav>
    </header>

    <main>
        <HelloWorld msg="Modern mainstream web application example" />
    </main>

    <footer class="text-muted py-5">
        <div class="container">
            <p class="float-end mb-1">
                <a href="#">Back to top</a>
            </p>
        </div>
    </footer>

</template>

<style>
</style>
