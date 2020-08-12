import { uniqueId } from 'lodash'

/**
 * @description 给菜单数据补充上 path 字段
 * @description https://github.com/d2-projects/d2-admin/issues/209
 * @param {Array} menu 原始的菜单数据
 */
function supplementPath (menu) {
  return menu.map(e => ({
    ...e,
    path: e.path || uniqueId('d2-menu-empty-'),
    ...e.children ? {
      children: supplementPath(e.children)
    } : {}
  }))
}

export const menuHeader = supplementPath([
  { path: '/index', title: '首页', icon: 'home' },
])

export const menuAside = supplementPath([
  { path: '/index', title: '首页', icon: 'home' },
  {
    title: '集群概览',
    icon: 'desktop',
    children: [
      { path: '/storage', icon: 'folder-open', title: '存储概览' },
      { path: '/calculation', icon: 'object-group', title: '计算概览' },
      { path: 'http://47.108.140.82:3000', icon: 'magnet', title: '集群监控' }
    ]
  },
  { path: '/query', title: '数据查询', icon: 'hourglass' }
])
