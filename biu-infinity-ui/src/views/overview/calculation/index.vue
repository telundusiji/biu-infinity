<template>
  <d2-container>
    <template slot="header"></template>
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card" header="Running任务" shadow="always">
          {{ yarn.runningApps }}
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="Pending任务" shadow="always">
          <div class="text item">
            {{ yarn.pendingApps}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="总任务" shadow="always">
          <div class="text item">
            {{yarn.submittedApps }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="Failed任务" shadow="always">
          <div class="text item">
            {{ yarn.failedApps}}
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top:15px">
      <el-col :span="6">
        <el-card class="box-card" header="活跃节点" shadow="always">
          <div class="text item">
            {{ yarn.liveNodeManagerNums }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="死亡节点" shadow="always">
          <div class="text item">
            {{yarn.deadNodeManagerNums }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="不健康节点" shadow="always">
          <div class="text item">
            {{yarn.unhealthyNodeManagerNums }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="kill任务" shadow="always">
          <div class="text item">
            {{yarn.killedApps}}
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top:15px">
      <el-col :span="6">
        <el-card class="box-card" header="可用Mem" shadow="always">
          <div class="text item">
            {{ yarn.availableMem }}MB
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="可用Vcore" shadow="always">
          <div class="text item">
            {{yarn.availableCores }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="分配Mem" shadow="always">
          <div class="text item">
            {{yarn.allocatedMem }}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card" header="分配Vcore" shadow="always">
          <div class="text item">
            {{yarn.allocatedCores}}
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top:15px">
      <el-card header="队列状态柱状图" shadow="always">
        <template>
          <ve-histogram :data="queueChartData" :settings="queueChartSettings"></ve-histogram>
        </template>
      </el-card>
    </el-row>

    <el-row style="margin-top:15px">
      <el-card header="计算资源变化曲线" shadow="always">
        <template>
          <div class="inner">
            <ve-line :data="calculationChartData" :settings="calculationChartSettings"></ve-line>
          </div>
        </template>
      </el-card>
    </el-row>

  </d2-container>
</template>

<script>

  import api from '@/api'

  export default {
    name: 'calc',
    data() {
      return {
        queueChartSettings: {
          labelMap: {
            appPending: '排队应用',
            appRunning: '运行应用'
          },
          area: true,
          dimension: ['queueName'],
          metrics: ['appPending', 'appRunning']
        },
        queueChartData: {
          columns: ['queueName', 'appPending', 'appRunning', 'allocatedMB', 'availableMB', 'ActiveUsers'],
          rows: [
            {
              'queueName': 'queue1',
              'appPending': 1,
              'appRunning': 3,
              'allocatedMB': 100,
              'availableMB': 20,
              'ActiveUsers': 2
            },
            {
              'queueName': 'queue2',
              'appPending': 2,
              'appRunning': 4,
              'allocatedMB': 80,
              'availableMB': 10,
              'ActiveUsers': 1
            },
            {
              'queueName': 'queue3',
              'appPending': 2,
              'appRunning': 3,
              'allocatedMB': 150,
              'availableMB': 80,
              'ActiveUsers': 6
            }
          ]
        },
        calculationChartSettings: {
          labelMap: {
            allocatedCores: '使用核心数',
            allocatedMem: '使用内存'
          },
          area: true,
          dimension: ['createTime'],
          metrics: ['allocatedCores', 'allocatedMem'],
          axisSite: {right: ['allocatedCores']},
          yAxisName: ['MB', '核心数']
        },
        calculationChartData: {
          columns: ["liveNodeManagerNums", "deadNodeManagerNums", "unhealthyNodeManagerNums", "submittedApps", "runningApps", "pendingApps", "completedApps", "killedApps", "failedApps", "allocatedMem", "allocatedCores", "allocatedContainers", "availableMem", "availableCores", "pendingMem", "pendingCores", "pendingContainers", "reservedMem", "reservedCores", "reservedContainers", "createTime"],
          rows: [
            {
              "liveNodeManagerNums": 1,
              "deadNodeManagerNums": 0,
              "unhealthyNodeManagerNums": 0,
              "submittedApps": 0,
              "runningApps": 0,
              "pendingApps": 0,
              "completedApps": 0,
              "killedApps": 0,
              "failedApps": 0,
              "allocatedMem": 0,
              "allocatedCores": 0,
              "allocatedContainers": 0,
              "availableMem": 8192,
              "availableCores": 8,
              "pendingMem": 0,
              "pendingCores": 0,
              "pendingContainers": 0,
              "reservedMem": 0,
              "reservedCores": 0,
              "reservedContainers": 0,
              "createTime": "2020-08-07T06:58:00.000+0000"
            }
          ]
        },
        yarn: {
          'liveNodeManagerNums': 10,
          'deadNodeManagerNums': 0,
          'unhealthyNodeManagerNums': 0,
          'submittedApps': 20,
          'runningApps': 10,
          'pendingApps': 0,
          'completedApps': 10,
          'killedApps': 0,
          'failedApps': 0,
          'allocatedMem': 20,
          'allocatedCores': 10,
          'allocatedContainers': 0,
          'availableMem': 10,
          'availableCores': 0,
          'pendingMem': 0,
          'pendingCores': 20,
          'pendingContainers': 10,
          'reservedMem': 0,
          'reservedCores': 10,
          'reservedContainers': 0
        }
      }
    },
    mounted() {
      api.CalculationInfo()
        .then(res => {
          this.yarn = res
        })
      api.queueMetricsChart()
        .then(res => {
          console.log(res)
          // this.chartSettings = res.chartSettings
          this.queueChartData = res
        })
      api.CalculationChart()
        .then(res => {
          console.log(res)
          // this.chartSettings = res.chartSettings
          this.calculationChartData = res
        })
    }

  }
</script>
