export default ({ service, request, serviceForMock, requestForMock, mock, faker, tools }) => ({
  /**
   * @description 请求项目中的文件
   * @param {String} url 文件地址
   */
  CalculationInfo (url = '/cluster/information/calculation') {
    return request({
      url,
      method: 'get'
    })
  },
  CalculationChart (url = '/cluster/information/calculation/chart') {
    return request({
      url,
      method: 'get'
    })
  },
  queueMetricsChart (url = '/cluster/information/queueMetrics') {
    return request({
      url,
      method: 'get'
    })
  }
})
