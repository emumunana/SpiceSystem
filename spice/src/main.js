// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App'
import router from './router'

import ElementUI from 'element-ui'
import RequestTool from '@/tools/requestTool'
import CommonTool from '@/tools/commonTool'
// import './common/index'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.$http = RequestTool
Vue.prototype.$util = CommonTool

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    template: '<App/>',
    components: { App }
})
