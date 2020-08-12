export default ({ service, request, serviceForMock, requestForMock, mock, faker, tools }) => ({
  /**
   * @description 请求项目中的文件
   * @param {String} url 文件地址
   */
  StorageInfo (url = '/cluster/information/storage') {
    return request({
      url,
      method: 'get'
    })
  },
  StorageChart (url = '/cluster/information/storage/chart') {
    return request({
      url,
      method: 'get'
    })
  }
})
