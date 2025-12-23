<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">

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


      <!--      <el-form-item label="楼栋ID" prop="buildingId">-->
<!--        <el-input-->
<!--          v-model="queryParams.buildingId"-->
<!--          placeholder="请输入所属楼栋ID"-->
<!--          clearable-->
<!--          @keyup.enter="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="房间号" prop="roomNumber">
        <el-input
          v-model="queryParams.roomNumber"
          placeholder="请输入房间号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="已住人数" prop="currentCount">
        <el-input
          v-model="queryParams.currentCount"
          placeholder="请输入已住人数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="床位数量" prop="maxCount">-->
<!--        <el-input-->
<!--          v-model="queryParams.maxCount"-->
<!--          placeholder="请输入床位数量"-->
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

    <el-table v-loading="loading" :data="roomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="房间ID" align="center" prop="roomId" />-->

      <el-table-column label="楼栋编号" align="center">
        <template #default="scope">
          {{ getBuildingCode(scope.row.buildingId) }}
        </template>
      </el-table-column>

<!--      <el-table-column label="所属楼栋ID" align="center" prop="buildingId" />-->
      <el-table-column label="房间号" align="center" prop="roomNumber" />
      <el-table-column label="房间类型" align="center" prop="type">
        <template #default="scope">
          <el-tag v-if="scope.row.type === '1'" type="primary">4人间</el-tag>
          <el-tag v-else-if="scope.row.type === '2'" type="primary">6人间</el-tag>
          <span v-else>{{ scope.row.type }}</span>
        </template>
      </el-table-column>

      <!--      <el-table-column label="房间类型(1代表4人间;2代表6人间)" align="center" prop="type" />-->
      <el-table-column label="已住人数" align="center" prop="currentCount" />
<!--      <el-table-column label="床位数量" align="center" prop="maxCount" />-->
      <el-table-column label="状态" align="center" prop="status">
        <template #default="scope">
          <el-tag v-if="scope.row.status === '0'" type="success">可入住</el-tag>
          <el-tag v-else-if="scope.row.status === '1'" type="warning">已满员</el-tag>
<!--          <el-tag v-else-if="scope.row.status === '2'" type="danger">维修中</el-tag>-->
          <span v-else>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="状态(0可入住;1已满员;2维修中)" align="center" prop="status" />-->
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

    <!-- 添加或修改宿舍房间对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="roomRef" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="楼栋编号" prop="buildingId">
          <el-select
              v-model="form.buildingId"
              placeholder="请选择所属楼栋"
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


        <!--        <el-form-item label="楼栋ID" prop="buildingId">-->
<!--          <el-input v-model="form.buildingId" placeholder="请输入所属楼栋ID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="房间号" prop="roomNumber">-->
<!--          <el-input v-model="form.roomNumber" placeholder="请输入房间号" />-->
<!--        </el-form-item>-->

        <el-form-item label="房间号" prop="roomNumber">
          <el-input
              v-model="form.roomNumber"
              placeholder="请输入3位房间号"
              oninput="value=value.replace(/[^\d]/g,'').slice(0,3)"
              maxlength="3"
          />
        </el-form-item>


        <el-form-item label="房间类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择房间类型">
            <el-option label="4人间" value="1"></el-option>
            <el-option label="6人间" value="2"></el-option>
          </el-select>
        </el-form-item>


<!--        <el-form-item label="已住人数" prop="currentCount">-->
<!--          <el-input v-model="form.currentCount" placeholder="请输入已住人数" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="床位数量" prop="maxCount">-->
<!--          <el-input v-model="form.maxCount" placeholder="请输入床位数量" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="状态" prop="status">-->
<!--          <el-select v-model="form.status" placeholder="请选择状态">-->
<!--            <el-option label="可入住" value="0"></el-option>-->
<!--            <el-option label="已满员" value="1"></el-option>-->
<!--&lt;!&ndash;            <el-option label="维修中" value="2"></el-option>&ndash;&gt;-->
<!--          </el-select>-->
<!--        </el-form-item>-->

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

<script setup name="Room">
import { listRoom, getRoom, delRoom, addRoom, updateRoom } from "@/api/manage/room"
// 在导入部分添加
import { listBuilding } from "@/api/manage/building"


const { proxy } = getCurrentInstance()

const roomList = ref([])
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
    buildingId: null,
    roomNumber: null,
    type: null,
    currentCount: null,
    maxCount: null,
    status: null,
  },
  rules: {
    // buildingId: [
    //   { required: true, message: "所属楼栋ID不能为空", trigger: "blur" }
    // ],
    buildingId: [
      { required: true, message: "所属楼栋不能为空", trigger: "change" } // 从 blur 改为 change
    ],
    // roomNumber: [
    //   { required: true, message: "房间号不能为空", trigger: "blur" }
    // ],
    roomNumber: [
      { required: true, message: "房间号不能为空", trigger: "blur" },
      { pattern: /^\d{3}$/, message: "房间号必须为3位数字", trigger: "blur" }
    ],

    type: [
      { required: true, message: "房间类型不能为空", trigger: "change" }
    ],
    // currentCount: [
    //   { required: true, message: "已住人数不能为空", trigger: "blur" },
    //   { pattern: /^\d+$/, message: "已住人数必须为数字", trigger: "blur" },
    //   {
    //     validator: validateCurrentCount,
    //     trigger: "blur"
    //   }
    // ],
    // currentCount: [
    //   { required: true, message: "已住人数不能为空", trigger: "blur" }
    // ],
    // currentCount: [
    //   { required: true, message: "已住人数不能为空", trigger: "blur" },
    //   { pattern: /^(0|[1-9][0-9]*)$/, message: "已住人数必须为非负整数", trigger: "blur" },
    //   { validator: validateCurrentCount, trigger: "blur" }
    // ],
    // currentCount: [
    //   { pattern: /^(0|[1-9][0-9]*)$/, message: "已住人数必须为非负整数", trigger: "blur" },
    //   { validator: validateCurrentCount, trigger: "blur" }
    // ],
    maxCount: [
      { required: true, message: "床位数量不能为空", trigger: "blur" }
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

// // 添加自定义验证函数
// function validateCurrentCount(rule, value, callback) {
//   if (value === "" || value === null || value === undefined) {
//     callback(new Error("已住人数不能为空"));
//     return;
//   }
//
//   // 检查是否为数字
//   if (!/^(0|[1-9][0-9]*)$/.test(value)) {
//     callback(new Error("已住人数必须为非负整数"));
//     return;
//   }
//
//   const currentCount = parseInt(value);
//   const roomType = form.value.type;
//
//   if (!roomType) {
//     callback(new Error("请先选择房间类型"));
//     return;
//   }
//
//   if (roomType === "1") {
//     // 4人间
//     if (currentCount < 0 || currentCount > 4) {
//       callback(new Error("已住人数应当小于等于所选房间类型的人数(4人间最多住4人)"));
//       return;
//     }
//   } else if (roomType === "2") {
//     // 6人间
//     if (currentCount < 0 || currentCount > 6) {
//       callback(new Error("已住人数应当小于等于所选房间类型的人数(6人间最多住6人)"));
//       return;
//     }
//   }
//
//   callback();
// }


// // 修改 validateCurrentCount 函数，只在有值时校验
// function validateCurrentCount(rule, value, callback) {
//   // 如果用户没有输入值，则不进行校验
//   if (value === "" || value === null || value === undefined) {
//     callback();
//     return;
//   }
//
//   // 检查是否为数字
//   if (!/^(0|[1-9][0-9]*)$/.test(value)) {
//     callback(new Error("已住人数必须为非负整数"));
//     return;
//   }
//
//   const currentCount = parseInt(value);
//   const roomType = form.value.type;
//
//   if (!roomType) {
//     callback(new Error("请先选择房间类型"));
//     return;
//   }
//
//   if (roomType === "1") {
//     // 4人间
//     if (currentCount < 0 || currentCount > 4) {
//       callback(new Error("已住人数应当小于等于所选房间类型的人数(4人间最多住4人)"));
//       return;
//     }
//   } else if (roomType === "2") {
//     // 6人间
//     if (currentCount < 0 || currentCount > 6) {
//       callback(new Error("已住人数应当小于等于所选房间类型的人数(6人间最多住6人)"));
//       return;
//     }
//   }
//
//   callback();
// }




// // 监听房间类型变化，重新校验已住人数
// watch(() => form.value.type, (newType) => {
//   if (newType && form.value.currentCount !== null && form.value.currentCount !== undefined) {
//     proxy.$refs.roomRef.validateField("currentCount");
//   }
// });




// 添加获取楼栋列表的方法
function getBuildingList() {
  listBuilding({}).then(response => {
    data.buildingList = response.rows || response.data || []
  })
}

// 添加获取楼栋编号的函数
function getBuildingCode(buildingId) {
  if (!data.buildingList || data.buildingList.length === 0) return buildingId
  const building = data.buildingList.find(item => item.buildingId === buildingId)
  return building ? building.buildingCode : buildingId
}



/** 查询宿舍房间列表 */
function getList() {
  loading.value = true
  listRoom(queryParams.value).then(response => {
    roomList.value = response.rows
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
    roomId: null,
    buildingId: null,
    roomNumber: null,
    type: null,
    currentCount: null,
    maxCount: null,
    status: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("roomRef")
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
  ids.value = selection.map(item => item.roomId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加宿舍房间"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _roomId = row.roomId || ids.value
  getRoom(_roomId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改宿舍房间"
  })
}

// /** 提交按钮 */
// function submitForm() {
//   proxy.$refs["roomRef"].validate(valid => {
//     if (valid) {
//       if (form.value.roomId != null) {
//         updateRoom(form.value).then(response => {
//           proxy.$modal.msgSuccess("修改成功")
//           open.value = false
//           getList()
//         })
//       } else {
//         addRoom(form.value).then(response => {
//           proxy.$modal.msgSuccess("新增成功")
//           open.value = false
//           getList()
//         })
//       }
//     }
//   })
// }

// /** 提交按钮 */
// function submitForm() {
//   proxy.$refs["roomRef"].validate(valid => {
//     if (valid) {
//       if (form.value.roomId != null) {
//         updateRoom(form.value).then(response => {
//           proxy.$modal.msgSuccess("修改成功")
//           open.value = false
//           getList()
//         }).catch(error => {
//           if (error.msg) {
//             proxy.$modal.msgError(error.msg)
//           } else {
//             proxy.$modal.msgError("修改失败")
//           }
//         })
//       } else {
//         addRoom(form.value).then(response => {
//           proxy.$modal.msgSuccess("新增成功")
//           open.value = false
//           getList()
//         }).catch(error => {
//           if (error.msg) {
//             proxy.$modal.msgError(error.msg)
//           } else {
//             proxy.$modal.msgError("新增失败")
//           }
//         })
//       }
//     }
//   })
// }


/** 提交按钮 */
function submitForm() {
  proxy.$refs["roomRef"].validate(valid => {
    if (valid) {
      if (form.value.roomId != null) {
        updateRoom(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        }).catch(error => {
          if (error.msg) {
            proxy.$modal.msgError(error.msg)
          } else {
            proxy.$modal.msgError("修改失败")
          }
        })
      } else {
        addRoom(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        }).catch(error => {
          if (error.msg) {
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
  const _roomIds = row.roomId || ids.value
  proxy.$modal.confirm('是否确认删除宿舍房间编号为"' + _roomIds + '"的数据项？').then(function() {
    return delRoom(_roomIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('manage/room/export', {
    ...queryParams.value
  }, `room_${new Date().getTime()}.xlsx`)
}

getList()
getBuildingList() // 添加楼栋列表加载

</script>
