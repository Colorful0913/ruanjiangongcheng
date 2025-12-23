<template>
  <div class="app-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>我的基本信息</span>
        </div>
      </template>
      <el-descriptions v-if="studentInfo" :column="2" border>
        <el-descriptions-item label="学号">{{ studentInfo.studentNumber }}</el-descriptions-item>
        <el-descriptions-item label="姓名" :span="2">{{ studentInfo.remark || '无' }}</el-descriptions-item>
        <el-descriptions-item label="所属院系">{{ studentInfo.faculty }}</el-descriptions-item>
        <el-descriptions-item label="班级">{{ studentInfo.className }}</el-descriptions-item>
<!--        <el-descriptions-item label="创建时间">{{ parseTime(studentInfo.createTime) }}</el-descriptions-item>-->

      </el-descriptions>
      <el-empty v-else description="暂无学生信息" />
    </el-card>

    <el-card class="box-card" style="margin-top: 20px;">
      <template #header>
        <div class="card-header">
          <span>我的住宿信息</span>
        </div>
      </template>
      <el-descriptions v-if="allocationInfo" :column="2" border>
        <el-descriptions-item label="楼栋编号">{{ allocationInfo.buildingCode }}</el-descriptions-item>
        <el-descriptions-item label="房间号">{{ allocationInfo.roomNumber }}</el-descriptions-item>

<!--        <el-descriptions-item label="床位编号">{{ allocationInfo.bedNumber }}</el-descriptions-item>-->
        <el-descriptions-item label="入住日期">{{ parseTime(allocationInfo.checkInDate, '{y}-{m}-{d}') }}</el-descriptions-item>
<!--        <el-descriptions-item label="退宿日期">{{ allocationInfo.checkOutDate ? parseTime(allocationInfo.checkOutDate, '{y}-{m}-{d}') : '未退宿' }}</el-descriptions-item>-->
        <el-descriptions-item label="状态">
          <el-tag :type="allocationInfo.status === '0' ? 'success' : 'info'">
            {{ allocationInfo.status === '0' ? '入住中' : '已退宿' }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="备注" :span="2">{{ allocationInfo.remark || '无' }}</el-descriptions-item>
      </el-descriptions>
      <el-empty v-else description="暂无住宿分配信息" />
    </el-card>
  </div>
</template>

<script setup name="MyInfo">
import { getMyStudentInfo, getMyAllocationInfo } from "@/api/manage/myInfo"

const { proxy } = getCurrentInstance()

const studentInfo = ref(null)
const allocationInfo = ref(null)
const loading = ref(true)

/** 查询我的信息 */
function getMyInfo() {
  loading.value = true
  Promise.all([
    getMyStudentInfo(),
    getMyAllocationInfo()
  ]).then(([studentResponse, allocationResponse]) => {
    studentInfo.value = studentResponse.data
    allocationInfo.value = allocationResponse.data
  }).finally(() => {
    loading.value = false
  })
}

getMyInfo()
</script>

<style scoped>
.box-card {
  margin-bottom: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>