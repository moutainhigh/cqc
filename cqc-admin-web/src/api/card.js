import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/bankCard/list',
    method:'get',
    params:params
  })
}
