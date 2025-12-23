<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="学生ID" prop="studentId">-->
<!--        <el-input-->
<!--          v-model="queryParams.studentId"-->
<!--          placeholder="请输入学生ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="房间ID" prop="roomId">-->
<!--        <el-input-->
<!--          v-model="queryParams.roomId"-->
<!--          placeholder="请输入房间ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="床位编号" prop="bedNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.bedNumber"-->
<!--          placeholder="请输入床位编号"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="入住日期" prop="checkInDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.checkInDate"-->
<!--          type="date"-->
<!--          value-format="YYYY-MM-DD"-->
<!--          placeholder="请选择入住日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="退宿日期" prop="checkOutDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.checkOutDate"-->
<!--          type="date"-->
<!--          value-format="YYYY-MM-DD"-->
<!--          placeholder="请选择退宿日期">-->
<!--        </el-date-picker>-->
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


      <el-form-item label="楼栋编号" prop="buildingId">
        <el-select
            v-model="queryParams.buildingId"
            placeholder="请选择楼栋编号"
            clearable
            filterable>
          <el-option
              v-for="building in data.buildingList"
              :key="building.buildingId"
              :label="building.buildingCode"
              :value="building.buildingId">
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



      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
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

    <el-table v-loading="loading" :data="allocationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="分配ID" align="center" prop="allocationId" />-->
<!--      <el-table-column label="学生ID" align="center" prop="studentId" />-->
<!--      <el-table-column label="房间ID" align="center" prop="roomId" />-->
      <!-- 替换或添加以下列 -->
      <el-table-column label="学生学号" align="center" prop="studentNumber" />
      <el-table-column label="学生姓名" align="center" prop="studentName" />
      <el-table-column label="楼栋编号" align="center" prop="buildingCode" />
      <el-table-column label="房间号" align="center" prop="roomNumber" />


<!--      <el-table-column label="床位编号" align="center" prop="bedNumber" />-->
      <el-table-column label="入住日期" align="center" prop="checkInDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.checkInDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="退宿日期" align="center" prop="checkOutDate" width="180">-->
<!--        <template #default="scope">-->
<!--          <span>{{ parseTime(scope.row.checkOutDate, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <el-tag v-if="scope.row.status === '0'" type="success">入住</el-tag>
          <el-tag v-else-if="scope.row.status === '1'" type="danger">退宿</el-tag>
          <span v-else>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="状态(0入住;1退宿)" align="center" prop="status" />-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button
              link
              type="primary"
              icon="Refresh"
              @click="handleChangeStatus(scope.row)">
            {{ scope.row.status === '0' ? '退宿' : '入住' }}
          </el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)">删除</el-button>
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

    <!-- 添加或修改住宿分配对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="allocationRef" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="学生ID" prop="studentId">-->
<!--          <el-input v-model="form.studentId" placeholder="请输入学生ID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="房间ID" prop="roomId">-->
<!--          <el-input v-model="form.roomId" placeholder="请输入房间ID" />-->
<!--        </el-form-item>-->

        <!-- 替换原有的学生ID、房间ID等字段 -->
<!--        <el-form-item label="学生学号" prop="studentId">-->
<!--          <el-input v-model="form.studentId" placeholder="请输入学生学号" />-->
<!--        </el-form-item>-->
        <el-form-item label="学生学号" prop="studentNumber">
          <el-input v-model="form.studentNumber" placeholder="请输入学生学号" />
        </el-form-item>

<!--        <el-form-item label="学生姓名" prop="studentName">-->
<!--          <el-input v-model="form.studentName" placeholder="请输入学生姓名" />-->
<!--        </el-form-item>-->
        <el-form-item label="楼栋编号" prop="buildingId">
          <el-select v-model="form.buildingId" placeholder="请选择楼栋编号" @change="handleBuildingChange">
            <el-option
                v-for="building in data.buildingOptions"
                :key="building.buildingId"
                :label="building.buildingCode"
                :value="building.buildingId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="房间号" prop="roomId">
          <el-select v-model="form.roomId" placeholder="请选择房间号">
            <el-option
                v-for="room in data.roomOptions"
                :key="room.roomId"
                :label="room.roomNumber"
                :value="room.roomId">
            </el-option>
          </el-select>
        </el-form-item>


<!--        <el-form-item label="床位编号" prop="bedNumber">-->
<!--          <el-input v-model="form.bedNumber" placeholder="请输入床位编号" />-->
<!--        </el-form-item>-->
        <el-form-item label="入住日期" prop="checkInDate">
          <el-date-picker clearable
            v-model="form.checkInDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择入住日期">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="退宿日期" prop="checkOutDate">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.checkOutDate"-->
<!--            type="date"-->
<!--            value-format="YYYY-MM-DD"-->
<!--            placeholder="请选择退宿日期">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option label="入住" value="0"></el-option>
            <el-option label="退宿" value="1"></el-option>
          </el-select>
        </el-form-item>
        <!--        <el-form-item label="删除标志" prop="delFlag">-->
<!--          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />-->
<!--        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
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

<script setup name="Allocation">
import { listAllocation, getAllocation, delAllocation, addAllocation, updateAllocation } from "@/api/manage/allocation"

// 添加以下导入
import { listBuilding } from "@/api/manage/building"
import { listRoom } from "@/api/manage/room"



const { proxy } = getCurrentInstance()

const allocationList = ref([])
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
    studentId: null,
    roomId: null,
    bedNumber: null,
    checkInDate: null,
    checkOutDate: null,
    status: null,
    studentNumber: null,  // 添加学生学号查询参数
    studentName: null,    // 添加学生姓名查询参数
    buildingCode: null,   // 添加楼栋编号查询参数
    roomNumber: null,      // 添加房间号查询参数

  },
  rules: {
    studentId: [
      { required: true, message: "学生ID不能为空", trigger: "blur" }
    ],
    roomId: [
      { required: true, message: "房间ID不能为空", trigger: "blur" }
    ],
    bedNumber: [
      { required: true, message: "床位编号不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "change" }
    ],
    delFlag: [
      { required: true, message: "删除标志不能为空", trigger: "blur" }
    ],
  },
  buildingList: [], // 添加楼栋列表

  // 添加以下内容
  buildingOptions: [],  // 楼栋选项
  roomOptions: []       // 房间选项

})

const { queryParams, form, rules } = toRefs(data)


// 获取楼栋选项
function getBuildingOptions() {
  listBuilding({status: "0"}).then(response => {
    data.buildingOptions = response.rows
  })
}

// 获取房间选项
function getRoomOptions(buildingId) {
  if (!buildingId) {
    data.roomOptions = []
    return
  }

  listRoom({buildingId: buildingId, status: "0"}).then(response => {
    data.roomOptions = response.rows
  })
}

// 楼栋选择变化处理
function handleBuildingChange(buildingId) {
  form.value.roomId = null// 清空已选择的房间
  getRoomOptions(buildingId)// 加载对应楼栋的房间选项
}







/** 查询住宿分配列表 */
function getList() {
  loading.value = true
  listAllocation(queryParams.value).then(response => {
    allocationList.value = response.rows
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
    allocationId: null,
    studentId: null,
    roomId: null,
    bedNumber: null,
    checkInDate: null,
    checkOutDate: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("allocationRef")
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
  // queryParams.value.buildingId = null
  queryParams.value.buildingCode = null
  queryParams.value.roomNumber = null


  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.allocationId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

// /** 新增按钮操作 */
// function handleAdd() {
//   reset()
//   open.value = true
//   title.value = "添加住宿分配"
// }

// 修改 handleAdd 方法
function handleAdd() {
  reset()
  getBuildingOptions()  // 加载楼栋选项
  open.value = true
  title.value = "添加住宿分配"
}


// /** 修改按钮操作 */
// function handleUpdate(row) {
//   reset()
//   const _allocationId = row.allocationId || ids.value
//   getAllocation(_allocationId).then(response => {
//     form.value = response.data
//     open.value = true
//     title.value = "修改住宿分配"
//   })
// }

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _allocationId = row.allocationId || ids.value
  getAllocation(_allocationId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改住宿分配"

    // 清空roomId，使房间号下拉框显示"请选择房间号"
    form.value.roomId = null

    // 添加以下代码：加载楼栋和房间选项数据
    getBuildingOptions() // 加载楼栋选项

    // 如果已有楼栋ID，加载对应的房间选项
    if (form.value.buildingId) {
      getRoomOptions(form.value.buildingId)
    }
  })
}




/** 提交按钮 */
function submitForm() {
  proxy.$refs["allocationRef"].validate(valid => {
    if (valid) {
      if (form.value.allocationId != null) {
        updateAllocation(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addAllocation(form.value).then(response => {
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
  const _allocationIds = row.allocationId || ids.value
  proxy.$modal.confirm('是否确认删除住宿分配编号为"' + _allocationIds + '"的数据项？').then(function() {
    return delAllocation(_allocationIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}


// 添加状态修改方法
function handleChangeStatus(row) {
  const statusText = row.status === '0' ? '退宿' : '入住';
  proxy.$modal.confirm(`确认要将该学生状态修改为${statusText}吗？`).then(() => {
    // 创建一个只包含需要更新字段的对象
    const updateData = {
      allocationId: row.allocationId,
      status: row.status === '0' ? '1' : '0', // 切换状态
      // 可以添加退宿日期等其他字段
      checkOutDate: row.status === '0' ? new Date().toISOString().slice(0, 10) : row.checkOutDate
    };

    return updateAllocation(updateData);
  }).then(() => {
    proxy.$modal.msgSuccess("状态修改成功");
    getList(); // 刷新列表
  }).catch(() => {});
}


function getBuildingList() {
  listBuilding({}).then(response => {
    data.buildingList = response.rows || response.data || []
  })
}



/** 导出按钮操作 */
function handleExport() {
  proxy.download('manage/allocation/export', {
    ...queryParams.value
  }, `allocation_${new Date().getTime()}.xlsx`)
}

getList()
getBuildingList() // 添加楼栋列表加载
</script>
