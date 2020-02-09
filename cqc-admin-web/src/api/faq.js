import request from '@/utils/request'
export function fetchList(params) {
  return request({
    url:'/faq/list',
    method:'get',
    params:params
  })
}


export function add(data) {
  return request({
    url:'/faq/add',
    method:'post',
    data: data
  })
}

export function edit(data) {
  return request({
    url:'/faq/edit',
    method:'post',
    data: data
  })
}
