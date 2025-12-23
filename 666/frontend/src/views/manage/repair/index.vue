<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="房间ID" prop="roomId">-->
<!--        <el-input-->
<!--          v-model="queryParams.roomId"-->
<!--          placeholder="请输入报修房间ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="报修人ID" prop="reportUserId">-->
<!--        <el-input-->
<!--          v-model="queryParams.reportUserId"-->
<!--          placeholder="请输入报修人ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="维修工ID" prop="workerUserId">-->
<!--        <el-input-->
<!--          v-model="queryParams.workerUserId"-->
<!--          placeholder="请输入指派维修工ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

      <el-form-item label="学生学号" prop="studentNumber">
        <el-input
            v-model="queryParams.studentNumber"
            placeholder="请输入学生学号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
            v-model="queryParams.studentName"
            placeholder="请输入学生姓名"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="楼栋编号" prop="buildingCode">-->
<!--        <el-input-->
<!--            v-model="queryParams.buildingCode"-->
<!--            placeholder="请输入楼栋编号"-->
<!--            clearable-->
<!--            @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->


      <el-form-item label="楼栋编号" prop="buildingCode">
        <el-select
            v-model="queryParams.buildingCode"
            placeholder="请选择楼栋编号"
            clearable
            filterable>
          <el-option
              v-for="building in data.buildingList"
              :key="building.buildingId"
              :label="building.buildingCode"
              :value="building.buildingCode">
          </el-option>
        </el-select>
      </el-form-item>




      <el-form-item label="房间号" prop="roomNumber">
        <el-input
            v-model="queryParams.roomNumber"
            placeholder="请输入房间号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维修工" prop="workerName">
        <el-select v-model="queryParams.workerName" placeholder="请选择维修工" clearable @change="handleQuery">
          <el-option label="维修工1" value="维修工1"></el-option>
          <el-option label="维修工2" value="维修工2"></el-option>
        </el-select>
      </el-form-item>








      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="Plus"-->
<!--          @click="handleAdd"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="repairList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="报修ID" align="center" prop="repairId" />-->
<!--      <el-table-column label="报修房间ID" align="center" prop="roomId" />-->
<!--      <el-table-column label="报修人ID" align="center" prop="reportUserId" />-->

      <!-- 替换原有的房间ID和报修人ID列 -->
      <el-table-column label="楼栋编号" align="center" prop="buildingCode" />
      <el-table-column label="房间号" align="center" prop="roomNumber" />
      <el-table-column label="学生学号" align="center" prop="studentNumber" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />



      <el-table-column label="问题描述" align="center" prop="description" />
<!--      <el-table-column label="报修图片URL" align="center" prop="images" />-->
<!--      <el-table-column label="维修工ID" align="center" prop="workerUserId" />-->
      <el-table-column label="维修工" align="center">
        <template #default="scope">
          {{ getWorkerName(scope.row.workerUserId) }}
        </template>
      </el-table-column>


      <el-table-column label="维修反馈" align="center" prop="feedback" />
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <el-tag :type="scope.row.status === '0' ? 'warning' : scope.row.status === '1' ? 'primary' : 'success'">
            {{ scope.row.status === '0' ? '待处理' : scope.row.status === '1' ? '维修中' : '已完成' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
<!--          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改维修报修对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="repairRef" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="房间ID" prop="roomId">-->
<!--          <el-input v-model="form.roomId" placeholder="请输入报修房间ID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="报修人ID" prop="reportUserId">-->
<!--          <el-input v-model="form.reportUserId" placeholder="请输入报修人ID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="问题描述" prop="description">-->
<!--          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="报修图片URL" prop="images">-->
<!--          <el-input v-model="form.images" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="维修工ID" prop="workerUserId">-->
<!--          <el-input v-model="form.workerUserId" placeholder="维修工ID" />-->
<!--        </el-form-item>-->

        <el-form-item label="维修工" prop="workerUserId">
          <el-select v-model="form.workerUserId" placeholder="请选择维修工">
            <el-option label="维修工1" value="5"></el-option>
            <el-option label="维修工2" value="6"></el-option>
          </el-select>
        </el-form-item>


<!--        <el-form-item label="维修反馈" prop="feedback">-->
<!--          <el-input v-model="form.feedback" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->

        <el-form-item label="维修状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择维修状态">
            <el-option label="待处理" value="0"></el-option>
            <el-option label="维修中" value="1"></el-option>
            <el-option label="已完成" value="2"></el-option>
          </el-select>
        </el-form-item>


        <!--        <el-form-item label="维修状态" prop="status">-->
<!--          <el-input v-model="form.status" type="textarea" placeholder="0:待处理;1:维修中;2:已完成" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="备注" prop="remark">-->
<!--          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Repair">
import { listRepair, getRepair, delRepair, addRepair, updateRepair } from "@/api/manage/repair"

import { listBuilding } from "@/api/manage/building"


const { proxy } = getCurrentInstance()

const repairList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    roomId: null,
    reportUserId: null,
    description: null,
    images: null,
    workerUserId: null,
    feedback: null,
    status: null,
    studentNumber: null,  // 添加学生学号查询参数
    studentName: null,    // 添加学生姓名查询参数
    buildingCode: null,   // 添加楼栋编号查询参数
    roomNumber: null,     // 添加房间号查询参数
    workerName: null      // 添加维修工查询参数

  },
  rules: {
    roomId: [
      { required: true, message: "报修房间ID不能为空", trigger: "blur" }
    ],
    reportUserId: [
      { required: true, message: "报修人ID不能为空", trigger: "blur" }
    ],
    description: [
      { required: true, message: "问题描述不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "change" }
    ],
    delFlag: [
      { required: true, message: "删除标志不能为空", trigger: "blur" }
    ],
  },
  buildingList: [] // 添加楼栋列表

})

const { queryParams, form, rules } = toRefs(data)

/** 查询维修报修列表 */
function getList() {
  loading.value = true
  listRepair(queryParams.value).then(response => {
    repairList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    repairId: null,
    roomId: null,
    reportUserId: null,
    description: null,
    images: null,
    workerUserId: null,
    feedback: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("repairRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")

  // 重置新增的查询参数
  queryParams.value.studentNumber = null
  queryParams.value.studentName = null
  // queryParams.value.buildingCode = null
  queryParams.value.buildingId = null

  queryParams.value.roomNumber = null
  queryParams.value.workerName = null



  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.repairId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加维修报修"
}

// /** 修改按钮操作 */
// function handleUpdate(row) {
//   reset()
//   const _repairId = row.repairId || ids.value
//   getRepair(_repairId).then(response => {
//     form.value = response.data
//     open.value = true
//     title.value = "修改维修报修"
//   })
// }

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _repairId = row.repairId || ids.value
  getRepair(_repairId).then(response => {
    form.value = response.data
    // 确保 workerUserId 是字符串类型以匹配 el-option 的 value
    if (form.value.workerUserId) {
      form.value.workerUserId = String(form.value.workerUserId)
    }
    open.value = true
    title.value = "修改维修报修"
  })
}



/** 提交按钮 */
function submitForm() {
  proxy.$refs["repairRef"].validate(valid => {
    if (valid) {
      if (form.value.repairId != null) {
        updateRepair(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addRepair(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _repairIds = row.repairId || ids.value
  proxy.$modal.confirm('是否确认删除维修报修编号为"' + _repairIds + '"的数据项？').then(function() {
    return delRepair(_repairIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('manage/repair/export', {
    ...queryParams.value
  }, `repair_${new Date().getTime()}.xlsx`)
}

// 维修工ID转维修工名称
function getWorkerName(workerId) {
  const workerMap = {
    5: '维修工1',
    6: '维修工2'
  }
  return workerMap[workerId] || workerId
}

// 添加获取楼栋列表的方法
function getBuildingList() {
  listBuilding({}).then(response => {
    data.buildingList = response.rows || response.data || []
  })
}






getList()
getBuildingList() // 添加楼栋列表加载


</script>
