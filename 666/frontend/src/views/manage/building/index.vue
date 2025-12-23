<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="楼栋名称" prop="buildingName">
        <el-input
          v-model="queryParams.buildingName"
          placeholder="请输入楼栋名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="楼栋编号" prop="buildingCode">
        <el-input
          v-model="queryParams.buildingCode"
          placeholder="请输入楼栋编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入详细地址"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="管理员ID" prop="adminId">-->
<!--        <el-input-->
<!--          v-model="queryParams.adminId"-->
<!--          placeholder="请输入管理员ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="联系电话" prop="contactPhone">-->
<!--        <el-input-->
<!--          v-model="queryParams.contactPhone"-->
<!--          placeholder="请输入联系电话"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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

    <el-table v-loading="loading" :data="buildingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="楼栋ID" align="center" prop="buildingId" />-->
      <el-table-column label="楼栋名称" align="center" prop="buildingName" />
      <el-table-column label="楼栋编号" align="center" prop="buildingCode" />
      <el-table-column label="详细地址" align="center" prop="address" />
      <el-table-column label="管理员" align="center">
        <template #default="scope">
          {{ getAdminName(scope.row.adminId) }}
        </template>
      </el-table-column>


      <!--      <el-table-column label="管理员ID" align="center" prop="adminId" />-->
      <el-table-column label="联系电话" align="center" prop="contactPhone" />
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <el-tag v-if="scope.row.status === '0'" type="success">正常</el-tag>
          <el-tag v-else-if="scope.row.status === '1'" type="danger">停用</el-tag>
          <span v-else>{{ scope.row.status }}</span>
        </template>
      </el-table-column>


      <!--      <el-table-column label="状态(0正常;1停用)" align="center" prop="status" />-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)">修改</el-button>
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

    <!-- 添加或修改宿舍楼对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="buildingRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="楼栋名称" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入楼栋名称" />
        </el-form-item>
        <el-form-item label="楼栋编号" prop="buildingCode">
          <el-input v-model="form.buildingCode" placeholder="请输入楼栋编号" />
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入详细地址" />
        </el-form-item>

        <el-form-item label="管理员" prop="adminId">
          <el-select v-model="form.adminId" placeholder="请选择管理员">
            <el-option label="宿管1" value="3"></el-option>
            <el-option label="宿管2" value="4"></el-option>
          </el-select>
        </el-form-item>


        <!--        <el-form-item label="管理员ID" prop="adminId">-->
<!--          <el-input v-model="form.adminId" placeholder="请输入管理员ID" />-->
<!--        </el-form-item>-->
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option label="正常" value="0"></el-option>
            <el-option label="停用" value="1"></el-option>
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

<script setup name="Building">
import { listBuilding, getBuilding, delBuilding, addBuilding, updateBuilding } from "@/api/manage/building"

const { proxy } = getCurrentInstance()

const buildingList = ref([])
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
    buildingName: null,
    buildingCode: null,
    address: null,
    adminId: null,
    contactPhone: null,
    status: null,
  },
  rules: {
    buildingName: [
      { required: true, message: "楼栋名称不能为空", trigger: "blur" }
    ],
    buildingCode: [
      { required: true, message: "楼栋编号不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "change" }
    ],
    delFlag: [
      { required: true, message: "删除标志不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询宿舍楼列表 */
function getList() {
  loading.value = true
  listBuilding(queryParams.value).then(response => {
    buildingList.value = response.rows
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
    buildingId: null,
    buildingName: null,
    buildingCode: null,
    address: null,
    adminId: null,
    contactPhone: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("buildingRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.buildingId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加宿舍楼"
}

// /** 修改按钮操作 */
// function handleUpdate(row) {
//   reset()
//   const _buildingId = row.buildingId || ids.value
//   getBuilding(_buildingId).then(response => {
//     form.value = response.data
//     open.value = true
//     title.value = "修改宿舍楼"
//   })
// }

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _buildingId = row.buildingId || ids.value
  getBuilding(_buildingId).then(response => {
    form.value = response.data
    // 确保 adminId 是字符串类型以匹配 el-option 的 value
    if (form.value.adminId) {
      form.value.adminId = String(form.value.adminId)
    }
    open.value = true
    title.value = "修改宿舍楼"
  })
}





// /** 提交按钮 */
// function submitForm() {
//   proxy.$refs["buildingRef"].validate(valid => {
//     if (valid) {
//       if (form.value.buildingId != null) {
//         updateBuilding(form.value).then(response => {
//           proxy.$modal.msgSuccess("修改成功")
//           open.value = false
//           getList()
//         })
//       } else {
//         addBuilding(form.value).then(response => {
//           proxy.$modal.msgSuccess("新增成功")
//           open.value = false
//           getList()
//         })
//       }
//     }
//   })
// }


/** 提交按钮 */
function submitForm() {
  proxy.$refs["buildingRef"].validate(valid => {
    if (valid) {
      if (form.value.buildingId != null) {
        updateBuilding(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        }).catch(error => {
          // 处理后端返回的错误信息
          if (error.response && error.response.data && error.response.data.msg) {
            proxy.$modal.msgError(error.response.data.msg)
          } else if (error.msg) {
            proxy.$modal.msgError(error.msg)
          } else {
            proxy.$modal.msgError("修改失败")
          }
        })
      } else {
        addBuilding(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        }).catch(error => {
          // 处理后端返回的错误信息
          if (error.response && error.response.data && error.response.data.msg) {
            proxy.$modal.msgError(error.response.data.msg)
          } else if (error.msg) {
            proxy.$modal.msgError(error.msg)
          } else {
            proxy.$modal.msgError("新增失败")
          }
        })
      }
    }
  })
}



/** 删除按钮操作 */
function handleDelete(row) {
  const _buildingIds = row.buildingId || ids.value
  proxy.$modal.confirm('是否确认删除宿舍楼编号为"' + _buildingIds + '"的数据项？').then(function() {
    return delBuilding(_buildingIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('manage/building/export', {
    ...queryParams.value
  }, `building_${new Date().getTime()}.xlsx`)
}

// 管理员ID转宿管名称
function getAdminName(adminId) {
  const adminMap = {
    3: '宿管1',
    4: '宿管2'
  }
  return adminMap[adminId] || adminId
}

getList()
</script>
