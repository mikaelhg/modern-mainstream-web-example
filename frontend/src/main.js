import { createApp } from 'vue'

import CoreuiVue from '@coreui/vue'


import App from './App.vue'


const app = createApp(App)
app.use(CoreuiVue)
app.mount('#app')
