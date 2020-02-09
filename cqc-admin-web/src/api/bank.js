import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/bank/list',
    method:'get',
    params:params
  })
}

export function add(data) {
  return request({
    url:'/bank/add',
    method:'post',
    data:data
  })
}

