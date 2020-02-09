import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
 **/
export const constantRouterMap = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/404', component: () => import('@/views/404'), hidden: true},
  {
    path: '',
    component: Layout,
    redirect: '/home',
    children: [{
      path: 'home',
      name: 'home',
      component: () => import('@/views/home/index'),
      meta: {title: '首页', icon: 'home'}
    }]
  },
  {
    path:'/mms',
    component: Layout,
    redirect: '/mms/msg',
    name: 'mms',
    meta: {title: '消息管理', icon: 'sms'},
    alwaysShow: true,
    children: [
      {
        path: 'msg',
        name: 'msg',
        component: () => import('@/views/mms/msg/index'),
        meta: {title: '消息列表', icon: 'sms-coupon'}
      },
      {
        path: 'notice',
        name: 'notice',
        component: () => import('@/views/mms/notice/index'),
        meta: {title: '公告列表', icon: 'sms-coupon'}
      }
      ]
  },
  {
    path:'/faq',
    component: Layout,
    redirect: '/faq/faq',
    name: 'faq',
    meta: {title: '常见问题管理', icon: 'sms'},
    alwaysShow: true,
    children: [
      {
        path: 'faq',
        name: 'faq',
        component: () => import('@/views/faq/faq/index'),
        meta: {title: '常见问题列表', icon: 'sms-coupon'}
      }
    ]
  },
  {
    path:'/ums',
    component: Layout,
    redirect: '/ums/user',
    name: 'ums',
    meta: {title: '用户管理', icon: 'sms'},
    alwaysShow: true,
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('@/views/ums/user/index'),
        meta: {title: '用户列表', icon: 'sms-coupon'}
      },
      {
        path: 'rate',
        name: 'rate',
        component: () => import('@/views/ums/rate/index'),
        meta: {title: '费率设置', icon: 'sms-coupon'}
      },
      {
        path: 'real',
        name: 'real',
        component: () => import('@/views/ums/real/index'),
        meta: {title: '实名认证', icon: 'sms-coupon'}
      },
      {
        path: 'realInfoDetail',
        name: 'realInfoDetail',
        component: () => import('@/views/ums/real/realInfoDetail'),
        meta: {title: '实名认证详情', icon: 'sms-coupon'},
        hidden: true
      },
      {
        path: 'withdraw',
        name: 'withdraw',
        component: () => import('@/views/ums/withdraw/index'),
        meta: {title: '提现管理', icon: 'sms-coupon'}
      },
      {
        path: 'card',
        name: 'card',
        component: () => import('@/views/ums/card/index'),
        meta: {title: '银行卡管理', icon: 'sms-coupon'}
      }
    ]
  },
  {
    path:'/oms',
    component: Layout,
    redirect: '/oms/order',
    name: 'oms',
    meta: {title: '订单管理', icon: 'sms'},
    alwaysShow: true,
    children: [
      {
        path: 'pushorder',
        name: 'pushorder',
        component: () => import('@/views/oms/pushorder/index'),
        meta: {title: '放单列表', icon: 'sms-coupon'}
      }
    ]
  },

  {path: '*', redirect: '/404', hidden: true}
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

