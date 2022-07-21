
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import PetManager from "./components/listers/PetCards"
import PetDetail from "./components/listers/PetDetail"

import ItemManager from "./components/listers/ItemCards"
import ItemDetail from "./components/listers/ItemDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/pets',
                name: 'PetManager',
                component: PetManager
            },
            {
                path: '/pets/:id',
                name: 'PetDetail',
                component: PetDetail
            },

            {
                path: '/items',
                name: 'ItemManager',
                component: ItemManager
            },
            {
                path: '/items/:id',
                name: 'ItemDetail',
                component: ItemDetail
            },



    ]
})
