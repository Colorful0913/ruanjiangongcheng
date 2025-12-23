<template>
  <div class="app-container home">
    <div class="header">
      <h1>华东交通大学宿舍管理系统</h1>
      <p class="welcome-msg">欢迎回家，愿你在校园里度过美好时光</p>
    </div>

    <div class="content-wrapper">
      <div class="left-content">
        <div class="message-card">
          <h2>温馨寄语</h2>
          <div class="message-content">
            <p>亲爱的同学们：</p>
            <p>欢迎回到华东交通大学这个温暖的大家庭。宿舍不仅是你们休息的地方，更是你们在校园里的家。</p>
            <p>在这里，你们将与来自五湖四海的室友相遇，共同学习、生活，一起成长。希望你们能够珍惜这份缘分，相互理解、相互帮助。</p>
            <p>愿你们在新的一学期里：</p>
            <ul>
              <li>学业进步，成绩优异</li>
              <li>身体健康，心情愉快</li>
              <li>与室友和睦相处，共创美好回忆</li>
              <li>积极参与校园活动，丰富大学生活</li>
            </ul>
            <p>无论遇到什么困难，请记住，宿舍管理中心的老师们始终在这里支持你们，为你们提供帮助。</p>
<!--            <p class="blessing">祝愿大家在华东交通大学度过充实而快乐的校园时光！</p>-->
          </div>

          <!-- 添加饼状图容器 -->
          <div class="chart-card">
            <h3>各楼栋住宿人数分布</h3>
            <div ref="pieChartRef" style="width: 100%; height: 300px;"></div>
          </div>




        </div>




      </div>

      <div class="right-content">
        <div class="image-container">
          <img src="@/assets/images/campus.jpg" alt="华东交通大学校园" class="campus-image">
          <div class="image-caption">美景欣赏</div>
        </div>

        <div class="quick-info">
          <h3>温馨提示</h3>
          <ul>
            <li>保持宿舍整洁，营造舒适生活环境</li>
            <li>注意用电安全，禁止使用违规电器</li>
            <li>遵守宿舍管理规定，共建和谐社区</li>
            <li>早睡早起，保持健康作息</li>
            <li>互相关爱，营造温馨室友关系</li>
          </ul>
        </div>

        <!-- 添加 ECharts 图表容器 -->
        <div class="chart-card">
          <h3>住宿分配统计</h3>
          <div ref="chartRef" style="width: 100%; height: 300px;"></div>
        </div>




      </div>
    </div>

    <div class="footer">
      <p>华东交通大学宿舍管理中心</p>
      <p>愿每一位学子都能在这里找到属于自己的精彩</p>
    </div>
  </div>
</template>

<script setup name="Index">
import * as echarts from 'echarts'
import { listAllocation } from '@/api/manage/allocation'
import { onMounted, onBeforeUnmount, ref } from 'vue'
import { listBuilding } from '@/api/manage/building'
import { listRoom } from '@/api/manage/room'




function goTarget(url) {
  window.open(url, '__blank')
}

// 创建图表容器的引用
const chartRef = ref()

// 图表实例
let myChart = null


// 添加饼状图容器引用
const pieChartRef = ref()

// 饼状图实例
let pieChart = null



// // 初始化图表
// onMounted(() => {
//   // 初始化 echarts 实例
//   myChart = echarts.init(chartRef.value)
//
//   // 从后端获取住宿分配数据
//   loadAllocationData()
//
//   // 监听窗口大小变化，自适应图表
//   window.addEventListener('resize', () => {
//     myChart.resize()
//   })
// })


// 初始化图表
onMounted(() => {
  // 初始化 echarts 实例
  myChart = echarts.init(chartRef.value)
  pieChart = echarts.init(pieChartRef.value)

  // 从后端获取住宿分配数据
  loadAllocationData()
  loadBuildingDistributionData()

  // 监听窗口大小变化，自适应图表
  window.addEventListener('resize', () => {
    myChart.resize()
    pieChart.resize()
  })
})




// // 组件销毁前清理图表实例
// onBeforeUnmount(() => {
//   if (myChart) {
//     myChart.dispose()
//   }
// })


// 组件销毁前清理图表实例
onBeforeUnmount(() => {
  if (myChart) {
    myChart.dispose()
  }
  if (pieChart) {
    pieChart.dispose()
  }
})



// 加载楼栋分布数据
async function loadBuildingDistributionData() {
  try {
    // 获取所有楼栋
    const buildingResponse = await listBuilding({})
    const buildings = buildingResponse.rows || buildingResponse.data || []

    // 获取所有房间
    const roomResponse = await listRoom({})
    const rooms = roomResponse.rows || roomResponse.data || []

    // 获取住宿分配数据
    const allocationResponse = await listAllocation({})
    const allocations = allocationResponse.rows || allocationResponse.data || []

    // 处理数据，统计各楼栋住宿人数
    const buildingData = processBuildingData(buildings, rooms, allocations)

    // 设置饼状图选项
    const option = {
      title: {
        text: '各楼栋住宿人数分布',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [{
        type: 'pie',
        radius: '50%',
        data: buildingData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }]
    }

    // 使用配置项显示图表
    pieChart.setOption(option)
  } catch (error) {
    console.error('获取楼栋分布数据失败:', error)
  }
}

// 处理楼栋数据
function processBuildingData(buildings, rooms, allocations) {
  // 创建楼栋ID到楼栋编号的映射
  const buildingMap = {}
  buildings.forEach(building => {
    buildingMap[building.buildingId] = building.buildingCode
  })

  // 创建房间ID到楼栋ID的映射
  const roomToBuildingMap = {}
  rooms.forEach(room => {
    roomToBuildingMap[room.roomId] = room.buildingId
  })

  // 统计各楼栋住宿人数
  const buildingCount = {}
  allocations.forEach(allocation => {
    if (allocation.roomId && allocation.status === '0') { // 只统计入住中的学生
      const buildingId = roomToBuildingMap[allocation.roomId]
      if (buildingId) {
        if (!buildingCount[buildingId]) {
          buildingCount[buildingId] = 0
        }
        buildingCount[buildingId]++
      }
    }
  })

  // 转换为图表需要的格式
  const result = []
  Object.keys(buildingCount).forEach(buildingId => {
    const buildingCode = buildingMap[buildingId] || `楼栋${buildingId}`
    result.push({
      value: buildingCount[buildingId],
      name: buildingCode
    })
  })

  return result
}






// 加载住宿分配数据
async function loadAllocationData() {
  try {
    // 获取住宿分配列表
    const response = await listAllocation({})
    const allocations = response.rows || response.data || []

    // 处理数据，按月份统计
    const monthlyData = processMonthlyData(allocations)

    // 设置图表选项
    const option = {
      title: {
        text: '住宿分配趋势'
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: {
        type: 'category',
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
      },
      yAxis: {
        type: 'value',
        name: '分配人数'
      },
      series: [{
        data: monthlyData,
        type: 'line',
        smooth: true,
        itemStyle: { color: '#409EFF' },
        areaStyle: { opacity: 0.3 }
      }]
    }

    // 使用配置项显示图表
    myChart.setOption(option)
  } catch (error) {
    console.error('获取住宿分配数据失败:', error)
    // 出错时显示默认数据
    showDefaultChart()
  }
}

// 处理按月份统计数据
function processMonthlyData(allocations) {
  // 初始化12个月的数据
  const monthlyCount = new Array(12).fill(0)

  // 遍历住宿分配数据，统计每个月的分配数量
  allocations.forEach(allocation => {
    if (allocation.createTime) {
      // 从创建时间中提取月份
      const date = new Date(allocation.createTime)
      const month = date.getMonth() // 0-11 代表 1-12月
      monthlyCount[month]++
    }
  })

  return monthlyCount
}

// 显示默认图表（出错时使用）
function showDefaultChart() {
  const option = {
    title: {
      text: '住宿分配趋势'
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    },
    yAxis: {
      type: 'value',
      name: '分配人数'
    },
    series: [{
      data: [120, 200, 150, 80, 70, 110, 130, 180, 220, 190, 160, 140],
      type: 'line',
      smooth: true,
      itemStyle: { color: '#409EFF' },
      areaStyle: { opacity: 0.3 }
    }]
  }

  myChart.setOption(option)
}
</script>

<style scoped lang="scss">
.home {
  background: linear-gradient(135deg, #f5f7fa 0%, #e4edf9 100%);
  min-height: 100vh;
  padding: 20px;

  .header {
    text-align: center;
    margin-bottom: 30px;
    padding: 30px;
    background: rgba(255, 255, 255, 0.9);
    border-radius: 15px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);

    h1 {
      font-size: 28px;
      margin-bottom: 15px;
      color: #333;
      font-weight: 500;
    }

    .welcome-msg {
      font-size: 18px;
      color: #666;
      font-style: italic;
    }
  }

  .content-wrapper {
    display: flex;
    gap: 30px;
    margin-bottom: 30px;

    .left-content {
      flex: 1;

      .message-card {
        background: rgba(255, 255, 255, 0.9);
        border-radius: 15px;
        padding: 30px;
        box-shadow: 0 6px 25px rgba(0, 0, 0, 0.1);
        height: 100%;

        h2 {
          text-align: center;
          color: #333;
          margin-bottom: 25px;
          font-size: 24px;
          font-weight: 500;
        }

        .message-content {
          font-size: 16px;
          line-height: 1.8;
          color: #555;

          p {
            margin-bottom: 15px;

            &.blessing {
              text-align: center;
              font-weight: bold;
              margin-top: 20px;
              color: #409EFF;
            }
          }

          ul {
            padding-left: 20px;
            margin-bottom: 15px;

            li {
              margin-bottom: 8px;
            }
          }
        }
      }
    }

    .right-content {
      flex: 1;
      display: flex;
      flex-direction: column;
      gap: 30px;

      .image-container {
        background: rgba(255, 255, 255, 0.9);
        border-radius: 15px;
        padding: 20px;
        box-shadow: 0 6px 25px rgba(0, 0, 0, 0.1);
        text-align: center;

        .campus-image {
          width: 100%;
          max-height: 300px;
          border-radius: 10px;
          box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
          object-fit: cover;
        }

        .image-caption {
          margin-top: 15px;
          color: #777;
          font-style: italic;
        }
      }

      .quick-info {
        background: rgba(255, 255, 255, 0.9);
        border-radius: 15px;
        padding: 25px;
        box-shadow: 0 6px 25px rgba(0, 0, 0, 0.1);

        h3 {
          text-align: center;
          color: #333;
          margin-bottom: 20px;
          font-size: 20px;
          font-weight: 500;
        }

        ul {
          padding-left: 20px;

          li {
            margin-bottom: 12px;
            color: #555;
            line-height: 1.5;
          }
        }
      }

      // 添加图表卡片样式
      .chart-card {
        background: rgba(255, 255, 255, 0.9);
        border-radius: 15px;
        padding: 25px;
        box-shadow: 0 6px 25px rgba(0, 0, 0, 0.1);

        h3 {
          text-align: center;
          color: #333;
          margin-bottom: 20px;
          font-size: 20px;
          font-weight: 500;
        }
      }
    }
  }

  .footer {
    text-align: center;
    padding: 20px;
    color: #777;
    font-size: 14px;

    p {
      margin: 8px 0;
    }
  }
}

@media (max-width: 768px) {
  .content-wrapper {
    flex-direction: column;
  }
}
</style>
