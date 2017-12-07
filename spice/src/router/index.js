import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/pages/main'
import Material from '@/pages/material'
import Recipe from '@/pages/recipe'
import Production from '@/pages/production'
import Password from '@/pages/password'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'main',
            component: Main,
            children: [
                {
                    path: '',
                    name: 'material',
                    component: Material
                },
                {
                    path: 'recipe',
                    name: 'recipe',
                    component: Recipe
                },
                {
                    path: 'production',
                    name: 'production',
                    component: Production
                },
                {
                    path: 'password',
                    name: 'password',
                    component: Password
                }
            ]
        }
    ]
})
